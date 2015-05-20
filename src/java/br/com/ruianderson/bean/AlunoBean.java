/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.bean;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.arrays.OpcaoSEXO;
import br.com.ruianderson.arrays.OpcaoSTATUS;
import br.com.ruianderson.arrays.OpcaoTRANSACAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Matricula;
import br.com.ruianderson.model.Usuario;
import br.com.ruianderson.servicos.AlunoSRV;
import br.com.ruianderson.servicos.MatriculaSRV;
import br.com.ruianderson.servicos.TransationSRV;
import br.com.ruianderson.utilitarios.FacesMessages;
import br.com.ruianderson.utilitarios.Obj_gen;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

/**
 *
 * @author Rui
 */
@ManagedBean(name = "AlunoBean")
@SessionScoped
public class AlunoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Aluno aluno;
    private Matricula matricula;
    private List<Aluno> todosAlunos;
    private Aluno alunoSelecionado;
    private Usuario usuario;

    private FacesMessages messages;

    public AlunoBean() {
        alunoSelecionado = null;
        messages = new FacesMessages();
        usuario = new Usuario();
    }

    public void preparaNovoCadastro() {
        aluno = new Aluno();
        aluno.setAcademia(usuario.getAcademiaId());
        matricula = new Matricula();
        alunoSelecionado = null;
    }

    public void consutarAlunos() throws ClassNotFoundException, SQLException {

        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");

        Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);
        todosAlunos = AlunoSRV.listarAlunos(conect, usuario.getAcademiaId().getId());
    }

    public void excluir() throws ClassNotFoundException, SQLException {
      
        int id_aluno = 0;
        int id_matricula = 0;

        Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.COM_TRANSACAO);

        Matricula matricula_exc = new Matricula(alunoSelecionado.getId());
        Aluno aluno_exc = new Aluno(alunoSelecionado.getId());

        matricula_exc.setAcademia(new Academia(1));
        aluno_exc.setAcademia(new Academia(1));

        id_matricula = MatriculaSRV.mergeMatricula(conect, OpcaoDAO.REMOVER, matricula_exc);

        if (id_matricula > 0) {

            id_aluno = AlunoSRV.mergeAluno(conect, OpcaoDAO.REMOVER, aluno_exc);

            if (id_aluno > 0) {

                TransationSRV.commit(conect);
                messages.info("Aluno excluido com sucesso!");
                consutarAlunos();
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg", "frm-aluno:aluno-table"));
                alunoSelecionado = null;

            } else {
                TransationSRV.rollback(conect);
                messages.error("Aluno não foi excluido!");
                consutarAlunos();
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg", "frm-aluno:aluno-table"));

            }

        } else {
            TransationSRV.rollback(conect);
            messages.error("Aluno não foi excluido!");
            consutarAlunos();
            RequestContext.getCurrentInstance().update(
                    Arrays.asList("frm-aluno:msg", "frm-aluno:aluno-table"));

        }

    }

    public void salvar() throws ClassNotFoundException, SQLException, ParseException {

        if (alunoSelecionado != null) {

            int id_aluno = 0;

            Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);

            id_aluno = AlunoSRV.mergeAluno(conect, OpcaoDAO.ATUALIZAR, aluno);

            if (id_aluno > 0) {

                messages.info("Aluno atualizado com sucesso!");
                consutarAlunos();
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg", "frm-aluno:aluno-table"));

            } else {

                messages.error("Aluno não foi atualizado!");
                consutarAlunos();
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg", "frm-aluno:aluno-table"));
            }

            conect.closeAll();

        } else {

            int id_aluno = 0;

            Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.COM_TRANSACAO);

            id_aluno = AlunoSRV.mergeAluno(conect, OpcaoDAO.ADICIONAR, aluno);

            if (id_aluno > 0) {

                matricula.setId(id_aluno);
                matricula.setAcademia(usuario.getAcademiaId());
                matricula.setAluno(new Aluno(id_aluno));
                matricula.setDtMatricula(Obj_gen.getDateAtual());
                matricula.setStatus(OpcaoSTATUS.ATIVO.getValor());

                int id_matricula = 0;

                id_matricula = MatriculaSRV.mergeMatricula(conect, OpcaoDAO.ADICIONAR, matricula);

                if (id_matricula > 0) {
                    TransationSRV.commit(conect);
                    consutarAlunos();
                    messages.info("Aluno adicionado com sucesso!");

                    RequestContext.getCurrentInstance().update(
                            Arrays.asList("frm-aluno:msg", "frm-aluno:aluno-table"));

                } else {
                    messages.error("Aluno não foi adicionado");
                    TransationSRV.rollback(conect);
                    RequestContext.getCurrentInstance().update(
                            Arrays.asList("frm-aluno:msg"));
                }

            } else {

                messages.error("Aluno não foi adicionado");
                TransationSRV.rollback(conect);
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));

            }

        }

    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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

    public OpcaoSEXO[] getOpcaoSexo() {
        return OpcaoSEXO.values();
    }

    /**
     * @return the todoAlunos
     */
    public List<Aluno> getTodosAlunos() {
        return todosAlunos;
    }

    /**
     * @param todoAlunos the todoAlunos to set
     */
    public void setTodosAlunos(List<Aluno> todoAlunos) {
        this.todosAlunos = todoAlunos;
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
