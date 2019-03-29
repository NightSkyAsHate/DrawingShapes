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
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Utils.*;

import java.io.*;

//Добавить метод переписи файла
public class MoveMenu {

    @FXML
    private ComboBox<IShape> shapeList;

    @FXML
    private ComboBox<String> actionList;

    @FXML
    private Button close;

    @FXML
    private Button move;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    private File file;
    private ObservableList<IShape> list = FXCollections.observableArrayList();

    @FXML
    void initialize() throws IOException {
        ObservableList<String> langs = FXCollections.observableArrayList("Сдвиг", "Поворот", "Симметрия");
        actionList.setItems(langs);

        ini();

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

        actionList.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hideAll();
                switch (actionList.getSelectionModel().getSelectedItem()){
                    case ("Сдвиг"):
                        textField1.setVisible(true);
                        textField2.setVisible(true);
                        break;
                    case ("Поворот"):
                        textField1.setVisible(true);
                        break;
                    case ("Симметрия"):
                        textField1.setVisible(true);
                        break;
                }
            }
        });

        move.setOnAction(event -> {
            switch (actionList.getSelectionModel().getSelectedItem()){
                case "Сдвиг":
                   if (shapeList.getSelectionModel().getSelectedItem() != null){
                       double[] ar = new double[]{Double.parseDouble(textField1.getText()),Double.parseDouble(textField2.getText())};
                       IShape iShape = shapeList.getSelectionModel().getSelectedItem().shift(new Point2D(ar));
                       list.remove(shapeList.getSelectionModel().getSelectedIndex());
                       list.add(iShape);
                   }
                   break;
                case "Поворот":
                    if (shapeList.getSelectionModel().getSelectedItem() != null){
                        IShape iShape = shapeList.getSelectionModel().getSelectedItem().rot(Double.parseDouble(textField1.getText()));
                        list.remove(shapeList.getSelectionModel().getSelectedIndex());
                        list.add(iShape);
                    }
                    break;
                case "Симметрия":
                    if (shapeList.getSelectionModel().getSelectedItem() != null){
                        IShape iShape = shapeList.getSelectionModel().getSelectedItem().symtxis(Integer.parseInt(textField1.getText()));
                        list.remove(shapeList.getSelectionModel().getSelectedIndex());
                        list.add(iShape);
                    }
                    break;
                default:
                    showAllertMistake();
                    break;
            }
            try {
                reWriteFile();
            } catch (IOException e) {
                e.printStackTrace();
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
            Stage stage1 = (Stage) move.getScene().getWindow();
            stage1.close();
        });
    }

    private void ini() throws IOException {
        hideAll();
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
            shapeList.setItems(list);
        }
    }

    public void setFile(File file){
        this.file = file;
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

    private void hideAll(){
        textField1.setVisible(false);
        textField2.setVisible(false);
    }

    private void showAllertInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Перемещение фигуры");
        alert.setHeaderText("Фигура перемещена");
        alert.setContentText("Фигура, которую вы выбрали была успешно перемещена");
        alert.showAndWait();
    }

    private void showAllertMistake(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Перемещение фигуры");
        alert.setHeaderText("Фигура не перемещена");
        alert.setContentText("Что-то пошло не так");
        alert.showAndWait();
    }

    public void reWriteFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (IShape i : list){
            System.out.println(i);
        }
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
}
