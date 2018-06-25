package sample.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.DAO.PartDAO;
import sample.DAO.UsterkaDAO;

import java.sql.SQLException;

public class Serwis {
    private IntegerProperty id_serwis;
    private IntegerProperty usterka_id;
    private IntegerProperty czesc_id;
    private StringProperty opis_usterka;
    private StringProperty nazwa_czesc;

    public Serwis() {
        this.id_serwis = new SimpleIntegerProperty();
        this.usterka_id = new SimpleIntegerProperty();
        this.czesc_id = new SimpleIntegerProperty();
    }

    public Serwis(Serwis another) {
        this.id_serwis = another.id_serwisProperty();
        this.usterka_id = another.usterka_idProperty();
        this.czesc_id = another.czesc_idProperty();
    }

    public int getId_serwis() {
        return id_serwis.get();
    }

    public IntegerProperty id_serwisProperty() {
        return id_serwis;
    }

    public void setId_serwis(int id_serwis) {
        this.id_serwis.set(id_serwis);
    }

    public int getUsterka_id() {
        return usterka_id.get();
    }

    public IntegerProperty usterka_idProperty() {
        return usterka_id;
    }

    public void setUsterka_id(int usterka_id) {
        this.usterka_id.set(usterka_id);
    }

    public int getCzesc_id() {
        return czesc_id.get();
    }

    public IntegerProperty czesc_idProperty() {
        return czesc_id;
    }

    public void setCzesc_id(int czesc_id) {
        this.czesc_id.set(czesc_id);
    }

    public String getOpis_usterka() {
        return opis_usterka.get();
    }

    public StringProperty opis_usterkaProperty() throws SQLException, ClassNotFoundException {
        Usterka usterka = UsterkaDAO.getUsterka(getUsterka_id());
        return new SimpleStringProperty(usterka.getOpis());
    }

    public String getNazwa_czesc() {
        return nazwa_czesc.get();
    }

    public StringProperty nazwa_czescProperty() throws SQLException, ClassNotFoundException {
        Part part = PartDAO.getPart(getCzesc_id());
        return new SimpleStringProperty(part.getNazwa());
    }
}
