package mokki.mokki.gui.paapaneeli;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import mokki.mokki.gui.alipaneeli.Hallintapaneeli;
import mokki.mokki.gui.alipaneeli.Taulukkopaneeli;
import mokki.mokki.gui.wrapper.TaulukkoWrapper;

/**
 * Luokka toteuttaa kohteiden tietojen hallintaan tarkoitetun paneelin.
 */
public class KohteetPaneeli extends VBox {
    private Hallintapaneeli hallintapaneeli;
    private Taulukkopaneeli<TaulukkoWrapper> taulukkopaneeli;

    /**
     * Luokan alustaja
     * @param fonttikoko fontin koko
     * @param taulukonSisalto taulukon sisältö
     */
    public KohteetPaneeli(int fonttikoko, ObservableList<TaulukkoWrapper> taulukonSisalto) {
        hallintapaneeli = new Hallintapaneeli(
                new String[] {"Lisää kohde", "Rajaa kohteita", "Poista rajaukset"});
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä kohteen tiedot", "Muuta kohteen tietoja", "Poista kohde"}, taulukonSisalto);
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
    public Taulukkopaneeli<TaulukkoWrapper> getTaulukkopaneeli() {
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
