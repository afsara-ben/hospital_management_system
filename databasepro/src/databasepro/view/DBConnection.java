/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import java.sql.*;
/**
 *
 * @author user
 */
public class DBConnection {
    
    public static Connection LoginConnector()
    {
       try
       {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##project", "1234");
        return conn;
       }
       catch(Exception e)
       {
        System.out.println(e);
        return null;
       }
    }
}
