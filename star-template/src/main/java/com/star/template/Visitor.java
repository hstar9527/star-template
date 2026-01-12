package com.star.template;

import java.io.IOException;
import java.text.ParseException;

public interface Visitor {

    boolean visit(Node node) throws IOException, ParseException;

}