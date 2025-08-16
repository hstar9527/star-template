package com.star.ast;


import com.star.util.StringUtils;

import java.lang.reflect.Type;
import java.text.ParseException;

public class SetDirective extends LineDirective {

    private final Type type;

    private final String name;

    private final Expression expression;

    private final boolean export;

    private final boolean hide;

    public SetDirective(Type type, String name, Expression expression, boolean export, boolean hide, int offset) throws ParseException {
        super(offset);
        if (!StringUtils.isNamed(name)) {
            throw new ParseException("Illegal variable name " + name + ", Can not contains any symbol.", offset);
        }
        this.type = type;
        this.name = name;
        this.expression = expression;
        this.export = export;
        this.hide = hide;
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

    public boolean isExport() {
        return export;
    }

    public boolean isHide() {
        return hide;
    }

    @Override
    public String toString() {
        String typeName = type == null ? "" : (type instanceof Class ? ((Class<?>) type).getCanonicalName() : type.toString());
        return "#set(" + typeName + " " + name +
                (expression == null ? "" : (export ? " := " : (hide ? " .= " : " = ")) + expression) + ")";
    }

}