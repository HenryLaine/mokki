package mokki.mokki.gui;

import javafx.beans.property.*;

public class RaportitWrapper {
    private StringProperty kohde;
    private IntegerProperty kayttoaste;
    private IntegerProperty varaustenMaara;
    private DoubleProperty paivatulot;
    private DoubleProperty viikkotulot;
    private DoubleProperty kuukausitulot;
    private DoubleProperty kokonaistulot;

    private String[][] maaritykset;

    public RaportitWrapper(String kohde, int kayttoaste, int varaustenMaara,
                           double paivatulot, double viikkotulot, double kuukausitulot, double kokonaistulot) {

        this.kohde = new SimpleStringProperty(kohde);
        this.kayttoaste = new SimpleIntegerProperty(kayttoaste);
        this.varaustenMaara = new SimpleIntegerProperty(varaustenMaara);
        this.paivatulot = new SimpleDoubleProperty(paivatulot);
        this.viikkotulot = new SimpleDoubleProperty(viikkotulot);
        this.kuukausitulot = new SimpleDoubleProperty(kuukausitulot);
        this.kokonaistulot = new SimpleDoubleProperty(kokonaistulot);

        maaritykset = new String[][] {
                {"Kohde", "String", "kohde"},
                {"Käyttöaste", "Integer", "kayttoaste"},
                {"Varausten määrä", "Integer", "varaustenMaara"},
                {"Päivätulot", "Double", "paivatulot"},
                {"Viikkotulot", "Double", "viikkotulot"},
                {"Kuukausitulot", "Double", "kuukausitulot"},
                {"Kokonaistulot", "Double", "kokonaistulot"}
        };
    }

    public String getKohde() {
        return kohde.get();
    }

    public int getKayttoaste() {
        return kayttoaste.get();
    }

    public int getVaraustenMaara() {
        return varaustenMaara.get();
    }

    public double getPaivatulot() {
        return paivatulot.get();
    }

    public double getViikkotulot() {
        return viikkotulot.get();
    }

    public double getKuukausitulot() {
        return kuukausitulot.get();
    }

    public double getKokonaistulot() {
        return kokonaistulot.get();
    }

    public String[][] getMaaritykset() {
        return maaritykset;
    }
}
