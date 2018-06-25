package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DBUtil;
import sample.Model.Part;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartDAO {
    // Zamienia Obiekt typu ResultSet na obiekt typu Part
    private static Part getPartFromResultSet(ResultSet rs) throws SQLException {
        Part part = null;
        if (rs.next()) {
            part = new Part();
            part.setId_czesc(rs.getInt("id_czesc"));
            part.setNazwa(rs.getString("nazwa"));
            part.setKoszt(rs.getInt("koszt"));
            part.setCzasnaprawy(rs.getInt("czasnaprawy"));
            part.setSposobnaprawy(rs.getString("sposobnaprawy"));
        }
        return part;
    }
    // metoda dodawająca grę komputerową do bazy danych
    public static void insertPart (Part part) throws SQLException, ClassNotFoundException {

        String updateStmt =
                "INSERT INTO serwispc1.czesci" +
            "(nazwa, koszt, czasnaprawy, sposobnaprawy)" +
                    "VALUES" +
                    "('"+ part.getNazwa() + "', '" + part.getKoszt() + "', '" + part.getCzasnaprawy() +"', '"+ part.getSposobnaprawy() +"')";
            try {
                System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error acccurred while INSERT Operation: " + e);
            throw e;
        }
    }
    // metoda aktualizująca dane gry komputerowej
    public static void updatePart (Part part) throws SQLException, ClassNotFoundException  {
        String updateStmt =
                "UPDATE serwispc1.czesci\n" +
                        "    SET " +
                        "nazwa = '" + part.getNazwa() + "', " +
                        "koszt = '" + part.getKoszt() + "', " +
                        "czasnaprawy = '" + part.getCzasnaprawy() + "', " +
                        "sposobnaprawy = '" + part.getSposobnaprawy() + "'" +
                        "\n" +
                        "    WHERE id_czesc = " + part.getId_czesc();
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error accured while UPDATE Operation: " + e);
            throw e;
        }
    }
    // metoda usuwająca grę komputerową
    public static void deletePartWithId (Integer partId) throws SQLException,ClassNotFoundException {
        String updateStmt =
                "DELETE FROM serwispc1.czesci\n" +
                        "        WHERE id_czesc = " + partId;
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e) {
            System.out.println("Error accured while DELETE Operation: " + e);
            throw e;
        }
    }
    // metoda tworzy listę obserwowaną. Dodaje obiekty typ Part do listy
    private static ObservableList<Part> getPartList(ResultSet rs) throws SQLException,ClassNotFoundException{
        ObservableList<Part> partsList = FXCollections.observableArrayList();

        while (rs.next()){
            Part part = new Part();
            part.setId_czesc(rs.getInt("id_czesc"));
            part.setNazwa(rs.getString("nazwa"));
            part.setKoszt(rs.getInt("koszt"));
            part.setCzasnaprawy(rs.getInt("czasnaprawy"));
            part.setSposobnaprawy(rs.getString("sposobnaprawy"));
            partsList.add(part);
        }
        return partsList;
    }
    // metoda pobiera pełną listę gier komputerowych z bazy danych
    public static ObservableList<Part> findAllParts() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.czesci";
        try {
            ResultSet rsParts = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Part> partList = getPartList(rsParts);
            return partList;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static Part getPart(Integer partId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.czesci WHERE id_czesc = " + partId ;
        try {
            ResultSet rsPart = DBUtil.dbExecuteQuery(selectStmt);
            Part part = getPartFromResultSet(rsPart);
            return part;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static Part getPart(String partString) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.czesci WHERE nazwa=\'" + partString  + "\'";
        try {
            ResultSet rsPart = DBUtil.dbExecuteQuery(selectStmt);
            Part part = getPartFromResultSet(rsPart);
            return part;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
