package mokki.mokki.gui.alipaneeli;

/**
 * <p>
 * Rajapinta sisältää metodimäärittelyt sellaisille luokille, jotka on tarkoitettu taulukkopaneeliin
 * syötettävän tiedon tyypiksi. Taulukkoon syötettävien kenttien tyypin tulee perustua StringProperty-
 * luokkaan. Sopivia tyyppejä ovat esimerkiksi SimpleStringProperty, SimpleIntegerProperty ja
 * SimpleDoubleProperty.
 * </p><p>
 * Jokaisella taulukkoon asetettavalla kentällä tulee luokassa olla seuraavat metodit:
 * setKentanNimi, getKentanNimi ja kentanNimiProperty. Alustajassa tulee käyttää setKentanNimi-metodia.
 * Alla on esimerkki näiden metodien määrittelystä tunnus-nimiselle kentälle, jonka tiedon tyyppi on String.
 * </p><p><pre>{@code
 * public String getTunnus() {
 *   return tunnusProperty().get();
 * }
 *
 * public void setTunnus(String tunnus) {
 *   tunnusProperty().set(tunnus);
 * }
 *
 * public StringProperty tunnusProperty() {
 *   if (tunnus == null) {
 *      tunnus = new SimpleStringProperty(this, maaritykset[0][2]);
 *   }
 *   return tunnus;
 * }
 * }</pre>
 */
public interface TaulukonData {
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

    /**
     * Metodi tarkistaa, ovatko arvot hyväksyttäviä luokan kenttien arvoiksi.
     * @param arvot arvoehdokkaat merkkijonoina
     * @return true, jos hyväskyttäviä; false muussa tapauksessa
     */
    boolean ovatkoArvotHyvaksyttavia(String[] arvot);

    /**
     * Metodi tarkistaa, mitkä arvot ovat hyväksyttäviä ja palauttaa totuusarvolistan tuloksista.
     * @param arvot arvoehdokkaat merkkijonoina
     * @return totuusarvolista: true, aina kun arvo on hyväksyttävä; false muussa tapauksessa
     */
    boolean[] mitkaArvotHyvaksyttavia(String[] arvot);

    /**
     * Metodi päivittää luokan kenttien arvot merkkijonolistan perusteella. Metodi varmistaa, että parametrina
     * oleva merkkijonolista on hyväksyttävä ennen muutoksen tekemistä.
     * @return true, jos muutos on tehty; false muussa tapauksessa
     */
    boolean paivitaKenttienArvot(String[] arvot);

    /**
     * Metodi tarkistaa, että tunniste-ehdokas ei ole vielä käytössä tietokannassa jonkin muun samantyyppisen
     * tietokokonaisuuden tunnisteena. Metodi ei tarkista tätä, jos nykyinen tunniste ja tunniste-ehdokas ovat
     * samat.
     * @return true, jos tunniste on uniikki tai jos tunniste-ehdokas on sama kuin nykyinen tunniste;
     * false muussa tapauksessa
     */
    boolean onkoTunnisteUniikki(String tunniste);

    /**
     * Metodi palauttaa tunnisteen indeksin. Indeksin avulla pitää pystyä noutamaan tunnisteen määritykset
     * ja tunnisteen arvo.
     * @return tunnisteen indeksi
     */
    int palautaTunnisteenIndeksi();
}
