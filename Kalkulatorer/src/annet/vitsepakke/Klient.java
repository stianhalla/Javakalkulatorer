/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.vitsepakke;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author drizz
 */
public class Klient extends Application{
    final static int VIN_X = 500; 
    final static int VIN_Y = 500; 
    
    /***Layout***/
    private TextField tfServer;
    private TextField tfInn;
    
    ObjectOutputStream tilServer;
    
    @Override
    public void start(Stage vindu) throws Exception {
        /***Layout***/
        tfServer = new TextField();
        tfServer.setDisable(true);
        tfInn = new TextField();
        tfInn.setOnAction(e -> {
                    try {
                        tilServer.writeUTF(tfInn.getText());
                        System.out.println("Melding sendt..");
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
        });
        BorderPane hovedPanel = new BorderPane(); 
        hovedPanel.setTop(tfServer);
        hovedPanel.setCenter(tfInn);
        
        /*Vis GUI*/
        Scene sene = new Scene(hovedPanel, VIN_X, VIN_Y);
        vindu.setScene(sene);
        vindu.setTitle("GUI Mal");
        vindu.show();
        
        new Thread(() -> {
            try { 
                Socket socket = new Socket(Server.HOST, Server.PORT);
                System.out.println("Koblet til server.");
                tilServer = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream fraServer = new ObjectInputStream(socket.getInputStream());
                System.out.println("Satt opp i/o");
                Platform.runLater(() -> {
                    try {
                        tfServer.setText(fraServer.readUTF());
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });
                System.out.println("Send svar til server");
                Platform.runLater(() -> {
                    try {
                        tfServer.setText(fraServer.readUTF());
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });
            } catch (IOException e) {
                System.out.println(e);
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args); 
    }  
}
