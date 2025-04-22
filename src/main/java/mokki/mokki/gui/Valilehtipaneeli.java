package mokki.mokki.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

public class Valilehtipaneeli extends TabPane {
    private ArrayList<Tab> valilehtilista = new ArrayList<>();

    public Valilehtipaneeli(String[] valilehtienNimet) {
        for (String nimi : valilehtienNimet) {
            Tab lehti = new Tab(nimi);
            valilehtilista.add(lehti);
            this.getTabs().add(lehti);
        }
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    public ArrayList<Tab> getValilehtilista() {
        return valilehtilista;
    }

    public void asetaFonttikoko(int fonttikoko) {
        fonttikoko = (int)Math.round(fonttikoko * 1.3);
        this.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

}
