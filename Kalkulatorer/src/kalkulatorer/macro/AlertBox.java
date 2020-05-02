package kalkulatorer.macro;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author Espen
 * Klasse for popup meldinger i JavaFX
 * Når vinduet er oppe vil resten av programmet være låst
 */
public class AlertBox {

    /**
     * Viser fram vinduet og låser resten av programmet
     * til vinduet er lukket
     * @return returnerer true dersom man trykker på ja eller ok knapp
     */
    private static boolean display(Alert alert) {
        Optional<ButtonType> option = alert.showAndWait();
        return option.isPresent() && (option.get().getButtonData() == ButtonType.YES.getButtonData() || option.get().getButtonData() == ButtonType.OK.getButtonData());
    }


    /**
     * Viser fram en vareselboks som låser programmet til man trykker ok eller
     * lukker vinduet
     * @param title tittelen på vinduet
     * @param header overskriften i vinduet
     * @param message melding i vinduet
     * @return returnerer true dersom man trykker ok
     */
    public static boolean displayAlertBox(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        return display(alert);
    }


    public static boolean displayAlertBox(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advarsel");
        alert.setHeaderText(null);
        alert.setContentText(message);

        return display(alert);
    }


    /**
     * Viser fram en bekreftelsesbks som låser programmet til man trykker ja,
     * nei eller lukker vinduet
     * @param title tittelen på vinduet
     * @param header overskriften i vinduet
     * @param message melding i vinduet
     * @return returnerer tru dersom man trykker ja
     */
    public static boolean displayConfirmBox(String title, String header, String message) {
        // setter opp knapper
        ButtonType ja = new ButtonType("JA", ButtonBar.ButtonData.YES);
        ButtonType nei = new ButtonType("NEI", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ja, nei);
        alert.setTitle(title);
        alert.setHeaderText(header);

        return display(alert);
    }

    public static boolean displayConfirmBox(String message) {
        // setter opp knapper
        ButtonType ja = new ButtonType("JA", ButtonBar.ButtonData.YES);
        ButtonType nei = new ButtonType("NEI", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ja, nei);
        alert.setTitle("Bekreft");
        alert.setHeaderText(null);

        return display(alert);
    }

}

