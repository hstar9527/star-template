package com.star.template.spi.engines;

import com.star.template.Context;
import com.star.template.Engine;
import com.star.template.Node;
import com.star.template.Resource;
import com.star.template.Template;
import com.star.template.spi.Loader;
import com.star.template.spi.Parser;
import com.star.template.spi.Translator;
import com.star.template.spi.loaders.ClasspathLoader;
import com.star.template.spi.parsers.TemplateParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author star
 * @version 1.0.0
 * @description
 */
public class DefaultEngine extends Engine {

    private Loader loader = new ClasspathLoader();

    private Parser parser = new TemplateParser();

    private Translator translator;

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
        return translator.translate(resource, root);
    }

    public String getName() {
        return null;
    }

    public Map<String, Object> createContext(Context parent, Map<String, Object> current) {
        return null;
    }
}