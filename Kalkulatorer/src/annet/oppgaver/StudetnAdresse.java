/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.oppgaver;

/**
 *
 * @author drizz
 */
public class StudetnAdresse {
    private static final int POSTNR_LEN = 4;
    private String navn; 
    private String gate;
    private String by;
    private String fylke;
    private char[] postnummer;

    public StudetnAdresse(String navn, String gate, String by, String fylke, String postnummer) throws UloveligPostnummerUnntak {
        setNavn(navn);
        setGate(gate);
        setBy(by);
        setFylke(fylke);
        setPostnummer(postnummer);
    }

    public StudetnAdresse() {
        //Default
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getFylke() {
        return fylke;
    }

    public void setFylke(String fylke) {
        this.fylke = fylke;
    }

    public char[] getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(char[] postnummer) throws UloveligPostnummerUnntak {
        if(loveligPostnummer(postnummer.toString()))
            this.postnummer = postnummer;
        else 
            throw new UloveligPostnummerUnntak("Ulovelig postnummer...");
    }
    
    public void setPostnummer(String postnummer) throws UloveligPostnummerUnntak {
        if(loveligPostnummer(postnummer))
            this.postnummer = postnummer.toCharArray();
        else 
            throw new UloveligPostnummerUnntak("Ulovelig postnummer...");
    }
    
    public boolean loveligPostnummer(String postnr){
        return postnr.length() == 4 && postnr.matches("[0-9]+");
    }

}

class UloveligPostnummerUnntak extends Exception{ 

    public UloveligPostnummerUnntak(String melding) {
        super(melding);
    }

}
