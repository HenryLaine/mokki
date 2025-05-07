package mokki.mokki.gui.testiluokatTaulukonDatalle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mokki.mokki.gui.alipaneeli.TaulukonData;

/**
 * Wrapper-luokka asiakkaiden tiedoille. Luokka on tarkoitettu taulukkopaneeliin syötettävän tiedon tyypiksi.
 */
public class AsiakkaatWrapper implements TaulukonData {
    private StringProperty nimi;
    private StringProperty sahkoposti;
    private StringProperty puhelinnumero;
    private StringProperty tyyppi;
    private StringProperty osoite;
    private StringProperty ytunnus;

    /** Taulukkomääritykset, joita tarvitaan taulukon luomisessa */
    private String[][] maaritykset;

    /**
     * Luokan alustaja
     * @param nimi nimi
     * @param sahkoposti sähköpostiosoite
     * @param puhelinnumero puhelinnumero
     * @param tyyppi tyyppi
     * @param ytunnus Y-tunnus
     */
    public AsiakkaatWrapper(String sahkoposti,String tyyppi, String nimi,String puhelinnumero, String osoite,
                            String ytunnus) {

        this.sahkoposti = new SimpleStringProperty(sahkoposti);
        this.nimi = new SimpleStringProperty(nimi);
        this.puhelinnumero = new SimpleStringProperty(puhelinnumero);
        this.tyyppi = new SimpleStringProperty(tyyppi);
        this.osoite= new SimpleStringProperty(osoite);
        this.ytunnus = new SimpleStringProperty(ytunnus);

        maaritykset = new String[][] {
                {"Nimi", "String", "nimi"},
                {"Sähköposti", "String", "sahkoposti"},
                {"Puhelinnumero", "String", "puhelinnumero"},
                {"Tyyppi", "String", "tyyppi"},
                {"Y-tunnus", "String", "ytunnus"}
        };
    }

    /**
     * Metodi palauttaa nimi-kentän arvon.
     * @return nimi
     */
    public String getNimi() {
        return nimi.get();
    }

    /**
     * Metodi palauttaa puhelinnumero-kentän arvon.
     * @return puhelinnumero
     */
    public String getPuhelinnumero() {
        return puhelinnumero.get();
    }

    /**
     * Metodi palauttaa sähköposti-kentän arvon
     * @return sähköposti
     */
    public String getSahkoposti() {
        return sahkoposti.get();
    }

    /**
     * Metodi palauttaa tyyppi-kentän arvon.
     * @return tyyppi
     */
    public String getTyyppi() {
        return tyyppi.get();
    }

    /**
     * Metodi palauttaa ytunnus-kentän arvon.
     * @return Y-tunnus
     */
    public String getYtunnus() {
        return ytunnus.get();
    }

    /**
     * Metodi palauttaa taulukkomääritykset.
     * @return taulukkomääritykset
     */
    public String[][] getMaaritykset() {
        return maaritykset;
    }

    public String getOsoite() {
        return osoite.get();
    }

    public void setNimi(String nimi) {
        this.nimi.set(nimi);
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti.set(sahkoposti);
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero.set(puhelinnumero);
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi.set(tyyppi);
    }

    public void setOsoite(String osoite) {
        this.osoite.set(osoite);
    }

    public void setYtunnus(String ytunnus) {
        this.ytunnus.set(ytunnus);
    }

    public void setMaaritykset(String[][] maaritykset) {
        this.maaritykset = maaritykset;
    }

    public StringProperty osoiteProperty() {
        return osoite;
    }

    /**
     * Metodi palauttaa tietokokonaisuuden tunnisteen eli asiakkaan sähköpostiosoitteen.
     * @return tunniste
     */
    public String palautaTunniste() {
        return sahkoposti.get();
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin, joka on asiakkaan nimi ja sähköpostiosoite.
     * @return tunniste
     */
    public String palautaKuvausteksti() {
        return nimi.get() + " (" + sahkoposti.get() + ")";
    }

    /**
     * Metodi palauttaa kenttien arvot merkkijonolistana.
     * @return kenttien arvot
     */
    public String[] palautaKenttienArvot() {
        return new String[] {nimi.get(), sahkoposti.get(), puhelinnumero.get(), tyyppi.get(), ytunnus.get()};
    }

    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        return true;
    }

    public boolean paivitaKenttienArvot(String[] arvot) {
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
        if (sahkoposti.get().equals(tunniste)) {
            return true;
        }
        else {
            // TODO: tarkista, että tunniste ei ole sama kuin jonkin muun kohteen tunniste tietokannassa
            return true;
        }
    }

    public int palautaTunnisteenIndeksi() {
        return 1;
    }

}