/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.dao.ExercicioDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Exercicio;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class ExercicioSRV {

    // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeExercicio(Conexao con, OpcaoDAO operacao, int id, String descricao, String apelido, Academia academia) {
        int retorno = 0;

        Exercicio exercicio = new Exercicio();

        exercicio.setApelido(apelido);
        exercicio.setDescricao(descricao);
        exercicio.setAcademiaId(academia);

        ExercicioDAO daoExercicio = new ExercicioDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoExercicio.adicionar(exercicio, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

            exercicio.setId(id);
            retorno = daoExercicio.atualizar(exercicio, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            exercicio.setId(id);
            retorno = daoExercicio.remover(exercicio, con);

        }

        return retorno;
    }

    public final static List<Exercicio> listarExercicios(Conexao con, Academia academia) {

        ExercicioDAO daoExercicio = new ExercicioDAO();
        return daoExercicio.listarTodos(academia.getId(), con);

    }

    public final static Exercicio localizarExercicioPorId(Conexao con, int id, Academia academia) {

        ExercicioDAO daoExercicio = new ExercicioDAO();
        return daoExercicio.buscarPorId(id, academia.getId(), con);

    }

    public final static List<Exercicio> localizarExercicioPorApelido(Conexao con, String apelido, int pesquisa, Academia academia) {

        ExercicioDAO daoExercicio = new ExercicioDAO();
        return daoExercicio.buscarPorNome(apelido, pesquisa, academia.getId(), con);

    }

}
