/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.servicos;

import br.com.ruianderson.arrays.OpcaoDAO;
import br.com.ruianderson.dao.UsuarioDAO;
import br.com.ruianderson.dbutil.Conexao;
import br.com.ruianderson.model.Usuario;

/**
 *
 * @author Rui
 */
public class UsuarioSRV {
    
    // Operação 1 - Adiciona, 2 - Atualiza, 3 - remove 
    public final static int mergeAluno(Conexao con, OpcaoDAO operacao, Usuario usuario) {

        int retorno = 0;

        UsuarioDAO daoUsuario = new UsuarioDAO();

        if (operacao == OpcaoDAO.ADICIONAR) {

            retorno = daoUsuario.adicionar(usuario, con);

        } else if (operacao == OpcaoDAO.ATUALIZAR) {

            retorno = daoUsuario.atualizar(usuario, con);

        } else if (operacao == OpcaoDAO.REMOVER) {

            retorno = daoUsuario.remover(usuario, con);

        }

        return retorno;
    }
    
    
     public final static Usuario logar(String login, String senha, int academia_id, Conexao con){
         
         boolean logado = false;
         
         UsuarioDAO daoUsuario = new UsuarioDAO();
         
         Usuario usuario = daoUsuario.logarNoSistema(login, senha, academia_id, con);
         
         if(usuario != null){
             
             logado = (usuario.getStatus() == 1);

            if(logado){
                return usuario;
            }else{

                return null;
            }
             
         }else{
             
             return null;
         }
           
       
     }
     
     
     public final static boolean verificaStatus(Usuario usuario){
         
         boolean retorno = false;
         
         if(usuario.getStatus() == 1){
             retorno = true;
         }
         
         return retorno;
     }
    
}
