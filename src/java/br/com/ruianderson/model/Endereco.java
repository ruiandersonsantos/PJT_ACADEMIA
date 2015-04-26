/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.model;

import java.io.Serializable;

/**
 *
 * @author Rui
 */

public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer id;
    private String logradouro;
    private String bairro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String par;
    private Academia academiaId;
    private Aluno alunoId;
    private Cidade cidadeId;
    private Integer id_cidade;

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public Academia getAcademiaId() {
        return academiaId;
    }

    public void setAcademiaId(Academia academiaId) {
        this.academiaId = academiaId;
    }

    public Aluno getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Aluno alunoId) {
        this.alunoId = alunoId;
    }

    public Cidade getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Cidade cidadeId) {
        this.cidadeId = cidadeId;
    }

    /**
     * @return the id_cidade
     */
    public Integer getId_cidade() {
        return id_cidade;
    }

    /**
     * @param id_cidade the id_cidade to set
     */
    public void setId_cidade(Integer id_cidade) {
        this.id_cidade = id_cidade;
    }

    
    
}
