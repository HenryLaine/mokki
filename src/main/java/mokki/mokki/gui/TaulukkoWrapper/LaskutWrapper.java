package mokki.mokki.gui.TaulukkoWrapper;


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
    private DoubleProperty alv;
    private ObjectProperty<LocalDate> paivamaara;
    private ObjectProperty<LocalDate> eraPaiva;
    private StringProperty sahkposti;
    private StringProperty osoite;
    private StringProperty nimi;
    private StringProperty asiakas; //

    /**
     * Taulukkomääritykset, joita tarvitaan taulukon luomisessa.
     * Määrittää jokaisen kentän nimen, tyypin ja avaimen.
     */
    private String[][] maaritykset;

    /**
     * Konstruktori, joka luo uuden LaskutWrapper-olion käyttäen varausnumeroa ja viitenumeroa.
     * Muut kentät alustetaan oletusarvoilla.
     *
     * @param varaus Varausnumero.
     * @param viitenumero Viitenumero.
     */
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

        // Alustetaan taulukkomääritykset
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

    /**
     * Konstruktori, joka luo uuden LaskutWrapper-olion kaikilla annetuilla tiedoilla.
     * Laskee automaattisesti maksettavan summan käyttäen arvonlisäveroa (10%).
     *
     * @param laskunumero Laskunumero.
     * @param varaus Varausnumero.
     * @param asiakas Asiakkaan nimi ja sähköposti.
     * @param viitenumero Viitenumero.
     * @param maksettava Maksettava summa.
     * @param verotonhinta Veroton hinta.
     * @param alv Arvonlisäveron määrä.
     * @param paivamaara Laskun päivämäärä.
     * @param erapaiva Laskun eräpäivä.
     * @param sahkoposti Asiakkaan sähköposti.
     * @param osoite Asiakkaan osoite.
     * @param nimi Asiakkaan nimi.
     * @param tila Laskun tila.
     */
    public LaskutWrapper(int laskunumero, Integer varaus, String asiakas,
                         int viitenumero, double maksettava, double verotonhinta,
                         double alv, LocalDate paivamaara, LocalDate erapaiva, String sahkoposti,
                         String osoite, String nimi, String tila) {

        double majoituksenAlv=0.1;
        double alvSumma= verotonhinta*majoituksenAlv;
        this.laskunumero = new SimpleIntegerProperty(laskunumero);
        this.varaus = new SimpleIntegerProperty(varaus);
        this.asiakas = new SimpleStringProperty(nimi + " (" + sahkoposti + ")");
        this.viitenumero = new SimpleIntegerProperty(viitenumero);
        this.maksettava = new SimpleDoubleProperty(alvSumma+verotonhinta);
        this.tila = new SimpleStringProperty(tila);
        this.alv = new SimpleDoubleProperty(alvSumma);
        this.eraPaiva = new SimpleObjectProperty<>(erapaiva);
        this.paivamaara = new SimpleObjectProperty<>(paivamaara);
        this.verotonHinta = new SimpleDoubleProperty(verotonhinta);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkposti = new SimpleStringProperty(sahkoposti);
        this.osoite = new SimpleStringProperty(osoite);

        // Alustetaan taulukkomääritykset
        maaritykset = new String[][] {
                {"Laskunumero", "Integer", "laskunumero"},
                {"Varaustunnus", "Integer", "varaus"},
                {"Asiakas", "String", "asiakas"},
                {"Viitenumero", "Integer", "viitenumero"},
                {"Veroton hinta", "Double", "verotonHinta"},
                {"ALV", "Double", "alv"},
                {"Maksettava hinta", "Double", "maksettava"},
                {"Päivämäärä", "LocalDate", "paivamaara"},
                {"Eräpäivä", "LocalDate", "eraPaiva"},
                {"Tila", "String", "tila"}
        };
    }

    /**
     * Metodi palauttaa verottoman hinnan.
     *
     * @return veroton hinta
     */
    public double getVerotonHinta() {
        return verotonHinta.get();
    }

    /**
     * Metodi palauttaa eräpäivän.
     *
     * @return eräpäivä
     */
    public LocalDate getEraPaiva() {
        return eraPaiva.get();
    }

    /**
     * Metodi palauttaa laskun päivämäärän.
     *
     * @return päivämäärä
     */
    public LocalDate getPaivamaara() {
        return paivamaara.get();
    }

    /**
     * Metodi palauttaa arvonlisäveron määrän.
     *
     * @return ALV
     */
    public double getAlv() {
        return alv.get();
    }

    /**
     * Metodi palauttaa asiakkaan osoitteen.
     *
     * @return osoite
     */
    public String getOsoite() {
        return osoite.get();
    }

    /**
     * Metodi palauttaa asiakkaan sähköpostiosoitteen.
     *
     * @return sähköposti
     */
    public String getSahkposti() {
        return sahkposti.get();
    }

    /**
     * Metodi palauttaa asiakkaan nimen.
     *
     * @return nimi
     */
    public String getNimi() {
        return nimi.get();
    }

    /**
     * Metodi palauttaa laskunumeron.
     *
     * @return laskunumero
     */
    public int getLaskunumero() {
        return laskunumero.get();
    }

    /**
     * Metodi palauttaa varausnumeron.
     *
     * @return varausnumero
     */
    public int getVaraus() {
        return varaus.get();
    }

    /**
     * Metodi palauttaa asiakkaan tiedot yhdistettynä (nimi ja sähköposti).
     *
     * @return asiakas
     */
    public String getAsiakas() {
        return asiakas.get();
    }

    /**
     * Metodi palauttaa viitenumeron.
     *
     * @return viitenumero
     */
    public int getViitenumero() {
        return viitenumero.get();
    }


    /**
     * Metodi palauttaa maksettavan kokonaissumman.
     *
     * @return maksettava määrä
     */
    public double getMaksettava() {
        return maksettava.get();
    }

    /**
     * Metodi palauttaa laskun tilan (Avoin, Maksetu, Myöhässä).
     *
     * @return tila
     */
    public String getTila() {
        return tila.get();
    }

    /**
     * Asettaa eräpäivän.
     *
     * @param eraPaiva Eräpäivä, joka asetetaan.
     */
    public void setEraPaiva(LocalDate eraPaiva) {
        eraPaivaProperty().set(eraPaiva);
    }

    /**
     * Palauttaa eräpäiväominaisuuden (property).
     * Jos ominaisuus on null, se alustetaan.
     *
     * @return Eräpäiväominaisuus (property).
     */
    public ObjectProperty<LocalDate> eraPaivaProperty() {
        if (eraPaiva == null) {
            eraPaiva = new SimpleObjectProperty<>(this, maaritykset[6][2]);
        }
        return eraPaiva;
    }

    /**
     * Asettaa laskun päivämäärän.
     *
     * @param paivamaara Päivämäärä, joka asetetaan.
     */
    public void setPaivamaara(LocalDate paivamaara) {
        paivamaaraProperty().set(paivamaara);
    }

    /**
     * Palauttaa päivämääräominaisuuden (property).
     * Jos ominaisuus on null, se alustetaan.
     *
     * @return Päivämääräominaisuus (property).
     */
    public ObjectProperty<LocalDate> paivamaaraProperty() {
        if (paivamaara == null) {
            paivamaara = new SimpleObjectProperty<>(this, maaritykset[5][2]);
        }
        return paivamaara;
    }

    /**
     * Asettaa arvonlisäveron (ALV).
     *
     * @param alv Arvonlisävero, joka asetetaan.
     */
    public void setAlv(double alv) {
        this.alv.set(alv);
    }

    /**
     * Asettaa asiakkaan tiedot.
     *
     * @param asiakas Asiakkaan nimi ja mahdolliset muut tiedot.
     */
    public void setAsiakas(String asiakas) {
        this.asiakas.set(asiakas);
    }

    /**
     * Asettaa laskun numeron.
     *
     * @param laskunumero Laskunumero, joka asetetaan.
     */
    public void setLaskunumero(int laskunumero) {
        this.laskunumero.set(laskunumero);
    }


    /**
     * Asettaa maksettavan kokonaissumman.
     *
     * @param maksettava Maksettava summa, joka asetetaan.
     */
    public void setMaksettava(double maksettava) {
        this.maksettava.set(maksettava);
    }

    /**
     * Asettaa laskun tilan.
     *
     * @param tila Laskun tila, joka asetetaan.
     */
    public void setTila(String tila) {
        this.tila.set(tila);
    }

    /**
     * Asettaa varausnumeron.
     *
     * @param varaus Varausnumero, joka asetetaan.
     */
    public void setTuote(int varaus) {
        this.varaus.set(varaus);
    }

    /**
     * Asettaa viitenumeron.
     *
     * @param viitenumero Viitenumero, joka asetetaan.
     */
    public void setViitenumero(int viitenumero) {
        this.viitenumero.set(viitenumero);
    }

    /**
     * Asettaa taulukkomääritykset.
     *
     * @param maaritykset Taulukon määritykset, jotka asetetaan.
     */
    public void setMaaritykset(String[][] maaritykset) {
        this.maaritykset = maaritykset;
    }


    /**
     * Asettaa verottoman hinnan.
     *
     * @param verotonHinta Veroton hinta, joka asetetaan.
     */
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
     * Metodi palauttaa tietokokonaisuuden tunnisteen eli laskunumeron.
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
     *
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
                "" + paivamaara.get(),             // 7: Päivämäärä
                "" + eraPaiva.get(),               // 8: Eräpäivä
                tila.get()                   // 9: Tila
        };
    }

    /**
     * Tarkistaa, ovatko annetut arvot hyväksyttäviä.
     *
     * @param arvot arvoehdokkaat merkkijonoina.
     * @return true, jos arvot ovat hyväksyttäviä, muulloin false.
     */
    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        try {
            // Tarkistetaan asiakkaan nimi
            String nimi = arvot[2];
            if (nimi.isEmpty()) return false;


            // Tarkistetaan sähköposti
            String sahkoposti = arvot[3];
            if (sahkoposti.isEmpty() || !sahkoposti.contains("@")) return false;

            // Tarkistetaan osoite (ei saa olla tyhjä)
            String osoite = arvot[4];
            if (osoite.isEmpty()) return false;

            // Tarkistetaan viitenumero
            int viitenumero = Integer.parseInt(arvot[5]);
            if (viitenumero <= 0) return false;

            // Tarkistetaan veroton hinta
            double verotonHinta = Double.parseDouble(arvot[6]);
            if (verotonHinta <= 0) return false;

            // Kaikki tarkistukset läpäisty
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Palautetaan false, jos tapahtuu virhe
        }
    }

    /**
     * Päivittää kenttien arvot annetun taulukon perusteella.
     *
     * @param arvot Uudet arvot.
     * @return True, jos päivitys onnistui, muuten false.
     */
    public boolean paivitaKenttienArvot(String[] arvot) {
        try {
            this.laskunumero.set(Integer.parseInt(arvot[0])); // 0: Laskunumero
            this.varaus.set(Integer.parseInt(arvot[1]));                        // 1: Varaus
            this.nimi.set(arvot[2]);                         // 2: Asiakas nimi
            this.sahkposti.set(arvot[3]);                    // 3: Asiakas sähköposti
            this.osoite.set(arvot[4]);                       // 4: Asiakas osoite
            this.viitenumero.set(Integer.parseInt(arvot[5])); // 5: Viitenumero
            this.verotonHinta.set(Double.parseDouble(arvot[6])); // 6: Veroton hintaD
            this.paivamaara.set(LocalDate.parse(arvot[7]));        // 7: Päivämäärä
            this.eraPaiva.set(LocalDate.parse(arvot[8]));          // 8: Eräpäivä
            this.tila.set(arvot[9]);                         // 9: Tila
            this.asiakas.set(arvot[2] + " (" + arvot[3] + ")"); // Asiakas

            // Lasketaan ALV ja maksettava summa
            double verotonHinta = Double.parseDouble(arvot[6]);
            double alv = verotonHinta * 0.1; 
            this.alv.set(alv);
            this.maksettava.set(verotonHinta + alv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Tarkistaa, mitkä annetun arvotaulukon arvot ovat hyväksyttäviä.
     * Tässä toteutuksessa kaikki arvot katsotaan hyväksyttäviksi, joten totuusarvolista
     * sisältää vain arvoja `true`.
     *
     * @param arvot Merkkijonotaulukko, joka sisältää tarkistettavat arvot.
     * @return Taulukko, jossa jokainen arvo on merkitty hyväksyttäväksi (true).
     */
    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        boolean[] totuusarvolista = new boolean[arvot.length];
        for (int i = 0; i < arvot.length; i++) {
            totuusarvolista[i] = true;
        }
        return totuusarvolista;
    }

    /**
     * Tarkistaa, onko annetun tunnisteen arvo uniikki.
     * Tässä toteutuksessa tunnisteen tarkistusta ei suoriteta, koska järjestelmä hoitaa tunnusten luomisen.
     *
     * @param tunniste Merkkijono, joka edustaa tarkastettavaa tunnistetta.
     * @return Aina true, koska uniikkiustarkistusta ei tarvita.
     */
    public boolean onkoTunnisteUniikki(String tunniste) {
        // Järjestelmä hoitaa tunnusten luomisen. Ei tarvitse tarkistaa.
        return true;
    }

    /**
     * Palauttaa tunnisteen indeksin.
     * Tässä toteutuksessa tunnisteen indeksi on aina 0.
     *
     * @return Tunnisteen indeksi (aina 0).
     */
    public int palautaTunnisteenIndeksi() {
        return 0;
    }


}