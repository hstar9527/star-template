package com.star.template.ast;


import com.star.template.Node;
import com.star.template.util.StringUtils;

import java.text.ParseException;

public class MacroDirective extends BlockDirective {

    private final String name;

    public MacroDirective(String name, int offset) throws ParseException {
        super(offset);
        if (!StringUtils.isNamed(name)) {
            throw new ParseException("Illegal macro name " + name + ", Can not contains any symbol.", offset);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(Node parent) throws ParseException {
        if (parent.getClass() != MacroDirective.class && parent.getClass() != RootDirective.class)
            throw new ParseException("Can not define macro inside the #" + parent.getClass().getSimpleName().toLowerCase() + " directive.", getOffset());
        super.setParent(parent);
    }

    @Override
    public String toString() {
        return "#macro(" + name + ")";
    }

}