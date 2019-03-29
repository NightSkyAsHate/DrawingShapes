package sample.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Utils.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewFigures {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> list;

    @FXML
    private Button add;

    @FXML
    private Button close;

    @FXML
    private AnchorPane section;

    @FXML
    private TextField x1;

    @FXML
    private TextField y1;

    @FXML
    private TextField x2;

    @FXML
    private TextField y2;

    @FXML
    private TextField right;

    @FXML
    private TextField x3;

    @FXML
    private TextField y3;

    @FXML
    private TextField x4;

    @FXML
    private TextField y4;

    @FXML
    private TextField x5;

    @FXML
    private TextField y5;

    File file;
    Point2D[] point2DS;
    IShape er;
    int countOfPoints;

    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("Отрезок", "Ломаная", "Окружность", "Многоугольник", "Треугольник", "Четырёхугольник", "Прямоугольник", "Трапеция");
        list.setItems(langs);

        list.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hideAll(true);
                switch (list.getSelectionModel().getSelectedItem()){
                    case ("Отрезок"):
                        x1.setVisible(true);
                        y1.setVisible(true);
                        x2.setVisible(true);
                        y2.setVisible(true);
                        break;
                    case ("Ломаная"):
                        allSections();
                        break;
                    case "Окружность":
                        right.setVisible(true);
                        right.setPromptText("Радиус");
                        x1.setVisible(true);
                        y1.setVisible(true);
                        break;
                    case "Многоугольник":
                        allSections();
                        break;
                    case "Треугольник":
                        x1.setVisible(true);
                        y1.setVisible(true);
                        x2.setVisible(true);
                        y2.setVisible(true);
                        x3.setVisible(true);
                        y3.setVisible(true);
                        break;
                    case "Четырёхугольник":
                        showFor4();
                        break;
                    case "Прямоугольник":
                        showFor4();
                        break;
                    case "Трапеция":
                        showFor4();
                        break;
                }
            }
        });

        add.setOnAction(event -> {
            switch (list.getSelectionModel().getSelectedItem()){
                case ("Отрезок"):
                    point2DS = new Point2D[2];
                    point2DS[0] = new Point2D();
                    point2DS[1] = new Point2D();
                    Point2D q = new Point2D();
                    q.setX(Double.parseDouble(x1.getText()),0);
                    q.setX(Double.parseDouble(y1.getText()),1);
                    point2DS[0] = q;
                    Point2D r = new Point2D();
                    r.setX(Double.parseDouble(x2.getText()),0);
                    r.setX(Double.parseDouble(y2.getText()),1);
                    point2DS[1] = r;
                    er = new Section(point2DS[0],point2DS[1]);
                    try {
                        writeDownFigure(er);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("Ломаная"):
                    Point2D[] arr = getPoint2DS();
                    Broken_line br = new Broken_line(countOfPoints,arr);
                    try {
                        writeDownFigure(br);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Окружность":
                    if (!x1.getText().equals("") && !right.getText().equals("") && !y1.getText().equals("")){
                        Point2D p = new Point2D();
                        p.setX(Double.parseDouble(x1.getText()),0);
                        p.setX(Double.parseDouble(y1.getText()),1);
                        Circle circle = new Circle(p,Double.parseDouble(right.getText()));
                        try {
                            writeDownFigure(circle);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "Многоугольник":
                    Point2D[] rer = getPoint2DS();
                    Nshape nshape = new Nshape(countOfPoints,rer);
                    try {
                        writeDownFigure(nshape);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Треугольник":
                    Point2D p = new Point2D();
                    p.setX(Double.parseDouble(x1.getText()),0);
                    p.setX(Double.parseDouble(y1.getText()),1);
                    Point2D x = new Point2D();
                    x.setX(Double.parseDouble(x2.getText()),0);
                    x.setX(Double.parseDouble(y2.getText()),1);
                    Point2D z = new Point2D();
                    z.setX(Double.parseDouble(x3.getText()),0);
                    z.setX(Double.parseDouble(y3.getText()),1);
                    arr = new Point2D[]{p,x,z};
                    Triangle triangle = new Triangle(arr);
                    try {
                        writeDownFigure(triangle);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Четырёхугольник":
                    Point2D[] ar = getPointsFor4();
                    Quad quad = new Quad(ar);
                    try {
                        writeDownFigure(quad);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Прямоугольник":
                    ar = getPointsFor4();
                    Rectangle rectangle = new Rectangle(ar);
                    try {
                        writeDownFigure(rectangle);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Трапеция":
                    ar = getPointsFor4();
                    Trapeciya trapeciya = new Trapeciya(ar);
                    try {
                        writeDownFigure(trapeciya);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Добавление фигуры");
            alert.setHeaderText("Фигура добавлена");
            alert.setContentText("Введённая вами фигура была успешно добавлена");
            alert.showAndWait();

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MoveMenu.class.getResource("../FXML/sample.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Controller controller = loader.getController();
            controller.setFile(file);
            try {
                controller.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Stage stage1 = (Stage) close.getScene().getWindow();
            stage1.close();
        });

        close.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MoveMenu.class.getResource("../FXML/sample.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Controller controller = loader.getController();
            controller.setFile(file);
            try {
                controller.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Stage stage1 = (Stage) close.getScene().getWindow();
            stage1.close();
        });
    }

    private void writeDownFigure(IShape shape) throws IOException {
        FileWriter fw = new FileWriter(file,true);
        fw.write(shape.getClass().getSimpleName()+"\n");
        String s = "";
        for (int i = 0; i < shape.amountOfPoints() ; i ++){
            if (shape.getInd(i) != null) {
                s += shape.getInd(i).getX(0) + "&" + shape.getInd(i).getX(1) + " ";
            }
        }
        if (shape instanceof Circle){
            s += ((Circle) shape).getRadius();
        }
        fw.write(s+"\n");
        fw.flush();
        fw.close();
    }

    public void hideAll(boolean flag){
        x1.setVisible(false);
        x2.setVisible(false);
        x3.setVisible(false);
        x4.setVisible(false);
        x5.setVisible(false);
        y1.setVisible(false);
        y2.setVisible(false);
        y3.setVisible(false);
        y4.setVisible(false);
        y5.setVisible(false);
        if (flag) {
            right.setVisible(false);
            right.setPromptText("Количество вершин");
        }
    }

    public void allSections(){
        right.setVisible(true);
        right.setPromptText("Количество вершин");
        right.textProperty().addListener(observable1 -> {
            hideAll(false);
            if (!right.getText().equals("")) {
                if (Integer.valueOf(right.getText()) > 1) {
                    x1.setVisible(true);
                    y1.setVisible(true);
                    x2.setVisible(true);
                    y2.setVisible(true);
                    countOfPoints = 2;
                }
                if (Integer.valueOf(right.getText()) > 2) {
                    x3.setVisible(true);
                    y3.setVisible(true);
                    countOfPoints = 3;
                }
                if (Integer.valueOf(right.getText()) > 3) {
                    x4.setVisible(true);
                    y4.setVisible(true);
                    countOfPoints = 4;
                }
                if (Integer.valueOf(right.getText()) > 4 && Integer.valueOf(right.getText()) < 6) {
                    x5.setVisible(true);
                    y5.setVisible(true);
                    countOfPoints = 5;
                }
            }
        });
    }

    public Point2D[] getPoint2DS(){
        Point2D p = null,s = null,t = null;
        Point2D q = null;
        Point2D r = null;
        if (!x1.getText().equals("") && !y1.getText().equals("")){
            p = new Point2D();
            p.setX(Double.parseDouble(x1.getText()),0);
            p.setX(Double.parseDouble(y1.getText()),1);
            countOfPoints = 1;
        }
        if (!x2.getText().equals("") && !y2.getText().equals("")){
            q = new Point2D();
            q.setX(Double.parseDouble(x2.getText()),0);
            q.setX(Double.parseDouble(y2.getText()),1);
            countOfPoints = 2;
        }
        if (!x3.getText().equals("") && !y3.getText().equals("")){
            r = new Point2D();
            r.setX(Double.parseDouble(x3.getText()),0);
            r.setX(Double.parseDouble(y3.getText()),1);
            countOfPoints = 3;
        }
        if (!x4.getText().equals("") && !y4.getText().equals("")){
            s = new Point2D();
            s.setX(Double.parseDouble(x4.getText()),0);
            s.setX(Double.parseDouble(y4.getText()),1);
            countOfPoints = 4;
        }
        if (!x5.getText().equals("") && !y5.getText().equals("")){
            t = new Point2D();
            t.setX(Double.parseDouble(x5.getText()),0);
            t.setX(Double.parseDouble(y5.getText()),1);
            countOfPoints = 5;
        }
        Point2D[] arr = new Point2D[]{p,q,r,s,t};
        return arr;
    }

    public void showFor4(){
        x1.setVisible(true);
        x2.setVisible(true);
        x3.setVisible(true);
        x4.setVisible(true);
        y1.setVisible(true);
        y2.setVisible(true);
        y3.setVisible(true);
        y4.setVisible(true);
    }

    public Point2D[] getPointsFor4(){
        Point2D p = new Point2D();
        p.setX(Double.parseDouble(x1.getText()),0);
        p.setX(Double.parseDouble(y1.getText()),1);
        Point2D z = new Point2D();
        z.setX(Double.parseDouble(x2.getText()),0);
        z.setX(Double.parseDouble(y2.getText()),1);
        Point2D x = new Point2D();
        x.setX(Double.parseDouble(x3.getText()),0);
        x.setX(Double.parseDouble(y3.getText()),1);
        Point2D c = new Point2D();
        c.setX(Double.parseDouble(x4.getText()),0);
        c.setX(Double.parseDouble(y4.getText()),1);
        Point2D[] ar = new Point2D[]{p,z,x,c};
        return ar;
    }

    public void setFile(File file){
        this.file = file;
    }
}
