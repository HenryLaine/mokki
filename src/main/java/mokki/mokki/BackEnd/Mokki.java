package mokki.mokki.BackEnd;

/**
 * Tämä luokka edustaa mökkiä, joka sisältää tiedot, kuten sijainnin, hinnan, huonealan ja maksimihenkilömäärän.
 */
public class Mokki {
    private int mokkiID;
    private String sijainti;
    private double hinta;
    private int huoneala;
    private int henkiloMaara;

    /**
     * Luo mökki-olion kaikilla tiedoilla.
     *
     * @param mokkiID Mökin yksilöllinen tunniste (ID).
     * @param sijainti Mökin sijainti.
     * @param hinta Mökin hinta.
     * @param huoneala Mökin huoneala neliömetreinä.
     * @param henkiloMaara Mökin maksimihenkilömäärä.
     */
    public Mokki(int mokkiID, String sijainti, double hinta, int huoneala, int henkiloMaara) {
        this.mokkiID = mokkiID;
        this.sijainti = sijainti;
        this.hinta = hinta;
        this.huoneala = huoneala;
        this.henkiloMaara = henkiloMaara;
    }

    // Getterit ja setterit

    /**
     * Palauttaa mökin tunnisteen (ID).
     *
     * @return Mökin tunniste (ID).
     */
    public int getMokkiID() {
        return mokkiID;
    }

    /**
     * Asettaa mökin tunnisteen (ID).
     *
     * @param mokkiID Uusi mökin tunniste.
     */
    public void setMokkiID(int mokkiID) {
        this.mokkiID = mokkiID;
    }

    /**
     * Palauttaa mökin sijainnin.
     *
     * @return Mökin sijainti.
     */
    public String getSijainti() {
        return sijainti;
    }

    /**
     * Asettaa mökin sijainnin.
     *
     * @param sijainti Uusi sijainti.
     */
    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    /**
     * Palauttaa mökin hinnan.
     *
     * @return Mökin hinta.
     */
    public double getHinta() {
        return hinta;
    }

    /**
     * Asettaa mökin hinnan.
     *
     * @param hinta Uusi hinta.
     */
    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    /**
     * Palauttaa mökin huonealan.
     *
     * @return Mökin huoneala neliömetreinä.
     */
    public int getHuoneala() {
        return huoneala;
    }

    /**
     * Asettaa mökin huonealan.
     *
     * @param huoneala Uusi huoneala neliömetreinä.
     */
    public void setHuoneala(int huoneala) {
        this.huoneala = huoneala;
    }

    /**
     * Palauttaa mökin maksimihenkilömäärän.
     *
     * @return Mökin maksimihenkilömäärä.
     */
    public int getHenkiloMaara() {
        return henkiloMaara;
    }

    /**
     * Asettaa mökin maksimihenkilömäärän.
     *
     * @param henkiloMaara Uusi maksimihenkilömäärä.
     */
    public void setHenkiloMaara(int henkiloMaara) {
        this.henkiloMaara = henkiloMaara;
    }
}