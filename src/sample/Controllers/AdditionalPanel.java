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

import java.io.*;

public class AdditionalPanel {

    @FXML
    private ComboBox<IShape> ShapeList;

    @FXML
    private Button button;

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

        button.setOnAction(event -> {
            try {
                list.remove(ShapeList.getSelectionModel().getSelectedItem());
                reWriteFile();
            } catch (IOException e) {
                showAllertMistake();
            }
            showAllertInformation();
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
            Stage stage1 = (Stage) button.getScene().getWindow();
            stage1.close();
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

    public void reWriteFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (IShape i : list){
            String s = "";
            fileWriter.write(i.getClass().getSimpleName()+"\n");
            for (int j = 0 ; j < i.amountOfPoints(); j ++){
                if (i.getInd(j) != null){
                    s += i.getInd(j).getX(0) + "&" + i.getInd(j).getX(1) + " ";
                }
            }
            if (i instanceof Circle){
                s += ((Circle) i).getRadius();
            }
            fileWriter.write(s+"\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private void showAllertInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Удаление фигуры");
        alert.setHeaderText("Фигура удалена");
        alert.setContentText("Фигура, которую вы выбрали была успешно удалена");
        alert.showAndWait();
    }

    private void showAllertMistake(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Удаление фигуры");
        alert.setHeaderText("Фигура не удалена");
        alert.setContentText("Что-то пошло не так");
        alert.showAndWait();
    }

    public void setFile(File file){
        this.file = file;
    }
}
