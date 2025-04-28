package mokki.mokki.gui.ponnahdusikkuna;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mokki.mokki.gui.wrapper.TaulukkoWrapper;


public class KohteenTiedotIkkuna extends Stage {
    private VBox paapaneeli;
    private TextField tunnusKentta;
    private TextField sijaintiKentta;
    private TextField huoneitaKentta;
    private TextField pintaAlaKentta;
    private TextField hintaKentta;
    private TextArea huomioitavaaKentta;
    boolean tulos = false;

    /**
     * Alustaja, joka luo muokattavan ikkunan tyhjillä tiedoilla
     */
    public KohteenTiedotIkkuna(TaulukkoWrapper kohdeluokka) {

    }

    /**
     * Alustaja, joka luo esitäytetyn ikkunan
     * @param kohdeluokka
     * @param muokattavissa
     */
    public KohteenTiedotIkkuna(TaulukkoWrapper kohdeluokka, boolean muokattavissa) {
        String[] kenttienArvot = kohdeluokka.palautaKenttienArvot();
        paapaneeli = new VBox(20);
        paapaneeli.setPadding(new Insets(20));
        GridPane ruudukkopaneeli = luoRuudukkopaneeli(kenttienArvot, muokattavissa);
        VBox huomioitavaaPaneeli = luoHuomioitavaaPaneeli(kenttienArvot, muokattavissa);
        if (muokattavissa) {
            HBox painikeLaatikko = new HBox(20);
            Button muutaPainike = new Button("Muuta tiedot");
            Button peruutaPainike = new Button("Peruuta");
            muutaPainike.setMinWidth(100);
            peruutaPainike.setMinWidth(100);

            muutaPainike.setOnAction(e -> {
                tulos = kohdeluokka.ovatkoArvotHyvaksyttavia(palautaKenttienTiedot());
                if (tulos) {
                    this.close();
                }
                else {
                    //TODO: Luodaan virheikkuna
                }
            });
            peruutaPainike.setOnAction(e -> {
                tulos = false;
                this.close();
            });

            painikeLaatikko.getChildren().addAll(muutaPainike, peruutaPainike);
            paapaneeli.getChildren().addAll(ruudukkopaneeli, huomioitavaaPaneeli, painikeLaatikko);
        }
        else {
            Button suljePainike = new Button("Sulje");
            suljePainike.setMinWidth(100);
            suljePainike.setOnAction(e -> {
                this.close();
            });
            paapaneeli.getChildren().addAll(ruudukkopaneeli, huomioitavaaPaneeli, suljePainike);
        }

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        if (muokattavissa) {
            this.setTitle("Muuta kohteen tietoja");
        }
        else {
            this.setTitle("Kohteen tiedot");
        }
        this.setScene(kehys);
    }

    private GridPane luoRuudukkopaneeli(String[] kenttienArvot, boolean muokattavissa) {
        GridPane ruudukkopaneeli = new GridPane(40, 40);

        Text tunnus = new Text("Tunnus:");
        tunnus.setStyle("-fx-font-weight:bold;");
        tunnusKentta = new TextField(kenttienArvot[0]);
        ruudukkopaneeli.add(tunnus, 0,0);
        ruudukkopaneeli.add(tunnusKentta, 1,0);

        Text sijainti = new Text("Sijainti:");
        sijainti.setStyle("-fx-font-weight:bold;");
        sijaintiKentta = new TextField(kenttienArvot[1]);
        ruudukkopaneeli.add(sijainti, 0,1);
        ruudukkopaneeli.add(sijaintiKentta, 1,1);

        Text huoneita = new Text("Huoneita:");
        huoneita.setStyle("-fx-font-weight:bold;");
        huoneitaKentta = new TextField(kenttienArvot[2]);
        ruudukkopaneeli.add(huoneita, 0,2);
        ruudukkopaneeli.add(huoneitaKentta, 1,2);

        Text pintaAla = new Text("Pinta-ala:");
        pintaAla.setStyle("-fx-font-weight:bold;");
        pintaAlaKentta = new TextField(kenttienArvot[3].replaceAll("\\.", ","));
        ruudukkopaneeli.add(pintaAla, 2,0);
        ruudukkopaneeli.add(pintaAlaKentta, 3,0);

        Text hinta = new Text("Hinta:");
        hinta.setStyle("-fx-font-weight:bold;");
        hintaKentta = new TextField(kenttienArvot[4].replaceAll("\\.", ","));
        ruudukkopaneeli.add(hinta, 2,1);
        ruudukkopaneeli.add(hintaKentta, 3,1);

        if (!muokattavissa) {
            tunnusKentta.setEditable(false);
            tunnusKentta.setFocusTraversable(false);
            tunnusKentta.setBackground(Background.fill(Color.GAINSBORO));
            sijaintiKentta.setEditable(false);
            sijaintiKentta.setFocusTraversable(false);
            sijaintiKentta.setBackground(Background.fill(Color.GAINSBORO));
            huoneitaKentta.setEditable(false);
            huoneitaKentta.setFocusTraversable(false);
            huoneitaKentta.setBackground(Background.fill(Color.GAINSBORO));
            pintaAlaKentta.setEditable(false);
            pintaAlaKentta.setFocusTraversable(false);
            pintaAlaKentta.setBackground(Background.fill(Color.GAINSBORO));
            hintaKentta.setEditable(false);
            hintaKentta.setFocusTraversable(false);
            hintaKentta.setBackground(Background.fill(Color.GAINSBORO));
        }

        return ruudukkopaneeli;
    }

    private VBox luoHuomioitavaaPaneeli(String[] kenttienArvot, boolean muokattavissa) {
        VBox huomioitavaaPaneeli = new VBox(10);
        huomioitavaaPaneeli.setPadding(new Insets(30,0,0,0));

        Text otsikko = new Text("Huomioitavaa:");
        otsikko.setStyle("-fx-font-weight:bold;");
        huomioitavaaKentta = new TextArea(kenttienArvot[5]);
        huomioitavaaKentta.setWrapText(true);
        huomioitavaaKentta.setContextMenu(new ContextMenu());

        if (!muokattavissa) {
            huomioitavaaKentta.setEditable(false);
            huomioitavaaKentta.setFocusTraversable(false);
            huomioitavaaKentta.setStyle("-fx-control-inner-background:#DCDCDC;-fx-background-color:#DCDCDC;");
        }

        huomioitavaaPaneeli.getChildren().addAll(otsikko, huomioitavaaKentta);

        return huomioitavaaPaneeli;
    }

    public boolean naytaJaOdotaJaPalautaTulos() {
        this.showAndWait();
        return tulos;
    }

    public void asetaFonttikoko(int fonttikoko) {
        paapaneeli.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

    public String[] palautaKenttienTiedot() {
        return new String[] {tunnusKentta.getText(), sijaintiKentta.getText(), huoneitaKentta.getText(),
        pintaAlaKentta.getText(), hintaKentta.getText(), huomioitavaaKentta.getText()};
    }

}
