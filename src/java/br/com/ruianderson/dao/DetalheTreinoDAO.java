/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.DetalheTreino;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class DetalheTreinoDAO implements Dao_base<DetalheTreino> {

    @Override
    public int adicionar(DetalheTreino detalheTreino, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO DETALHE_TREINO "
                    + "(SERIE, REPETICOES, EXERCICIO_ID, DIAS_SEMANA_ID, TREINO_ID, CARGA_INICIAL) "
                    + "VALUES (?, ?, ?, ?, ?, ?);");

            // Passando os parametros para o comando
            ps.setInt(1, detalheTreino.getSerie());
            ps.setInt(2, detalheTreino.getRepeticoes());
            ps.setInt(3, detalheTreino.getExercicio().getId());
            ps.setInt(4, detalheTreino.getDiasSemanaId());
            ps.setInt(5, detalheTreino.getTreino().getId());
            ps.setFloat(6, detalheTreino.getCargaInicial());

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
    public int atualizar(DetalheTreino detalheTreino, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE DETALHE_TREINO SET "
                    + "SERIE = ?, REPETICOES = ?, EXERCICIO_ID = ?, DIAS_SEMANA_ID = ?, TREINO_ID = ?, CARGA_INICIAL = ?  "
                    + " WHERE ID = ?;");

            // Passando os parametros para o comando
            ps.setInt(1, detalheTreino.getSerie());
            ps.setInt(2, detalheTreino.getRepeticoes());
            ps.setInt(3, detalheTreino.getExercicio().getId());
            ps.setInt(4, detalheTreino.getDiasSemanaId());
            ps.setInt(5, detalheTreino.getTreino().getId());
            ps.setFloat(6, detalheTreino.getCargaInicial());
            ps.setInt(7, detalheTreino.getId());

            //Executando o camando no banco
            int retornoDoID =  ps.executeUpdate();

            

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
    public int remover(DetalheTreino detalheTreino, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalheTreino> listarTodos(int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetalheTreino buscarPorId(int id, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalheTreino> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
