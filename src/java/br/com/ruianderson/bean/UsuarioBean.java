/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.bean;

import br.com.ruianderson.arrays.OpcaoTRANSACAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Usuario;
import br.com.ruianderson.servicos.TransationSRV;
import br.com.ruianderson.servicos.UsuarioSRV;
import br.com.ruianderson.utilitarios.FacesMessages;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rui
 */
@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private Usuario usrLogado;
    private FacesMessages messages;

    public UsuarioBean() {
       // usrLogado = new Usuario();
        usuario = new Usuario();
        messages = new FacesMessages();

    }

    public void preparaNovoUsuario() {

    }

    public String logout() {

        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        usrLogado = null;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", null);
        
        return "/login?faces-redirect=true";
    }

    public String login() throws ClassNotFoundException, SQLException {

        Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);

        setUsrLogado(null);

        setUsrLogado(UsuarioSRV.logar(usuario.getLogin(), usuario.getSenha(), usuario.getIdacademia(), conect));

        if (getUsrLogado() != null) {

            if (UsuarioSRV.verificaStatus(getUsrLogado())) {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", getUsrLogado());

                messages.info("Login efetuado com sucesso!");

                return "restrito/index?faces-redirect=true";

            } else {

                messages.error("Usuario não está ativo! Contate o administrador do sistema");
            }

        } else {

            messages.error("Usuario e/ou senha não cadastros para essa academia");

        }

        conect.closeAll();

        return null;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usrLogado
     */
    public Usuario getUsrLogado() {
        return usrLogado;
    }

    /**
     * @param usrLogado the usrLogado to set
     */
    public void setUsrLogado(Usuario usrLogado) {
        this.usrLogado = usrLogado;
    }
    
    

}
