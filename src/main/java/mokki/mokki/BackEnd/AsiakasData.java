package mokki.mokki.BackEnd;


import mokki.mokki.gui.alipaneeli.TaulukonData;

// Leikin luokalla hieman ja tutustuin kuinka sen pitäisi toimia

public class AsiakasData implements TaulukonData {

    @Override
    public String[][] getMaaritykset() {
        return new String[0][];
    }

    @Override
    public String palautaTunniste() {
        return "";
    }

    @Override
    public String palautaKuvausteksti() {
        return "";
    }

    @Override
    public String[] palautaKenttienArvot() {
        return new String[0];
    }

    @Override
    public boolean ovatkoArvotHyvaksyttavia(String[] arvot) {
        return false;
    }

    @Override
    public boolean[] mitkaArvotHyvaksyttavia(String[] arvot) {
        return new boolean[0];
    }

    @Override
    public boolean paivitaKenttienArvot(String[] arvot) {
        return false;
    }

    @Override
    public boolean onkoTunnisteUniikki(String tunniste) {
        return false;
    }

    @Override
    public int palautaTunnisteenIndeksi() {
        return 0;
    }
}
