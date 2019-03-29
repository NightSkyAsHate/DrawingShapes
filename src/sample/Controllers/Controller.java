package sample.Controllers;

import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Utils.*;
import sample.Utils.Rectangle;

import javax.imageio.ImageIO;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button load;

    @FXML
    private Button save;

    @FXML
    private Button move;

    @FXML
    private Button clean;

    @FXML
    private Button cross;

    @FXML
    private Canvas MyCanvas;

    @FXML
    private TextField textField;

    @FXML
    private Button delete;

    @FXML
    private Button square;

    @FXML
    private Button perimetr;

    @FXML
    private Button screenShot;

    private File file = new File("src\\sample\\Files\\main.txt");
    private String textFieldStroke = "";
    private IShape markedShape;
    private IShape markedShape2;

    @FXML
    void initialize() throws IOException {
        updateScene();
        markShape();

        add.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MoveMenu.class.getResource("../FXML/AddNewFigure.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AddNewFigures controller = loader.getController();
            controller.setFile(file);
            controller.initialize();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Stage stage1 = (Stage) move.getScene().getWindow();
            stage1.close();
        });

        save.setOnAction(event -> {
            if (!textField.getText().equals("")){
                try {
                    PrintWriter printWriter = new PrintWriter("src\\sample\\Files\\" + textField.getText() + ".txt","UTF-8");
                    System.out.println(file);
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    while (bufferedReader.ready()){
                        String s = bufferedReader.readLine();
                        printWriter.println(s);
                    }
                    printWriter.close();
                    file = new File("src\\sample\\Files\\" + textField.getText() + ".txt");
                    cleanMain();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Сохранение файла");
                    alert.setHeaderText("Файл сохранён");
                    alert.setContentText("Вы успешно сохранили всю информация в файл " + textField.getText());
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Сохранение файла");
                alert.setHeaderText("Файл не сохранён");
                alert.setContentText("Не удалось сохранить информацию в файл");
                alert.showAndWait();
            }

        });

        load.setOnAction(event -> {
            Desktop desktop = Desktop.getDesktop();
            FileChooser fileChooser = new FileChooser();
            File recent = this.file;
            this.file = fileChooser.showOpenDialog(load.getScene().getWindow());
            try {
                if (file != null) {
                    initialize();
                }else{
                    this.file = recent;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        screenShot.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*png", "*.png");
            fc.getExtensionFilters().add(extensionFilter);

            File newFile = fc.showSaveDialog(screenShot.getScene().getWindow());

            if (newFile != null){
                WritableImage writableImage = new WritableImage(769,788);
                MyCanvas.snapshot(null,writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
                try {
                    ImageIO.write(renderedImage,"png",newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        move.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MoveMenu.class.getResource("../FXML/MoveMenu.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MoveMenu controller = loader.getController();
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

        delete.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdditionalPanel.class.getResource("../FXML/AdditionalPanel.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AdditionalPanel controller = loader.getController();
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

        clean.setOnAction(event -> {
            cleanCanvas();
            try {
                cleanFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        square.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Square.class.getResource("../FXML/Square.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Square controller = loader.getController();
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

        perimetr.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Square.class.getResource("../FXML/Perimetr.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Perimetr controller = loader.getController();
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

        cross.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Square.class.getResource("../FXML/Cross.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Cross controller = loader.getController();
            controller.setFile(file);
            controller.initialize();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Stage stage1 = (Stage) move.getScene().getWindow();
            stage1.close();
        });
    }

    public void updateScene() throws IOException {
        cleanCanvas();
        setText();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String stroke = reader.readLine();
        while (stroke != null){
            if (stroke.equals("Circle")){
                stroke = reader.readLine();
                String[] arr = stroke.split(" ");
                Point2D point2D = new Point2D();
                String[] points = arr[0].split("&");
                point2D.setX(Double.parseDouble(points[0]),0);
                point2D.setX(Double.parseDouble(points[1]),1);
                Circle circle = new Circle(point2D,Double.parseDouble(arr[1]));
                circle.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Section")){
                stroke = reader.readLine();
                Point2D[] point2DS = getArrayPoint2D(stroke);
                Section section = new Section(point2DS[0],point2DS[1]);
                section.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Broken_line")){
                stroke = reader.readLine();
                Point2D[] point2DS = getArrayPoint2D(stroke);
                Broken_line broken_line = new Broken_line(point2DS.length,point2DS);
                broken_line.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Nshape")){
                stroke = reader.readLine();
                Point2D[] point2D = getArrayPoint2D(stroke);
                Nshape nshape = new Nshape(point2D.length,point2D);
                nshape.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Triangle")){
                stroke = reader.readLine();
                Point2D[] point2DS = getArrayPoint2D(stroke);
                Triangle triangle = new Triangle(point2DS);
                triangle.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Quad")){
                stroke = reader.readLine();
                Point2D[] point2DS = getArrayPoint2D(stroke);
                Quad quad = new Quad(point2DS);
                quad.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Rectangle")){
                stroke = reader.readLine();
                Point2D[] point2DS = getArrayPoint2D(stroke);
                Rectangle quad = new Rectangle(point2DS);
                quad.draw(MyCanvas,Color.BLACK);
            }
            if (stroke.equals("Trapeciya")){
                stroke = reader.readLine();
                Point2D[] point2DS = getArrayPoint2D(stroke);
                Trapeciya quad = new Trapeciya(point2DS);
                quad.draw(MyCanvas,Color.BLACK);
            }
            stroke = reader.readLine();
        }
    }

    public void cleanCanvas(){
        GraphicsContext g = MyCanvas.getGraphicsContext2D();
        g.clearRect(0,0,10000,10000);
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.5);
        g.strokeLine(384.5,0,384.5,850);
        g.strokeLine(0,394,769,394);
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

    public void cleanMain() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("src\\sample\\Files\\main.txt"));
        fileWriter.flush();
        fileWriter.close();
    }

    private void cleanFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.flush();
        fileWriter.close();
    }

    public void setText(){
        textField.setText(this.textFieldStroke);
    }

    public void setStroke(String stroke){
        this.textFieldStroke = stroke;
    }

    public void setFile(File file){
        this.file = file;
    }

    public void markShape(){
        if (markedShape != null) {
            markedShape.draw(MyCanvas, Color.RED);
        }
        if (markedShape2 != null){
            markedShape2.draw(MyCanvas, Color.RED);
        }
    }

    public void setMarkedShape(IShape iShape){
        markedShape = iShape;
    }

    public void setMarkedShape2(IShape iShape){
        markedShape2 = iShape;
    }
}
