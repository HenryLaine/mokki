package mokki.mokki.gui.testiluokatTaulukonDatalle;


import javafx.beans.property.*;
import mokki.mokki.gui.alipaneeli.TaulukonData;

import java.sql.Date;
import java.text.SimpleDateFormat;


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

    private DoubleProperty alv;
    private Date paivamaara;
    private Date eraPaiva;
    private StringProperty sahkposti;
    private StringProperty osoite;
    private StringProperty nimi;
    private DoubleProperty verotonHinta;


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
                         int viitenumero, double maksettava, double verotonhinta,
                         double alv, Date paivamaara, Date erapaiva, String sahkoposti,
                         String osoite, String nimi, String tila) {

        this.laskunumero = new SimpleIntegerProperty(laskunumero);
        this.tuote = new SimpleStringProperty(tuote);
        this.asiakas = new SimpleStringProperty(nimi + " " + sahkoposti);
        this.viitenumero = new SimpleIntegerProperty(viitenumero);
        this.maksettava = new SimpleDoubleProperty(maksettava);
        this.tila = new SimpleStringProperty(tila);
        this.alv = new SimpleDoubleProperty(alv);
        this.eraPaiva = erapaiva;
        this.paivamaara = paivamaara;
        this.verotonHinta = new SimpleDoubleProperty(verotonhinta);
        this.osoite = new SimpleStringProperty(osoite);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkposti = new SimpleStringProperty(sahkoposti);

        maaritykset = new String[][] {
                {"Laskunumero", "Integer", "laskunumero"},
                {"Tuote", "String", "tuote"},
                {"Asiakas", "String", "asiakas"},
                {"Viitenumero", "Integer", "viitenumero"},
                {"Maksettava", "Double", "maksettava"},
                {"Veroton hinta", "Double", "verotonHinta"},
                {"ALV", "Double", "alv"},
                {"Päivämäärä", "Date", "paivamaara"},
                {"Eräpäivä", "Date", "erapaiva"},
                {"Tila", "String", "tila"}
        };
    }

    public double getVerotonHinta() {
        return verotonHinta.get();
    }

    public Date getEraPaiva() {
        return eraPaiva;
    }

    public Date getPaivamaara() {
        return paivamaara;
    }

    public double getAlv() {
        return alv.get();
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

    public void setEraPaiva(Date eraPaiva) {
        this.eraPaiva = eraPaiva;
    }

    public void setPaivamaara(Date paivamaara) {
        this.paivamaara = paivamaara;
    }

    public void setAlv(double alv) {
        this.alv.set(alv);
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

    public void setOsoite(String osoite) {
        this.osoite.set(osoite);
    }

    public void setSahkposti(String sahkposti) {
        this.sahkposti.set(sahkposti);
    }

    public void setNimi(String nimi) {
        this.nimi.set(nimi);
    }

    public void setTila(String tila) {
        this.tila.set(tila);
    }

    public void setTuote(String tuote) {
        this.tuote.set(tuote);
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
        return new String[] {""+laskunumero.get(), tuote.get(), asiakas.get(),
                ""+viitenumero.get(), ""+maksettava.get(), ""+verotonHinta.get(),
                ""+alv.get(), ""+paivamaara, ""+eraPaiva, sahkposti.get(),
                osoite.get(), nimi.get(), tila.get()};
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        return true;
    }

    public boolean paivitaKenttienArvot(String[] arvot) {
        try {
            this.laskunumero.set(Integer.parseInt(arvot[0]));
            this.tuote.set(arvot[1]);
            this.nimi.set(arvot[2]); // Nimi
            this.sahkposti.set(arvot[3]); // Sähköposti
            this.asiakas.set(nimi.get() + " " + sahkposti.get()); // Asiakas muodostetaan
            this.viitenumero.set(Integer.parseInt(arvot[4]));
            this.maksettava.set(Double.parseDouble(arvot[5]));
            this.verotonHinta.set(Double.parseDouble(arvot[6]));
            this.alv.set(Double.parseDouble(arvot[7]));
            this.paivamaara = Date.valueOf(arvot[8]); // Päivämäärä
            this.eraPaiva = Date.valueOf(arvot[9]); // Eräpäivä
            this.tila.set(arvot[10]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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


}