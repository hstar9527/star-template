package com.star.template.spi.loaders;

import com.star.template.Resource;
import com.star.template.spi.Loader;
import com.star.template.spi.loaders.resources.ClasspathResource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ClasspathLoader implements Loader {

    private String cleanPath(String path) {
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public List<String> list(String suffix) throws IOException {
        return null;
    }

    public boolean exists(String name, Locale locale) {
        return false;
    }

    public Resource load(String name, Locale locale, String encoding) throws IOException {
        return new ClasspathResource(name, locale, encoding, name);
    }
}