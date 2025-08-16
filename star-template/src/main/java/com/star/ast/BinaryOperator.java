package com.star.ast;

import com.star.Node;
import com.star.Visitor;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class BinaryOperator extends Operator {

    private Expression leftParameter;

    private Expression rightParameter;

    public BinaryOperator(String name, int priority, int offset) {
        super(name, priority, offset);
    }

    public void accept(Visitor visitor) throws IOException, ParseException {
        leftParameter.accept(visitor);
        rightParameter.accept(visitor);
        visitor.visit(this);
    }

    public Expression getLeftParameter() {
        return leftParameter;
    }

    public void setLeftParameter(Expression leftParameter) throws ParseException {
        if (this.leftParameter != null)
            throw new ParseException("Can not modify left parameter.", getOffset());
        this.leftParameter = leftParameter;
    }

    public Expression getRightParameter() {
        return rightParameter;
    }

    public void setRightParameter(Expression rightParameter) throws ParseException {
        if (this.rightParameter != null)
            throw new ParseException("Can not modify right parameter.", getOffset());
        this.rightParameter = rightParameter;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Node> getChildren() {
        return (List) Arrays.asList(leftParameter, rightParameter);
    }

    @Override
    public String toString() {
        if (getParent() instanceof Operator
                && ((Operator) getParent()).getPriority() < getPriority()) {
            return "(" + leftParameter + " " + getName() + " " + rightParameter + ")";
        }
        return leftParameter + " " + getName() + " " + rightParameter;
    }

}