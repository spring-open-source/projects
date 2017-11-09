package cache.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 *
 * @author hardiku
 */
public class MemcachedClient
{

    protected static MemCachedClient mcc = new MemCachedClient();

    static
    {

        String[] servers =
        {
            "localhost:11211",
        };

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

    }
}
