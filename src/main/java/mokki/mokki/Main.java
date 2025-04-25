package mokki.mokki;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mokki.mokki.gui.*;

import java.util.ArrayList;

public class Main extends Application {
    int fonttikoko = 16;
    VBox kohteetPaneeli = new VBox();
    VBox varauksetPaneeli = new VBox();
    VBox asiakkaatPaneeli = new VBox();
    VBox laskutPaneeli = new VBox();
    VBox raportitPaneeli = new VBox();

    private void alustaKohteetPaneeli() {
        Hallintapaneeli hallintapaneeli = new Hallintapaneeli(
                new String[] {"Lisää kohde", "Rajaa kohteita", "Poista rajaukset"});
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<KohteetWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new KohteetWrapper("JOE001", "Joensuu", 1,
                        46, 250, "Kohteessa on poreallas."),
                new KohteetWrapper("KON005", "Kontiolahti", 5,
                        25, 85, ""),
                new KohteetWrapper("LIP003", "Liperi", 2, 15,
                        130, "Kohde on remontoitavana 16.3.2025 asti.")
        );
        Taulukkopaneeli<KohteetWrapper> taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä kohteen tiedot", "Muuta kohteen tietoja", "Poista kohde"}, taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        kohteetPaneeli.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

        // TODO: Aseta painikkeiden toiminnallisuus.
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });
    }

    private void alustaVarauksetPaneeli() {
        Hallintapaneeli hallintapaneeli = new Hallintapaneeli(
                new String[] {"Lisää varaus", "Rajaa varauksia", "Poista rajaukset"});
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<VarauksetWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new VarauksetWrapper("A003", "JOE001", "Matti Meikäläinen",
                        "05.04.2025", "08.04.2025", "Päättynyt",
                        "Lisäpalvelu: ylimääräinen sänky")
        );
        Taulukkopaneeli<VarauksetWrapper> taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä varauksen tiedot", "Näytä mökin tiedot", "Näytä asiakkaan tiedot",
                        "Muuta varauksen tietoja", "Poista varaus"}, taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        varauksetPaneeli.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

        // TODO: Aseta painikkeiden toiminnallisuus.
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });
    }

    private void alustaAsiakkaatPaneeli() {
        Hallintapaneeli hallintapaneeli = new Hallintapaneeli(
                new String[] {"Lisää asiakas", "Rajaa asiakkaita", "Poista rajaukset"});
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<AsiakkaatWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new AsiakkaatWrapper("Jukka Jokunen", "jukka@gmail.com",
                        "043-046-0349","yksityishenkilö", "")
        );
        Taulukkopaneeli<AsiakkaatWrapper> taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä asiakkaan tiedot", "Muuta asiakkaan tietoja", "Poista asiakas"},
                taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        asiakkaatPaneeli.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

        // TODO: Aseta painikkeiden toiminnallisuus.
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });
    }

    private void alustaLaskutPaneeli() {
        Hallintapaneeli hallintapaneeli = new Hallintapaneeli(
                new String[] {"Lisää lasku", "Rajaa laskuja", "Poista rajaukset"});
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<LaskutWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new LaskutWrapper(3950359, "Vuokraus: JOE001; 15.03.2025-16.03.2025",
                        "Jaska Jokunen (jaska@gmail.com)", 90405964, 150.35,
                        "Avoin")
        );
        Taulukkopaneeli<LaskutWrapper> taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä laskun tiedot", "Muuta laskun tietoja", "Merkitse lasku maksetuksi",
                        "Poista lasku"}, taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        laskutPaneeli.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

        // TODO: Aseta painikkeiden toiminnallisuus.
        hallintapaneeli.getLisaaPainike().setOnAction(e -> {

        });
        hallintapaneeli.getRajaaPainike().setOnAction(e -> {

            //hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        hallintapaneeli.getPoistaRajauksetPainike().setOnAction(event -> {
            hallintapaneeli.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });
    }

    private void alustaRaportitPaneeli() {
        RaportitHallintapaneeli hallintapaneeli = new RaportitHallintapaneeli();
        hallintapaneeli.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<RaportitWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new RaportitWrapper("JOE001", 45, 21,
                        20, 46.4, 100, 10000)
        );
        Taulukkopaneeli<RaportitWrapper> taulukkopaneeli = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(),
                new String[] {"Näytä kohteen tiedot", "Näytä kohteen varaukset"}, taulukonSisalto);
        taulukkopaneeli.asetaFonttikoko(fonttikoko);
        raportitPaneeli.getChildren().addAll(hallintapaneeli, taulukkopaneeli);

        // TODO: Aseta elementtien toiminnallisuus.
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