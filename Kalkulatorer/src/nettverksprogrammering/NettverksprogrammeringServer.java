/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nettverksprogrammering;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.imageio.IIOException;

/**
 *
 * @author finn
 */
public class NettverksprogrammeringServer extends Application {
    private ObjectOutputStream outputToClient;
    private ObjectInputStream inputFromClient;
            
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*TextArea ta = new TextArea();
        System.out.println("1");
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        System.out.println("2");
        primaryStage.show();*/
        
        new Thread(() -> {
            try {
                
                ServerSocket serverSocket = new ServerSocket(8000);
                
                
                /*Platform.runLater(() ->
                        ta.appendText("Server startet " + new Date() + "\n"));*/
                System.out.println("Server startet " + new Date());

                while (true) {
                    Socket socket = serverSocket.accept();
                    outputToClient = new ObjectOutputStream(socket.getOutputStream());
                    inputFromClient = new ObjectInputStream(socket.getInputStream());
                    
                    String sFromClient = (String)inputFromClient.readObject();

                    // dele opp string fra Client
                    // [0] = hvilken kalkulator
                    // [1] = Hvilken type kalkulator
                    // resten er det som brukles av kalkulatoren
                    String[] result = sFromClient.split(";");
                    String sAnswerCalculator = "";
                    
                    switch(result[1]) {
                        case "Liter":
                            sAnswerCalculator = calculateLiter(result[2], result[3], result[4]);
                            break;
                        case "Meter":
                            sAnswerCalculator = calculateMeter(result[2], result[3], result[4]);
                            break;
                                
                    }
                    System.out.println(sAnswerCalculator);
                    outputToClient.writeObject(sAnswerCalculator);
                    
                    System.out.println(sFromClient);
                }
            }
            catch(ClassNotFoundException e) {
                System.out.println(e.toString());
            }
            catch(IOException ex) {
                System.out.println(ex.toString());
            }
        }).start();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public String calculateMeter(String sAntall, String sValg1, String sValg2) {
        double dMeter = 1.0;
        double dFot   = 0.3;
        double dFavn  = 1.83;
        double dYard  = 0.91;
        double dTomme = 0.03;
    
        double antall = Double.parseDouble(sAntall);
        String meter1 = sValg1;
        String meter2 = sValg2;
        double value1 = 0.0;
        double value2 = 0.0;
                
        switch(meter1) {
            case "Meter":
                value1 = dMeter;
                break;
            case "Fot":
                value1 = dFot;
                break;
            case "Favn":
                value1 = dFavn;
                break;
            case "Yard":
                value1 = dYard;
                break;
            case "Tomme":
                value1 = dTomme;
                break;
        }
        
        switch(meter2) {
            case "Meter":
                value2 = dMeter;
                break;
            case "Fot":
                value2 = dFot;
                break;
            case "Favn":
                value2 = dFavn;
                break;
            case "Yard":
                value2 = dYard;
                break;
            case "Tomme":
                value2 = dTomme;
                break;
        }
        
        double dSvar = antall*value1*value2;
        return ("" + dSvar);
    }
    
    public String calculateLiter(String sAntall, String sValg1, String sValg2) {
        double dLiter      = 1.0;
        double dGallon     = 0.2642;
        double dPint       = 2.113;
        double dFluidDram  = 270.5;
        double dBarrel     = 0.00629;
        
        double antall = Double.parseDouble(sAntall);
        String meter1 = sValg1;
        String meter2 = sValg2;
        double value1 = 0.0;
        double value2 = 0.0;
                
        switch(meter1) {
            case "Liter":
                value1 = dLiter;
                break;
            case "Gallon":
                value1 = dGallon;
                break;
            case "Pint":
                value1 = dPint;
                break;
            case "Fluid dram":
                value1 = dFluidDram;
                break;
            case "Barrel":
                value1 = dBarrel;
                break;
        }
        
        switch(meter2) {
            case "Liter":
                value2 = dLiter;
                break;
            case "Gallon":
                value2 = dGallon;
                break;
            case "Pint":
                value2 = dPint;
                break;
            case "Fluid dram":
                value2 = dFluidDram;
                break;
            case "Barrel":
                value2 = dBarrel;
                break;
        }
        
        double dSvar = antall*value1*value2;
        return ("" + dSvar);
    }
}
