package com.star;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

public abstract class Engine {

    public abstract Template getTemplate(String name, Locale locale, String encoding) throws IOException, ParseException;

    public abstract String getName();

    public abstract Map<String,Object> createContext(Context parent, Map<String, Object> current);
}