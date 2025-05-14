package mokki.mokki.gui.ponnahdusikkuna;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mokki.mokki.dao.AsiakasDAO;
import mokki.mokki.database.DatabaseManager;
import mokki.mokki.gui.alipaneeli.TaulukonData;
import mokki.mokki.gui.testiluokatTaulukonDatalle.AsiakkaatWrapper;
import mokki.mokki.gui.testiluokatTaulukonDatalle.VarauksetWrapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Luokka toteuttaa ikkunan, jolla näytetään ja hallitaan varauksen tietoja.
 */
public class VarauksenTiedotIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TaulukonData data;
    private ArrayList<TextField> tekstikenttalista;
    private ArrayList<TextArea> tekstialuelista;
    private ArrayList<DatePicker> paivamaaravalitsinlista;
    private ToggleGroup valintanapit;
    private boolean tulos = false;
    private String tyyppi;
    int fonttikoko = 16;

    /**
     * Luokan alustaja, joka luo tyypin perusteella varauksen tietoikkunan. Hyväksyttävät
     * tyyppi-arvot ovat "Varauksen tiedot", "Muuta varauksen tietoja" ja "Lisää varaus".
     * @param data asiakkaan tiedot
     * @param tyyppi paneelin tyyppi
     */
    public VarauksenTiedotIkkuna(TaulukonData data, String tyyppi) {
        this.data = data;
        this.tyyppi = tyyppi;
        paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(20));
        tekstikenttalista = new ArrayList<>();
        tekstialuelista = new ArrayList<>();
        paivamaaravalitsinlista = new ArrayList<>();
        if (tyyppi.equals("Varauksen tiedot")) {
            luoStaattinenSisalto();
        }
        else if (tyyppi.equals("Muuta varauksen tietoja")) {
            luoMuokattavaSisalto();
        }
        else if (tyyppi.equals("Lisää varaus")) {
            luoMuokattavaSisalto();
        }

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(tyyppi);
        this.setScene(kehys);
    }

    /**
     * Metodi luo pääpaneelin komponentit ja asettaa tiedot niin, että
     * niitä ei voi muokata.
     */
    private void luoStaattinenSisalto() {
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

    /**
     * Metodi luo pääpaneelin komponentit ja asettaa tiedot niin, että
     * niitä voi muokata.
     */
    private void luoMuokattavaSisalto() {
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
            if (i == 0) {
                Text otsikko = new Text(maaritykset[i][0] + ":");
                otsikko.setStyle("-fx-font-weight:bold;");
                TextField tekstikentta = new TextField("Järjestelmä täydentää");
                if (tyyppi.equals("Muuta varauksen tietoja")) {
                    tekstikentta.setText(kenttienArvot[i]);
                }
                tekstikentta.setContextMenu(new ContextMenu());
                tekstikentta.setEditable(false);
                tekstikentta.setFocusTraversable(false);
                tekstikentta.setBackground(Background.fill(Color.GAINSBORO));
                ruudukkopaneeli.add(otsikko, sarake, rivi);
                ruudukkopaneeli.add(tekstikentta, sarake + 1, rivi);
                tekstikenttalista.add(tekstikentta);
            }
            else if (i == 2) {
                // Lisätään asiakkaan sähköpostiosoite ja nimi ruudukkopaneeliin
                Text otsikko1 = new Text("Asiakkaan sähköposti\n(tarkista painamalla Enter):");
                otsikko1.setStyle("-fx-font-weight:bold;");
                Text otsikko2 = new Text("Asiakkaan nimi:");
                otsikko2.setStyle("-fx-font-weight:bold;");
                TextField tekstikentta1 = new TextField("");
                if (tyyppi.equals("Muuta varauksen tietoja")) {
                    tekstikentta1.setText(varauksenTiedot.getAsiakkaanSahkoposti());
                }
                tekstikentta1.setContextMenu(new ContextMenu());

                TextField tekstikentta2 = new TextField("");
                if (tyyppi.equals("Muuta varauksen tietoja")) {
                    tekstikentta2.setText(kenttienArvot[i]);
                }
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
                    try {
                        Connection conn = DatabaseManager.getConnection();
                        AsiakasDAO asiakasDAO = new AsiakasDAO(conn);
                        AsiakkaatWrapper asiakas = asiakasDAO.haeAsiakas(syotettySahkopostiosoite);
                        if (asiakas != null) {
                            tekstikentta2.setText(asiakas.getNimi());
                        }
                        else {
                            tekstikentta2.setText("[Asiakasta ei löydy]");
                        }
                    } catch (SQLException ex) {
                        tekstikentta2.setText("[Asiakasta ei löydy]");
                        ex.printStackTrace();
                    }
                });
                tekstikenttalista.add(tekstikentta1);
                tekstikenttalista.add(tekstikentta2);
            }

            else if (i == 3) {
                Text otsikko = new Text(maaritykset[i][0] + ":");
                otsikko.setStyle("-fx-font-weight:bold;");
                DatePicker paivamaaravalitsin = new DatePicker(LocalDate.now());
                if (tyyppi.equals("Muuta varauksen tietoja")) {
                    paivamaaravalitsin.setValue(varauksenTiedot.getAlkaa());
                }
                ruudukkopaneeli.add(otsikko, sarake, rivi);
                ruudukkopaneeli.add(paivamaaravalitsin, sarake + 1, rivi);
                paivamaaravalitsinlista.add(paivamaaravalitsin);
            }

            else if (i == 4) {
                Text otsikko = new Text(maaritykset[i][0] + ":");
                otsikko.setStyle("-fx-font-weight:bold;");
                DatePicker paivamaaravalitsin = new DatePicker(LocalDate.now());
                if (tyyppi.equals("Muuta varauksen tietoja")) {
                    paivamaaravalitsin.setValue(varauksenTiedot.getPaattyy());
                }
                ruudukkopaneeli.add(otsikko, sarake, rivi);
                ruudukkopaneeli.add(paivamaaravalitsin, sarake + 1, rivi);
                paivamaaravalitsinlista.add(paivamaaravalitsin);
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
                TextField tekstikentta = new TextField("");
                if (tyyppi.equals("Muuta varauksen tietoja")) {
                    tekstikentta.setText(kenttienArvot[i]);
                }
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

    /**
     * Metodi luo tekstialuepaneelin, johon voi syöttää huomioitavaa-kentän sisällön.
     * @return tekstialuepaneeli
     */
    private VBox luotekstialuepaneeli() {
        String[] kenttienArvot = data.palautaKenttienArvot();
        String[][] maaritykset = data.getMaaritykset();
        VBox tekstialuepaneeli = new VBox(10);
        tekstialuepaneeli.setPadding(new Insets(30,0,0,0));

        Text otsikko = new Text(maaritykset[maaritykset.length - 1][0] + ":");
        otsikko.setStyle("-fx-font-weight:bold;");
        TextArea tekstialue = new TextArea("");
        if (tyyppi.equals("Muuta varauksen tietoja") || tyyppi.equals("Varauksen tiedot")) {
            tekstialue.setText(kenttienArvot[kenttienArvot.length - 1]);
        }

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
        else if (tyyppi.equals("Muuta varauksen tietoja") || tyyppi.equals("Lisää varaus")) {
            Button hyvaksyPainike = new Button("Lisää varaus");
            if (tyyppi.equals("Muuta varauksen tietoja")) {
                hyvaksyPainike.setText("Muuta tiedot");
            }
            Button peruutaPainike = new Button("Peruuta");
            hyvaksyPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);
            painikepaneeli.getChildren().addAll(hyvaksyPainike, peruutaPainike);

            hyvaksyPainike.setOnAction(e -> {
                // TODO: tarkista, että tietojen tarkistukselle löytyy toimivat metodit VarauksetWrapper-luokassa

                VarauksetWrapper data = (VarauksetWrapper)this.data;

                // Tarkistetaan, että kohde löytyy tietokannasta.
                try {
                    if (!data.onkoKohdeTietokannassa(Integer.valueOf(tekstikenttalista.get(1).getText().strip()))) {
                        String otsikko = "Kohdevirhe";
                        String virheteksti = "Kohdetta ei löydy tietokannasta tunnuksella "
                                + tekstikenttalista.get(1).getText().strip() + ". Lisää kohde ennen varauksen lisäämistä.";
                        Virheikkuna virheikkuna = new Virheikkuna(otsikko, virheteksti);
                        virheikkuna.show();
                    }
                    // Tarkistetaan, että asiakkaan sähköpostiosoite löytyy tietokannasta.
                    else {
                        try {
                            if(!data.onkoAsiakasTietokannassa(tekstikenttalista.get(2).getText().strip())) {
                                String otsikko = "Sähköpostiosoitevirhe";
                                String virheteksti = "Asiakasta ei löydy tietokannasta sähköpostiosoitteella "
                                        + tekstikenttalista.get(2).getText().strip() + ". Lisää asiakas ennen varauksen lisäämistä.";
                                Virheikkuna virheikkuna = new Virheikkuna(otsikko, virheteksti);
                                virheikkuna.show();
                            }
                            // Tarkistetaan, että päättymispäivä ei ole ennen alkamispäivää.
                            else if (paivamaaravalitsinlista.get(0).getValue().isAfter(
                                    paivamaaravalitsinlista.get(1).getValue())) {
                                String otsikko = "Päivämäärävirhe";
                                String virheteksti = "Varauksen päättymispäivä ei voi olla ennen varauksen alkamispäivää. " +
                                        "Tarkista valitut päivämäärät.";
                                Virheikkuna virheikkuna = new Virheikkuna(otsikko, virheteksti);
                                virheikkuna.show();
                            }
                            // Kaikki arvot ovat hyväksyttäviä, joten ikkuna voidaan sulkea.
                            else {
                                tulos = true;

                                String syotettySahkopostiosoite = tekstikenttalista.get(2).getText().strip();
                                try {
                                    Connection conn = DatabaseManager.getConnection();
                                    AsiakasDAO asiakasDAO = new AsiakasDAO(conn);
                                    AsiakkaatWrapper asiakas = asiakasDAO.haeAsiakas(syotettySahkopostiosoite);
                                    if (asiakas != null) {
                                        tekstikenttalista.get(3).setText(asiakas.getNimi());
                                    }
                                    else {
                                        tekstikenttalista.get(3).setText("");
                                    }
                                } catch (SQLException ex) {
                                    tekstikenttalista.get(3).setText("");
                                    ex.printStackTrace();
                                }

                                // Päivitetään samalla asiakkaan nimi ja sähköpostiosoite
                                data.setAsiakkaanSahkoposti(tekstikenttalista.get(2).getText().strip());
                                data.setAsiakkaanNimi(tekstikenttalista.get(3).getText().strip());
                                this.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
     * Metodi luo valintanappipaneelin, jolla  valitaan varauksen tila.
     * @return valintanappipaneeli
     */
    private VBox luoValintanappipaneeli() {
        VBox valintanappipaneeli = new VBox(10);
        valintanapit = new ToggleGroup();
        RadioButton valintanappi1 = new RadioButton("Aktiivinen");
        RadioButton valintanappi2 = new RadioButton("Päättynyt");
        RadioButton valintanappi3 = new RadioButton("Peruttu");
        valintanappi1.setToggleGroup(valintanapit);
        valintanappi2.setToggleGroup(valintanapit);
        valintanappi3.setToggleGroup(valintanapit);

        valintanappi1.setSelected(true);

        if (tyyppi.equals("Muuta varauksen tietoja")) {
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

        }

        valintanappipaneeli.getChildren().addAll(valintanappi1, valintanappi2, valintanappi3);

        return valintanappipaneeli;
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
                    tekstikenttalista.get(0).getText().strip(), // tunnus
                    tekstikenttalista.get(1).getText().strip(), // kohteen tunnus
                    tekstialuelista.get(0).getText().strip(), // asiakas
                    tekstikenttalista.get(2).getText().strip(), // alkaa
                    tekstikenttalista.get(3).getText().strip(), // päättyy
                    tekstikenttalista.get(4).getText().strip(), // tila
                    tekstialuelista.get(1).getText().strip() // huomioitavaa
            };
        }
        else if (tyyppi.equals("Muuta varauksen tietoja")) {
            RadioButton valittuNappi = (RadioButton)valintanapit.getSelectedToggle();
            tiedot = new String[] {
                    tekstikenttalista.get(0).getText().strip(), // tunnus
                    tekstikenttalista.get(1).getText().strip(), // kohteen tunnus
                    tekstikenttalista.get(3).getText().strip() +
                            " (" + tekstikenttalista.get(2).getText().strip() + ")", // asiakas
                    paivamaaravalitsinlista.get(0).getValue().toString(), // alkaa
                    paivamaaravalitsinlista.get(1).getValue().toString(), // päättyy
                    valittuNappi.getText().strip(), // tila
                    tekstialuelista.getFirst().getText().strip() // huomioitavaa
            };
        }
        else if (tyyppi.equals("Lisää varaus")) {
            RadioButton valittuNappi = (RadioButton)valintanapit.getSelectedToggle();
            tiedot = new String[] {
                    "", // tunnus
                    tekstikenttalista.get(1).getText().strip(), // kohteen tunnus
                    tekstikenttalista.get(3).getText().strip() +
                            " (" + tekstikenttalista.get(2).getText().strip() + ")", // asiakas
                    paivamaaravalitsinlista.get(0).getValue().toString(), // alkaa
                    paivamaaravalitsinlista.get(1).getValue().toString(), // päättyy
                    valittuNappi.getText().strip(), // tila
                    tekstialuelista.getFirst().getText().strip() // huomioitavaa
            };
        }

        return tiedot;
    }

}
