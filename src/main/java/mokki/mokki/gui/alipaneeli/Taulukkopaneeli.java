package mokki.mokki.gui.alipaneeli;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Luokka toteuttaa käytöliittymän taulukkopaneelin. Taulukko tukee String, Integer ja
 * Double-tyypin arvoja.
 * @param <T> taulukkoon sijoitettavan tiedon tyyppi
 */
public class Taulukkopaneeli<T> extends TableView<T> {
    ArrayList<MenuItem> kontekstivalikonKohdat = new ArrayList<>();

    /**
     * Luokan alustaja, joka ei luo kontekstivalikkoa.
     * @param sarakkeidenTiedot sarakkeidentiedot muodossa {"Sarakkeen nimi", "Tyyppi", "muuttujanNimi"}
     * @param taulukonSisalto taulukon sisältö
     */
    public Taulukkopaneeli(String[][] sarakkeidenTiedot,
                           ObservableList<T> taulukonSisalto) {
        // Määritetään taulukon asetukset.
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);
        this.setPrefHeight(4000);
        this.setPlaceholder(new Label(""));

        // Luodaan sarakkeet
        luoSarakkeet(sarakkeidenTiedot);

        // Lisätään taulukon sisältö.
        this.setItems(taulukonSisalto);
    }

    /**
     * Luokan alustaja, joka luo kontekstivalikon.
     * @param sarakkeidenTiedot sarakkeidentiedot muodossa {"Sarakkeen nimi", "Tyyppi", "muuttujanNimi"}
     * @param taulukonSisalto taulukon sisältö
     */
    public Taulukkopaneeli(String[][] sarakkeidenTiedot, String[] kontekstivalikonNimet,
                           ObservableList<T> taulukonSisalto) {
        // Määritetään taulukon asetukset.
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);
        this.setPrefHeight(4000);
        this.setPlaceholder(new Label(""));

        // Luodaan sarakkeet ja kontekstivalikko.
        luoSarakkeet(sarakkeidenTiedot);
        lisaaKontekstivalikko(kontekstivalikonNimet);

        // Lisätään taulukon sisältö.
        this.setItems(taulukonSisalto);
    }

    /**
     * Metodi luo talukon sarakkeet.
     * @param sarakkeidenTiedot sarakkeiden tiedot
     */
    private void luoSarakkeet(String[][] sarakkeidenTiedot) {
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
            else if (tiedot[1].equals("Double")) {
                TableColumn<T, Double> sarake = new TableColumn<>(tiedot[0]);
                this.getColumns().add(sarake);
                sarake.setCellValueFactory(new PropertyValueFactory<>(tiedot[2]));
                // Määritetään, että desimaaliluvut näytetään pilkun kanssa.
                sarake.setCellFactory(solu -> new TableCell<T, Double>() {
                    @Override
                    protected void updateItem(Double arvo, boolean tyhja) {
                        super.updateItem(arvo, tyhja);
                        if (tyhja) {
                            setText(null);
                        }
                        else {
                            setText(String.valueOf(arvo).replaceAll("\\.", ","));
                        }
                    }
                });
            }
            else if (tiedot[1].equals("LocalDate")) {
                TableColumn<T, LocalDate> sarake = new TableColumn<>(tiedot[0]);
                this.getColumns().add(sarake);
                sarake.setCellValueFactory(new PropertyValueFactory<>(tiedot[2]));
            }
        }
    }

    /**
     * Metodi luo kontekstivalikon.
     * @param kontekstivalikonNimet valikon kohteiden nimet
     */
    private void lisaaKontekstivalikko(String [] kontekstivalikonNimet) {
        ContextMenu kontekstivalikko = new ContextMenu();
        for (String nimi : kontekstivalikonNimet) {
            MenuItem kohta = new MenuItem(nimi);
            kontekstivalikko.getItems().add(kohta);
            kontekstivalikonKohdat.add(kohta);
        }

        this.setRowFactory(tv -> {
            TableRow<T> rivi = new TableRow<>() {
                @Override
                public void updateItem(T tieto, boolean tyhja) {
                    super.updateItem(tieto, tyhja);
                    if (tyhja || tieto == null) {
                        setContextMenu(null);
                    }
                    else {
                        setContextMenu(kontekstivalikko);
                    }
                }
            };
            return rivi;
        });
    }

    /**
     * Metodi palauttaa kontekstivalikon kohdat listarakenteena.
     * @return kohtekstivalikon kohdat listana
     */
    public ArrayList<MenuItem> getKontekstivalikonKohdat() {
        return kontekstivalikonKohdat;
    }

    /**
     * Metodi palauttaa rivillä olevat tiedot oliona.
     * @return rivin tiedot oliona
     */
    public T palautaRivinTiedot() {
        return this.getSelectionModel().getSelectedItem();
    }

    /**
     * Metodi muuttaa paneelin tekstin fonttikokoa.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        this.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

}
