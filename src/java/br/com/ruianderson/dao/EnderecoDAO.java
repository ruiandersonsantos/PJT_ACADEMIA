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
import br.com.ruianderson.model.Cidade;
import br.com.ruianderson.model.Endereco;
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
public class EnderecoDAO implements Dao_base<Endereco> {

    @Override
    public int adicionar(Endereco endereco, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("INSERT INTO ENDERECO "
                    + "(LOGRADOURO, BAIRRO, NUMERO, COMPLEMENTO, CEP, PAR, ACADEMIA_ID, ALUNO_ID, CIDADE_ID) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);");

            // Passando os parametros para o comando
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getBairro());
            ps.setInt(3, endereco.getNumero());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getCep());
            ps.setString(6, endereco.getPar());
            ps.setInt(7, endereco.getAcademiaId().getId());
            ps.setInt(8, endereco.getAlunoId().getId());
            ps.setInt(9, endereco.getCidadeId().getId());

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
    public int atualizar(Endereco endereco, Conexao con) {
        int var_retorno = 0;

        PreparedStatement ps;

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("UPDATE ENDERECO SET  "
                    + "LOGRADOURO = ?, "
                    + "BAIRRO = ?, "
                    + "NUMERO = ?, "
                    + "COMPLEMENTO = ?, "
                    + "CEP = ?, "
                    + "PAR = ?, "
                    + "ACADEMIA_ID = ?, "
                    + "ALUNO_ID = ?, "
                    + "CIDADE_ID = ? WHERE ALUNO_ID = ? ");

            // Passando os parametros para o comando
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getBairro());
            ps.setInt(3, endereco.getNumero());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getCep());
            ps.setString(6, endereco.getPar());
            ps.setInt(7, endereco.getAcademiaId().getId());
            ps.setInt(8, endereco.getAlunoId().getId());
            ps.setInt(9, endereco.getCidadeId().getId());
            ps.setInt(10, endereco.getAlunoId().getId());

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
    public int remover(Endereco endereco, Conexao con) {
        int var_retorno = 0;
        
        PreparedStatement ps;
        
        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("DELETE FROM ENDERECO WHERE ALUNO_ID = ?");

            // Passando os parametros para o comando
            ps.setInt(1, endereco.getAlunoId().getId());

            //Executando o camando no banco
            int result = ps.executeUpdate();

            // Verificando se houve execução com sucesso.
            if (result > 0) {
                
                var_retorno = endereco.getAlunoId().getId();
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
    public List<Endereco> listarTodos(int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Metodo não implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco buscarPorId(int id_aluno, int id_solicitante, Conexao con) {
        PreparedStatement ps;
        Endereco retorno = new Endereco();

        try {
            // Criando o comonado sql
            ps = (PreparedStatement) con.getPreparedStatement("SELECT  ID, LOGRADOURO, "
                    + "BAIRRO, NUMERO, COMPLEMENTO, CEP, PAR, ACADEMIA_ID, ALUNO_ID, CIDADE_ID "
                    + "FROM ENDERECO WHERE ALUNO_ID = ? AND ACADEMIA_ID = ?");

            ps.setInt(1, id_aluno);
            ps.setInt(2, id_solicitante);

            //Executando o camando no banco
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                populaEndereco(retorno, result);

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
    public List<Endereco> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con) {
        throw new UnsupportedOperationException("Metodo não implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    private void populaEndereco(Endereco retorno, ResultSet result) throws SQLException {
        retorno.setId(result.getInt("ID"));
        retorno.setLogradouro(result.getString("LOGRADOURO"));
        retorno.setNumero(result.getInt("NUMERO"));
        retorno.setComplemento(result.getString("COMPLEMENTO"));
        retorno.setBairro(result.getString("BAIRRO"));
        retorno.setCep(result.getString("CEP"));
        retorno.setPar(result.getString("PAR"));
        Academia academia = new Academia(result.getInt("ACADEMIA_ID"));
        Aluno aluno = new Aluno(result.getInt("ALUNO_ID"));
        Cidade cidade = new Cidade(result.getInt("CIDADE_ID"));
        retorno.setAcademiaId(academia);
        retorno.setAlunoId(aluno);
        retorno.setCidadeId(cidade);

    }

}
