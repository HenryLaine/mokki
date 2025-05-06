package mokki.mokki.gui.ponnahdusikkuna;

import javafx.scene.control.Alert;

/**
 * Luokka toteuttaa ikkunan, joka näytetään käyttäjälle virhetilanteissa.
 */
public class Virheikkuna extends Alert {

    /**
     * Luokan alustaja, joka luo virheikkunan
     * @param otsikko ikkunan otikko
     * @param huomautusteksti ikkunassa näytettävä huomautusteksti
     */
    public Virheikkuna(String otsikko, String huomautusteksti) {
        super(AlertType.ERROR);
        this.setTitle(otsikko);
        this.setContentText(huomautusteksti);
        this.setHeaderText(otsikko);
    }
}
