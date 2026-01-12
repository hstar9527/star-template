package com.star.template;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public interface Resource {

    String getName();

    String getSource() throws IOException;

    Reader openReader() throws IOException;

    InputStream openStream() throws IOException;
}