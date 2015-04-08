/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.model;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Rui
 */

public class Academia implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer id;
    private String razao;
    private String cnpj;
    private String contato;
    private String telefone;
    private String email;
    private List<Endereco> enderecoList;
    private List<Exercicio> exercicioList;
    private List<Professor> professorList;
    private List<Matricula> matriculaList;
    private List<Usuario> usuarioList;

    public Academia() {
    }

    public Academia(Integer id) {
        this.id = id;
    }

    public Academia(Integer id, String razao, String cnpj, String contato) {
        this.id = id;
        this.razao = razao;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public List<Exercicio> getExercicioList() {
        return exercicioList;
    }

    public void setExercicioList(List<Exercicio> exercicioList) {
        this.exercicioList = exercicioList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    
}
