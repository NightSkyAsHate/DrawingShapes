package sample.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PointTestDrive {
    public static void main(String[] args) throws IOException {
        ArrayList<IShape> bit = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество фигур ");
        int n = Integer.parseInt(reader.readLine());
        int q;
        double sumSquare = 0;
        double sumLength = 0;
        double x;
        double y;
        String string;
        Point2D[] ark;
        for (int i = 0 ; i < n ; i ++){
            string = reader.readLine();
            switch (string){
                case "круг" : System.out.println("Введите сперва координаты центра круга, а затем радиум");
                    x = Double.parseDouble(reader.readLine());
                    y = Double.parseDouble(reader.readLine());
                    double radius = Double.parseDouble(reader.readLine());
                    double[] arr = {x,y};
                    Point2D point2D = new Point2D(arr);
                    Circle circle = new Circle(point2D,radius);
                    bit.add(circle);
                    sumSquare += circle.square();
                    sumLength += circle.length();
                    break;

                case "ломаная" : System.out.println("Введите количество вершин, а потом введите их координаты");
                    q = Integer.parseInt(reader.readLine());
                    ark = new Point2D[q];
                    for (int j = 0 ; j < q ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    Broken_line broken_line = new Broken_line(q,ark);
                    bit.add(broken_line);
                    sumSquare += broken_line.square();
                    sumLength += broken_line.length();
                    break;

                case "н-угольник" : System.out.println("Введите количество вершин, а потом введите их координаты");
                    q = Integer.parseInt(reader.readLine());
                    ark = new Point2D[q];
                    for (int j = 0 ; j < q ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    Nshape nshape = new Nshape(q,ark);
                    bit.add(nshape);
                    sumSquare += nshape.square();
                    sumLength += nshape.length();
                    break;

                case "четырехугольник" : System.out.println("Введите его координаты");
                    ark = new Point2D[4];
                    for (int j = 0 ; j < 4 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    Quad quad = new Quad(ark);
                    bit.add(quad);
                    sumSquare += quad.square();
                    sumLength += quad.length();
                    break;

                case "треугольник" : System.out.println("Введите его координаты");
                    ark = new Point2D[3];
                    for (int j = 0 ; j < 3 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    Triangle triangle = new Triangle(ark);
                    bit.add(triangle);
                    sumSquare += triangle.square();
                    sumLength += triangle.length();
                    break;

                case "прямоугольник" : System.out.println("Введите его координаты");
                    ark = new Point2D[4];
                    for (int j = 0 ; j < 4 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    Rectangle rectangle = new Rectangle(ark);
                    bit.add(rectangle);
                    sumSquare += rectangle.square();
                    sumLength += rectangle.length();
                    break;

                case "отрезок" : System.out.println("Введите его координаты");
                    x = Double.parseDouble(reader.readLine());
                    y = Double.parseDouble(reader.readLine());
                    arr = new double[]{x,y};
                    point2D = new Point2D(arr);
                    x = Double.parseDouble(reader.readLine());
                    y = Double.parseDouble(reader.readLine());
                    arr = new double[]{x,y};
                    Point2D point2D1= new Point2D(arr);
                    Section section = new Section(point2D, point2D1);
                    bit.add(section);
                    sumSquare += section.square();
                    sumLength += section.length();
                    break;
                case "трапеция" : System.out.println("Введите ее координаты");
                    ark = new Point2D[4];
                    for (int j = 0 ; j < 4 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    Trapeciya trapeciya = new Trapeciya(ark);
                    bit.add(trapeciya);
                    sumSquare += trapeciya.square();
                    sumLength += trapeciya.length();

            }
        }
        System.out.println("Общая площадь = " + sumSquare);
        System.out.println("Суммарная длина = " + sumLength);
        System.out.println("Средняя длина = " + (sumLength / n));
        IShape iShape = null;
        for (int i = 0 ; i < n ; i ++){
            string = reader.readLine();
            switch (string){
                case "круг" : System.out.println("Введите сперва координаты центра круга, а затем радиум");
                    x = Double.parseDouble(reader.readLine());
                    y = Double.parseDouble(reader.readLine());
                    double radius = Double.parseDouble(reader.readLine());
                    double[] arr = {x,y};
                    Point2D point2D = new Point2D(arr);
                    iShape = new Circle(point2D,radius);
                    break;

                case "ломаная" : System.out.println("Введите количество вершин, а потом введите их координаты");
                    q = Integer.parseInt(reader.readLine());
                    ark = new Point2D[q];
                    for (int j = 0 ; j < q ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    iShape = new Broken_line(q,ark);
                    break;

                case "н-угольник" : System.out.println("Введите количество вершин, а потом введите их координаты");
                    q = Integer.parseInt(reader.readLine());
                    ark = new Point2D[q];
                    for (int j = 0 ; j < q ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    iShape = new Nshape(q,ark);
                    break;

                case "четырехугольник" : System.out.println("Введите его координаты");
                    ark = new Point2D[4];
                    for (int j = 0 ; j < 4 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    iShape = new Quad(ark);
                    break;

                case "треугольник" : System.out.println("Введите его координаты");
                    ark = new Point2D[3];
                    for (int j = 0 ; j < 3 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    iShape = new Triangle(ark);
                    break;

                case "прямоугольник" : System.out.println("Введите его координаты");
                    ark = new Point2D[4];
                    for (int j = 0 ; j < 4 ; j ++){
                        x = Double.parseDouble(reader.readLine());
                        y = Double.parseDouble(reader.readLine());
                        arr = new double[]{x, y};
                        ark[j] = new Point2D(arr);
                    }
                    iShape = new Rectangle(ark);
                    break;

                case "отрезок" : System.out.println("Введите его координаты");
                    x = Double.parseDouble(reader.readLine());
                    y = Double.parseDouble(reader.readLine());
                    arr = new double[]{x,y};
                    point2D = new Point2D(arr);
                    x = Double.parseDouble(reader.readLine());
                    y = Double.parseDouble(reader.readLine());
                    arr = new double[]{x,y};
                    Point2D point2D1= new Point2D(arr);
                    iShape = new Section(point2D, point2D1);
                    break;
            }
            System.out.println(bit.get(i).cross(iShape));
            System.out.println("Выберите тип движения {повернуть , переместить , отобразить}");
            string = reader.readLine();
            switch (string){
                case "повернуть" :
                    System.out.println("Введите угол на который хотите повернуть");
                    y = Double.parseDouble(reader.readLine());
                    iShape = iShape.rot(y);
                    break;
                case "переместить" :
                    System.out.println("Введите координаты точки на векотр которого хотите сместить");
                    y = Double.parseDouble(reader.readLine());
                    x = Double.parseDouble(reader.readLine());
                    double[] arr = new double[]{y,x};
                    Point2D point2D = new Point2D(arr);
                    iShape = iShape.shift(point2D);
                    break;
                case "отобразить" :
                    System.out.println("Введите ось относительно которой хотите отобразить, начинается с 1");
                    q = Integer.parseInt(reader.readLine());
                    iShape = iShape.symtxis(q);
                    break;
            }
            System.out.println(bit.get(i).cross(iShape));
        }
    }
}


