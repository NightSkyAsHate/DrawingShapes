package sample.Utils;

import java.util.Arrays;

public class Trapeciya extends Quad implements IShape {


    public Trapeciya(Point2D[] p) {
        super(p);
    }

    @Override
    public double square() {
        double ab = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[1].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[1].getX(1),2));
        double bc = Math.sqrt(Math.pow(this.p[1].getX(0) - this.p[2].getX(0),2) + Math.pow(this.p[1].getX(1) - this.p[2].getX(1),2));
        double cd = Math.sqrt(Math.pow(this.p[2].getX(0) - this.p[3].getX(0),2) + Math.pow(this.p[2].getX(1) - this.p[3].getX(1),2));
        double ad = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[3].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[3].getX(1),2));
        return ((cd+ab)/2)*Math.sqrt(ad*ad - Math.pow(((ab-cd)*(ab-cd) + ad*ad - bc*bc)/(2*(ab-cd)),2));
    }

    @Override
    public double length() {
        double ab = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[1].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[1].getX(1),2));
        double bc = Math.sqrt(Math.pow(this.p[1].getX(0) - this.p[2].getX(0),2) + Math.pow(this.p[1].getX(1) - this.p[2].getX(1),2));
        double cd = Math.sqrt(Math.pow(this.p[2].getX(0) - this.p[3].getX(0),2) + Math.pow(this.p[2].getX(1) - this.p[3].getX(1),2));
        double ad = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[3].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[3].getX(1),2));
        return ab+bc+cd+ad;
    }

    @Override
    public String toString() {
        return "Trapeciya{" +
                "n=" + n +
                ", p=" + Arrays.toString(p) +
                '}';
    }
}