package sample.Utils;

import java.util.Arrays;

public class Rectangle extends Quad implements IShape{
    public Rectangle(Point2D[] p) {
        super(p);
    }

    @Override
    public double square() {
        double ab = Math.sqrt(Math.pow(this.p[0].getX(0) - this.p[1].getX(0),2) + Math.pow(this.p[0].getX(1) - this.p[1].getX(1),2));
        double bc = Math.sqrt(Math.pow(this.p[1].getX(0) - this.p[2].getX(0),2) + Math.pow(this.p[1].getX(1) - this.p[2].getX(1),2));
        return ab * bc;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "n=" + n +
                ", p=" + Arrays.toString(p) +
                '}';
    }
}
