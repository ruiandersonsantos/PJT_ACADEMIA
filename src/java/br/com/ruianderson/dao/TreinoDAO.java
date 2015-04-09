/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Treino;
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
public class TreinoDAO implements Dao_base<Treino> {

    @Override
    public int adicionar(Treino treino, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO TREINO (DESCRICAO, OBSERVACAO, OBJETIVO) VALUES(?, ?, ?);");

            // Passando os parametros para o comando
            ps.setString(1, treino.getDescricao());
            ps.setString(2, treino.getObservacao());
            ps.setString(3, treino.getObjetivo());

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
    public int atualizar(Treino treino, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE TREINO SET "
                    + "DESCRICAO = ?, "
                    + "OBSERVACAO = ?, "
                    + "OBJETIVO = ? WHERE ID = ? ;");

            // Passando os parametros para o comando
            ps.setString(1, treino.getDescricao());
            ps.setString(2, treino.getObservacao());
            ps.setString(3, treino.getObjetivo());
            ps.setInt(4, treino.getId());

            //Executando o camando no banco
            int retornoDoID = ps.executeUpdate();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID > 0) {
                var_retorno = retornoDoID;
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
    public int remover(Treino treino, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM TREINO WHERE ID = ? ;");

            // Passando os parametros para o comando
            ps.setInt(1, treino.getId());

            //Executando o camando no banco
            int retornoDoID = ps.executeUpdate();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID > 0) {
                var_retorno = retornoDoID;
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
    public List<Treino> listarTodos(int id_solicitante, Conexao con) {

        PreparedStatement ps;
        List<Treino> lista = new LinkedList<>();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DESCRICAO, OBSERVACAO, OBJETIVO FROM TREINO "
                    + "INNER JOIN DETALHE_TREINO ON TREINO.ID = DETALHE_TREINO.TREINO_ID "
                    + "INNER JOIN EXERCICIO ON EXERCICIO.ID = DETALHE_TREINO.EXERCICIO_ID "
                    + "WHERE EXERCICIO.ACADEMIA_ID = ?;");

            ps.setInt(1, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Treino treino = new Treino();

                populaTreino(treino, result);

                lista.add(treino);

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
    public Treino buscarPorId(int id, int id_solicitante, Conexao con) {
        PreparedStatement ps;
        Treino retorno = new Treino();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DESCRICAO, OBSERVACAO, OBJETIVO FROM TREINO "
                    + "INNER JOIN DETALHE_TREINO ON TREINO.ID = DETALHE_TREINO.TREINO_ID "
                    + "INNER JOIN EXERCICIO ON EXERCICIO.ID = DETALHE_TREINO.EXERCICIO_ID "
                    + "WHERE EXERCICIO.ACADEMIA_ID = ? AND TREINO.ID = ? ;");

            ps.setInt(1, id_solicitante);
            ps.setInt(2, id);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaTreino(retorno, result);

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
    public List<Treino> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        PreparedStatement ps;
        List<Treino> lista = new LinkedList<>();
        
        String sql = "";
        
         switch (tipoPesquisa) {
            case 1:
                sql = "SELECT ID, DESCRICAO, OBSERVACAO, OBJETIVO FROM TREINO "
                    + "INNER JOIN DETALHE_TREINO ON TREINO.ID = DETALHE_TREINO.TREINO_ID "
                    + "INNER JOIN EXERCICIO ON EXERCICIO.ID = DETALHE_TREINO.EXERCICIO_ID "
                    + "WHERE EXERCICIO.ACADEMIA_ID = ? AND DESCRICAO LIKE ? '%';";
                break;
            
            case 2:
                sql = "SELECT ID, DESCRICAO, OBSERVACAO, OBJETIVO FROM TREINO "
                    + "INNER JOIN DETALHE_TREINO ON TREINO.ID = DETALHE_TREINO.TREINO_ID "
                    + "INNER JOIN EXERCICIO ON EXERCICIO.ID = DETALHE_TREINO.EXERCICIO_ID "
                    + "WHERE EXERCICIO.ACADEMIA_ID = ? DESCRICAO LIKE '%' ?;";
                break;
            
            case 3:
                sql = "SELECT ID, DESCRICAO, OBSERVACAO, OBJETIVO FROM TREINO "
                    + "INNER JOIN DETALHE_TREINO ON TREINO.ID = DETALHE_TREINO.TREINO_ID "
                    + "INNER JOIN EXERCICIO ON EXERCICIO.ID = DETALHE_TREINO.EXERCICIO_ID "
                    + "WHERE EXERCICIO.ACADEMIA_ID = ? AND DESCRICAO LIKE '%' ? '%' ;";
                break;
        }

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement(sql);

            ps.setInt(1, id_solicitante);
            ps.setString(2, nome);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Treino treino = new Treino();

                populaTreino(treino, result);

                lista.add(treino);

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

    private void populaTreino(Treino retorno, ResultSet result) throws SQLException {
        retorno.setId(result.getInt("ID"));
        retorno.setDescricao(result.getString("DESCRICAO"));
        retorno.setObservacao(result.getString("OBSERVACAO"));
        retorno.setObjetivo(result.getString("OBJETIVO"));
    }

}
