/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

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
    public final static int mergeAcademia(Conexao con,int operacao,int id, String nome, String celular, String telefone, String sexo, String dt_nasc, String email) {
       int retorno = 0;
       
       Aluno aluno = new Aluno();
       aluno.setNome(nome);
       aluno.setCelular(celular);
       aluno.setTelefone(telefone);
       aluno.setSexo(sexo);
       aluno.setDtNascimento(Obj_gen.convertStringToSqlDate(dt_nasc));
       aluno.setEmail(email);
       
       
       AlunoDAO daoAluno = new AlunoDAO();
       
       
       switch (operacao) {
            case 1:
                retorno = daoAluno.adicionar(aluno,con);
                break;
            case 2:
                aluno.setId(id);
                retorno = daoAluno.atualizar(aluno,con);
                break;
            case 3:
                aluno.setId(id);
                retorno = daoAluno.remover(aluno,con);
        }
       
       
       return retorno;
    }
    
    public final static Aluno localizarAlunoPorId(Conexao con,int id){
        AlunoDAO daoAluno = new AlunoDAO();
        return daoAluno.buscarPorId(id, 0,con);
    }
}
