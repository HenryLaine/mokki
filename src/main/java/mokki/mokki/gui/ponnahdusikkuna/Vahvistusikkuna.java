package mokki.mokki.gui.ponnahdusikkuna;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Vahvistusikkuna extends Alert {

    public Vahvistusikkuna(String otsikko, String vahvistusteksti) {
        super(Alert.AlertType.CONFIRMATION,
                vahvistusteksti,
                new ButtonType("Kyllä", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Ei", ButtonBar.ButtonData.CANCEL_CLOSE));
        this.setTitle(otsikko);
        this.setHeaderText(otsikko);
    }
}
