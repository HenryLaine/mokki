package mokki.mokki.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/** tämä luokka lisää, muokkaa, poistaa ja tulostaa tietoja asiakastaulusta.
public class AsiakasDAO {
    private Connection conn;

    public AsiakasDAO(Connection conn) {
        this.conn = conn;
    }

    public void lisaaAsiakas(Asiakas asiakas) throws SQLException {
        String sql = "INSERT INTO Asiakas (sahkoposti, asiakastyyppi) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asiakas.getSahkoposti());
            stmt.setString(2, asiakas.getAsiakastyyppi());
            stmt.executeUpdate();
        }

        if ("yksityinen".equalsIgnoreCase(asiakas.getAsiakastyyppi())) {
            String sqlYksityinen = "INSERT INTO Yksityisasiakas (sahkoposti, nimi, osoite, puhelinnumero) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYksityinen)) {
                stmt.setString(1, asiakas.getSahkoposti());
                stmt.setString(2, asiakas.getNimi());
                stmt.setString(3, asiakas.getOsoite());
                stmt.setString(4, asiakas.getPuhelinnumero());
                stmt.executeUpdate();
            }
        } else if ("yritys".equalsIgnoreCase(asiakas.getAsiakastyyppi())) {
            String sqlYritys = "INSERT INTO Yritysasiakas (sahkoposti, nimi, osoite, y_tunnus) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYritys)) {
                stmt.setString(1, asiakas.getSahkoposti());
                stmt.setString(2, asiakas.getNimi());
                stmt.setString(3, asiakas.getOsoite());
                stmt.setString(4, asiakas.getYTunnus());
                stmt.executeUpdate();
            }
        }
    }

    public void poistaAsiakas(String sahkoposti) throws SQLException {
        String sql = "DELETE FROM Asiakas WHERE sahkoposti = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sahkoposti);
            stmt.executeUpdate();
        }
    }

    /** metodi asiakkaan tietojen muokkaamiseksi.
     *
     * @param asiakas
     * @throws SQLException

    public void muokkaaAsiakasta(Asiakas asiakas) throws SQLException {
        String sql = "UPDATE Asiakas SET asiakastyyppi = ? WHERE sahkoposti = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asiakas.getAsiakastyyppi());
            stmt.setString(2, asiakas.getSahkoposti());
            stmt.executeUpdate();
        }

        if ("yksityinen".equalsIgnoreCase(asiakas.getAsiakastyyppi())) {
            String sqlYksityinen = "UPDATE Yksityisasiakas SET nimi = ?, osoite = ?, puhelinnumero = ? WHERE sahkoposti = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYksityinen)) {
                stmt.setString(1, asiakas.getNimi());
                stmt.setString(2, asiakas.getOsoite());
                stmt.setString(3, asiakas.getPuhelinnumero());
                stmt.setString(4, asiakas.getSahkoposti());
                stmt.executeUpdate();
            }
        } else if ("yritys".equalsIgnoreCase(asiakas.getAsiakastyyppi())){
            String sqlYritys ="UPDATE Yritysasiakas SET nimi= ?, osoite = ?, y_tunnus = ? WHERE sahkoposti = ?";
            try(PreparedStatement stmt=conn.prepareStatement(sqlYritys)){
                stmt.setString(1, asiakas.getNimi());
                stmt.setString(2, asiakas,getOsoite());
                stmt.setString(3, asiakas.getYtunnus());
                stmt.setString(4, asiakas.getSahkoposti());
                stmt.executeUpdate();
            }
        }
    }

    /** Metodi palauttaa listauksen Asiakas-olioista
     *
     * @return
     * @throws SQLException

    public List<Asiakas> haeAsiakkaat() throws SQLException {
        List<Asiakas> asiakkaat = new ArrayList<>();

        String sql = "SELECT a.sahkoposti, a.asiakastyyppi, "
                + "y.nimi AS yksityis_nimi, y.osoite AS yksityis_osoite, y.puhelinnumero, "
                + "yr.nimi AS yritys_nimi, yr.osoite AS yritys_osoite, yr.y_tunnus "
                + "FROM Asiakas a "
                + "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti "
                + "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String sahkoposti = rs.getString("sahkoposti");
                String asiakastyyppi = rs.getString("asiakastyyppi");
                Asiakas asiakas;

                if ("yksityinen".equalsIgnoreCase(asiakastyyppi)) {
                    asiakas = new Asiakas(sahkoposti, asiakastyyppi,
                            rs.getString("yksityis_nimi"),
                            rs.getString("yksityis_osoite"));
                    asiakas.setPuhelinnumero(rs.getString("puhelinnumero"));
                } else {
                    asiakas = new Asiakas(sahkoposti, asiakastyyppi,
                            rs.getString("yritys_nimi"),
                            rs.getString("yritys_osoite"));
                    asiakas.setYTunnus(rs.getString("y_tunnus"));
                }

                asiakkaat.add(asiakas);
            }
        }
        return asiakkaat;
    }
}*/