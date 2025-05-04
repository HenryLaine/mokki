package mokki.mokki.BackEnd;

import java.util.Date;

/**
 *  Tämä luokka luo Lasku olion, jonka metodeja käytetääm DAO-luokassa yhdistämään tietokanta luokkiin
 */
public class Lasku {
    private int laskuID; // Laskun yksilöllinen tunniste
    private double veroton_hinta; // Laskun veroton hinta
    private Date paivamaara; // Laskun päivämäärä
    private Date erapaiva; // Laskun eräpäivä
    private String osoite; // Asiakkaan osoite
    private String nimi; // Asiakkaan nimi
    private String sahkoposti; // Asiakkaan sähköpostiosoite
    private String status; // Laskun status (esim. "maksettu", "myöhässä")
    private int viitenumero; // Laskun viitenumero
    private int varaustunnus; // Laskun varaustunnus

    /**
     * Alustaja, joka luo uuden Lasku-olion kaikilla parametreilla
     *
     * @param laskuID Laskun yksilöllinen tunniste
     * @param verotonHinta Laskun veroton hinta
     * @param paivamaara Laskun päivämäärä
     * @param erapaiva Laskun eräpäivä
     * @param osoite Asiakkaan osoite
     * @param nimi Asiakkaan nimi
     * @param sahkoposti Asiakkaan sähköposti
     * @param status Laskun status
     * @param viitenumero Laskun viitenumero
     * @param varaustunnus Laskun varaustunnus
     */

    public Lasku(int laskuID, double verotonHinta, Date paivamaara, Date erapaiva, String osoite, String nimi, String sahkoposti, String status, int viitenumero, int varaustunnus) {
        this.laskuID = laskuID;
        veroton_hinta = verotonHinta;
        this.paivamaara = paivamaara;
        this.erapaiva = erapaiva;
        this.osoite = osoite;
        this.nimi = nimi;
        this.sahkoposti = sahkoposti;
        this.status = status;
        this.viitenumero = viitenumero;
        this.varaustunnus = varaustunnus;
    }


    /**
     * Palauttaa laskun yksilöllisen tunnisteen.
     *
     * @return Laskun tunniste
     */
    public int getLaskuID() {
        return laskuID;
    }

    /**
     * Asettaa laskun yksilöllisen tunnisteen.
     *
     * @param laskuID Laskun tunniste
     */
    public void setLaskuID(int laskuID) {
        this.laskuID = laskuID;
    }

    /**
     * Palauttaa laskun verottoman hinnan.
     *
     * @return Veroton hinta
     */
    public double getVeroton_hinta() {
        return veroton_hinta;
    }

    /**
     * Asettaa laskun verottoman hinnan.
     *
     * @param veroton_hinta Veroton hinta
     */
    public void setVeroton_hinta(double veroton_hinta) {
        this.veroton_hinta = veroton_hinta;
    }

    /**
     * Palauttaa laskun päivämäärän.
     *
     * @return Laskun päivämäärä
     */
    public Date getPaivamaara() {
        return paivamaara;
    }

    /**
     * Asettaa laskun päivämäärän.
     *
     * @param paivamaara Laskun päivämäärä
     */
    public void setPaivamaara(Date paivamaara) {
        this.paivamaara = paivamaara;
    }

    /**
     * Palauttaa laskun eräpäivän.
     *
     * @return Laskun eräpäivä
     */
    public Date getErapaiva() {
        return erapaiva;
    }

    /**
     * Asettaa laskun eräpäivän.
     *
     * @param erapaiva Laskun eräpäivä
     */
    public void setErapaiva(Date erapaiva) {
        this.erapaiva = erapaiva;
    }

    /**
     * Palauttaa asiakkaan osoitteen.
     *
     * @return Asiakkaan osoite
     */
    public String getOsoite() {
        return osoite;
    }

    /**
     * Asettaa asiakkaan osoitteen.
     *
     * @param osoite Asiakkaan osoite
     */
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    /**
     * Palauttaa asiakkaan nimen.
     *
     * @return Asiakkaan nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa asiakkaan nimen.
     *
     * @param nimi Asiakkaan nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Palauttaa asiakkaan sähköpostiosoitteen.
     *
     * @return Asiakkaan sähköpostiosoite
     */
    public String getSahkoposti() {
        return sahkoposti;
    }

    /**
     * Asettaa asiakkaan sähköpostiosoitteen.
     *
     * @param sahkoposti Asiakkaan sähköpostiosoite
     */
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    /**
     * Palauttaa laskun statuksen.
     *
     * @return Laskun status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Asettaa laskun statuksen.
     *
     * @param status Laskun status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Palauttaa laskun viitenumeron.
     *
     * @return Laskun viitenumero
     */
    public int getViitenumero() {
        return viitenumero;
    }

    /**
     * Asettaa laskun viitenumeron.
     *
     * @param viitenumero Laskun viitenumero
     */
    public void setViitenumero(int viitenumero) {
        this.viitenumero = viitenumero;
    }

    /**
     * Palauttaa laskun varaustunnuksen.
     *
     * @return Laskun varaustunnus
     */
    public int getVaraustunnus() {
        return varaustunnus;
    }

    /**
     * Asettaa laskun varaustunnuksen.
     *
     * @param varaustunnus Laskun varaustunnus
     */
    public void setVaraustunnus(int varaustunnus) {
        this.varaustunnus = varaustunnus;
    }
}
