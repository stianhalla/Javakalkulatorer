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
public class KaffeKopp {
    private String kaffeMerke;
    private int mgKoffein;
    private  int mlVeske; 

    public KaffeKopp(String kaffeMerke, int mgKoffein, int mlVeske) {
        this.kaffeMerke = kaffeMerke;
        this.mgKoffein = mgKoffein;
        this.mlVeske = mlVeske;
    }

    public KaffeKopp() {
        this("Vanelig", 80, 200); 
    }
    
    public String getKaffeMerke() {
        return kaffeMerke;
    }

    public void setKaffeMerke(String kaffeMerke) {
        this.kaffeMerke = kaffeMerke;
    }

    public int getMgKoffein() {
        return mgKoffein;
    }

    public void setMgKoffein(int mgKoffein) {
        this.mgKoffein = mgKoffein;
    }

    public int getMlVeske() {
        return mlVeske;
    }

    public void setMlVeske(int mlVeske) {
        this.mlVeske = mlVeske;
    }

    @Override
    public String toString() {
        return "KaffeKopp";
    }
    
    
    
}
