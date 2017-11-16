package spring.memcache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author hardiku
 */
public class MemcachedUtil
{

    private static MemcachedClient spyMemClient;
    private static MemCachedClient mcc;

    private static final String[] servers =
    {
        "127.0.0.1:11214"
    };

    static
    {

        Integer[] weights =
        {
            1
        };

        SockIOPool pool = SockIOPool.getInstance("default");

        pool.setServers(servers);
        pool.setWeights(weights);
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);
        pool.initialize();

        mcc = new MemCachedClient("default");
    }

    public static MemcachedClient getMemcachedClient() throws IOException
    {
        if(spyMemClient == null)
        {
            spyMemClient = createMemcachedClient();
        }
        return spyMemClient;
    }

    public static MemcachedClient getSpyMemcachedClient() throws IOException
    {
        if(spyMemClient == null)
        {
            spyMemClient = createMemcachedClient();
        }
        return spyMemClient;
    }

    private static MemcachedClient createMemcachedClient() throws IOException
    {
        return new MemcachedClient(connectionFactory(), getAddresses());
    }

    private static ConnectionFactory connectionFactory()
    {
        ConnectionFactoryBuilder builder = new ConnectionFactoryBuilder();
        builder.setOpTimeout(1000);
        return builder.build();
    }

    private static List<InetSocketAddress> getAddresses()
    {
        return AddrUtil.getAddresses("127.0.0.1:11214");
    }
}
