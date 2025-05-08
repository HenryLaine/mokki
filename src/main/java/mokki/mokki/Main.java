package mokki.mokki;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import mokki.mokki.gui.alipaneeli.Hallintapaneeli;
import mokki.mokki.gui.alipaneeli.RaportitHallintapaneeli;
import mokki.mokki.gui.alipaneeli.Taulukkopaneeli;
import mokki.mokki.gui.Valilehtipaneeli;
import mokki.mokki.gui.alipaneeli.TaulukonData;
import mokki.mokki.gui.paapaneeli.*;
import mokki.mokki.gui.ponnahdusikkuna.*;
import mokki.mokki.gui.testiluokatTaulukonDatalle.*;
import mokki.mokki.dao.*;
import mokki.mokki.database.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    int fonttikoko = 16;
    KohteetPaneeli kohteetPaneeli;
    VarauksetPaneeli varauksetPaneeli;
    AsiakkaatPaneeli asiakkaatPaneeli;
    LaskutPaneeli laskutPaneeli;
    RaportitPaneeli raportitPaneeli;


    private void alustaKohteetPaneeli() {
        // Dummy-dataa
        List<TaulukonData> kohteet = List.of(
                new KohteetWrapper("JOE001", "Joensuu", 1,
                        46, 250, "Kohteessa on poreallas."),
                new KohteetWrapper("KON005", "Kontiolahti", 5,
                        25, 85, ""),
                new KohteetWrapper("LIP003", "Liperi", 2, 15,
                        130, "Kohde on remontoitavana 16.3.2025 asti.")
        );
        // Käyttöliittymän taulukon sisältö
        ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(kohteet);
        kohteetPaneeli = new KohteetPaneeli(fonttikoko, taulukonSisalto);

        Taulukkopaneeli<TaulukonData> taulukkopaneeli = kohteetPaneeli.getTaulukkopaneeli();
        Hallintapaneeli hallintapaneeli = kohteetPaneeli.getHallintapaneeli();

        hallintapaneeli.getLisaaPainike().setOnAction(e -> {
            // Uusi kohde luodaan.
            TaulukonData uusiKohde = new KohteetWrapper();
            KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(uusiKohde, true,
                    "Lisää kohde", new String[] {"Lisää kohde", "Peruuta"});
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
            if (tulos) {
                uusiKohde.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                // TODO: Kohde lisätään tietokantaan.

                // Kohde lisätään käyttöliittymän taulukkoon.
                taulukonSisalto.add(uusiKohde);
            }

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {
            // Kohteita rajataan.
            // TODO: Rajausten hallintapaneeli avataan.


            // TODO: Ylimääräiset kohteet poistetaan taulukon sisällöstä.

            // Rajaukset-teksti päivitetään vastaamaan rajauksia.
            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            // Rajaukset poistetaan
            // TODO: Taulukon sisältöön lisätään jollain logiikalla kaikki näytettävät kohteet.
            //taulukonSisalto.clear();
            //taulukonSisalto.addAll();

            // Rajaukset-teksti alustetaan.
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");
        });

        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Kohteen tiedot näytetään.
            KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(taulukkopaneeli.palautaRivinTiedot(),
                    false, true, "Kohteen tiedot", new String[] {"", "Sulje"});
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();
        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Kohteen tietoja muutetaan.
            TaulukonData kohteenTiedot = taulukkopaneeli.palautaRivinTiedot();
            KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(kohteenTiedot, true, true,
                    "Muuta kohteen tietoja", new String[] {"Muuta tiedot", "Peruuta"});
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
            if (tulos) {
                // TODO: Kohteen tietoja muutetaan tietokannassa.

                // Kohteen tiedot muutetaan käyttöliittymän taulukossa.
                kohteenTiedot.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
            }
        });
        kontekstivalikonKohdat.get(2).setOnAction(e -> {
            // Kohteen tiedot poistetaan.
            Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                    "Haluatko varmasti poistaa kohteen " +
                    taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
            Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

            if(tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                // TODO: Kohde poistetaan tietokannasta.



                // Kohde poistetaan käyttöliittymän taulukosta.
                taulukonSisalto.remove(taulukkopaneeli.palautaRivinTiedot());
            }
        });
    }

    private void alustaVarauksetPaneeli() {
        // Dummy-dataa
        List<TaulukonData> varaukset = List.of(
                new VarauksetWrapper("A003", "JOE001",
                        "Matti Meikäläinen", "matti@gmail.com", "05.04.2025",
                        "08.04.2025", "Päättynyt", "Lisäpalvelu: ylimääräinen sänky"),
                new VarauksetWrapper("J046", "KUO004", "Jukka Jokunen" ,
                        "jukka@gmail.com", "08.06.2025", "14.07.2025",
                        "Aktiivinen", "")
        );
        ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(varaukset);
        varauksetPaneeli = new VarauksetPaneeli(fonttikoko, taulukonSisalto);

        Hallintapaneeli hallintapaneeli = varauksetPaneeli.getHallintapaneeli();
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {
            // Uusi varaus lisätään
            // TODO

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {
            // Varauksia rajataan
            // TODO


            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            // Rajaukset poistetaan
            // TODO


            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");
        });

        Taulukkopaneeli<TaulukonData> taulukkopaneeli = varauksetPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Varauksen tiedot näytetään
            VarauksenTiedotIkkuna tiedotIkkuna = new VarauksenTiedotIkkuna(taulukkopaneeli.palautaRivinTiedot(),
                    "Varauksen tiedot");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();
        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Kohteen tiedot näytetään
            // TODO: selvitä kohteen tunnus, hae tietokannasta kohteen tiedot ja luo KohteenTiedotIkkuna
            String kohteenTunnus = taulukkopaneeli.palautaRivinTiedot().palautaKenttienArvot()[1];

            /* Luo kohteen tiedot sisältävä TaulukonData-olio ja syötä se KohteenTiedotIkkuna-olioon
            TaulukonData kohteenTiedot = new KohteetWrapper();
            KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(kohteenTiedot,
                    false, true, "Kohteen tiedot", new String[] {"", "Sulje"});
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();
             */
        });
        kontekstivalikonKohdat.get(2).setOnAction(e -> {
            // Asiakkaan tiedot näytetään
            // TODO: selvitä asiakkaan sähköpostiosoite, hae tietokannasta asiakkaan tiedot
            //  ja luo AsiakkaanTiedotIkkuna

            VarauksetWrapper varauksenTiedot = (VarauksetWrapper)taulukkopaneeli.palautaRivinTiedot();
            String asiakkaanSahkoposti = varauksenTiedot.getAsiakkaanSahkoposti();

            /* Luo asiakkaan tiedot sisältävä TaulukonData-olio ja syötä se AsiakkaanTiedotIkkuna-olioon
            TaulukonData asiakkaanTiedot = new AsiakkaatWrapper();
            AsiakkaanTiedotIkkuna tiedotIkkuna = new AsiakkaanTiedotIkkuna(asiakkaanTiedot, "Asiakkaan tiedot");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();
             */
        });
        kontekstivalikonKohdat.get(3).setOnAction(e -> {
            // Varauksen tietoja muutetaan
            TaulukonData varauksenTiedot = taulukkopaneeli.palautaRivinTiedot();
            VarauksenTiedotIkkuna tiedotIkkuna = new VarauksenTiedotIkkuna(varauksenTiedot,
                    "Muuta varauksen tietoja");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
            if (tulos) {
                String[] kenttienTiedot = tiedotIkkuna.palautaKenttienTiedot();
                // TODO: Varauksen tietoja muutetaan tietokannassa.

                // Kohteen tiedot muutetaan käyttöliittymän taulukossa.
                varauksenTiedot.paivitaKenttienArvot(kenttienTiedot);
            }

        });
        kontekstivalikonKohdat.get(4).setOnAction(e -> {
            // Varaus poistetaan
            Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                    "Haluatko varmasti poistaa varauksen " +
                            taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
            Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

            if(tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                // TODO: Varaus poistetaan tietokannasta.



                // Varaus poistetaan käyttöliittymän taulukosta.
                taulukonSisalto.remove(taulukkopaneeli.palautaRivinTiedot());
            }
        });
    }

    private void alustaAsiakkaatPaneeli() {
        try {
            Connection conn = DatabaseManager.getConnection();
            AsiakasDAO asiakasDAO = new AsiakasDAO(conn);

            List<AsiakkaatWrapper> asiakkaat = asiakasDAO.haeAsiakkaat();
            ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(asiakkaat);
            asiakkaatPaneeli = new AsiakkaatPaneeli(fonttikoko, taulukonSisalto);

            Hallintapaneeli hallintapaneeli = asiakkaatPaneeli.getHallintapaneeli();

            // Lisää asiakas -painikkeen toiminto
            hallintapaneeli.getLisaaPainike().setOnAction(e -> {
                TaulukonData uusiAsiakas = new AsiakkaatWrapper("", "", "", "", "", "");
                AsiakkaanTiedotIkkuna tiedotIkkuna = new AsiakkaanTiedotIkkuna(uusiAsiakas, "Lisää asiakas");
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
                if (tulos) {
                    uusiAsiakas.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                    try {
                        asiakasDAO.lisaaAsiakas((AsiakkaatWrapper) uusiAsiakas);
                        taulukonSisalto.add(uusiAsiakas);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Voit näyttää virheilmoituksen käyttöliittymässä
                    }
                }
            });

            hallintapaneeli.getRajaaPainike().setOnAction(e -> {
                RajausIkkuna rajausIkkuna = new RajausIkkuna();
                rajausIkkuna.setTitle("Rajaa asiakkaita hakusanalla");

                String hakusana = rajausIkkuna.naytaJaOdotaJaPalautaTulos();

                // Tarkistetaan, onko hakusana null tai tyhjä
                if (hakusana != null && !hakusana.isBlank()) {
                    try {
                        List<AsiakkaatWrapper> rajatut = asiakasDAO.rajaaAsiakkaat(hakusana);

                        taulukonSisalto.clear();
                        taulukonSisalto.addAll(rajatut);
                        hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET: " + hakusana);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        new Virheikkuna("Tietokantavirhe", "Asiakashaku epäonnistui.").show();
                    }
                }
            });

            hallintapaneeli.getPoistaRajauksetPainike().setOnAction(e -> {
                taulukonSisalto.addAll(asiakkaat);
                hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");
            });

            Taulukkopaneeli<TaulukonData> taulukkopaneeli = asiakkaatPaneeli.getTaulukkopaneeli();
            ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

            // Näytä asiakkaan tiedot
            kontekstivalikonKohdat.get(0).setOnAction(e -> {
                AsiakkaanTiedotIkkuna tiedotIkkuna = new AsiakkaanTiedotIkkuna(
                        taulukkopaneeli.palautaRivinTiedot(), "Asiakkaan tiedot");
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                tiedotIkkuna.showAndWait();
            });
            // Muokkaa asiakkaan tietoja
            kontekstivalikonKohdat.get(1).setOnAction(e -> {
                TaulukonData asiakkaanTiedot = taulukkopaneeli.palautaRivinTiedot();
                AsiakkaanTiedotIkkuna tiedotIkkuna = new AsiakkaanTiedotIkkuna(
                        asiakkaanTiedot, "Muuta asiakkaan tietoja");
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
                if (tulos) {
                    // Päivitä asiakastiedot
                    asiakkaanTiedot.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());

                    try {
                        // Päivitetään asiakas tietokannassa
                        AsiakkaatWrapper uusiData = (AsiakkaatWrapper) asiakkaanTiedot;
                        asiakasDAO.muokkaaAsiakasta(uusiData);

                        // Päivitetään taulukon sisältö
                        // Poista vanha asiakas taulukosta ja lisää päivitetty
                        int index = taulukonSisalto.indexOf(asiakkaanTiedot);
                        if (index >= 0) {
                            taulukonSisalto.set(index, uusiData); // Päivitetään vanhan rivin tiedot
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Poista asiakas
            kontekstivalikonKohdat.get(2).setOnAction(e -> {
                TaulukonData valittu = taulukkopaneeli.palautaRivinTiedot();
                Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                        "Haluatko varmasti poistaa asiakkaan " + valittu.palautaKuvausteksti() + "?");
                Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();
                if (tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                    try {
                        String sahkoposti = valittu.palautaTunniste();
                        asiakasDAO.poistaAsiakas(sahkoposti);
                        taulukonSisalto.remove(valittu);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } catch (SQLException e) {
            System.err.println("Tietokantayhteyden muodostaminen epäonnistui: " + e.getMessage());
            e.printStackTrace();
            // TODO: Näytä virheilmoitus käyttöliittymässä, jos tarvitaan
        }
    }
    private void alustaLaskutPaneeli() {
        // Dummy-dataa
        ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(
                new LaskutWrapper(3950359, "Vuokraus: JOE001; 15.03.2025-16.03.2025",
                        "Jaska Jokunen (jaska@gmail.com)", 90405964, 150.35,
                        "Avoin")
        );
        laskutPaneeli = new LaskutPaneeli(fonttikoko, taulukonSisalto);

        // TODO: Aseta hallintapaneelin painikkeiden toiminnallisuus.
        Hallintapaneeli hallintapaneeli = laskutPaneeli.getHallintapaneeli();
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {
            TaulukonData uusiLasku = new LaskutWrapper(0, "", "", 0, 0.0, "Avoin");
            LaskunTiedotIkkuna tiedotIkkuna = new LaskunTiedotIkkuna(uusiLasku, "Lisää lasku");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);

            boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
            if (tulos) {
                uusiLasku.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                taulukonSisalto.add(uusiLasku);
            }
        });

        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });

        // TODO: Aseta taulukkopaneelin kontekstivalikon toiminnallisuus.
        Taulukkopaneeli<TaulukonData> taulukkopaneeli = laskutPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Laskun tiedot näytetään
            LaskunTiedotIkkuna tiedotIkkuna = new LaskunTiedotIkkuna(
                    taulukkopaneeli.palautaRivinTiedot(), "Laskun tiedot");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();

        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Laskun tietoja muutetaan
            TaulukonData laskunTiedot = taulukkopaneeli.palautaRivinTiedot();
            LaskunTiedotIkkuna tiedotIkkuna = new LaskunTiedotIkkuna(laskunTiedot, "Muuta laskun tietoja");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
            if (tulos) {
                laskunTiedot.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                // TODO: Päivitä laskun tiedot tietokantaan.
            }
        });

        kontekstivalikonKohdat.get(2).setOnAction(e -> {
            // Lasku merkitään maksetuksi
            TaulukonData laskunTiedot = taulukkopaneeli.palautaRivinTiedot();
            if (laskunTiedot instanceof LaskutWrapper) {
                LaskutWrapper lasku = (LaskutWrapper) laskunTiedot;
                lasku.setTila("Maksettu");

                // Päivitä ObservableList
                int index = taulukonSisalto.indexOf(lasku);
                if (index >= 0) {
                    taulukonSisalto.set(index, lasku); // Tämä pakottaa TableView:n päivityksen
                }
            }
            // TODO: Päivitä tietokantaan, että lasku on maksettu.
        });

        kontekstivalikonKohdat.get(3).setOnAction(e -> {
            // Lasku poistetaan
            Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                    "Haluatko varmasti poistaa laskun " +
                            taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
            Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

            if(tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                // TODO: lasku poistetaan tietokannasta

                // Lasku poistetaan taulukon sisällöstä
                taulukonSisalto.remove(taulukkopaneeli.palautaRivinTiedot());
            }
        });
    }

    private void alustaRaportitPaneeli() {
        // Dummy-dataa
        ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(
                new RaportitWrapper("JOE001", 45, 21,
                        20, 46.4, 100, 10000)
        );
        raportitPaneeli = new RaportitPaneeli(fonttikoko, taulukonSisalto);

        // TODO: Aseta elementtien toiminnallisuus.
        RaportitHallintapaneeli hallintapaneeli = raportitPaneeli.getHallintapaneeli();
        /*
        hallintapaneeli.getAlkuvuosivalikko().*(e -> {

        });
        hallintapaneeli.getAlkukuukausivalikko().*(e -> {

        });
        hallintapaneeli.getLoppuvuosivalikko().*(e -> {

        });
        hallintapaneeli.getLoppukuukausivalikko().*(e -> {

        });
        hallintapaneeli.getRajauksetKentta().*(e -> {

        });

         */

        // TODO: Aseta taulukkopaneelin kontekstivalikon toiminnallisuus.
        Taulukkopaneeli<TaulukonData> taulukkopaneeli = raportitPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Kohteen tiedot näytetään
            // TODO: selvitä kohteen tunnus, hae tietokannasta kohteen tiedot ja luo KohteenTiedotIkkuna
            String kohteenTunnus = taulukkopaneeli.palautaRivinTiedot().palautaTunniste();

            /* Luo kohteen tiedot sisältävä TaulukonData-olio ja syötä se KohteenTiedotIkkuna-olioon
            TaulukonData kohteenTiedot = new KohteetWrapper();
            KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(kohteenTiedot,
                    false, true, "Kohteen tiedot", new String[] {"", "Sulje"});
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();
             */
        });

        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Kohteen varaukset näytetään
            // TODO: Siiry Varaukset-välilehdelle ja aseta rajaukseksi kohteen tunnus.

        });
    }

    public static void main(String[] args) {
        DatabaseCreator.ensureDatabaseExists();
        DatabaseInitializer.initializeDatabase();
        Application.launch(args);


    }

    @Override
    public void start(Stage primaryStage) {

        Valilehtipaneeli valilehtipaneeli = new Valilehtipaneeli(
                new String[] {"Kohteet", "Varaukset", "Asiakkaat", "Laskut", "Raportit"});
        valilehtipaneeli.asetaFonttikoko((int)Math.round(fonttikoko * 1.3));

        ArrayList<Tab> valilehdet = valilehtipaneeli.getValilehtilista();
        // Alustetaan ensimmäinen välilehti.
        alustaKohteetPaneeli();
        valilehdet.getFirst().setContent(kohteetPaneeli);
        // Alustetaan toinen välilehti.
        alustaVarauksetPaneeli();
        valilehdet.get(1).setContent(varauksetPaneeli);
        // Alustetaan kolmas välilehti.
        alustaAsiakkaatPaneeli();
        valilehdet.get(2).setContent(asiakkaatPaneeli);
        // Alustetaan neljäs välilehti.
        alustaLaskutPaneeli();
        valilehdet.get(3).setContent(laskutPaneeli);
        // Alustetaan viides välilehti
        alustaRaportitPaneeli();
        valilehdet.get(4).setContent(raportitPaneeli);

        Scene kehys = new Scene(valilehtipaneeli, 1000, 650);
        primaryStage.setTitle("Mokki");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}