package annet.annimasjon;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import kalkulatorer.macro.Vektor;

import java.io.*;
import java.util.ArrayList;

public class AnnimasjonsTest extends Application {
    public static final int WIN_X = 300;
    public static final int WIN_Y = 300;

    AnnimasjonsPane annimasjonsPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Oppretter root panel
        BorderPane root = new BorderPane();

        annimasjonsPane = new AnnimasjonsPane();
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
                case ENTER: annimasjonsPane.play(); break;
                default:
                    System.out.println(keyEvent.getCode());
            }
        });

    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file
       annimasjonsPane.lagre();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

class AnnimasjonsPane extends Pane {
    final int BANE[][] = {
            {1, 1,  1,  1,  1},
            {1, 0,  0,  0,  1},
            {1, 0,  0,  0,  1},
            {1, 0,  0,  0,  1},
            {1, 1,  1,  1,  1},
    };

    ArrayList<Shape> shapes = new ArrayList<>();

    public static final int FART = 1;

    Bil bil = new Bil();

    public AnnimasjonsPane() {
        getChildren().add(bil.node);
        settOppBane();
    }

    public void startAnnimasjon() {
        Timeline annimasjon = new Timeline(new KeyFrame(Duration.millis(12), actionEvent -> {
            bil.oppdater();
            bil.render();
            if(kollisjon()) {
                bil.setHastighet(0,0);
                bil.setPosisjon(bil.possisjoner.get(bil.possisjoner.size()-2));
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

    public void settOppBane() {
        for(int rad = 0; rad < 5; rad++ ) {
            for(int kol = 0; kol < 5; kol++) {
                if(BANE[rad][kol] == 1) {
                    Rectangle rectangle = new Rectangle(rad*100 - 100, kol*100 - 100, 100,100);
                    shapes.add(rectangle);
                }
            }
        }
        getChildren().addAll(shapes);
    }

    public boolean kollisjon() {
        for(Shape shape : shapes){
            if(shape.getBoundsInParent().intersects(bil.node.getBoundsInParent()))
                return true;
        }
        return false;
    }

    public void lagre() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("posisjoner.dat")));) {
            output.writeObject(bil.possisjoner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        System.out.println("play");

        Rectangle rect = new Rectangle(50,50);
        rect.setFill(Color.ORANGE);
        getChildren().add(rect);

        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(getDoubleArray(getPosisjoner()));
        polyline.setStroke(Color.ORANGE);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setPath(polyline);
        pathTransition.setNode(rect);
        pathTransition.play();

        getChildren().add(polyline);
    }

    public ArrayList<Vektor> getPosisjoner() {
        ArrayList<Vektor> posisjoner = new ArrayList<>();

        try ( ObjectInputStream input = new ObjectInputStream(new FileInputStream("posisjoner.dat"))) {
            posisjoner = (ArrayList<Vektor>) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return posisjoner;
    }

    public Double[] getDoubleArray(ArrayList<Vektor> arrayList) {
        Double[] pos = new Double[arrayList.size()*2];

        for(int i = 0; i < arrayList.size(); i++) {
            Vektor vektor = arrayList.get(i);
            double x = vektor.getX();
            double y = vektor.getY();
            System.out.println(x +" , " + y);
                pos[i+i] = x + 25;
                pos[i+i+1] = y + 25;
        }

        return pos;
    }
}

abstract class Sprite {
    public Node node;
    protected Vektor posisjon;
    protected Vektor hastighet;
    public ArrayList<Vektor> possisjoner = new ArrayList<>();

    public Sprite() {
        this(1,1,0,0);
    }

    public Sprite(double posx, double posy, double hastx, double hasty) {
        this.posisjon = new Vektor(posx,posy);
        this.hastighet = new Vektor(hastx, hasty);
    }

    public Vektor getPosisjon() {
        return posisjon;
    }

    public void setPosisjon(double x, double y) {
        this.posisjon = new Vektor(x, y);
    }

    public void setPosisjon(Vektor posisjon) {
        this.posisjon = posisjon;
    }

    public void setHastighet(double x, double y) {
        this.hastighet = new Vektor(x, y);
    }


    public void oppdater() {
        this.posisjon = posisjon.add(this.hastighet);
        possisjoner.add(new Vektor(posisjon));
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

