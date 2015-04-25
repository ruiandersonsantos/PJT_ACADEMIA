/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.HistoricoTreino;
import br.com.ruianderson.model.Matricula;
import br.com.ruianderson.utilitarios.Obj_gen;
import com.sun.javafx.scene.text.HitInfo;
import java.sql.Date;
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
public class HistoricoTreinoDAO implements Dao_base<HistoricoTreino> {

    @Override
    public int adicionar(HistoricoTreino objt, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO historico_treino "
                    + "(DT_ATIVACAO, STATUS, OBSERVACAO, MATRICULA_ID, TREINO_ID, AVALIACAO_ID, PROFESSOR_ID, ACADEMIA_ID) "
                    + "VALUES (?,?,?,?,?,?,?,?);".toLowerCase());

            // Passando os parametros para o comando
            ps.setDate(1, (Date) objt.getDtAtivacao());
            ps.setInt(2, objt.getStatus());
            ps.setString(3, objt.getObservacao());
            ps.setInt(4, objt.getMatriculaId().getId());
            ps.setInt(5, objt.getTreinoId().getId());
            ps.setInt(6, objt.getAvaliacaoId().getId());
            ps.setInt(7, objt.getProfessorId().getId());
            ps.setInt(8, objt.getAcademia().getId());

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
    public int atualizar(HistoricoTreino objt, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE historico_treino SET "
                    + " DT_FINALIZACAO ?, STATUS = ?, OBSERVACAO = ? WHERE ID = ? AND ACADEMIA_ID = ?".toLowerCase());

            // Passando os parametros para o comando
            ps.setDate(1, (Date) objt.getDtFinalizacao());
            ps.setInt(2, objt.getStatus());
            ps.setString(3, objt.getObservacao());
            ps.setInt(4, objt.getId());
            ps.setInt(5, objt.getAcademia().getId());

            //Executando o camando no banco
            int retornoDoID = ps.executeUpdate();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID > 0) {
                var_retorno = objt.getId();
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
    public int remover(HistoricoTreino objt, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM historico_treino WHERE ID = ? AND ACADEMIA_ID = ? and DT_FINALIZACAO IS NULL;".toLowerCase());

            // Passando os parametros para o comando
            ps.setInt(1, objt.getId());
            ps.setInt(2, objt.getAcademia().getId());

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
    public List<HistoricoTreino> listarTodos(int id_solicitante, Conexao con) {
      throw new UnsupportedOperationException("Metodo não implementado."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<HistoricoTreino> listarTodos(int id_solicitante, Conexao con, Matricula matricula) {
        
        PreparedStatement ps;
        List<HistoricoTreino> lista = new LinkedList<>();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DT_ATIVACAO, DT_FINALIZACAO, STATUS, OBSERVACAO FROM historico_treino "
                    + "WHERE ACADEMIA_ID = ? AND MATRICULA_ID = ?;".toLowerCase());

            ps.setInt(1, id_solicitante);
            ps.setInt(2, matricula.getId());

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                HistoricoTreino hist_Treino = new HistoricoTreino();

                populaHistoricoTreino(hist_Treino, result);

                lista.add(hist_Treino);

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
    public HistoricoTreino buscarPorId(int id, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Metodo não implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HistoricoTreino> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Metodo não implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    private void populaHistoricoTreino(HistoricoTreino hist_Treino, ResultSet result) throws SQLException {
        hist_Treino.setId(result.getInt("ID"));
        hist_Treino.setDtAtivacao(result.getDate("DT_ATIVACAO"));
        hist_Treino.setDtFinalizacao(result.getDate("DT_FINALIZACAO"));
        hist_Treino.setStatus(result.getInt("STATUS"));
        hist_Treino.setObservacao(result.getString("OBSERVACAO"));
    }

}
