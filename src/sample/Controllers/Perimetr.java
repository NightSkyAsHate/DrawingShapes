package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Utils.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Perimetr {

    @FXML
    private ComboBox<IShape> ShapeList;

    @FXML
    private Button solve;

    @FXML
    private Button close;

    private File file;
    private ObservableList<IShape> list = FXCollections.observableArrayList();

    @FXML
    void initialize() throws IOException {
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

        ini();

        solve.setOnAction(event -> {
            if (ShapeList.getSelectionModel().getSelectedItem() != null) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Square.class.getResource("../FXML/sample.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Controller controller = loader.getController();
                controller.setStroke(String.valueOf(ShapeList.getSelectionModel().getSelectedItem().length()));
                try {
                    controller.setMarkedShape(ShapeList.getSelectionModel().getSelectedItem());
                    controller.setFile(file);
                    controller.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                showAllertInformation();
                stage.show();
                Stage stage3 = (Stage) close.getScene().getWindow();
                stage3.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Подсчёт периметра фигуры");
                alert.setHeaderText("Не успешно");
                alert.setContentText("Что-то пошло не так");
                alert.showAndWait();
            }
        });
    }

    private void ini() throws IOException {
        if (file != null) {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            while (reader.ready()) {
                String stroke = reader.readLine();
                if (stroke.equals("Circle")) {
                    stroke = reader.readLine();
                    String[] arr = stroke.split(" ");
                    Point2D point2D = new Point2D();
                    String[] points = arr[0].split("&");
                    point2D.setX(Double.parseDouble(points[0]), 0);
                    point2D.setX(Double.parseDouble(points[1]), 1);
                    Circle circle = new Circle(point2D, Double.parseDouble(arr[1]));
                    list.add(circle);
                }
                if (stroke.equals("Section")) {
                    stroke = reader.readLine();
                    Point2D[] point2DS = getArrayPoint2D(stroke);
                    Section section = new Section(point2DS[0], point2DS[1]);
                    list.add(section);
                }
                if (stroke.equals("Broken_line")) {
                    stroke = reader.readLine();
                    Point2D[] point2DS = getArrayPoint2D(stroke);
                    Broken_line broken_line = new Broken_line(point2DS.length, point2DS);
                    list.add(broken_line);
                }
                if (stroke.equals("Nshape")) {
                    stroke = reader.readLine();
                    Point2D[] point2D = getArrayPoint2D(stroke);
                    Nshape nshape = new Nshape(point2D.length, point2D);
                    list.add(nshape);
                }
                if (stroke.equals("Triangle")) {
                    stroke = reader.readLine();
                    Point2D[] point2DS = getArrayPoint2D(stroke);
                    Triangle triangle = new Triangle(point2DS);
                    list.add(triangle);
                }
                if (stroke.equals("Quad")) {
                    stroke = reader.readLine();
                    Point2D[] point2DS = getArrayPoint2D(stroke);
                    Quad quad = new Quad(point2DS);
                    list.add(quad);
                }
                if (stroke.equals("Rectangle")) {
                    stroke = reader.readLine();
                    Point2D[] point2DS = getArrayPoint2D(stroke);
                    Rectangle quad = new Rectangle(point2DS);
                    list.add(quad);
                }
                if (stroke.equals("Trapeciya")) {
                    stroke = reader.readLine();
                    Point2D[] point2DS = getArrayPoint2D(stroke);
                    Trapeciya quad = new Trapeciya(point2DS);
                    list.add(quad);
                }
            }
            ShapeList.setItems(list);
        }
    }

    public Point2D[] getArrayPoint2D(String stroke){
        String[] arr = stroke.split(" ");
        Point2D[] arrPoints = new Point2D[arr.length];
        int j = 0;
        for (String i : arr){
            String[] points = i.split("&");
            Point2D point2D = new Point2D();
            point2D.setX(Double.parseDouble(points[0]),0);
            point2D.setX(Double.parseDouble(points[1]),1);
            arrPoints[j] = point2D;
            j++;
        }
        return arrPoints;
    }

    private void showAllertInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Подсчёт периметра фигуры");
        alert.setHeaderText("Периметр фигуры подсчитан");
        alert.setContentText("Периметр, выбранной вами фигуры, был успешно подсчитана");
        alert.showAndWait();
    }

    public void setFile(File file){
        this.file = file;
    }
}
