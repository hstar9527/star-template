//package com.star.template.spi.translators.templates;
//
//import com.star.template.model.*;
//import com.star.template.*;
//import com.star.template.util.*;
//import java.util.*;
//
//public final class Template_templates_books_hxx_writer extends com.star.template.spi.translators.templates.WriterTemplate {
//
//    private static final char[] $TXT1 = com.star.template.util.CharCache.getAndRemove("1");
//    private static final char[] $TXT2 = com.star.template.util.CharCache.getAndRemove("2");
//    private static final char[] $TXT4 = com.star.template.util.CharCache.getAndRemove("3");
//    private static final char[] $TXT5 = com.star.template.util.CharCache.getAndRemove("4");
//    private static final char[] $TXT6 = com.star.template.util.CharCache.getAndRemove("5");
//    private static final char[] $TXT7 = com.star.template.util.CharCache.getAndRemove("6");
//
//    protected void doRenderWriter(com.star.template.Context $context, java.io.Writer $output) throws java.lang.Exception {
//        java.util.List books = (java.util.List) $context.get("books");	$output.write($TXT1);
//        $output.write($TXT2);
//        Object _d_3 = books;
//        for (java.util.Iterator _i_book = com.star.template.util.CollectionUtils.toIterator(_d_3); _i_book.hasNext();) {
//            com.star.template.model.Book book = (com.star.template.model.Book)(_i_book.next());
//            $output.write($TXT4);
//            $output.write(book.getTitle());
//            $output.write($TXT5);
//            $output.write(book.getTitle());
//            $output.write($TXT6);
//
//        }
//        $output.write($TXT7);
//    }
//
//}