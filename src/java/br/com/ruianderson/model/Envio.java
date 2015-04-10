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

public class Envio implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Integer id;
    private Date dtEnvio;
    private String descricao;
    private Integer status;
    private Usuario usuarioId;
    private Mensagem mensagemId;
    private Academia academia;

    public Envio() {
    }

    public Envio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtEnvio() {
        return dtEnvio;
    }

    public void setDtEnvio(Date dtEnvio) {
        this.dtEnvio = dtEnvio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Mensagem getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Mensagem mensagemId) {
        this.mensagemId = mensagemId;
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
