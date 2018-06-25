package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.DAO.PartDAO;
import sample.DAO.SerwisDAO;
import sample.DAO.UsterkaDAO;
import sample.Model.Part;
import sample.Model.Serwis;
import sample.Model.Usterka;

import java.sql.SQLException;

public class SerwisController {
    private Integer _id_serwis;

    @FXML
    TableView serwisTable;
    @FXML
    private TableColumn<Serwis, String> usterkaColumn;
    @FXML
    private TableColumn<Serwis, String> czescColumn;
    @FXML
    ComboBox usterkaCombo;
    @FXML
    ComboBox czescCombo;




    @FXML // inicjalizacja tabeli z bazy danych
    private void initialize() throws SQLException, ClassNotFoundException {
        usterkaColumn.setCellValueFactory(cellData -> {
            try {
                return cellData.getValue().opis_usterkaProperty();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });
        czescColumn.setCellValueFactory(cellData -> {
            try {
                return cellData.getValue().nazwa_czescProperty();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });
        loadTableFromDataBase();
    }
    private void loadTableFromDataBase() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Serwis> serwisData = SerwisDAO.findAllSerwis();
            serwisTable.setItems(serwisData);
            ObservableList<Usterka> usterkaComboList = UsterkaDAO.findAllUsterki();
            usterkaCombo.setItems(usterkaComboList);
            ObservableList<Part> czescComboList = PartDAO.findAllParts();
            czescCombo.setItems(czescComboList);
            System.out.println("REFRESH TABLE");
        } catch (SQLException e) {
            System.out.println("Error accured while getting usterki information from DB.\n");
            throw e;
        }
    }
    @FXML
    private void setSerwisInputsFromTable() throws SQLException, ClassNotFoundException {
        if (serwisTable.getSelectionModel().getSelectedItem() != null) {
            Serwis serwis = (Serwis) serwisTable.getSelectionModel().getSelectedItem();
            _id_serwis = serwis.getId_serwis();
            Usterka usterka = UsterkaDAO.getUsterka(serwis.getUsterka_id());
            Part part = PartDAO.getPart(serwis.getCzesc_id());
            usterkaCombo.setValue(usterka.getOpis());
            czescCombo.setValue(part.getNazwa());
        }
    }

    @FXML
    private void insertSerwis() throws SQLException, ClassNotFoundException {
        Serwis serwis = new Serwis();
        Usterka usterka = new Usterka((Usterka) usterkaCombo.getValue());
        Part part = new Part((Part) czescCombo.getValue());
        serwis.setUsterka_id(usterka.getId_usterka());
        serwis.setCzesc_id(part.getId_czesc());
        SerwisDAO.insertSerwis(serwis);
        loadTableFromDataBase();
        clearInput();
    }
    @FXML
    private void updateSerwis() throws SQLException, ClassNotFoundException {
        Serwis serwis = new Serwis();
        Usterka usterka = UsterkaDAO.getUsterka(usterkaCombo.getValue().toString());
        Part part = PartDAO.getPart(czescCombo.getValue().toString());
        serwis.setId_serwis(_id_serwis);
        serwis.setCzesc_id(part.getId_czesc());
        serwis.setUsterka_id(usterka.getId_usterka());
        SerwisDAO.updateSerwis(serwis);
        loadTableFromDataBase();
        clearInput();
    }
    @FXML
    private void deleteSerwis() throws SQLException, ClassNotFoundException {
        SerwisDAO.deleteSerwisWithId(_id_serwis);
        loadTableFromDataBase();
        clearInput();
    }

    private void clearInput(){
        _id_serwis = null;
        usterkaCombo.setValue(null);
        czescCombo.setValue(null);
    }
}
