package com.star.template.spi.translators.templates;

import com.star.template.Context;

import java.io.OutputStream;
import java.io.Writer;

public abstract class WriterTemplate extends CompiledTemplate {

    @Override
    protected void doRenderStream(Context context, OutputStream stream) throws Exception {
        throw new UnsupportedOperationException("Unsupported out type " + Writer.class.getName()
                + " in compiled " + OutputStream.class.getName() + " template. Please config output.stream=true");
    }
}