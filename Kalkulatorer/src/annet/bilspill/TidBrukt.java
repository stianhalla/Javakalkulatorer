/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.bilspill;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author drizz
 */
public class TidBrukt extends Label{
    private String tekst; 
    private int tidMilli; 
    private boolean ferdig; 

    public TidBrukt(String tekst) {
        this.tekst = tekst; 
        setText(tekst + 0);
        new Thread(() -> startTid() ).start();
    }

    private void startTid() {
       while(!ferdig){
           try {
               Thread.sleep(1);
               Platform.runLater(() -> {
                   ++tidMilli;
                   visIsek();
               });
           } catch (InterruptedException e) {
               System.out.println(e);
           }
       }
    }

    public boolean isFerdig() {
        return ferdig;
    }

    public void setFerdig(boolean ferdig) {
        this.ferdig = ferdig;
    }
    
    private void visIsek(){
        setText(tekst + tidMilli / 1000.0);
    }
    
}
