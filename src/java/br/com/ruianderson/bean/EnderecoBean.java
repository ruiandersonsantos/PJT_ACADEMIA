/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.bean;

import br.com.ruianderson.arrays.OpcaoCIDADE;
import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.arrays.OpcaoESTADO;
import br.com.ruianderson.arrays.OpcaoTRANSACAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Cidade;
import br.com.ruianderson.model.Endereco;
import br.com.ruianderson.servicos.EnderecoSRV;
import br.com.ruianderson.servicos.TransationSRV;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import br.com.ruianderson.utilitarios.FacesMessages;

/**
 *
 * @author Rui
 */
@ManagedBean(name = "EnderecoBean")
@SessionScoped
public class EnderecoBean {

    private static final long serialVersionUID = 1L;

    private Endereco enderecoEdicao;
    private Aluno alunoSelecionado;
    private Endereco endrecoBanco;
    private Conexao conect;

    private FacesMessages messages;

    public EnderecoBean() {

        alunoSelecionado = new Aluno();
        messages = new FacesMessages();
        endrecoBanco = new Endereco();
        enderecoEdicao = new Endereco();
        enderecoEdicao.setId(0);

    }

    public void prepararEnderecoAluno() throws ClassNotFoundException, SQLException {

        conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);

        endrecoBanco = EnderecoSRV.localizarEnderecoPorIdDoAluno(conect, alunoSelecionado.getId(), 1);

        if (endrecoBanco.getId() > 0) {

            enderecoEdicao = endrecoBanco;
            enderecoEdicao.setId_cidade(endrecoBanco.getCidadeId().getId());
        } else {

            enderecoEdicao = new Endereco();

        }

        conect.closeAll();

    }

    public void salvarEnderecoAluno() throws ClassNotFoundException, SQLException {

        conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);

        enderecoEdicao.setAlunoId(getAlunoSelecionado());
        enderecoEdicao.setPar("aluno");

        int gravado = 0;

        if (endrecoBanco.getId() == 0) {

            Cidade cidade = new Cidade(enderecoEdicao.getId_cidade());
            enderecoEdicao.setCidadeId(cidade);
            enderecoEdicao.setAlunoId(alunoSelecionado);
            enderecoEdicao.setAcademiaId(new Academia(1));

            gravado = EnderecoSRV.mergeEndereco(conect, OpcaoDAO.ADICIONAR, enderecoEdicao);

            if (gravado > 0) {

                messages.info("Endereço adicionado com sucesso!");

                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));

            } else {

                messages.error("Endereço não foi adicionado!");

                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));

            }

        } else {

            enderecoEdicao.getCidadeId().setId(enderecoEdicao.getId_cidade());

            gravado = EnderecoSRV.mergeEndereco(conect, OpcaoDAO.ATUALIZAR, enderecoEdicao);

            if (gravado > 0) {

                messages.info("Endereço atualizado com sucesso!");

                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));

            } else {

                messages.error("Endereço não foi atualizado!");

                RequestContext.getCurrentInstance().update(
                        Arrays.asList("frm-aluno:msg"));

            }

        }

        enderecoEdicao = null;

        conect.closeAll();

    }

    /**
     * @return the enderecoEdicao
     */
    public Endereco getEnderecoEdicao() {
        return enderecoEdicao;
    }

    /**
     * @param enderecoEdicao the enderecoEdicao to set
     */
    public void setEnderecoEdicao(Endereco enderecoEdicao) {
        this.enderecoEdicao = enderecoEdicao;
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

    public OpcaoESTADO[] getOpcaoEstado() {
        return OpcaoESTADO.values();
    }

    public OpcaoCIDADE[] getOpcaoCidade() {
        return OpcaoCIDADE.values();
    }

}
