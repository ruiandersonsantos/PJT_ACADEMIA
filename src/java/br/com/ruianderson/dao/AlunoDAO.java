/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.utilitarios.Obj_gen;
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
public class AlunoDAO implements Dao_base<Aluno> {

    @Override
    public int adicionar(Aluno aluno, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO aluno (nome,celular,telefone,sexo,dt_nascimento,email, academia_id) "
                    + "VALUES (?,?,?,?,?,?,?);");

            // Passando os parametros para o comando
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCelular());
            ps.setString(3, aluno.getTelefone());
            ps.setString(4, aluno.getSexo());
            ps.setString(5, String.valueOf(aluno.getDtNascimento()));
            ps.setString(6, aluno.getEmail());
            ps.setInt(7, aluno.getAcademia().getId());

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
    public int atualizar(Aluno aluno, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE aluno SET "
                    + "nome = ?,"
                    + "celular = ?,"
                    + "telefone = ?,"
                    + "sexo = ?,"
                    + "dt_nascimento = ?,"
                    + "email = ? "
                    + "WHERE ID = ?");

            // Passando os parametros para o comando
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCelular());
            ps.setString(3, aluno.getTelefone());
            ps.setString(4, aluno.getSexo());
            ps.setString(5, String.valueOf(aluno.getDtNascimento()));
            ps.setString(6, aluno.getEmail());
            ps.setInt(7, aluno.getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                var_retorno = aluno.getId();
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
    public int remover(Aluno aluno, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM ALUNO WHERE ID = ? AND ACADEMIA_ID = ?");

            // Passando os parametros para o comando
            ps.setInt(1, aluno.getId());
            ps.setInt(2, aluno.getAcademia().getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                var_retorno = aluno.getId();
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
    public List listarTodos(int id_, Conexao con) {
        return null;
    }

    @Override
    public Aluno buscarPorId(int id, int id_solicitante, Conexao con) {

        PreparedStatement ps;
        Aluno retorno = new Aluno();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, NOME, CELULAR, TELEFONE, SEXO, DT_NASCIMENTO, EMAIL FROM ALUNO WHERE ID = ?");
            ps.setInt(1, id);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaAluno(retorno, result);

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
    public List buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {

        List<Aluno> lista = new LinkedList<>();

        String sql = "";

        switch (tipoPesquisa) {
            case 1:
                sql = "SELECT ID, NOME, CELULAR, TELEFONE, SEXO, DT_NASCIMENTO, EMAIL FROM ALUNO WHERE NOME like ? '%'";
                break;

            case 2:
                sql = "SELECT ID, NOME, CELULAR, TELEFONE, SEXO, DT_NASCIMENTO, EMAIL FROM ALUNO WHERE NOME like '%' ?";
                break;

            case 3:
                sql = "SELECT ID, NOME, CELULAR, TELEFONE, SEXO, DT_NASCIMENTO, EMAIL FROM ALUNO WHERE NOME like '%' ? '%'";
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

                Aluno novoaluno = new Aluno();
                populaAluno(novoaluno, result);
                lista.add(novoaluno);

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

    private void populaAluno(Aluno aluno, ResultSet result) throws SQLException {

        aluno.setId(result.getInt("ID"));
        aluno.setNome(result.getString("NOME"));
        aluno.setCelular(result.getString("CELULAR"));
        aluno.setTelefone(result.getString("TELEFONE"));
        aluno.setSexo(result.getString("SEXO"));
        aluno.setDtNascimento(Obj_gen.convertStringToSqlDate(result.getString("DT_NASCIMENTO")));
        aluno.setEmail(result.getString("EMAIL"));

    }

}
