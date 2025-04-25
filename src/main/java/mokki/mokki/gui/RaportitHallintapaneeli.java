package mokki.mokki.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class RaportitHallintapaneeli extends FlowPane {
    private ComboBox<String> alkuvuosivalikko;
    private ComboBox<String> alkukuukausivalikko;
    private ComboBox<String> loppuvuosivalikko;
    private ComboBox<String> loppukuukausivalikko;
    private Text kokonaiskayttoasteTeksti;
    private Text kokonaistulotTeksti;
    private TextField rajauksetKentta;

    public RaportitHallintapaneeli() {
        String[] kuukaudet = new String[] {"Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu",
                "Kesäkuu", "Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu"};
        String[] vuodet = new String[] {"2020", "2021", "2022", "2023", "2024", "2025"};
        alkuvuosivalikko = new ComboBox<>(FXCollections.observableArrayList(vuodet));
        alkukuukausivalikko = new ComboBox<>(FXCollections.observableArrayList(kuukaudet));
        loppuvuosivalikko = new ComboBox<>(FXCollections.observableArrayList(vuodet));
        loppukuukausivalikko = new ComboBox<>(FXCollections.observableArrayList(kuukaudet));
        VBox alkupaivamaaralaatiko = luoPaivamaaralaatikko("Alkupäivämäärä",
                alkuvuosivalikko, alkukuukausivalikko);
        VBox loppupaivamaaralaatikko = luoPaivamaaralaatikko("Loppupäivämäärä",
                loppuvuosivalikko, loppukuukausivalikko);
        GridPane tilastolaatikko = luoTilastolaatikko();
        VBox rajauspaneeli = luoRajauslaatikko();
        this.getChildren().addAll(alkupaivamaaralaatiko, loppupaivamaaralaatikko, rajauspaneeli, tilastolaatikko);
        this.setHgap(40);
        this.setVgap(0);
    }

    private VBox luoPaivamaaralaatikko(String nimi, ComboBox<String> vuosi, ComboBox<String> kuukausi) {
        VBox paivamaarapaneeli = new VBox(10);
        paivamaarapaneeli.setPadding(new Insets(10));
        Text teksti = new Text(nimi);
        teksti.setStyle("-fx-font-weight:bold;");
        HBox pudotusvalikkolaatikko = new HBox(10);
        pudotusvalikkolaatikko.getChildren().addAll(vuosi, kuukausi);
        paivamaarapaneeli.getChildren().addAll(teksti, pudotusvalikkolaatikko);
        return paivamaarapaneeli;
    }

    private GridPane luoTilastolaatikko() {
        GridPane tilastolaatikko = new GridPane();
        tilastolaatikko.setPadding(new Insets(10));
        tilastolaatikko.setVgap(15);
        tilastolaatikko.setHgap(10);
        kokonaiskayttoasteTeksti = new Text("");
        kokonaistulotTeksti = new Text("");
        tilastolaatikko.add(new Text("Kokonaiskäyttöaste: "), 0, 0);
        tilastolaatikko.add(new Text("Kokonaistulot: "), 0, 1);
        tilastolaatikko.add(kokonaiskayttoasteTeksti, 1, 0);
        tilastolaatikko.add(kokonaistulotTeksti, 1, 1);
        return tilastolaatikko;
    }

    private VBox luoRajauslaatikko() {
        VBox rajauslaatikko = new VBox(10);
        rajauslaatikko.setPadding(new Insets(10));
        rajauksetKentta = new TextField();
        Text rajausteksti = new Text("Rajaa kohteita");
        rajausteksti.setStyle("-fx-font-weight:bold;");
        rajauslaatikko.getChildren().addAll(rajausteksti, rajauksetKentta);
        return rajauslaatikko;
    }

    public void asetaFonttikoko(int fonttikoko) {
        this.setStyle("-fx-font-size:" + fonttikoko + "px;");
        alkuvuosivalikko.setPrefWidth(9*fonttikoko);
        alkukuukausivalikko.setPrefWidth(9*fonttikoko);
        loppuvuosivalikko.setPrefWidth(9*fonttikoko);
        loppukuukausivalikko.setPrefWidth(9*fonttikoko);
    }

    public ComboBox<String> getAlkukuukausivalikko() {
        return alkukuukausivalikko;
    }

    public ComboBox<String> getAlkuvuosivalikko() {
        return alkuvuosivalikko;
    }

    public ComboBox<String> getLoppukuukausivalikko() {
        return loppukuukausivalikko;
    }

    public ComboBox<String> getLoppuvuosivalikko() {
        return loppuvuosivalikko;
    }

    public Text getKokonaiskayttoasteTeksti() {
        return kokonaiskayttoasteTeksti;
    }

    public Text getKokonaistulotTeksti() {
        return kokonaistulotTeksti;
    }

    public TextField getRajauksetKentta() {
        return rajauksetKentta;
    }

}
