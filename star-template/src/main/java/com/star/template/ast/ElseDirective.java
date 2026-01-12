package com.star.template.ast;

public class ElseDirective extends BlockDirective {

    private final Expression expression;

    public ElseDirective(Expression expression, int offset) {
        super(offset);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return expression == null ? "#else" : "#else(" + expression + ")";
    }

}