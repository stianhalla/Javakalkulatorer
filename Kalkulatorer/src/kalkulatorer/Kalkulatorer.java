/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kalkulatorer.koffein.KoffeinDødlighetPanel;
import kalkulatorer.konvertering.ClassKonvertering;
import kalkulatorer.macro.Macro;

/**
 *
 * @author drizz
 */
public class Kalkulatorer extends Application{
    final static int VIN_X = 500; 
    final static int VIN_Y = 500; 
    
    //1. Kalkulator paneler
    KoffeinDødlighetPanel kdp = new KoffeinDødlighetPanel(); 
    Macro macroPanel = new Macro(); 
    ClassKonvertering konverter = new ClassKonvertering();
    
    //2. Knapper 
    Button btnKoffein = new Button("Koffein");
    Button btnMacro = new Button("Macro");
    Button btnKonverter = new Button("Konverter");
    
    @Override
    public void start(Stage vindu) throws Exception {
        //3. set handling på kanpp
        btnKoffein.setOnAction(e -> visKalkulator(kdp, VIN_X, VIN_Y, "Koffein dødelighet kalkulator"));
        btnMacro.setOnAction(e -> visKalkulator(macroPanel, VIN_X, VIN_Y, "Macro kalkulator"));
        btnKonverter.setOnAction(e -> visKalkulator(konverter, VIN_X+150, VIN_Y, "Konverterings kalkulator"));
        
        //Hoved Panel for visning av knapper 
        FlowPane hovedPanel = new FlowPane(5,5);
        hovedPanel.setAlignment(Pos.CENTER);
        
        //4. Legger ut knapper 
        hovedPanel.getChildren().addAll(btnKoffein, btnMacro, btnKonverter); 
        
        /*Vis GUI*/
        Scene sene = new Scene(hovedPanel, VIN_X, VIN_Y);
        vindu.setScene(sene);
        vindu.setTitle("Kalkulatorer");
        vindu.show();
    }
    
    private void visKalkulator(Pane panel, int vinx, int viny, String tittel){
        Stage vindu = new Stage(); 
        Scene sene = new Scene(panel, vinx, viny); 
        vindu.setTitle(tittel);
        vindu.setScene(sene);
        vindu.show();
    }
    
    public static void main(String[] args) {
        launch(args); 
    }
}