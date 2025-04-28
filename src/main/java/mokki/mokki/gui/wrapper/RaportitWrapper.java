package mokki.mokki.gui.wrapper;

import javafx.beans.property.*;

/**
 *
 */
public class RaportitWrapper implements TaulukkoWrapper {
    private StringProperty kohde;
    private IntegerProperty kayttoaste;
    private IntegerProperty varaustenMaara;
    private DoubleProperty paivatulot;
    private DoubleProperty viikkotulot;
    private DoubleProperty kuukausitulot;
    private DoubleProperty kokonaistulot;

    private String[][] maaritykset;

    /**
     * Luokan alustaja
     * @param kohde kohde
     * @param kayttoaste käyttöaste
     * @param varaustenMaara varausten määrä
     * @param paivatulot päivätulot
     * @param viikkotulot viikkotulot
     * @param kuukausitulot kuukausitulot
     * @param kokonaistulot kokonaistulot
     */
    public RaportitWrapper(String kohde, int kayttoaste, int varaustenMaara,
                           double paivatulot, double viikkotulot, double kuukausitulot, double kokonaistulot) {

        this.kohde = new SimpleStringProperty(kohde);
        this.kayttoaste = new SimpleIntegerProperty(kayttoaste);
        this.varaustenMaara = new SimpleIntegerProperty(varaustenMaara);
        this.paivatulot = new SimpleDoubleProperty(paivatulot);
        this.viikkotulot = new SimpleDoubleProperty(viikkotulot);
        this.kuukausitulot = new SimpleDoubleProperty(kuukausitulot);
        this.kokonaistulot = new SimpleDoubleProperty(kokonaistulot);

        maaritykset = new String[][] {
                {"Kohde", "String", "kohde"},
                {"Käyttöaste", "Integer", "kayttoaste"},
                {"Varausten määrä", "Integer", "varaustenMaara"},
                {"Päivätulot", "Double", "paivatulot"},
                {"Viikkotulot", "Double", "viikkotulot"},
                {"Kuukausitulot", "Double", "kuukausitulot"},
                {"Kokonaistulot", "Double", "kokonaistulot"}
        };
    }

    /**
     * Metodi palauttaa kohteen.
     * @return kohde
     */
    public String getKohde() {
        return kohde.get();
    }

    /**
     * Metodi palauttaa käyttöasteen.
     * @return käyttöaste
     */
    public int getKayttoaste() {
        return kayttoaste.get();
    }

    /**
     * Metodi palauttaa varausten määrän.
     * @return varauten määrä
     */
    public int getVaraustenMaara() {
        return varaustenMaara.get();
    }

    /**
     * Metodi palauttaa päivätulot.
     * @return päivätulot
     */
    public double getPaivatulot() {
        return paivatulot.get();
    }

    /**
     * Metodi palauttaa viikkotulot.
     * @return viikkotulot
     */
    public double getViikkotulot() {
        return viikkotulot.get();
    }

    /**
     * Metodi palauttaa kuukausitulot.
     * @return kuukausitulot
     */
    public double getKuukausitulot() {
        return kuukausitulot.get();
    }

    /**
     * Metodi palauttaa kokonaistulot.
     * @return kokonaistulot
     */
    public double getKokonaistulot() {
        return kokonaistulot.get();
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
        return kohde.get();
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin eli kohteen tunnuksen.
     * @return tunniste
     */
    public String palautaKuvausteksti() {
        return kohde.get();
    }

    /**
     * Metodi palauttaa kenttien arvot merkkijonolistana.
     * @return kenttien arvot
     */
    public String[] palautaKenttienArvot() {
        return new String[] {kohde.get(), ""+kayttoaste.get(), ""+varaustenMaara.get(),
                ""+paivatulot.get(), ""+viikkotulot.get(), ""+kuukausitulot.get(), ""+kokonaistulot.get()};
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
}
