package sample.Utils;

import java.util.Arrays;

public class Quad extends Nshape implements IShape {
    public Quad() {
    }

    public Quad(Point2D[] p) {
        super(4, p);
    }

    @Override
    public double square() {
        double ab = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[1].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[1].getX(1),2));
//        System.out.println(ab);
        double bc = Math.sqrt(Math.pow(this.p[1].getX(0) - this.p[2].getX(0),2) + Math.pow(this.p[1].getX(1) - this.p[2].getX(1),2));
//        System.out.println(bc);
        double cd = Math.sqrt(Math.pow(this.p[2].getX(0) - this.p[3].getX(0),2) + Math.pow(this.p[2].getX(1) - this.p[3].getX(1),2));
//        System.out.println(cd);
        double ad = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[3].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[3].getX(1),2));
//        System.out.println(ad);
        double length = (ab + bc + cd + ad)*0.5;
//        System.out.println(length);
        double a = length -ab;
       System.out.println(a);
        double b = length - bc;
        System.out.println(b);
        double c = length - cd;
        System.out.println(c);
        double d = length - ad;
        return (double)(Math.sqrt((a) * (b) * (c) * (d)));
    }

    @Override
    public String toString() {
        return "Quad{" +
                "n=" + n +
                ", p=" + Arrays.toString(p) +
                '}';
    }
}
