/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.model;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Rui
 */
public class HistoricoTreino implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date dtAtivacao;
    private Date dtFinalizacao;
    private Integer status;
    private String observacao;
    private Matricula matriculaId;
    private Treino treinoId;
    private Avaliacao avaliacaoId;
    private Professor professorId;
    private Academia academia;

    public HistoricoTreino() {
    }

    public HistoricoTreino(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtAtivacao() {
        return dtAtivacao;
    }

    public void setDtAtivacao(Date dtAtivacao) {
        this.dtAtivacao = dtAtivacao;
    }

    public Date getDtFinalizacao() {
        return dtFinalizacao;
    }

    public void setDtFinalizacao(Date dtFinalizacao) {
        this.dtFinalizacao = dtFinalizacao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Matricula getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Matricula matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Treino getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(Treino treinoId) {
        this.treinoId = treinoId;
    }

    public Avaliacao getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Avaliacao avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public Professor getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Professor professorId) {
        this.professorId = professorId;
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
