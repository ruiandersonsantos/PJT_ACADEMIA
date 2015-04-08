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

public class Exercicio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private String apelido;
    private Academia academiaId;
    private List<DetalheTreino> detalheTreinoList;

    public Exercicio() {
    }

    public Exercicio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Academia getAcademiaId() {
        return academiaId;
    }

    public void setAcademiaId(Academia academiaId) {
        this.academiaId = academiaId;
    }

    public List<DetalheTreino> getDetalheTreinoList() {
        return detalheTreinoList;
    }

    public void setDetalheTreinoList(List<DetalheTreino> detalheTreinoList) {
        this.detalheTreinoList = detalheTreinoList;
    }

}
