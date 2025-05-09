package mokki.mokki.gui.testiluokatTaulukonDatalle;

import javafx.beans.property.*;
import mokki.mokki.gui.alipaneeli.TaulukonData;

import java.time.LocalDate;

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

    public void setTunnus(String tunnus) {
        tunnusProperty().set(tunnus);
    }

    public String getTunnus() {
        return tunnusProperty().get();
    }

    public StringProperty tunnusProperty() {
        if (tunnus == null) {
            tunnus = new SimpleStringProperty(this, maaritykset[0][2]);
        }
        return tunnus;
    }

    public void setKohteenTunnus(String kohteenTunnus) {
        kohteenTunnusProperty().set(kohteenTunnus);
    }

    public String getKohteenTunnus() {
        return kohteenTunnusProperty().get();
    }

    public StringProperty kohteenTunnusProperty() {
        if (kohteenTunnus == null) {
            kohteenTunnus = new SimpleStringProperty(this, maaritykset[1][2]);
        }
        return kohteenTunnus;
    }

    public void setAsiakas(String asiakas) {
        asiakasProperty().set(asiakas);
    }

    public String getAsiakas() {
        return asiakasProperty().get();
    }

    public StringProperty asiakasProperty() {
        if (asiakas == null) {
            asiakas = new SimpleStringProperty(this, maaritykset[2][2]);
        }
        return asiakas;
    }

    public void setAlkaa(LocalDate alkaa) {
        alkaaProperty().set(alkaa);
    }

    public LocalDate getAlkaa() {
        return alkaa.get();
    }

    public ObjectProperty<LocalDate> alkaaProperty() {
        if (alkaa == null) {
            alkaa = new SimpleObjectProperty<>(this, maaritykset[3][2]);
        }
        return alkaa;
    }

    public void setPaattyy(LocalDate paattyy) {
        paattyyProperty().set(paattyy);
    }

    public LocalDate getPaattyy() {
        return paattyy.get();
    }

    public ObjectProperty<LocalDate> paattyyProperty() {
        if (paattyy == null) {
            paattyy = new SimpleObjectProperty<>(this, maaritykset[4][2]);
        }
        return paattyy;
    }

    public void setTila(String tila) {
        tilaProperty().set(tila);
    }

    public String getTila() {
        return tilaProperty().get();
    }

    public StringProperty tilaProperty() {
        if (tila == null) {
            tila = new SimpleStringProperty(this, maaritykset[5][2]);
        }
        return tila;
    }

    public void setHuomioitavaa(String huomioitavaa) {
        huomioitavaaProperty().set(huomioitavaa);
    }

    public String getHuomioitavaa() {
        return huomioitavaaProperty().get();
    }

    public StringProperty huomioitavaaProperty() {
        if (huomioitavaa == null) {
            huomioitavaa = new SimpleStringProperty(this, maaritykset[6][2]);
        }
        return huomioitavaa;
    }

    public void setAsiakkaanSahkoposti(String asiakkaanSahkoposti) {
        this.asiakkaanSahkoposti = asiakkaanSahkoposti;
    }

    public String getAsiakkaanSahkoposti() {
        return asiakkaanSahkoposti;
    }

    public void setAsiakkaanNimi(String asiakkaanNimi) {
        this.asiakkaanNimi = asiakkaanNimi;
    }

    public String getAsiakkaanNimi() {
        return asiakkaanNimi;
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
        return true;
    }

    public boolean paivitaKenttienArvot(String[] arvot) {
        setTunnus(arvot[0]);
        setKohteenTunnus(arvot[1]);
        setAsiakas(arvot[2]);
        setAlkaa(LocalDate.parse(arvot[3]));
        setPaattyy(LocalDate.parse(arvot[4]));
        setTila(arvot[5]);
        setHuomioitavaa(arvot[6]);

        return true;
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
        return true;
    }

    public int palautaTunnisteenIndeksi() {
        return 0;
    }

    public boolean onkoSahkopostiTietokannassa(String asiakkaanSahkoposti) {
        return true;
    }

}
