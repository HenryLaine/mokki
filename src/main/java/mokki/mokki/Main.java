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
        Hallintapaneeli kohteetHallinta = new Hallintapaneeli(
                new String[] {"Lisää kohde", "Rajaa kohteita", "Poista rajaukset"});
        kohteetHallinta.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<KohteetWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new KohteetWrapper("JOE001", "Joensuu", 1,
                        46, 250, "Kohteessa on poreallas."),
                new KohteetWrapper("KON005", "Kontiolahti", 5,
                        25, 85, ""),
                new KohteetWrapper("LIP003", "Liperi", 2, 15,
                        130, "Kohde on remontoitavana 16.3.2025 asti.")
        );
        Taulukkopaneeli<KohteetWrapper> kohteetTaulukko = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(), taulukonSisalto);
        kohteetTaulukko.asetaFonttikoko(fonttikoko);
        kohteetPaneeli.getChildren().addAll(kohteetHallinta, kohteetTaulukko);

        // TODO: Aseta painikkeiden toiminnallisuus.
        kohteetHallinta.getLisaaPainike().setOnAction(e -> {

        });
        kohteetHallinta.getRajaaPainike().setOnAction(e -> {

            //kohteetHallinta.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        kohteetHallinta.getPoistaRajauksetPainike().setOnAction(event -> {
            kohteetHallinta.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

        });
    }

    private void alustaVarauksetPaneeli() {
        Hallintapaneeli kohteetHallinta = new Hallintapaneeli(
                new String[] {"Lisää varaus", "Rajaa varauksia", "Poista rajaukset"});
        kohteetHallinta.asetaFonttikoko(fonttikoko);
        // Dummy-dataa
        ObservableList<VarauksetWrapper> taulukonSisalto = FXCollections.observableArrayList(
                new VarauksetWrapper("A003", "JOE001", "Matti Meikäläinen",
                        "05.04.2025", "08.04.2025", "Päättynyt",
                        "Lisäpalvelu: ylimääräinen sänky")
        );
        Taulukkopaneeli<VarauksetWrapper> kohteetTaulukko = new Taulukkopaneeli<>(
                taulukonSisalto.getFirst().getMaaritykset(), taulukonSisalto);
        kohteetTaulukko.asetaFonttikoko(fonttikoko);
        varauksetPaneeli.getChildren().addAll(kohteetHallinta, kohteetTaulukko);

        // TODO: Aseta painikkeiden toiminnallisuus.
        kohteetHallinta.getLisaaPainike().setOnAction(e -> {

        });
        kohteetHallinta.getRajaaPainike().setOnAction(e -> {

            //kohteetHallinta.getRajauksetTeksti().setText("RAJAUKSET:\t" + "rajausteksti");
        });
        kohteetHallinta.getPoistaRajauksetPainike().setOnAction(event -> {
            kohteetHallinta.getRajauksetTeksti().setText("RAJAUKSET:\t\t\t");

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


        Scene kehys = new Scene(valilehtipaneeli, 900, 600);
        primaryStage.setTitle("Mokki");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}