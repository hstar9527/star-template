package com.star.template.spi;


import com.star.template.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author star
 * @version 1.0.0
 * @description 资源加载器
 */
public interface Loader {

    List<String> list(String suffix) throws IOException;

    boolean exists(String name, Locale locale);

    Resource load(String name, Locale locale, String encoding) throws IOException;

}