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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            Connection conn = DatabaseManager.getConnection();
            MokkiDAO mokkiDAO = new MokkiDAO(conn);

            List<KohteetWrapper> kohteet = mokkiDAO.haeMokit();
            ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(kohteet);
            kohteetPaneeli = new KohteetPaneeli(fonttikoko, taulukonSisalto);

            Hallintapaneeli hallintapaneeli = kohteetPaneeli.getHallintapaneeli();
            Taulukkopaneeli<TaulukonData> taulukkopaneeli = kohteetPaneeli.getTaulukkopaneeli();

            // Lisää kohde
            hallintapaneeli.getLisaaPainike().setOnAction(e -> {
                TaulukonData uusiKohde = new KohteetWrapper(); // Tyhjä konstruktori
                KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(uusiKohde, true, "Lisää kohde", new String[]{"Lisää kohde", "Peruuta"});
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();

                if (tulos) {
                    uusiKohde.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                    try {
                        mokkiDAO.lisaaMokki((KohteetWrapper) uusiKohde);
                        taulukonSisalto.add(uusiKohde);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Rajaa kohteita
            hallintapaneeli.getRajaaPainike().setOnAction(e -> {
                RajausIkkuna rajausIkkuna = new RajausIkkuna();
                rajausIkkuna.setTitle("Rajaa mökkejä hakusanalla");

                String hakusana = rajausIkkuna.naytaJaOdotaJaPalautaTulos();
                if (hakusana != null && !hakusana.isBlank()) {
                    try {
                        List<KohteetWrapper> rajatut = mokkiDAO.rajaaMokit(hakusana);
                        taulukonSisalto.clear();
                        taulukonSisalto.addAll(rajatut);
                        hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET: " + hakusana);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        new Virheikkuna("Tietokantavirhe", "Mökkien haku epäonnistui.").show();
                    }
                }
            });

            // Poista rajaukset
            hallintapaneeli.getPoistaRajauksetPainike().setOnAction(e -> {
                try {
                    taulukonSisalto.clear();
                    List<KohteetWrapper> kaikki = mokkiDAO.haeMokit();
                    taulukonSisalto.setAll(kaikki);
                    hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

            // Näytä tiedot
            kontekstivalikonKohdat.get(0).setOnAction(e -> {
                KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(
                        taulukkopaneeli.palautaRivinTiedot(), false, true, "Kohteen tiedot", new String[]{"", "Sulje"});
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                tiedotIkkuna.showAndWait();
            });

            // Muokkaa kohdetta
            kontekstivalikonKohdat.get(1).setOnAction(e -> {
                TaulukonData kohteenTiedot = taulukkopaneeli.palautaRivinTiedot();
                KohteenTiedotIkkuna tiedotIkkuna = new KohteenTiedotIkkuna(
                        kohteenTiedot, true, true, "Muuta kohteen tietoja", new String[]{"Muuta tiedot", "Peruuta"});
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
                if (tulos) {
                    kohteenTiedot.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                    try {
                        mokkiDAO.muokkaaMokki((KohteetWrapper) kohteenTiedot);
                        int index = taulukonSisalto.indexOf(kohteenTiedot);
                        if (index >= 0) {
                            taulukonSisalto.set(index, kohteenTiedot);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Poista kohde
            kontekstivalikonKohdat.get(2).setOnAction(e -> {
                TaulukonData valittu = taulukkopaneeli.palautaRivinTiedot();
                Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                        "Haluatko varmasti poistaa kohteen " + valittu.palautaKuvausteksti() + "?");
                Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

                if (tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                    try {
                        mokkiDAO.poistaMokki(Integer.parseInt(valittu.palautaTunniste()));
                        taulukonSisalto.remove(valittu);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } catch (SQLException e) {
            System.err.println("Tietokantayhteyden muodostaminen epäonnistui: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void alustaVarauksetPaneeli() {
        try {
            Connection conn = DatabaseManager.getConnection();
            VarausDAO varausDAO = new VarausDAO(conn);

            List<VarauksetWrapper> varaukset = varausDAO.haeKaikkiWrapperVaraukset();
            ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(varaukset);
            varauksetPaneeli = new VarauksetPaneeli(fonttikoko, taulukonSisalto);

            Hallintapaneeli hallintapaneeli = varauksetPaneeli.getHallintapaneeli();
            Taulukkopaneeli<TaulukonData> taulukkopaneeli = varauksetPaneeli.getTaulukkopaneeli();

            // Lisää varaus

            hallintapaneeli.getLisaaPainike().setOnAction(event -> {
                TaulukonData uusiVaraus = new VarauksetWrapper(); // Tyhjä Konstruktori
                VarauksenTiedotIkkuna tiedotIkkuna = new VarauksenTiedotIkkuna(uusiVaraus, "Lisää varaus");
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();

                if (tulos)
                {
                    uusiVaraus.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                    try {
                        varausDAO.lisaaVaraus((VarauksetWrapper) uusiVaraus);
                        taulukonSisalto.add(uusiVaraus);
                    }
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });


                // Rajaa varauksia
                hallintapaneeli.getRajaaPainike().setOnAction(event1 -> {
                    RajausIkkuna rajausIkkuna = new RajausIkkuna();
                    rajausIkkuna.setTitle("Rajaa varauksia hakusanalla");

                    String hakusana = rajausIkkuna.naytaJaOdotaJaPalautaTulos();
                    if (hakusana != null && !hakusana.isBlank()){
                        try {
                            List<VarauksetWrapper> rajatut = varausDAO.rajaaVaraukset(hakusana);
                            taulukonSisalto.clear();
                            taulukonSisalto.addAll(rajatut);
                            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET: " + hakusana);
                        }

                        catch (SQLException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                });

                // Poista rajaukset

                hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
                    try {
                        taulukonSisalto.clear();
                        List<VarauksetWrapper> kaikki = varausDAO.haeKaikkiWrapperVaraukset();
                        taulukonSisalto.setAll(kaikki);
                        hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");
                    }
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });

                ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

                // Näytä tiedot

                kontekstivalikonKohdat.get(0).setOnAction(e -> {
                    VarauksenTiedotIkkuna tiedotIkkuna = new VarauksenTiedotIkkuna(
                            taulukkopaneeli.palautaRivinTiedot(), "Varauksen tiedot");
                    tiedotIkkuna.asetaFonttikoko(fonttikoko);
                    tiedotIkkuna.showAndWait();
                });

                // Muokkaa varausta
                kontekstivalikonKohdat.get(3).setOnAction(e -> {
                    TaulukonData varauksentiedot = taulukkopaneeli.palautaRivinTiedot();
                    VarauksenTiedotIkkuna tiedotIkkuna = new VarauksenTiedotIkkuna(varauksentiedot, "Muuta varauksen tietoja");
                    tiedotIkkuna.asetaFonttikoko(fonttikoko);
                    boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
                    if  (tulos) {
                        varauksentiedot.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                        try {
                            varausDAO.muokkaaVarausta((VarauksetWrapper) varauksentiedot);
                            int index = taulukonSisalto.indexOf(varauksentiedot);
                            if (index >= 0) {
                               taulukonSisalto.set(index, varauksentiedot);
                             }
                      }

                        catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

            // Poista varaus
            kontekstivalikonKohdat.get(4).setOnAction(e -> {
                TaulukonData valittu = taulukkopaneeli.palautaRivinTiedot();
                Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                        "Haluatko varmasti poistaa varauksen" + valittu.palautaKuvausteksti() + "?");
                Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();
                if (tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                    try {
                        varausDAO.poistaVaraus(Integer.parseInt(valittu.palautaTunniste()));
                        taulukonSisalto.remove(valittu);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


        }

        catch (SQLException e)
        {
            System.err.println("Tietokantayhteyden muodostaminen epäonnistui: " + e.getMessage());
            e.printStackTrace();
        }
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
                taulukonSisalto.clear();
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
        try {
            Connection conn = DatabaseManager.getConnection();
            LaskutDAO laskutDAO = new LaskutDAO(conn);
            List<LaskutWrapper> laskut = laskutDAO.haeKaikkiLaskut();
            ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(laskut);


            laskutPaneeli = new LaskutPaneeli(fonttikoko, taulukonSisalto);

            // TODO: Aseta hallintapaneelin painikkeiden toiminnallisuus.
            Hallintapaneeli hallintapaneeli = laskutPaneeli.getHallintapaneeli();
            hallintapaneeli.getLisaaPainike().setOnAction(e -> {
                TaulukonData uusiLasku = new LaskutWrapper(
                        0, 0, "", 0,
                        0, 0, 0, LocalDate.now(), LocalDate.now().plusDays(30),
                        "", "", "", "Avoin"
                );
                LaskunTiedotIkkuna tiedotIkkuna = new LaskunTiedotIkkuna(uusiLasku, "Lisää lasku");
                tiedotIkkuna.asetaFonttikoko(fonttikoko);

                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
                if (tulos) {
                    uusiLasku.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());

                    try {
                        laskutDAO.lisaaLasku((LaskutWrapper) uusiLasku);
                        taulukonSisalto.add(uusiLasku);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }});

            hallintapaneeli.getRajaaPainike().setOnAction(e -> {
                RajausIkkuna rajausIkkuna = new RajausIkkuna();
                rajausIkkuna.setTitle("Rajaa laskuja hakusanan mukaan");

                String hakusana = rajausIkkuna.naytaJaOdotaJaPalautaTulos();
                if (hakusana != null && !hakusana.isBlank()) {
                    try {
                        List<LaskutWrapper> rajatut = laskutDAO.rajaaLaskut(hakusana);

                        taulukonSisalto.clear();
                        taulukonSisalto.addAll(rajatut);
                        hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET: " + hakusana);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });
            hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
                taulukonSisalto.clear();
                taulukonSisalto.addAll(laskut);
                hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

            });

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
                LaskunTiedotIkkuna tiedotIkkuna = new LaskunTiedotIkkuna(laskunTiedot, "Muokkaa laskun tietoja");
                tiedotIkkuna.asetaFonttikoko(fonttikoko);
                boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
                if (tulos) {
                    laskunTiedot.paivitaKenttienArvot(tiedotIkkuna.palautaKenttienTiedot());
                    try {
                        LaskutWrapper uusiData = (LaskutWrapper) laskunTiedot;
                        laskutDAO.muokkaaLaskua(uusiData);
                        int index = taulukonSisalto.indexOf(laskunTiedot);
                        if (index >= 0)
                        {
                            taulukonSisalto.set(index, laskunTiedot);
                        }
                    }

                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
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
                    try {
                        laskutDAO.paivitaLaskunStatus(lasku.getLaskunumero(), "Maksettu");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            });

            kontekstivalikonKohdat.get(3).setOnAction(e -> {
                // Lasku poistetaan
                TaulukonData valittu = taulukkopaneeli.palautaRivinTiedot();
                Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                        "Haluatko varmasti poistaa laskun " +
                                taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
                Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

                if (tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                    try {
                    laskutDAO.poistaLasku(Integer.parseInt(valittu.palautaTunniste()));

                    // Lasku poistetaan taulukon sisällöstä
                    taulukonSisalto.remove(taulukkopaneeli.palautaRivinTiedot());
                }

                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }}
            });
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }



    private void alustaRaportitPaneeli() {
        try {
            // Oletuspäivämäärät
            LocalDate oletusAlku = LocalDate.of(2023, 1, 1);
            LocalDate oletusLoppu = LocalDate.of(2025, 12, 31);

            // Luodaan yhteys ja haetaan alkuraportti
            try (Connection conn = DatabaseManager.getConnection()) {
                RaportitDAO raporttiDAO = new RaportitDAO(conn);
                List<RaportitWrapper> raportit = raporttiDAO.haeRaportti(oletusAlku, oletusLoppu);
                ObservableList<TaulukonData> taulukonSisalto = FXCollections.observableArrayList(raportit);

                // Luodaan käyttöliittymäkomponentit
                raportitPaneeli = new RaportitPaneeli(fonttikoko, taulukonSisalto);
                RaportitHallintapaneeli hallintapaneeli = raportitPaneeli.getHallintapaneeli();

                // Asetetaan oletuspäivämäärät
                hallintapaneeli.getAlkupaivamaaraPicker().setValue(oletusAlku);
                hallintapaneeli.getLoppupaivamaaraPicker().setValue(oletusLoppu);

                // Päivitä taulukko, kun päivämäärävalinnat muuttuvat
                hallintapaneeli.getAlkupaivamaaraPicker().valueProperty().addListener((obs, oldVal, newVal) -> {
                    try {
                        haeJaPaivitaRaportti(taulukonSisalto, hallintapaneeli);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                hallintapaneeli.getLoppupaivamaaraPicker().valueProperty().addListener((obs, oldVal, newVal) -> {
                    try {
                        haeJaPaivitaRaportti(taulukonSisalto, hallintapaneeli);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                // Kontekstivalikon toiminto
                Taulukkopaneeli<TaulukonData> taulukkopaneeli = raportitPaneeli.getTaulukkopaneeli();
                ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();
                if (!kontekstivalikonKohdat.isEmpty()) {
                    kontekstivalikonKohdat.get(0).setOnAction(e -> {
                        TaulukonData valittu = taulukkopaneeli.palautaRivinTiedot();
                        if (valittu != null) {
                            String kohteenTunnus = valittu.palautaTunniste();
                            System.out.println("Näytetään tiedot kohteesta: " + kohteenTunnus);


                        }
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Voit halutessasi näyttää virheilmoituksen myös käyttöliittymässä
        }
    }

    private void haeJaPaivitaRaportti(ObservableList<TaulukonData> taulukonSisalto, RaportitHallintapaneeli hallintapaneeli) throws SQLException {
        LocalDate alku = hallintapaneeli.getAlkupaivamaaraPicker().getValue();
        LocalDate loppu = hallintapaneeli.getLoppupaivamaaraPicker().getValue();

        if (alku == null || loppu == null || alku.isAfter(loppu)) {
            System.out.println("Virheellinen päivämääräväli");
            return;
        }

        try (Connection conn = DatabaseManager.getConnection()) {
            RaportitDAO raporttiDao = new RaportitDAO(conn);
            List<RaportitWrapper> raportit = raporttiDao.haeRaportti(alku, loppu);
            taulukonSisalto.setAll(raportit);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Virhe haettaessa raporttia tietokannasta.");
        }

    }

    public static void main(String[] args) {
        DatabaseCreator.ensureDatabaseExists();
        DatabaseInitializer.initializeDatabase();
        Application.launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws SQLException {

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