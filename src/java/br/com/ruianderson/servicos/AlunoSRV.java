/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.arrays.OpcaoSEXO;
import br.com.ruianderson.dao.AlunoDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.utilitarios.Obj_gen;

/**
 *
 * @author Rui
 */
public final class AlunoSRV {

    // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeAluno(Conexao con, OpcaoDAO operacao, Aluno aluno) {
        
        int retorno = 0;

        AlunoDAO daoAluno = new AlunoDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoAluno.adicionar(aluno, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

          retorno = daoAluno.atualizar(aluno, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoAluno.remover(aluno, con);

        }

        return retorno;
    }

    public final static Aluno localizarAlunoPorId(Conexao con, int id) {
        AlunoDAO daoAluno = new AlunoDAO();
        return daoAluno.buscarPorId(id, 0, con);
    }
}
