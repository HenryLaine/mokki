package mokki.mokki.dao;

import mokki.mokki.gui.testiluokatTaulukonDatalle.AsiakkaatWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsiakasDAO {
    private Connection conn;

    public AsiakasDAO(Connection conn) {
        this.conn = conn;
    }

    public void lisaaAsiakas(AsiakkaatWrapper a) throws SQLException {
        if (a.getSahkoposti() == null || a.getSahkoposti().trim().isEmpty()) {
            throw new IllegalArgumentException("Sähköpostiosoite ei saa olla tyhjä.");
        }

        String sql = "INSERT INTO Asiakas (sahkoposti, asiakastyyppi) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getSahkoposti());
            stmt.setString(2, a.getTyyppi());
            stmt.executeUpdate();
        }

        if ("yksityisasiakas".equalsIgnoreCase(a.getTyyppi())) {
            String sqlYks = "INSERT INTO Yksityisasiakas (sahkoposti, nimi, osoite, puhelinnumero) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYks)) {
                stmt.setString(1, a.getSahkoposti());
                stmt.setString(2, a.getNimi());
                stmt.setString(3, a.getOsoite());
                stmt.setString(4, a.getPuhelinnumero());
                stmt.executeUpdate();
            }
        } else if ("yritys".equalsIgnoreCase(a.getTyyppi())) {
            String sqlYrt = "INSERT INTO Yritysasiakas (sahkoposti, nimi, osoite, y_tunnus) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYrt)) {
                stmt.setString(1, a.getSahkoposti());
                stmt.setString(2, a.getNimi());
                stmt.setString(3, a.getOsoite());
                stmt.setString(4, a.getYtunnus());
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

    public void muokkaaAsiakasta(AsiakkaatWrapper uusi) throws SQLException {
        AsiakkaatWrapper vanha = haeAsiakas(uusi.getSahkoposti());
        if (vanha == null) {
            throw new SQLException("Asiakasta ei löydy sähköpostilla " + uusi.getSahkoposti());
        }

        String sql = "UPDATE Asiakas SET asiakastyyppi = ? WHERE sahkoposti = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, uusi.getTyyppi() != null ? uusi.getTyyppi() : vanha.getTyyppi());
            stmt.setString(2, uusi.getSahkoposti());
            stmt.executeUpdate();
        }

        if ("yksityisasiakas".equalsIgnoreCase(uusi.getTyyppi())) {
            String sqlYks = "UPDATE Yksityisasiakas SET nimi = ?, osoite = ?, puhelinnumero = ? WHERE sahkoposti = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYks)) {
                stmt.setString(1, valitseArvo(uusi.getNimi(), vanha.getNimi()));
                stmt.setString(2, valitseArvo(uusi.getOsoite(), vanha.getOsoite()));
                stmt.setString(3, valitseArvo(uusi.getPuhelinnumero(), vanha.getPuhelinnumero()));
                stmt.setString(4, uusi.getSahkoposti());
                stmt.executeUpdate();
            }
        } else if ("yritys".equalsIgnoreCase(uusi.getTyyppi())) {
            String sqlYrt = "UPDATE Yritysasiakas SET nimi = ?, osoite = ?, y_tunnus = ? WHERE sahkoposti = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlYrt)) {
                stmt.setString(1, valitseArvo(uusi.getNimi(), vanha.getNimi()));
                stmt.setString(2, valitseArvo(uusi.getOsoite(), vanha.getOsoite()));
                stmt.setString(3, valitseArvo(uusi.getYtunnus(), vanha.getYtunnus()));
                stmt.setString(4, uusi.getSahkoposti());
                stmt.executeUpdate();
            }
        }
    }

    private String valitseArvo(String uusi, String vanha) {
        return (uusi != null && !uusi.isEmpty()) ? uusi : vanha;
    }

    public AsiakkaatWrapper haeAsiakas(String sahkoposti) throws SQLException {
        String sql = "SELECT a.*, y.nimi AS yksityis_nimi, y.osoite AS yksityis_osoite, y.puhelinnumero, " +
                "yr.nimi AS yritys_nimi, yr.osoite AS yritys_osoite, yr.y_tunnus " +
                "FROM Asiakas a " +
                "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti " +
                "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti " +
                "WHERE a.sahkoposti = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sahkoposti);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tyyppi = rs.getString("asiakastyyppi");
                    if ("yksityisasiakas".equalsIgnoreCase(tyyppi)) {
                        AsiakkaatWrapper a = new AsiakkaatWrapper(
                                sahkoposti,
                                tyyppi,
                                rs.getString("yksityis_nimi"),
                                rs.getString("puhelinnumero"),
                                rs.getString("yksityis_osoite"),
                                null
                        );
                        return a;
                    } else if ("yritys".equalsIgnoreCase(tyyppi)) {
                        AsiakkaatWrapper a = new AsiakkaatWrapper(
                                sahkoposti,
                                tyyppi,
                                rs.getString("yritys_nimi"),
                                null,
                                rs.getString("yritys_osoite"),
                                rs.getString("y_tunnus")
                        );
                        return a;
                    }
                }
                return null;
            }
        }
    }

    public List<AsiakkaatWrapper> haeAsiakkaat() throws SQLException {
        List<AsiakkaatWrapper> lista = new ArrayList<>();
        String sql = "SELECT a.*, y.nimi AS yksityis_nimi, y.osoite AS yksityis_osoite, y.puhelinnumero, " +
                "yr.nimi AS yritys_nimi, yr.osoite AS yritys_osoite, yr.y_tunnus " +
                "FROM Asiakas a " +
                "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti " +
                "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String sahkoposti = rs.getString("sahkoposti");
                String tyyppi = rs.getString("asiakastyyppi");

                if ("yksityisasiakas".equalsIgnoreCase(tyyppi)) {
                    lista.add(new AsiakkaatWrapper(
                            sahkoposti, tyyppi,
                            rs.getString("yksityis_nimi"),
                            rs.getString("puhelinnumero"),
                            rs.getString("yksityis_osoite"),
                            null
                    ));
                } else if ("yritys".equalsIgnoreCase(tyyppi)) {
                    lista.add(new AsiakkaatWrapper(
                            sahkoposti, tyyppi,
                            rs.getString("yritys_nimi"),
                            null,
                            rs.getString("yritys_osoite"),
                            rs.getString("y_tunnus")
                    ));
                }
            }
        }
        return lista;
    }

    public List<AsiakkaatWrapper> rajaaAsiakkaat(String hakusana) throws SQLException {
        List<AsiakkaatWrapper> lista = new ArrayList<>();
        StringBuilder where = new StringBuilder();
        List<String> sanat = new ArrayList<>();

        if (hakusana != null && !hakusana.isBlank()) {
            for (String s : hakusana.toLowerCase().split("\\s+")) {
                if (!s.isBlank()) sanat.add(s);
            }
        }

        if (!sanat.isEmpty()) {
            where.append("WHERE ");
            for (int i = 0; i < sanat.size(); i++) {
                if (i > 0) where.append(" AND ");
                where.append("(LOWER(a.sahkoposti) LIKE ? OR LOWER(a.asiakastyyppi) LIKE ? " +
                        "OR LOWER(y.nimi) LIKE ? OR LOWER(y.puhelinnumero) LIKE ? " +
                        "OR LOWER(yr.nimi) LIKE ? OR LOWER(yr.y_tunnus) LIKE ?)");
            }
        }

        String sql = "SELECT a.*, y.nimi AS yksityis_nimi, y.osoite AS yksityis_osoite, y.puhelinnumero, " +
                "yr.nimi AS yritys_nimi, yr.osoite AS yritys_osoite, yr.y_tunnus " +
                "FROM Asiakas a " +
                "LEFT JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti " +
                "LEFT JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti " + where.toString();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int idx = 1;
            for (String sana : sanat) {
                String like = "%" + sana + "%";
                for (int j = 0; j < 6; j++) stmt.setString(idx++, like);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String sahkoposti = rs.getString("sahkoposti");
                    String tyyppi = rs.getString("asiakastyyppi");

                    if ("yksityisasiakas".equalsIgnoreCase(tyyppi)) {
                        lista.add(new AsiakkaatWrapper(
                                sahkoposti, tyyppi,
                                rs.getString("yksityis_nimi"),
                                rs.getString("puhelinnumero"),
                                rs.getString("yksityis_osoite"),
                                null
                        ));
                    } else if ("yritys".equalsIgnoreCase(tyyppi)) {
                        lista.add(new AsiakkaatWrapper(
                                sahkoposti, tyyppi,
                                rs.getString("yritys_nimi"),
                                null,
                                rs.getString("yritys_osoite"),
                                rs.getString("y_tunnus")
                        ));
                    }
                }
            }
        }
        return lista;
    }
}
