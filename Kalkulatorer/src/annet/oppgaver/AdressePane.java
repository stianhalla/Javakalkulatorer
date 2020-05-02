package annet.oppgaver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class AdressePane extends GridPane {

    // Inputs
    TextField navn;
    TextField gate;
    TextField by;
    TextField fylke;
    TextField postnummer;

    public AdressePane(){
        setUp();
    }

    private void setUp() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);

        navn = new TextField();
        navn.setPrefWidth(200);
        gate = new TextField();
        gate.setPrefWidth(200);
        by = new TextField();
        by.setPrefWidth(100);
        fylke = new TextField();
        fylke.setPrefWidth(30);
        postnummer = new TextField();
        postnummer.setPrefWidth(50);

        Label lbNavn = new Label("Navn");
        Label lbGate = new Label("Gate");
        Label lbBy = new Label("By");
        Label lbFylke = new Label("Fylke");
        Label lbZip = new Label("Zip");

        // Rad 1
        this.add(lbNavn, 0,0);
        this.add(navn, 1,0);

        // Rad 2
        this.add(lbGate, 0,1);
        this.add(gate, 1,1);

        // Rad 3
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(by, lbFylke, fylke, lbZip, postnummer);
        this.add(lbBy, 0,2);
        this.add(hBox, 1,2);

        // Rad 4
        this.add(getButtons(),1,3);

    }

    private HBox getButtons() {
        HBox hBox = new HBox(2);
        hBox.setAlignment(Pos.CENTER);

        Button add = new Button("Legg til");
        Button first = new Button("Første");
        Button next = new Button("Neste");
        Button previous = new Button("Forgje");
        Button last = new Button("Siste");

        hBox.getChildren().addAll(add, first, next, previous, last);
        return hBox;
    }
}
