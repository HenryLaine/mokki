package mokki.mokki.gui.ponnahdusikkuna;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mokki.mokki.gui.wrapper.TaulukkoWrapper;

import java.util.ArrayList;


public class TiedotIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TaulukkoWrapper dataluokka;
    private ArrayList<TextField> tekstikenttalista;
    private TextArea tekstialue;
    private boolean muokattavissa;
    private boolean tekstialueKaytossa;
    private boolean tulos = false;

    /**
     * Alustaja, joka luo muokattavan ikkunan tyhjillä tiedoilla
     */
    public TiedotIkkuna(TaulukkoWrapper kohdeluokka) {

    }


    public TiedotIkkuna(TaulukkoWrapper dataluokka, boolean tekstialueKaytossa,
                        boolean muokattavissa, String otsikko) {
        this.dataluokka = dataluokka;
        this.muokattavissa = muokattavissa;
        this.tekstialueKaytossa = tekstialueKaytossa;
        tekstikenttalista = new ArrayList<>();
        paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(20));
        VBox ylapaneeli = new VBox();
        GridPane ruudukkopaneeli = luoRuudukkopaneeli();
        if (tekstialueKaytossa) {
            VBox tekstialuepaneeli = luotekstialuepaneeli();
            ylapaneeli.getChildren().addAll(ruudukkopaneeli, tekstialuepaneeli);
        }
        else {
            ylapaneeli.getChildren().add(ruudukkopaneeli);
        }
        VBox alapaneeli = new VBox();
        alapaneeli.getChildren().add(luoPainikepaneeli());
        paapaneeli.setTop(ylapaneeli);
        paapaneeli.setBottom(alapaneeli);

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(otsikko);
        this.setScene(kehys);
    }

    private GridPane luoRuudukkopaneeli() {
        GridPane ruudukkopaneeli = new GridPane(40, 40);
        String[] kenttienArvot = dataluokka.palautaKenttienArvot();
        String[][] maaritykset = dataluokka.getMaaritykset();
        int pituus;
        if (tekstialueKaytossa) {
            pituus = kenttienArvot.length - 1;
        }
        else {
            pituus = kenttienArvot.length;
        }
        int vaihtoindeksi = Math.round((float)pituus / 2);
        int sarake = 0;
        int rivi = 0;
        for (int i = 0; i < pituus; i++) {
            Text otsikko = new Text(maaritykset[i][0]);
            otsikko.setStyle("-fx-font-weight:bold;");
            TextField tekstikentta = new TextField();
            if (maaritykset[i][1].equals("Double")) {
                tekstikentta.setText(kenttienArvot[i].replaceAll("\\.", ","));
            }
            else {
                tekstikentta.setText(kenttienArvot[i]);
            }
            tekstikentta.setContextMenu(new ContextMenu());
            if (!muokattavissa) {
                tekstikentta.setEditable(false);
                tekstikentta.setFocusTraversable(false);
                tekstikentta.setBackground(Background.fill(Color.GAINSBORO));
            }
            tekstikenttalista.add(tekstikentta);

            if (rivi == vaihtoindeksi) {
                rivi = 0;
                sarake = 2;
            }
            ruudukkopaneeli.add(otsikko, sarake, rivi);
            ruudukkopaneeli.add(tekstikentta, sarake + 1, rivi);
            rivi = rivi + 1;
        }

        return ruudukkopaneeli;
    }

    private VBox luotekstialuepaneeli() {
        String[] kenttienArvot = dataluokka.palautaKenttienArvot();
        String[][] maaritykset = dataluokka.getMaaritykset();
        VBox tekstialuepaneeli = new VBox(10);
        tekstialuepaneeli.setPadding(new Insets(30,0,0,0));

        Text otsikko = new Text(maaritykset[maaritykset.length - 1][0]);
        otsikko.setStyle("-fx-font-weight:bold;");
        tekstialue = new TextArea(kenttienArvot[kenttienArvot.length - 1]);
        tekstialue.setWrapText(true);
        tekstialue.setContextMenu(new ContextMenu());

        if (!muokattavissa) {
            tekstialue.setEditable(false);
            tekstialue.setFocusTraversable(false);
            tekstialue.setStyle("-fx-control-inner-background:#DCDCDC;-fx-background-color:#DCDCDC;");
        }

        tekstialuepaneeli.getChildren().addAll(otsikko, tekstialue);

        return tekstialuepaneeli;
    }

    private HBox luoPainikepaneeli() {
        HBox painikepaneeli = new HBox(20);
        if (muokattavissa) {
            Button muutaPainike = new Button("Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");
            muutaPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);
            painikepaneeli.getChildren().addAll(muutaPainike, peruutaPainike);

            muutaPainike.setOnAction(e -> {
                tulos = dataluokka.ovatkoArvotHyvaksyttavia(palautaKenttienTiedot());
                if (tulos) {
                    this.close();
                }
                else {
                    //TODO: Luodaan virheikkuna
                }
            });
            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });
        }
        else {
            Button suljePainike = new Button("Sulje");
            suljePainike.setMinWidth(100);
            painikepaneeli.getChildren().add(suljePainike);
            suljePainike.setOnAction(e -> {
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
        if (!tekstialueKaytossa) {
            tiedot = new String[tekstikenttalista.size()];
            for (int i = 0; i < tiedot.length; i++) {
                if (dataluokka.getMaaritykset()[i][2].equals("Double")) {
                    tiedot[i] = tekstikenttalista.get(i).getText().replaceAll(",", ".");
                }
                else {
                    tiedot[i] = tekstikenttalista.get(i).getText();
                }
            }
        }
        else {
            tiedot = new String[tekstikenttalista.size() + 1];
            for (int i = 0; i < tiedot.length; i++) {
                if (dataluokka.getMaaritykset()[i][2].equals("Double")) {
                    tiedot[i] = tekstikenttalista.get(i).getText().replaceAll(",", ".");
                }
                else {
                    tiedot[i] = tekstikenttalista.get(i).getText();
                }
            }
            tiedot[tiedot.length - 1] = tekstialue.getText();
        }
        return tiedot;
    }

}
