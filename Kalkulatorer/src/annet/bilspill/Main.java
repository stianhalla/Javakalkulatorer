/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.bilspill;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author drizz
 */
public class Main extends Application{
    final static int VIN_X = 500; 
    final static int VIN_Y = 525;
    
    private BorderPane hovedPanel;
    private SpillBrett spillebrett;
    private Bil bil;
    private Timeline gameloop;
    private Scene sene;
    private TidBrukt lbTid;
    
    @Override
    public void start(Stage vindu) throws Exception {
        settOppGUI(vindu);
        sene.setOnKeyPressed(e -> settOppSpillKontroll(e));
        startSpill();
    }
    
    public static void main(String[] args) {
        launch(args); 
    }

    private void settOppGUI(Stage vindu) {
        /***Noder***/
        bil = new Bil(SpillBrett.START_POS.getX(), 
                SpillBrett.START_POS.getY(), Bil.stille);
        lbTid = new TidBrukt("Tid brukt: ");
        
        /***Spillebrett***/
        spillebrett = new SpillBrett();
        spillebrett.setPrefSize(SpillBrett.BREDDE, SpillBrett.HØYDE);
        spillebrett.getChildren().add(bil.node); 
        
        /**HovedPanel**/
        hovedPanel = new BorderPane(); 
        hovedPanel.setTop(lbTid);
        hovedPanel.setCenter(spillebrett);

        /*Vis GUI*/
        sene = new Scene(hovedPanel, VIN_X, VIN_Y);
        vindu.setScene(sene);
        vindu.setTitle("GUI Mal");
        vindu.show();
    }

    private void startSpill() {
        //Game loop ca 60fps 
        gameloop = new Timeline(new KeyFrame(Duration.seconds(0.016), e -> {
            //Oppdater
            bil.oppdater();
            //Sjekk
            bil.sjekk(lbTid);
            //Render
            bil.render();
        }));
        gameloop.setCycleCount(Timeline.INDEFINITE);
        gameloop.play();
    }

    private void settOppSpillKontroll(KeyEvent e) {
       if(e.getCode() == KeyCode.UP)
           bil.setHastighet(bil.opp);
       else if(e.getCode() == KeyCode.DOWN)
           bil.setHastighet(bil.ned);
       else if(e.getCode() == KeyCode.LEFT)
           bil.setHastighet(bil.venstre);
       else if(e.getCode() == KeyCode.RIGHT)
           bil.setHastighet(bil.høyre);
    }
    
}
