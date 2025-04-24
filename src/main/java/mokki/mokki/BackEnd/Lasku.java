package mokki.mokki.BackEnd;

public class Lasku {
    private int laskunumero;

    private int lasku_summa;
    private boolean maksun_tila;
    private int erapaiva;
    private int lasku_paivamaara;

    public Lasku(int laskunumero, int laskuSumma, boolean maksunTila, int erapaiva, int laskuPaivamaara) {
        this.laskunumero = laskunumero;
        lasku_summa = laskuSumma;
        maksun_tila = maksunTila;
        this.erapaiva = erapaiva;
        lasku_paivamaara = laskuPaivamaara;
    }

    public void setLaskunumero(int laskunumero) {
        this.laskunumero = laskunumero;
    }

    public int getLaskunumero() {
        return laskunumero;
    }

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
