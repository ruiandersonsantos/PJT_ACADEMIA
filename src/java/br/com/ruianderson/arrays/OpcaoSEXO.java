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
public enum OpcaoSEXO {
    
     MASCULINO("M"), FEMININO("F");
    
    private final String valor; 
    
    OpcaoSEXO(String valorOpcao){ 
        
        valor = valorOpcao; 
    } 
    
    public String getValor(){ 
        return valor; 
    }

    
}
