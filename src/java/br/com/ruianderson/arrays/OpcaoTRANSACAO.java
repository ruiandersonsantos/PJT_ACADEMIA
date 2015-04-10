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
public enum OpcaoTRANSACAO {
    
     COM_TRANSACAO(1), SEM_TRANSACAO(2);
    
    private final int valor; 
    
    OpcaoTRANSACAO(int valorOpcao){ 
        
        valor = valorOpcao; 
    } 
    
    public int getValor(){ 
        return valor; 
    }


    
}
