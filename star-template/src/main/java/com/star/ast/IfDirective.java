package com.star.ast;

import java.text.ParseException;

public class IfDirective extends BlockDirective {

    private final Expression expression;

    public IfDirective(Expression expression, int offset) throws ParseException {
        super(offset);
        if (expression == null) {
            throw new ParseException("The if expression is required.", offset);
        }
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "#if(" + expression + ")";
    }

}