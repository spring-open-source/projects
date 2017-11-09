package cache.memcached;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

/**
 * <dependencies>
 * <dependency>
 * <groupId>com.couchbase.client</groupId>
 * <artifactId>java-client</artifactId>
 * <version>2.5.1</version>
 * </dependency>
 * </dependencies>
 *
 * @author hardiku
 */
public class CouchbaseJavaClient
{

    public static void main(String... args) throws Exception
    {

        // Initialize the Connection
        Cluster cluster = CouchbaseCluster.create("localhost");
        cluster.authenticate("username", "password");
        Bucket bucket = cluster.openBucket("bucketname");

        // Create a JSON Document
        JsonObject arthur = JsonObject.create()
                .put("name", "Arthur")
                .put("email", "kingarthur@couchbase.com")
                .put("interests", JsonArray.from("Holy Grail", "African Swallows"));

        // Store the Document
        bucket.upsert(JsonDocument.create("u:king_arthur", arthur));

        // Load the Document and print it
        // Prints Content and Metadata of the stored Document
        System.out.println(bucket.get("u:king_arthur"));

        // Create a N1QL Primary Index (but ignore if it exists)
        bucket.bucketManager().createN1qlPrimaryIndex(true, false);

        // Perform a N1QL Query
        N1qlQueryResult result = bucket.query(
                N1qlQuery.parameterized("SELECT name FROM bucketname WHERE $1 IN interests",
                        JsonArray.from("African Swallows"))
        );

        // Print each found Row
        for(N1qlQueryRow row : result)
        {
            // Prints {"name":"Arthur"}
            System.out.println(row);
        }
    }
}
