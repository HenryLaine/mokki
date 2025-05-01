package mokki.mokki.BackEnd;

/**
 * Tämä luokka luo Lasku olion, jonka metodeja käytetääm DAO-luokassa yhdistämään tietokanta luokkiin
 */
public class Lasku {

    /** Muuttuja kuvaa laskussa olevaa laskunumeroa*/
    private int laskunumero;

    /** Muuttuja kuvaa laskulla olevan summan määrää*/
    private int lasku_summa;

    /** Muuttuja kuvaa sen hetkistä laskun tilaa (maksettu/ei maksettu) */
    private boolean maksun_tila;

    /** Muuttuja kuvaa laskun eräpäivää*/
    private int erapaiva;

    /** Muuttuja kuvaa laskun päivämäärää*/
    private int lasku_paivamaara;

    /**
     * Konstruktori, jolla asetetaan arvot Lasku olion muuttujille
     * @param laskunumero Laskulla oleva laskunumero
     * @param laskuSumma Laskulla oleva summa
     * @param maksunTila Laskun sen hetkinen tila (maksettu/ei maksettu)
     * @param erapaiva Laskun eräpäivä
     * @param laskuPaivamaara Laskun päivämäärä
     */
    public Lasku(int laskunumero, int laskuSumma, boolean maksunTila, int erapaiva, int laskuPaivamaara) {
        this.laskunumero = laskunumero;
        lasku_summa = laskuSumma;
        maksun_tila = maksunTila;
        this.erapaiva = erapaiva;
        lasku_paivamaara = laskuPaivamaara;
    }

    /**
     * Metodi asettaa parametrina tulleen numeron laskun numeroksi
     * @param laskunumero Ilmaisee laskulla olevaa laskunumero
     */
    public void setLaskunumero(int laskunumero) {
        this.laskunumero = laskunumero;
    }

    /**
     * Metodi palauttaa laskulla olevan laskunumeron
     * @return Laskulla oleva laskunumero
     */
    public int getLaskunumero() {
        return laskunumero;
    }

    /**
     * Metodi
     * @param lasku_summa
     */
    public void setLasku_summa(int lasku_summa) {
        this.lasku_summa = lasku_summa;
    }

    public int getLasku_summa() {
        return lasku_summa;
    }

    public void setMaksun_tila(boolean maksun_tila) {
        this.maksun_tila = maksun_tila;
    }

    public boolean isMaksun_tila() {
        return maksun_tila;
    }

    public void setErapaiva(int erapaiva) {
        this.erapaiva = erapaiva;
    }

    public int getErapaiva() {
        return erapaiva;
    }

    public void setLasku_paivamaara(int lasku_paivamaara) {
        this.lasku_paivamaara = lasku_paivamaara;
    }

    public int getLasku_paivamaara() {
        return lasku_paivamaara;
    }
}
