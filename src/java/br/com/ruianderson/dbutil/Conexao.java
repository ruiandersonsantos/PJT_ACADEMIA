/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rui
 */
public class Conexao {
    
    	
private Connection cx = null;
     
    public Connection GetConnection() throws SQLException {
        
        if(cx==null){
        	
        	try {
        	
                    //MySql
                        Class.forName("com.mysql.jdbc.Driver");

                        cx = DriverManager.getConnection("jdbc:mysql://localhost/academia","root","42301886");

                        if(cx != null){
                              //  System.out.print("Conectou ao mySql");
                        }

                    } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        System.out.print("Não Conectou");
                        System.out.print(e1.getMessage());
                        e1.printStackTrace();
                    }

        	
            
        }
        
        //cx.setAutoCommit(false);
        
        return cx;
    }
    
    public Statement getStatement() throws ClassNotFoundException, ClassNotFoundException, SQLException{
        
        return GetConnection().createStatement();
        
    }
    
     public Statement getPreparedStatement(String sql) throws ClassNotFoundException, ClassNotFoundException, SQLException{
        
        return GetConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        
    }
     
     public void closeAll() throws SQLException{
         if(cx!=null){
             cx.close();
         }
         
     }
    


    
}
