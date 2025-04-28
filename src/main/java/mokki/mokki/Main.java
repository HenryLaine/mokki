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
import mokki.mokki.gui.paapaneeli.*;
import mokki.mokki.gui.ponnahdusikkuna.TiedotIkkuna;
import mokki.mokki.gui.ponnahdusikkuna.Vahvistusikkuna;
import mokki.mokki.gui.wrapper.*;

import java.util.ArrayList;
import java.util.Optional;

public class Main extends Application {
    int fonttikoko = 16;
    KohteetPaneeli kohteetPaneeli;
    VarauksetPaneeli varauksetPaneeli;
    AsiakkaatPaneeli asiakkaatPaneeli;
    LaskutPaneeli laskutPaneeli;
    RaportitPaneeli raportitPaneeli;

    private void alustaKohteetPaneeli() {
        // Käyttöliittymän taulukon sisältö (dummy-dataa)
        ObservableList<TaulukkoWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new KohteetWrapper("JOE001", "Joensuu", 1,
                        46, 250, "Kohteessa on poreallas."),
                new KohteetWrapper("KON005", "Kontiolahti", 5,
                        25, 85, ""),
                new KohteetWrapper("LIP003", "Liperi", 2, 15,
                        130, "Kohde on remontoitavana 16.3.2025 asti.")
        );
        kohteetPaneeli = new KohteetPaneeli(fonttikoko, taulukonSisalto);

        // TODO: Aseta hallintapaneelin painikkeiden toiminnallisuus.
        Hallintapaneeli hallintapaneeli = kohteetPaneeli.getHallintapaneeli();
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });

        // TODO: Aseta taulukkopaneelin kontekstivalikon toiminnallisuus.
        Taulukkopaneeli<TaulukkoWrapper> taulukkopaneeli = kohteetPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Kohteen tiedot näytetään.
            TiedotIkkuna tiedotIkkuna = new TiedotIkkuna(
                    taulukkopaneeli.getSelectionModel().getSelectedItem(),
                    true, false, "Kohteen tiedot");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            tiedotIkkuna.showAndWait();
        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Kohteen tietoja muutetaan.
            TiedotIkkuna tiedotIkkuna = new TiedotIkkuna(
                    taulukkopaneeli.getSelectionModel().getSelectedItem(),
                    true, true, "Muuta kohteen tietoja");
            tiedotIkkuna.asetaFonttikoko(fonttikoko);
            boolean tulos = tiedotIkkuna.naytaJaOdotaJaPalautaTulos();
            if (tulos) {
                // TODO: Kohteen tietoja muutetaan tietokannassa.

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
                taulukonSisalto.remove(taulukkopaneeli.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void alustaVarauksetPaneeli() {
        // Dummy-dataa
        ObservableList<TaulukkoWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new VarauksetWrapper("A003", "JOE001", "Matti Meikäläinen",
                        "05.04.2025", "08.04.2025", "Päättynyt",
                        "Lisäpalvelu: ylimääräinen sänky")
        );
        varauksetPaneeli = new VarauksetPaneeli(fonttikoko, taulukonSisalto);

        // TODO: Aseta hallintapaneelin painikkeiden toiminnallisuus.
        Hallintapaneeli hallintapaneeli = varauksetPaneeli.getHallintapaneeli();
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });

        // TODO: Aseta taulukkopaneelin kontekstivalikon toiminnallisuus.
        Taulukkopaneeli<TaulukkoWrapper> taulukkopaneeli = varauksetPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Varauksen tiedot näytetään

        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Mökin tiedot näytetään

        });
        kontekstivalikonKohdat.get(2).setOnAction(e -> {
            // Asiakkaan tiedot näytetään

        });
        kontekstivalikonKohdat.get(3).setOnAction(e -> {
            // Varauksen tietoja muutetaan

        });
        kontekstivalikonKohdat.get(4).setOnAction(e -> {
            Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                    "Haluatko varmasti poistaa varauksen " +
                            taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
            Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

            if(tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                // Varaus poistetaan ensin tietokannasta ja sitten taulukon sisällöstä.

                taulukonSisalto.remove(taulukkopaneeli.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void alustaAsiakkaatPaneeli() {
        // Dummy-dataa
        ObservableList<TaulukkoWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new AsiakkaatWrapper("Jukka Jokunen", "jukka@gmail.com",
                        "043-046-0349","yksityishenkilö", "")
        );
        asiakkaatPaneeli = new AsiakkaatPaneeli(fonttikoko, taulukonSisalto);

        // TODO: Aseta hallintapaneelin painikkeiden toiminnallisuus.
        Hallintapaneeli hallintapaneeli = asiakkaatPaneeli.getHallintapaneeli();
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });

        // TODO: Aseta taulukkopaneelin kontekstivalikon toiminnallisuus.
        Taulukkopaneeli<TaulukkoWrapper> taulukkopaneeli = asiakkaatPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Varauksen tiedot näytetään

        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Mökin tiedot näytetään

        });

        kontekstivalikonKohdat.get(2).setOnAction(e -> {
            Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                    "Haluatko varmasti poistaa asiakkaan " +
                            taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
            Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

            if(tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                // Asiakas poistetaan ensin tietokannasta ja sitten taulukon sisällöstä.

                taulukonSisalto.remove(taulukkopaneeli.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void alustaLaskutPaneeli() {
        // Dummy-dataa
        ObservableList<TaulukkoWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new LaskutWrapper(3950359, "Vuokraus: JOE001; 15.03.2025-16.03.2025",
                        "Jaska Jokunen (jaska@gmail.com)", 90405964, 150.35,
                        "Avoin")
        );
        laskutPaneeli = new LaskutPaneeli(fonttikoko, taulukonSisalto);

        // TODO: Aseta hallintapaneelin painikkeiden toiminnallisuus.
        Hallintapaneeli hallintapaneeli = laskutPaneeli.getHallintapaneeli();
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });

        // TODO: Aseta taulukkopaneelin kontekstivalikon toiminnallisuus.
        Taulukkopaneeli<TaulukkoWrapper> taulukkopaneeli = laskutPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();

        kontekstivalikonKohdat.getFirst().setOnAction(e -> {
            // Varauksen tiedot näytetään

        });
        kontekstivalikonKohdat.get(1).setOnAction(e -> {
            // Mökin tiedot näytetään

        });

        kontekstivalikonKohdat.get(2).setOnAction(e -> {
            // Mökin tiedot näytetään

        });

        kontekstivalikonKohdat.get(3).setOnAction(e -> {
            Vahvistusikkuna vahvistusikkuna = new Vahvistusikkuna("Vahvistus",
                    "Haluatko varmasti poistaa laskun " +
                            taulukkopaneeli.palautaRivinTiedot().palautaKuvausteksti() + "?");
            Optional<ButtonType> tulos = vahvistusikkuna.showAndWait();

            if(tulos.isPresent() && tulos.get() == vahvistusikkuna.getButtonTypes().getFirst()) {
                // Asiakas poistetaan ensin tietokannasta ja sitten taulukon sisällöstä.

                taulukonSisalto.remove(taulukkopaneeli.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void alustaRaportitPaneeli() {
        // Dummy-dataa
        ObservableList<TaulukkoWrapper> taulukonSisalto = FXCollections.observableArrayList(
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
        Taulukkopaneeli<TaulukkoWrapper> taulukkopaneeli = raportitPaneeli.getTaulukkopaneeli();
        ArrayList<MenuItem> kontekstivalikonKohdat = taulukkopaneeli.getKontekstivalikonKohdat();
        kontekstivalikonKohdat.getFirst().setOnAction(e -> {

        });

        kontekstivalikonKohdat.get(1).setOnAction(e -> {

        });
    }

    public static void main(String[] args) {
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