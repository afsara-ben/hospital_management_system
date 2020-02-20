/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class loginModel {
       Connection connection;
       public loginModel()
       {
         connection=DBConnection.LoginConnector();
         if(connection==null)
         {
           System.exit(1);
         }
       }
    public boolean isLoginValid(String user, String pass) throws SQLException
    {
     PreparedStatement preparedStatement =null;
     ResultSet resultSet=null;
     String query="select * from login where user_id=? and password=?";
     
     try
     {
     preparedStatement=connection.prepareStatement(query);
     preparedStatement.setString(1,user);
     preparedStatement.setString(2,pass);
     resultSet=preparedStatement.executeQuery();
     if(resultSet.next())
     {
      return true;
     }
     else
         return false;
     }
     catch(Exception e)
     {
       return false;
     }
     finally
     {
      preparedStatement.close();
      resultSet.close();
     // connection.close();
     }
    }
    
     public boolean isValid(Integer user_id) throws SQLException
    {
     PreparedStatement preparedStatement =null;
     ResultSet resultSet=null;
     String query1="select * from nurse where employee_id=?";
     
     try
     {
     preparedStatement=connection.prepareStatement(query1);
     preparedStatement.setInt(1,user_id);
    // preparedStatement.setString(2,pass);
     resultSet=preparedStatement.executeQuery();
     if(resultSet.next())
     {
      return true;
     }
     else
         return false;
     }
     catch(Exception e)
     {
       return false;
     }
     finally
     {
      preparedStatement.close();
      resultSet.close();
     // connection.close();
     }
    }
}
