package com.star.template.spi.translators;

import com.star.template.Node;
import com.star.template.Resource;
import com.star.template.Template;
import com.star.template.spi.Translator;
import com.star.template.spi.translators.templates.CompiledTemplate;
import com.star.template.spi.translators.templates.CompiledVisitor;
import com.star.template.util.ClassUtils;
import com.star.template.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;


public class CompiledTranslator implements Translator {

    private static final String TEMPLATE_CLASS_PREFIX = CompiledTemplate.class.getPackage().getName() + ".Template_";

    public Template translate(Resource resource, Node root) throws IOException, ParseException {
        try {
            Class<?> clazz = parseClass(resource, root, true, 0);
            Template streamTemplate = (Template) clazz.getConstructor()
                    .newInstance();
            return streamTemplate;
        } catch (IOException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException("Failed to translate template: " + resource.getName() + ", cause: " + ClassUtils.toString(e), 0);
        }
    }

    private String getTemplateClassName(Resource resource, boolean stream) {
        String name = resource.getName();
        StringBuilder buf = new StringBuilder(name.length() + 40);
        buf.append(name);
        buf.append("_stream");
        return TEMPLATE_CLASS_PREFIX + StringUtils.getVaildName(buf.toString());
    }

    private Class<?> parseClass(Resource resource, Node root, boolean stream, int offset) throws IOException, ParseException {
        String name = getTemplateClassName(resource, stream);
        try {
            return Class.forName(name, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
//            if (types == null) {
//                types = new HashMap<String, Class<?>>();
//            }
            CompiledVisitor visitor = new CompiledVisitor();
//            visitor.setResource(resource);
//            visitor.setNode(root);
//            visitor.setTypes(types);
//            visitor.setStream(stream);
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
//            root.accept(visitor);
//            return visitor.compile();
            return null;
        }
    }

}