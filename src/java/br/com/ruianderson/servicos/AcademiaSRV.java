/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.dao.AcademiaDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import java.util.List;

/**
 *
 * @author Rui
 */
public final class AcademiaSRV {

    public final static int mergeAcademia(Conexao con,int operacao, int id, String razao, String cnpj, String contato, String email, String telefone) {
        // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 

        int retorno = 0;

        Academia academia = new Academia();
        academia.setRazao(razao);
        academia.setCnpj(cnpj);
        academia.setContato(contato);
        academia.setEmail(email);
        academia.setTelefone(telefone);

        AcademiaDAO daoAcademia = new AcademiaDAO();

        switch (operacao) {
            case 1:
                retorno = daoAcademia.adicionar(academia,con);
                break;
            case 2:
                academia.setId(id);
                retorno = daoAcademia.atualizar(academia,con);
                break;
            case 3:
                academia.setId(id);
                retorno = daoAcademia.remover(academia,con);
        }

        return retorno;
    }

    public final static List<Academia> listarAcademias(Conexao con) {

        AcademiaDAO daoAcademia = new AcademiaDAO();
        return daoAcademia.listarTodos(0,con);

    }

    public final static Academia localizarAcademiaPorId(Conexao con,int id) {

        AcademiaDAO daoAcademia = new AcademiaDAO();
        return daoAcademia.buscarPorId(id, 0,con);

    }

    public final static List<Academia> localizarAcademiaPorRazao(Conexao con,String razao, int pesquisa) {

        AcademiaDAO daoAcademia = new AcademiaDAO();
        return daoAcademia.buscarPorNome(razao, pesquisa, 0,con);

    }

}
