package sample.Utils;

import java.util.Arrays;

public class Triangle extends Nshape implements IShape {

    public Triangle(Point2D[] p) {
        super(3, p);
    }

    @Override
    public double square() {
        return (Math.abs(0.5 * (this.p[0].getX(0) - this.p[2].getX(0) * (this.p[1].getX(1) - this.p[2].getX(1)) - (this.p[1].getX(0) - this.p[2].getX(0)) * (this.p[0].getX(1) - this.p[2].getX(0)))));
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "n=" + n +
                ", p=" + Arrays.toString(p) +
                '}';
    }
}
