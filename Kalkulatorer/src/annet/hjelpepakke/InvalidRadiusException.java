/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annet.hjelpepakke;

/**
 *
 * @author drizz
 */
public class InvalidRadiusException extends Exception{
    private int radius; 

    public InvalidRadiusException(int radius) {
        super("Invalid radius: " + radius);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
