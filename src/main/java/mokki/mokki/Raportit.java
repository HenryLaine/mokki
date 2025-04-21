package mokki.mokki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Raportit {

    public void haeKaikkiAsiakkaat() {
        String sql = "SELECT \n" +
                "    a.sahkoposti,\n" +
                "    a.asiakastyyppi,\n" +
                "    \n" +
                "    y.nimi AS yksityis_nimi,\n" +
                "    y.sahkoposti AS yksityis_sahkoposti,\n" +
                "    y.puhelinnumero AS yksityis_puhelinnumero,\n" +
                "    \n" +
                "    yr.nimi AS yritys_nimi,\n" +
                "    yr.y_tunnus,\n" +
                "    yr.sahkoposti AS yritys_sahkoposti,\n" +
                "    yr.osoite\n" +
                "\n" +
                "FROM Asiakas a\n" +
                "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti\n" +
                "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti;";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String sahkoposti = rs.getString("sahkoposti");
                String asiakastyyppi = rs.getString("asiakastyyppi");

                if ("yksityinen".equalsIgnoreCase(asiakastyyppi)) {
                    String nimi = rs.getString("yksityis_nimi");
                    String puhelin = rs.getString("yksityis_puhelinnumero");
                    System.out.printf("Yksityisasiakas: %s, %s, %s%n", nimi, sahkoposti, puhelin);
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
    public static void main(String[] args) {
        Raportit raportti = new Raportit();
        raportti.haeKaikkiAsiakkaat();
    }
}