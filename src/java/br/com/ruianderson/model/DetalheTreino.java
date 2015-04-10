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

public class DetalheTreino implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer serie;
    private Integer repeticoes;
    private int diasSemanaId;
    private Float cargaInicial;
    private Exercicio exercicio;
    private Treino treino;
    private Academia academia;

    public DetalheTreino() {
    }

    public DetalheTreino(Integer id) {
        this.id = id;
    }

    public DetalheTreino(Integer id, int diasSemanaId) {
        this.id = id;
        this.diasSemanaId = diasSemanaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getDiasSemanaId() {
        return diasSemanaId;
    }

    public void setDiasSemanaId(int diasSemanaId) {
        this.diasSemanaId = diasSemanaId;
    }

    public Float getCargaInicial() {
        return cargaInicial;
    }

    public void setCargaInicial(Float cargaInicial) {
        this.cargaInicial = cargaInicial;
    }

    /**
     * @return the exercicio
     */
    public Exercicio getExercicio() {
        return exercicio;
    }

    /**
     * @param exercicio the exercicio to set
     */
    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    /**
     * @return the treino
     */
    public Treino getTreino() {
        return treino;
    }

    /**
     * @param treino the treino to set
     */
    public void setTreino(Treino treino) {
        this.treino = treino;
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
