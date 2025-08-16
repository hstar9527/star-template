package com.star.spi.engines;

import com.star.Engine;
import com.star.Node;
import com.star.Resource;
import com.star.Template;
import com.star.spi.Loader;
import com.star.spi.Parser;
import com.star.spi.loaders.ClasspathLoader;
import com.star.spi.parsers.TemplateParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Properties;

/**
 * @author star
 * @version 1.0.0
 * @description
 */
public class DefaultEngine extends Engine {

    private Loader loader = new ClasspathLoader();

    private Parser parser = new TemplateParser();

    public static Engine getEngine() {
        return getEngine(null, new Properties());
    }

    public static Engine getEngine(String configPath, Properties configProperties) {
        return new DefaultEngine();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Engine engine = getEngine();
        Template template = engine.getTemplate("templates/hxxtest.hxx", null, null);
    }

    public Template getTemplate(String name, Locale locale, String encoding) throws IOException, ParseException {
        Resource resource = loader.load(name, locale, encoding);
        Node root = parser.parse(resource.getSource(), 0);
        return null;
    }
}