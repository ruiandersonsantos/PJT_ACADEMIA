/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.arrays;

/**
 *
 * @author Rui
 */
public enum OpcaoDAO {
    
    ADICIONAR(1), ATUALIZAR(2), REMOVER(3);
    
    private final int valor; 
    
    OpcaoDAO(int valorOpcao){ 
        
        valor = valorOpcao; 
    } 
    
    public int getValor(){ 
        return valor; 
    }


    
}
