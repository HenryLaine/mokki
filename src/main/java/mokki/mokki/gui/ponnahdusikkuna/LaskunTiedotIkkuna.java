package mokki.mokki.gui.ponnahdusikkuna;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mokki.mokki.gui.alipaneeli.TaulukonData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Luokka toteuttaa ikkunan, jolla näytetään ja hallitaan laskun tietoja.
 */
public class LaskunTiedotIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TaulukonData data;
    private ArrayList<TextField> tekstikenttalista;
    private ArrayList<DatePicker> paivamaaravalitsinlista;
    private ToggleGroup valintanapit;
    private boolean tulos = false;
    private String tyyppi;

    public LaskunTiedotIkkuna(TaulukonData data, String tyyppi) {
        this.data = data;
        this.tyyppi = tyyppi;
        paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(20));
        tekstikenttalista = new ArrayList<>();
        paivamaaravalitsinlista = new ArrayList<>();

        HBox alapaneeli = luoPainikepaneeli();

        paapaneeli.setTop(luoRuudukkopaneeli());
        paapaneeli.setBottom(alapaneeli);

        Scene scene = new Scene(paapaneeli, 800, 600);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(tyyppi);
        this.setScene(scene);
    }

    private GridPane luoRuudukkopaneeli() {
        GridPane ruudukkopaneeli = new GridPane();
        ruudukkopaneeli.setHgap(10);
        ruudukkopaneeli.setVgap(10);

        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritelmat = data.getMaaritykset();

        // Laskunumero
        Text laskunumeroOtsikko = new Text("Laskunumero:");
        TextField laskunumeroKentta = new TextField(kenttienArvot[0]);
        if (tyyppi.equals("Laskun tiedot")) {
            laskunumeroKentta.setEditable(false);
            laskunumeroKentta.setFocusTraversable(false);
            laskunumeroKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(laskunumeroKentta);
        ruudukkopaneeli.add(laskunumeroOtsikko, 0, 0);
        ruudukkopaneeli.add(laskunumeroKentta, 1, 0);

        // Tuote
        Text tuoteOtsikko = new Text("Tuote:");
        TextField tuoteKentta = new TextField(kenttienArvot[1]);
        if (tyyppi.equals("Laskun tiedot")) {
            tuoteKentta.setEditable(false);
            tuoteKentta.setFocusTraversable(false);
            tuoteKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(tuoteKentta);
        ruudukkopaneeli.add(tuoteOtsikko, 0, 1);
        ruudukkopaneeli.add(tuoteKentta, 1, 1);

        // Nimi
        Text nimiOtsikko = new Text("Nimi:");
        TextField nimiKentta = new TextField(kenttienArvot[2]);
        if (tyyppi.equals("Laskun tiedot")) {
            nimiKentta.setEditable(false);
            nimiKentta.setFocusTraversable(false);
            nimiKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(nimiKentta);
        ruudukkopaneeli.add(nimiOtsikko, 0, 2);
        ruudukkopaneeli.add(nimiKentta, 1, 2);

        // Sähköposti
        Text sahkopostiOtsikko = new Text("Sähköposti:");
        TextField sahkopostiKentta = new TextField(kenttienArvot[3]);
        if (tyyppi.equals("Laskun tiedot")) {
            sahkopostiKentta.setEditable(false);
            sahkopostiKentta.setFocusTraversable(false);
            sahkopostiKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(sahkopostiKentta);
        ruudukkopaneeli.add(sahkopostiOtsikko, 0, 3);
        ruudukkopaneeli.add(sahkopostiKentta, 1, 3);

        // Osoite
        Text osoiteOtsikko = new Text("Osoite:");
        TextField osoiteKentta = new TextField(kenttienArvot[4]);
        if (tyyppi.equals("Laskun tiedot")) {
            osoiteKentta.setEditable(false);
            osoiteKentta.setFocusTraversable(false);
            osoiteKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(osoiteKentta);
        ruudukkopaneeli.add(osoiteOtsikko, 0, 4);
        ruudukkopaneeli.add(osoiteKentta, 1, 4);

        // Viitenumero
        Text viitenumeroOtsikko = new Text("Viitenumero:");
        TextField viitenumeroKentta = new TextField(kenttienArvot[5]);
        if (tyyppi.equals("Laskun tiedot")) {
            viitenumeroKentta.setEditable(false);
            viitenumeroKentta.setFocusTraversable(false);
            viitenumeroKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(viitenumeroKentta);
        ruudukkopaneeli.add(viitenumeroOtsikko, 0, 5);
        ruudukkopaneeli.add(viitenumeroKentta, 1, 5);

        // Veroton hinta
        Text verotonHintaOtsikko = new Text("Veroton hinta:");
        TextField verotonHintaKentta = new TextField(kenttienArvot[6]);
        if (tyyppi.equals("Laskun tiedot")) {
            verotonHintaKentta.setEditable(false);
            verotonHintaKentta.setFocusTraversable(false);
            verotonHintaKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(verotonHintaKentta);
        ruudukkopaneeli.add(verotonHintaOtsikko, 0, 6);
        ruudukkopaneeli.add(verotonHintaKentta, 1, 6);

        // Päivämäärä
        Text paivamaaraOtsikko = new Text("Päivämäärä:");
        DatePicker paivamaaraKentta = new DatePicker(LocalDate.now());
        if (tyyppi.equals("Laskun tiedot")) {
            paivamaaraKentta.setEditable(false);
            paivamaaraKentta.setFocusTraversable(false);
            paivamaaraKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        paivamaaravalitsinlista.add(paivamaaraKentta);
        ruudukkopaneeli.add(paivamaaraOtsikko, 0, 7);
        ruudukkopaneeli.add(paivamaaraKentta, 1, 7);

        // Eräpäivä
        Text erapaivaOtsikko = new Text("Eräpäivä:");
        DatePicker erapaivaKentta = new DatePicker(LocalDate.now());
        if (tyyppi.equals("Laskun tiedot")) {
            erapaivaKentta.setEditable(false);
            erapaivaKentta.setFocusTraversable(false);
            erapaivaKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        paivamaaravalitsinlista.add(erapaivaKentta);
        ruudukkopaneeli.add(erapaivaOtsikko, 0, 8);
        ruudukkopaneeli.add(erapaivaKentta, 1, 8);

        // Tila
        Text tilaOtsikko = new Text("Tila:");
        TextField tilaKentta = new TextField(kenttienArvot[9]);
        if (tyyppi.equals("Laskun tiedot")) {
            tilaKentta.setEditable(false);
            tilaKentta.setFocusTraversable(false);
            tilaKentta.setBackground(Background.fill(Color.GAINSBORO));
        }
        tekstikenttalista.add(tilaKentta);
        ruudukkopaneeli.add(tilaOtsikko, 0, 9);
        ruudukkopaneeli.add(tilaKentta, 1, 9);

        return ruudukkopaneeli;
    }

    private HBox luoPainikepaneeli() {
        HBox painikepaneeli = new HBox(20);
        painikepaneeli.setPadding(new Insets(20, 0, 0, 0));
        if (tyyppi.equals("Laskun tiedot")) {
            Button suljePainike = new Button("Sulje");
            suljePainike.setMinWidth(100);
            painikepaneeli.getChildren().add(suljePainike);
            suljePainike.setOnAction(e -> this.close());
        } else {
            Button hyvaksyPainike = new Button(tyyppi.equals("Lisää lasku") ? "Lisää lasku" : "Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");
            hyvaksyPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);
            painikepaneeli.getChildren().addAll(hyvaksyPainike, peruutaPainike);

            hyvaksyPainike.setOnAction(e -> {
                boolean arvotHyvaksyttavia = data.ovatkoArvotHyvaksyttavia(palautaKenttienTiedot());
                if (arvotHyvaksyttavia) {
                    data.paivitaKenttienArvot(palautaKenttienTiedot());
                    tulos = true;
                    this.close();
                } else {
                    Alert virhe = new Alert(Alert.AlertType.ERROR);
                    virhe.setTitle("Virhe");
                    virhe.setHeaderText("Tietokenttävirhe");
                    virhe.setContentText("Tarkista kenttien arvot.");
                    virhe.showAndWait();
                }
            });

            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });
        }

        return painikepaneeli;
    }

    public boolean naytaJaOdotaJaPalautaTulos() {
        this.showAndWait();
        return tulos;
    }

    public void asetaFonttikoko(int fonttikoko) {
        paapaneeli.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

    public String[] palautaKenttienTiedot() {
        String[] tiedot;

        if (tyyppi.equals("Laskun tiedot")) {
            // Palautetaan laskun tiedot järjestyksessä
            tiedot = new String[] {
                    tekstikenttalista.get(0).getText(), // Laskunumero
                    tekstikenttalista.get(1).getText(), // Tuote
                    tekstikenttalista.get(2).getText(), // Nimi
                    tekstikenttalista.get(3).getText(), // Sähköposti
                    tekstikenttalista.get(4).getText(), // Osoite
                    tekstikenttalista.get(5).getText(), // Viitenumero
                    tekstikenttalista.get(6).getText(), // Veroton hinta
                    paivamaaravalitsinlista.get(0).getValue().toString(), // Päivämäärä
                    paivamaaravalitsinlista.get(1).getValue().toString(), // Eräpäivä
                    tekstikenttalista.get(7).getText()  // Tila
            };
        } else if (tyyppi.equals("Muokkaa laskun tietoja") || tyyppi.equals("Lisää lasku")) {
            // Palautetaan tiedot muokkausta tai lisäystä varten
            tiedot = new String[] {
                    tekstikenttalista.get(0).getText(), // Laskunumero
                    tekstikenttalista.get(1).getText(), // Tuote
                    tekstikenttalista.get(2).getText(), // Nimi
                    tekstikenttalista.get(3).getText(), // Sähköposti
                    tekstikenttalista.get(4).getText(), // Osoite
                    tekstikenttalista.get(5).getText(), // Viitenumero
                    tekstikenttalista.get(6).getText(), // Veroton hinta
                    paivamaaravalitsinlista.get(0).getValue().toString(), // Päivämäärä
                    paivamaaravalitsinlista.get(1).getValue().toString(), // Eräpäivä
                    tekstikenttalista.get(7).getText()  // Tila
            };
        } else {
            // Palautetaan tyhjä taulukko, jos tyyppiä ei tunnisteta
            tiedot = new String[] {""};
        }

        return tiedot;
    }
}