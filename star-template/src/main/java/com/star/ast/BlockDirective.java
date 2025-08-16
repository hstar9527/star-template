package com.star.ast;

import com.star.Node;
import com.star.Visitor;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public abstract class BlockDirective extends Directive {

    private List<Node> children;

    private EndDirective end;

    public BlockDirective(int offset) {
        super(offset);
    }

    @Override
    public void accept(Visitor visitor) throws IOException, ParseException {
        Expression expression = getExpression();
        if (expression != null)
            expression.accept(visitor);
        if (visitor.visit(this)) {
            if (children != null) {
                for (Node node : children) {
                    node.accept(visitor);
                }
            }
            if (end != null)
                end.accept(visitor);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Node> getChildren() {
        return children == null ? Collections.EMPTY_LIST : children;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setChildren(List<Statement> children) throws ParseException {
        if (this.children != null)
            throw new ParseException("Can not modify children statement.", getOffset());
        for (Statement node : children) {
            node.setParent(this);
        }
        this.children = (List) Collections.unmodifiableList(children);
    }

    public EndDirective getEnd() {
        return end;
    }

    public void setEnd(EndDirective end) throws ParseException {
        if (this.end != null)
            throw new ParseException("Can not modify end.", this.end.getOffset());
        this.end = end;
        end.setStart(this);
    }

}