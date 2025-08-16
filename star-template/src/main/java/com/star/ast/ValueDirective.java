package com.star.ast;

import java.text.ParseException;

public class ValueDirective extends LineDirective {

    private final Expression expression;

    private final boolean noFilter;

    public ValueDirective(Expression expression, boolean noFilter, int offset) throws ParseException {
        super(offset);
        if (expression == null) {
            throw new ParseException("The value expression is required.", offset);
        }
        this.expression = expression;
        this.noFilter = noFilter;
    }

    public Expression getExpression() {
        return expression;
    }

    public boolean isNoFilter() {
        return noFilter;
    }

    @Override
    public String toString() {
        return "$" + (noFilter ? "!" : "") + "{" + expression + "}";
    }

}