package mokki.mokki.dao;
import mokki.mokki.BackEnd.Asiakas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** tämä luokka lisää, muokkaa, poistaa ja tulostaa tietoja asiakastaulusta.
*/
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

    // metodi asiakkaan tietojen muokkaamiseksi. Mikäli muokattava tieto on tyhjä,
    // jää vanha tieto voimaan.

    public void muokkaaAsiakasta(Asiakas asiakas) throws SQLException {
        // 1. Haetaan vanha asiakas
        Asiakas vanhaAsiakas = haeAsiakas(asiakas.getSahkoposti());
        if (vanhaAsiakas == null) {
            throw new SQLException("Asiakasta ei löydy sähköpostilla " + asiakas.getSahkoposti());
        }

        // 2. Päivitetään Asiakas-taulu
        String sql = "UPDATE Asiakas SET asiakastyyppi = ? WHERE sahkoposti = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asiakas.getAsiakastyyppi() != null ? asiakas.getAsiakastyyppi() : vanhaAsiakas.getAsiakastyyppi());
            stmt.setString(2, asiakas.getSahkoposti());
            stmt.executeUpdate();
        }

        // 3. Päivitetään joko yksityisasiakas tai yritysasiakas taulu
        if ("yksityinen".equalsIgnoreCase(asiakas.getAsiakastyyppi())) {
            String sqlYksityinen = "UPDATE Yksityisasiakas SET nimi = ?, osoite = ?, puhelinnumero = ? WHERE sahkoposti = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYksityinen)) {
                stmt.setString(1, asiakas.getNimi() != null && !asiakas.getNimi().isEmpty() ? asiakas.getNimi() : vanhaAsiakas.getNimi());
                stmt.setString(2, asiakas.getOsoite() != null && !asiakas.getOsoite().isEmpty() ? asiakas.getOsoite() : vanhaAsiakas.getOsoite());
                stmt.setString(3, asiakas.getPuhelinnumero() != null && !asiakas.getPuhelinnumero().isEmpty() ? asiakas.getPuhelinnumero() : vanhaAsiakas.getPuhelinnumero());
                stmt.setString(4, asiakas.getSahkoposti());
                stmt.executeUpdate();
            }
        } else if ("yritys".equalsIgnoreCase(asiakas.getAsiakastyyppi())) {
            String sqlYritys = "UPDATE Yritysasiakas SET nimi = ?, osoite = ?, y_tunnus = ? WHERE sahkoposti = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYritys)) {
                stmt.setString(1, asiakas.getNimi() != null && !asiakas.getNimi().isEmpty() ? asiakas.getNimi() : vanhaAsiakas.getNimi());
                stmt.setString(2, asiakas.getOsoite() != null && !asiakas.getOsoite().isEmpty() ? asiakas.getOsoite() : vanhaAsiakas.getOsoite());
                stmt.setString(3, asiakas.getYTunnus() != null && !asiakas.getYTunnus().isEmpty() ? asiakas.getYTunnus() : vanhaAsiakas.getYTunnus());
                stmt.setString(4, asiakas.getSahkoposti());
                stmt.executeUpdate();
            }
        }
    }
    // metodi palauttaa yhden asiakkaan sähköpostin perusteella
    public Asiakas haeAsiakas(String sahkoposti) throws SQLException {
        String sql = "SELECT a.*, " +
                "y.nimi AS yksityis_nimi, y.osoite AS yksityis_osoite, y.puhelinnumero, " +
                "yr.nimi AS yritys_nimi, yr.osoite AS yritys_osoite, yr.y_tunnus " +
                "FROM Asiakas a " +
                "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti " +
                "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti " +
                "WHERE a.sahkoposti = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sahkoposti);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String asiakastyyppi = rs.getString("asiakastyyppi");
                    Asiakas asiakas;

                    if ("yksityinen".equalsIgnoreCase(asiakastyyppi)) {
                        asiakas = new Asiakas(
                                rs.getString("sahkoposti"),
                                asiakastyyppi,
                                rs.getString("yksityis_nimi"),
                                rs.getString("yksityis_osoite")
                        );
                        asiakas.setPuhelinnumero(rs.getString("puhelinnumero"));
                    } else if ("yritys".equalsIgnoreCase(asiakastyyppi)) {
                        asiakas = new Asiakas(
                                rs.getString("sahkoposti"),
                                asiakastyyppi,
                                rs.getString("yritys_nimi"),
                                rs.getString("yritys_osoite")
                        );
                        asiakas.setYTunnus(rs.getString("y_tunnus"));
                    } else {
                        // Tuntematon asiakastyyppi
                        return null;
                    }
                    return asiakas;
                } else {
                    // Ei löytynyt asiakasta
                    return null;
                }
            }
        }
    }

    // Metodi palauttaa listauksen Asiakas-olioista

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
    // metodi rajaa asiakkaita hakusanojen perusteella

    public List<Asiakas> rajaaAsiakkaat(String hakusana) throws SQLException {
        List<Asiakas> asiakkaat = new ArrayList<>();

        String baseSql = "SELECT a.sahkoposti, a.asiakastyyppi, "
                + "y.nimi AS yksityis_nimi, y.osoite AS yksityis_osoite, y.puhelinnumero, "
                + "yr.nimi AS yritys_nimi, yr.osoite AS yritys_osoite, yr.y_tunnus "
                + "FROM Asiakas a "
                + "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti "
                + "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti ";

        List<String> sanat = new ArrayList<>();
        if (hakusana != null && !hakusana.trim().isEmpty()) {
            for (String sana : hakusana.toLowerCase().split("\\s+")) {
                if (!sana.isBlank()) {
                    sanat.add(sana);
                }
            }
        }

        StringBuilder whereClause = new StringBuilder();
        if (!sanat.isEmpty()) {
            whereClause.append("WHERE ");
            for (int i = 0; i < sanat.size(); i++) {
                if (i > 0) whereClause.append(" AND ");

                whereClause.append("(")
                        .append("LOWER(a.sahkoposti) LIKE ? OR ")
                        .append("LOWER(a.asiakastyyppi) LIKE ? OR ")
                        .append("LOWER(y.nimi) LIKE ? OR ")
                        .append("LOWER(y.puhelinnumero) LIKE ? OR ")
                        .append("LOWER(yr.nimi) LIKE ? OR ")
                        .append("LOWER(yr.y_tunnus) LIKE ?")
                        .append(")");
            }
        }

        String finalSql = baseSql + whereClause.toString();

        try (PreparedStatement stmt = conn.prepareStatement(finalSql)) {
            int paramIndex = 1;
            for (String sana : sanat) {
                String like = "%" + sana + "%";
                for (int j = 0; j < 6; j++) {
                    stmt.setString(paramIndex++, like);
                }
            }

            try (ResultSet rs = stmt.executeQuery()) {
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
        }
        return asiakkaat;
    }

}
