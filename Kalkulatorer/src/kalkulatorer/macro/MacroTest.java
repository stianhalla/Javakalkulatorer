package kalkulatorer.macro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MacroTest extends Application{
    public static final int WIN_X = 420;
    public static final int WIN_Y = 300;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Oppretter root panel
        BorderPane root = new BorderPane();

        Macro macro = new Macro();
        root.setCenter(macro);

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
