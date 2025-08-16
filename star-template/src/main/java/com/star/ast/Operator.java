package com.star.ast;

import com.star.Node;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class Operator extends Expression {

    private final String name;

    private final int priority;

    private List<Node> children;

    public Operator(String name, int priority, int offset) {
        super(offset);
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Node> getChildren() {
        return children == null ? Collections.EMPTY_LIST : children;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setChildren(List<Expression> children) throws ParseException {
        if (this.children != null)
            throw new ParseException("Can not modify operator parameters.", getOffset());
        for (Expression node : children) {
            node.setParent(this);
        }
        this.children = (List) Collections.unmodifiableList(children);
    }

    @Override
    public String toString() {
        return getName();
    }

}