package sample.Utils;

import java.util.Arrays;

public class Pointr {
    protected int dim;
    protected double[] x = new double[dim];

    public int getDim() {
        return dim;
    }

    public double[] getX() {
        return this.x;
    }

    public double getX(int i){
        return this.x[i];
    }

    public void setX(double[] point) {
        this.x = point;
    }

    public void setX(double x, int i){
        this.x[i] = x;
    }

    public Pointr(int dim, double[] point) {
        this.dim = dim;
        this.x = point;
    }

    public Pointr(int dim) {
        this.dim = dim;
        this.x = new double[this.dim];
        if (this.dim == this.x.length){
            for (int i=0;i<dim;i++) {
                this.x[i] = 0.0;
            }
        }else{
            System.out.println("Индексы неверные");
        }
    }

    @Override
    public String toString() {
        return "Pointr{" +
                "dim=" + this.dim +
                ", x=" + Arrays.toString(this.x) +
                '}';
    }

    //Все методы протестированы

    //Расстояние до начала координат
    public double abs(){
        double res = 0;
        for (int i=0;i<this.dim;i++){
            res += Math.pow(this.x[i],2);
        }
        return  Math.pow(res,1.0/2);
    }

    //Сложение точек
    public Pointr add(Pointr pointr){
        try{
            Pointr pp = new Pointr(pointr.getDim());
            for (int i = 0 ; i < pointr.getDim() ; i ++){
                pp.setX(this.x[i]+pointr.getX(i),i);
            }
            return pp;
        }catch (Exception e){
            System.out.println("Размерности не совпадают");
            return null;
        }
    }

    //Вычитание точек
    public Pointr sub(Pointr pointr){
        try{
            Pointr pp = new Pointr(pointr.getDim());
            for (int i = 0 ; i < pointr.getDim() ; i ++){
                pp.setX(this.x[i]-pointr.getX(i),i);
            }
            return pp;
        }catch (Exception e){
            System.out.println("Размерности не совпадают");
            return null;
        }
    }

    //Умножение точек друг на друга
    public double mult(Pointr pointr){
        try{
            double result = 0;
            for (int i = 0 ; i < pointr.getDim() ; i ++){
                result += this.x[i]*pointr.getX(i);
            }
            return result;
        }catch (Exception e){
            System.out.println("Размерности не совпадают");
            return -1;
        }
    }

    //Умножение координат точки на число b
    public Pointr mult(double b){
        Pointr pointr = new Pointr(this.dim);
        for (int i = 0 ; i < pointr.getDim() ; i ++){
            pointr.setX(this.x[i]*b,i);
        }
        return pointr;
    }

    //Симметрия относительно оси j
    public Pointr symtxis(int j){
        j--;
        Pointr pointr = new Pointr(this.dim);
        for (int i = 0 ; i < pointr.getDim() ; i ++){
            if (i != j){
                pointr.setX(this.x[i]*-1,i);
            }
            else
            {
                pointr.setX(this.x[i],i);
            }
        }
        return pointr;
    }
}
