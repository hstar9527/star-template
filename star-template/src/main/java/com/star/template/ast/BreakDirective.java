package com.star.template.ast;

import com.star.template.Node;

import java.text.ParseException;

public class BreakDirective extends LineDirective {

    private final Expression expression;

    public BreakDirective(Expression expression, int offset) {
        super(offset);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setParent(Node parent) throws ParseException {
        super.setParent(parent);
    }

    @Override
    public String toString() {
        return expression == null ? "#break" : "#break(" + expression + ")";
    }

}