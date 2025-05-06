package mokki.mokki.gui.ponnahdusikkuna;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Luokka toteuttaa ikkunan, jolla pyydetään käyttäjän vahvistus jollekin toiminnolle.
 */
public class Vahvistusikkuna extends Alert {

    /**
     * Luokan alustaja, joka luo vahvistusikkunan.
     * @param otsikko ikkunan otsikko
     * @param vahvistusteksti ikkunassa näytettävä vahvistusteksti
     */
    public Vahvistusikkuna(String otsikko, String vahvistusteksti) {
        super(Alert.AlertType.CONFIRMATION,
                vahvistusteksti,
                new ButtonType("Kyllä", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Ei", ButtonBar.ButtonData.CANCEL_CLOSE));
        this.setTitle(otsikko);
        this.setHeaderText(otsikko);
    }
}
