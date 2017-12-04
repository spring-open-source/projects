import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Java Program to demonstrate how to load resources e.g. properties file from
 * classpath. There are two ways to load resources in Java, one by using
 * getResourceAsStream() and getResource() method from java.lang.Class. Main
 * difference between these two methods are that one returns an InputStream
 * while other returns a URL object.
 *
 * @author hardiku
 */
public class Application
{

    private static final Class LOADER = Application.class;

    public static void main(String args[])
    {

        // loading resource using getResourceAsStream() method
        InputStream in = LOADER.getResourceAsStream("db.properties");

        Properties config = new Properties();
        try
        {
            config.load(in);
            System.out.println(config.getProperty("spring.datasource.username"));
            System.out.println(config.getProperty("spring.datasource.password"));

        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

}
