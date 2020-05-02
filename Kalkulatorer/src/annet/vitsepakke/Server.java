/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.vitsepakke;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drizz
 */
public class Server {
    
    public static final String HOST = "localhost";
    public static final int PORT = 9091;
    
    private static ObjectOutputStream tilKlient;
    private static ObjectInputStream fraKlient; 
    
    private static String[] vitseTab = {
        "Alle barna satt rundt bålet. Unntatt Bitten,hun satt i midten",
        "Hvorfor går svenskene alltid med hendene i lomma? De vil ikke at noen skal se at ikke alle fingrene er like lange..",
        "Hørt om han som skulle opptre, men så forsov han seg og kom ikke opp før halv fire?",
        "Hva får du hvis du putter en hund i en badstue? En hot dog.",
        "Hva heter muslimenes favorittkomiker? Halal Eia.",
        "Hvorfor liker ikke Knerten å sove på magen? Han liker ikke å våkne opp på mårrakvisten.",
        "Vet du hva det står på utgangsdøra til Sædbanken? Takk for at du kom.",
        "Hva sa den ene snømannen til den andre? “Er det bare meg eller lukter det litt gulrot her?",
        "Har du hørt om de to eplene som ble stoppet i døra av en eplenektar?",
        "Hva slags musikk hører osten på? Ostepop.",
        "Hørt om selen som var så fryktelig selopptatt?",
        "Hvilken hunderase drikker mest vann? Olden Retriever."
    };
    
    
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Server startet.");
            System.out.println("Venter på klient...");
            //Evig mange klienter 
            while(true){
                Socket socket = ss.accept();
                System.out.println("Koblet til klient.");
                //Lar en egen tråd håmdtere en klient 
                Thread tråd1 = new Thread(()->{
                    try {
                        tilKlient = new ObjectOutputStream(socket.getOutputStream());
                        fraKlient = new ObjectInputStream(socket.getInputStream());
                        //Spør om klienten vil høre en vits 
                        tilKlient.writeUTF("Vil du høre en vits (skriv ja eller nei)?");
                        //if(!fraKlient.readUTF().equalsIgnoreCase("nei")){
                            System.out.println(fraKlient.readUTF());
                            tilKlient.writeUTF(vitseTab[new Random().nextInt(vitseTab.length)]);
                            System.out.println("Sender vits...");
                        //}
                        //Lukker og tømmer shit
                        tilKlient.flush();
                        tilKlient.close();
                        fraKlient.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }                    
                });
                tråd1.start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
}
