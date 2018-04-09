package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.datamodel.BookData;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Biblioteka");
        primaryStage.setScene(new Scene(root, 1000, 400));
        Image image = new Image("/sample/ksiazka.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
