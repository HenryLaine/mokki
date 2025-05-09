package mokki.mokki.gui.testiluokatTaulukonDatalle;

import javafx.beans.property.*;
import mokki.mokki.gui.alipaneeli.TaulukonData;

import java.time.LocalDate;
import java.util.Date;

/**
 * Wrapper-luokka varausten tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class VarauksetWrapper implements TaulukonData {
    private StringProperty tunnus;
    private StringProperty kohteenTunnus;
    private StringProperty asiakas;
    private ObjectProperty<LocalDate> alkaa;
    private ObjectProperty<LocalDate> paattyy;
    private StringProperty tila;
    private StringProperty huomioitavaa;

    private String asiakkaanNimi;
    private String asiakkaanSahkoposti;
    private Date alkamisPaiva;
    private Date loppumisPaiva;

    /** Taulukkomääritykset, joita tarvitaan taulukon luomisessa */
    private String[][] maaritykset;


    public VarauksetWrapper(String tunnus, String kohteenTunnus, String asiakkaanNimi, String asiakkaanSahkoposti,
                            LocalDate alkaa, LocalDate paattyy, String tila, String huomioitavaa) {

        this.asiakkaanNimi = asiakkaanNimi;
        this.asiakkaanSahkoposti = asiakkaanSahkoposti;

        this.tunnus = new SimpleStringProperty(tunnus);
        this.kohteenTunnus = new SimpleStringProperty(kohteenTunnus);
        this.asiakas = new SimpleStringProperty(asiakkaanNimi + " (" +asiakkaanSahkoposti + ")");
        this.alkaa = new SimpleObjectProperty<>(alkaa);
        this.paattyy = new SimpleObjectProperty<>(paattyy);
        this.tila = new SimpleStringProperty(tila);
        this.huomioitavaa = new SimpleStringProperty(huomioitavaa);

        maaritykset = new String[][] {
                {"Tunnus", "String", "tunnus"},
                {"Kohteen tunnus", "String", "kohteenTunnus"},
                {"Asiakas", "String", "asiakas"},
                {"Alkaa", "LocalDate", "alkaa"},
                {"Päättyy", "LocalDate", "paattyy"},
                {"Tila", "String", "tila"},
                {"Huomioitavaa", "String", "huomioitavaa"},
        };
    }

    public String getAsiakkaanSahkoposti() {
        return asiakkaanSahkoposti;
    }

    public String getAsiakkaanNimi() {
        return asiakkaanNimi;
    }

    public Date getAlkamisPaiva() {
        return alkamisPaiva;
    }

    public Date getLoppumisPaiva() {
        return loppumisPaiva;
    }

    /**
     * Metodi palauttaa tunnus-kentän arvon.
     * @return tunnus
     */
    public String getTunnus() {
        return tunnus.get();
    }

    /**
     * Metodi palauttaa kohteenTunnus-kentän arvon.
     * @return kohteen tunnus
     */
    public String getKohteenTunnus() {
        return kohteenTunnus.get();
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
    public LocalDate getAlkaa() {
        return alkaa.get();
    }

    public ObjectProperty<LocalDate> alkaaProperty() {
        if (alkaa == null) {
            alkaa = new SimpleObjectProperty<>(this, maaritykset[5][2]);
        }
        return alkaa;
    }

    public void setAlkaa(LocalDate alkaa) {
        alkaaProperty().set(alkaa);
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
    public LocalDate getPaattyy() {
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

    /**
     * Metodi palauttaa tietokokonaisuuden tunnisteen eli varauksen tunnuksen.
     *
     * @return tunniste
     */
    public String palautaTunniste() {
        return tunnus.get();
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin eli varauksen tunnuksen.
     *
     * @return kuvausteksti
     */
    public String palautaKuvausteksti() {
        return tunnus.get();
    }

    /**
     * Metodi palauttaa kenttien arvot merkkijonolistana.
     * @return kenttien arvot
     */
    public String[] palautaKenttienArvot() {
        return new String[] {tunnus.get(), kohteenTunnus.get(), asiakas.get(),
                String.valueOf(alkaa.get()), String.valueOf(paattyy.get()), tila.get(), huomioitavaa.get()};
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        return false;
    }

    public boolean paivitaKenttienArvot(String[] arvot) {
        return true;
    }

    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        boolean[] totuusarvolista = new boolean[arvot.length];
        for (int i = 0; i < arvot.length; i++) {
            totuusarvolista[i] = false;
        }
        return totuusarvolista;
    }

    public boolean onkoTunnisteUniikki(String tunniste) {
        // TODO: tarkista, että tunnistetta ei löydy tietokannasta.
        return true;
    }

    public int palautaTunnisteenIndeksi() {
        return 0;
    }

    public boolean onkoSahkopostiTietokannassa(String asiakkaanSahkoposti) {
        return true;
    }

}
