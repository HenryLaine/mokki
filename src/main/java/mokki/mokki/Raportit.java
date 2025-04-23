package mokki.mokki;
import mokki.mokki.database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/** Loin tämän luokan testitarkoituksessa, tätä ei ole tarkoitus jättää lopulliseen ohjelmaan.
 * Halusin harjoitella, että kuinka yhteys muodostetaan ja kuinka tietoja haetaan tietokannasta.
 */

public class Raportit {

    private Connection conn;

    public Raportit(Connection conn) {
        this.conn = conn;
    }


    // raportti kaikkien asiakkaiden ajamista varten

    public void haeKaikkiAsiakkaat() {
        String sql = "SELECT \n" +
                "    a.sahkoposti,\n" +
                "    a.asiakastyyppi,\n" +
                "    \n" +
                "    y.nimi AS yksityis_nimi,\n" +
                "    y.sahkoposti AS yksityis_sahkoposti,\n" +
                "    y.puhelinnumero AS yksityis_puhelinnumero,\n" +
                "    y.osoite AS yksityis_osoite,\n" +
                "    \n" +
                "    yr.nimi AS yritys_nimi,\n" +
                "    yr.y_tunnus,\n" +
                "    yr.sahkoposti AS yritys_sahkoposti,\n" +
                "    yr.osoite\n" +
                "\n" +
                "FROM Asiakas a\n" +
                "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti\n" +
                "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti;";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String sahkoposti = rs.getString("sahkoposti");
                String asiakastyyppi = rs.getString("asiakastyyppi");

                if ("yksityinen".equalsIgnoreCase(asiakastyyppi)) {
                    String nimi = rs.getString("yksityis_nimi");
                    String puhelin = rs.getString("yksityis_puhelinnumero");
                    String osoite=rs.getString("yksityis_osoite");
                    System.out.printf("Yksityisasiakas: %s, %s, %s, %s%n", nimi, sahkoposti, puhelin, osoite);
                } else if ("yritys".equalsIgnoreCase(asiakastyyppi)) {
                    String nimi = rs.getString("yritys_nimi");
                    String yTunnus = rs.getString("y_tunnus");
                    String osoite = rs.getString("osoite");
                    System.out.printf("Yritysasiakas: %s, %s, Y-tunnus: %s, Osoite: %s%n", nimi, sahkoposti, yTunnus, osoite);
                } else {
                    System.out.printf("Tuntematon asiakastyyppi: %s%n", sahkoposti);
                }
            }

        } catch (SQLException e) {
            System.err.println("Tietokantavirhe: " + e.getMessage());
        }
    }

    // metodi ajaa raportin mökeistä

    public void tulostaMokit(){
        String sql = "SELECT * FROM Mokki";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("MÖKIT:");
            while (rs.next()) {
                int id = rs.getInt("mokkiID");
                String sijainti = rs.getString("sijainti");
                double hinta = rs.getDouble("hinta");
                int huoneala = rs.getInt("huoneala");
                int henkiloMaara = rs.getInt("henkilo_maara");

                System.out.printf("ID: %d, Sijainti: %s, Hinta: %.2f, Huoneala: %d, Henkilömäärä: %d%n",
                        id, sijainti, hinta, huoneala, henkiloMaara);
            }
            System.out.println();

        }
        catch (SQLException e) {
            System.err.println("Tietokantavirhe: " + e.getMessage());
        }
    }

    // ajaa raportin kaikista varauksista

    public void tulostaVaraukset() {
        String sql = "SELECT * FROM Varaus";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("VARAUKSET:");
            while (rs.next()) {
                int id = rs.getInt("varaustunnus");
                Date aloitus = rs.getDate("aloitus_pvm");
                Date paattyminen = rs.getDate("paattymis_pvm");
                int henkilot = rs.getInt("henkilo_maara");
                String sahkoposti = rs.getString("sahkoposti");
                int mokkiID = rs.getInt("mokkiID");

                System.out.printf("ID: %d, Alku: %s, Loppu: %s, Henkilöitä: %d, Asiakas: %s, MökkiID: %d%n",
                        id, aloitus, paattyminen, henkilot, sahkoposti, mokkiID);
            }
            System.out.println();
        }
        catch (SQLException e) {
            System.err.println("Tietokantavirhe: " + e.getMessage());
        }
    }

    // ajaa raportin kaikista laskuista

    public void tulostaLaskut() {
        String sql = "SELECT * FROM Laskut";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("LASKUT:");
            while (rs.next()) {
                int id = rs.getInt("laskuID");
                double veroton = rs.getDouble("veroton_hinta");
                double alv = rs.getDouble("alv");
                Date paiva = rs.getDate("paivamaara");
                Date erapaiva = rs.getDate("erapaiva");
                String status = rs.getString("status");
                String sahkoposti = rs.getString("sahkoposti");
                String osoite = rs.getString("osoite");
                String nimi = rs.getString("nimi");

                System.out.printf("ID: %d, Veroton: %.2f, ALV: %.2f, Päivä: %s, Eräpäivä: %s, Status: %s, Asiakas: %s, Osoite: %s, Nimi: %s%n",
                        id, veroton, alv, paiva, erapaiva, status, sahkoposti, osoite, nimi);
            }
            System.out.println();
        }
        catch (SQLException e) {
            System.err.println("Tietokantavirhe: " + e.getMessage());
        }
    }

    /** testi-pääohjelma raporttien ajamista ja tietokantayhteyden
     * testaamista varten.
     * @param args
     */
    public static void main(String[] args) throws SQLException {

        Connection conn = DatabaseManager.getConnection();
        DatabaseInitializer.initializeDatabase();
        Raportit raportti=new Raportit(conn);
        raportti.haeKaikkiAsiakkaat();
        raportti.tulostaMokit();
        raportti.tulostaVaraukset();
        raportti.tulostaLaskut();
    }
}