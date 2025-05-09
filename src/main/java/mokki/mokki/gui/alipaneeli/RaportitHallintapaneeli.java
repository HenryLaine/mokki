package mokki.mokki.gui.alipaneeli;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * Luokka toteuttaa paneelin, jonka avulla on mahdollista hallita raportteja.
 */
public class RaportitHallintapaneeli extends FlowPane {
    private DatePicker alkupaivamaaraPicker;
    private DatePicker loppupaivamaaraPicker;
    private Text kokonaiskayttoasteTeksti;
    private Text kokonaistulotTeksti;
    private TextField rajauksetKentta;

    /**
     * Luokan alustaja
     */
    public RaportitHallintapaneeli() {
        alkupaivamaaraPicker = new DatePicker();
        loppupaivamaaraPicker = new DatePicker();
        VBox alkupaivamaaralaatikko = luoPaivamaaralaatikko("Alkupäivämäärä", alkupaivamaaraPicker);
        VBox loppupaivamaaralaatikko = luoPaivamaaralaatikko("Loppupäivämäärä", loppupaivamaaraPicker);
        GridPane tilastolaatikko = luoTilastolaatikko();
        VBox rajauspaneeli = luoRajauslaatikko();
        this.getChildren().addAll(alkupaivamaaralaatikko, loppupaivamaaralaatikko, rajauspaneeli, tilastolaatikko);
        this.setHgap(40);
        this.setVgap(0);
    }

    /**
     * Metodi luo hallintapaneeliin sijoitettavan päivämäärälaatikon.
     * @param nimi laatikon otsikko
     * @return päivämäärälaatikko
     */
    private VBox luoPaivamaaralaatikko(String nimi, DatePicker datePicker) {
        VBox paivamaarapaneeli = new VBox(10);
        paivamaarapaneeli.setPadding(new Insets(10));
        Text teksti = new Text(nimi);
        teksti.setStyle("-fx-font-weight:bold;");
        paivamaarapaneeli.getChildren().addAll(teksti, datePicker);
        return paivamaarapaneeli;
    }

    /**
     * Metodi luo hallintapaneeliin sijoitettavan tilastolaatikon
     * @return tilastolaatikko
     */
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

    /**
     * Metodi luo hallintapaneeliin sijoitettavan rajauslaatikon.
     * @return rajauslaatikko
     */
    private VBox luoRajauslaatikko() {
        VBox rajauslaatikko = new VBox(10);
        rajauslaatikko.setPadding(new Insets(10));
        rajauksetKentta = new TextField();
        Text rajausteksti = new Text("Rajaa kohteita");
        rajausteksti.setStyle("-fx-font-weight:bold;");
        rajauslaatikko.getChildren().addAll(rajausteksti, rajauksetKentta);
        return rajauslaatikko;
    }

    /**
     * Metodi muuttaa fontin kokoa.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        this.setStyle("-fx-font-size:" + fonttikoko + "px;");
        alkupaivamaaraPicker.setPrefWidth(9*fonttikoko);
        loppupaivamaaraPicker.setPrefWidth(9*fonttikoko);

    }

    /** getteri alkupäivämäärälle
     *
     * @return
     */
    public DatePicker getAlkupaivamaaraPicker() {
        return alkupaivamaaraPicker;
    }

    /** getteri loppupäivämäärälle
     * @return
     */
    public DatePicker getLoppupaivamaaraPicker() {
        return loppupaivamaaraPicker;
    }

    /**
     * Metodi palauttaa kokonaiskäyttöasteen näyttävän tekstin.
     * @return kokonaiskäyttöaste
     */
    public Text getKokonaiskayttoasteTeksti() {
        return kokonaiskayttoasteTeksti;
    }

    /**
     * Metodi palauttaa kokonaistulot näyttävän tekstin.
     * @return kokonaistuloteksti
     */
    public Text getKokonaistulotTeksti() {
        return kokonaistulotTeksti;
    }

    /**
     * Metodi palauttaa rajaukset sisältävän tekstikentän.
     * @return Rajaukset-tekstikenttä
     */
    public TextField getRajauksetKentta() {
        return rajauksetKentta;
    }

}
