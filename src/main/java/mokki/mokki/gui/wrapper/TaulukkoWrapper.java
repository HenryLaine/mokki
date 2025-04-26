package mokki.mokki.gui.wrapper;

/**
 *  * Rajapinta sisältää metodimäärittelyt sellaisille luokille, jotka on tarkoitettu taulukkopaneeliin
 *  * syötettävän tiedon tyypiksi.
 */
public interface TaulukkoWrapper {
    /**
     * Metodi palauttaa taulukkoon syötettävän tiedon määritykset. Määrityksiin sisällytetään
     * sarakkeen nimi (esim. "Asiakkaan puhelinnumero"), tiedon tyyppi (esim. "Integer") ja
     * luokassa olevan kentän nimi (esim. "asiakkaanPuhelinnumero").
     * Määritysten on oltava seuraavassa muodossa: {{"Sarakkeen nimi", "tiedonTyyppi", "kentanNimi"}, ...}.
     * @return taulukon tiedon määritykset
     */
    String[][] getMaaritykset();

    /**
     * Metodi palauttaa tietokokonaisuuden tunnisteen, joka yksilöi tietkokokonaisuuden eli taulukossa
     * näytettävän rivin. Tunnisteen tulee olla jokin luokan kentistä. Kentän arvo tulee muuntaa
     * merkkijonoksi.
     * @return tunniste
     */
    String palautaTunniste();

    /**
     * Metodi palauttaa merkkijonon, joka kuvaa tietokokonaisuutta eli taulukossa näytettävää riviä.
     * @return kuvausteksti
     */
    String palautaKuvausteksti();

    /**
     * Metodi palauttaa luokan jokaisen kentän arvon merkkijonolistana.
     * @return kenttien arvot
     */
    String[] palautaKenttienArvot();
}
