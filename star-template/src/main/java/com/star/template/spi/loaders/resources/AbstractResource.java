package com.star.template.spi.loaders.resources;

import com.star.template.Resource;
import com.star.template.util.IOUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

/**
 * @author star
 * @version 1.0.0
 * @description
 */
public abstract class AbstractResource implements Resource, Serializable {

    private static final long serialVersionUID = 6834431114838915042L;

    private final String name;

    private final String encoding;

    private final Locale locale;

    private final long lastModified;

    public AbstractResource(String name, Locale locale, String encoding) {
        this(name, locale, encoding, -1);
    }

    public AbstractResource(String name, Locale locale, String encoding, long lastModified) {
        this.name = name;
        this.encoding = encoding;
        this.locale = locale;
        this.lastModified = lastModified;
    }


    public String getName() {
        return name;
    }

    public String getEncoding() {
        return encoding;
    }

    public Locale getLocale() {
        return locale;
    }

//    public long getLastModified() {
//        return lastModified;
//    }

    public long getLength() {
        return -1;
    }

    public String getSource() throws IOException {
        try {
            return IOUtils.readToString(openReader());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return getName();
    }

}