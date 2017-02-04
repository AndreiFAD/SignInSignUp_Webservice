/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.fadcafe.wscontroll;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Fekete András Demeter
 * 
 */
public class MySqlMethod {

public String ElementFromDB (String method,String SqlQuery) {
    String url = "jdbc:mysql://host:port/shema";
    String username = "user";
    String password = "pass";
    String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    String sqlresults = "";
    System.out.println("Connecting database...");
    try {
        Class.forName(DATABASE_DRIVER);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MySqlMethod.class.getName()).log(Level.SEVERE, null, ex);
    }
      Connection conn = null;
    try {
        conn = (Connection) DriverManager.getConnection(url,username,password);
    } catch (SQLException ex) {
        Logger.getLogger(MySqlMethod.class.getName()).log(Level.SEVERE, null, ex);
    }
      
      try {
            
            System.out.println("Database connection is active");
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            
            if (method == "insert"){
                stmt.executeUpdate(SqlQuery);
                   return "sql insert run successful";
            } else {
                    try{
                        rs = stmt.executeQuery(SqlQuery);
                        ResultSetMetaData metaData = rs.getMetaData();
                        int count = metaData.getColumnCount();
                        
                        for (int i = 1; i < count+1; i++) {
                            sqlresults=sqlresults+"#";
                            rs = stmt.executeQuery(SqlQuery);
                                while (rs.next()) {
                                    if (rs.getString(metaData.getColumnLabel(1))!=null)
                                    {
                                        try {

                                          sqlresults=sqlresults+"';'"+(rs.getString(metaData.getColumnLabel(i)));
 
                                        }catch (SQLException e) {
                                            System.out.println(e.getMessage());
                                        }                               
                                    }
                                }
                        }
                        return sqlresults;
                        
                       } catch (SQLException e) {
                       
                       System.out.println(e.getMessage()); 
                        return e.getMessage();
                      }
            }

            
        } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                    return "sql run with errors";
        } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(MySqlMethod.class.getName()).log(Level.SEVERE, null, ex);
                    }
            System.out.println("Database connection close now");
      }
        
    }
    
}

