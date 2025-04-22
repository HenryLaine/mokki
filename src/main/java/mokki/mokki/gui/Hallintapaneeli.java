package mokki.mokki.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class Hallintapaneeli extends FlowPane {
    private Button lisaaPainike;
    private Button rajaaPainike;
    private Button poistaRajauksetPainike;
    private Text rajauksetTeksti;

    public Hallintapaneeli(String[] painikkeidenNimet) {
        lisaaPainike = new Button(painikkeidenNimet[0]);
        rajaaPainike = new Button(painikkeidenNimet[1]);
        poistaRajauksetPainike = new Button(painikkeidenNimet[2]);
        rajauksetTeksti = new Text("RAJAUKSET:\t\t\t");
        this.setHgap(20);
        this.setVgap(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(lisaaPainike, rajaaPainike, poistaRajauksetPainike, rajauksetTeksti);
    }

    public Button getLisaaPainike() {
        return lisaaPainike;
    }

    public Button getRajaaPainike() {
        return rajaaPainike;
    }

    public Button getPoistaRajauksetPainike() {
        return poistaRajauksetPainike;
    }

    public Text getRajauksetTeksti() {
        return rajauksetTeksti;
    }

    public void asetaFonttikoko(int fonttikoko) {
        String tyyli = "-fx-font-size:" + fonttikoko + "px;";
        lisaaPainike.setStyle(tyyli);
        rajaaPainike.setStyle(tyyli);
        poistaRajauksetPainike.setStyle(tyyli);
        rajauksetTeksti.setStyle(tyyli);
    }

}
