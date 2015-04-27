/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dbutil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rui
 */
public class Conexao implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Connection cx = null;

    public Connection GetConnection() throws SQLException {

        if (cx == null) {

            try {

                //MySql
                Class.forName("com.mysql.jdbc.Driver");

                // Conexão local
                cx = DriverManager.getConnection("jdbc:mysql://localhost/academia","root","42301886");
                // Conexão remota
                //cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/ruiand_academia", "ruiand_root", "42301886");

                // Conexão remota testes
                //cx = DriverManager.getConnection("jdbc:mysql://ruianderson.com.br:3306/ruiand_academia","ruiand_root","42301886");
                if (cx != null) {
                    System.out.println("Conectou ao mySql");
                }

            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                System.out.println("Não Conectou");
                System.out.println(e1.getMessage());
                e1.printStackTrace();
            }

        }

        //cx.setAutoCommit(false);
        return cx;
    }

    public Statement getStatement() throws ClassNotFoundException, ClassNotFoundException, SQLException {

        return GetConnection().createStatement();

    }

    public Statement getPreparedStatement(String sql) throws ClassNotFoundException, ClassNotFoundException, SQLException {

        return GetConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    }

    public void closeAll() throws SQLException {
        System.out.println("Fechou a conexao!");
        if (cx != null) {
            cx.close();
        }

    }

}
