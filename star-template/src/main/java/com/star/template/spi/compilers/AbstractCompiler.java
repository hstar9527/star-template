package com.star.template.spi.compilers;

import com.star.template.spi.Logger;
import com.star.template.spi.loggers.SimpleLogger;
import com.star.template.util.ClassUtils;
import com.star.template.util.StringUtils;
import com.star.template.spi.Compiler;
import com.star.template.util.VolatileReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCompiler implements Compiler {

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("package\\s+([_a-zA-Z][_a-zA-Z0-9\\.]*);");

    private static final Pattern CLASS_PATTERN = Pattern.compile("class\\s+([_a-zA-Z][_a-zA-Z0-9]*)\\s+");

    private static final ConcurrentMap<String, VolatileReference<Class<?>>> CLASS_CACHE = new ConcurrentHashMap<String, VolatileReference<Class<?>>>();

    protected File codeDirectory;

    protected File compileDirectory;

    protected Logger logger = new SimpleLogger();

    private volatile boolean first = true;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void setCodeDirectory(String codeDirectory) {
        if (codeDirectory != null && codeDirectory.trim().length() > 0) {
            File file = new File(codeDirectory);
            if (file.exists() || file.mkdirs()) {
                this.codeDirectory = file;
            }
        }
    }

    public void setCompileDirectory(String directory) {
        if (directory != null && directory.trim().length() > 0) {
            File file = new File(directory);
            if (file.exists() || file.mkdirs()) {
                this.compileDirectory = file;
            }
        }
    }

    protected void saveBytecode(String name, byte[] bytecode) {
        if (compileDirectory != null) {
            try {
                File file = new File(compileDirectory, name.replace('.', '/') + ".class");
                FileOutputStream out = new FileOutputStream(file);
                try {
                    out.write(bytecode);
                    out.flush();
                } finally {
                    out.close();
                }
                if (first) {
                    first = false;
                    if (logger != null && logger.isInfoEnabled()) {
                        logger.info("Compile template classes to directory " + compileDirectory.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }

    private void logJavaCode(String className, String sorceCode) throws IOException {
        if (logger != null && logger.isDebugEnabled()) {
            logger.debug("\n================================\n" + sorceCode + "\n================================\n");
        }
        if (codeDirectory != null) {
            File javaFile = new File(codeDirectory, className.replace('.', '/') + ".java");
            File javaDir = javaFile.getParentFile();
            if (javaDir.exists() || javaDir.mkdirs()) {
                FileWriter writer = new FileWriter(javaFile);
                try {
                    writer.write(sorceCode);
                    writer.flush();
                } finally {
                    writer.close();
                }
            }
        }
    }

    public Class<?> compile(String code) throws ParseException {
        String className = null;
        try {
            code = code.trim();
            if (!code.endsWith("}")) {
                throw new ParseException("The java code not endsWith \"}\"", code.length() - 1);
            }
            Matcher matcher = PACKAGE_PATTERN.matcher(code);
            String pkg;
            if (matcher.find()) {
                pkg = matcher.group(1);
            } else {
                pkg = "";
            }
            matcher = CLASS_PATTERN.matcher(code);
            String classSimpleName;
            if (matcher.find()) {
                classSimpleName = matcher.group(1);
            } else {
                throw new ParseException("No such class name in java code.", 0);
            }
            className = StringUtils.isNotEmpty(pkg) ? pkg + "." + classSimpleName : classSimpleName;
            VolatileReference<Class<?>> ref = CLASS_CACHE.get(className);
            if (ref == null) {
                ref = new VolatileReference<Class<?>>();
                VolatileReference<Class<?>> old = CLASS_CACHE.putIfAbsent(className, ref);
                if (old != null) {
                    ref = old;
                }
            }
            Class<?> cls = ref.get();
            logJavaCode(className, code);
            if (cls == null) {
                synchronized (ref) {
                    cls = ref.get();
                    if (cls == null) {
                        cls = doCompile(className, code);
                        ref.set(cls);
                    }
                }
            }
            return cls;
        } catch (Throwable t) {
            if (logger != null && logger.isErrorEnabled()) {
                logger.error("Failed to compile class, cause: " + t.getMessage() + ", class: " + className + ", code: \n================================\n" + code + "\n================================\n", t);
            }
            if (t instanceof ParseException) {
                throw (ParseException) t;
            }
            throw new ParseException("Failed to compile class, cause: " + t.getMessage() + ", class: " + className + ", stack: " + ClassUtils.toString(t), 0);
        }
    }

    protected abstract Class<?> doCompile(String name, String source) throws Exception;

}