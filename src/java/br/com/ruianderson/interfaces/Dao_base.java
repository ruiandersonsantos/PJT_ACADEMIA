/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.interfaces;

import br.com.ruianderson.dbutil.Conexao;
import java.util.List;

/**
 *
 * @author Rui
 */
public interface Dao_base<T> {

    public int adicionar(T objt, Conexao con);

    public int atualizar(T objt, Conexao con);

    public int remover(T objt, Conexao con);

    public List<T> listarTodos(int id_solicitante, Conexao con);
      
    public T buscarPorId(int id, int id_solicitante, Conexao con);
    
    public List<T> buscarPorNome(String nome, int tipoPesquisa, int id_solicitante, Conexao con);
  
}
