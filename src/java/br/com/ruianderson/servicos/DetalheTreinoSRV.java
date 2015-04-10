/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.dao.DetalheTreinoDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.DetalheTreino;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class DetalheTreinoSRV {
    
     // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeDetalheTreino(Conexao con, OpcaoDAO operacao, DetalheTreino detalheTreino) {
        int retorno = 0;

       DetalheTreinoDAO daoDetalheTreino = new DetalheTreinoDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoDetalheTreino.adicionar(detalheTreino, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

            retorno = daoDetalheTreino.atualizar(detalheTreino, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoDetalheTreino.remover(detalheTreino, con);

        }

        return retorno;
    }

    public final static List<DetalheTreino> listarDetalheTreinos(Conexao con, Academia academia) {

        DetalheTreinoDAO daoDetalheTreino = new DetalheTreinoDAO();
        return daoDetalheTreino.listarTodos(academia.getId(), con);

    }

    public final static DetalheTreino localizarDetalheTreinoPorId(Conexao con, int id, Academia academia) {

        DetalheTreinoDAO daoDetalheTreino = new DetalheTreinoDAO();
        return daoDetalheTreino.buscarPorId(id, academia.getId(), con);

    }

    public final static List<DetalheTreino> localizarDetalheTreinoPorNome(Conexao con, String nome, int pesquisa, Academia academia) {

        DetalheTreinoDAO daoDetalheTreino = new DetalheTreinoDAO();
        return daoDetalheTreino.buscarPorNome(nome, pesquisa, academia.getId(), con);

    }
    
}
