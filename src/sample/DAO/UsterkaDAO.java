package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DBUtil;
import sample.Model.Usterka;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsterkaDAO {
    private static Usterka getUsterkaFromResultSet(ResultSet rs) throws SQLException {
        Usterka usterka = null;
        if (rs.next()) {
            usterka = new Usterka();
            usterka.setId_usterka(rs.getInt("id_usterka"));
            usterka.setOpis(rs.getString("opis"));
        }
        return usterka;
    }
    public static void insertUsterka(Usterka usterka) throws SQLException, ClassNotFoundException {

        String updateStmt =
                "INSERT INTO serwispc1.usterki" +
                        "(opis)" +
                        "VALUES" +
                        "('"+ usterka.getOpis() +"')";
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error acccurred while INSERT Operation: " + e);
            throw e;
        }
    }
    // metoda aktualizująca dane gry komputerowej
    public static void updateUsterka(Usterka usterka) throws SQLException, ClassNotFoundException  {
        String updateStmt =
                "UPDATE serwispc1.usterki\n" +
                        "    SET " +
                        "opis = '" + usterka.getOpis() + "' " +
                        "\n" +
                        "    WHERE id_usterka = " + usterka.getId_usterka();
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error accured while UPDATE Operation: " + e);
            throw e;
        }
    }
    // metoda usuwająca grę komputerową
    public static void deleteUsterkaWithId(Integer usterkaId) throws SQLException,ClassNotFoundException {
        String updateStmt =
                "DELETE FROM serwispc1.usterki\n" +
                        "        WHERE id_usterka = " + usterkaId;
        try {
            System.out.println(updateStmt);
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e) {
            System.out.println("Error accured while DELETE Operation: " + e);
            throw e;
        }
    }
    private static ObservableList<Usterka> getUsterkaList(ResultSet rs) throws SQLException,ClassNotFoundException{
        ObservableList<Usterka> usterkaList = FXCollections.observableArrayList();

        while (rs.next()){
            Usterka usterka = new Usterka();
            usterka.setId_usterka(rs.getInt("id_usterka"));
            usterka.setOpis(rs.getString("opis"));
            usterkaList.add(usterka);
        }
        return usterkaList;
    }
    // metoda pobiera pełną listę gier komputerowych z bazy danych
    public static ObservableList<Usterka> findAllUsterki() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.usterki";
        try {
            ResultSet rsUsterki = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Usterka> usterkaList = getUsterkaList(rsUsterki);
            return usterkaList;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static Usterka getUsterka(Integer usterkaId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.usterki WHERE id_usterka = " + usterkaId ;
        try {
            ResultSet rsUsterki = DBUtil.dbExecuteQuery(selectStmt);
            Usterka usterka = getUsterkaFromResultSet(rsUsterki);
            return usterka;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static Usterka getUsterka(String usterkaString) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM serwispc1.usterki WHERE opis=\'" + usterkaString + "\'";
        try {
            ResultSet rsUsterki = DBUtil.dbExecuteQuery(selectStmt);
            Usterka usterka = getUsterkaFromResultSet(rsUsterki);
            return usterka;
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


}
