package com.star.ast;

import com.star.util.StringUtils;

import java.lang.reflect.Type;
import java.text.ParseException;

public class ForDirective extends BlockDirective {

    private final Type type;

    private final String name;

    private final Expression expression;

    public ForDirective(Type type, String name, Expression expression, int offset) throws ParseException {
        super(offset);
        if (!StringUtils.isNamed(name)) {
            throw new ParseException("Illegal foreach name " + name + ", Can not contains any symbol.", offset);
        }
        if (expression == null) {
            throw new ParseException("The foreach expression is required.", offset);
        }
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        String typeName = type == null ? "" : (type instanceof Class ? ((Class<?>) type).getCanonicalName() : type.toString());
        return "#for(" + typeName + " " + name + " : " + expression + ")";
    }

}