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
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date dtAvaliacao;
    private Float peso;
    private Integer altura;
    private Float imc;
    private Integer idade;
    private Integer objetivo;
    private Integer bebe;
    private Integer fuma;
    private Float perimetroTorax;
    private Float perimetroCintura;
    private Float perimetroObdome;
    private Float perimetroQuadril;
    private Float perimetroAntebraco;
    private Float perimetroBraco;
    private Float perimetroCoxa;
    private Float perimetroPanturrilha;
    private Float dobraSubscapular;
    private Float dobraTriciptal;
    private Float dobraPeitoral;
    private Float dobraAxilamedia;
    private Float dobraSupraIliaca;
    private Float dobraAbdominal;
    private Float dobraCoxa;

    public Avaliacao() {
    }

    public Avaliacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtAvaliacao() {
        return dtAvaliacao;
    }

    public void setDtAvaliacao(Date dtAvaliacao) {
        this.dtAvaliacao = dtAvaliacao;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Float getImc() {
        return imc;
    }

    public void setImc(Float imc) {
        this.imc = imc;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getBebe() {
        return bebe;
    }

    public void setBebe(Integer bebe) {
        this.bebe = bebe;
    }

    public Integer getFuma() {
        return fuma;
    }

    public void setFuma(Integer fuma) {
        this.fuma = fuma;
    }

    public Float getPerimetroTorax() {
        return perimetroTorax;
    }

    public void setPerimetroTorax(Float perimetroTorax) {
        this.perimetroTorax = perimetroTorax;
    }

    public Float getPerimetroCintura() {
        return perimetroCintura;
    }

    public void setPerimetroCintura(Float perimetroCintura) {
        this.perimetroCintura = perimetroCintura;
    }

    public Float getPerimetroObdome() {
        return perimetroObdome;
    }

    public void setPerimetroObdome(Float perimetroObdome) {
        this.perimetroObdome = perimetroObdome;
    }

    public Float getPerimetroQuadril() {
        return perimetroQuadril;
    }

    public void setPerimetroQuadril(Float perimetroQuadril) {
        this.perimetroQuadril = perimetroQuadril;
    }

    public Float getPerimetroAntebraco() {
        return perimetroAntebraco;
    }

    public void setPerimetroAntebraco(Float perimetroAntebraco) {
        this.perimetroAntebraco = perimetroAntebraco;
    }

    public Float getPerimetroBraco() {
        return perimetroBraco;
    }

    public void setPerimetroBraco(Float perimetroBraco) {
        this.perimetroBraco = perimetroBraco;
    }

    public Float getPerimetroCoxa() {
        return perimetroCoxa;
    }

    public void setPerimetroCoxa(Float perimetroCoxa) {
        this.perimetroCoxa = perimetroCoxa;
    }

    public Float getPerimetroPanturrilha() {
        return perimetroPanturrilha;
    }

    public void setPerimetroPanturrilha(Float perimetroPanturrilha) {
        this.perimetroPanturrilha = perimetroPanturrilha;
    }

    public Float getDobraSubscapular() {
        return dobraSubscapular;
    }

    public void setDobraSubscapular(Float dobraSubscapular) {
        this.dobraSubscapular = dobraSubscapular;
    }

    public Float getDobraTriciptal() {
        return dobraTriciptal;
    }

    public void setDobraTriciptal(Float dobraTriciptal) {
        this.dobraTriciptal = dobraTriciptal;
    }

    public Float getDobraPeitoral() {
        return dobraPeitoral;
    }

    public void setDobraPeitoral(Float dobraPeitoral) {
        this.dobraPeitoral = dobraPeitoral;
    }

    public Float getDobraAxilamedia() {
        return dobraAxilamedia;
    }

    public void setDobraAxilamedia(Float dobraAxilamedia) {
        this.dobraAxilamedia = dobraAxilamedia;
    }

    public Float getDobraSupraIliaca() {
        return dobraSupraIliaca;
    }

    public void setDobraSupraIliaca(Float dobraSupraIliaca) {
        this.dobraSupraIliaca = dobraSupraIliaca;
    }

    public Float getDobraAbdominal() {
        return dobraAbdominal;
    }

    public void setDobraAbdominal(Float dobraAbdominal) {
        this.dobraAbdominal = dobraAbdominal;
    }

    public Float getDobraCoxa() {
        return dobraCoxa;
    }

    public void setDobraCoxa(Float dobraCoxa) {
        this.dobraCoxa = dobraCoxa;
    }

}
