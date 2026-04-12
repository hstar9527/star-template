package com.star.template;

import java.io.IOException;
import java.text.ParseException;

public interface Template {

    Engine getEngine();

    String getName();

    void render(Object map, Object out) throws IOException, ParseException;
}