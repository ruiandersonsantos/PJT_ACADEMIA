/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.dao.EnderecoDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Cidade;
import br.com.ruianderson.model.Endereco;

/**
 *
 * @author Rui
 */
public final class EnderecoSRV {
    
     // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeEndereco(Conexao con,int operacao,int id, String logradouro, String bairro, 
            int numero, String complemento, String cep, String par, Academia academia, Aluno aluno, Cidade cidade) {
       int retorno = 0;
       
       Endereco endereco = new Endereco();
       endereco.setId(id);
       endereco.setLogradouro(logradouro);
       endereco.setBairro(bairro);
       endereco.setNumero(numero);
       endereco.setComplemento(complemento);
       endereco.setCep(cep);
       endereco.setPar(par);
       endereco.setAcademiaId(academia);
       endereco.setAlunoId(aluno);
       endereco.setCidadeId(cidade);
       
       EnderecoDAO daoEndereco = new EnderecoDAO();
       
       switch (operacao) {
            case 1:
                retorno = daoEndereco.adicionar(endereco,con);
                break;
            case 2:
                endereco.setId(id);
                retorno = daoEndereco.atualizar(endereco,con);
                break;
          
        }
       
       
       return retorno;
    }
    
     public final static Endereco localizarEnderecoPorIdDoAluno(Conexao con,int id, int id_solicitante){
        EnderecoDAO daoEndereco = new EnderecoDAO();
        return daoEndereco.buscarPorId(id, id_solicitante,con);
    }
    
}
