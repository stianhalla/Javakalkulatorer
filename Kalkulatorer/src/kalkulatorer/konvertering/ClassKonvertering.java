/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.konvertering;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author finn
 */
public class ClassKonvertering extends Pane {
    /*private static double LBS = 2.2046226218;
    private static double PROTEIN_PER_LBS = 1.1;
    private static double LOW_FAT = 0.3;
    private static double MODERATE_FAT = 0.4;
    private static double HIGH_FAT = 0.6;*/

    //private String[] dietTypes = {"Low-fat", "Moderate-Fat", "High-fat"};

    
    private TextField value1 = new TextField();

    /*private Label lbCarbs = new Label();
    private Label lbProtein = new Label();
    private Label lbFat = new Label();*/
    
    private ComboBox<String> cbConvertMeter = new ComboBox<>();
    private ComboBox<String> cbConvertMeter2 = new ComboBox<>();
    private String cb1 = "Meter";
    private String cb2 = "Fot";
    private String cb3 = "Favn";
    private String cb4 = "Yard";
    private String cb5 = "Tomme";
    
    
    public ClassKonvertering() {
        BorderPane root = new BorderPane();

        Label meter = new Label("Meter");
        
        cbConvertMeter.getItems().addAll(cb1,cb2,cb3,cb4,cb5);
        cbConvertMeter.setValue(cb1);
        cbConvertMeter2.getItems().addAll(cb1,cb2,cb3,cb4,cb5);
        cbConvertMeter2.setValue(cb1);
        
        Button btSubmitMeter = new Button("Beregn");
        
        HBox input = new HBox(10);
        input.setPadding(new Insets(10,10,10,10));
        input.getChildren().addAll(value1, cbConvertMeter, cbConvertMeter2, btSubmitMeter);
        root.setTop(input);
        
        getChildren().add(root);

        /*cbDiet.getItems().addAll(dietTypes);
        cbDiet.setPrefWidth(100);
        cbDiet.getSelectionModel().selectFirst();

        HBox input = new HBox(10);
        input.setPadding(new Insets(10,10,10,10));
        input.getChildren().addAll(tfKg, tfKcal, cbDiet, btSubmitMeter);
        root.setTop(input);

        VBox output = new VBox(10);
        output.setPadding(new Insets(10,10,10,10));
        output.getChildren().addAll(lbProtein, lbCarbs, lbFat);
        root.setCenter(output);

        getChildren().add(root);*/

        btSubmitMeter.setOnAction(e -> calculateMeter());
    }
    
    public void calculateMeter() {
        
    }
}
