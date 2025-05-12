package mokki.mokki.gui.testiluokatTaulukonDatalle;


import javafx.beans.property.*;
import mokki.mokki.gui.alipaneeli.TaulukonData;

import java.time.LocalDate;


/**
 * Wrapper-luokka laskujen tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class LaskutWrapper implements TaulukonData {
    private IntegerProperty laskunumero;
    private IntegerProperty varaus;
    private IntegerProperty viitenumero;
    private DoubleProperty maksettava;  //
    private StringProperty tila;
    private DoubleProperty verotonHinta;

    private Double alv;
    private ObjectProperty<LocalDate> paivamaara;
    private ObjectProperty<LocalDate> eraPaiva;
    private StringProperty sahkposti;
    private StringProperty osoite;
    private StringProperty nimi;
    private StringProperty asiakas; //


    /** Taulukkomääritykset, joita tarvitaan taulukon luomisessa */
    private String[][] maaritykset;



    public LaskutWrapper(int varaus, int viitenumero) {

        this.laskunumero = new SimpleIntegerProperty(0);
        this.varaus = new SimpleIntegerProperty(0);
        this.asiakas = new SimpleStringProperty("");
        this.viitenumero = new SimpleIntegerProperty(0);
        this.maksettava = new SimpleDoubleProperty(0);
        this.tila = new SimpleStringProperty("");
        this.alv = this.alv;
        this.eraPaiva = new SimpleObjectProperty<>(null);
        this.paivamaara = new SimpleObjectProperty<>(null);
        this.verotonHinta = new SimpleDoubleProperty(0);
        this.nimi = new SimpleStringProperty("");
        this.sahkposti = new SimpleStringProperty("");
        this.osoite = new SimpleStringProperty("");

        maaritykset = new String[][] {
                {"Laskunumero", "Integer", "laskunumero"},
                {"Varaustunnus", "Integer", "varaus"},
                {"Asiakas", "String", "asiakas"},
                {"Viitenumero", "Integer", "viitenumero"},
                {"Veroton hinta", "Double", "verotonHinta"},
                {"Päivämäärä", "LocalDate", "paivamaara"},
                {"Eräpäivä", "LocalDate", "eraPaiva"},
                {"Tila", "String", "tila"}
        };

    }
    public LaskutWrapper(int laskunumero, Integer varaus, String asiakas,
                         int viitenumero, double maksettava, double verotonhinta,
                         double alv, LocalDate paivamaara, LocalDate erapaiva, String sahkoposti,
                         String osoite, String nimi, String tila) {

        this.laskunumero = new SimpleIntegerProperty(laskunumero);
        this.varaus = new SimpleIntegerProperty(varaus);
        this.asiakas = new SimpleStringProperty(nimi + " (" + sahkoposti + ")");
        this.viitenumero = new SimpleIntegerProperty(viitenumero);
        this.maksettava = new SimpleDoubleProperty(maksettava);
        this.tila = new SimpleStringProperty(tila);
        this.alv = alv;
        this.eraPaiva = new SimpleObjectProperty<>(erapaiva);
        this.paivamaara = new SimpleObjectProperty<>(paivamaara);
        this.verotonHinta = new SimpleDoubleProperty(verotonhinta);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkposti = new SimpleStringProperty(sahkoposti);
        this.osoite = new SimpleStringProperty(osoite);

        maaritykset = new String[][] {
                {"Laskunumero", "Integer", "laskunumero"},
                {"Varaustunnus", "Integer", "varaus"},
                {"Asiakas", "String", "asiakas"},
                {"Viitenumero", "Integer", "viitenumero"},
                {"Veroton hinta", "Double", "verotonHinta"},
                {"Päivämäärä", "LocalDate", "paivamaara"},
                {"Eräpäivä", "LocalDate", "eraPaiva"},
                {"Tila", "String", "tila"}
        };
    }


    public LaskutWrapper(int laskunumero, Integer varaus,
                         int viitenumero, double verotonhinta,
                         double alv, LocalDate paivamaara, LocalDate erapaiva, String sahkoposti,
                         String osoite, String nimi, String tila) {

        this.laskunumero = new SimpleIntegerProperty(laskunumero);
        this.varaus = new SimpleIntegerProperty(varaus);
        this.viitenumero = new SimpleIntegerProperty(viitenumero);
        this.tila = new SimpleStringProperty(tila);
        this.alv = alv;
        this.eraPaiva = new SimpleObjectProperty<>(erapaiva);
        this.paivamaara = new SimpleObjectProperty<>(paivamaara);
        this.verotonHinta = new SimpleDoubleProperty(verotonhinta);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkposti = new SimpleStringProperty(sahkoposti);
        this.osoite = new SimpleStringProperty(osoite);

        maaritykset = new String[][] {
                {"Laskunumero", "Integer", "laskunumero"},
                {"Varaustunnus", "String", "varaus"},
                {"Viitenumero", "Integer", "viitenumero"},
                {"Veroton hinta", "Double", "verotonHinta"},
                {"Päivämäärä", "LocalDate", "paivamaara"},
                {"Eräpäivä", "LocalDate", "eraPaiva"},
                {"Tila", "String", "tila"}
        };
    }

    public double getVerotonHinta() {
        return verotonHinta.get();
    }

    public LocalDate getEraPaiva() {
        return eraPaiva.get();
    }

    public LocalDate getPaivamaara() {
        return paivamaara.get();
    }

    public double getAlv() {
        return alv;
    }

    public String getOsoite() {
        return osoite.get();
    }

    public String getSahkposti() {
        return sahkposti.get();
    }

    public String getNimi() {
        return nimi.get();
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
    public int getVaraus() {
        return varaus.get();
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

    public void setEraPaiva(LocalDate eraPaiva) {
        eraPaivaProperty().set(eraPaiva);
    }

    public ObjectProperty<LocalDate> eraPaivaProperty() {
        if (eraPaiva == null) {
            eraPaiva = new SimpleObjectProperty<>(this, maaritykset[6][2]);
        }
        return eraPaiva;
    }

    public void setPaivamaara(LocalDate paivamaara) {
        paivamaaraProperty().set(paivamaara);
    }

    public ObjectProperty<LocalDate> paivamaaraProperty() {
        if (paivamaara == null) {
            paivamaara = new SimpleObjectProperty<>(this, maaritykset[5][2]);
        }
        return paivamaara;
    }

    public void setAlv(Double alv) {
        this.alv = alv;
    }

    public void setAsiakas(String asiakas) {
        this.asiakas.set(asiakas);
    }

    public void setLaskunumero(int laskunumero) {
        this.laskunumero.set(laskunumero);
    }

    public void setMaksettava(double maksettava) {
        this.maksettava.set(maksettava);
    }


    public void setTila(String tila) {
        this.tila.set(tila);
    }

    public void setTuote(int varaus) {
        this.varaus.set(varaus);
    }

    public void setViitenumero(int viitenumero) {
        this.viitenumero.set(viitenumero);
    }

    public void setMaaritykset(String[][] maaritykset) {
        this.maaritykset = maaritykset;
    }

    public void setVerotonHinta(double verotonHinta) {
        this.verotonHinta.set(verotonHinta);
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
        // Palautetaan kentät määritettyjen taulukkomääritysten mukaisesti
        return new String[]{
                "" + laskunumero.get(),      // 0: Laskunumero
                "" + varaus.get(),                 // 1: Varaus
                getNimi(),                  // 2: Asiakas nimi
                getSahkposti(),             // 3: Asiakas sähköposti
                getOsoite(),                // 4: Asiakas osoite
                "" + viitenumero.get(),      // 5: Viitenumero
                "" + verotonHinta.get(),     // 6: Veroton hinta
                "" + paivamaara,             // 7: Päivämäärä
                "" + eraPaiva,               // 8: Eräpäivä
                tila.get()                   // 9: Tila
        };
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        return true;
    }

    public boolean paivitaKenttienArvot(String[] arvot) {
        try {
            this.laskunumero.set(Integer.parseInt(arvot[0])); // 0: Laskunumero
            this.varaus.set(Integer.parseInt(arvot[1]));                        // 1: Varaus
            this.nimi.set(arvot[2]);                         // 2: Asiakas nimi
            this.sahkposti.set(arvot[3]);                    // 3: Asiakas sähköposti
            this.osoite.set(arvot[4]);                       // 4: Asiakas osoite
            this.viitenumero.set(Integer.parseInt(arvot[5])); // 5: Viitenumero
            this.verotonHinta.set(Double.parseDouble(arvot[6])); // 6: Veroton hinta
            this.paivamaara.set(LocalDate.parse(arvot[7]));        // 7: Päivämäärä
            this.eraPaiva.set(LocalDate.parse(arvot[8]));          // 8: Eräpäivä
            this.tila.set(arvot[9]);                         // 9: Tila
            this.asiakas.set(arvot[2] + " " + arvot[3] + " " + arvot[4]); // Asiakas
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
        // Järjestelmä hoitaa tunnusten luomisen. Ei tarvitse tarkistaa.
        return true;
    }

    public int palautaTunnisteenIndeksi() {
        return 0;
    }


}