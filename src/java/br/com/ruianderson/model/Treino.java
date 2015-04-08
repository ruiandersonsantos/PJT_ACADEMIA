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

public class Treino implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer id;
    private String descricao;
    private String observacao;
    private String objetivo;
    private List<HistoricoTreino> historicoTreinoList;
    private List<DetalheTreino> detalheTreinoList;

    public Treino() {
    }

    public Treino(Integer id) {
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public List<HistoricoTreino> getHistoricoTreinoList() {
        return historicoTreinoList;
    }

    public void setHistoricoTreinoList(List<HistoricoTreino> historicoTreinoList) {
        this.historicoTreinoList = historicoTreinoList;
    }

    public List<DetalheTreino> getDetalheTreinoList() {
        return detalheTreinoList;
    }

    public void setDetalheTreinoList(List<DetalheTreino> detalheTreinoList) {
        this.detalheTreinoList = detalheTreinoList;
    }

    
}
