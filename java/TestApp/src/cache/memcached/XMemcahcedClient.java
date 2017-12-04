package cache.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;

/**
 * https://github.com/killme2008/xmemcached/wiki/Getting%20started
 *
 * @author hardiku
 */
public class XMemcahcedClient
{

    public static void main(String[] args)
    {
        try
        {
            MemcachedClient client = new XMemcachedClient("host", 11211);

            String someObject = "value";
            //store a value for one hour(synchronously).
            client.set("key", 3600, someObject);
            //Retrieve a value.(synchronously).
            someObject = client.get("key");
            //Retrieve a value.(synchronously),operation timeout two seconds.
            someObject = client.get("key", 2000);

            //Touch cache item ,update it's expire time to 10 seconds.
            boolean success = client.touch("key", 10);

            //delete value
            client.delete("key");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
