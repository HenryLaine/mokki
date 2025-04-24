package mokki.mokki.gui;


import javafx.beans.property.*;

/**
 * Wrapper-luokka laskujen tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class LaskutWrapper {
    private IntegerProperty laskunumero;
    private StringProperty tuote;
    private StringProperty asiakas;
    private IntegerProperty viitenumero;
    private DoubleProperty maksettava;
    private StringProperty tila;

    /** Taulukkomääritykset, joita tarvitaan taulukon luomisessa */
    private String[][] maaritykset;

    /**
     * Luokan alustaja
     * @param laskunumero laskunumero
     * @param tuote tuote
     * @param asiakas asiakas
     * @param viitenumero viitenumero
     * @param maksettava maksettava määrä
     * @param tila tila
     */
    public LaskutWrapper(int laskunumero, String tuote, String asiakas,
                         int viitenumero, double maksettava, String tila) {

        this.laskunumero = new SimpleIntegerProperty(laskunumero);
        this.tuote = new SimpleStringProperty(tuote);
        this.asiakas = new SimpleStringProperty(asiakas);
        this.viitenumero = new SimpleIntegerProperty(viitenumero);
        this.maksettava = new SimpleDoubleProperty(maksettava);
        this.tila = new SimpleStringProperty(tila);

        maaritykset = new String[][] {
                {"Laskunumero", "Integer", "laskunumero"},
                {"Tuote", "String", "tuote"},
                {"Asiakas", "String", "asiakas"},
                {"Viitenumero", "Integer", "viitenumero"},
                {"Maksettava", "Double", "maksettava"},
                {"Tila", "String", "tila"}
        };
    }

    /**
     * Metodi palauttaa laskunumero-kentän arvon.
     * @return laskunumero
     */
    public int getLaskunumero() {
        return laskunumero.get();
    }

    /**
     * Metodi palauttaa tuote-kentän arvon.
     * @return tuote
     */
    public String getTuote() {
        return tuote.get();
    }

    /**
     * Metodi palauttaa asiakas-kentän arvon.
     * @return asiakas
     */
    public String getAsiakas() {
        return asiakas.get();
    }

    /**
     * Metodi palauttaa viitenumero-kentän arvon.
     * @return viitenumero
     */
    public int getViitenumero() {
        return viitenumero.get();
    }

    /**
     * Metodi palauttaa maksettava-kentän arvon.
     * @return maksettava määrä
     */
    public double getMaksettava() {
        return maksettava.get();
    }

    /**
     * Metodi palauttaa tila-kentän arvon.
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