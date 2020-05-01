package kalkulatorer.macro;

import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Macro extends Pane {
    private static double LBS = 2.2046226218;
    private static double PROTEIN_PER_LBS = 1.1;
    private static double LOW_FAT = 0.3;
    private static double MODERATE_FAT = 0.4;
    private static double HIGH_FAT = 0.6;

    private String[] dietTypes = {"Low-fat", "Moderate-Fat", "High-fat"};

    private TextField tfKg = new TextField();
    private TextField tfKcal = new TextField();

    private Label lbCarbs = new Label();
    private Label lbProtein = new Label();
    private Label lbFat = new Label();

    private ComboBox<String> cbDiet = new ComboBox<>();

    public Macro() {
        BorderPane root = new BorderPane();

        tfKg.setPromptText("KG");
        tfKg.setPrefWidth(100);

        tfKcal.setPromptText("KCAL");
        tfKcal.setPrefWidth(100);

        Button btSubmit = new Button("Beregn");

        cbDiet.getItems().addAll(dietTypes);
        cbDiet.setPrefWidth(100);
        cbDiet.getSelectionModel().selectFirst();

        HBox input = new HBox(10);
        input.setPadding(new Insets(10,10,10,10));
        input.getChildren().addAll(tfKg, tfKcal, cbDiet, btSubmit);
        root.setTop(input);

        VBox output = new VBox(10);
        output.setPadding(new Insets(10,10,10,10));
        output.getChildren().addAll(lbProtein, lbCarbs, lbFat);
        root.setCenter(output);

        getChildren().add(root);

        btSubmit.setOnAction(e -> calculateMacros());
    }

    private static int calculateCarbs(int kcal, int fat, int protein) {
        return (int) Math.round((kcal - fat * 9 - protein * 4)/4.0);
    }
    private static int calculateProtein(int kg) {
        return (int)Math.round(kg * LBS * PROTEIN_PER_LBS) ;
    }
    private static int calculateFat(int kcal, double fatamount) {
        return (int)Math.round((kcal * fatamount)/9.0);
    }

    private void calculateMacros() {
        // TODO: feilhåndtering
        int kcal = 0;
        int kg = 0;

        try {
            kcal = Integer.parseInt(tfKcal.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("feil i kaloriene");
        }

        try {
            kg = Integer.parseInt(tfKg.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("feil i kg");
        }


        double fatamount = cbDiet.getSelectionModel().getSelectedIndex() == 0 ? LOW_FAT : cbDiet.getSelectionModel().getSelectedIndex() == 1 ? MODERATE_FAT : HIGH_FAT;

        int fat = calculateFat(kcal, fatamount);
        int protein = calculateProtein(kg);
        int carbs = calculateCarbs(kcal, fat, protein);

        lbCarbs.setText("Carbs: " + carbs +"g");
        lbFat.setText("Fat: " + fat + "g");
        lbProtein.setText("Protein: " + protein + "g");
    }
}
