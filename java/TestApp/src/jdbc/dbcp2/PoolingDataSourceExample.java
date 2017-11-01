package jdbc.dbcp2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

//
// Here's a simple example of how to use the PoolingDataSource.
//
//
// Note that this example is very similar to the PoolingDriver
// example.  In fact, you could use the same pool in both a
// PoolingDriver and a PoolingDataSource
//
//
// To compile this example, you'll want:
//  * commons-pool2-2.3.jar
//  * commons-dbcp2-2.1.jar
// in your classpath.
//
// To run this example, you'll want:
//  * commons-pool2-2.3.jar
//  * commons-dbcp2-2.1.jar
//  * commons-logging-1.2.jar
//  * the classes for your (underlying) JDBC driver
// in your classpath.
//
// Invoke the class using two arguments:
//  * the connect string for your underlying JDBC driver
//  * the query you'd like to execute
// You'll also want to ensure your underlying JDBC driver
// is registered.  You can use the "jdbc.drivers"
// property to do this.
//
// For example:
//  java -Djdbc.drivers=org.h2.Driver \
//       -classpath commons-pool2-2.3.jar:commons-dbcp2-2.1.jar:commons-logging-1.2.jar:h2-1.3.152.jar:. \
//       PoolingDataSourceExample \
//       "jdbc:h2:~/test" \
//       "SELECT 1"
//
public class PoolingDataSourceExample
{

    public static void main(String[] args)
    {
        //
        // First we load the underlying JDBC driver.
        // You need this if you don't use the jdbc.drivers
        // system property.
        //
        System.out.println("Loading underlying JDBC driver.");
        try
        {
            Class.forName("org.h2.Driver");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        System.out.println("Done.");

        //
        // Then, we set up the PoolingDataSource.
        // Normally this would be handled auto-magically by
        // an external configuration, but in this example we'll
        // do it manually.
        //
        System.out.println("Setting up data source.");
        DataSource dataSource = setupDataSource(args[0]);
        System.out.println("Done.");

        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try
        {
            System.out.println("Creating connection.");
            conn = dataSource.getConnection();
            System.out.println("Creating statement.");
            stmt = conn.createStatement();
            System.out.println("Executing statement.");
            rset = stmt.executeQuery(args[1]);
            System.out.println("Results:");
            int numcols = rset.getMetaData().getColumnCount();
            while(rset.next())
            {
                for(int i = 1; i <= numcols; i++)
                {
                    System.out.print("\t" + rset.getString(i));
                }
                System.out.println("");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rset != null)
                {
                    rset.close();
                }
            }
            catch(Exception e)
            {
            }
            try
            {
                if(stmt != null)
                {
                    stmt.close();
                }
            }
            catch(Exception e)
            {
            }
            try
            {
                if(conn != null)
                {
                    conn.close();
                }
            }
            catch(Exception e)
            {
            }
        }
    }

    public static DataSource setupDataSource(String connectURI)
    {
        //
        // First, we'll create a ConnectionFactory that the
        // pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory,
        // using the connect string passed in the command line
        // arguments.
        //
        ConnectionFactory connectionFactory
                = new DriverManagerConnectionFactory(connectURI, null);

        //
        // Next we'll create the PoolableConnectionFactory, which wraps
        // the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        //
        PoolableConnectionFactory poolableConnectionFactory
                = new PoolableConnectionFactory(connectionFactory, null);

        //
        // Now we'll need a ObjectPool that serves as the
        // actual pool of connections.
        //
        // We'll use a GenericObjectPool instance, although
        // any ObjectPool implementation will suffice.
        //
        ObjectPool<PoolableConnection> connectionPool
                = new GenericObjectPool<>(poolableConnectionFactory);

        // Set the factory's pool property to the owning pool
        poolableConnectionFactory.setPool(connectionPool);

        //
        // Finally, we create the PoolingDriver itself,
        // passing in the object pool we created.
        //
        PoolingDataSource<PoolableConnection> dataSource
                = new PoolingDataSource<>(connectionPool);

        return dataSource;
    }
}
