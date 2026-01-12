package com.star.template.spi;


import com.star.template.Node;
import com.star.template.Resource;
import com.star.template.Template;

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