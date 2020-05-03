package kalkulatorer.macro;

import java.io.Serializable;

public class Vektor  implements Serializable {
    double x;
    double y;

    public Vektor(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vektor(Vektor vektor) {
        this(vektor.getX(), vektor.getY());
    }

    // Eks: utregning av ny posisjon (bevegelse)
    public Vektor add(Vektor v) {
        x += v.x;
        y += v.y;
        return this;
    }

    // Eks: utregning av vector mellom to punkter
    public void sub(Vektor v) {
        x -= v.x;
        y -= v.y;
    }

    // Eks: gjøre en vector større eller mindre (hastighet)
    public void multi(double n) {
        x *= n;
        y *= n;
    }

    // Eks: gjøre en vector større eller mindre (hastighet)
    public void div(double n) {
        x /= n;
        y /= n;
    }

    // Lengde av vector (magnitude)
    public double mag(){
        return Math.sqrt(x*x + y*y);
    }

    // Gjør lengden av vektoren til 1 for å enklere å skalere (normalixe()*50)
    public void normalize() {
        double m = mag();
        div(m);
    }

    // Setter størelsen på vektoren
    public void setMag(double n) {
        normalize();
        multi(n);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}



