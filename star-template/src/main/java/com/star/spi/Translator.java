package com.star.spi;


import com.star.Node;
import com.star.Resource;
import com.star.Template;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author star
 * @version 1.0.0
 * @description 模板翻译器
 */
public interface Translator {

    Template translate(Resource resource, Node root) throws ParseException, IOException;

}