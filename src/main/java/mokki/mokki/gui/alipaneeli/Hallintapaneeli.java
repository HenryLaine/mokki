package mokki.mokki.gui.alipaneeli;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * Luokka toteuttaa käyttöliittymän hallintapaneelin, jossa on napit lisäykselle, rajaukselle ja
 * rajausten poistolle. Lisäksi paneelissa on rajaukset ilmoittava teksti.
 */
public class Hallintapaneeli extends FlowPane {
    /** Lisää-painike */
    private Button lisaaPainike;
    /** Rajaa-painike */
    private Button rajaaPainike;
    /** Poista rajaukset -painike */
    private Button poistaRajauksetPainike;
    /** RAJAUKSET-teksti */
    private Text rajauksetTeksti;

    /**
     * Luokan alustaja.
     * @param painikkeidenNimet Painikkeiden nimet
     */
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

    /**
     * Metodi palauttaa Lisää-painikkeen.
     * @return Lisää-painike
     */
    public Button getLisaaPainike() {
        return lisaaPainike;
    }

    /**
     * Metodi palauttaa Rajaa-painikkeen.
     * @return Rajaa-painike
     */
    public Button getRajaaPainike() {
        return rajaaPainike;
    }

    /**
     * Metodi palauttaa Poista rajaukset -painikkeen
     * @return Poista rajaukset -painike
     */
    public Button getPoistaRajauksetPainike() {
        return poistaRajauksetPainike;
    }

    /**
     * Metodi palauttaa RAJAUKSET-tekstin
     * @return RAJAUKSET-teksti
     */
    public Text getRajauksetTeksti() {
        return rajauksetTeksti;
    }

    /**
     * Metodi muuttaa paneelin elementtien tekstin fonttikokoa.
     * @param fonttikoko fontin koko
     */
    public void asetaFonttikoko(int fonttikoko) {
        String tyyli = "-fx-font-size:" + fonttikoko + "px;";
        lisaaPainike.setStyle(tyyli);
        rajaaPainike.setStyle(tyyli);
        poistaRajauksetPainike.setStyle(tyyli);
        rajauksetTeksti.setStyle(tyyli);
    }

}
