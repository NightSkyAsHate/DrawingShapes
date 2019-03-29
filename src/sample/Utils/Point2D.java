package sample.Utils;

public class Point2D extends Pointr {
    public Point2D() {
        super(2);
    }

    public Point2D(double[] x){
        super(2,x);
    }

    //Поворот точки на угол fi
    public Point2D rot(double fi){
        Point2D point2D = new Point2D();
        double radians = Math.toRadians(fi);
        point2D.setX(this.getX(0) * Math.cos(radians) - this.getX(1) * Math.sin(radians),0);
            point2D.setX(this.getX(0) * Math.sin(radians) + this.getX(1) * Math.cos(radians),1);
        return point2D;
    }

        @Override
        public Point2D symtxis(int j) {
            j--;
            Point2D pointr = new Point2D();
            for (int i = 0 ; i < 2 ; i ++){
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
