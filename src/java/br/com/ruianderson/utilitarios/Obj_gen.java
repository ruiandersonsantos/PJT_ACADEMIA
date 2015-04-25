/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ruianderson.utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rui
 */
public class Obj_gen {
    
    public final static java.sql.Date bancoConvertStringToSqlDate(String str) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date dataUtil = new java.util.Date();
        try {
            dataUtil = df.parse(str);
        } catch (ParseException ex) {

        }
        
        String dataOk = ajustaData(str);
        
        java.sql.Date dataSql = new java.sql.Date(df.parse(dataOk).getTime());

        return dataSql;
    }

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

    public final static String convertStringToDate(Date indate) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            dateString = sdfr.format(indate);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }
    
    public final static java.sql.Date getDateAtual() throws ParseException { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date date = new Date();
        
        String data = dateFormat.format(date); 
        
        return convertStringToSqlDate(data);
    }
    
    private final static String ajustaData(String dt){
        
        String retorno="";
        
        retorno = dt.substring(8, 10)+"/"+dt.substring(5, 7)+"/"+dt.substring(0, 4);
        
        return retorno;
    }



}
