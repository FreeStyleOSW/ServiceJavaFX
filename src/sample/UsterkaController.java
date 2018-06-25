package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Model.Usterka;
import sample.DAO.UsterkaDAO;

import java.sql.SQLException;

public class UsterkaController {
    private Integer id_usterka;


    @FXML
    private TextField nazwafield;

    @FXML
    private TableView usterkiTable;

    @FXML
    private TableColumn<Usterka, Integer> idColumn;
    @FXML
    private TableColumn<Usterka, String> nazwaColumn;

    @FXML // inicjalizacja tabeli z bazy danych
    private void initialize() throws SQLException, ClassNotFoundException {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().id_usterkaProperty().asObject());
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().opisProperty());
        loadTableFromDataBase();
    }

    private void loadTableFromDataBase() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Usterka> usterkaData = UsterkaDAO.findAllUsterki();
            usterkiTable.setItems(usterkaData);
            System.out.println("REFRESH TABLE");
        } catch (SQLException e) {
            System.out.println("Error accured while getting usterki information from DB.\n");
            throw e;
        }
    }

    @FXML
    private void setUsterkaIdTextFromTable() {
        if (usterkiTable.getSelectionModel().getSelectedItem() != null) {
            Usterka usterka = (Usterka) usterkiTable.getSelectionModel().getSelectedItem();
            id_usterka = usterka.getId_usterka();
            nazwafield.setText(String.valueOf(usterka.getOpis()));
        }
    }
    @FXML
    private void insertUsterka() throws SQLException, ClassNotFoundException {
        Usterka usterka = new Usterka();
        usterka.setOpis(nazwafield.getText());
        UsterkaDAO.insertUsterka(usterka);
        loadTableFromDataBase();
        clearInput();
    }
    @FXML
    private void updateUsterka() throws SQLException, ClassNotFoundException {
        Usterka usterka = new Usterka();
        usterka.setId_usterka(id_usterka);
        usterka.setOpis(nazwafield.getText());
        UsterkaDAO.updateUsterka(usterka);
        loadTableFromDataBase();
        clearInput();
    }
    @FXML
    private void deleteUsterka() throws SQLException, ClassNotFoundException {
        UsterkaDAO.deleteUsterkaWithId(id_usterka);
        loadTableFromDataBase();
        clearInput();
    }

    private void clearInput(){
        id_usterka = null;
        nazwafield.clear();
    }
}
