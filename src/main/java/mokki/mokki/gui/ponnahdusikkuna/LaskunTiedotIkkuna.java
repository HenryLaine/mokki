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
        ruudukkopaneeli.setPadding(new Insets(10));

        int riviIndeksi = 0;

        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();

        for (int i = 0; i < maaritykset.length; i++) {
            Text otsikko = new Text(maaritykset[i][0] + ":");
            otsikko.setStyle("-fx-font-weight: bold;");
            TextField tekstikentta = new TextField(kenttienArvot[i]);

            // Jos tyyppi on "Laskun tiedot", kenttiä ei voi muokata
            if (tyyppi.equals("Laskun tiedot")) {
                tekstikentta.setEditable(false);
                tekstikentta.setBackground(Background.fill(Color.GAINSBORO));
            }

            tekstikenttaLista.add(tekstikentta);
            ruudukkopaneeli.add(otsikko, 0, riviIndeksi);
            ruudukkopaneeli.add(tekstikentta, 1, riviIndeksi++);
        }
        return ruudukkopaneeli;
    }
    private void lisaaSeparaattori(GridPane paneeli, String otsikko, int riviIndex) {
        Text erotusOtsikko = new Text(otsikko);
        erotusOtsikko.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        paneeli.add(erotusOtsikko, 0, riviIndex, 2, 1);
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

            hyvaksyPainike.setOnAction(e -> {
                boolean arvotHyvat = data.ovatkoArvotHyvaksyttavia(palautaKenttienTiedot());

                if (arvotHyvat) {
                    data.paivitaKenttienArvot(palautaKenttienTiedot());
                    tulos = true;
                    this.close();
                } else {
                    Virheikkuna virheikkuna = new Virheikkuna("Tietokenttävirhe", "Joidenkin kenttien arvot virheelliset");
                    virheikkuna.show();
                }
            });

            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });

        }
        return painikepaneeli;
    }

    public String[] palautaKenttienTiedot() {
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
