package com.star;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public abstract class Engine {

    public abstract Template getTemplate(String name, Locale locale, String encoding) throws IOException, ParseException;
}