package mokki.mokki.gui.testiluokatTaulukonDatalle;

import javafx.beans.property.*;
import mokki.mokki.gui.alipaneeli.TaulukonData;

/**
 * Testiluokka kohteiden tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class KohteetWrapper implements TaulukonData {
    private StringProperty tunnus;
    private StringProperty sijainti;
    private IntegerProperty huoneita;
    private DoubleProperty pintaAla;
    private DoubleProperty hinta;
    private StringProperty huomioitavaa;

    /** Määrityksiä tarvitaan taulukon luomisessa */
    private String[][] maaritykset;

    /**
     * Luokan parametriton alustaja
     */
    public KohteetWrapper() {
        maaritykset = new String[][] {
                {"Tunnus", "String", "tunnus"},
                {"Sijainti", "String", "sijainti"},
                {"Huoneita", "Integer", "huoneita"},
                {"Pinta-ala", "Double", "pintaAla"},
                {"Hinta", "Double", "hinta"},
                {"Huomioitavaa", "String", "huomioitavaa"},
        };

        setTunnus("");
        setSijainti("");
        setHuoneita(0);
        setPintaAla(0);
        setHinta(0);
        setHuomioitavaa("");
    }

    /**
     * Luokan parametrillinen alustaja
     * @param tunnus tunnus
     * @param sijainti sijainti
     * @param huoneita huoneita
     * @param pintaAla pinta-ala
     * @param hinta hinta
     * @param huomioitavaa huomioitavaa
     */
    public KohteetWrapper(String tunnus, String sijainti, int huoneita,
                          int pintaAla, int hinta, String huomioitavaa) {

        maaritykset = new String[][] {
                {"Tunnus", "String", "tunnus"},
                {"Sijainti", "String", "sijainti"},
                {"Huoneita", "Integer", "huoneita"},
                {"Pinta-ala", "Double", "pintaAla"},
                {"Hinta", "Double", "hinta"},
                {"Huomioitavaa", "String", "huomioitavaa"},
        };

        setTunnus(tunnus);
        setSijainti(sijainti);
        setHuoneita(huoneita);
        setPintaAla(pintaAla);
        setHinta(hinta);
        setHuomioitavaa(huomioitavaa);
    }

    /**
     * Metodi asettaa tunnuksen arvon.
     * @param tunnus tunnuksen arvon
     */
    public void setTunnus(String tunnus) {
        tunnusProperty().set(tunnus);
    }

    /**
     * Metodi palauttaa tunnuksen arvon.
     * @return tunnuksen arvo
     */
    public String getTunnus() {
        return tunnusProperty().get();
    }

    /**
     * Metodi alustaa tunnuksen, jos se on alustamaton.
     * @return tunnus
     */
    public StringProperty tunnusProperty() {
        if (tunnus == null) {
            tunnus = new SimpleStringProperty(this, maaritykset[0][2]);
        }
        return tunnus;
    }

    /**
     * Metodi asettaa sijainnin arvon.
     * @param sijainti sijainnin arvo
     */
    public void setSijainti(String sijainti) {
        sijaintiProperty().set(sijainti);
    }

    /**
     * Metodi palauttaa sijainnin arvon.
     * @return sijainnin arvo
     */
    public String getSijainti() {
        return sijaintiProperty().get();
    }

    /**
     * Metodi alustaa sijainnin, jos se on alustamaton.
     * @return sijainti
     */
    public StringProperty sijaintiProperty() {
        if (sijainti == null) {
            sijainti = new SimpleStringProperty(this, maaritykset[1][2]);
        }
        return sijainti;
    }

    /**
     * Metodi asettaa huoneita-kentän arvon.
     * @param huoneita huoneita-kentän arvo
     */
    public void setHuoneita(int huoneita) {
        huoneitaProperty().set(huoneita);
    }

    /**
     * Metodi palauttaa huoneita-kentän arvon.
     * @return huoneita-kentän arvo
     */
    public int getHuoneita() {
        return huoneitaProperty().get();
    }

    /**
     * Metodi alustaa huoneita-kentän, jos se on alustamaton.
     * @return huoneita
     */
    public IntegerProperty huoneitaProperty() {
        if (huoneita == null) {
            huoneita = new SimpleIntegerProperty(this, maaritykset[2][2]);
        }
        return huoneita;
    }

    /**
     * Metodi asettaa pinta-alan arvon.
     * @param pintaAla pinta-alan arvo
     */
    public void setPintaAla(double pintaAla) {
        pintaAlaProperty().set(pintaAla);
    }

    /**
     * Metodi palauttaa pinta-alan arvon.
     * @return pinta-alan arvo
     */
    public double getPintaAla() {
        return pintaAlaProperty().get();
    }

    /**
     * Metodi alustaa pinta-alan, jos se on alustamaton.
     * @return pinta-ala
     */
    public DoubleProperty pintaAlaProperty() {
        if (pintaAla == null) {
            pintaAla = new SimpleDoubleProperty(this, maaritykset[3][2]);
        }
        return pintaAla;
    }

    /**
     * Metodi asettaa hinnan arvon.
     * @param hinta hinnan arvo
     */
    public void setHinta(double hinta) {
        hintaProperty().set(hinta);
    }

    /**
     * Metodi palauttaa hinnan arvon.
     * @return hinnan arvo
     */
    public double getHinta() {
        return hintaProperty().get();
    }

    /**
     * Metodi alustaa hinnan, jos se on alustamaton.
     * @return hinta
     */
    public DoubleProperty hintaProperty() {
        if (hinta == null) {
            hinta = new SimpleDoubleProperty(this, maaritykset[4][2]);
        }
        return hinta;
    }

    /**
     * Metodi asettaa huomioitavaa-kentän arvon.
     * @param huomioitavaa huomioitavaa-kentän arvo
     */
    public void setHuomioitavaa(String huomioitavaa) {
        huomioitavaaProperty().set(huomioitavaa);
    }

    /**
     * Metodi palauttaa huomioitavaa-kentän arvon.
     * @return huomioitavaa-kentän arvo
     */
    public String getHuomioitavaa() {
        return huomioitavaaProperty().get();
    }

    /**
     * Metodi alustaa huomioitavaa-kentän, jos se on alustamaton.
     * @return huomioitavaa
     */
    public StringProperty huomioitavaaProperty() {
        if (huomioitavaa == null) {
            huomioitavaa = new SimpleStringProperty(this, maaritykset[5][2]);
        }
        return huomioitavaa;
    }

    /**
     * Metodi palauttaa taulukkomääritykset.
     * @return taulukkomääritykset
     */
    public String[][] getMaaritykset() {
        return maaritykset;
    }

    /**
     * Metodi palauttaa tietokokonaisuuden tunnisteen eli kohteen tunnuksen.
     * @return tunniste
     */
    public String palautaTunniste() {
        return tunnus.get();
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin eli kohteen tunnuksen.
     * @return tunniste
     */
    public String palautaKuvausteksti() {
        return tunnus.get();
    }

    /**
     * Metodi palauttaa kenttien arvot merkkijonolistana.
     * @return kenttien arvot
     */
    public String[] palautaKenttienArvot() {

        return new String[] {tunnus.get(), sijainti.get(), ""+huoneita.get(),
                ""+pintaAla.get(), ""+hinta.get(), huomioitavaa.get()};
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        // TODO: tarkista, ovatko arvot hyväksyttäviä
        if (arvot.length != 6) {
            return false;
        }
        else if (arvot[0].trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Metodi päivittää kenttien arvot merkkijonolistan perusteella.
     * @param arvot arvot merkkijonolistana
     * @return true, jos päivittäminen onnistui; false muussa tapauksessa
     */
    public boolean paivitaKenttienArvot(String[] arvot) {
        if (ovatkoArvotHyvaksyttavia(arvot)) {
            setTunnus(arvot[0]);
            setSijainti(arvot[1]);
            setHuoneita(Integer.parseInt(arvot[2]));
            setPintaAla(Double.parseDouble(arvot[3]));
            setHinta(Double.parseDouble(arvot[4]));
            setHuomioitavaa(arvot[5]);
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkistaa, mitkä arvot ovat hyväksyttäviä kenttien arvoiksi.
     * @param arvot arvoehdokkaat merkkijonoina
     * @return totuusarvolista tarkistuksen tuloksista
     */
    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        boolean[] totuusarvolista = new boolean[arvot.length];
        for (int i = 0; i < arvot.length; i++) {
            // TODO: tarkista, mitkä arvot ovat hyväksyttäviä

            totuusarvolista[i] = false;
            //totuusarvolista[i] = true;
        }
        return totuusarvolista;
    }

}
