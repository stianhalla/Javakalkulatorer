/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.konvertering;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author finn
 */
public class ClassKonvertering extends Pane {
    private TextField value1 = new TextField("1");
    private TextField svarMeter = new TextField();

    private ComboBox<String> cbConvertMeter = new ComboBox<>();
    private ComboBox<String> cbConvertMeter2 = new ComboBox<>();
    private String cb1 = "Meter";
    private String cb2 = "Fot";
    private String cb3 = "Favn";
    private String cb4 = "Yard";
    private String cb5 = "Tomme";
    
    private TextField value2 = new TextField("1");
    private TextField svarLiter = new TextField();
        
    private ComboBox<String> cbConvertLiter = new ComboBox<>();
    private ComboBox<String> cbConvertLiter2 = new ComboBox<>();
    
    private String cb6 = "Liter";
    private String cb7 = "Gallon";
    private String cb8 = "Pint";
    private String cb9 = "Fluid dram";
    private String cb10 = "Barrel";
    
    private TextField nettverk = new TextField();
    Button nettverkButton = new Button("Beregn");
    ObjectOutputStream toServer = null;
    ObjectInputStream fromServer = null;
    
    String sendToServer = "convert;";
    
    public ClassKonvertering() {
        BorderPane root = new BorderPane();

        Label meter = new Label("Meter");
        
        cbConvertMeter.getItems().addAll(cb1);
        cbConvertMeter.setValue(cb1);
        cbConvertMeter2.getItems().addAll(cb2,cb3,cb4,cb5);
        cbConvertMeter2.setValue(cb1);
        
        Button btSubmitMeter = new Button("Beregn");
        
        HBox input = new HBox(10);
        input.setPadding(new Insets(10,10,10,10));
        input.getChildren().addAll(meter, value1, cbConvertMeter, cbConvertMeter2, btSubmitMeter, svarMeter);
        
        cbConvertLiter.getItems().addAll(cb6);
        cbConvertLiter.setValue(cb6);
        cbConvertLiter2.getItems().addAll(cb7,cb8,cb9,cb10);
        cbConvertLiter2.setValue(cb7);
        
        Button btSubmitLiter = new Button("Beregn");
        
        Label liter = new Label("Liter");
        
        HBox input2 = new HBox(10);
        input2.setPadding(new Insets(10,10,10,10));
        input2.getChildren().addAll(liter, value2, cbConvertLiter, cbConvertLiter2, btSubmitLiter, svarLiter);
        
        HBox input3 = new HBox(10);
        input3.setPadding(new Insets(10,10,10,10));
        input3.getChildren().addAll(nettverk, nettverkButton);

        root.setTop(input);
        root.setCenter(input2);
        root.setBottom(input3);

        getChildren().add(root);

        btSubmitMeter.setOnAction(e -> calculateMeter());
        btSubmitLiter.setOnAction(e -> calculateLiter());
    }
    
    public void connectToServer(String sendToServer, String kalkulator) {
        System.out.println(sendToServer);
        System.out.println(kalkulator);
        String svarFraServer = "";
        
        try {
            String host = "localhost";
            Socket socket = new Socket(host, 8000);
            
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            toServer.writeObject(sendToServer);
            
            
            toServer.flush();
            
            svarFraServer = (String)fromServer.readObject();
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        switch(kalkulator) {
            case "Liter":
                svarLiter.setText(svarFraServer);
                break;

            case "Meter":
                svarMeter.setText(svarFraServer);
                break;
        }
    }
    
    public void calculateLiter() {
        System.out.println("Liter");
        sendToServer += "Liter;";
        sendToServer += value2.getText() + ";";
        sendToServer += cbConvertLiter.getValue() + ";";
        sendToServer += cbConvertLiter2.getValue();
        System.out.println("LiterEnd");
        connectToServer(sendToServer, "Liter");
        
        // sette String tom så den ikke legger på mer info
        sendToServer = "convert;";
    }
    
     public void calculateMeter() {
         System.out.println("Meter");
        sendToServer += "Meter;";
        sendToServer += value1.getText() + ";";
        sendToServer += cbConvertMeter.getValue() + ";";
        sendToServer += cbConvertMeter2.getValue();
        System.out.println("MeterEnd");
        connectToServer(sendToServer, "Meter");
        
        // sette String tom så den ikke legger på mer info
        sendToServer = "convert;";
     }
    
    /*public void calculateLiter() {
        double antall = Double.parseDouble(value2.getText());
        String meter1 = cbConvertLiter.getValue();
        String meter2 = cbConvertLiter2.getValue();
        double value1 = 0.0;
        double value2 = 0.0;
        int type = 0;
        int type2 = 0;
                
        switch(meter1) {
            case "Liter":
                value1 = dLiter;
                type = 1;
                break;
            case "Gallon":
                value1 = dGallon;
                type = 2;
                break;
            case "Pint":
                value1 = dPint;
                type = 3;
                break;
            case "Fluid dram":
                value1 = dFluidDram;
                type = 4;
                break;
            case "Barrel":
                value1 = dBarrel;
                type = 5;
                break;
        }
        
        switch(meter2) {
            case "Liter":
                value2 = dLiter;
                type = 1;
                break;
            case "Gallon":
                value2 = dGallon;
                type = 2;
                break;
            case "Pint":
                value2 = dPint;
                type = 3;
                break;
            case "Fluid dram":
                value2 = dFluidDram;
                type = 4;
                break;
            case "Barrel":
                value2 = dBarrel;
                type = 5;
                break;
        }
        
        double dSvar = antall*value1*value2;
        System.out.println(dSvar+"");
        svar2.setText(""+dSvar);
    }
    
    public void calculateMeter() {
        double antall = Double.parseDouble(value1.getText());
        String meter1 = cbConvertMeter.getValue();
        String meter2 = cbConvertMeter2.getValue();
        double value1 = 0.0;
        double value2 = 0.0;
        int type = 0;
        int type2 = 0;
                
        switch(meter1) {
            case "Meter":
                value1 = dMeter;
                type = 1;
                break;
            case "Fot":
                value1 = dFot;
                type = 2;
                break;
            case "Favn":
                value1 = dFavn;
                type = 3;
                break;
            case "Yard":
                value1 = dYard;
                type = 4;
                break;
            case "Tomme":
                value1 = dTomme;
                type = 5;
                break;
        }
        
        switch(meter2) {
            case "Meter":
                value2 = dMeter;
                type2 = 1;
                break;
            case "Fot":
                value2 = dFot;
                type2 = 2;
                break;
            case "Favn":
                value2 = dFavn;
                type2 = 3;
                break;
            case "Yard":
                value2 = dYard;
                type2 = 4;
                break;
            case "Tomme":
                value2 = dTomme;
                type2 = 5;
                break;
        }
        
        double dSvar = antall*value1*value2;
        System.out.println(dSvar+"");
        svar.setText(""+dSvar);
    }*/
}
