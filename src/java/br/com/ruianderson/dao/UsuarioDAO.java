/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.dao;

import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.interfaces.Dao_base;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Usuario;
import br.com.ruianderson.utilitarios.Obj_gen;
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
public class UsuarioDAO implements Dao_base<Usuario> {

    @Override
    public int adicionar(Usuario usuario, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("insert into usuario (login, senha, academia_id, par, acesso_id, status) "
                    + "values (?,?,?,?,?,?)");

            // Passando os parametros para o comando
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getAcademiaId().getId());
            ps.setString(4, "professor".toUpperCase());
            ps.setInt(5, 1);
            ps.setInt(6, usuario.getStatus());

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
    public int atualizar(Usuario usuario, Conexao con) {

        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("update usuario set status = ? where id = ? and academia_id = ? "
                    + "values (?,?,?,?,?,?)");

            // Passando os parametros para o comando
            ps.setInt(1, usuario.getStatus());
            ps.setInt(2, usuario.getId());
            ps.setInt(3, usuario.getAcademiaId().getId());

            //Executando o camando no banco
            int retornoDoID = ps.executeUpdate();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID > 0) {
                var_retorno = usuario.getId();
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

    public Usuario logarNoSistema(String login, String senha, int academia_id, Conexao con) {

        PreparedStatement ps;
        Usuario retorno = new Usuario();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("select login, academia_id, status from usuario "
                    + "where login = ? and senha = ? and academia_id = ?");
            ps.setString(1, login);
            ps.setString(2, senha);
            ps.setInt(3, academia_id);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            if(result.next()) {

                populaUsuario(retorno, result);

            }else{
                
                retorno = null;
                
            }

            // fechando conexão
            ps.close();
            result.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try {
            con.closeAll();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    @Override
    public int remover(Usuario objt, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listarTodos(int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarPorId(int id, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void populaUsuario(Usuario retorno, ResultSet result) throws SQLException {
        retorno.setLogin(result.getString("login"));
        int idAcademia = result.getInt("academia_id");
        retorno.setAcademiaId(new Academia(idAcademia));
        retorno.setStatus(result.getInt("status"));
    }

}
