/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class TransationDAO extends Conexao {

    public int abreTransacao() throws ClassNotFoundException {

        int retorno = 0;

        try {
            PreparedStatement ps = (PreparedStatement) getPreparedStatement("START TRANSACTION");
            retorno = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TransationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public void efetivarTransacao() {

        try {
            GetConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(TransationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void voltarTransacao() throws ClassNotFoundException {

        try {
            GetConnection().rollback();
        } catch (SQLException ex) {
            Logger.getLogger(TransationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
