package sample.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usterka {
    private IntegerProperty id_usterka;
    private StringProperty opis;

    public Usterka() {
        this.id_usterka = new SimpleIntegerProperty();
        this.opis = new SimpleStringProperty();
    }

    public Usterka(Usterka another) {
        this.id_usterka = another.id_usterkaProperty();
        this.opis = another.opisProperty();
    }

    public int getId_usterka() {
        return id_usterka.get();
    }

    public IntegerProperty id_usterkaProperty() {
        return id_usterka;
    }

    public void setId_usterka(int id_usterka) {
        this.id_usterka.set(id_usterka);
    }

    public String getOpis() {
        return opis.get();
    }

    public StringProperty opisProperty() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }

    @Override
    public String toString() {
        return this.getOpis();
    }
}
