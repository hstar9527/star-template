package com.star.spi;

import java.text.ParseException;


public interface Compiler {

    Class<?> compile(String code) throws ParseException;

}