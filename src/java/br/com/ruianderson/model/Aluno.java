/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author Rui
 */

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Integer id;
    @NotEmpty(message = "Nome não pode ser vazio!")
    private String nome;
    @NotEmpty(message = "Celular não pode ser vazio!")
    private String celular;
    @NotEmpty(message = "Telefone não pode ser vazio!")
    private String telefone;
    @NotEmpty(message = "Sexo deve ser selecionado!")
    private String sexo;
    @NotNull(message = "A data de nascimento deve ser informada!")
    private Date dtNascimento;
    @Email(message = "Informe um e-mail valido!")
    @NotEmpty(message = "O E-mail deve ser informado!")
    private String email;
    private List<Endereco> enderecoList;
    private List<Matricula> matriculaList;
    private List<Usuario> usuarioList;
    private Academia academia;
   

    public Aluno() {
    }

    public Aluno(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
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

    /**
     * @return the academia
     */
    public Academia getAcademia() {
        return academia;
    }

    /**
     * @param academia the academia to set
     */
    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

   
    
}
