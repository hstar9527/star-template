package com.star.ast;

public final class Variable extends Expression {

    private final String name;

    public Variable(String name, int offset) {
        super(offset);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}