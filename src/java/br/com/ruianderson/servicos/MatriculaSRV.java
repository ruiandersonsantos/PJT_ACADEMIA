/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.dao.MatriculaDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Matricula;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class MatriculaSRV {

    // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeExercicio(Conexao con,int operacao, int id, Date dataAticacao, Date dt_inativacao,int status, String obs, Academia academia, Aluno aluno) {
        int retorno = 0;

        Matricula matricula = new Matricula();

        matricula.setId(aluno.getId());
        matricula.setDtMatricula(dataAticacao);
        matricula.setObservacao(obs);
        matricula.setStatus(status);
        matricula.setDtInativacao(dt_inativacao);
        matricula.setAcademia(academia);
        matricula.setAluno(aluno);

        MatriculaDAO daoMatricula = new MatriculaDAO();

        switch (operacao) {
            case 1:
                retorno = daoMatricula.adicionar(matricula,con);
                break;
            case 2:
                matricula.setId(id);
                retorno = daoMatricula.atualizar(matricula,con);
                break;
            case 3:
                matricula.setId(id);
                retorno = daoMatricula.remover(matricula,con);
        }

        return retorno;
    }
    
    public final static List<Matricula> listarMatricula(Conexao con,int id){
        
        MatriculaDAO daoMatricula = new MatriculaDAO();
        return daoMatricula.listarTodos(id,con);
        
    }
    
    public final static Matricula localizarMatriculaPorId(Conexao con,int id, Academia academia) {

        MatriculaDAO daoMatricula = new MatriculaDAO();
        return daoMatricula.buscarPorId(id, academia.getId(),con);

    }

}
