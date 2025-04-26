package mokki.mokki.gui.ponnahdusikkuna;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mokki.mokki.gui.wrapper.TaulukkoWrapper;


public class KohteenTiedotIkkuna extends Stage {
    VBox paapaneeli;

    public KohteenTiedotIkkuna(TaulukkoWrapper tiedot) {
        String[] kenttienArvot = tiedot.palautaKenttienArvot();
        paapaneeli = new VBox(20);
        paapaneeli.setPadding(new Insets(20));
        GridPane ruudukkopaneeli = luoRuudukkopaneeli(kenttienArvot);
        VBox huomioitavaaPaneeli = luoHuomioitavaaPaneeli(kenttienArvot);
        Button suljePainike = new Button("Sulje");
        suljePainike.setOnAction(e -> {
            this.close();
        });
        paapaneeli.getChildren().addAll(ruudukkopaneeli, huomioitavaaPaneeli, suljePainike);

        Scene kehys = new Scene(paapaneeli);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Kohteen tiedot");
        this.setScene(kehys);
    }

    private GridPane luoRuudukkopaneeli(String[] kenttienArvot) {
        GridPane ruudukkopaneeli = new GridPane(40, 40);

        Text tunnus = new Text("Tunnus:");
        tunnus.setStyle("-fx-font-weight:bold;");
        Text tunnuksenArvo = new Text(kenttienArvot[0]);
        ruudukkopaneeli.add(tunnus, 0,0);
        ruudukkopaneeli.add(tunnuksenArvo, 1,0);

        Text sijainti = new Text("Sijainti:");
        sijainti.setStyle("-fx-font-weight:bold;");
        Text sijainninArvo = new Text(kenttienArvot[1]);
        ruudukkopaneeli.add(sijainti, 0,1);
        ruudukkopaneeli.add(sijainninArvo, 1,1);

        Text huoneita = new Text("Huoneita:");
        huoneita.setStyle("-fx-font-weight:bold;");
        Text huoneidenArvo = new Text(kenttienArvot[2]);
        ruudukkopaneeli.add(huoneita, 0,2);
        ruudukkopaneeli.add(huoneidenArvo, 1,2);

        Text pintaAla = new Text("Pinta-ala:");
        pintaAla.setStyle("-fx-font-weight:bold;");
        Text pintaAlanArvo = new Text(kenttienArvot[3].replaceAll("\\.", ","));
        ruudukkopaneeli.add(pintaAla, 2,0);
        ruudukkopaneeli.add(pintaAlanArvo, 3,0);

        Text hinta = new Text("Hinta:");
        hinta.setStyle("-fx-font-weight:bold;");
        Text hinnanArvo = new Text(kenttienArvot[4].replaceAll("\\.", ","));
        ruudukkopaneeli.add(hinta, 2,1);
        ruudukkopaneeli.add(hinnanArvo, 3,1);

        return ruudukkopaneeli;
    }

    private VBox luoHuomioitavaaPaneeli(String[] kenttienArvot) {
        VBox huomioitavaaPaneeli = new VBox(10);
        huomioitavaaPaneeli.setPadding(new Insets(30,0,0,0));

        Text otsikko = new Text("Huomioitavaa:");
        otsikko.setStyle("-fx-font-weight:bold;");
        TextArea tekstilaatikko = new TextArea(kenttienArvot[5]);
        tekstilaatikko.setEditable(false);
        tekstilaatikko.setFocusTraversable(false);
        tekstilaatikko.setWrapText(true);
        tekstilaatikko.setContextMenu(new ContextMenu());
        tekstilaatikko.setBackground(Background.fill(Color.GAINSBORO));

        huomioitavaaPaneeli.getChildren().addAll(otsikko, tekstilaatikko);

        return huomioitavaaPaneeli;
    }

    public void asetaFonttikoko(int fonttikoko) {
        paapaneeli.setStyle("-fx-font-size:" + fonttikoko + "px;");
    }

}
