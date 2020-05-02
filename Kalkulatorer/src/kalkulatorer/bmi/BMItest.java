/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.bmi;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 *
 * @author mussal
 */
public class BMItest extends Application{
    public static final int WIN_X = 500;
    public static final int WIN_Y = 300;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Oppretter root panel
        BorderPane root = new BorderPane();

        BMIKalkulator bmiKalk = new BMIKalkulator();
        root.setCenter(bmiKalk);

        // Setter opp vinduet
        Scene scene = new Scene(root, WIN_X, WIN_Y);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}