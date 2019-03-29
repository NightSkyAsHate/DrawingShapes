package sample.Utils;

import javafx.scene.canvas.Canvas;

public interface IShape {
    double square();
    double length();
    IShape shift(Point2D n);
    IShape rot(double fi);
    IShape symtxis(int i);
    boolean cross(IShape i);
    void draw(Canvas canvas, javafx.scene.paint.Paint color);
    Point2D getInd(int i);
    int amountOfPoints();
}
