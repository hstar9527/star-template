package com.star.template.ast;


import com.star.template.Node;
import com.star.template.Visitor;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class UnaryOperator extends Operator {

    private Expression parameter;

    public UnaryOperator(String name, int priority, int offset) {
        super(name, priority, offset);
    }

    public void accept(Visitor visitor) throws IOException, ParseException {
        parameter.accept(visitor);
        visitor.visit(this);
    }

    public Expression getParameter() {
        return parameter;
    }

    public void setParameter(Expression parameter) throws ParseException {
        if (this.parameter != null)
            throw new ParseException("Can not modify parameter.", getOffset());
        this.parameter = parameter;
        parameter.setParent(this);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Node> getChildren() {
        return (List) Arrays.asList(parameter);
    }

    @Override
    public String toString() {
        return getName() + " " + parameter;
    }

}