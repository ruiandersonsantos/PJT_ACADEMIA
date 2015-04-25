/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.testeMain;

/**
 *
 * @author Rui
 */
public class teste2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String dt = "2015-02-31";
        
        String ret = dt.substring(8, 10)+"/"+dt.substring(5, 7)+"/"+dt.substring(0, 4);
        
        System.out.println(ret);
    }
    
}
