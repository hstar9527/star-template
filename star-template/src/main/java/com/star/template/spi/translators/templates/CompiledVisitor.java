package com.star.template.spi.translators.templates;

import com.star.template.Context;
import com.star.template.Node;
import com.star.template.Resource;
import com.star.template.ast.AstVisitor;
import com.star.template.ast.BinaryOperator;
import com.star.template.ast.Constant;
import com.star.template.ast.ElseDirective;
import com.star.template.ast.ForDirective;
import com.star.template.ast.IfDirective;
import com.star.template.ast.SetDirective;
import com.star.template.ast.Statement;
import com.star.template.ast.Text;
import com.star.template.ast.UnaryOperator;
import com.star.template.ast.ValueDirective;
import com.star.template.ast.Variable;
import com.star.template.spi.Compiler;
import com.star.template.spi.compilers.JdkCompiler;
import com.star.template.util.CharCache;
import com.star.template.util.ClassUtils;
import com.star.template.util.CollectionUtils;
import com.star.template.util.LinkedStack;
import com.star.template.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CompiledVisitor extends AstVisitor {

    private StringBuilder builder = new StringBuilder();

    private Compiler compiler = new JdkCompiler();

    private final Map<String, Type> types = new HashMap<String, Type>();

    private final List<String> defVariables = new ArrayList<String>();

    private final List<Class<?>> defVariableTypes = new ArrayList<Class<?>>();

    private final AtomicInteger seq = new AtomicInteger();

    private LinkedStack<Type> typeStack = new LinkedStack<Type>();
    private LinkedStack<String> codeStack = new LinkedStack<String>();
    private Map<String, Class<?>> variableTypes = new HashMap<String, Class<?>>();

    public Class<?> compile() throws IOException, ParseException {
        String code = getCode();
        return compiler.compile(code);
    }

    @Override
    public boolean visit(Statement node) throws IOException, ParseException {
        System.out.println("visit(Statement node)" + node);
        boolean result = super.visit(node);
        return result;
    }

    @Override
    public void visit(Text node) throws IOException, ParseException {
        String txt = node.getContent();
        String part = getTextPart(txt, false);
        if (StringUtils.isNotEmpty(part)) {
            builder.append("	$output.write(" + part + ");\n");
        }
    }

    @Override
    public void visit(ValueDirective node) throws IOException, ParseException {
        builder.append("	$output.write(")
                .append("book.getTitle()");
        builder.append(");\n");
    }

    @Override
    public void visit(SetDirective node) throws IOException, ParseException {
        Type type = node.getType();
        Class<?> clazz = (Class<?>) (type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type);
        //存储set指令设置的变量类型
        types.put(node.getName(), type);
        defVariables.add(node.getName());
        defVariableTypes.add(clazz);
    }

    @Override
    public boolean visit(IfDirective node) throws IOException, ParseException {
        System.out.println(" visit(IfDirective node)" + node);
        return true;
    }

    @Override
    public void end(IfDirective node) throws IOException, ParseException {
        System.out.println(" end(IfDirective node)" + node);
    }

    @Override
    public boolean visit(ElseDirective node) throws IOException, ParseException {
        System.out.println("visit(ElseDirective node)" + node);
        return true;
    }

    @Override
    public void end(ElseDirective node) throws IOException, ParseException {
        System.out.println("end(ElseDirective node)" + node);
    }

    @Override
    public boolean visit(ForDirective node) throws IOException, ParseException {
        String var = node.getName();
        Type type = node.getType();
        Class<?> clazz = (Class<?>) (type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type);
        String code = popExpressionCode();
        int i = seq.incrementAndGet();
        String dataName = "_d_" + i;
        builder.append("	" + Object.class.getSimpleName() + " " + dataName + " = " + code + ";\n");
        String name = "_i_" + var;
        builder.append("	for (" + Iterator.class.getName() + " " + name + " = " + CollectionUtils.class.getName() + ".toIterator(" + dataName + "); " + name + ".hasNext();) {\n");
        String varCode = name + ".next()";
        builder.append("	" + clazz.getCanonicalName() + " " + var + " = (" + clazz.getCanonicalName() + ")(" + varCode + ");\n");
        return true;
    }

    @Override
    public void end(ForDirective node) throws IOException, ParseException {
        builder.append("\n	}\n");
    }

    public void visit(Constant node) throws IOException, ParseException {
        System.out.println("visit(Constant node)" + node);
    }

    public void visit(Variable node) throws IOException, ParseException {
        String name = node.getName();
        Type type = types.get(name);
        Class<?> clazz = (Class<?>) (type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type);
        String code = ClassUtils.filterJavaKeyword(name);
        typeStack.push(type);
        codeStack.push(code);
        variableTypes.put(name, clazz);
    }

    @Override
    public void visit(UnaryOperator node) throws IOException, ParseException {
        System.out.println("visit(UnaryOperator node)" + node);
    }

    public void visit(BinaryOperator node) throws IOException, ParseException {
        System.out.println("visit(BinaryOperator node)" + node);
    }

    private static final String TEMPLATE_CLASS_PREFIX = CompiledTemplate.class.getPackage().getName() + ".Template_";

    private Resource resource;

    private Node node;

    private boolean stream;

    /**
     * for test
     */
    private String[] importPackages = new String[]{"com.star.template.model", "com.star.template", "com.star.template.util", "java.util"};

    private StringBuilder textFields = new StringBuilder();

    public String getCode() {
        String name = getTemplateClassName(resource, node, stream);
        String code = builder.toString();
        int i = name.lastIndexOf('.');
        String packageName = i < 0 ? "" : name.substring(0, i);
        String className = i < 0 ? name : name.substring(i + 1);
        StringBuilder imports = new StringBuilder();
        String[] packages = importPackages;
        if (packages != null && packages.length > 0) {
            for (String pkg : packages) {
                imports.append("import ");
                imports.append(pkg);
                imports.append(".*;\n");
            }
        }
        String declare = "java.util.List books = (java.util.List) $context.get(\"books\");";
        String methodCode = declare + code;
        String sorceCode = "package " + packageName + ";\n"
                + "\n"
                + imports.toString()
                + "\n"
                + "public final class " + className + " extends " + (stream ? OutputStreamTemplate.class.getName() : WriterTemplate.class.getName()) + " {\n"
                + "\n"
                //模板类持有的成员变量
                + textFields
                + "\n"
                + "protected void doRender"
                + (stream ? "Stream" : "Writer")
                + "(" + Context.class.getName() + " $context, "
                + (stream ? OutputStream.class.getName() : Writer.class.getName())
                + " $output) throws " + Exception.class.getName() + " {\n"
                + methodCode
                + "}\n"
                + "\n"
                + "}\n";
        return sorceCode;
    }

    private String getTemplateClassName(Resource resource, Node node, boolean stream) {
        String name = resource.getName();
        StringBuilder buf = new StringBuilder();
        buf.append(name);
        buf.append(stream ? "_stream" : "_writer");
        return TEMPLATE_CLASS_PREFIX + StringUtils.getVaildName(buf.toString());
    }

    private String getTextPart(String txt, boolean string) {
        if (StringUtils.isNotEmpty(txt)) {
            String var = "$TXT" + seq.incrementAndGet();
            String txtId = CharCache.put(txt.toCharArray());
            textFields.append("private static final char[] " + var + " = " + CharCache.class.getName() + ".getAndRemove(\"" + txtId + "\");\n");
            return var;
        }
        return "";

    }

    private String popExpressionCode() {
        String code = codeStack.pop();
        if (!codeStack.isEmpty()) {
            throw new IllegalStateException("Illegal expression.");
        }
        return code;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }
}