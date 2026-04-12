import com.star.template.Engine;
import com.star.template.Template;
import com.star.template.spi.engines.DefaultEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class EngineTest {
    public static void main(String[] args) throws IOException, ParseException {
        Engine engine = DefaultEngine.getEngine();
        Template template = engine.getTemplate("templates/books.hxx", null, null);
        Map<String, Object> parameters = new HashMap<String, Object>();
        StringWriter stringWriter = new StringWriter();
        template.render(parameters, stringWriter);
        System.out.println(stringWriter.toString());
    }
}