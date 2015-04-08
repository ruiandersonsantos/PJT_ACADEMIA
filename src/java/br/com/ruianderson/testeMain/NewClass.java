/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.testeMain;

import br.com.ruianderson.dao.AcademiaDAO;
import br.com.ruianderson.dao.AlunoDAO;
import br.com.ruianderson.dao.ExercicioDAO;
import br.com.ruianderson.dao.ProfessorDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Cidade;
import br.com.ruianderson.model.Exercicio;
import br.com.ruianderson.model.Professor;
import br.com.ruianderson.servicos.AcademiaSRV;
import br.com.ruianderson.servicos.AlunoSRV;
import br.com.ruianderson.servicos.EnderecoSRV;
import br.com.ruianderson.servicos.ExercicioSRV;
import br.com.ruianderson.servicos.MatriculaSRV;
import br.com.ruianderson.servicos.ProfessorSRV;
import br.com.ruianderson.servicos.TransationSRV;
import br.com.ruianderson.utilitarios.Obj_gen;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rui
 */
public class NewClass {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Inserindo, atualizando ou apagando uma academia
        // System.out.println(AcademiaSRV.mergeAcademia(1,999,"Academia nova","3333333","Rui Anderson","rui@teste","999999"));
        //Listando todas
        //System.out.println(AcademiaSRV.listarAcademias());
        //Buscando por id
        //System.out.println(AcademiaSRV.localizarAcademiaPorId(1).getContato());
        //Buscando por razaão 
        //System.out.println(AcademiaSRV.localizarAcademiaPorRazao("nova", 3).get(0).getRazao());
        //Inserindo, atualizando ou apagando um aluno
        //System.out.println(AlunoSRV.mergeAcademia(1, 1, "Rui Anderson", "219876", "2126513558", "M", "08/11/1975", "pedro@teste"));
        //System.out.println(MatriculaSRV.localizarMatriculaPorId(1, new Academia(1)).getAluno().getEmail());
        //int id_aluno = MatriculaSRV.localizarMatriculaPorId(, new Academia(1)).getId();
        //System.out.println(AlunoSRV.localizarAlunoPorId(id_aluno).getNome());
        // EnderecoSRV.mergeEndereco(2, 1, "Rua Silva Gomes", "Cascadura", 70, "Teste", "21350050", "aluno", new Academia(1), new Aluno(1), new Cidade(1));
        // System.out.println(EnderecoSRV.localizarEnderecoPorIdDoAluno(1, 1).getLogradouro());
        int ok = 0;

        Conexao conect = TransationSRV.begin();

        ok = AlunoSRV.mergeAcademia(conect, 1, 1, "Simone Moraes", "21987600777", "21265135", "M", "08/11/1975", "pedro@teste12");
        //ok = 0;
        if (ok > 0) {

            ok = EnderecoSRV.mergeEndereco(conect, 1, 1, "Rua Silva Gomes", "Cascadura", 70, "Teste", "21350050", "aluno", new Academia(1), new Aluno(ok), new Cidade(1));
            if (ok > 0) {
                TransationSRV.commit(conect);
            } else {
                TransationSRV.rollback(conect);
            }
        } else {

            TransationSRV.rollback(conect);
        }

    }

}
