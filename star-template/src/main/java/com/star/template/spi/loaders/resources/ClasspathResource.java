package com.star.template.spi.loaders.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class ClasspathResource extends InputStreamResource {
    public ClasspathResource(String name, Locale locale, String encoding, String path) {
        super(name, locale, encoding, path);
    }

    public InputStream openStream() throws IOException {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(getPath());
    }
}