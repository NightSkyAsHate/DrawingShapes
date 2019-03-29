package sample.Utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Broken_line extends Unlocked implements IShape {
    int n;
    Point2D[] p;

    Broken_line(){
        this.n = 0 ;
        this.p = null;
    };

    public Broken_line(int n, Point2D[] p){
        this.n = n;
        this.p = p;
    }

    @Override
    public String toString() {
        return "Broken_line{" +
                "n=" + n +
                ", p=" + Arrays.toString(p) +
                '}';
    }

    //Длина ломанной
    public double length()  {
        double res = 0;
        for (int i = 1 ; i < this.n ; i ++){
            res += Math.sqrt(Math.pow(p[i].getX(0) - p[i-1].getX(0),2) + Math.pow(p[i].getX(1) - p[i-1].getX(1),2));
        }
        return res;
    }

    //Сдвиг на вектор
    public IShape shift(Point2D n) {
        Broken_line broken_line = new Broken_line();
        broken_line.n = this.n;
        broken_line.p = new Point2D[this.n];
        for (int i = 0 ; i < this.n ; i ++){
            broken_line.p[i] = new Point2D();
            broken_line.p[i].setX(this.p[i].getX(0) + n.getX(0),0);
            broken_line.p[i].setX(this.p[i].getX(1) + n.getX(1),1);
        }
        return broken_line;
    }

    //Поворот на угол фи
    public IShape rot(double fi) {
        Broken_line broken_line = new Broken_line();
        broken_line.n = this.n;
        broken_line.p = new Point2D[this.n];
        for (int i = 0 ; i < this.n ; i ++){
            broken_line.p[i] = this.p[i].rot(fi);
        }
        return broken_line;
    }

    //Отображение симметрично относительно оси i
    public IShape symtxis(int i) {
        Broken_line broken_line = new Broken_line();
        broken_line.p = new Point2D[this.n];
        broken_line.n = this.n;
        for (int j = 0 ; j < this.n ; j ++){
            broken_line.p[j] = this.p[j].symtxis(i);
        }
        return broken_line;
    }

    //Пересечение ломанных
    public boolean cross(IShape i) {
        if (i instanceof Broken_line) {
            Section section = new Section();
            Section section1 = new Section();
            for (int j = 0; j < this.n; j++) {
                section.start = this.p[j];
                section.finish = this.p[j + 1];
                for (int k = 0; k < ((Broken_line) i).n; k++) {
                    section1.start = ((Broken_line) i).p[k];
                    section1.finish = ((Broken_line) i).p[k + 1];
                    if (section.cross(section1)){
                        return true;
                    }
                }
            }
            return false;
        }
        else
        {
            System.out.println("На вход подана не ломаная линия");
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas, javafx.scene.paint.Paint color) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setStroke(color);
        g.setLineWidth(1);
        for (int i = 1 ; i < this.n ; i ++){
            g.strokeLine(384 + p[i-1].getX(0),394 - p[i-1].getX(1),384 + p[i].getX(0),394 - p[i].getX(1));
        }
    }

    @Override
    public Point2D getInd(int i) {
        return this.p[i];
    }

    @Override
    public int amountOfPoints() {
        return this.n;
    }
}
