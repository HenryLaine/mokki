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

/**
 * Luokka toteuttaa ikkunan, jolla näytetään ja hallitaan varauksen tietoja.
 */
public class VarauksenTiedotIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TaulukonData data;
    private ArrayList<TextField> tekstikenttalista;
    private ArrayList<TextArea> tekstialuelista;
    private ToggleGroup valintanapit;
    private boolean tulos = false;
    private String tyyppi;
    int fonttikoko = 16;

    public VarauksenTiedotIkkuna(TaulukonData data, String tyyppi) {
        this.data = data;
        this.tyyppi = tyyppi;
        paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(20));
        tekstikenttalista = new ArrayList<>();
        tekstialuelista = new ArrayList<>();
        if (tyyppi.equals("Varauksen tiedot")) {
            varauksenTiedot();
        }

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(tyyppi);
        this.setScene(kehys);
    }


    private void varauksenTiedot() {
        VBox ylapaneeli = new VBox();

        // Luodaan ruudukkopaneeli
        GridPane ruudukkopaneeli = new GridPane(40, 40);
        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();
        int sarake = 0;
        int rivi = 0;
        for (int i = 0; i < maaritykset.length - 1; i++) {
            if (i == 3) {
                sarake = 2;
                rivi = 0;
            }
            Text otsikko = new Text(maaritykset[i][0] + ":");
            otsikko.setStyle("-fx-font-weight:bold;");
            if (i == 2) {
                TextArea tekstialue = new TextArea(kenttienArvot[i]);
                tekstialue.setMaxWidth(200);
                tekstialue.setMaxHeight(fonttikoko * 4);
                tekstialue.setWrapText(true);
                tekstialue.setContextMenu(new ContextMenu());
                tekstialue.setEditable(false);
                tekstialue.setFocusTraversable(false);
                tekstialue.setStyle("-fx-control-inner-background:#DCDCDC;-fx-background-color:#DCDCDC;");
                ruudukkopaneeli.add(otsikko, sarake, rivi);
                ruudukkopaneeli.add(tekstialue, sarake + 1, rivi);
                tekstialuelista.add(tekstialue);
            }
            else {
                TextField tekstikentta = new TextField(kenttienArvot[i]);
                tekstikentta.setContextMenu(new ContextMenu());
                tekstikentta.setEditable(false);
                tekstikentta.setFocusTraversable(false);
                tekstikentta.setBackground(Background.fill(Color.GAINSBORO));
                ruudukkopaneeli.add(otsikko, sarake, rivi);
                ruudukkopaneeli.add(tekstikentta, sarake + 1, rivi);
                tekstikenttalista.add(tekstikentta);
            }
            rivi++;
        }
        // Luodaan tekstialuepaneeli
        VBox tekstialuepaneeli = luotekstialuepaneeli();
        // Asetetaan ruudukkopaneeli ja tekstialuepaneeli yläpaneeliin
        ylapaneeli.getChildren().addAll(ruudukkopaneeli, tekstialuepaneeli);

        VBox alapaneeli = new VBox();
        // Luodaan painikepaneeli ja asetetaan se alapaneeliin
        alapaneeli.getChildren().add(luoPainikepaneeli());

        paapaneeli.setTop(ylapaneeli);
        paapaneeli.setBottom(alapaneeli);
    }

    private VBox luotekstialuepaneeli() {
        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();
        VBox tekstialuepaneeli = new VBox(10);
        tekstialuepaneeli.setPadding(new Insets(30,0,0,0));

        Text otsikko = new Text(maaritykset[maaritykset.length - 1][0] + ":");
        otsikko.setStyle("-fx-font-weight:bold;");
        TextArea tekstialue = new TextArea(kenttienArvot[kenttienArvot.length - 1]);
        tekstialue.setWrapText(true);
        tekstialue.setContextMenu(new ContextMenu());

        if (tyyppi.equals("Varauksen tiedot")) {
            tekstialue.setEditable(false);
            tekstialue.setFocusTraversable(false);
            tekstialue.setStyle("-fx-control-inner-background:#DCDCDC;-fx-background-color:#DCDCDC;");
        }
        tekstialuelista.add(tekstialue);
        tekstialuepaneeli.getChildren().addAll(otsikko, tekstialue);

        return tekstialuepaneeli;
    }

    /**
     * Metodi luo painikepaneelin, jonka avulla voi joko lisätä tietoja, muuttaa tietoja tai sulkea
     * ikkunan.
     * @return painikepaneeli
     */
    private HBox luoPainikepaneeli() {
        HBox painikepaneeli = new HBox(20);
        painikepaneeli.setPadding(new Insets(20, 0, 0, 0));
        if (tyyppi.equals("Varauksen tiedot")) {
            Button suljePainike = new Button("Sulje");
            suljePainike.setMinWidth(100);
            painikepaneeli.getChildren().add(suljePainike);
            suljePainike.setOnAction(e -> {
                this.close();
            });
        }
        else if (tyyppi.equals("Muuta asiakkaan tietoja") || tyyppi.equals("Lisää asiakas")) {
            Button hyvaksyPainike = new Button("Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");
            if (tyyppi.equals("Lisää asiakas")) {
                hyvaksyPainike.setText("Lisää asiakas");
            }
            hyvaksyPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);
            painikepaneeli.getChildren().addAll(hyvaksyPainike, peruutaPainike);

            hyvaksyPainike.setOnAction(e -> {
                int tunnisteenIndeksi = data.palautaTunnisteenIndeksi();
                boolean arvotHyvaksyttavia = data.ovatkoArvotHyvaksyttavia(palautaKenttienTiedot());
                boolean tunnisteUniikki = data.onkoTunnisteUniikki(
                        tekstikenttalista.get(tunnisteenIndeksi).getText());
                if (arvotHyvaksyttavia && tunnisteUniikki) {
                    tulos = true;
                    this.close();
                }
                else if (!arvotHyvaksyttavia) {
                    Virheikkuna virheikkuna = new Virheikkuna("Tietokenttävirhe",
                            muotoileTietokenttavirheteksti());
                    virheikkuna.show();
                }
                else {
                    String virheteksti = data.getMaaritykset()[tunnisteenIndeksi][0] + " " +
                            tekstikenttalista.get(tunnisteenIndeksi).getText() +
                            " löytyy jo tietokannasta. Valitse jokin toinen arvo.";
                    Virheikkuna virheikkuna = new Virheikkuna("Tunnistevirhe",
                            virheteksti);
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


    /**
     * Metodi asettaa valintanappien toiminnallisuuden.
     * @param valintanappi1 valintanappi 1
     * @param valintanappi2 valintanappi 2
     */
    private void asetaValintanappienToiminnallisuus(RadioButton valintanappi1, RadioButton valintanappi2) {
        valintanappi1.setOnAction(e -> {
            TextField tekstikentta = tekstikenttalista.getLast();
            tekstikentta.setEditable(false);
            tekstikentta.setFocusTraversable(false);
            tekstikentta.setStyle("");
            tekstikentta.setBackground(Background.fill(Color.GAINSBORO));
        });
        valintanappi2.setOnAction(e -> {
            TextField tekstikentta = tekstikenttalista.getLast();
            tekstikentta.setEditable(true);
            tekstikentta.setFocusTraversable(true);
            tekstikentta.setStyle("-fx-control-inner-background:#" +
                    Color.WHITE.toString().substring(2));
        });
    }

    /**
     * Metodi muotoilee virhetekstin, joka näytetään, kun käyttäjä syöttää virheellisiä arvoja
     * tietokenttiin.
     * @return tietokenttävirheteksti
     */
    private String muotoileTietokenttavirheteksti() {
        StringBuilder virheteksti = new StringBuilder("""
                Joidenkin kenttien arvot ovat virheelliset. Tarkista, \
                että pakolliset kentät on täytetty ja kaikkien kenttien arvot \
                noudattavat muotoiluvaatimuksia.
                
                Seuraavien kenttien arvot ovat virheelliset:\s""");
        String[][] maaritykset = data.getMaaritykset();
        boolean[] virhearvot = data.mitkaArvotHyvaksyttavia(palautaKenttienTiedot());
        for (int i = 0; i < virhearvot.length; i++) {
            if (!virhearvot[i]) {
                virheteksti.append(maaritykset[i][0]);
                if (i != virhearvot.length - 1) {
                    virheteksti.append(", ");
                }
            }
        }

        return virheteksti.toString();
    }

    /**
     * Metodi kutsuu Stagen showAndWait-metodia. Ikkunan sulkeuduttua metodi palauttaa
     * tuloksen.
     * @return true, jos tekstikentissä on arvoja, jotka ovat hyväksyttäviä järjestelmän ja
     * käyttäjän puolesta; false muussa tapauksessa
     */
    public boolean naytaJaOdotaJaPalautaTulos() {
        this.showAndWait();
        return tulos;
    }

    /**
     * Metodi asettaa ikkunan fonttikoon.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        this.fonttikoko = fonttikoko;
        paapaneeli.setStyle("-fx-font-size:" + fonttikoko + "px;");
        if(tyyppi.equals("Varauksen tiedot")) {
            tekstialuelista.getFirst().setMaxHeight(fonttikoko*4);
        }
    }

    /**
     * Metodi palauttaa ikkunan tekstikentissä olevat tiedot.
     * @return tekstikenttien tiedot
     */
    public String[] palautaKenttienTiedot() {
        String[] tiedot = new String[] {""};
        if (tyyppi.equals("Asiakkaan tiedot")) {
            tiedot = new String[] {tekstikenttalista.get(0).getText(), tekstikenttalista.get(1).getText(),
                    tekstikenttalista.get(2).getText(), tekstikenttalista.get(3).getText(),
                    tekstikenttalista.get(4).getText()};
        }
        else if (tyyppi.equals("Muuta asiakkaan tietoja") || tyyppi.equals("Lisää asiakas")) {
            String ytunnus;
            if (valintanapit.getToggles().getFirst().isSelected()) {
                ytunnus = tekstikenttalista.get(3).getText();
            }
            else {
                ytunnus = "";
            }
            RadioButton valittuNappi = (RadioButton)valintanapit.getSelectedToggle();
            String asiakastyyppi = valittuNappi.getText().toLowerCase();
            tiedot = new String[] {tekstikenttalista.get(0).getText(), tekstikenttalista.get(1).getText(),
                    tekstikenttalista.get(2).getText(), asiakastyyppi, ytunnus};
        }

        return tiedot;
    }

}
