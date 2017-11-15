package com.mycompany.javamustache;

import com.github.mustachejava.Mustache;
import gui.ava.html.image.generator.HtmlImageGenerator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hardiku
 */
public class Main
{
    
    public static void main(String[] args) throws IOException
    {
        Todo todo = new Todo("Todo 1", "Todo description");
        Mustache m = MustacheUtil.getMustacheFactory().compile("todo.html.tpl");
        Map<String, Object> context = new HashMap<>();
        //context.put("todo", todo);
        context.put("title", todo.getTitle());
        context.put("description", todo.getText());
        context.put("createdOn", todo.getCreatedOn());
        
        StringWriter writer = new StringWriter();
        m.execute(writer, context).flush();
        String html = writer.getBuffer().toString();
        
        System.out.println(m.execute(writer, m));

        convrtHtmlToImage("");
        //String expected = "<h2>Todo 1</h2>";
        //assertThat(executeTemplate(m, todo)).contains(expected);
    }
    
    public static void convrtHtmlToImage(String html)
    {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(
                "Hello World! Please goto <a title=\"Goto Google\""
                + " href=\"http://www.google.com\">Google.");
        imageGenerator.saveAsImage("hello-world.png");
        imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
    }
    
}
