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
public enum OpcaoCIDADE {
    
    
    RIO_DE_JANEIRO(1), DUQUE_DE_CAXIAS(2),SAO_JOAO_DE_MERETI(3);
    
    private final int valor; 
    
    OpcaoCIDADE(int valorOpcao){ 
        
        valor = valorOpcao; 
    } 
    
    public int getValor(){ 
        return valor; 
    }
    
}
