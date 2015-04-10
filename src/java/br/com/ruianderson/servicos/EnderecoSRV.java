/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
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
    public final static int mergeEndereco(Conexao con, OpcaoDAO operacao, Endereco endereco) {
        int retorno = 0;

        EnderecoDAO daoEndereco = new EnderecoDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoEndereco.adicionar(endereco, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {
           
            retorno = daoEndereco.atualizar(endereco, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoEndereco.remover(endereco, con);

        }

        return retorno;
    }

    public final static Endereco localizarEnderecoPorIdDoAluno(Conexao con, int id, int id_solicitante) {
        EnderecoDAO daoEndereco = new EnderecoDAO();
        return daoEndereco.buscarPorId(id, id_solicitante, con);
    }

}
