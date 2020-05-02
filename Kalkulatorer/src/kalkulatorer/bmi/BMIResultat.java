/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.bmi;

import java.io.Serializable;

/**
 *
 * @author mussal
 */
public class BMIResultat implements Serializable{
    private double kmi;
    private String melding;

    public BMIResultat(double kmi, String melding) {
        this.kmi = kmi;
        this.melding = melding;
    }

    public void setKmi(double kmi) {
        this.kmi = kmi;
    }

    public void setMelding(String melding) {
        this.melding = melding;
    }

    public double getKmi() {
        return kmi;
    }

    public String getMelding() {
        return melding;
    }

    @Override
    public String toString() {
        return "BMIResultat{" + "kmi=" + kmi + ", melding=" + melding + '}';
    }
}
