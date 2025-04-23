package mokki.mokki.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import java.util.ArrayList;

/**
 * Luokka toteuttaa käyttöliittymän välilehtipaneelin.
 */
public class Valilehtipaneeli extends TabPane {
    /** Välilehdet sisältävä listarakenne */
    private ArrayList<Tab> valilehtilista = new ArrayList<>();

    /**
     * Luokan alustaja
     * @param valilehtienNimet Välilehtien nimet
     */
    public Valilehtipaneeli(String[] valilehtienNimet) {
        // Luodaan välilehdet ja asetetaan ne paneeliin.
        for (String nimi : valilehtienNimet) {
            Tab lehti = new Tab(nimi);
            valilehtilista.add(lehti);
            this.getTabs().add(lehti);
        }
        // Määritetään, että välilehtiä ei voi sulkea.
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    /**
     * Metodi palauttaa välilehtilistan.
     * @return välilehtilista
     */
    public ArrayList<Tab> getValilehtilista() {
        return valilehtilista;
    }

    /**
     * Metodi muuttaa paneelin tekstin fonttikokoa.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        this.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

}
