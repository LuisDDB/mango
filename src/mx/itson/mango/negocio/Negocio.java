package mx.itson.mango.negocio;

/**
 * Contains methods to generate a curp
 *
 * @author Luis, Yolanda, José, Sergio and Pedro
 */
public class Negocio {

    public String generarCurp(String nombres, String primerApellido, String segundoApellido, String dia, String mes, String year, String sexo) {
        String curp = "";
         
        
        //----- Gets the first letter and the first vowel of the first last name. -----
        char primeraLetra = primerApellido.charAt(0);
        char primeraVocalInterna = encontrarPrimeraVocalInterna(primerApellido);

        curp = "" + primeraLetra + primeraVocalInterna;
        
        //----- Gets the first letter of the second last name. -----
        char primeraLetraSegundoApellido = obtenerPrimeraLetraSegundoApellido(segundoApellido);

        curp = curp + primeraLetraSegundoApellido;
        
         
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
     *What this method does is find the first internal vowel of the first last name,
     *enter the for we tell you that "i" starts from position 1 since from that position
     *the internal letters of the last name start, so the for runs from position 1 to
     *penultimate position of the length of the last name looking for the first internal vowel.
     *In each iteration of the loop, the character at position "i" in the string last name is obtained
     *using apellido.charAt(i), then Character.toUpperCase(o) which is used to convert the character
     *a capital letter, which allows you to verify the vowels in upper or lower case without distinction.
     *Then in the if it is verified if the character "c" is a vowel, then it is compared with the 5 uppercase vowels
     *using the == operator
     *After "return c" if a vowel is found in the last name, that character is returned as a result and the loop is exited.
     *If after the loop, no vowel is
     *found returns a "\0" which is a null character.
     * @param apellido
     * @return return c or return "\0"
     */
     public static char encontrarPrimeraVocalInterna(String apellido) {
        for (int i = 1; i < apellido.length() - 1; i++) {
            char c = Character.toUpperCase(apellido.charAt(i));
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return c;
            }

        }
        return '\0';
     }
     /**
      * What this method does is find the first letter of the second last name.
      * Enter the if where it verifies that the "segundoApellido" parameter is not null and that it is not empty.
      * segundoApellido != null checks if it is not null.
      * !segundoApellido.isEmpty() checks if it is not empty. This means that it must contain at least one character.
      * If secondLastName is not null and is not empty, returns the first character of the last name using the charAt(0) method.
      * This means that the function returns the first letter of the second last name.
      * But if it happens that secondLastName is null or empty, it enters the else and returns an
      * when a person does not have a second last name.
      * @param segundoApellido
      * @return segundoApellido.charAt(0) or returns character "X".
      */
     public static char obtenerPrimeraLetraSegundoApellido(String segundoApellido) {
        if (segundoApellido != null && !segundoApellido.isEmpty()) {
            return segundoApellido.charAt(0);
        } else {
            return 'X';
        }
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
    //------ first internal consonant (not initial) of the name-------
      /*
      * This code block performs the following actions:
 *
 * Section 1: Declaration and initialization of variables.
 * - A variable 'firstConsonant' is declared and assigned the first consonant found in 'name'.
 * - A variable 'dateValue' is declared and the value '0' is assigned as the default value.
 * - A variable 'number' is declared and the value '1' is assigned.
 * - A string 'result' is created by combining 'firstConsonant', 'dateValue' and 'number'.
 * - The result is displayed in the console along with a message.
 *
 * Section 2: Processing 'name' to find the first internal consonant.
 * - Convert 'name' to lowercase to ensure consistency in processing.
 * - 'Name' is looped from the second character.
 * - If 'letter' is not a vowel ('a', 'e', ​​'i', 'o', 'u'), it is returned as the first consonant after the first letter in 'name'.
 * - If no consonant is found, blank is returned as the default value.....
      */
     public char encontrarPrimeraConsonanteInterna(String nombre) {
       
       char primeraConsonante = encontrarPrimeraConsonanteInterna(nombre);

char valorFecha = '0';  //// Como no tenemos la fecha de nacimiento, establecemos un valor predeterminado '0'.

char numero = '1';

String resultado = String.valueOf(primeraConsonante) + valorFecha + numero;


nombre = nombre.toUpperCase();


for (int i = 1; i < nombre.length(); i++) {
    char letra = nombre.charAt(i);
    

    if (letra != 'A' && letra != 'E' && letra != 'I' && letra != 'O' && letra != 'U') {
        return letra;
       
    }
}

return ' ';

    }
}  
    


