package mx.itson.mango.negocio;

/**
 * Contains methods to generate a curp
 *
 * @author Luis, Yolanda, Jose, Sergio and Pedro
 */
public class Negocio {

    public String generarCurp(String nombres, String primerApellido, String segundoApellido, String dia, String mes, String year, String sexo) {
        String curp = "";
         //aqui andamos yo el pedro 
         
        // ----------Sacar la primera letra --------
        String nombresMayusculas = nombres.toUpperCase().replace(" ", "");
        char[] letrasNombres = nombresMayusculas.toCharArray();

        // ------- en caso de que sea nombre compuesto que inicie con Joso o maria
        if (nombresMayusculas.startsWith("JOSE") || nombresMayusculas.startsWith("JOSÉ")) {
            curp = curp + letrasNombres[4];
        } else if (nombresMayusculas.startsWith("MARIA") || nombresMayusculas.startsWith("MARÍA")) {
            curp = curp + letrasNombres[5];
        } else {
            curp = curp + letrasNombres[0];
        }

        //---------Sacar juntar fechas de nacimiento --------
        String mesMayusculas = mes.toUpperCase();
        String fechaSinEspacios;
        String mesNumero = "";

        //Dias en numero
        if (dia.length() == 1) {
            fechaSinEspacios = "0" + dia;
        } else {
            char[] carateresDias = dia.toCharArray();
            fechaSinEspacios = "" + carateresDias[dia.length() - 2] + carateresDias[dia.length() - 1];
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
                fechaSinEspacios = fechaSinEspacios + mes;
            }

        }

        // ultimo numeros de los años 
        char[] letrasyear = year.toCharArray();
        fechaSinEspacios = fechaSinEspacios + letrasyear[2] + letrasyear[3];

        curp = curp + fechaSinEspacios;
        return curp;
    }
    
         
            

    /**
     *This method checks the state where the citizen was born and takes 
     * the two letters corresponding to the entity of birth.
     * @param federalEntity Identify the entity of birth of the citizen.
     * @return returns the value of verify
     */
    public  String estados(String federalEntity) {
        String verify;

        if (federalEntity.equals("Aguascalientes")) {
            verify = "AS";
        } else if (federalEntity.equals("Baja California")) {
            verify = "BC";
        } else if (federalEntity.equals("Baja California Sur")) {
            verify = "BS";
        } else if (federalEntity.equals("Campeche")) {
            verify = "CC";
        } else if (federalEntity.equals("Chiapas")) {
            verify = "CS";
        } else if (federalEntity.equals("Chihuahua")) {
            verify = "CH";
        } else if (federalEntity.equals("Coahuila")) {
            verify = "CL";
        } else if (federalEntity.equals("Colima")) {
            verify = "CM";
        } else if (federalEntity.equals("Distrito Federal")) {
            verify = "DF";
        } else if (federalEntity.equals("Durango")) {
            verify = "DG";
        } else if (federalEntity.equals("Guanajuato")) {
            verify = "GT";
        } else if (federalEntity.equals("Guerrero")) {
            verify = "GR";
        } else if (federalEntity.equals("Hidalgo")) {
            verify = "HG";
        } else if (federalEntity.equals("Jalisco")) {
            verify = "JC";
        } else if (federalEntity.equals("México")) {
            verify = "MC";
        } else if (federalEntity.equals("Michoacan")) {
            verify = "MN";
        } else if (federalEntity.equals("Morelos")) {
            verify = "MS";
        } else if (federalEntity.equals("Nayarit")) {
            verify = "NT";
        } else if (federalEntity.equals("Nuevo León")) {
            verify = "NL";
        } else if (federalEntity.equals("Oaxaca")) {
            verify = "OC";
        } else if (federalEntity.equals("Puebla")) {
            verify = "PL";
        } else if (federalEntity.equals("Querétaro")) {
            verify = "QT";
        } else if (federalEntity.equals("Quintana Roo")) {
            verify = "QR";
        } else if (federalEntity.equals("San Luis Potosí")) {
            verify = "SP";
        } else if (federalEntity.equals("Sinaloa")) {
            verify = "SL";
        } else if (federalEntity.equals("Sonora")) {
            verify = "SR";
        } else if (federalEntity.equals("Tabasco")) {
            verify = "TC";
        } else if (federalEntity.equals("Tlaxcala")) {
            verify = "TL";
        } else if (federalEntity.equals("Tamaulipas")) {
            verify = "TS";
        } else if (federalEntity.equals("Veracruz")) {
            verify = "VZ";
        } else if (federalEntity.equals("Yucatán")) {
            verify = "YN";
        } else if (federalEntity.equals("Zacatecas")) {
            verify = "ZS";
        } else if (federalEntity.equals("Nacido en el extranjero")) {
            verify = "NE";
        } else {
            verify = "Ne";
        }
        return verify;

    }
    //aqui voy yo pedro
    

}
