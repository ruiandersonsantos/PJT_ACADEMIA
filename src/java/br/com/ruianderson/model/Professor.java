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

public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer id;
    private String nome;
    private String email;
    private String celular;
    private List<HistoricoTreino> historicoTreinoList;
    private Academia academiaId;

    public Professor() {
    }

    public Professor(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<HistoricoTreino> getHistoricoTreinoList() {
        return historicoTreinoList;
    }

    public void setHistoricoTreinoList(List<HistoricoTreino> historicoTreinoList) {
        this.historicoTreinoList = historicoTreinoList;
    }

    public Academia getAcademiaId() {
        return academiaId;
    }

    public void setAcademiaId(Academia academiaId) {
        this.academiaId = academiaId;
    }
}
