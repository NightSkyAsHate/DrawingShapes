package sample.Utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements IShape{
    Point2D point = new Point2D();
    double radius;

    public Circle(Point2D point2D, double radius){
        this.point = point2D;
        this.radius = radius;
    }

    public Circle(){};

    public void setRadius(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return this.radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "point=" + point +
                ", radius=" + radius +
                '}';
    }

    //Площадь
    public double square() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    //Длина окружности
    public double length() {
        return Math.PI * 2 * this.radius;
    }

    //Cдвиг на вектор
    public IShape shift(Point2D n) {
        Circle circle = new Circle();
        circle.point.setX(n.getX(0)+this.point.getX(0), 0);
        circle.point.setX(n.getX(1)+this.point.getX(1), 1);
        circle.setRadius(this.radius);
        return circle;
    }

    //Поворот фигуры на угол фи
    public IShape rot(double fi) {
        Circle circle = new Circle();
        circle.point = this.point.rot(fi);
        circle.setRadius(this.radius);
        return circle;
    }

    //Отображение симметрично относительно оси i
    public IShape symtxis(int i) {
        Circle circle = new Circle();
        circle.point = this.point.symtxis(i);
        circle.setRadius(this.radius);
        return circle;
    }

    //Пересечение окржуностей
    public boolean cross(IShape i) {
//        double eps = 0.0000001;
//        if (i instanceof Circle){
//            double r = Math.sqrt(Math.pow(((Circle) i).point.getX(0) - this.point.getX(0), 2) + Math.pow(((Circle) i).point.getX(1) - this.point.getX(1) , 2));
//            if ((r - 0)<eps && (Math.abs(((Circle) i).getRadius() - this.radius)<eps)){
//                return  true;
//            }
//            if (this.radius + ((Circle) i).getRadius() >= r && r + ((Circle) i).getRadius() >= this.radius && this.radius + r >= ((Circle) i).getRadius()){
//                return true;
//            }
//            return false;
//        }
//        System.out.println("На вход подан не круг!");
//        return false;

        System.out.println("x2 " + i.getInd(0).getX(0));
        System.out.println("x1 " + this.point.getX(0));
        System.out.println("y2 " + i.getInd(0).getX(1));
        System.out.println("y1 " + this.point.getX(1));
        double c = Math.sqrt(Math.pow(i.getInd(0).getX(0) - this.point.getX(0),2) + Math.pow(i.getInd(0).getX(1) - this.point.getX(1),2));
        System.out.println(c);
        System.out.println(((Circle) i).getRadius() + this.radius);
        if (c < (((Circle) i).getRadius() + this.radius)){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas, javafx.scene.paint.Paint color) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setLineWidth(1);
        g.setStroke(color);
        g.strokeOval(this.point.getX(0)+ 384 - this.radius,394 - this.point.getX(1) - this.radius,this.radius,this.radius);
    }

    @Override
    public Point2D getInd(int i) {
        return this.point;
    }

    @Override
    public int amountOfPoints() {
        return 1;
    }
}
