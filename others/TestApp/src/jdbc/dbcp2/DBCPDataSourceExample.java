package jdbc.dbcp2;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hardiku
 */
public class DBCPDataSourceExample
{

    public static void main(String[] args) throws PropertyVetoException, SQLException, IOException
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from employee");
            while(resultSet.next())
            {
                System.out.println("employeeid: " + resultSet.getString("employeeid"));
                System.out.println("employeename: " + resultSet.getString("employeename"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(resultSet != null)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(statement != null)
            {
                try
                {
                    statement.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

}
