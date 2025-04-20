package mokki.mokki.gui;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Valilehtipaneeli extends VBox {
    private FlowPane liukupaneeli = new FlowPane();
    private ArrayList<StackPane> pinopaneelilista = new ArrayList<>();
    private ArrayList<Rectangle> nelikulmiolista = new ArrayList<>();
    private ArrayList<Text> tekstilista = new ArrayList<>();
    private int aktiivinen = 0;
    private String aktiivinenTyyli = "-fx-fill:#6EC5FF;-fx-stroke:black;-fx-stroke-width:1px;";
    private String eiAktiivinenTyyli = "-fx-fill:#D5DEDC;-fx-stroke:black;-fx-stroke-width:1px;";

    public Valilehtipaneeli(String[] nimet, int leveys, int korkeus, int aktiivinen) {
        for (String nimi : nimet) {
            luoLehti(nimi, leveys, korkeus);
        }
        this.getChildren().add(liukupaneeli);
        paivitaLehdet();
        asetaTekstienTyyli("-fx-font-size:18px");
        this.setStyle("-fx-border-color:black;-fx-border-width:1px;-fx-background-color:#C4CCC9;");
        setAktiivinen(aktiivinen);
        asetaToiminnallisuus();
    }

    private void luoLehti(String nimi, int leveys, int korkeus) {
        StackPane paneeli = new StackPane();
        pinopaneelilista.add(paneeli);
        Rectangle nelikulmio = new Rectangle(leveys, korkeus);
        nelikulmiolista.add(nelikulmio);
        Text teksti = new Text(nimi);
        tekstilista.add(teksti);
        paneeli.getChildren().addAll(nelikulmio, teksti);
        liukupaneeli.getChildren().add(paneeli);
    }

    public ArrayList<StackPane> getPinopaneelilista() {
        return pinopaneelilista;
    }

    public ArrayList<Rectangle> getNelikulmiolista() {
        return nelikulmiolista;
    }

    public ArrayList<Text> getTekstilista() {
        return tekstilista;
    }

    public void setAktiivinenTyyli(String tyyli) {
        aktiivinenTyyli = tyyli;
        paivitaLehdet();
    }

    public void setEiAktiivinenTyyli(String tyyli) {
        eiAktiivinenTyyli = tyyli;
        paivitaLehdet();
    }

    private void paivitaLehdet() {
        for (int i = 0; i < nelikulmiolista.size(); i++) {
            Rectangle nelikulmio = nelikulmiolista.get(i);
            if (i == aktiivinen) {
                nelikulmio.setStyle(aktiivinenTyyli);
            }
            else {
                nelikulmio.setStyle(eiAktiivinenTyyli);
            }
        }
    }

    public void asetaTekstienTyyli(String tyyli) {
        for (Text teksti : tekstilista) {
            teksti.setStyle(tyyli);
        }
    }

    public void setAktiivinen(int indeksi) {
        if (indeksi >= 0 && indeksi < pinopaneelilista.size()) {
            aktiivinen = indeksi;
            paivitaLehdet();
        }
    }

    private void asetaToiminnallisuus() {
        for (int i = 0; i < pinopaneelilista.size(); i++) {
            StackPane paneeli = pinopaneelilista.get(i);
            Rectangle nelikulmio = nelikulmiolista.get(i);
                paneeli.setOnMouseEntered(e -> {
                    nelikulmio.setStyle(aktiivinenTyyli);
                });
            int finalI = i;
            paneeli.setOnMouseExited(e -> {
                    if (finalI != aktiivinen) {
                        nelikulmio.setStyle(eiAktiivinenTyyli);
                    }
                });
        }
    }

}
