/*
 * Oppgave 33_06
 */
package annet.oppgaver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author drizz
 * Oppgave fra nettkapittel til boka 
 */
public class Oppgave33_06Klient extends Application {
    public static final int WIN_X = 400;
    public static final int WIN_Y = 150;



    @Override
    public void start(Stage primaryStage) throws Exception{
        // Oppretter root panel
        BorderPane root = new BorderPane();


        AdressePane adressePane = new AdressePane();
        root.setCenter(adressePane);
        BorderPane.setAlignment(adressePane, Pos.CENTER);

        // Setter opp vinduet
        Scene scene = new Scene(root, WIN_X, WIN_Y);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
