/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.bean;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.arrays.OpcaoSTATUS;
import br.com.ruianderson.arrays.OpcaoTRANSACAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Matricula;
import br.com.ruianderson.servicos.MatriculaSRV;
import br.com.ruianderson.servicos.TransationSRV;
import br.com.ruianderson.utilitarios.FacesMessages;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Rui
 */
@ManagedBean(name = "MatriculaBean")
@SessionScoped
public class MatriculaBean {
    
    private static final long serialVersionUID = 1L;
    
    private Matricula matricula;
    private Conexao conect;
    private FacesMessages messages;
    private Aluno alunoSelecionado;
    
    public MatriculaBean(){
        
        messages = new FacesMessages();
        matricula = new Matricula();
        matricula.setStatus(1);
    }
    
    public void prepararMatricula() throws ClassNotFoundException, SQLException{
        
        conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);
        
        matricula =  MatriculaSRV.localizarMatriculaPorId(conect, alunoSelecionado.getId(), new Academia(1));
        
        conect.closeAll();
        
    }
    
    public void atualizarMatricula() throws ClassNotFoundException, SQLException{
        
        conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);
        
        int atualizado = 0;
        
        atualizado = MatriculaSRV.mergeMatricula(conect, OpcaoDAO.ATUALIZAR, matricula);
        
        if(atualizado > 0){
            
             messages.info("Matricula atualizada com sucesso!");

                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));
            
        }else{
            
             messages.error("Matricula não foi atualizada!");

                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));
            
        }
        
        conect.closeAll();
        
    }

    /**
     * @return the matricula
     */
    public Matricula getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    
     public OpcaoSTATUS [] getOpcaStatus() {
        return OpcaoSTATUS.values();
    }

    /**
     * @return the alunoSelecionado
     */
    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    /**
     * @param alunoSelecionado the alunoSelecionado to set
     */
    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }
    
     
    
}
