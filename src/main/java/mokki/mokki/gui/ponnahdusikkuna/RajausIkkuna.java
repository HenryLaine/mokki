package mokki.mokki.gui.ponnahdusikkuna;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Luokka toteuttaa ponnahdusikkunan, jossa käyttäjä voi syöttää hakusanan asiakkaiden rajausta varten.
 */
public class RajausIkkuna extends Stage {
    private BorderPane paapaneeli;
    private TextField hakukentta;
    private String hakusana = null;

    private String otsikko;

    /**
     * Luo rajausikkunan.
     */
    public RajausIkkuna() {
        paapaneeli = new BorderPane();
        paapaneeli.setPadding(new Insets(20));

        VBox keskisisalto = new VBox(10);
        Label ohje = new Label("Syötä hakusana:");
        hakukentta = new TextField();

        keskisisalto.getChildren().addAll(ohje, hakukentta);
        paapaneeli.setCenter(keskisisalto);

        HBox alapaneeli = new HBox(10);
        alapaneeli.setPadding(new Insets(20, 0, 0, 0));
        Button okPainike = new Button("OK");
        Button peruPainike = new Button("Peruuta");
        alapaneeli.getChildren().addAll(okPainike, peruPainike);
        paapaneeli.setBottom(alapaneeli);

        // Painikkeiden toiminta
        okPainike.setOnAction(e -> {
            String syote = hakukentta.getText().trim();
            if (!syote.isEmpty()) {
                hakusana = syote;
                this.close();
            }
        });

        peruPainike.setOnAction(e -> {
            hakusana = null;
            this.close();
        });

        // Enter näppäin toimii OK:nä
        hakukentta.setOnAction(e -> okPainike.fire());

        Scene kehys = new Scene(paapaneeli, 300, 150);
        this.setScene(kehys);
        this.setTitle(otsikko);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Näyttää ikkunan ja palauttaa syötetyn hakusanan.
     * @return hakusana tai null jos peruutettiin
     */
    public String naytaJaOdotaJaPalautaTulos() {
        this.showAndWait();
        return hakusana;
    }

    /**
     * Asettaa ikkunan fonttikoon.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        paapaneeli.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }
}
