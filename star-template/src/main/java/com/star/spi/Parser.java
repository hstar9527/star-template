package com.star.spi;

import com.star.Node;

import java.text.ParseException;

/**
 * @author star
 * @version 1.0.0
 * @description AST解析器
 */
public interface Parser {

    Node parse(String source, int offset) throws ParseException;
}