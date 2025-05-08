package mokki.mokki.gui.testiluokatTaulukonDatalle;


import javafx.beans.property.*;
import mokki.mokki.gui.alipaneeli.TaulukonData;

/**
 * Wrapper-luokka laskujen tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class LaskutWrapper implements TaulukonData {
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

    /**
     * Metodi palauttaa tietokokonaisuuden tunnisteen eli laskun numeron.
     *
     * @return tunniste
     */
    public String palautaTunniste() {
        return "" + laskunumero.get();
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin eli laskun numeron.
     *
     * @return tunniste
     */
    public String palautaKuvausteksti() {
        return "" + laskunumero.get();
    }

    /**
     * Metodi palauttaa kenttien arvot merkkijonolistana.
     * @return kenttien arvot
     */
    public String[] palautaKenttienArvot() {
        return new String[] {""+laskunumero.get(), tuote.get(), asiakas.get(),
                ""+viitenumero.get(), ""+maksettava.get(), tila.get()};
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        return true;
    }

    public boolean paivitaKenttienArvot(String[] arvot) {
        try {
            this.laskunumero.set(Integer.parseInt(arvot[0]));
            this.tuote.set(arvot[1]);
            this.asiakas.set(arvot[2]);
            this.viitenumero.set(Integer.parseInt(arvot[3]));
            this.maksettava.set(Double.parseDouble(arvot[4]));
            this.tila.set(arvot[5]);
            return true;
        } catch (Exception e) {
            return false; // Virheellinen syöte
        }
    }

    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        boolean[] totuusarvolista = new boolean[arvot.length];
        for (int i = 0; i < arvot.length; i++) {
            totuusarvolista[i] = true;
        }
        return totuusarvolista;
    }

    public boolean onkoTunnisteUniikki(String tunniste) {
        // TODO: tarkista, että tunnistetta ei löydy tietokannasta.
        return false;
    }

    public int palautaTunnisteenIndeksi() {
        return 0;
    }

    public void setTila(String uusiTila) {
        this.tila.set(uusiTila);
    }
}