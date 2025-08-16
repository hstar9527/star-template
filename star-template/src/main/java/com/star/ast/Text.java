package com.star.ast;

import com.star.util.StringUtils;

import java.text.ParseException;

public class Text extends Statement {

    private final String content;

    private final boolean literal;

    public Text(String content, boolean literal, int offset) throws ParseException {
        super(offset);
        if (StringUtils.isEmpty(content)) {
            throw new ParseException("The text content == null.", offset);
        }
        this.content = content;
        this.literal = literal;
    }

    public String getContent() {
        return content;
    }

    public boolean isLiteral() {
        return literal;
    }

    @Override
    public String toString() {
        return literal ? "#[" + content + "]#" : content;
    }

}