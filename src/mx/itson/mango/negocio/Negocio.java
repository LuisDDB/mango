/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.mango.negocio;

/**
 *
 * @author luisd
 */
public class Negocio {
    
    public static String  generarCurp(String nombres, String primerApellido, String segundoApellido, String dia, String mes, String year, String sexo) {
        //Se agrego un nuevo metodo
        String curp = "";

        // ----------Sacar la primera letra --------
        String nombresMayusculas = nombres.toUpperCase().replace(" ", "");
        char[] letrasNombres = nombresMayusculas.toCharArray();

        // ------- en caso de que sea nombre compuesto que inicie con Joso o maria
        if (nombresMayusculas.startsWith("JOSE")|| nombresMayusculas.startsWith("JOSÉ")) {
            curp = curp + letrasNombres[4];
        } else if (nombresMayusculas.startsWith("MARIA")||nombresMayusculas.startsWith("MARÍA")) {
            curp = curp + letrasNombres[5];
        } else {
            curp = curp + letrasNombres[0];
        }

        //---------Sacar juntar fechas de nacimiento --------
        String mesMayusculas = mes.toUpperCase();
        String fechaSinEspacios;
        String mesNumero="";
     
        //Dias en numero
        if (dia.length() == 1) {
            fechaSinEspacios = "0" + dia;
        } else {
            char [] carateresDias =dia.toCharArray();
            fechaSinEspacios = ""+carateresDias[dia.length()-2] + carateresDias[dia.length()-1];
        }

        //meses en numero
        if (mesMayusculas.contains("E") || mesMayusculas.contains("O") || mesMayusculas.contains("A")) {
            switch (mesMayusculas) {
                case "ENERO":
                    mesNumero = "01";
                    break;
                case "FEBRERO":
                    mesNumero = "02";
                    break;
                case "MARZO":
                    mesNumero = "03";
                    break;
                case "ABRIL":
                    mesNumero = "04";
                    break;
                case "MAYO":
                    mesNumero = "05";
                    break;
                case "JUNIO":
                    mesNumero = "06";
                    break;
                case "JULIO":
                    mesNumero = "07";
                    break;
                case "AGOSTO":
                    mesNumero = "08";
                    break;
                case "SEPTIEMBRE":
                    mesNumero = "09";
                    break;
                case "OCTUBRE":
                    mesNumero = "10";
                    break;
                case "NOVIEMBRE":
                    mesNumero = "11";
                    break;
                case "DICIEMBRE":
                    mesNumero = "12";
                    break;
            }
            fechaSinEspacios = fechaSinEspacios + mesNumero;
        } else {
            if (mes.length() == 1) {
                fechaSinEspacios = fechaSinEspacios + "0" + mes;
            } else {
                fechaSinEspacios = fechaSinEspacios+ mes;
            }

        }
        
        // ultimo numeros de los años 
        char[] letrasyear = year.toCharArray();
        fechaSinEspacios=fechaSinEspacios+letrasyear[2]+letrasyear[3];
        
        curp=curp+fechaSinEspacios;
        
       
        return curp;
        

    }
}
