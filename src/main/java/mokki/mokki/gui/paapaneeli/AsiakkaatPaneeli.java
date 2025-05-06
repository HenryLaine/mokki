package mokki.mokki.gui.paapaneeli;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import mokki.mokki.gui.alipaneeli.Hallintapaneeli;
import mokki.mokki.gui.alipaneeli.Taulukkopaneeli;
import mokki.mokki.gui.alipaneeli.TaulukonData;

/**
 * Luokka toteuttaa asiakkaiden tietojen hallintaan tarkoitetun paneelin.
 */
public class AsiakkaatPaneeli extends VBox {
    private Hallintapaneeli hallintapaneeli;
    private Taulukkopaneeli<TaulukonData> taulukkopaneeli;

    /**
     * Luokan alustaja
     * @param fonttikoko fontin koko
     * @param taulukonSisalto taulukon sisältö
     */
    public AsiakkaatPaneeli(int fonttikoko, ObservableList<TaulukonData> taulukonSisalto) {
        hallintapaneeli = new Hallintapaneeli(
                new String[] {"Lisää asiakas", "Rajaa asiakkaita", "Poista rajaukset"});
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä asiakkaan tiedot", "Muuta asiakkaan tietoja", "Poista asiakas"},
                taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        this.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

    }

    /**
     * Metodi palauttaa hallintapaneelin.
     * @return hallintapaneeli
     */
    public Hallintapaneeli getHallintapaneeli() {
        return hallintapaneeli;
    }

    /**
     * Metodi palauttaa taulukkopaneelin.
     * @return taulukkopaneeli
     */
    public Taulukkopaneeli<TaulukonData> getTaulukkopaneeli() {
        return taulukkopaneeli;
    }

    /**
     * Metodi asettaa fontin koon.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
    }
}
