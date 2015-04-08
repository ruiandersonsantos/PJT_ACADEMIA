/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Matricula;
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
public class MatriculaDAO implements Dao_base<Matricula> {

    @Override
    public int adicionar(Matricula matricula, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO MATRICULA (ID, DT_MATRICULA, STATUS, OBSERVACAO, ACADEMIA_ID, ALUNO_ID) "
                    + "VALUES (?,?,?,?,?,?);");

            // Passando os parametros para o comando
            ps.setInt(1, matricula.getId());
            ps.setDate(2, (Date) matricula.getDtMatricula());
            ps.setInt(3, matricula.getStatus());
            ps.setString(4, matricula.getObservacao());
            ps.setInt(5, matricula.getAcademia().getId());
            ps.setInt(6, matricula.getAluno().getId());

            //Executando o camando no banco
            int retornoDoID = 0;
            retornoDoID = ps.executeUpdate();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID > 0) {
                var_retorno = matricula.getId();
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
    public int atualizar(Matricula matricula, Conexao con) {
        
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE matricula SET "
                    + "dt_inativacao = ?,"
                    + "status = ?,"
                    + " observacao = ?"
                    + " WHERE ID = ? and ACADEMIA_ID = ? ");

            // Passando os parametros para o comando
            ps.setDate(1, (Date) matricula.getDtInativacao());
            ps.setInt(2, matricula.getStatus());
            ps.setString(3, matricula.getObservacao());
            ps.setInt(4, matricula.getId());
            ps.setInt(5, matricula.getAcademia().getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                var_retorno = matricula.getId();
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
    public int remover(Matricula matricula, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement(" DELETE FROM MATRICULA WHERE ID = ? AND ACADEMIA_ID = ? ");

            // Passando os parametros para o comando
            ps.setInt(1, matricula.getId());
            ps.setInt(2, matricula.getAcademia().getId());
           

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                var_retorno = matricula.getId();
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
    public List<Matricula> listarTodos(int id_solicitante, Conexao con) {
        
        List<Matricula> lista = new LinkedList<>();

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DT_MATRICULA, DT_INATIVACAO, STATUS, OBSERVACAO, ACADEMIA_ID, ALUNO_ID "
                    + "FROM MATRICULA WHERE ACADEMIA_ID = ?");
            
            ps.setInt(1, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();
            
            while (result.next()) {

                Matricula matricula = new Matricula();
                populaMatricula(matricula, result);
                lista.add(matricula);

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
    public Matricula buscarPorId(int id, int id_solicitante, Conexao con) {
        
         PreparedStatement ps;
        Matricula retorno = new Matricula();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DT_MATRICULA, DT_INATIVACAO, STATUS, OBSERVACAO, ACADEMIA_ID, ALUNO_ID "
                    + "FROM MATRICULA WHERE ACADEMIA_ID = ? AND ID = ?");
            ps.setInt(1, id_solicitante);
            ps.setInt(2, id);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaMatricula(retorno, result);

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
    public List<Matricula> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Metodo não implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    private void populaMatricula(Matricula matricula, ResultSet result) throws SQLException {
        matricula.setId(result.getInt("ID"));
        matricula.setDtMatricula(result.getDate("DT_MATRICULA"));
        matricula.setDtInativacao(result.getDate("DT_INATIVACAO"));
        matricula.setStatus(result.getInt("STATUS"));
        matricula.setObservacao(result.getString("OBSERVACAO"));
        
        Academia academia = new Academia();
        academia.setId(result.getInt("ACADEMIA_ID"));
        
        Aluno aluno = new Aluno();
        aluno.setId(result.getInt("ALUNO_ID"));
        
        matricula.setAcademia(academia);
        matricula.setAluno(aluno);
    }

}
