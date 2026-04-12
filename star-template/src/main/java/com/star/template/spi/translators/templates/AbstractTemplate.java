package com.star.template.spi.translators.templates;

import com.star.template.Context;
import com.star.template.Template;
import com.star.template.util.ClassUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.ParseException;
import java.util.Map;

public abstract class AbstractTemplate implements Template {

    public void render(Object parameters, Object out) throws IOException, ParseException {
        Map<String, Object> map = (Map<String, Object>) parameters;
        Context context = Context.pushContext(map);
        if (out instanceof OutputStream) {
            context.setOut((OutputStream) out);
        } else if (out instanceof Writer) {
            context.setOut((Writer) out);
        } else {
            throw new IllegalArgumentException("No such Converter to convert the " + out.getClass().getName() + " to OutputStream or Writer.");
        }
        try {
            doRender(context);
        } catch (RuntimeException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(ClassUtils.toString(e), e);
        }
    }

    protected abstract void doRender(Context context) throws Exception;
}