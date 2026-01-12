package com.star.template.ast;

import java.text.ParseException;

public class EndDirective extends Directive {

    private BlockDirective start;

    public EndDirective(int offset) {
        super(offset);
    }

    public BlockDirective getStart() {
        return start;
    }

    public void setStart(BlockDirective start) throws ParseException {
        if (this.start != null)
            throw new ParseException("Can not modify start.", getOffset());
        this.start = start;
    }

    @Override
    public String toString() {
        return "#end";
    }

}