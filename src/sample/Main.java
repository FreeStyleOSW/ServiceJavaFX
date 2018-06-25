package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/mainView.fxml"));
        primaryStage.setTitle("Serwis Komputerowy");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("sample/Resources/tlo.png"));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
