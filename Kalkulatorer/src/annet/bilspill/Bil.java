/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.bilspill;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.print.Collation;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author drizz
 * En enkel sprite klasse 
 */
public class Bil {
    
    public static final int RADIUS = SpillBrett.PIXEL / 2; 
    public Node node;
    
    public static double boost = 2;
    public static Point2D opp = new Point2D(0, boost * -1);
    public static Point2D ned = new Point2D(0, boost);
    public static Point2D venstre = new Point2D(-1 * boost, 0);
    public static Point2D høyre = new Point2D(boost, 0);
    public static Point2D stille = new Point2D(0, 0);
    
    
    private Point2D posisjon;
    private Point2D hastighet;

    public Bil(double posx, double posy, Point2D rettning) {
        posisjon = new Point2D(posx, posy);
        hastighet = rettning;
        
        node = new Circle(RADIUS);
        node.setTranslateX(posx);
        node.setTranslateY(posy);
    }
    
    public void oppdater(){
        posisjon = posisjon.add(hastighet); 
    }
    
    public void render(){
        node.setTranslateX(posisjon.getX());
        node.setTranslateY(posisjon.getY());
    }
    
    public void sjekk(TidBrukt tb){
        //Sjekker krasj med vegger 
        for(Rectangle2D r2 : SpillBrett.veggHitbokser)
            if(getHitboks().intersects(r2)){
                setPosisjon(posisjon.subtract(hastighet));
                setHastighet(stille);
            }    
        //Sjekker om bil er i mål
        for(Rectangle r : SpillBrett.mål)
            if(r.getBoundsInParent().intersects(node.getBoundsInParent())){
                System.out.println("Du er i mål!");
                    tb.setFerdig(true);
                    setIStart();
            }  
        //Sjekker om boost 
        for(Rectangle r : SpillBrett.boost){
            if(r.getBoundsInParent().intersects(node.getBoundsInParent())){
                if(r.getFill() == Color.YELLOW){
                    addBoost();
                    r.setFill(Color.WHEAT);
                }    
            }
        }
    }
    
    public Rectangle2D getHitboks(){
        return new Rectangle2D(posisjon.getX() - RADIUS, posisjon.getY() - RADIUS , RADIUS * 2, RADIUS * 2 );
    }
   
    public boolean krash(Rectangle2D r)
    {
        return r.intersects( this.getHitboks() );
    }

    public Point2D getPosisjon() {
        return posisjon;
    }

    public void setPosisjon(Point2D posisjon) {
        this.posisjon = posisjon;
    }

    public Point2D getHastighet() {
        return hastighet;
    }

    public void setHastighet(Point2D hastighet) {
        this.hastighet = hastighet;
    }

    private void setIStart() {
        setPosisjon(SpillBrett.START_POS);
        setHastighet(stille);
        boost = 2;
        opp = new Point2D(0, boost * -1);
        ned = new Point2D(0, boost);
        venstre = new Point2D(-1 * boost, 0);
        høyre = new Point2D(boost, 0);
        for(Rectangle r : SpillBrett.boost){
            r.setFill(Color.YELLOW);
        }
    }
    
    public void addBoost(){
        boost+= 2;
        opp = new Point2D(0, boost * -1);
        ned = new Point2D(0, boost);
        venstre = new Point2D(-1 * boost, 0);
        høyre = new Point2D(boost, 0);
    }
    
    
}
