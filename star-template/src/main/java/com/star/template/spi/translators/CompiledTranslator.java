package com.star.template.spi.translators;

import com.star.template.Node;
import com.star.template.Resource;
import com.star.template.Template;
import com.star.template.spi.Translator;
import com.star.template.spi.translators.templates.CompiledTemplate;
import com.star.template.spi.translators.templates.CompiledVisitor;
import com.star.template.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CompiledTranslator implements Translator {

    private static final String TEMPLATE_CLASS_PREFIX = CompiledTemplate.class.getPackage().getName() + ".Template_";

    private Class<?> parseClass(Resource resource, Node root, Map<String, Class<?>> types, boolean stream, int offset) throws IOException, ParseException {
        String name = getTemplateClassName(resource, stream);
        try {
            return Class.forName(name, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            if (types == null) {
                types = new HashMap<String, Class<?>>();
            }
            CompiledVisitor visitor = new CompiledVisitor();
            visitor.setResource(resource);
            visitor.setNode(root);
//            visitor.setTypes(types);
            visitor.setStream(stream);
//            visitor.setOffset(offset);
//            visitor.setDefaultFilterVariable(defaultFilterVariable);
//            visitor.setDefaultFormatterVariable(defaultFormatterVariable);
//            visitor.setDefaultVariableType(defaultVariableType);
//            visitor.setEngineName(engineName);
//            visitor.setFilterVariable(filterVariable);
//            visitor.setFormatterSwitcher(formatterSwitcher);
//            visitor.setFormatterVariable(formatterVariable);
//            visitor.setForVariable(forVariable);
//            visitor.setImportMacroTemplates(importMacroTemplates);
//            visitor.setImportPackages(importPackages);
//            visitor.setImportPackageSet(importPackageSet);
//            visitor.setImportSizers(importSizers);
//            visitor.setImportGetters(importGetters);
//            visitor.setImportTypes(importTypes);
//            visitor.setImportMethods(functions);
//            visitor.setOutputEncoding(outputEncoding);
//            visitor.setSourceInClass(sourceInClass);
//            visitor.setTextFilter(textFilter);
//            visitor.setTextFilterSwitcher(textFilterSwitcher);
//            visitor.setTextInClass(textInClass);
//            visitor.setValueFilterSwitcher(valueFilterSwitcher);
//            visitor.setCompiler(compiler);
//            visitor.init();
            root.accept(visitor);
            System.out.println("code:" + visitor.getCode());
            return visitor.compile();
        }
    }

    private String getTemplateClassName(Resource resource, boolean stream) {
        String name = resource.getName();
        return TEMPLATE_CLASS_PREFIX + name + "_stream";
    }

    public Template translate(Resource resource, Node root) throws ParseException, IOException {
        Class<?> clazz = parseClass(resource, root, null, false, 0);
        return null;
    }
}