package jdbc.h2;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.DeleteDbFiles;

// H2 Database ConnectionPool Example
public class H2DatabaseConnectionPoolExample
{

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) throws Exception
    {
        try
        {
            // delete the database named 'test' in the user home directory for initialization
            DeleteDbFiles.execute("~", "test", true);
            batchInsertWithStatement();
            batchInsertWithPreparedStatement();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void batchInsertWithPreparedStatement() throws SQLException
    {
        JdbcConnectionPool jdbcConnectionPool = getConnectionPool();
        Connection connection = jdbcConnectionPool.getConnection();
        PreparedStatement preparedStatement = null;

        String Query = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
        try
        {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(Query);

            preparedStatement.setInt(1, 4);
            preparedStatement.setString(2, "Rockey");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Jacky");
            preparedStatement.addBatch();

            int[] countWithoutException = preparedStatement.executeBatch();
            System.out.println("OK: countWithoutException = " + countWithoutException.length);
            connection.commit();
        }
        catch(BatchUpdateException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            preparedStatement.close();
            connection.close();
            jdbcConnectionPool.dispose();
        }
    }

    private static void batchInsertWithStatement() throws SQLException
    {
        JdbcConnectionPool jdbcConnectionPool = getConnectionPool();
        Connection connection = jdbcConnectionPool.getConnection();
        Statement stmt = null;
        try
        {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            stmt.addBatch("INSERT INTO PERSON(id, name) VALUES(1, 'A')");
            stmt.addBatch("INSERT INTO PERSON(id, name) VALUES(2, 'B')");
            stmt.addBatch("INSERT INTO PERSON(id, name) VALUES(3, 'C')");

            int[] countWithoutException = stmt.executeBatch();
            System.out.println("OK: countWithoutException = " + countWithoutException.length);

            connection.commit();
        }
        catch(BatchUpdateException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            stmt.close();
            connection.close();
            jdbcConnectionPool.dispose();
        }
    }

    // Create H2 JdbcConnectionPool
    private static JdbcConnectionPool getConnectionPool()
    {
        JdbcConnectionPool cp = null;
        try
        {
            Class.forName(DB_DRIVER);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        cp = JdbcConnectionPool.create(DB_CONNECTION, DB_USER, DB_PASSWORD);
        return cp;
    }
}
