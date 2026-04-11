package com.star.template.spi.translators.templates;

import com.star.template.Node;
import com.star.template.Resource;
import com.star.template.ast.AstVisitor;
import com.star.template.ast.BinaryOperator;
import com.star.template.ast.Constant;
import com.star.template.ast.ElseDirective;
import com.star.template.ast.ForDirective;
import com.star.template.ast.IfDirective;
import com.star.template.ast.Statement;
import com.star.template.ast.Text;
import com.star.template.ast.UnaryOperator;
import com.star.template.ast.ValueDirective;
import com.star.template.ast.Variable;
import com.star.template.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;

public class CompiledVisitor extends AstVisitor {
    public Class<?> compile() {
        return null;
    }

    @Override
    public boolean visit(Statement node) throws IOException, ParseException {
        System.out.println("visit(Statement node)" + node);
        boolean result = super.visit(node);
        return result;
    }

    @Override
    public void visit(Text node) throws IOException, ParseException {
        System.out.println("visit(Text node)" + node);
    }

    @Override
    public void visit(ValueDirective node) throws IOException, ParseException {
        System.out.println("visit(ValueDirective node)" + node);
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
        System.out.println("visit(ForDirective node)" + node);
        return true;
    }

    @Override
    public void end(ForDirective node) throws IOException, ParseException {
        System.out.println("end(ForDirective node)" + node);
    }

    public void visit(Constant node) throws IOException, ParseException {
        System.out.println("visit(Constant node)" + node);
    }

    public void visit(Variable node) throws IOException, ParseException {
        System.out.println("visit(Variable node)" + node);
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

    public String getCode() {
        String name = getTemplateClassName(resource, node, stream);
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
        String sorceCode = "package " + packageName + ";\n"
                + "\n"
                + imports.toString()
                + "\n"
                + className
                + "\n";
        return sorceCode;
    }

    private String getTemplateClassName(Resource resource, Node node, boolean stream) {
        String name = resource.getName();
        StringBuilder buf = new StringBuilder(name.length() + 40);
        buf.append(name);
        buf.append(stream ? "_stream" : "_writer");
        return TEMPLATE_CLASS_PREFIX + StringUtils.getVaildName(buf.toString());
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