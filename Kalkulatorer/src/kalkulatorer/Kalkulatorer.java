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
import kalkulatorer.bmi.BMIKalkulator;

/**
 *
 * @author drizz
 */
public class Kalkulatorer extends Application{
    final static int VIN_X = 500; 
    final static int VIN_Y = 500; 
    
    //1. Kalkulator paneler
    KoffeinDødlighetPanel kdp; 
    Macro macroPanel; 
    ClassKonvertering konverter;
    BMIKalkulator bmiKalk;
    
    //2. Knapper 
    Button btnKoffein = new Button("Koffein");
    Button btnMacro = new Button("Macro");
    Button btnKonverter = new Button("Konverter");
    Button btnBMI = new Button("BMI kalkulator");
    
    @Override
    public void start(Stage vindu) throws Exception {
        //3. set handling på kanpp
        btnKoffein.setOnAction(e -> {
            kdp = new KoffeinDødlighetPanel();
            visKalkulator(kdp, 200, 200, "Koffein dødelighet kalkulator");
                });
        btnMacro.setOnAction(e -> {
            macroPanel = new Macro();
            visKalkulator(macroPanel, VIN_X, VIN_Y, "Macro kalkulator");
                });
        btnKonverter.setOnAction(e -> {
            konverter = new ClassKonvertering();
            visKalkulator(konverter, VIN_X+150, VIN_Y, "Konverterings kalkulator");
                });
        btnBMI.setOnAction(e -> {
            bmiKalk = new BMIKalkulator();
            visKalkulator(bmiKalk, VIN_X+150, VIN_Y, "BMI kalkulator");
                });        
        //Hoved Panel for visning av knapper 
        FlowPane hovedPanel = new FlowPane(5,5);
        hovedPanel.setAlignment(Pos.CENTER);
        
        //4. Legger ut knapper 
        hovedPanel.getChildren().addAll(btnKoffein, btnMacro, btnKonverter, btnBMI); 
        
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