package sample.Utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Nshape implements IShape {
    int n;//Количество вершин
    Point2D[] p = new Point2D[n];//Координаты вершин

    public Nshape() {
    }

    //Конструктор со всеми параметрами
    public Nshape(int n, Point2D[] p){
        this.n = n;
        this.p = p;
    }

    @Override
    public String toString() {
        return "Nshape{" +
                "n=" + n +
                ", p=" + Arrays.toString(p) +
                '}';
    }

    //Площадь н-угольника(Работает корректно)
    public double square() {
        double sum = 0;
        double sum1 = 0;
        for (int i = 1 ; i < this.n ; i ++){
            sum += this.p[i].getX(1) * this.p[i-1].getX(0);
            sum1 += this.p[i-1].getX(1) * this.p[i].getX(0);
        }
        sum += this.p[this.n-1].getX(0) * this.p[0].getX(1);
        sum1 += this.p[this.n-1].getX(1) * this.p[0].getX(0);
        if ((sum - sum1) == 0){
            System.out.println("1");
            return (this.n * Math.sqrt(Math.pow(p[1].getX(0) - p[0].getX(0),2) + Math.pow(p[1].getX(1) - p[0].getX(1),2))) / 4 * (1 / Math.tan(Math.PI / this.n));
        }
        else
        {
            System.out.println("2");
            return (sum - sum1) * 0.5;
        }
    }

    //Длина n-угольника(Работает корректно)
    public double length() {
        double res = 0;
        for (int i = 1 ; i < this.n ; i ++){
            res += Math.sqrt(Math.pow(p[i].getX(0) - p[i-1].getX(0),2) + Math.pow(p[i].getX(1) - p[i-1].getX(1),2));
        }
        res += Math.sqrt(Math.pow(p[n-1].getX(0) - p[0].getX(0),2) + Math.pow(p[n-1].getX(1) - p[0].getX(1),2));
        return res;
    }

    //Перемещение не вектор(Работает корректно)
    public IShape shift(Point2D l) {
        Nshape nshape = new Nshape();
        nshape.p = new Point2D[this.n];
        nshape.n = this.n;
        for (int i = 0 ; i < this.n ; i ++){
            nshape.p[i] = new Point2D();
            nshape.p[i].setX(this.p[i].getX(0) + l.getX(0),0);
            nshape.p[i].setX(this.p[i].getX(1) + l.getX(1),1);
        }
        return nshape;
    }

    //Пороврот n-угольника на угол fi(Работает корректно)
    public IShape rot(double fi) {
        Nshape nshape = new Nshape();
        nshape.n = this.n;
        nshape.p = new Point2D[this.n];
        for (int i = 0 ; i < this.n ; i ++ ){
            nshape.p[i] = new Point2D();
            nshape.p[i] = this.p[i].rot(fi);
        }
        return nshape;
    }

    //Отображение симметрично относительно оси i(Работает корректно)
    public IShape symtxis(int i) {
        Nshape nshape = new Nshape();
        nshape.n = this.n;
        nshape.p = new Point2D[this.n];
        for (int j = 0 ; j < this.n ; j ++ ){
            nshape.p[j] = this.p[j].symtxis(i);
        }
        return nshape;
    }

    //Пересечение(Протестил , не уверен но должен работать корректно)
    public boolean cross(IShape i) {
        if (i instanceof Nshape) {
            Section section = new Section();
            Section section1 = new Section();
            for (int j = 1; j < this.n - 1; j++) {
                section.start = this.p[j];
                section.finish = this.p[j + 1];
                for (int k = 1; k < ((Nshape) i).n - 1; k++) {
                    section1.start = ((Nshape) i).p[k];
                    section1.finish = ((Nshape) i).p[k + 1];
                    if (section.cross(section1)) {
                        return true;
                    }
                }
                section1.start = ((Nshape) i).p[((Nshape) i).n - 1];
                section1.finish = ((Nshape) i).p[((Nshape) i).n - 1];
                if (section.cross(section1)) {
                    return true;
                }
            }
            section.start = this.p[this.n - 1];
            section.finish = this.p[0];
            for (int k = 1; k < ((Nshape) i).n - 1; k++) {
                section1.start = ((Nshape) i).p[k];
                section1.finish = ((Nshape) i).p[k + 1];
                if (section.cross(section1)) {
                    return true;
                }
            }
            return false;
        } else {
            System.out.println("На вход подана не ломаная линиия");
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
        g.strokeLine(384 + p[0].getX(0),394 - p[0].getX(1),384 + p[n-1].getX(0),394 - p[n-1].getX(1));
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
