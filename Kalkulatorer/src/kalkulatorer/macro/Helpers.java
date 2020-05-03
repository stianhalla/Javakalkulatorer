package kalkulatorer.macro;


/**
 * @author Espen
 * Hjelpeklasse med forskjellige nyttige metoder
 */
public final class Helpers {

    /**
     * Gjør om et ord til stor forbokstav og resten småe
     * @param str ord som skal ha stor forbokstav
     * @return returnerer et ord med stor forbokstav og resten småe
     */
    public static String storForbokstav(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Runder av desimal tall til gitt antall desimaler
     * @param verdi tall som skal avrundes
     * @param plasser hvor mange desimaler
     * @return returnerer en avrundet double verdi
     */
    public static double rundAv(double verdi, int plasser) {
        double scale = Math.pow(10, plasser);
        return Math.round(verdi * scale) / scale;
    }

    /**
     * Runder av et desimaltall til nærmeste heltall
     * @param verdi
     * @return returnerer avrundet heltall
     */
    public static int rundAvTilHeltall(double verdi) {
        return (int) Math.round(verdi);
    }

    /***************************************Sjekker*******************************************/

    /**
     * Sjekker om et ord kunn inneholder gyldige bokstaver
     * @param str ord som skal sjekkes
     * @return returnerer true dersom ordet inneholder kunn gyldige bokstaver
     */
    public static boolean erGyldigOrd(String str) {
        String ord = str.trim().toUpperCase();
        for(int i = 0; i < ord.length(); i++) {
            char tegn = ord.charAt(i);
            if(!Character.isLetter(tegn))
                return false;
        }
        return true;
    }

    /**
     * Sjekker om et tall er primtall
     * @param n tall som skal sjekkes
     * @return returnerer true dersom tallet er primtall
     */
    public static boolean erPrimtall(int n) {
        if (n == 2)
            return true;

        int squareRoot = (int)Math.sqrt(n);
        for (int i = 1; i <= squareRoot; i++)
            if (n % i == 0 && i != 1)
                return false;
        return true;
    }

    /**
     * Sjekker om et tall er partall eller oddetall
     * @param n heltall
     * @return true dersom tallet er partall
     */
    public static boolean erPartall(int n) {
        return n % 2 == 0;
    }

    /**
     * Sjekker om en string er tom eller ikke
     * @param str
     * @return returnerer true dersom Stingen har innhold
     */
    public static boolean harInnhold(String str) {
        return (str != null && str.trim().length() >0 );
    }

    /**
     * Sjekker om et tall er større eller lik 0
     * @param i tall som skal sjekkes
     * @return returnerer tru dersom gitt tall er større eller lik 0
     */
    public static boolean erPositivtTall(double i) {
        return i > 0.0;
    }

    /**
     * Sjekker om et tall er mellom gitte verdier
     * @param tall
     * @param fra fra til og med
     * @param til til til og med
     * @return
     */
    public static boolean erInnenfor(double tall, double fra, double til) {
        return tall >= fra && tall <= til;
    }

    /**
     * Sjekker om en string er et tall
     * @param str
     * @return returnerer true dersom stringen er ett tall
     */
    public static boolean erTall(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }



    // Sjekk for null

    // Skal ikke instansieres
    private Helpers() {}
}
