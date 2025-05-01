package mokki.mokki.gui.paapaneeli;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import mokki.mokki.gui.alipaneeli.RaportitHallintapaneeli;
import mokki.mokki.gui.alipaneeli.Taulukkopaneeli;
import mokki.mokki.gui.alipaneeli.TaulukonData;

/**
 * Luokka toteuttaa raporttien tietojen hallintaan tarkoitetun paneelin.
 */
public class RaportitPaneeli extends VBox {
    private RaportitHallintapaneeli hallintapaneeli;
    private Taulukkopaneeli<TaulukonData> taulukkopaneeli;

    /**
     * Luokan alustaja
     * @param fonttikoko fontin koko
     * @param taulukonSisalto taulukon sisältö
     */
    public RaportitPaneeli(int fonttikoko, ObservableList<TaulukonData> taulukonSisalto) {
        hallintapaneeli = new RaportitHallintapaneeli();
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä kohteen tiedot", "Näytä kohteen varaukset"}, taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        this.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

    }

    /**
     * Metodi palauttaa hallintapaneelin.
     * @return hallintapaneeli
     */
    public RaportitHallintapaneeli getHallintapaneeli() {
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
