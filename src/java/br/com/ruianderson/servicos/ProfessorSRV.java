/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

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
    public final static int mergeProfessor(Conexao con,int operacao, int id, String nome, String email, String celular, Academia academia) {
        int retorno = 0;

        Professor professor = new Professor();

        professor.setNome(nome);
        professor.setCelular(celular);
        professor.setEmail(email);
        professor.setAcademiaId(academia);

        ProfessorDAO daoProfessor = new ProfessorDAO();

        switch (operacao) {
            case 1:
                retorno = daoProfessor.adicionar(professor,con);
                break;
            case 2:
                professor.setId(id);
                retorno = daoProfessor.atualizar(professor,con);
                break;
            case 3:
                professor.setId(id);
                retorno = daoProfessor.remover(professor,con);
        }

        return retorno;
    }

    public final static List<Professor> listarProfessores(Conexao con,Academia academia) {

        ProfessorDAO daoProfessor = new ProfessorDAO();
        return daoProfessor.listarTodos(academia.getId(),con);

    }

    public final static Professor localizarProfessorPorId(Conexao con,int id, Academia academia) {

        ProfessorDAO daoProfessor = new ProfessorDAO();
        return daoProfessor.buscarPorId(id, academia.getId(),con);

    }

    public final static List<Professor> localizarProfessorPorNome(Conexao con,String nome, int pesquisa, Academia academia) {

        ProfessorDAO daoProfessor = new ProfessorDAO();
        return daoProfessor.buscarPorNome(nome, pesquisa, academia.getId(),con);

    }
}
