package mokki.mokki.gui;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * Luokka toteuttaa käytöliittymän taulukkopaneelin. Taulukko tukee String- ja Integer-tyypin arvoja.
 * @param <T> taulukkoon sijoitettavan tiedon tyyppi
 */
public class Taulukkopaneeli<T> extends TableView<T> {

    /**
     * Luokan alustaja
     * @param sarakkeidenTiedot sarakkeidentiedot muodossa {"Sarakkeen nimi", "Tyyppi", "muuttujanNimi"}
     * @param taulukonSisalto taulukon sisältö
     */
    public Taulukkopaneeli(String[][] sarakkeidenTiedot, ObservableList<T> taulukonSisalto) {
        // Määritetään taulukon asetukset.
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);
        this.setPrefHeight(4000);
        this.setPlaceholder(new Label(""));

        // Luodaan sarakkeet tietojen perusteella.
        for (String[] tiedot : sarakkeidenTiedot) {
            if (tiedot[1].equals("String")) {
                TableColumn<T, String> sarake = new TableColumn<>(tiedot[0]);
                this.getColumns().add(sarake);
                sarake.setCellValueFactory(new PropertyValueFactory<>(tiedot[2]));

                // Määritetään, että tekstisarakkaiden sisältö voidaan rivittää.
                sarake.setCellFactory(param -> {
                    TableCell<T, String> solu = new TableCell<>();
                    Text text = new Text();
                    solu.setGraphic(text);
                    solu.setPrefHeight(Control.USE_COMPUTED_SIZE);
                    text.wrappingWidthProperty().bind(sarake.widthProperty());
                    text.textProperty().bind(solu.itemProperty());
                    return solu;
                });
            }
            else if (tiedot[1].equals("Integer")) {
                TableColumn<T, Integer> sarake = new TableColumn<>(tiedot[0]);
                this.getColumns().add(sarake);
                sarake.setCellValueFactory(new PropertyValueFactory<>(tiedot[2]));
            }
        }

        // Lisätään taulukon sisältö.
        this.setItems(taulukonSisalto);
    }

    /**
     * Metodi muuttaa paneelin tekstin fonttikokoa.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        this.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

}
