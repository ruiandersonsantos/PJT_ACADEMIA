/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.dao.TreinoDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Treino;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class TreinoSRV {
    
    // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeTreino(Conexao con, OpcaoDAO operacao, Treino treino) {
        int retorno = 0;

       TreinoDAO daoTreino = new TreinoDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoTreino.adicionar(treino, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

            retorno = daoTreino.atualizar(treino, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoTreino.remover(treino, con);

        }

        return retorno;
    }

    public final static List<Treino> listarTreinos(Conexao con, Academia academia) {

        TreinoDAO daoTreino = new TreinoDAO();
        return daoTreino.listarTodos(academia.getId(), con);

    }

    public final static Treino localizarTreinoPorId(Conexao con, int id, Academia academia) {

        TreinoDAO daoTreino = new TreinoDAO();
        return daoTreino.buscarPorId(id, academia.getId(), con);

    }

    public final static List<Treino> localizarTreinoPorNome(Conexao con, String nome, int pesquisa, Academia academia) {

        TreinoDAO daoTreino = new TreinoDAO();
        return daoTreino.buscarPorNome(nome, pesquisa, academia.getId(), con);

    }
    
}
