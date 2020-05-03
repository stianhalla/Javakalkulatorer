package annet.annimasjon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnnimasjonsTest extends Application {
    public static final int WIN_X = 300;
    public static final int WIN_Y = 275;


    @Override
    public void start(Stage primaryStage) throws Exception{
        // Oppretter root panel
        BorderPane root = new BorderPane();

        AnnimasjonsPane annimasjonsPane = new AnnimasjonsPane();
        root.setCenter(annimasjonsPane);

        // Setter opp vinduet
        Scene scene = new Scene(root, WIN_X, WIN_Y);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.show();

        annimasjonsPane.startAnnimasjon();

        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP : annimasjonsPane.gåOpp(); break;
                case DOWN: annimasjonsPane.gåNed(); break;
                case RIGHT: annimasjonsPane.gåHøyre(); break;
                case LEFT: annimasjonsPane.gåVenstre(); break;
                default:
                    System.out.println(keyEvent.getCode());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}

class AnnimasjonsPane extends Pane {
    public static final int FART = 1;
    public Bounds øvreVegg = new BoundingBox(0,0, AnnimasjonsTest.WIN_X, 0);

    Bil bil = new Bil();

    public AnnimasjonsPane() {
        getChildren().add(bil.node);
    }

    public void startAnnimasjon() {
        Timeline annimasjon = new Timeline(new KeyFrame(Duration.millis(12), actionEvent -> {
           System.out.println(sjekkKollisjon());
            bil.oppdater();
            bil.render();
            if(sjekkKollisjon()) {
                bil.setHastighet(0,0);
            }

        }));
        annimasjon.setCycleCount(Timeline.INDEFINITE);
        annimasjon.play();
    }

    public void gåOpp() {
        bil.setHastighet(0,-FART);
    }

    public void gåNed() {
        bil.setHastighet(0,FART);

    }

    public void gåHøyre() {
        bil.setHastighet(FART,0);

    }

    public void gåVenstre() {
        bil.setHastighet(-FART,0);
    }

    public boolean sjekkKollisjon() {
        System.out.println();
        return bil.node.getBoundsInParent().intersects(øvreVegg);
    }
}

abstract class Sprite {
    public Node node;
    protected Point2D posisjon;
    protected Point2D hastighet;

    public Sprite() {
        this(0,0,0,0);
    }

    public Sprite(double posx, double posy, double hastx, double hasty) {
        this.posisjon = new Point2D(posx,posy);
        this.hastighet = new Point2D(hastx, hasty);
    }

    public Point2D getPosisjon() {
        return posisjon;
    }

    public void setPosisjon(double x, double y) {
        this.posisjon = new Point2D(x, y);
    }

    public void setHastighet(double x, double y) {
        this.hastighet = new Point2D(x, y);
    }


    public void oppdater() {
        this.posisjon = posisjon.add(this.hastighet);
    }

    public void render() {
        node.setTranslateX(posisjon.getX());
        node.setTranslateY(posisjon.getY());
    }

}

class Bil extends Sprite {

    public Bil() {
        super();
        node = new Rectangle(50,50);
    }
}
