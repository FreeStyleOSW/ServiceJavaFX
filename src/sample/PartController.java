package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Model.Part;
import sample.DAO.PartDAO;

import java.sql.SQLException;

public class PartController {
    private Integer id_czesci;


    @FXML
    private TextField nazwafield;

    @FXML
    private TextField kosztfield;

    @FXML
    private TextField czasfield;

    @FXML
    private TextField sposobfield;

    @FXML
    private TableView partsTable;

    @FXML
    private TableColumn<Part, Integer> idColumn;
    @FXML
    private TableColumn<Part, String> nazwaColumn;
    @FXML
    private TableColumn<Part, Integer> kosztColumn;
    @FXML
    private TableColumn<Part, Integer> czasnaprawyColumn;
    @FXML
    private TableColumn<Part, String> sposobnaprawyColumn;

    @FXML // inicjalizacja tabeli z bazy danych
    private void initialize() throws SQLException, ClassNotFoundException {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().id_czescProperty().asObject());
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        kosztColumn.setCellValueFactory(cellData -> cellData.getValue().kosztProperty().asObject());
        czasnaprawyColumn.setCellValueFactory(cellData -> cellData.getValue().czasnaprawyProperty().asObject());
        sposobnaprawyColumn.setCellValueFactory(cellData -> cellData.getValue().sposobnaprawyProperty());
        loadTableFromDataBase();
    }

    private void loadTableFromDataBase() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Part> partData = PartDAO.findAllParts();
            partsTable.setItems(partData);
            System.out.println("REFRESH TABLE");
        } catch (SQLException e) {
            System.out.println("Error accured while getting parts information from DB.\n");
            throw e;
        }
    }

    @FXML // metoda, która pobiera z tabeli tytuł zaznaczonej gry
    private void setPartIdTextFromTable() {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
            id_czesci = part.getId_czesc();
            nazwafield.setText(String.valueOf(part.getNazwa()));
            kosztfield.setText(String.valueOf(part.getKoszt()));
            czasfield.setText(String.valueOf(part.getCzasnaprawy()));
            sposobfield.setText(String.valueOf(part.getSposobnaprawy()));
        }
    }
    @FXML
    private void insertPart() throws SQLException, ClassNotFoundException {
        Part part = new Part();
        part.setNazwa(nazwafield.getText());
        part.setKoszt(Integer.valueOf(kosztfield.getText()));
        part.setCzasnaprawy(Integer.valueOf(czasfield.getText()));
        part.setSposobnaprawy(sposobfield.getText());
        PartDAO.insertPart(part);
        loadTableFromDataBase();
        clearInput();
    }
    @FXML
    private void updatePart() throws SQLException, ClassNotFoundException {
        Part part = new Part();
        part.setId_czesc(id_czesci);
        part.setNazwa(nazwafield.getText());
        part.setKoszt(Integer.valueOf(kosztfield.getText()));
        part.setCzasnaprawy(Integer.valueOf(czasfield.getText()));
        part.setSposobnaprawy(sposobfield.getText());
        PartDAO.updatePart(part);
        loadTableFromDataBase();
        clearInput();
    }
    @FXML
    private void deletePart() throws SQLException, ClassNotFoundException {
        PartDAO.deletePartWithId(id_czesci);
        loadTableFromDataBase();
        clearInput();
    }

    private void clearInput(){
        id_czesci = null;
        nazwafield.clear();
        kosztfield.clear();
        czasfield.clear();
        sposobfield.clear();
    }
}
