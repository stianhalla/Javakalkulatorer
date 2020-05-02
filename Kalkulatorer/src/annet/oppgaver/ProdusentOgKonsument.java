/*
 * 
    • To tråder skal samarbeide, «Produsent» og «Konsument».
    •Produsent produserer tall (f.eks. Fibonacci-tallene: 0, 1 ,1, 2, 3, 5, 8, 13, 21, 34,
    55, 89, osv).
    • Konsument skal få tallene og skrive dem ut.
    • Tallene skal overføres ved hjelp av en tabell som begge har tilgang til. Produsent
    legger inn tall dersom det er ledig plass, Konsument henter ut dersom det er nye
    tall der.
    • Lag en tabell med 10 posisjoner, bruk den «sirkulært», gjerne kalt ringbuffer. Etter
    at produsenten har lagt noe i posisjon 0, 2, ..., 9, legges neste verdi i posisjon 0 –
    dersom den nå er ledig!
    • Kan løses på flere måter, både med synchronized og med wait/notify
 */
package annet.oppgaver;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drizz
 */
public class ProdusentOgKonsument {
    
    //Kø mønster, først inn først u. Og er en tråd sikker datastruktur
    private static BlockingQueue<Integer> kø = new ArrayBlockingQueue<>(10); 
   
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                produsent();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProdusentOgKonsument.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                konsument();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProdusentOgKonsument.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        t1.start();
        t2.start();

    }
    
    private static void produsent() throws InterruptedException{
        Random random = new Random();
        while(true){
            //Kommer ikke til å legge til mer enn 10 verdier 
            kø.put(random.nextInt(100));
        }
    }
    
    private static void konsument() throws InterruptedException{
        Random random = new Random();
        while(true){
            Thread.sleep(100);
            if(random.nextInt(10) == 0){
                Integer verdi = kø.take();
                System.out.println("Verdi tatt ut av kø: " + verdi + "; kø størrelse er: " + kø.size());
            }                
        }
    }
}
