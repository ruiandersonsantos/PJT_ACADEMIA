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
public enum OpcaoDIA {
    
    DOMINGO(0), SEGUNDA(1), TERCA(2), QUARTA(3), QUINTA(4), SEXTA(5), SABADO(6);
    
    private final int valor; 
    
    OpcaoDIA(int valorOpcao){ 
        
        valor = valorOpcao; 
    } 
    
    public int getValor(){ 
        return valor; 
    }


    
}
