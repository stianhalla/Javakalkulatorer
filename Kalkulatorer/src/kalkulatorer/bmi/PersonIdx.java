/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.bmi;

import java.io.Serializable;
import kalkulatorer.macro.Helpers;

/**
 *
 * @author mussal
 */
public class PersonIdx implements Serializable {
    
    private static final double TUNGEST = 635;
    private static final double HOYEST = 3;
    private static final int ELDST = 125;
    
    private double hoyde, vekt; // oppgitt i meter og kg
    private int alder; 
    private boolean hun = true; // standard hunkjønn settes false for han

    public PersonIdx(double hoyde, double vekt, int alder, boolean hun) {
        setHoyde(hoyde); 
        setVekt(vekt);
        setAlder(alder);
        this.hun = hun;
    }

    public double getHoyde() {
        return hoyde;
    }

    public double getVekt() {
        return vekt;
    }

    public int getAlder() {
        return alder;
    }

    public boolean erHun() {
        return hun;
    }

    public void setHoyde(double hoyde) {
            if(!Helpers.erInnenfor(hoyde,0.0,HOYEST))
                throw new IllegalArgumentException("Hoyde må være et tall oppgitt i meter mellom 0 og 3. eks. 1.5");
        this.hoyde = hoyde;
    }
    
    
    public void setVekt(double vekt) {
        if(!Helpers.erInnenfor(vekt,0.0,TUNGEST))
            throw new IllegalArgumentException("Vekt må være et tall oppgitt i kg mellom 0 og 635.");
        this.vekt = vekt;
    }

    public void setAlder(int alder) {
        if(alder < 0 || alder > 125)
            throw new IllegalArgumentException("Alder må være et heltall mellom 0 og 125");
        this.alder = alder;
    }

    public void setHun(boolean hun) {
        this.hun=hun;
    }

    @Override
    public String toString() {
        return "PersonIdx{" + "hoyde=" + hoyde + ", vekt=" + vekt + ", alder=" + alder + ", kj\u00f8nn=" + hun + '}';
    }
    
    
    
}
