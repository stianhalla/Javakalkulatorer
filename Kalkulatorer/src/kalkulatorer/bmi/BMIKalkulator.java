/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.bmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import kalkulatorer.macro.AlertBox;

/**
 *
 * @author drizz
 */
public class BMIKalkulator extends Pane {
    private TextField tfVekt = new TextField();
    private TextField tfHoyde = new TextField();
    
    private TextField tfAlder = new TextField();      
    private ComboBox<String> cbKjonn = new ComboBox<>();
    
    private String[] kjonn = {"Han","Hun"};

    private Label lbKMI = new Label();
    private Label lbRes = new Label();
    
    int port = 8011;
    String host = "localhost";
    ObjectInputStream in;
    ObjectOutputStream out;
    ServerSocket server;
    Socket socket;


    
    public BMIKalkulator(){
        BorderPane hoved = new BorderPane();
        
        tfVekt.setPromptText("KG");
        tfVekt.setPrefWidth(100);

        tfAlder.setPromptText("Alder");
        tfAlder.setPrefWidth(100);
        
        tfHoyde.setPromptText("Hoyde");
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
        output.getChildren().addAll(lbKMI,lbRes);
        hoved.setCenter(output);

        getChildren().add(hoved);

        btSubmit.setOnAction(e -> {
            try{
                calculateBMI();
            } catch (IOException | ClassNotFoundException ex) {
                AlertBox.displayAlertBox("Ingen kontakt med tjener.");
                System.out.println(ex);
            }
        });
        
    }
    
        private void calculateBMI() throws IOException, ClassNotFoundException{
            double kmi;
            double vekt = 0;
            double hoyde = 0;
            int alder = 0;
            boolean klar = true;
            
            try {
                vekt = Double.parseDouble(tfVekt.getText().trim());
            } catch (NumberFormatException ex) {
                klar=false;
                AlertBox.displayAlertBox("Vekt må være oppgitt i kg. eks. 75.0");
                System.out.println(ex);
            }

            try {
                hoyde = Double.parseDouble(tfHoyde.getText().trim());
            } catch (NumberFormatException ex) {
                klar=false;
                AlertBox.displayAlertBox("Hoyde må være tall oppgitt i meter. eks. 1.75");
                System.out.println(ex);
            }
            
            try {
                alder = Integer.parseInt(tfAlder.getText().trim());
            } catch (NumberFormatException ex) {
                klar=false;
                AlertBox.displayAlertBox("Alder må være et heltall større enn 0");
                System.out.println(ex);
            }
            
            String kjønn = cbKjonn.getValue();
            
            if (klar)
                try {
                    PersonIdx person = new PersonIdx(hoyde, vekt, alder, (kjønn == "Hun"));
                    System.out.println(person);

                    socket = new Socket(host, port);
                    out = new ObjectOutputStream(socket.getOutputStream());
                    in = new ObjectInputStream(socket.getInputStream());

                    out.writeObject(person);

                    BMIResultat s = (BMIResultat)(in.readObject());

                    System.out.println(s);
                    lbKMI.setText("KMI: " + s.getKmi());
                    lbRes.setText(s.getMelding());

                    in.close();
                    out.close();
                    socket.close();
                } catch (IllegalArgumentException ex) {
                    AlertBox.displayAlertBox(ex.getMessage());
                }    
        }    
}
