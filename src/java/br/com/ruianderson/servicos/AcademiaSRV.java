/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.dao.AcademiaDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class AcademiaSRV {

    public final static int mergeAcademia(Conexao con, OpcaoDAO operacao, Academia academia) {
        // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 

        int retorno = 0;


        AcademiaDAO daoAcademia = new AcademiaDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoAcademia.adicionar(academia, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

           retorno = daoAcademia.atualizar(academia, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoAcademia.remover(academia, con);

        }

        return retorno;
    }

    public final static List<Academia> listarAcademias(Conexao con) {

        AcademiaDAO daoAcademia = new AcademiaDAO();
        return daoAcademia.listarTodos(0, con);

    }

    public final static Academia localizarAcademiaPorId(Conexao con, int id) {

        AcademiaDAO daoAcademia = new AcademiaDAO();
        return daoAcademia.buscarPorId(id, 0, con);

    }

    public final static List<Academia> localizarAcademiaPorRazao(Conexao con, String razao, int pesquisa) {

        AcademiaDAO daoAcademia = new AcademiaDAO();
        return daoAcademia.buscarPorNome(razao, pesquisa, 0, con);

    }

}
