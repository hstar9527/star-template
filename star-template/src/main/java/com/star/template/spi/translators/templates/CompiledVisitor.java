package com.star.template.spi.translators.templates;

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


}