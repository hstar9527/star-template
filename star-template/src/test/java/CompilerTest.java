import com.star.template.spi.compilers.JdkCompiler;

import java.text.ParseException;

public class CompilerTest {

    static final String source = "package com.star.template.spi.translators.templates;\n" +
            "\n" +
            "import com.star.template.model.*;\n" +
            "import com.star.template.*;\n" +
            "import com.star.template.util.*;\n" +
            "import java.util.*;\n" +
            "\n" +
            "public final class Template_templates_books_hxx_writer extends com.star.template.spi.translators.templates.WriterTemplate {\n" +
            "\n" +
            "private static final char[] $TXT1 = com.star.template.util.CharCache.getAndRemove(\"1\");\n" +
            "private static final char[] $TXT2 = com.star.template.util.CharCache.getAndRemove(\"2\");\n" +
            "private static final char[] $TXT3 = com.star.template.util.CharCache.getAndRemove(\"3\");\n" +
            "private static final char[] $TXT4 = com.star.template.util.CharCache.getAndRemove(\"4\");\n" +
            "private static final char[] $TXT5 = com.star.template.util.CharCache.getAndRemove(\"5\");\n" +
            "private static final char[] $TXT6 = com.star.template.util.CharCache.getAndRemove(\"6\");\n" +
            "private static final char[] $TXT7 = com.star.template.util.CharCache.getAndRemove(\"7\");\n" +
            "private static final char[] $TXT8 = com.star.template.util.CharCache.getAndRemove(\"8\");\n" +
            "\n" +
            "protected void doRenderWriter(com.star.template.Context $context, java.io.Writer $output) throws java.lang.Exception {\n" +
            "\t$output.write($TXT1);\n" +
            "\t$output.write($TXT2);\n" +
            "\t$output.write($TXT3);\n" +
            "\t$output.write($TXT4);\n" +
            "\t$output.write($TXT5);\n" +
            "\t$output.write($TXT6);\n" +
            "\t$output.write($TXT7);\n" +
            "\t$output.write($TXT8);\n" +
            "}\n" +
            "\n" +
            "}\n";

    public static void main(String[] args) throws ParseException {
        JdkCompiler jdkCompiler = new JdkCompiler();
        Class<?> clazz = jdkCompiler.compile(source);
        System.out.println(clazz);
    }
}