package jdbc.bonecp;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoneCPDataSourceExample
{

    public static void main(String[] args) throws PropertyVetoException, SQLException, IOException, ClassNotFoundException
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            // fetch a connection
            connection = DataSource.getInstance().getConnection();

            if(connection != null)
            {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select * from employee");
                while(resultSet.next())
                {
                    System.out.println("employeeid: " + resultSet.getString("employeeid"));
                    System.out.println("employeename: " + resultSet.getString("employeename"));
                }
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
