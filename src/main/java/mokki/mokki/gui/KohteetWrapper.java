package mokki.mokki.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Wrapper-luokka kohteiden tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class KohteetWrapper {
    private StringProperty tunnus;
    private StringProperty sijainti;
    private IntegerProperty huoneita;
    private IntegerProperty pintaAla;
    private IntegerProperty hinta;
    private StringProperty huomioitavaa;

    /** Määrityksiä tarvitaan taulukon luomisessa */
    private String[][] maaritykset;

    /**
     * Luokan alustaja
     * @param tunnus tunnus
     * @param sijainti sijainti
     * @param huoneita huoneita
     * @param pintaAla pinta-ala
     * @param hinta hinta
     * @param huomioitavaa huomioitavaa
     */
    public KohteetWrapper(String tunnus, String sijainti, int huoneita,
                          int pintaAla, int hinta, String huomioitavaa) {

        this.tunnus = new SimpleStringProperty(tunnus);
        this.sijainti = new SimpleStringProperty(sijainti);
        this.huoneita = new SimpleIntegerProperty(huoneita);
        this.pintaAla = new SimpleIntegerProperty(pintaAla);
        this.hinta = new SimpleIntegerProperty(hinta);
        this.huomioitavaa = new SimpleStringProperty(huomioitavaa);

        maaritykset = new String[][] {
                {"Tunnus", "String", "tunnus"},
                {"Sijainti", "String", "sijainti"},
                {"Huoneita", "Integer", "huoneita"},
                {"Pinta-ala", "Integer", "pintaAla"},
                {"Hinta", "Integer", "hinta"},
                {"Huomioitavaa", "String", "huomioitavaa"},
        };
    }

    /**
     * Metodi palauttaa tunnus-kentän arvon.
     * @return tunnus
     */
    public String getTunnus() {
        return tunnus.get();
    }

    /**
     * Metodi palauttaa sijainti-kentän arvon.
     * @return sijainti
     */
    public String getSijainti() {
        return sijainti.get();
    }

    /**
     * Metodi palauttaa huoneita-kentän arvon.
     * @return huoneita
     */
    public int getHuoneita() {
        return huoneita.get();
    }

    /**
     * Metodi palauttaa pinta-ala-kentän arvon.
     * @return pinta-ala
     */
    public int getPintaAla() {
        return pintaAla.get();
    }

    /**
     * Metodi palauttaa hinta-kentän arvon.
     * @return hinta
     */
    public int getHinta() {
        return hinta.get();
    }

    /**
     * Metodi palauttaa huomioitavaa-kentän arvon.
     * @return huomioitavaa
     */
    public String getHuomioitavaa() {
        return huomioitavaa.get();
    }

    /**
     * Metodi palauttaa taulukkomääritykset.
     * @return taulukkomääritykset
     */
    public String[][] getMaaritykset() {
        return maaritykset;
    }
}
