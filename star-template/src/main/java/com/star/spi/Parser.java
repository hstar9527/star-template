package com.star.spi;

import com.star.Node;

import java.text.ParseException;

/**
 * @author star
 * @version 1.0.0
 * @description 模板解析器
 */
public interface Parser {

    /**
     * parse the template
     *
     * @param source
     * @param offset
     * @return
     * @throws ParseException
     */
    Node parse(String source, int offset) throws ParseException;
}