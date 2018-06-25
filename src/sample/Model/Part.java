package sample.Model;

import javafx.beans.property.*;

public class Part {
    private IntegerProperty id_czesc;
    private StringProperty nazwa;
    private IntegerProperty koszt;
    private IntegerProperty czasnaprawy;
    private StringProperty sposobnaprawy;


    public Part() {
        this.id_czesc = new SimpleIntegerProperty();
        this.nazwa = new SimpleStringProperty();
        this.koszt = new SimpleIntegerProperty();
        this.czasnaprawy = new SimpleIntegerProperty();
        this.sposobnaprawy = new SimpleStringProperty();
    }

    public Part(Part another) {
        this.id_czesc = another.id_czescProperty();
        this.nazwa = another.nazwaProperty();
        this.koszt = another.kosztProperty();
        this.czasnaprawy = another.czasnaprawyProperty();
        this.sposobnaprawy = another.sposobnaprawyProperty();
    }

    public int getId_czesc() {
        return id_czesc.get();
    }

    public IntegerProperty id_czescProperty() {
        return id_czesc;
    }

    public void setId_czesc(int id_czesc) {
        this.id_czesc.set(id_czesc);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public int getKoszt() {
        return koszt.get();
    }

    public IntegerProperty kosztProperty() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt.set(koszt);
    }

    public int getCzasnaprawy() {
        return czasnaprawy.get();
    }

    public IntegerProperty czasnaprawyProperty() {
        return czasnaprawy;
    }

    public void setCzasnaprawy(int czasnaprawy) {
        this.czasnaprawy.set(czasnaprawy);
    }

    public String getSposobnaprawy() {
        return sposobnaprawy.get();
    }

    public StringProperty sposobnaprawyProperty() {
        return sposobnaprawy;
    }

    public void setSposobnaprawy(String sposobnaprawy) {
        this.sposobnaprawy.set(sposobnaprawy);
    }

    @Override
    public String toString() {
        return this.getNazwa();
    }
}
