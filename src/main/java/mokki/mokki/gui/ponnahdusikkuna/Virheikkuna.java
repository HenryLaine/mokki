package mokki.mokki.gui.ponnahdusikkuna;

import javafx.scene.control.Alert;

public class Virheikkuna extends Alert {

    public Virheikkuna(String otsikko, String huomautusteksti) {
        super(AlertType.ERROR);
        this.setTitle(otsikko);
        this.setContentText(huomautusteksti);
        this.setHeaderText(otsikko);
    }
}
