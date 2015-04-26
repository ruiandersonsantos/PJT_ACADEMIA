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
public enum OpcaoESTADO {
    
    Rio_de_Janeiro(1);
    
    private final int valor; 
    
    OpcaoESTADO(int valorOpcao){ 
        
        valor = valorOpcao; 
    } 
    
    public int getValor(){ 
        return valor; 
    }
    
}
