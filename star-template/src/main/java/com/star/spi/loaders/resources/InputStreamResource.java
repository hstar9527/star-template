package com.star.spi.loaders.resources;


import com.star.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;

/**
 * @author star
 * @version 1.0.0
 * @description
 */
public abstract class InputStreamResource extends AbstractResource {

    protected static final String FILE_PROTOCOL = "file";
    protected static final String FILE_PROTOCOL_PREFIX = "file:";
    protected static final String JAR_PROTOCOL = "jar";
    protected static final String JAR_PROTOCOL_PREFIX = "jar:";
    protected static final String JAR_FILE_SEPARATOR = "!/";
    private static final long serialVersionUID = -5150738383353330217L;
    private final String path;

    public InputStreamResource(String name, Locale locale, String encoding, String path) {
        super(name, locale, encoding);
        this.path = path;
    }

    public Reader openReader() throws IOException {
        InputStream in = openStream();
        if (in == null) {
            throw new FileNotFoundException("Not found template " + getName() + " in " + getClass().getSimpleName() + ": " + getPath());
        }
        String encoding = getEncoding();
        return StringUtils.isEmpty(encoding)
                ? new InputStreamReader(in) : new InputStreamReader(in, encoding);
    }

//    public long getLastModified() {
//        File file = getFile();
//        if (file != null && file.exists()) {
//            return file.lastModified();
//        }
//        URL url = getUrl();
//        if (url != null) {
//            if (JAR_PROTOCOL.equals(url.getProtocol())) {
//                String path = url.getFile();
//                if (path.startsWith(JAR_PROTOCOL_PREFIX)) {
//                    path = path.substring(JAR_PROTOCOL_PREFIX.length());
//                }
//                if (path.startsWith(FILE_PROTOCOL_PREFIX)) {
//                    path = path.substring(FILE_PROTOCOL_PREFIX.length());
//                }
//                int i = path.indexOf(JAR_FILE_SEPARATOR);
//                if (i > 0) {
//                    path = path.substring(0, i);
//                }
//                file = new File(path);
//                if (file.exists()) {
//                    return file.lastModified();
//                }
//            }
//        }
//        return super.getLastModified();
//    }

//    public long getLength() {
//        File file = getFile();
//        if (file != null) {
//            return file.length();
//        }
//        try {
//            InputStream in = openStream();
//            if (in != null) {
//                try {
//                    return in.available();
//                } finally {
//                    in.close();
//                }
//            }
//        } catch (IOException e) {
//        }
//        return super.getLength();
//    }
//
//    public File getFile() {
//        URL url = getUrl();
//        if (url != null) {
//            if (FILE_PROTOCOL.equals(url.getProtocol())) {
//                String path = url.getFile();
//                if (path.startsWith(FILE_PROTOCOL_PREFIX)) {
//                    path = path.substring(FILE_PROTOCOL_PREFIX.length());
//                }
//                File file = new File(path);
//                if (file.exists()) {
//                    return file;
//                }
//            }
//        }
//        return null;
//    }
//
//    protected URL getUrl() {
//        return null;
//    }

    protected String getPath() {
        return path;
    }

}