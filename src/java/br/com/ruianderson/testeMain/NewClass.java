/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.testeMain;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.arrays.OpcaoDIA;
import br.com.ruianderson.arrays.OpcaoSEXO;
import br.com.ruianderson.arrays.OpcaoSTATUS;
import br.com.ruianderson.arrays.OpcaoTRANSACAO;
import br.com.ruianderson.dao.AcademiaDAO;
import br.com.ruianderson.dao.AlunoDAO;
import br.com.ruianderson.dao.ExercicioDAO;
import br.com.ruianderson.dao.ProfessorDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Academia;
import br.com.ruianderson.model.Aluno;
import br.com.ruianderson.model.Cidade;
import br.com.ruianderson.model.DetalheTreino;
import br.com.ruianderson.model.Endereco;
import br.com.ruianderson.model.Exercicio;
import br.com.ruianderson.model.Matricula;
import br.com.ruianderson.model.Professor;
import br.com.ruianderson.model.Treino;
import br.com.ruianderson.servicos.AcademiaSRV;
import br.com.ruianderson.servicos.AlunoSRV;
import br.com.ruianderson.servicos.DetalheTreinoSRV;
import br.com.ruianderson.servicos.EnderecoSRV;
import br.com.ruianderson.servicos.ExercicioSRV;
import br.com.ruianderson.servicos.MatriculaSRV;
import br.com.ruianderson.servicos.ProfessorSRV;
import br.com.ruianderson.servicos.TransationSRV;
import br.com.ruianderson.servicos.TreinoSRV;
import br.com.ruianderson.utilitarios.Obj_gen;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rui
 */
public class NewClass {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        inserteBasico_1();
        
    }

    private static void inserteBasico_1() throws SQLException, NumberFormatException, ClassNotFoundException {
        
        int acadmia_id = 0;
        
        acadmia_id = insereAcademia();
        
        insereProfessor(acadmia_id);
        
        insereAluno(acadmia_id);
        
        insereTreino(acadmia_id);
    }

    private static void insereTreino(int acadmia_id) throws ClassNotFoundException, SQLException, NumberFormatException {
        Exercicio exercico = new Exercicio();
        exercico.setApelido("Mesa Flexora");
        exercico.setAcademiaId(new Academia(acadmia_id));
        exercico.setDescricao("Mesa Flexora");
        
        int id_exercicio = 0;
        
        Conexao con = TransationSRV.begin(OpcaoTRANSACAO.COM_TRANSACAO);
        
        id_exercicio = ExercicioSRV.mergeExercicio(con, OpcaoDAO.ADICIONAR, exercico);
        
        if (id_exercicio > 0) {
            System.out.println("Exercicio gravado com sucesso");
            
            Treino treino = new Treino();
            treino.setAcademia(new Academia(acadmia_id));
            treino.setDescricao("Treino ganho de massa");
            treino.setObjetivo("Aumento de massa muscular");
            treino.setObservacao("Treino para ser realizado no inicio");
            
            int id_treino = 0;
            
            id_treino = TreinoSRV.mergeTreino(con, OpcaoDAO.ADICIONAR, treino);
            
            if (id_treino > 0) {
                
                System.out.println("Treino gravado com sucesso");
                DetalheTreino detalhe = new DetalheTreino();
                detalhe.setAcademia(new Academia(acadmia_id));
                detalhe.setCargaInicial(Float.parseFloat("56.8"));
                detalhe.setDiasSemanaId(OpcaoDIA.QUARTA.getValor());
                detalhe.setExercicio(new Exercicio(id_exercicio));
                detalhe.setRepeticoes(10);
                detalhe.setSerie(3);
                detalhe.setTreino(new Treino(id_treino));
                
                int id_detalhe = 0;
                
                id_detalhe = DetalheTreinoSRV.mergeDetalheTreino(con, OpcaoDAO.ADICIONAR, detalhe);
                
                if (id_detalhe > 0) {
                    System.out.println("Detalhe do Treino gravado com sucesso");
                    TransationSRV.commit(con);
                } else {
                    
                    System.out.println("Problemas na gravação do detalhe treino");
                    TransationSRV.rollback(con);
                }
                
            } else {
                
                System.out.println("Problemas na gravação do treino");
                TransationSRV.rollback(con);
            }
            
        } else {
            System.out.println("Problemas na gravação do exercicio");
            TransationSRV.rollback(con);
        }
    }
    
    private static void insereAluno(int acadmia_id) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.setCelular("88776655");
        aluno.setDtNascimento(Obj_gen.convertStringToSqlDate("08/11/1975"));
        aluno.setEmail("rui@teste.com");
        aluno.setNome("Rui Anderson");
        aluno.setSexo(OpcaoSEXO.MASCULINO.getValor());
        aluno.setTelefone("66778899");
        aluno.setAcademia(new Academia(acadmia_id));
        
        int id_aluno = 0;
        
        Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.COM_TRANSACAO);
        
        id_aluno = AlunoSRV.mergeAluno(conect, OpcaoDAO.ADICIONAR, aluno);
        
        if (id_aluno > 0) {
            
            System.out.println("Aluno adicionado com sucesso.");
            
            Endereco endereco = new Endereco();
            endereco.setAcademiaId(new Academia(acadmia_id));
            endereco.setAlunoId(new Aluno(id_aluno));
            endereco.setBairro("Cascadura");
            endereco.setCep("25561162");
            endereco.setCidadeId(new Cidade(1));
            endereco.setComplemento("Lote 6");
            endereco.setLogradouro("Av Comendador Teles");
            endereco.setNumero(1758);
            endereco.setPar("aluno");
            
            int id_endereco = 0;
            
            id_endereco = EnderecoSRV.mergeEndereco(conect, OpcaoDAO.ADICIONAR, endereco);
            
            if (id_endereco > 0) {
                
                System.out.println("Endereço adicionado com sucesso.");
                
                Matricula matricula = new Matricula();
                matricula.setId(id_aluno);
                matricula.setAcademia(new Academia(acadmia_id));
                matricula.setAluno(new Aluno(id_aluno));
                matricula.setDtMatricula(Obj_gen.convertStringToSqlDate("09/04/2015"));
                matricula.setObservacao("Teste de inserte geral");
                matricula.setStatus(OpcaoSTATUS.ATIVO.getValor());
                
                int id_matricula = 0;
                id_matricula = MatriculaSRV.mergeExercicio(conect, OpcaoDAO.ADICIONAR, matricula);
                
                if (id_matricula > 0) {
                    System.out.println("Matricula adicionada com sucesso.");
                    TransationSRV.commit(conect);
                } else {
                    System.out.println("Problema Adicionando matricula.");
                    TransationSRV.rollback(conect);
                }
                
            } else {
                System.out.println("Problema Adicionando endereco.");
                TransationSRV.rollback(conect);
            }
            
        } else {
            System.out.println("Problema Adicionando aluno.");
            TransationSRV.rollback(conect);
        }
    }
    
    private static void insereProfessor(int acadmia_id) throws ClassNotFoundException, SQLException {
        Professor professor = new Professor();
        professor.setAcademiaId(new Academia(acadmia_id));
        professor.setCelular("99998888");
        professor.setEmail("Email@professor");
        professor.setNome("Professor Girafales");
        
        Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);
        
        int id = 0;
        
        id = ProfessorSRV.mergeProfessor(conect, OpcaoDAO.ADICIONAR, professor);
        
        if (id == 0) {
            System.out.println("Problemas adicionando professor");
        } else {
            
            System.out.println("Proffesor adicionado com sucesso");
        }
        
    }
    
    private static int insereAcademia() throws ClassNotFoundException, SQLException {
        Academia academia = new Academia();
        academia.setCnpj("00009999");
        academia.setTelefone("27520296");
        academia.setContato("Rui Anderson Paim Santos");
        academia.setEmail("rui@teste.com");
        academia.setRazao("Academia Teste");
        
        Conexao conect = TransationSRV.begin(OpcaoTRANSACAO.SEM_TRANSACAO);
        
        int id = 0;
        id = AcademiaSRV.mergeAcademia(conect, OpcaoDAO.ADICIONAR, academia);
        
        if (id == 0) {
            System.out.println("Problemas adicionando academia");
        } else {
            
            System.out.println("Academia adicionada com sucesso");
        }
        
        return id;
    }
    
}
