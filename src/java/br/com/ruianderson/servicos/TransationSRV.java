/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.dao.TransationDAO;
import br.com.ruianderson.dbutil.Conexao;
import java.sql.SQLException;

/**
 *
 * @author Rui
 */
public final class TransationSRV {
    
     public final static Conexao begin() throws ClassNotFoundException, SQLException{
        Conexao con = new Conexao();
        con.GetConnection().setAutoCommit(false);
        return con;
    }
    
    
    public final static void commit(Conexao con) throws ClassNotFoundException, SQLException{
        con.GetConnection().commit();
        con.closeAll();
    }
    
    public final static void rollback(Conexao con) throws ClassNotFoundException, SQLException{
       con.GetConnection().rollback();
       con.closeAll();
    }
    
}
