/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Professor;
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
public class ProfessorDAO implements Dao_base<Professor> {

    @Override
    public int adicionar(Professor prof, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO professor (nome, email, celular, academia_id) VALUES (?,?,?,?);");

            // Passando os parametros para o comando
            ps.setString(1, prof.getNome());
            ps.setString(2, prof.getEmail());
            ps.setString(3, prof.getCelular());
            ps.setInt(4, prof.getAcademiaId().getId());

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
    public int atualizar(Professor prof, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE professor SET "
                    + "nome = ?,"
                    + "email = ?,"
                    + "celular = ?"
                    + "WHERE ID = ?");

            // Passando os parametros para o comando
            ps.setString(1, prof.getNome());
            ps.setString(2, prof.getEmail());
            ps.setString(3, prof.getCelular());
            ps.setInt(4, prof.getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                var_retorno = prof.getId();
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
    public int remover(Professor prof, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM PROFESSOR WHERE ID = ? AND ACADEMIA_ID = ?");

            // Passando os parametros para o comando
            ps.setInt(1, prof.getId());
            ps.setInt(2, prof.getAcademiaId().getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                var_retorno = prof.getId();
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
    public List<Professor> listarTodos(int id_solicitante, Conexao con) {

        List<Professor> lista = new LinkedList<>();

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, NOME, EMAIL,CELULAR, ACADEMIA_ID FROM PROFESSOR WHERE ACADEMIA_ID = ?");
            ps.setInt(1, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();
            
            while (result.next()) {

                Professor professor = new Professor();
                populaProfessor(professor, result);
                lista.add(professor);

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
    public Professor buscarPorId(int id, int id_solicitante, Conexao con) {
        PreparedStatement ps;
        Professor retorno = new Professor();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, NOME, EMAIL,CELULAR, ACADEMIA_ID FROM PROFESSOR WHERE ID = ? and ACADEMIA_ID = ?");
            ps.setInt(1, id);
            ps.setInt(2, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaProfessor(retorno, result);

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
    public List<Professor> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        
        List<Professor> lista = new LinkedList<>();

        String sql = "";

        switch (tipoPesquisa) {
            case 1:
                sql = "SELECT ID, NOME, EMAIL,CELULAR, ACADEMIA_ID FROM PROFESSOR WHERE NOME like ? '%' AND ACADEMIA_ID = ?";
                break;

            case 2:
                sql = "SELECT ID, NOME, EMAIL,CELULAR, ACADEMIA_ID FROM PROFESSOR WHERE NOME like '%' ? AND ACADEMIA_ID = ?";
                break;

            case 3:
                sql = "SELECT ID, NOME, EMAIL,CELULAR, ACADEMIA_ID FROM PROFESSOR WHERE NOME like '%' ? '%' AND ACADEMIA_ID = ?";
                break;
        }

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Professor novoProf = new Professor();
                populaProfessor(novoProf, result);
                lista.add(novoProf);

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

   
    private void populaProfessor(Professor professor, ResultSet result) throws SQLException {
        professor.setId(result.getInt("ID"));
        professor.setNome(result.getString("NOME"));
        professor.setEmail(result.getString("EMAIL"));
        Academia obj = new Academia();
        obj.setId(result.getInt("ACADEMIA_ID"));
        professor.setAcademiaId(obj);
    }

}
