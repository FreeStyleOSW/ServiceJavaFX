package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DBUtil;
import sample.Model.Serwis;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerwisDAO {
    private static Serwis getSerwisFromResultSet(ResultSet rs) throws SQLException {
        Serwis serwis = null;
        if (rs.next()) {
            serwis = new Serwis();
            serwis.setId_serwis(rs.getInt("id_serwis"));
            serwis.setUsterka_id(rs.getInt("usterka_id"));
            serwis.setCzesc_id(rs.getInt("czesc_id"));
        }
        return serwis;
    }
    public static void insertSerwis(Serwis serwis) throws SQLException, ClassNotFoundException {

        String updateStmt =
                "INSERT INTO serwispc1.serwis" +
                        "(usterka_id, czesc_id)" +
                        "VALUES" +
                        "('"+ serwis.getUsterka_id() +"', '"+ serwis.getCzesc_id() + "')";
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error acccurred while INSERT Operation: " + e);
            throw e;
        }
    }
    // metoda aktualizująca dane gry komputerowej
    public static void updateSerwis(Serwis serwis) throws SQLException, ClassNotFoundException  {
        String updateStmt =
                "UPDATE serwispc1.serwis\n" +
                        "    SET " +
                        "usterka_id = " + serwis.getUsterka_id() + ", " +
                        "czesc_id = " + serwis.getCzesc_id() + " " +
                        "\n" +
                        "    WHERE id_serwis = " + serwis.getId_serwis();
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error accured while UPDATE Operation: " + e);
            throw e;
        }
    }
    // metoda usuwająca grę komputerową
    public static void deleteSerwisWithId(Integer serwisId) throws SQLException,ClassNotFoundException {
        String updateStmt =
                "DELETE FROM serwispc1.serwis\n" +
                        "        WHERE id_serwis = " + serwisId;
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e) {
            System.out.println("Error accured while DELETE Operation: " + e);
            throw e;
        }
    }
    private static ObservableList<Serwis> getSerwisList(ResultSet rs) throws SQLException,ClassNotFoundException{
        ObservableList<Serwis> serwisList = FXCollections.observableArrayList();

        while (rs.next()){
            Serwis serwis = new Serwis();
            serwis.setId_serwis(rs.getInt("id_serwis"));
            serwis.setUsterka_id(rs.getInt("usterka_id"));
            serwis.setCzesc_id(rs.getInt("czesc_id"));
            serwisList.add(serwis);
        }
        return serwisList;
    }
    // metoda pobiera pełną listę gier komputerowych z bazy danych
    public static ObservableList<Serwis> findAllSerwis() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.serwis";
        try {
            ResultSet rsSerwis = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Serwis> serwisList = getSerwisList(rsSerwis);
            return serwisList;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
