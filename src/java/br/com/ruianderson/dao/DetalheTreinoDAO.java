/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.DetalheTreino;
import br.com.ruianderson.model.Exercicio;
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
public class DetalheTreinoDAO implements Dao_base<DetalheTreino> {

    @Override
    public int adicionar(DetalheTreino detalheTreino, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO DETALHE_TREINO "
                    + "(SERIE, REPETICOES, EXERCICIO_ID, DIAS_SEMANA_ID, TREINO_ID, CARGA_INICIAL, ACADEMIA_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);");

            // Passando os parametros para o comando
            ps.setInt(1, detalheTreino.getSerie());
            ps.setInt(2, detalheTreino.getRepeticoes());
            ps.setInt(3, detalheTreino.getExercicio().getId());
            ps.setInt(4, detalheTreino.getDiasSemanaId());
            ps.setInt(5, detalheTreino.getTreino().getId());
            ps.setFloat(6, detalheTreino.getCargaInicial());
            ps.setInt(7, detalheTreino.getAcademia().getId());

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
                    + " WHERE ID = ? AND ACADEMIA_ID = ?;");

            // Passando os parametros para o comando
            ps.setInt(1, detalheTreino.getSerie());
            ps.setInt(2, detalheTreino.getRepeticoes());
            ps.setInt(3, detalheTreino.getExercicio().getId());
            ps.setInt(4, detalheTreino.getDiasSemanaId());
            ps.setInt(5, detalheTreino.getTreino().getId());
            ps.setFloat(6, detalheTreino.getCargaInicial());
            ps.setInt(7, detalheTreino.getId());
            ps.setInt(8, detalheTreino.getAcademia().getId());

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
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM DETALHE_TREINO WHERE ID = ? AND ACADEMIA_ID = ?;");

            // Passando os parametros para o comando
            ps.setInt(1, detalheTreino.getId());
            ps.setInt(2, detalheTreino.getAcademia().getId());

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
    public List<DetalheTreino> listarTodos(int id_solicitante, Conexao con) {
        
        PreparedStatement ps;
        List<DetalheTreino> lista = new LinkedList<>();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, SERIE, REPETICOES, EXERCICIO_ID, "
                    + "DIAS_SEMANA_ID, TREINO_ID, CARGA_INICIAL, ACADEMIA_ID FROM DETALHE_TREINO "
                   + "WHERE ACADEMIA_ID = ?;");

            ps.setInt(1, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                DetalheTreino detalheTreino = new DetalheTreino();

                populaDetalheTreino(detalheTreino, result);

                lista.add(detalheTreino);

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
    public DetalheTreino buscarPorId(int id, int id_solicitante, Conexao con) {
        PreparedStatement ps;
        DetalheTreino retorno = new DetalheTreino();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, SERIE, REPETICOES, EXERCICIO_ID, "
                    + "DIAS_SEMANA_ID, TREINO_ID, CARGA_INICIAL, ACADEMIA_ID FROM DETALHE_TREINO "
                   + "WHERE ACADEMIA_ID = ? AND ID = ?;");

            ps.setInt(1, id_solicitante);
            ps.setInt(2, id);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaDetalheTreino(retorno, result);

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
    public List<DetalheTreino> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Nao implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    private void populaDetalheTreino(DetalheTreino detalheTreino, ResultSet result) throws SQLException {
        detalheTreino.setId(result.getInt("ID"));
        detalheTreino.setDiasSemanaId(result.getInt("DIAS_SEMANA_ID"));
        detalheTreino.setCargaInicial(result.getFloat("CARGA_INICIAL"));
        detalheTreino.setRepeticoes(result.getInt("REPETICOES"));
        
        Academia academia = new Academia(result.getInt("ACADEMIA_ID"));
        Treino treino = new Treino(result.getInt("TREINO_ID"));
        Exercicio exercicio = new Exercicio(result.getInt("EXERCICIO_ID"));
        
        detalheTreino.setAcademia(academia);
        detalheTreino.setExercicio(exercicio);
        detalheTreino.setTreino(treino);
        
    }

}
