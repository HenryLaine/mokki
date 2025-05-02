package mokki.mokki.BackEnd;

/**
 * Tämä luokka edustaa asiakasta, joka voi olla joko yksityisasiakas tai yritysasiakas
 * Asiakkaalla on yhteiset ominaisuudet, kuten sähköpostiosoite, asiakastyyppi, nimi ja osoite
 * Lisäksi yksityisasiakkailla on puhelinnumero, ja yritysasiakkailla on Y-tunnus
 */
public class Asiakas {
    private String sahkoposti;
    private String asiakastyyppi;
    private String nimi;
    private String osoite;
    private String puhelinnumero; // vain yksityisasiakkaalle
    private String ytunnus; // vain yritysasiakkaalle

    /**
     * Luo Asiakas-olion yhteisillä tiedoilla.
     *
     * @param sahkoposti Asiakkaan sähköpostiosoite.
     * @param asiakastyyppi Asiakkaan tyyppi, joko "yksityinen" tai "yritys".
     * @param nimi Asiakkaan nimi.
     * @param osoite Asiakkaan osoite.
     */
    public Asiakas(String sahkoposti, String asiakastyyppi, String nimi, String osoite) {
        this.sahkoposti = sahkoposti;
        this.asiakastyyppi = asiakastyyppi;
        this.nimi = nimi;
        this.osoite = osoite;
    }

    /**
     * Palauttaa asiakkaan sähköpostiosoitteen.
     *
     * @return Asiakkaan sähköpostiosoite.
     */
    public String getSahkoposti() {
        return sahkoposti;
    }

    /**
     * Asettaa asiakkaan sähköpostiosoitteen.
     *
     * @param sahkoposti Uusi sähköpostiosoite.
     */
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    /**
     * Palauttaa asiakkaan tyypin.
     *
     * @return Asiakkaan tyyppi, joko "yksityinen" tai "yritys".
     */
    public String getAsiakastyyppi() {
        return asiakastyyppi;
    }

    /**
     * Asettaa asiakkaan tyypin.
     *
     * @param asiakastyyppi Uusi asiakastyyppi, joko "yksityinen" tai "yritys".
     */
    public void setAsiakastyyppi(String asiakastyyppi) {
        this.asiakastyyppi = asiakastyyppi;
    }

    /**
     * Palauttaa asiakkaan nimen.
     *
     * @return Asiakkaan nimi.
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa asiakkaan nimen.
     *
     * @param nimi Uusi nimi.
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Palauttaa asiakkaan osoitteen.
     *
     * @return Asiakkaan osoite.
     */
    public String getOsoite() {
        return osoite;
    }

    /**
     * Asettaa asiakkaan osoitteen.
     *
     * @param osoite Uusi osoite.
     */
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    /**
     * Palauttaa yksityisasiakkaan puhelinnumeron.
     *
     * @return Yksityisasiakkaan puhelinnumero, tai null jos asiakas on yritysasiakas.
     */
    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    /**
     * Asettaa yksityisasiakkaan puhelinnumeron.
     *
     * @param puhelinnumero Uusi puhelinnumero.
     */
    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    /**
     * Palauttaa yritysasiakkaan Y-tunnuksen.
     *
     * @return Yritysasiakkaan Y-tunnus, tai null jos asiakas on yksityisasiakas.
     */
    public String getYTunnus() {
        return ytunnus;
    }

    /**
     * Asettaa yritysasiakkaan Y-tunnuksen.
     *
     * @param ytunnus Uusi Y-tunnus.
     */
    public void setYTunnus(String ytunnus) {
        this.ytunnus = ytunnus;
    }
}

