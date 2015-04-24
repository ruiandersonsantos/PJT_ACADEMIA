/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.bean;

import br.com.ruianderson.arrays.OpcaoSEXO;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Matricula;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rui
 */
@ManagedBean(name = "AlunoBean")
@SessionScoped
public class AlunoBean {
    
    private Aluno aluno;
    private Matricula matricula;
    
    public void preparaNovoCadastro(){
        aluno = new Aluno();
        matricula = new Matricula();
    }
    
    public void salvar(){
        
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the matricula
     */
    public Matricula getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    public OpcaoSEXO[] getOpcaoSexo() {
		return OpcaoSEXO.values();
	}
    
    
}
