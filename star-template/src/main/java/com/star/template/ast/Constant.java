package com.star.template.ast;

public final class Constant extends Expression {

    private final Object value;

    private final boolean boxed;

    public Constant(Object value, boolean boxed, int offset) {
        super(offset);
        this.value = value;
        this.boxed = boxed;
    }

    public String getToken() {
        return toString();
    }

    public Object getValue() {
        return value;
    }

    public boolean isBoxed() {
        return boxed;
    }

    @Override
    public String toString() {
        return value == null && boxed ? "" : String.valueOf(value);
    }

}