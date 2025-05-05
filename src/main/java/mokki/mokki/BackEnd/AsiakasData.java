package mokki.mokki.BackEnd;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mokki.mokki.gui.alipaneeli.TaulukonData;

// Leikin luokalla hieman ja tutustuin kuinka sen pitäisi toimia

public class AsiakasData implements TaulukonData {
    private StringProperty sahkoposti; // Asiakkaan sähköpostiosoite, toimii tunnisteena
    private StringProperty nimi; // Asiakkaan nimi
    private StringProperty osoite; // Asiakkaan osoite
    private StringProperty puhelinnumero; // Asiakkaan puhelinnumero
    private StringProperty tyyppi; // Asiakkaan tyyppi (esim. "yksityishenkilö" tai "yritys")
    private StringProperty ytunnus; // Y-tunnus, jos asiakas on yritys

    private static final String[][] MAARITYKSET = {
            {"Sähköposti (Tunniste)", "String", "sahkoposti"},
            {"Nimi", "String", "nimi"},
            {"Osoite", "String", "osoite"},
            {"Puhelinnumero", "String", "puhelinnumero"},
            {"Tyyppi", "String", "tyyppi"},
            {"Y-tunnus", "String", "ytunnus"}
    };

    public AsiakasData(String sahkoposti, String nimi, String osoite, String puhelinnumero, String tyyppi, String ytunnus) {
        this.sahkoposti = new SimpleStringProperty(sahkoposti);
        this.nimi = new SimpleStringProperty(nimi);
        this.osoite = new SimpleStringProperty(osoite);
        this.puhelinnumero = new SimpleStringProperty(puhelinnumero);
        this.tyyppi = new SimpleStringProperty(tyyppi);
        this.ytunnus = new SimpleStringProperty(ytunnus);
    }


    @Override
    public String[][] getMaaritykset() {
        return MAARITYKSET;
    }

    @Override
    public String palautaTunniste() {
        return sahkoposti.get();
    }


    @Override
    public String palautaKuvausteksti() {
        return "";
    }

    @Override
    public String[] palautaKenttienArvot() {
        return new String[] {sahkoposti.get(), nimi.get(), osoite.get(), puhelinnumero.get(), tyyppi.get(), ytunnus.get()};
    }

    @Override
    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        if (arvot.length != MAARITYKSET.length) {
            return false;
        }
        // Validointi: sähköpostin on sisällettävä '@', puhelinnumeron oltava numeerinen
        return arvot[0].contains("@") &&
                !arvot[1].isEmpty() &&
                !arvot[2].isEmpty() &&
                arvot[3].matches("\\d{6,15}") && // Puhelinnumeron oltava 6-15 numeroa
                (arvot[4].equals("yksityishenkilö") || arvot[4].equals("yritys")) &&
                (!arvot[4].equals("yritys") || !arvot[5].isEmpty());
    }

    @Override
    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        boolean[] tulos = new boolean[arvot.length];
        tulos[0] = arvot[0].contains("@"); // Sähköposti oltava validi
        tulos[1] = !arvot[1].isEmpty(); // Nimi ei saa olla tyhjä
        tulos[2] = !arvot[2].isEmpty(); // Osoite ei saa olla tyhjä
        tulos[3] = arvot[3].matches("\\d{6,15}"); // Puhelinnumeron oltava numeerinen ja pituus 6-15
        tulos[4] = arvot[4].equals("yksityishenkilö") || arvot[4].equals("yritys"); // Tyyppi hyväksyttävä
        tulos[5] = !arvot[4].equals("yritys") || !arvot[5].isEmpty(); // Y-tunnus vaaditaan vain yritykselle
        return tulos;
    }

    @Override
    public boolean paivitaKenttienArvot(String[] arvot) {
        if (ovatkoArvotHyvaksyttavia(arvot)) {
            sahkoposti.set(arvot[0]);
            nimi.set(arvot[1]);
            osoite.set(arvot[2]);
            puhelinnumero.set(arvot[3]);
            tyyppi.set(arvot[4]);
            ytunnus.set(arvot[5]);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onkoTunnisteUniikki(String tunniste) {
        return false; // pitäisi tehdä tietokanta kysely ja katsoa onko tunniste uniikki vai ei
    }

    @Override
    public int palautaTunnisteenIndeksi() {
        return 0;
    }

    // Getterit ja setterit
    public StringProperty sahkopostiProperty() {
        return sahkoposti;
    }

    public StringProperty nimiProperty() {
        return nimi;
    }

    public StringProperty osoiteProperty() {
        return osoite;
    }

    public StringProperty puhelinnumeroProperty() {
        return puhelinnumero;
    }

    public StringProperty tyyppiProperty() {
        return tyyppi;
    }

    public StringProperty ytunnusProperty() {
        return ytunnus;
    }
}