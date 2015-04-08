/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.utilitarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 *
 * @author Rui
 */
public class Obj_gen {

    public final static java.sql.Date convertStringToSqlDate(String str) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date dataUtil = new java.util.Date();
        try {
            dataUtil = df.parse(str);
        } catch (ParseException ex) {

        }
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

        return dataSql;
    }

   
}
