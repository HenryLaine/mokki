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

import java.util.ArrayList;

public class LaskunTiedotIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TaulukonData data;
    private ArrayList<TextField> tekstikenttaLista;
    private boolean tulos = false;
    private String tyyppi;

    public LaskunTiedotIkkuna(TaulukonData data, String tyyppi) {
        this.data = data;
        this.tyyppi = tyyppi;
        this.tekstikenttaLista = new ArrayList<>();
        this.paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(20));

        VBox ylapaneeli = new VBox();
        GridPane ruudukkopaneeli = luoRuudukkopaneeli(data, tyyppi);
        ylapaneeli.getChildren().addAll(ruudukkopaneeli);

        VBox alapaneeli = new VBox();
        alapaneeli.getChildren().addAll(luoPainikepaneeli(data,tyyppi));

        paapaneeli.setTop(ylapaneeli);
        paapaneeli.setBottom(alapaneeli);

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Lisää lasku");
        this.setScene(kehys);
    }

    private GridPane luoRuudukkopaneeli(TaulukonData data, String tyyppi) {
        GridPane ruudukkopaneeli = new GridPane();
        ruudukkopaneeli.setHgap(10);
        ruudukkopaneeli.setVgap(10);

        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();

        for (int i = 0; i < maaritykset.length; i++) {
            Text otsikko = new Text(maaritykset[i][0] + ":");
            otsikko.setStyle("-fx-font-weight:bold;");

            TextField tekstikentta = new TextField();
            tekstikentta.setText(kenttienArvot[i]);

            if (tyyppi.equals("Laskun tiedot")) {
                tekstikentta.setEditable(false);
                tekstikentta.setBackground(Background.fill(Color.GAINSBORO));
            }

            tekstikenttaLista.add(tekstikentta);
            ruudukkopaneeli.add(otsikko, 0, i);
            ruudukkopaneeli.add(tekstikentta, 1, i);
        }

        // Laskunumero ja päivämäärä
        Text laskunumeroText = new Text("Laskunumero: ");
        laskunumeroText.setStyle("-fx-font-weight:bold;");
        TextField laskunumeroKentta = new TextField();
        tekstikenttaLista.add(laskunumeroKentta);

        Text paivamaaraText = new Text("Päivämäärä: ");
        paivamaaraText.setStyle("-fx-font-weight:bold;");
        DatePicker paivamaaraKentta = new DatePicker();

        ruudukkopaneeli.add(laskunumeroText, 0, 0);
        ruudukkopaneeli.add(laskunumeroKentta, 1, 0);
        ruudukkopaneeli.add(paivamaaraText, 0, 1);
        ruudukkopaneeli.add(paivamaaraKentta, 1, 1);

        // Tuote-erittely
        Text tuoteErittelyText = new Text("Tuote-erittely: ");
        tuoteErittelyText.setStyle("-fx-font-weight:bold;");

        ruudukkopaneeli.add(tuoteErittelyText, 0, 2);

        TableView<String> tuoteTaulukko = new TableView<>();
        tuoteTaulukko.setPrefHeight(200);
        tuoteTaulukko.getColumns().addAll(
                new TableColumn<>("Tuote"),
                new TableColumn<>("Kohde"),
                new TableColumn<>("Määrä"),
                new TableColumn<>("Yks."),
                new TableColumn<>("A-hinta"),
                new TableColumn<>("Alv-kanta"),
                new TableColumn<>("yht. alv 0"),
                new TableColumn<>("Alv yht."),
                new TableColumn<>("Yhteensä")
        );

        ruudukkopaneeli.add(tuoteTaulukko, 1, 2, 2, 1);

        // Maksutiedot
        Text maksutiedotText = new Text("Maksutiedot: ");
        maksutiedotText.setStyle("-fx-font-weight:bold;");

        TextField saajanTilinumero = new TextField();
        TextField bic = new TextField();
        TextField saaja = new TextField();
        TextField maksajanNimi = new TextField();
        TextField maksajanOsoite = new TextField();
        DatePicker erapaiva = new DatePicker();
        TextField viitenumero = new TextField();
        TextField summa = new TextField();

        tekstikenttaLista.add(saajanTilinumero);
        tekstikenttaLista.add(bic);
        tekstikenttaLista.add(saaja);
        tekstikenttaLista.add(maksajanNimi);
        tekstikenttaLista.add(viitenumero);
        tekstikenttaLista.add(summa);

        ruudukkopaneeli.add(new Label("Saajan tilinumero: "), 0, 4);
        ruudukkopaneeli.add(saajanTilinumero, 1, 4);
        ruudukkopaneeli.add(new Label("BIC: "), 0, 5);
        ruudukkopaneeli.add(bic, 1, 5);
        ruudukkopaneeli.add(new Label("Saaja: "), 0, 6);
        ruudukkopaneeli.add(saaja, 1, 6);
        ruudukkopaneeli.add(new Label("Maksajan nimi ja osoite: "), 0, 7);
        ruudukkopaneeli.add(maksajanNimi, 1, 7);
        ruudukkopaneeli.add(maksajanOsoite, 2, 7);
        ruudukkopaneeli.add(new Label("Eräpäivä: "), 0, 8);
        ruudukkopaneeli.add(erapaiva, 1, 8);
        ruudukkopaneeli.add(new Label("Viitenumero: "), 0, 9);
        ruudukkopaneeli.add(viitenumero, 1, 9);
        ruudukkopaneeli.add(new Label("Euro: "), 0, 10);
        ruudukkopaneeli.add(summa, 1, 10);

        return ruudukkopaneeli;
    }


    private HBox luoPainikepaneeli(TaulukonData data, String tyyppi) {
        HBox painikepaneeli = new HBox(20);
        painikepaneeli.setPadding(new Insets(20, 0, 0, 0));

        if (tyyppi.equals("Laskun tiedot")) {
            Button suljePainike = new Button("Sulje");
            suljePainike.setMinWidth(100);
            suljePainike.setOnAction(e -> this.close());

            painikepaneeli.getChildren().addAll(suljePainike);
        } else if (tyyppi.equals("Lisää lasku") || tyyppi.equals("Muuta laskun tietoja")) {
            Button hyvaksyPainike = new Button("Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");

            if (tyyppi.equals("Lisää lasku")) {
                hyvaksyPainike.setText("Lisää lasku");
            }

            hyvaksyPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);
            painikepaneeli.getChildren().addAll(hyvaksyPainike, peruutaPainike);

            // TODO: actionit napeille
        }
        return painikepaneeli;
    }

    private String[] palautaKenttienTiedot() {
        return tekstikenttaLista.stream().map(TextField::getText).toArray(String[]::new);
    }

    public boolean naytaJaOdotaJaPalautaTulos() {
        this.showAndWait();
        return tulos;
    }

    public void asetaFonttikoko(int fonttikoko) {
        paapaneeli.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }
}
