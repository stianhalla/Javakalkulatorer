/*
 * Oppgave 33_06
 */
package annet.oppgaver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author drizz
 * Oppgave fra nettkapittel til boka 
 */
public class Oppgave33_06Klient extends Application {
    public static final int WIN_X = 400;
    public static final int WIN_Y = 300;


    @Override
    public void start(Stage primaryStage) throws Exception{
        // Oppretter root panel
        BorderPane root = new BorderPane();


        AdressePane adressePane = new AdressePane();
        root.setCenter(adressePane);

        // Setter opp vinduet
        Scene scene = new Scene(root, WIN_X, WIN_Y);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    
    public static void main(String[] args) {
        launch(args);
    }


}
