package cache.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author hardiku
 */
public class SpyMemcachedClient
{

    private static MemcachedClient client = null;
    private static Logger logger = LogManager.getLogger(SpyMemcachedClient.class);

    public static Optional<MemcachedClient> getClient(InetSocketAddress addr)
    {
        if(Objects.isNull(addr))
        {
            logger.error("getClient: addr shouldn't be null");
            return Optional.empty();
        }

        try
        {
            client = new MemcachedClient(addr);
        }
        catch(IOException e)
        {
            logger.error(e);
            return Optional.empty();
        }

        return Optional.of(client);
    }

    public static Optional<MemcachedClient> getClient(List<InetSocketAddress> addrs)
    {
        if(Objects.isNull(addrs))
        {
            logger.error("getClient: addrs shouldn't be null");
            return Optional.empty();
        }

        try
        {
//            List servers = new ArrayList();
//            servers.add(new InetSocketAddress("192.168.0.9", 11211));
//            MemcachedClient client = new MemcachedClient(new BinaryConnectionFactory(), servers);

            client = new MemcachedClient(addrs);
        }
        catch(IOException e)
        {
            logger.error(e);
            return Optional.empty();
        }

        return Optional.of(client);
    }

    public static void shutdownClient(MemcachedClient client)
    {
        if(Objects.isNull(client))
        {
            logger.error("shutdownClient: client object is already closed");
            return;
        }

        client.shutdown();
    }

    public static void main(String args[]) throws InterruptedException, ExecutionException
    {
        /* Get MemcachedClient */
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 11211);

        Optional<MemcachedClient> client = SpyMemcachedClient.getClient(address);

        if(!client.isPresent())
        {
            logger.error("Unable to create client instance");
            return;
        }

        MemcachedClient memClient = client.get();

        /* Set some data */
        String data = "{\"org\":[{\"name\":\"Honeywell\",\"yrsOfExperience\":2.2},{\"name\":\"IBM\",\"yrsOfExperience\":1.8}],\"firstName\":\"Krishna\",\"lastName\":\"Hari\",\"salary\":80000.0}\r\n";

        Future<Boolean> future = memClient.set("12345", 900, data);

        System.out.println("set status:" + future.get());

        System.out.println("Value of the key 12345 is " + memClient.get("12345"));

        System.out.println("Memcached Statistics - " + memClient.getStats());

        SpyMemcachedClient.shutdownClient(memClient);

    }
}
