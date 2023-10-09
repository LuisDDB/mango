package mx.itson.mango.negocio;

import javax.swing.JOptionPane;
import mx.itson.mango.ui.MainJFrame;

/**
 * Contains methods to generate a curp
 *
 * @author Luis, Yolanda, José, Sergio and Pedro
 */
public class Negocio {

    /**
     * Este metodo genera la curp 
     * @param nombres nombres de la persona
     * @param primerApellido Primer apellido de la persona
     * @param segundoApellido Segundo apellido de la persona
     * @param dia dia de nacimiento de la persona
     * @param mes mes de nacimiento de la persona
     * @param year año de nacimiento de la persona
     * @param sexo sexo de la persona
     * @param state estado en el que reside la persona
     * @return retorn un tipo String curp
     */
    public String generarCurp(String nombres, String primerApellido, String segundoApellido, String dia, String mes, String year, String sexo, String state) {
        // Igualar todas las variables a mayusculas y limpiar el texto

        nombres = nombres.toUpperCase().replace(" ", "").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        primerApellido = primerApellido.toUpperCase().replace(" ", "").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        segundoApellido = segundoApellido.toUpperCase().replace(" ", "").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        dia = dia.replace(" ", "");
        mes = mes.replace(" ", "");
        year = year.replace(" ", "");
        String curp;

        //----- Gets the first letter and the first vowel of the first last name. -----
        char primeraLetra = primerApellido.charAt(0);
        char primeraVocalInterna = encontrarPrimeraVocalInterna(primerApellido);

        curp = "" + primeraLetra + primeraVocalInterna;

        //----- Gets the first letter of the second last name. -----
        char primeraLetraSegundoApellido = obtenerPrimeraLetraSegundoApellido(segundoApellido);

        curp = curp + primeraLetraSegundoApellido;

        // ----------Sacar la primera letra --------
        char[] letrasNombres = nombres.toCharArray();

        // ------- en caso de que sea nombre compuesto que inicie con Joso o maria
        if (nombres.startsWith("JOSE") && nombres.length() > 4) {
            nombres = nombres.replace("JOSE", "");
            curp = curp + letrasNombres[4];
        } else if (nombres.startsWith("MARIA") && nombres.length() > 5) {
            nombres = nombres.replace("MARIA", "");
            curp = curp + letrasNombres[5];
        } else {
            curp = curp + letrasNombres[0];
        }

        //---------Sacar juntar fechas de nacimiento --------
        String mesMayusculas = mes.toUpperCase();
        String fechaSinEspacios;
        String mesNumero = "";

        // ultimo numeros de los años 
        char[] letrasyear = year.toCharArray();
        fechaSinEspacios = "" + letrasyear[2] + letrasyear[3];

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
                default:
                    MainJFrame mJFrame = new MainJFrame();
                    JOptionPane.showMessageDialog(
                            mJFrame,
                            "El correo o contraseña son incorrectos",
                            "Algo salio mal!",
                            JOptionPane.ERROR_MESSAGE
                    );

            }
            fechaSinEspacios = fechaSinEspacios + mesNumero;
        } else {
            if (mes.length() == 1) {
                fechaSinEspacios = fechaSinEspacios + "0" + mes;
            } else {
                fechaSinEspacios = fechaSinEspacios + mes;
            }

        }
        //Dias en numero
        if (dia.length() == 1) {
            fechaSinEspacios =fechaSinEspacios + "0" + dia;
        } else {
            char[] carateresDias = dia.toCharArray();
            fechaSinEspacios = fechaSinEspacios + carateresDias[dia.length() - 2] + carateresDias[dia.length() - 1];
        }
        curp = curp + fechaSinEspacios;

        if (sexo.equals("H")) {
            curp = curp + "H";
        } else {
            curp = curp + "M";
        }

        String codigoEstado = estados(state);
        curp = curp + codigoEstado;

        char primeraConsonanteNoInicial1 = encontrarSegundaConsonanteInterna1(primerApellido);

        curp = curp + primeraConsonanteNoInicial1;

        //----- Gets the second letter of the second last name. -----
        char primeraConsonanteNoInicial = encontrarSegundaConsonanteInterna(segundoApellido);

        curp = curp + primeraConsonanteNoInicial;

        char primeraConsonante = encontrarPrimeraConsonanteInterna(nombres);
        curp = curp + primeraConsonante;

        char valorFecha = ' ';
        int anoNacimiento = Integer.parseInt(year);

        if (anoNacimiento >= 2000) {
            valorFecha = 'A'; // Si es 2000 o posterior.
        } else {
            valorFecha = '0'; // Si es anterior a 2000.
        }
        curp = curp + valorFecha + 1;
        return curp;
    }

    
    
    
    
    /**
     * What this method does is find the first internal vowel of the first last
     * name, enter the for we tell you that "i" starts from position 1 since
     * from that position the internal letters of the last name start, so the
     * for runs from position 1 to penultimate position of the length of the
     * last name looking for the first internal vowel. In each iteration of the
     * loop, the character at position "i" in the string last name is obtained
     * using apellido.charAt(i), then Character.toUpperCase(o) which is used to
     * convert the character a capital letter, which allows you to verify the
     * vowels in upper or lower case without distinction. Then in the if it is
     * verified if the character "c" is a vowel, then it is compared with the 5
     * uppercase vowels using the == operator After "return c" if a vowel is
     * found in the last name, that character is returned as a result and the
     * loop is exited. If after the loop, no vowel is found returns a "\0" which
     * is a null character.
     *
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
     * Enter the if where it verifies that the "segundoApellido" parameter is
     * not null and that it is not empty. segundoApellido != null checks if it
     * is not null. !segundoApellido.isEmpty() checks if it is not empty. This
     * means that it must contain at least one character. If secondLastName is
     * not null and is not empty, returns the first character of the last name
     * using the charAt(0) method. This means that the function returns the
     * first letter of the second last name. But if it happens that
     * secondLastName is null or empty, it enters the else and returns an when a
     * person does not have a second last name.
     *
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
     * Este metodo obtine la segunda letra en caso de no tener retorna X
     * @param segundoApellido
     * @return retorna un tipo char
     */
    public static char encontrarSegundaConsonanteInterna(String segundoApellido) {
        for (int i = 1; i < segundoApellido.length(); i++) {
            char c = Character.toUpperCase(segundoApellido.charAt(i));
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
                return c;
            }

        }
        return 'X';
    }
    
    
    
    /**
     * Este metodo obtine la segunda letra consonante 
     * @param Apellido apellido de la persona
     * @return retorna un tipo char
     */

    public static char encontrarSegundaConsonanteInterna1(String Apellido) {
        char c = 'a';
        for (int i = 1; i < Apellido.length(); i++) {
            c = Character.toUpperCase(Apellido.charAt(i));
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
                break;
            }
        }
        return c;
    }

    /**
     * This method checks the state where the citizen was born and takes the two
     * letters corresponding to the entity of birth.
     *
     * @param federalEntity Identify the entity of birth of the citizen.
     * @return returns the value of verify
     */
    public String estados(String federalEntity) {
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

    
    
    /**
    * This method finds the first consonant in a text string and returns it.
  * If no consonant is found, returns a blank (' ').
  *
  * @param name The text string in which to search for the first consonant.
  * @return The first consonant found or a blank if none are found.
     */
    public char encontrarPrimeraConsonanteInterna(String nombre) {

        nombre = nombre.toUpperCase();

        for (int i = 1; i < nombre.length(); i++) {
            char letra = nombre.charAt(i);

            if (letra != 'A' && letra != 'E' && letra != 'I' && letra != 'O' && letra != 'U') {
                return letra;
            }
        }

        return ' ';
    }
    
    
    
    /**
     * Verifica si los datos que ingresa son correspondientes a su uso para la curp
     * @param nombres nombres del usuario
     * @param primerApellido Primer apellido del usuario
     * @param segundoApellido Segundo apellido del usuario
     * @param dia Dia de nacimiento
     * @param mes mes de nacimiento
     * @param year año de nacimietno
     * @return retorna un tipo boolean 
     */
    public boolean verificarDatos(String nombres, String primerApellido, String segundoApellido, String dia, String mes, String year) {
        if (nombres.isEmpty()||nombres.isEmpty()||nombres.contains("1")||nombres.contains("2")
                ||nombres.contains("3")||nombres.contains("4")||nombres.contains("5")
                ||nombres.contains("6")||nombres.contains("7")||nombres.contains("8")
                ||nombres.contains("9")||nombres.contains("0")|| nombres.contains("-")|| nombres.contains("*")|| nombres.contains("/")) {
            return false;
        }
        if (primerApellido.isEmpty()||primerApellido.contains("1")||primerApellido.contains("2")
                ||primerApellido.contains("3")||primerApellido.contains("4")||primerApellido.contains("5")
                ||primerApellido.contains("6")||primerApellido.contains("7")||primerApellido.contains("8")
                ||primerApellido.contains("9")||primerApellido.contains("0")
                || primerApellido.contains("-")|| primerApellido.contains("*")|| primerApellido.contains("/")) {
            return false;
        }
        if (segundoApellido.contains("1")||segundoApellido.contains("2")||segundoApellido.contains("3")
                ||segundoApellido.contains("4")||segundoApellido.contains("5")||segundoApellido.contains("6")
                ||segundoApellido.contains("7")||segundoApellido.contains("8")||segundoApellido.contains("9")
                ||segundoApellido.contains("0") || segundoApellido.contains("-")
                || segundoApellido.contains("*")|| segundoApellido.contains("/")) {
            return false;
        }
        if (dia.isEmpty()||dia.equals("00")||dia.equals("0")) {
            return false;
        }
        char[] letrasDias = dia.toCharArray();
        for (char a : letrasDias) {
            if (!(a == '0' || a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9')) {
                return false;
            }
        }
        if (mes.isEmpty()||mes.equals("00")||mes.equals("0")|| mes.contains("-")|| mes.contains("*")|| mes.contains("/")) {
            return false;
        }
        if (year.isEmpty()|| year.length()!=4||year.startsWith("0")) {
            return false;
        }
        char[] letrasYear = year.toCharArray();
        for (char a : letrasYear) {
            if (!(a == '0' || a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9')) {
                return false;
            }
        }
        return true;
    }

}
