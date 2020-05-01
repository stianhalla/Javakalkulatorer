/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer;

/**
 *
 * @author drizz
 */
public class Nocco {
    private String navn; 
    private int mgKoffein;
    private  int mlVeske; 

    public Nocco() {
        this("Limon del sol", 105, 330);
    }

    public Nocco(String navn, int mgKoffein, int dlVeske) {
        this.navn = navn;
        this.mgKoffein = mgKoffein;
        this.mlVeske = mlVeske;
    }

    public int getMgKoffein() {
        return mgKoffein;
    }

    public void setMgKoffein(int mgKoffein) {
        this.mgKoffein = mgKoffein;
    }

    public int getDlVeske() {
        return mlVeske;
    }

    public void setDlVeske(int dlVeske) {
        this.mlVeske = dlVeske;
    }

    @Override
    public String toString() {
        return "Nocco";
    }

}
