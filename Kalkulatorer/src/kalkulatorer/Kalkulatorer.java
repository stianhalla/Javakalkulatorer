/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kalkulatorer.koffein.KoffeinDødlighetPanel;

/**
 *
 * @author drizz
 */
public class Kalkulatorer extends Application{
    final static int VIN_X = 500; 
    final static int VIN_Y = 500; 
    
    //Kalkulator paneler
    KoffeinDødlighetPanel kdp = new KoffeinDødlighetPanel(); 
    
    //Knapper 
    Button btnKoffeinKalk = new Button("Koffein dødlighet"); 
    
    @Override
    public void start(Stage vindu) throws Exception {
        btnKoffeinKalk.setOnAction(e -> visKoffeinkalk());
        
        FlowPane hovedPanel = new FlowPane(5,5);
        hovedPanel.setAlignment(Pos.CENTER);
        
        //Legger ut knapper 
        hovedPanel.getChildren().addAll(btnKoffeinKalk); 
        
        /*Vis GUI*/
        Scene sene = new Scene(hovedPanel, VIN_X, VIN_Y);
        vindu.setScene(sene);
        vindu.setTitle("Kalkulatorer");
        vindu.show();
    }
    
    private void visKoffeinkalk(){
        Stage vindu = new Stage(); 
        Scene sene = new Scene(kdp, VIN_X, VIN_Y); 
        vindu.setTitle("Koffein dødelighet kalkulator");
        vindu.setScene(sene);
        vindu.show();
    }
    
    public static void main(String[] args) {
        launch(args); 
    }
    
}
