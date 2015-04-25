/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Exercicio;
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
public class ExercicioDAO implements Dao_base<Exercicio> {

    @Override
    public int adicionar(Exercicio objt, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO exercicio (descricao,apelido,academia_id) "
                    + "VALUES (?,?,?);".toLowerCase());

            // Passando os parametros para o comando
            ps.setString(1, objt.getDescricao());
            ps.setString(2, objt.getApelido());
            ps.setInt(3, objt.getAcademiaId().getId());

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
    public int atualizar(Exercicio objt, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE EXERCICIO SET "
                    + "descricao = ?,"
                    + "apelido = ?,"
                    + "academia_id = ?"
                    + " WHERE ID = ? AND ACADEMIA_ID = ? ".toLowerCase());

            // Passando os parametros para o comando
            ps.setString(1, objt.getDescricao());
            ps.setString(2, objt.getApelido());
            ps.setInt(3, objt.getAcademiaId().getId());
            ps.setInt(4, objt.getId());
            ps.setInt(5, objt.getAcademiaId().getId());
            

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
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
    public int remover(Exercicio objt, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM EXERCICIO WHERE ID = ?".toLowerCase());

            // Passando os parametros para o comando
            ps.setInt(1, objt.getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
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
    public List<Exercicio> listarTodos(int id_academia, Conexao con) {
        List<Exercicio> lista = new LinkedList<>();

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DESCRICAO, APELIDO, ACADEMIA_ID FROM EXERCICIO WHERE ACADEMIA_ID = ?".toLowerCase());
            ps.setInt(1, id_academia);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Exercicio exercicio = new Exercicio();
                populaExercicio(exercicio, result);
                lista.add(exercicio);

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
    public Exercicio buscarPorId(int id, int id_academia, Conexao con) {
        PreparedStatement ps;
        Exercicio retorno = new Exercicio();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT ID, DESCRICAO, APELIDO, ACADEMIA_ID FROM EXERCICIO WHERE ID = ? and ACADEMIA_ID = ?".toLowerCase());
            ps.setInt(1, id);
            ps.setInt(2, id_academia);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaExercicio(retorno, result);

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
    public List<Exercicio> buscarPorNome(String nome, int tipoPesquisa, int id_academia, Conexao con) {
        List<Exercicio> lista = new LinkedList<>();

        String sql = "";

        switch (tipoPesquisa) {
            case 1:
                sql = "SELECT ID, DESCRICAO, APELIDO, ACADEMIA_ID FROM EXERCICIO WHERE APELIDO like ? '%' AND ACADEMIA_ID = ?".toLowerCase();
                break;

            case 2:
                sql = "SELECT ID, DESCRICAO, APELIDO, ACADEMIA_ID FROM EXERCICIO WHERE APELIDO like '%' ? AND ACADEMIA_ID = ?".toLowerCase();
                break;

            case 3:
                sql = "SELECT ID, DESCRICAO, APELIDO, ACADEMIA_ID FROM EXERCICIO WHERE APELIDO like '%' ? '%' AND ACADEMIA_ID = ?".toLowerCase();
                break;
        }

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, id_academia);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Exercicio novoExercicio = new Exercicio();
                populaExercicio(novoExercicio, result);
                lista.add(novoExercicio);

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

   

    private void populaExercicio(Exercicio exercicio, ResultSet result) throws SQLException {
        exercicio.setId(result.getInt("id"));
        exercicio.setDescricao(result.getString("descricao"));
        exercicio.setApelido(result.getString("apelido"));

        Academia objacademia = new Academia();
        objacademia.setId(result.getInt("academia_id"));

        exercicio.setAcademiaId(objacademia);

    }

}
