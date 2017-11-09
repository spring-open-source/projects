package cache.memcached;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.spy.memcached.internal.OperationFuture;

/**
 * Older couchbase client implementation requires
 * <repositories>
 * <repository>
 * <id>couchbase</id>
 * <name>Couchbase Maven Repository</name>
 * <layout>default</layout>
 * <url>http://files.couchbase.com/maven2/</url>
 * <snapshots>
 * <enabled>false</enabled>
 * </snapshots>
 * </repository>
 * </repositories>
 *
 * <dependencies>
 * <dependency>
 * <groupId>couchbase</groupId>
 * <artifactId>couchbase-client</artifactId>
 * <version>1.1.7</version>
 * <type>jar</type>
 * </dependency>
 * </dependencies>
 *
 * @author hardiku
 */
public class CouchbaseClientOld
{

    public static void main(String[] args) throws IOException
    {
        List<URI> baseList = Arrays.asList(
                URI.create("http://192.168.0.1:8091/pools"),
                URI.create("http://192.168.0.2:8091/pools"));

        com.couchbase.client.CouchbaseClient client = new com.couchbase.client.CouchbaseClient(baseList, "default", "");

        OperationFuture<Boolean> setOp = client.set("key", "{\"name\":\"Couchbase\"}");

        client.shutdown(3, TimeUnit.SECONDS);
    }
}
