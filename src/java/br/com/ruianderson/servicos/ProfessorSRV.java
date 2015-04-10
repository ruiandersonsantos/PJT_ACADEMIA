/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.dao.ProfessorDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Professor;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class ProfessorSRV {

    // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeProfessor(Conexao con, OpcaoDAO operacao, Professor professor) {
        int retorno = 0;

       ProfessorDAO daoProfessor = new ProfessorDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoProfessor.adicionar(professor, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

            retorno = daoProfessor.atualizar(professor, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoProfessor.remover(professor, con);

        }

        return retorno;
    }

    public final static List<Professor> listarProfessores(Conexao con, Academia academia) {

        ProfessorDAO daoProfessor = new ProfessorDAO();
        return daoProfessor.listarTodos(academia.getId(), con);

    }

    public final static Professor localizarProfessorPorId(Conexao con, int id, Academia academia) {

        ProfessorDAO daoProfessor = new ProfessorDAO();
        return daoProfessor.buscarPorId(id, academia.getId(), con);

    }

    public final static List<Professor> localizarProfessorPorNome(Conexao con, String nome, int pesquisa, Academia academia) {

        ProfessorDAO daoProfessor = new ProfessorDAO();
        return daoProfessor.buscarPorNome(nome, pesquisa, academia.getId(), con);

    }
}
