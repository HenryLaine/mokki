package mokki.mokki.BackEnd;

public class Mokki
{
    private int hinta;
    private String tunnus;
    private int huoneet;
    private String sijainti;
    private int ala;

    Mokki(String tunnus, int hinta, int huoneet, String sijainti, int ala)
    {
        this.tunnus = tunnus;
        this.hinta = hinta;
        this.huoneet = huoneet;
        this.sijainti = sijainti;
        this.ala = ala;
    }

    public void setAla(int ala) {
        this.ala = ala;
    }

    public void setHinta(int hinta) {
        this.hinta = hinta;
    }

    public void setHuoneet(int huoneet) {
        this.huoneet = huoneet;
    }

    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }

    public int getAla() {
        return ala;
    }

    public int getHinta() {
        return hinta;
    }

    public int getHuoneet() {
        return huoneet;
    }

    public String getSijainti() {
        return sijainti;
    }

    public String getTunnus() {
        return tunnus;
    }

    public void poistaMokki(Mokki mokki)
    {
        mokki = null;
    }



}
