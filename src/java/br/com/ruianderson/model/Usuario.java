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

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer id;
    private String login;
    private String senha;
    private String par;
    private List<Envio> envioList;
    private Academia academiaId;
    private Integer status;
    private Integer acesso_id;
    private Integer idacademia;
    

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public List<Envio> getEnvioList() {
        return envioList;
    }

    public void setEnvioList(List<Envio> envioList) {
        this.envioList = envioList;
    }

    public Academia getAcademiaId() {
        return academiaId;
    }

    public void setAcademiaId(Academia academiaId) {
        this.academiaId = academiaId;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the acesso_id
     */
    public Integer getAcesso_id() {
        return acesso_id;
    }

    /**
     * @param acesso_id the acesso_id to set
     */
    public void setAcesso_id(Integer acesso_id) {
        this.acesso_id = acesso_id;
    }

    /**
     * @return the idacademia
     */
    public Integer getIdacademia() {
        return idacademia;
    }

    /**
     * @param idacademia the idacademia to set
     */
    public void setIdacademia(Integer idacademia) {
        this.idacademia = idacademia;
    }
    
    
    
   
}
