import com.star.template.Engine;
import com.star.template.Template;
import com.star.template.model.Book;
import com.star.template.spi.engines.DefaultEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineTest {
    public static void main(String[] args) throws Exception {
        Engine engine = DefaultEngine.getEngine();
        Template template = engine.getTemplate("templates/books.hxx", null, null);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("books", findBooks());
        StringWriter stringWriter = new StringWriter();
        template.render(parameters, stringWriter);
        System.out.println(stringWriter.toString());
    }

    public static List<Book> findBooks() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Practical API Design", "Jaroslav Tulach", "Apress", format.parse("2008-07-29"), 75, 85));
        books.add(new Book("框架设计原则", "hxx", "Apress", format.parse("2021-07-29"), 75, 85));
        return books;
    }
}