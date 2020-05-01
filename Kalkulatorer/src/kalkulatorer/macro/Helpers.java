package kalkulatorer.macro;


/**
 * @author Espen
 * Hjelpeklasse med forskjellige nyttige metoder
 */
public class Helpers {
    /**
     * Sjekker om et ord kunn inneholder gyldige bokstaver
     * @param ord ord som skal sjekkes
     * @return returnerer true dersom ordet inneholder kunn gyldige bokstaver
     */
    public static boolean erGyldigOrd(String ord) {
        for(int i = 0; i < ord.length(); i++) {
            char tegn = ord.trim().toUpperCase().charAt(i);
            if(!Character.isLetter(tegn))
                return false;
        }
        return true;
    }

    /**
     * Sjekker om et heltall er større eller lik 0
     * @param i tall som skal sjekkes
     * @return returnerer tru dersom gitt tall er større eller lik 0
     */
    public static boolean erGyldigHeltall(int i) {
        return i >= 0;
    }


    /**
     * Sjekker om et tall er partall
     * @param n tall som skal sjekkes
     * @return returnerer true dersom tallet er partall
     */
    public static boolean erPartall(int n) {
        if (n == 2)
            return true;

        int squareRoot = (int)Math.sqrt(n);
        for (int i = 1; i <= squareRoot; i++)
            if (n % i == 0 && i != 1)
                return false;
        return true;
    }

}
