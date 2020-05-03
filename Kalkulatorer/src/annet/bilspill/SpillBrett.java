/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.bilspill;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author drizz
 */
public class SpillBrett extends Pane{
    public static final int BREDDE = 500;
    public static final int HØYDE = 500;
    public static final int PIXEL = 50;
    public static final Point2D START_POS = new Point2D(PIXEL * 2 - PIXEL / 2, PIXEL * 2);
    
    public static ArrayList<Rectangle> vegger = new ArrayList<>(); 
    public static ArrayList<Rectangle> mål = new ArrayList<>();
    public static ArrayList<Rectangle> start = new ArrayList<>();
    public static ArrayList<Rectangle> boost = new ArrayList<>();
    public static ArrayList<Rectangle2D> veggHitbokser = new ArrayList<>(); 
    public static final int [][] BANE = {
        {1,1,1,1,1,1,1,1,1,1},
        {1,2,0,0,0,0,0,0,5,1},
        {1,2,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,0,0,1},
        {1,5,0,0,0,3,1,0,0,1},
        {1,0,0,0,0,3,1,0,0,1},
        {1,0,0,1,1,1,1,0,0,1},
        {1,0,5,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,5,0,1},
        {1,1,1,1,1,1,1,1,1,1}
    };
    
    public SpillBrett() {
        byggBane();
        styleVegger();
        styleMål();
        styleStart();
        styleBoost();
        visBane();
    }

    private void byggBane() { 
        for(int rad = 0 ; rad < BANE.length ; rad++ ){
            for (int kol = 0; kol < BANE[0].length ; kol++) {
                if(BANE[kol][rad] == 1){
                    vegger.add(new Rectangle(PIXEL * rad, PIXEL * kol, PIXEL, PIXEL));
                    veggHitbokser.add(new Rectangle2D(PIXEL * rad, PIXEL * kol, PIXEL, PIXEL));
                }
                else if(BANE[kol][rad] == 2){
                   start.add(new Rectangle(PIXEL * rad, PIXEL * kol, PIXEL, PIXEL));
                }
                else if(BANE[kol][rad] == 3){
                   mål.add(new Rectangle(PIXEL * rad, PIXEL * kol, PIXEL, PIXEL));
                }
                else if(BANE[kol][rad] == 5){
                   boost.add(new Rectangle(PIXEL * rad, PIXEL * kol, PIXEL, PIXEL));
                }
            }
            System.out.println();
        }
    }
    
    private void styleVegger(){
        for(Rectangle r : vegger)
            r.setFill(Color.RED);
    }
    
    private void styleBoost(){
        for(Rectangle r : boost)
            r.setFill(Color.YELLOW);
    }
    
    private void styleStart(){
        for(Rectangle r : start)
            r.setFill(Color.CYAN);
    }
    
    private void styleMål(){
        for(Rectangle r : mål)
            r.setFill(Color.CYAN);
    }

    private void visBane() {
        for(Rectangle r : vegger)
            getChildren().add(r);
        for(Rectangle r : mål)
            getChildren().add(r);
        for(Rectangle r : start)
            getChildren().add(r);
        for(Rectangle r : boost)
            getChildren().add(r);
    }
    
}
