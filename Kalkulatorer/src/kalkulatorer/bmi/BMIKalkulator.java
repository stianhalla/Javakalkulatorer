/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.bmi;

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
 *
 * @author drizz
 */
public class BMIKalkulator extends Pane{
    private TextField tfVekt = new TextField();
    private TextField tfHoyde = new TextField();
    
    private TextField tfAlder = new TextField();      
    private ComboBox<String> cbKjonn = new ComboBox<>();
    
    private String[] kjonn = {"Han","Hun"};

    private Label lbKMI = new Label();

    
    public BMIKalkulator(){
        BorderPane hoved = new BorderPane();
        
        tfVekt.setPromptText("KG");
        tfVekt.setPrefWidth(100);

        tfAlder.setPromptText("Hoyde");
        tfAlder.setPrefWidth(100);
        
        tfHoyde.setPromptText("Alder");
        tfHoyde.setPrefWidth(100);
        
        
        Button btSubmit = new Button("Beregn");
        
        cbKjonn.getItems().addAll(kjonn);
        cbKjonn.setPrefWidth(100);
        cbKjonn.getSelectionModel().selectFirst();
        
        HBox input = new HBox(10);
        input.setPadding(new Insets(10,10,10,10));
        input.getChildren().addAll(tfVekt, tfHoyde, tfAlder,cbKjonn, btSubmit);
        hoved.setTop(input);

        VBox output = new VBox(10);
        output.setPadding(new Insets(10,10,10,10));
        output.getChildren().add(lbKMI);
        hoved.setCenter(output);

        getChildren().add(hoved);

        btSubmit.setOnAction(e -> calculateBMI());
        
    }
    
        private void calculateBMI(){
            lbKMI.setText("KMI: " + "test" + "g");
        }
}
