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
public enum OpcaoSTATUS {

    ATIVO(1),
    INATIVO(2),
    CANCELADO(3),
    AGUARDANDO_ENVIO(4),
    ENVIADA(5),;

    private final int valor;

    OpcaoSTATUS(int valorOpcao) {

        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }

}
