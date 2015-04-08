/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Academia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class AcademiaDAO implements Dao_base<Academia> {

    @Override
    public int adicionar(Academia academia,Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO academia (razao, cnpj, contato, telefone, email) "
                    + "VALUES (?,?,?,?,?);");

            // Passando os parametros para o comando
            ps.setString(1, academia.getRazao());
            ps.setString(2, academia.getCnpj());
            ps.setString(3, academia.getContato());
            ps.setString(4, academia.getTelefone());
            ps.setString(5, academia.getEmail());

            //Executando o camando no banco
            ps.executeUpdate();

            // pegando a chave do registro inserido
            ResultSet retornoDoID = ps.getGeneratedKeys();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID.next()) {
                var_retorno = retornoDoID.getInt(1);
            }

            // fechando conexão
            ps.close();
            retornoDoID.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return var_retorno;
    }

    @Override
    public int atualizar(Academia academia, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE academia SET "
                    + "razao = ?, "
                    + "cnpj = ?, "
                    + "contato = ?, "
                    + "telefone = ?, "
                    + "email = ? WHERE ID = ? ");

            // Passando os parametros para o comando
            ps.setString(1, academia.getRazao());
            ps.setString(2, academia.getCnpj());
            ps.setString(3, academia.getContato());
            ps.setString(4, academia.getTelefone());
            ps.setString(5, academia.getEmail());
            ps.setInt(6, academia.getId());

            //Executando o camando no banco
            int resposta = ps.executeUpdate();
            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (resposta > 0) {
                var_retorno = academia.getId();
            }

            // fechando conexão
            ps.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return var_retorno;
    }

    @Override
    public int remover(Academia academia, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM ACADEMIA WHERE ID = ?");

            // Passando os parametros para o comando
            ps.setInt(1, academia.getId());

            //Executando o camando no banco
            int resposta = ps.executeUpdate();
            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (resposta > 0) {
                var_retorno = academia.getId();
            }

            // fechando conexão
            ps.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return var_retorno;
    }

    @Override
    public List<Academia> listarTodos(int id_academia, Conexao con) {

        List<Academia> lista = new LinkedList<>();

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, RAZAO, CNPJ, CONTATO, TELEFONE, EMAIL FROM ACADEMIA");

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Academia academia = new Academia();
                populaAcademia(academia, result);
                lista.add(academia);

            }

            // fechando conexão
            ps.close();
            result.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Academia buscarPorId(int id, int id_solicitante, Conexao con) {
        
        PreparedStatement ps;
        Academia retorno = new Academia();
       
        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, RAZAO, CNPJ, CONTATO, TELEFONE, EMAIL FROM ACADEMIA WHERE ID = ?");
            ps.setInt(1, id);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaAcademia(retorno, result);

            }

            // fechando conexão
            ps.close();
            result.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    @Override
    public List<Academia> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {

        List<Academia> lista = new LinkedList<>();

        String sql = "";

        switch (tipoPesquisa) {
            case 1:
                sql = "SELECT ID, RAZAO, CNPJ, CONTATO, TELEFONE, EMAIL FROM ACADEMIA WHERE RAZAO LIKE ? '%'";
                break;

            case 2:
                sql = "SELECT ID, RAZAO, CNPJ, CONTATO, TELEFONE, EMAIL FROM ACADEMIA WHERE RAZAO LIKE '%' ?";
                break;

            case 3:
                sql = "SELECT ID, RAZAO, CNPJ, CONTATO, TELEFONE, EMAIL FROM ACADEMIA WHERE RAZAO LIKE '%' ? '%'";
                break;
        }

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement(sql);
            ps.setString(1, nome);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Academia novaAcademia = new Academia();
                populaAcademia(novaAcademia, result);
                lista.add(novaAcademia);

            }
            // fechando conexão
            ps.close();
            result.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    
    
    private void populaAcademia(Academia academia, ResultSet result) throws SQLException {
        academia.setId(result.getInt("ID"));
        academia.setRazao(result.getString("RAZAO"));
        academia.setCnpj(result.getString("CNPJ"));
        academia.setContato(result.getString("CONTATO"));
        academia.setTelefone(result.getString("TELEFONE"));
        academia.setEmail(result.getString("EMAIL"));
    }

}
