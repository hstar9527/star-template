package com.star.template;

import java.io.IOException;
import java.text.ParseException;

public interface Node {

    void accept(Visitor visitor) throws IOException, ParseException;


    int getOffset();
}