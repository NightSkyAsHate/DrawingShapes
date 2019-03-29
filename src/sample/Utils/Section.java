package sample.Utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Section extends Unlocked implements IShape {
    Point2D start;
    Point2D finish;

    public Section(){
        this.start = new Point2D();
        this.finish = new Point2D();
    }

    public Section(Point2D start , Point2D finish){
        this.start = start;
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "Section{" +
                "start=" + start +
                ", finish=" + finish +
                '}';
    }

    //Длина отрезка
    public double length() {
        return Math.sqrt(Math.pow(finish.getX(0) - start.getX(0),2)+Math.pow(finish.getX(1) - start.getX(1),2));
    }

    //Сдвиг на вектор
    public IShape shift(Point2D n) {
        Section section = new Section();
        section.start.setX(this.start.getX(0) + n.getX(0),0);
        section.start.setX(this.start.getX(1) + n.getX(1),1);
        section.finish.setX(this.finish.getX(0) + n.getX(0),0);
        section.finish.setX(this.finish.getX(1) + n.getX(1),1);
        return section;
    }

    //Поворот на угол фи, с тем же исключением 0
    public IShape rot(double fi) {
        Section section = new Section();
        section.start = this.start.rot(fi);
        section.finish = this.finish.rot(fi);
        return section;
    }

    //Отображение зеркально относительно оси i
    public IShape symtxis(int i) {
        Section section = new Section();
        section.start = this.start.symtxis(i);
        section.finish = this.finish.symtxis(i);
        return section;
    }

    //Пересечение отрезков
    public boolean cross(IShape i) {
        if (i instanceof Section) {
            if (this.start.toString().equals(((Section) i).start.toString()) ||
                    this.start.toString().equals(((Section) i).finish.toString()) ||
                    this.finish.toString().equals(((Section) i).start.toString()) ||
                    this.finish.toString().equals(((Section) i).finish.toString())) {
                return true;
            }
            double v1 = (((Section) i).finish.getX(0) - ((Section) i).start.getX(0)) * (this.start.getX(1) - ((Section) i).start.getX(1)) - (((Section) i).finish.getX(1) - ((Section) i).start.getX(1)) * (this.start.getX(0) - ((Section) i).start.getX(0));
            double v2 = (((Section) i).finish.getX(0) - ((Section) i).start.getX(0)) * (this.finish.getX(1) - ((Section) i).start.getX(1)) - (((Section) i).finish.getX(1) - ((Section) i).start.getX(1)) * (this.finish.getX(0) - ((Section) i).start.getX(0));
            double v3 = (this.finish.getX(0) - this.start.getX(0)) * (((Section) i).start.getX(1) - this.start.getX(1)) - (this.finish.getX(1) - this.start.getX(1)) * (((Section) i).start.getX(0) - this.start.getX(0));
            double v4 = (this.finish.getX(0) - this.start.getX(0)) * (((Section) i).finish.getX(1) - this.start.getX(1)) - (this.finish.getX(1) - this.start.getX(1)) * (((Section) i).finish.getX(0) - this.start.getX(0));
            if ((v1 * v2 < 0) && (v3 * v4 < 0)) return true;
            else return false;
        }
        else
        {
            System.out.println("На вход подан не отрезок");
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas, javafx.scene.paint.Paint color) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setStroke(color);
        g.setLineWidth(1);
        g.strokeLine(384+this.start.getX(0),394-this.start.getX(1),384+this.finish.getX(0),394-this.finish.getX(1));
    }

    @Override
    public Point2D getInd(int i) {
        if (i == 0){
            return this.start;
        }
        if (i == 1){
            return this.finish;
        }
        return null;
    }

    @Override
    public int amountOfPoints() {
        return 2;
    }
}
