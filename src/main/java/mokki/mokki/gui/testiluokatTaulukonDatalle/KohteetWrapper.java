package mokki.mokki.gui.testiluokatTaulukonDatalle;

import javafx.beans.property.*;
import mokki.mokki.BackEnd.Mokki;
import mokki.mokki.gui.alipaneeli.TaulukonData;

/**
 * Testiluokka kohteiden tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class KohteetWrapper implements TaulukonData {
    private IntegerProperty tunnus;
    private StringProperty sijainti;
    private IntegerProperty huoneita;
    private DoubleProperty pintaAla;
    private DoubleProperty hinta;
    private IntegerProperty henkilomaara;
    private StringProperty huomioitavaa;

    /** Määrityksiä tarvitaan taulukon luomisessa */
    private String[][] maaritykset;

    /**
     * Luokan parametriton alustaja
     */
    public KohteetWrapper(){
        maaritykset = new String[][] {
                {"Tunnus", "Integer", "tunnus"},
                {"Sijainti", "String", "sijainti"},
                {"Huoneita", "Integer", "huoneita"},
                {"Pinta-ala", "Double", "pintaAla"},
                {"Hinta", "Double", "hinta"},
                {"Henkilömäärä", "Integer", "henkilomaara"},
                {"Huomioitavaa", "String", "huomioitavaa"},
        };
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
    public KohteetWrapper(Integer tunnus, String sijainti, int huoneita,
                          double pintaAla, double hinta, int henkilomaara, String huomioitavaa) {

        maaritykset = new String[][] {
                {"Tunnus", "Integer", "tunnus"},
                {"Sijainti", "String", "sijainti"},
                {"Huoneita", "Integer", "huoneita"},
                {"Pinta-ala", "Double", "pintaAla"},
                {"Hinta", "Double", "hinta"},
                {"Henkilömäärä", "Integer", "henkilomaara"},
                {"Huomioitavaa", "String", "huomioitavaa"},
        };

        setTunnus(String.valueOf(tunnus));
        setSijainti(sijainti);
        setHuoneita(huoneita);
        setPintaAla(pintaAla);
        setHinta(hinta);
        setHenkilomaara(henkilomaara);
        setHuomioitavaa(huomioitavaa);
    }

    /**
     * Metodi asettaa tunnuksen arvon.
     * @param tunnus tunnuksen arvon
     */
    public void setTunnus(int tunnus) {
        tunnusProperty().set(tunnus);
    }
    public void setTunnus(String tunnus) {
        if (tunnus == null || tunnus.trim().isEmpty()) {
            this.setTunnus(0); // vaihtoehtoisesti: jätä asettamatta
        } else {
            try {
                this.setTunnus(Integer.parseInt(tunnus.trim()));
            } catch (NumberFormatException e) {
                System.err.println("Virheellinen tunnus: " + tunnus);
                this.setTunnus(0); // tai heitä poikkeus, tai jätä asettamatta
            }
        }
    }

    /**
     * Metodi palauttaa tunnuksen arvon.
     * @return tunnuksen arvo
     */
    public String getTunnus() {
        return String.valueOf(tunnusProperty().get());
    }

    /**
     * Metodi alustaa tunnuksen, jos se on alustamaton.
     * @return tunnus
     */
    public IntegerProperty tunnusProperty() {
        if (tunnus == null) {
            tunnus = new SimpleIntegerProperty(this, maaritykset[0][2]);
        }
        return tunnus;
    }

    public int getHenkilomaara() {
        return henkilomaara.get();
    }

    public IntegerProperty henkilomaaraProperty() {
        if (henkilomaara == null) {
            henkilomaara = new SimpleIntegerProperty(this, maaritykset[5][2]);
        }
        return henkilomaara;
    }

    public void setHenkilomaara(int henkilomaara) {
        henkilomaaraProperty().set(henkilomaara);
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
            huomioitavaa = new SimpleStringProperty(this, maaritykset[6][2]);
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
     *
     * @return tunniste
     */
    public String palautaTunniste() {
        return String.valueOf(tunnus.get());
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin eli kohteen tunnuksen.
     *
     * @return tunniste
     */
    public String palautaKuvausteksti() {
        return "";
    }

    /**
     * Metodi palauttaa kenttien arvot merkkijonolistana.
     * @return kenttien arvot
     */
    public String[] palautaKenttienArvot() {
        return new String[] {
                "" + tunnusProperty().get(),
                "" + sijaintiProperty().get(),
                "" + huoneitaProperty().get(),
                "" + pintaAlaProperty().get(),
                "" + hintaProperty().get(),
                "" + henkilomaaraProperty().get(),
                "" + huomioitavaaProperty().get()
        };
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        // Huom: tunnus ei ole pakollinen kenttä
        if (arvot.length < 7) return false;

        try {
            Integer.parseInt(arvot[2]); // huoneita
            Double.parseDouble(arvot[3]); // pinta-ala
            Double.parseDouble(arvot[4]); // hinta
            Integer.parseInt(arvot[5]); // henkilömäärä
        } catch (NumberFormatException e) {
            return false;
        }

        return !arvot[1].trim().isEmpty(); // sijainti ei saa olla tyhjä
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
            setHenkilomaara(Integer.parseInt(arvot[5]));
            setHuomioitavaa(arvot[6]);
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


    public boolean onkoTunnisteUniikki(String tunniste) {
        return true;
    }

    public int palautaTunnisteenIndeksi() {
        return 0;
    }
}
