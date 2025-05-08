
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
import mokki.mokki.gui.testiluokatTaulukonDatalle.LaskutWrapper;

import java.time.LocalDate;
import java.util.ArrayList;

public class LaskunTiedotIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TaulukonData data;
    private ArrayList<TextField> tekstikenttalista;
    private ArrayList<DatePicker> datePickerLista;
    private boolean tulos = false;
    private String tyyppi;

    public LaskunTiedotIkkuna (TaulukonData data, String tyyppi) {
        this.data = data;
        this.tyyppi = tyyppi;

        tekstikenttalista = new ArrayList<>();
        datePickerLista = new ArrayList<>();

        paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(30));

        VBox ylapaneeli = new VBox();

        GridPane ruudukkopaneeli = luoRuudukkopaneeli();
        ylapaneeli.getChildren().add(ruudukkopaneeli);

        VBox alapaneeli = new VBox();
        alapaneeli.getChildren().add(luoPainikepaneeli());

        paapaneeli.setTop(ylapaneeli);
        paapaneeli.setBottom(alapaneeli);

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(tyyppi);
        this.setScene(kehys);
    }

    // TODO: Viitenumero puuttuu
    private GridPane luoRuudukkopaneeli() {
        GridPane ruudukkopaneeli = new GridPane();
        ruudukkopaneeli.setVgap(20);
        ruudukkopaneeli.setHgap(20);

        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();

        for (int i = 0; i < maaritykset.length; i++) {
            String otsikkoTeksti = maaritykset[i][0];
            Text otsikko = new Text(otsikkoTeksti + ":");

            if (tyyppi.equals("Lisää lasku") && otsikkoTeksti.equals("Asiakas")) {
                // Korvataan "Asiakas" kahdella kentällä: "Nimi" ja "Sähköposti"
                Text otsikkoNimi = new Text("Nimi:");
                TextField tekstikenttaNimi = new TextField();

                Text otsikkoSahkoposti = new Text("Sähköposti:");
                TextField tekstikenttaSahkoposti = new TextField();

                tekstikenttalista.add(tekstikenttaNimi); // Lisää nimi listaan
                tekstikenttalista.add(tekstikenttaSahkoposti); // Lisää sähköposti listaan

                ruudukkopaneeli.add(otsikkoNimi, 0, i);
                ruudukkopaneeli.add(tekstikenttaNimi, 1, i);
                i++; // Siirrytään seuraavalle riville
                ruudukkopaneeli.add(otsikkoSahkoposti, 0, i);
                ruudukkopaneeli.add(tekstikenttaSahkoposti, 1, i);

                continue; // Ohitetaan "Asiakas"-kenttä
            }

            if (otsikkoTeksti.equals("Viitenumero")) {
                // Erityiskäsittely viitenumerolle
                TextField tekstikenttaViitenumero = new TextField();
                if (!tyyppi.equals("Lisää lasku")) {
                    tekstikenttaViitenumero.setText(kenttienArvot[i]);
                }
                if (tyyppi.equals("Laskun tiedot")) {
                    tekstikenttaViitenumero.setEditable(false);
                    tekstikenttaViitenumero.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                tekstikenttalista.add(tekstikenttaViitenumero);
                ruudukkopaneeli.add(otsikko, 0, i);
                ruudukkopaneeli.add(tekstikenttaViitenumero, 1, i);
            } else if (otsikkoTeksti.equals("Päivämäärä") || otsikkoTeksti.equals("Eräpäivä")) {
                // Päivämäärä ja eräpäivä käsitellään DatePickerillä
                DatePicker datePicker = new DatePicker();

                if (!tyyppi.equals("Lisää lasku")) {
                    if (!kenttienArvot[i].isEmpty()) {
                        datePicker.setValue(LocalDate.parse(kenttienArvot[i]));
                    }
                    if (tyyppi.equals("Laskun tiedot")) {
                        datePicker.setDisable(true);
                    }
                }

                datePickerLista.add(datePicker);
                ruudukkopaneeli.add(otsikko, 0, i);
                ruudukkopaneeli.add(datePicker, 1, i);
            } else {
                // Muut kentät pysyvät tekstikenttinä
                TextField tekstikentta = new TextField();
                if (!tyyppi.equals("Lisää lasku")) {
                    tekstikentta.setText(kenttienArvot[i]);
                }
                if (tyyppi.equals("Laskun tiedot")) {
                    tekstikentta.setEditable(false);
                    tekstikentta.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                tekstikenttalista.add(tekstikentta);
                ruudukkopaneeli.add(otsikko, 0, i);
                ruudukkopaneeli.add(tekstikentta, 1, i);
            }
        }

        return ruudukkopaneeli;
    }

    private HBox luoPainikepaneeli() {
        HBox painikepaneeli = new HBox();
        painikepaneeli.setPadding(new Insets(10, 0, 0, 0));
        painikepaneeli.setSpacing(10);

        if (tyyppi.equals("Laskun tiedot")) {
            Button suljePainike = new Button("Sulje");
            suljePainike.setOnAction(e -> this.close());
            painikepaneeli.getChildren().add(suljePainike);
        } else if (tyyppi.equals("Lisää lasku")) {
            Button lisaaPainike = new Button("Lisää lasku");
            Button peruutaPainike = new Button("Peruuta");

            lisaaPainike.setOnAction(e -> {
                String[] syotteet = palautaKenttienTiedot();
                boolean arvotHyvaksyttavia = data.ovatkoArvotHyvaksyttavia(syotteet);
                if (arvotHyvaksyttavia) {
                    boolean paivitysOnnistui = data.paivitaKenttienArvot(syotteet);
                    if (paivitysOnnistui) {
                        tulos = true;
                        this.close();
                    } else {
                        naytaVirheIlmoitus("Tietojen tallennuksessa tapahtui virhe. Tarkista syötteet.");
                    }
                } else {
                    naytaVirheIlmoitus("Jotkin kentät sisältävät virheellisiä arvoja. Tarkista arvot.");
                }
            });

            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });

            painikepaneeli.getChildren().addAll(lisaaPainike, peruutaPainike);
        } else if (tyyppi.equals("Muuta laskun tietoja")) {
            Button muutaPainike = new Button("Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");

            muutaPainike.setOnAction(e -> {
                String[] syotteet = palautaKenttienTiedot();
                boolean arvotHyvaksyttavia = data.ovatkoArvotHyvaksyttavia(syotteet);
                if (arvotHyvaksyttavia) {
                    boolean paivitysOnnistui = data.paivitaKenttienArvot(syotteet);
                    if (paivitysOnnistui) {
                        tulos = true;
                        this.close();
                    } else {
                        naytaVirheIlmoitus("Tietojen tallennuksessa tapahtui virhe. Tarkista syötteet.");
                    }
                } else {
                    naytaVirheIlmoitus("Jotkin kentät sisältävät virheellisiä arvoja. Tarkista arvot.");
                }
            });

            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });

            painikepaneeli.getChildren().addAll(muutaPainike, peruutaPainike);
        }

        return painikepaneeli;
    }

    private void naytaVirheIlmoitus(String virheteksti) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Virhe");
        alert.setHeaderText("Tietokenttävirhe");
        alert.setContentText(virheteksti);
        alert.showAndWait();
    }

    public void asetaFonttikoko(int fonttikoko) {
        paapaneeli.setStyle("-fx-font-size: " + fonttikoko + "px;");
    }

    public String[] palautaKenttienTiedot() {
        ArrayList<String> arvot = new ArrayList<>();

        int tekstikenttaIndeksi = 0;
        int datePickerIndeksi = 0;

        for (String[] maaritys : data.getMaaritykset()) {
            String otsikkoTeksti = maaritys[0];
            if (otsikkoTeksti.equals("Päivämäärä") || otsikkoTeksti.equals("Eräpäivä")) {
                DatePicker datePicker = datePickerLista.get(datePickerIndeksi++);
                LocalDate date = datePicker.getValue();
                arvot.add(date != null ? date.toString() : ""); // Lisätään päivämäärä merkkijonona
            } else {
                TextField tekstikentta = tekstikenttalista.get(tekstikenttaIndeksi++);
                arvot.add(tekstikentta.getText());
            }
        }

        return arvot.toArray(new String[0]);
    }

    /**
     * Näyttää ikkunan ja odottaa käyttäjän toimintaa.
     *
     * @return true, jos tekstikenttien arvot hyväksyttiin; false, jos peruutettiin
     */
    public boolean naytaJaOdotaJaPalautaTulos() {
        this.showAndWait();
        return tulos;
    }
}