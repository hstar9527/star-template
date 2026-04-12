package com.star.template.spi.translators.templates;

import com.star.template.Context;
import com.star.template.Engine;

import java.io.OutputStream;
import java.io.Writer;

public abstract class CompiledTemplate extends AbstractTemplate {
    public Engine getEngine() {
        return null;
    }

    public String getName() {
        return null;
    }

    @Override
    protected void doRender(Context context) throws Exception {
        if (context.getOut() instanceof OutputStream) {
            doRenderStream(context, (OutputStream) context.getOut());
        } else {
            doRenderWriter(context, (Writer) context.getOut());
        }
    }

    protected abstract void doRenderWriter(Context context, Writer out) throws Exception;

    protected abstract void doRenderStream(Context context, OutputStream out) throws Exception;
}