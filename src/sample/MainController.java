package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainController {

    @FXML
    Button btnParts;
    @FXML
    Button btnUsterki;
    @FXML
    Button btnSerwis;

    @FXML // inicjalizacja tabeli z bazy danych
    private void initialize() throws SQLException, ClassNotFoundException {

        System.out.println("START APP...");
    }

    public void pressBtnParts(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/partView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.getIcons().add(new Image("sample/Resources/tlo.png"));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pressBtnUsterki(ActionEvent event) throws Exception {
        try {
            System.out.println("DOSZEDL");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/usterkaView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.getIcons().add(new Image("sample/Resources/tlo.png"));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pressBtnSerwis(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/serwisView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.getIcons().add(new Image("sample/Resources/tlo.png"));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
