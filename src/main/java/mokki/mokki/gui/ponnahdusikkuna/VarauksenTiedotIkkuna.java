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
import mokki.mokki.gui.testiluokatTaulukonDatalle.VarauksetWrapper;

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
        else if(tyyppi.equals("Muuta varauksen tietoja")) {
            muutaVarauksenTietoja();
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

    private void muutaVarauksenTietoja() {
        VBox ylapaneeli = new VBox();

        // Luodaan ruudukkopaneeli
        GridPane ruudukkopaneeli = new GridPane(40, 40);
        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();
        VarauksetWrapper varauksenTiedot = (VarauksetWrapper)data;
        int sarake = 0;
        int rivi = 0;
        for (int i = 0; i < maaritykset.length - 1; i++) {
            if (i == 3) {
                sarake = 2;
                rivi = 0;
            }
            if (i == 2) {
                // Lisätään asiakkaan sähköpostiosoite ja nimi ruudukkopaneeliin
                Text otsikko1 = new Text("Asiakkaan sähköposti\n(tarkista painamalla Enter):");
                otsikko1.setStyle("-fx-font-weight:bold;");
                Text otsikko2 = new Text("Asiakkaan nimi:");
                otsikko2.setStyle("-fx-font-weight:bold;");
                TextField tekstikentta1 = new TextField(varauksenTiedot.getAsiakkaanSahkoposti());
                tekstikentta1.setContextMenu(new ContextMenu());
                TextField tekstikentta2 = new TextField(varauksenTiedot.getAsiakkaanNimi());
                tekstikentta2.setContextMenu(new ContextMenu());
                tekstikentta2.setEditable(false);
                tekstikentta2.setFocusTraversable(false);
                tekstikentta2.setBackground(Background.fill(Color.GAINSBORO));

                ruudukkopaneeli.add(otsikko1, sarake, rivi);
                ruudukkopaneeli.add(tekstikentta1, sarake + 1, rivi);
                rivi++;
                ruudukkopaneeli.add(otsikko2, sarake, rivi);
                ruudukkopaneeli.add(tekstikentta2, sarake + 1, rivi);

                tekstikentta1.setOnAction(e -> {
                    // TODO: tarkista, löytyykö asiakas tietokannasta sähköpostiosoitteen perusteella,
                    //  ja päivitä asiakkaan nimi -kenttä

                    String syotettySahkopostiosoite = tekstikentta1.getText().strip();
                    if (((VarauksetWrapper) data).onkoSahkopostiTietokannassa(syotettySahkopostiosoite)) {
                        // Etsi asiakkaan nimi ja syötä se tekstikenttään
                        tekstikentta2.setText("Asiakkaan nimi");
                    }
                    else {
                        tekstikentta2.setText("Asiakasta ei löydy");
                    }
                });
                tekstikenttalista.add(tekstikentta1);
                tekstikenttalista.add(tekstikentta2);
            }

            else if (i == 5) {
                Text otsikko = new Text(maaritykset[i][0] + ":");
                otsikko.setStyle("-fx-font-weight:bold;");
                VBox valintanappipaneeli = luoValintanappipaneeli();
                ruudukkopaneeli.add(otsikko, sarake, rivi);
                ruudukkopaneeli.add(valintanappipaneeli, sarake + 1, rivi);
            }
            else {
                Text otsikko = new Text(maaritykset[i][0] + ":");
                otsikko.setStyle("-fx-font-weight:bold;");
                TextField tekstikentta = new TextField(kenttienArvot[i]);
                tekstikentta.setContextMenu(new ContextMenu());
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
        else if (tyyppi.equals("Muuta varauksen tietoja")) {
            Button hyvaksyPainike = new Button("Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");
            hyvaksyPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);
            painikepaneeli.getChildren().addAll(hyvaksyPainike, peruutaPainike);

            hyvaksyPainike.setOnAction(e -> {
                // TODO: tarkista, että tietojen tarkistukselle löytyy toimivat metodit
                //  ja tarkistuken logiikka toimii oikein.

                VarauksetWrapper data = (VarauksetWrapper)this.data;
                boolean[] tulokset = data.mitkaArvotHyvaksyttavia(palautaKenttienTiedot());
                if (!tulokset[data.palautaTunnisteenIndeksi()]) {
                    String otsikko = "Tunnistevirhe";
                    String virheteksti = "";
                    // Tarkistetaan, että varauksen tunnus on hyväksyttävä.
                    if (!data.onkoTunnisteUniikki(tekstikenttalista.getFirst().getText())) {
                        virheteksti = "Tunnukselle " + tekstikenttalista.getFirst().getText() +
                                " on jo luotu varaus. Valtse toinen tunnus.";
                    }
                    else {
                        virheteksti = "Tunnus ei ole muodoltaa hyväksyttävä. Tarkista, että tunnus " +
                                "noudattaa tunnisteiden muotoiluvaatimuksia.";
                    }
                    Virheikkuna virheikkuna = new Virheikkuna(otsikko, virheteksti);
                    virheikkuna.show();
                }
                // Tarkistetaan, että asiakkaan sähköpostiosoite löytyy tietokannasta.
                else if(!data.onkoSahkopostiTietokannassa(tekstikenttalista.get(2).getText())) {
                    String otsikko = "Sähköpostiosoitevirhe";
                    String virheteksti = "Asiakasta ei löydy tietokannasta sähköpostiosoitteella "
                            + tekstikenttalista.get(2).getText() + ". Lisää asiakas ennen varauksen lisäämistä.";
                    Virheikkuna virheikkuna = new Virheikkuna(otsikko, virheteksti);
                    virheikkuna.show();
                }
                // Tarkistetaan, että kaikkien kenttien arvot ovat hyväksyttäviä.
                else if(!data.ovatkoArvotHyvaksyttavia(palautaKenttienTiedot())) {
                    String otsikko = "Tietokenttävirhe";
                    String virheteksti = muotoileTietokenttavirheteksti();
                    Virheikkuna virheikkuna = new Virheikkuna(otsikko, virheteksti);
                    virheikkuna.show();
                }
                // Kaikki arvot ovat hyväksyttäviä, joten ikkuna voidaan sulkea.
                else {
                    tulos = true;
                    this.close();
                }
            });
            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });
        }

        return painikepaneeli;
    }

    private VBox luoValintanappipaneeli() {
        VBox valintanappipaneeli = new VBox(10);
        valintanapit = new ToggleGroup();
        RadioButton valintanappi1 = new RadioButton("Aktiivinen");
        RadioButton valintanappi2 = new RadioButton("Päättynyt");
        RadioButton valintanappi3 = new RadioButton("Peruttu");
        valintanappi1.setToggleGroup(valintanapit);
        valintanappi2.setToggleGroup(valintanapit);
        valintanappi3.setToggleGroup(valintanapit);

        String varauksenTila = data.palautaKenttienArvot()[5];
        if (varauksenTila.equals("Aktiivinen")) {
            valintanappi1.setSelected(true);
        }
        else if (varauksenTila.equals("Päättynyt")) {
            valintanappi2.setSelected(true);
        }
        else if(varauksenTila.equals("Peruttu")) {
            valintanappi3.setSelected(true);
        }

        valintanappipaneeli.getChildren().addAll(valintanappi1, valintanappi2, valintanappi3);

        return valintanappipaneeli;
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
        String[] tiedot = new String[7];
        if (tyyppi.equals("Varauksen tiedot")) {
            tiedot = new String[] {
                    tekstikenttalista.get(0).getText().strip(),
                    tekstikenttalista.get(1).getText().strip(),
                    tekstialuelista.get(0).getText().strip(),
                    tekstikenttalista.get(2).getText().strip(),
                    tekstikenttalista.get(3).getText().strip(),
                    tekstikenttalista.get(4).getText().strip(),
                    tekstialuelista.get(1).getText().strip()};
        }
        else if (tyyppi.equals("Muuta varauksen tietoja")) {
            RadioButton valittuNappi = (RadioButton)valintanapit.getSelectedToggle();
            tiedot = new String[] {
                    tekstikenttalista.get(0).getText().strip(),
                    tekstikenttalista.get(1).getText().strip(),
                    tekstikenttalista.get(3).getText().strip() + " (" + tekstikenttalista.get(2).getText().strip() + ")",
                    tekstikenttalista.get(4).getText().strip(),
                    tekstikenttalista.get(5).getText().strip(),
                    valittuNappi.getText().strip(),
                    tekstialuelista.getFirst().getText().strip()};
        }

        return tiedot;
    }

}
