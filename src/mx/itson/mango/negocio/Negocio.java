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
     * This method generates the curp
     *
     * @param name names of the person
     * @param firstLastName First surname of the person
     * @param secondLastName Person's second last name
     * @param day day of birth of the person
     * @param month month of birth of the person
     * @param year year of birth of the person
     * @param sex sex of the person
     * @param state state in which the person resides
     * @return returns a String curp type
     */
    public String generarCurp(String name, String firstLastName, String secondLastName, String day, String month, String year, String sex, String state) {
        // Igualar todas las variables a mayusculas y limpiar el texto

        name = name.toUpperCase().replace(" ", "").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        firstLastName = firstLastName.toUpperCase().replace(" ", "").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        secondLastName = secondLastName.toUpperCase().replace(" ", "").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        day = day.replace(" ", "");
        month = month.replace(" ", "");
        year = year.replace(" ", "");
        String curp;

        //----- Gets the first letter and the first vowel of the first last name. -----
        char firstLetter = firstLastName.charAt(0);
        char firstVowelInternal = findFirstVowelInternal(firstLastName);

        curp = "" + firstLetter + firstVowelInternal;

        //----- Gets the first letter of the second last name. -----
        char firstLetterSecondSurname = getFirstLetterSecondLastName(secondLastName);

        curp = curp + firstLetterSecondSurname;

        // ----------Take out the first letter --------
        char[] lettersNames = name.toCharArray();
// ------- in case it is a compound name that begins with Joso or Maria
        if (name.startsWith("JOSE") && name.length() > 4) {
            name = name.replace("JOSE", "");
            curp = curp + lettersNames[4];
        } else if (name.startsWith("MARIA") && name.length() > 5) {
            name = name.replace("MARIA", "");
            curp = curp + lettersNames[5];
        } else {
            curp = curp + lettersNames[0];
        }

        //---------Put together dates of birth --------
        String monthCapital = month.toUpperCase();
        String dateWithoutSpaces;
        String monthNumber = "";

        // last numbers of the years
        char[] letrasyear = year.toCharArray();
        dateWithoutSpaces = "" + letrasyear[2] + letrasyear[3];

        //months in number
        if (monthCapital.contains("E") || monthCapital.contains("O") || monthCapital.contains("A")) {
            switch (monthCapital) {
                case "ENERO":
                    monthNumber = "01";
                    break;
                case "FEBRERO":
                    monthNumber = "02";
                    break;
                case "MARZO":
                    monthNumber = "03";
                    break;
                case "ABRIL":
                    monthNumber = "04";
                    break;
                case "MAYO":
                    monthNumber = "05";
                    break;
                case "JUNIO":
                    monthNumber = "06";
                    break;
                case "JULIO":
                    monthNumber = "07";
                    break;
                case "AGOSTO":
                    monthNumber = "08";
                    break;
                case "SEPTIEMBRE":
                    monthNumber = "09";
                    break;
                case "OCTUBRE":
                    monthNumber = "10";
                    break;
                case "NOVIEMBRE":
                    monthNumber = "11";
                    break;
                case "DICIEMBRE":
                    monthNumber = "12";
                    break;
                default:
                    MainJFrame mJFrame = new MainJFrame();
                    JOptionPane.showMessageDialog(
                            mJFrame,
                            "The email or password is incorrect",
                            "Something went wrong!",
                            JOptionPane.ERROR_MESSAGE
                    );

            }
            dateWithoutSpaces = dateWithoutSpaces + monthNumber;
        } else {
            if (month.length() == 1) {
                dateWithoutSpaces = dateWithoutSpaces + "0" + month;
            } else {
                dateWithoutSpaces = dateWithoutSpaces + month;
            }

        }
        //Dias en numero
        if (day.length() == 1) {
            dateWithoutSpaces = dateWithoutSpaces + "0" + day;
        } else {
            char[] charDay = day.toCharArray();
            dateWithoutSpaces = dateWithoutSpaces + charDay[day.length() - 2] + charDay[day.length() - 1];
        }
        curp = curp + dateWithoutSpaces;

        if (sex.equals("H")) {
            curp = curp + "H";
        } else {
            curp = curp + "M";
        }

        String statecode = estados(state);
        curp = curp + statecode;

        char firstConsonantNotInitial1 = findSecondConsonantInternal1(firstLastName);

        curp = curp + firstConsonantNotInitial1;

        //----- Gets the second letter of the second last name. -----
        char firstConsonantNotInitial = findSecondConsonantInternal(secondLastName);

        curp = curp + firstConsonantNotInitial;

        char firstConsonant = findSecondConsonantInternal(name);
        curp = curp + firstConsonant;

        char valueDate = ' ';
        int anoNacimiento = Integer.parseInt(year);

        if (anoNacimiento >= 2000) {
            valueDate = 'A'; // Si es 2000 o posterior.
        } else {
            valueDate = '0'; // Si es anterior a 2000.
        }
        curp = curp + valueDate + 1;
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
     * @param lastName
     * @return return c or return "\0"
     */
    public static char findFirstVowelInternal(String lastName) {
        for (int i = 1; i < lastName.length() - 1; i++) {
            char c = Character.toUpperCase(lastName.charAt(i));
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
     * @param secondSurname
     * @return segundoApellido.charAt(0) or returns character "X".
     */
    public static char getFirstLetterSecondLastName(String secondSurname) {
        if (secondSurname != null && !secondSurname.isEmpty()) {
            return secondSurname.charAt(0);
        } else {
            return 'X';
        }
    }

    /**
     * This method obtains the second letter if it does not have X, it returns
     *
     * @param secondSurname
     * @return returns a char type
     */
    public static char findSecondConsonantInternal(String secondSurname) {
        for (int i = 1; i < secondSurname.length(); i++) {
            char c = Character.toUpperCase(secondSurname.charAt(i));
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
                return c;
            }

        }
        return 'X';
    }

    /**
     * This method obtains the second consonant letter
     *
     * @param lastName person's last name
     * @return returns a char type
     */
    public static char findSecondConsonantInternal1(String lastName) {
        char c = 'a';
        for (int i = 1; i < lastName.length(); i++) {
            c = Character.toUpperCase(lastName.charAt(i));
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
     * This method finds the first consonant in a text string and returns it. If
     * no consonant is found, returns a blank (' ').
     *
     * @param name The text string in which to search for the first consonant.
     * @return The first consonant found or a blank if none are found.
     */
    public char findFirstInternalConsonant(String name) {

        name = name.toUpperCase();

        for (int i = 1; i < name.length(); i++) {
            char letter = name.charAt(i);

            if (letter != 'A' && letter != 'E' && letter != 'I' && letter != 'O' && letter != 'U') {
                return letter;
            }
        }

        return ' ';
    }

    /**
      * Verifies whether the data you enter corresponds to its use for the
      * curp
      *
      * @param name usernames
      * @param firstLastName User's first last name
      * @param secondLastName User's second last name
      * @param day Birth day
      * @param month month of birth
      * @param year year of birth
      * @return returns a boolean type
     */
    public boolean verificarDatos(String name, String firstLastName, String secondLastName, String day, String month, String year) {
        if (name.isEmpty() || name.isEmpty() || name.contains("1") || name.contains("2")
                || name.contains("3") || name.contains("4") || name.contains("5")
                || name.contains("6") || name.contains("7") || name.contains("8")
                || name.contains("9") || name.contains("0") || name.contains("-") || name.contains("*") || name.contains("/")) {
            return false;
        }
        if (firstLastName.isEmpty() || firstLastName.contains("1") || firstLastName.contains("2")
                || firstLastName.contains("3") || firstLastName.contains("4") || firstLastName.contains("5")
                || firstLastName.contains("6") || firstLastName.contains("7") || firstLastName.contains("8")
                || firstLastName.contains("9") || firstLastName.contains("0")
                || firstLastName.contains("-") || firstLastName.contains("*") || firstLastName.contains("/")) {
            return false;
        }
        if (secondLastName.contains("1") || secondLastName.contains("2") || secondLastName.contains("3")
                || secondLastName.contains("4") || secondLastName.contains("5") || secondLastName.contains("6")
                || secondLastName.contains("7") || secondLastName.contains("8") || secondLastName.contains("9")
                || secondLastName.contains("0") || secondLastName.contains("-")
                || secondLastName.contains("*") || secondLastName.contains("/")) {
            return false;
        }
        if (day.isEmpty() || day.equals("00") || day.equals("0")) {
            return false;
        }
        char[] lyricsDays = day.toCharArray();
        for (char a : lyricsDays) {
            if (!(a == '0' || a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9')) {
                return false;
            }
        }
        if (month.isEmpty() || month.equals("00") || month.equals("0") || month.contains("-") || month.contains("*") || month.contains("/")) {
            return false;
        }
        if (year.isEmpty() || year.length() != 4 || year.startsWith("0")) {
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
