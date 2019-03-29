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
import sample.Utils.*;

import java.io.*;

public class Cross {

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button check;

    @FXML
    private Button close;

    @FXML
    private ComboBox<IShape> ShapeList1;

    @FXML
    private ComboBox<IShape> ShapeList2;

    private File file;
    private String prev;
    private ObservableList<IShape> list = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("Broken_line", "Circle", "Nshape", "Quad", "Rectangle", "Section", "Trapeciya", "Triangle");
        type.setItems(langs);

        type.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                try {
                    setComboBoxesInfo(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        check.setOnAction(event -> {
            if (ShapeList2.getSelectionModel().getSelectedItem() != null && ShapeList1.getSelectionModel().getSelectedItem() != null){
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
                controller.setFile(file);
                if (ShapeList1.getSelectionModel().getSelectedItem().cross(ShapeList2.getSelectionModel().getSelectedItem())){
                    controller.setStroke("true");
                }else{
                    controller.setStroke("false");
                }
                try {
                    controller.setMarkedShape(ShapeList1.getSelectionModel().getSelectedItem());
                    controller.setMarkedShape2(ShapeList2.getSelectionModel().getSelectedItem());
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
            }
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

    public void setComboBoxesInfo(String type) throws IOException {
        if (prev != type) {
            cleanComboBoxes();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()) {
                String stroke = bufferedReader.readLine();
                if (stroke.equals(type)) {
                    if (stroke.equals("Circle")) {
                        stroke = bufferedReader.readLine();
                        String[] arr = stroke.split(" ");
                        Point2D point2D = new Point2D();
                        String[] points = arr[0].split("&");
                        point2D.setX(Double.parseDouble(points[0]), 0);
                        point2D.setX(Double.parseDouble(points[1]), 1);
                        Circle circle = new Circle(point2D, Double.parseDouble(arr[1]));
                        list.add(circle);
                    }
                    if (stroke.equals("Section")) {
                        stroke = bufferedReader.readLine();
                        System.out.println(stroke);
                        Point2D[] point2DS = getArrayPoint2D(stroke);
                        Section section = new Section(point2DS[0], point2DS[1]);
                        list.add(section);
                    }
                    if (stroke.equals("Broken_line")) {
                        stroke = bufferedReader.readLine();
                        Point2D[] point2DS = getArrayPoint2D(stroke);
                        Broken_line broken_line = new Broken_line(point2DS.length, point2DS);
                        list.add(broken_line);
                    }
                    if (stroke.equals("Nshape")) {
                        stroke = bufferedReader.readLine();
                        Point2D[] point2D = getArrayPoint2D(stroke);
                        Nshape nshape = new Nshape(point2D.length, point2D);
                        list.add(nshape);
                    }
                    if (stroke.equals("Triangle")) {
                        stroke = bufferedReader.readLine();
                        Point2D[] point2DS = getArrayPoint2D(stroke);
                        Triangle triangle = new Triangle(point2DS);
                        list.add(triangle);
                    }
                    if (stroke.equals("Quad")) {
                        stroke = bufferedReader.readLine();
                        Point2D[] point2DS = getArrayPoint2D(stroke);
                        Quad quad = new Quad(point2DS);
                        list.add(quad);
                    }
                    if (stroke.equals("Rectangle")) {
                        stroke = bufferedReader.readLine();
                        Point2D[] point2DS = getArrayPoint2D(stroke);
                        Rectangle quad = new Rectangle(point2DS);
                        list.add(quad);
                    }
                    if (stroke.equals("Trapeciya")) {
                        stroke = bufferedReader.readLine();
                        Point2D[] point2DS = getArrayPoint2D(stroke);
                        Trapeciya quad = new Trapeciya(point2DS);
                        list.add(quad);
                    }
                } else {
                    stroke = bufferedReader.readLine();
                }
            }
            ShapeList2.setItems(list);
            ShapeList1.setItems(list);
            prev = type;
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

    public void setFile(File file){
        this.file = file;
    }

    public void cleanComboBoxes(){
        list.clear();
        ShapeList1.setItems(list);
        ShapeList2.setItems(list);
    }

    private void showAllertInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Подсчёт площади фигуры");
        alert.setHeaderText("Площадь фигуры подсчитана");
        alert.setContentText("Площадь, выбранной вами фигуры, была успешно подсчитана");
        alert.showAndWait();
    }
}
