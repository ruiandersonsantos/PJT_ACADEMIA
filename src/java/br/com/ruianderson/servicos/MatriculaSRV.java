/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
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
    public final static int mergeExercicio(Conexao con, OpcaoDAO operacao, Matricula matricula) {
        int retorno = 0;

        MatriculaDAO daoMatricula = new MatriculaDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoMatricula.adicionar(matricula, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

           retorno = daoMatricula.atualizar(matricula, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

          retorno = daoMatricula.remover(matricula, con);

        }
     

        return retorno;
    }

    public final static List<Matricula> listarMatricula(Conexao con, int id) {

        MatriculaDAO daoMatricula = new MatriculaDAO();
        return daoMatricula.listarTodos(id, con);

    }

    public final static Matricula localizarMatriculaPorId(Conexao con, int id, Academia academia) {

        MatriculaDAO daoMatricula = new MatriculaDAO();
        return daoMatricula.buscarPorId(id, academia.getId(), con);

    }

}
