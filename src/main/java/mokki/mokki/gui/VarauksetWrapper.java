package mokki.mokki.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Wrapper-luokka varausten tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class VarauksetWrapper {
    private StringProperty tunnus;
    private StringProperty mokinTunnus;
    private StringProperty asiakas;
    private StringProperty alkaa;
    private StringProperty paattyy;
    private StringProperty tila;
    private StringProperty huomioitavaa;

    /** Taulukkomääritykset, joita tarvitaan taulukon luomisessa */
    private String[][] maaritykset;

    /**
     * Luokan alustaja
     * @param tunnus tunnus
     * @param mokinTunnus mökin tunnus
     * @param asiakas asiakas
     * @param alkaa alkaa
     * @param paattyy päättyy
     * @param tila tila
     * @param huomioitavaa huomioitavaa
     */
    public VarauksetWrapper(String tunnus, String mokinTunnus, String asiakas,
                            String alkaa, String paattyy, String tila, String huomioitavaa) {

        this.tunnus = new SimpleStringProperty(tunnus);
        this.mokinTunnus = new SimpleStringProperty(mokinTunnus);
        this.asiakas = new SimpleStringProperty(asiakas);
        this.alkaa = new SimpleStringProperty(alkaa);
        this.paattyy = new SimpleStringProperty(paattyy);
        this.tila = new SimpleStringProperty(tila);
        this.huomioitavaa = new SimpleStringProperty(huomioitavaa);

        maaritykset = new String[][] {
                {"Tunus", "String", "tunnus"},
                {"Mökin tunnus", "String", "mokinTunnus"},
                {"Asiakas", "String", "asiakas"},
                {"Alkaa", "String", "alkaa"},
                {"Päättyy", "String", "paattyy"},
                {"Tila", "String", "tila"},
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
     * Metodi palauttaa mökinTunnus-kentän arvon.
     * @return mökin tunnus
     */
    public String getMokinTunnus() {
        return mokinTunnus.get();
    }

    /**
     * Metodi palauttaa huomioitavaa-kentän arvon.
     * @return huomioitavaa
     */
    public String getHuomioitavaa() {
        return huomioitavaa.get();
    }

    /**
     * Metodi palauttaa alkaa-kentän arvon.
     * @return alkaa
     */
    public String getAlkaa() {
        return alkaa.get();
    }

    /**
     * Metodi palauttaa asiakas-kentän arvon.
     * @return asiakas
     */
    public String getAsiakas() {
        return asiakas.get();
    }

    /**
     * Metodi palauttaa päättyy-kentän arvon
     * @return päättyy
     */
    public String getPaattyy() {
        return paattyy.get();
    }

    /**
     * Metodi palauttaa tila-kentän arvon
     * @return tila
     */
    public String getTila() {
        return tila.get();
    }

    /**
     * Metodi palauttaa taulukkomääritykset.
     * @return taulukkomääritykset
     */
    public String[][] getMaaritykset() {
        return maaritykset;
    }
}
