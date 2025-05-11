package mokki.mokki.gui.testiluokatTaulukonDatalle;

import javafx.beans.property.*;
import mokki.mokki.dao.RaportitDAO;
import mokki.mokki.gui.alipaneeli.TaulukonData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public class RaportitWrapper implements TaulukonData {
    private StringProperty kohde;
    private IntegerProperty kayttoaste;
    private IntegerProperty varaustenMaara;
    private DoubleProperty paivatulot;
    private DoubleProperty viikkotulot;
    private DoubleProperty kuukausitulot;
    private DoubleProperty kokonaistulot;

    private String[][] maaritykset;
    private RaportitDAO dao;

    /**
     * Luokan alustaja
     */
    public RaportitWrapper(ResultSet rs) throws SQLException {
        this.kohde = new SimpleStringProperty(rs.getString("kohde"));
        this.kayttoaste = new SimpleIntegerProperty(rs.getInt("kayttoaste_prosentti"));
        this.varaustenMaara = new SimpleIntegerProperty(rs.getInt("varausten_maara"));
        this.paivatulot = new SimpleDoubleProperty(rs.getDouble("paivatulot"));
        this.viikkotulot = new SimpleDoubleProperty(rs.getDouble("viikkotulot"));
        this.kuukausitulot = new SimpleDoubleProperty(rs.getDouble("kuukausitulot"));
        this.kokonaistulot = new SimpleDoubleProperty(rs.getDouble("kokonaistulot"));

        this.maaritykset = new String[][] {
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
     *
     * @return tunniste
     */
    public String palautaTunniste() {
        return kohde.get();
    }

    /**
     * Metodi palauttaa tietokokonaisuuden kuvaustekstin eli kohteen tunnuksen.
     *
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

    @Override
    public boolean paivitaKenttienArvot(String[] arvot) {
        return false;
    }

    public boolean paivitaKenttienArvot(String[] arvot, LocalDate alku, LocalDate loppu) {
        try {
            if (dao == null) return false;

            // Haetaan tietokannasta raportti uudestaan – vain tälle kohteelle
            List<RaportitWrapper> raportit = dao.haeRaportti(alku, loppu);

            if (!raportit.isEmpty()) {
                RaportitWrapper uusi = raportit.get(0); // Oletetaan että 1 vastaus

                this.kayttoaste.set(uusi.getKayttoaste());
                this.varaustenMaara.set(uusi.getVaraustenMaara());
                this.paivatulot.set(uusi.getPaivatulot());
                this.viikkotulot.set(uusi.getViikkotulot());
                this.kuukausitulot.set(uusi.getKuukausitulot());
                this.kokonaistulot.set(uusi.getKokonaistulot());

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        boolean[] totuusarvolista = new boolean[arvot.length];
        for (int i = 0; i < arvot.length; i++) {
            totuusarvolista[i] = true;
        }
        return totuusarvolista;
    }

    public boolean onkoTunnisteUniikki(String tunniste) {
        // TODO: tarkista, että tunnistetta ei löydy tietokannasta.
        return false;
    }

    public int palautaTunnisteenIndeksi() {
        return 0;
    }
}
