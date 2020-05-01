/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.koffein;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import kalkulatorer.KaffeKopp;
import kalkulatorer.Nocco;

/**
 *
 * @author drizz
 */
public class KoffeinDødlighetPanel extends BorderPane{
    
    //Paneler 
    HBox tp = new HBox(5);
    FlowPane cp = new FlowPane(5,5);
    Label bp = new Label(); 
    
    //Drikker 
    private Object[] drikker = {
        new KaffeKopp(),
        new Nocco()
    };
    
    //noder 
    private ComboBox<Object> cBoxDrikke = new ComboBox<>(); 
    TextField tfVekt = new TextField();
    Button btnBeregn = new Button("Beregn");
    
    public KoffeinDødlighetPanel(){
        tp.setAlignment(Pos.CENTER);
        cp.setAlignment(Pos.CENTER);
        bp.setAlignment(Pos.CENTER);
        
        //Top panel
        cBoxDrikke.getItems().addAll(drikker); 
        cBoxDrikke.setPromptText("Velg drikke");
        setTop(cBoxDrikke);
        
        //Senter panel
        btnBeregn.setOnAction(e -> beregnDødlihet());
        cp.getChildren().addAll(new Label("Oppgi din vekt i kg"), tfVekt, btnBeregn);
        setCenter(cp);
        //Bunn panel 
        setBottom(bp);
    }
    
    private void beregnDødlihet(){
        if(cBoxDrikke.getSelectionModel().getSelectedItem() instanceof KaffeKopp){
            KaffeKopp kaffeKopp = (KaffeKopp) cBoxDrikke.getSelectionModel().getSelectedItem();
            int dødligMengeMg = 175 * Integer.parseInt(tfVekt.getText()); 
            int antKopper = dødligMengeMg / kaffeKopp.getMgKoffein();
            bp.setText("Dødelig dose: " + antKopper + " kopper kaffe på en dag");
        }else{
            Nocco nocco = (Nocco) cBoxDrikke.getSelectionModel().getSelectedItem();
            int dødligMengeMg = 175 * Integer.parseInt(tfVekt.getText()); 
            int antKopper = dødligMengeMg / nocco.getMgKoffein();
            bp.setText("Dødelig dose: " + antKopper + " bokser på en dag");
        }
        
    }
}
