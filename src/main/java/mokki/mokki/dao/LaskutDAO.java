package mokki.mokki.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tietokantayhteyden ottaminen ja laskuihin liittyvien sql kyselyiden tekeminen.
 * Luokka lisää laskuja, muokkaa laskuja, poistaa laskuja sekä raportoi laskuja.

public class LaskutDAO {
    private Connection conn;

    public LaskutDAO(Connection conn) {
        this.conn = conn;
    }

    // Uuden laskun luominen
    public void lisaaLasku(Varaus varaus, String status) throws SQLException {
        String asiakasSql = "SELECT nimi, osoite FROM Asiakas WHERE sahkoposti = ?";
        String mokkiSql = "SELECT hinta FROM Mokki WHERE mokkiID = ?";

        try (
                PreparedStatement asiakasStmt = conn.prepareStatement(asiakasSql);
                PreparedStatement mokkiStmt = conn.prepareStatement(mokkiSql)
        ) {
            // Haetaan asiakkaan nimi ja osoite
            asiakasStmt.setString(1, varaus.getSahkoposti());
            ResultSet asiakasRs = asiakasStmt.executeQuery();

            if (!asiakasRs.next()) {
                throw new SQLException("Asiakasta ei löytynyt sähköpostilla: " + varaus.getSahkoposti());
            }

            String nimi = asiakasRs.getString("nimi");
            String osoite = asiakasRs.getString("osoite");

            // Haetaan mökin hinta
            mokkiStmt.setInt(1, varaus.getMokkiID());
            ResultSet mokkiRs = mokkiStmt.executeQuery();

            if (!mokkiRs.next()) {
                throw new SQLException("Mökkiä ei löytynyt ID:llä: " + varaus.getMokkiID());
            }

            double hintaPerYo = mokkiRs.getDouble("hinta");

            // Lasketaan yöpymisten määrä
            long yoMaara = ChronoUnit.DAYS.between(
                    varaus.getAloitusPvm().toLocalDate(),
                    varaus.getPaattymisPvm().toLocalDate()
            );

            double verotonHinta = yoMaara * hintaPerYo;
            double alv = verotonHinta * 0.10;

            Date paivamaara = Date.valueOf(LocalDate.now());
            Date erapaiva = Date.valueOf(LocalDate.now().plusDays(30));

            String insertSql = "INSERT INTO Laskut (veroton_hinta, alv, paivamaara, erapaiva, status, sahkoposti, osoite, nimi, varaustunnus) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setDouble(1, verotonHinta);
                insertStmt.setDouble(2, alv);
                insertStmt.setDate(3, paivamaara);
                insertStmt.setDate(4, erapaiva);
                insertStmt.setString(5, status);
                insertStmt.setString(6, varaus.getSahkoposti());
                insertStmt.setString(7, osoite);
                insertStmt.setString(8, nimi);
                insertStmt.setInt(9, varaus.getVaraustunnus());
                insertStmt.executeUpdate();
            }
        }
    }

    // Koko laskun muokkaaminen
    public void muokkaaLaskua(Lasku lasku) throws SQLException {
        String sql = "UPDATE Laskut SET veroton_hinta = ?, alv = ?, paivamaara = ?, erapaiva = ?, status = ?, " +
                "sahkoposti = ?, osoite = ?, nimi = ? WHERE laskuID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, lasku.getVerotonHinta());
            stmt.setDouble(2, lasku.getAlv());
            stmt.setDate(3, lasku.getPaivamaara());
            stmt.setDate(4, lasku.getErapaiva());
            stmt.setString(5, lasku.getStatus());
            stmt.setString(6, lasku.getSahkoposti());
            stmt.setString(7, lasku.getOsoite());
            stmt.setString(8, lasku.getNimi());
            stmt.setInt(9, lasku.getLaskuID());
            stmt.executeUpdate();
        }
    }

    // Laskun statuksen päivittäminen
    public void paivitaLaskunStatus(int laskuID, String uusiStatus) throws SQLException {
        String sql = "UPDATE Laskut SET status = ? WHERE laskuID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, uusiStatus);
            stmt.setInt(2, laskuID);
            stmt.executeUpdate();
        }
    }

    // Laskun poistaminen
    public void poistaLasku(int laskuID) throws SQLException {
        String sql = "DELETE FROM Laskut WHERE laskuID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, laskuID);
            stmt.executeUpdate();
        }
    }

    // Laskujen raportointi
    public List<Lasku> haeKaikkiLaskut() throws SQLException {
        String sql = "SELECT * FROM Laskut";
        List<Lasku> laskut = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                laskut.add(ResultSetToLasku(rs));
            }
        }
        return laskut;
    }

    // Laskujen hakminen statuksen mukaan
    public List<Lasku> haeLaskutStatuksella(String status) throws SQLException {
        String sql = "SELECT * FROM Laskut WHERE status = ?";
        List<Lasku> laskut = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    laskut.add(ResultSetToLasku(rs));
                }
            }
        }
        return laskut;
    }

    // Apumetodi: ResultSet -> Lasku
    private Lasku ResultSetToLasku(ResultSet rs) throws SQLException {
        return new Lasku(
                rs.getInt("laskuID"),
                rs.getDouble("veroton_hinta"),
                rs.getDouble("alv"),
                rs.getDate("paivamaara"),
                rs.getDate("erapaiva"),
                rs.getString("status"),
                rs.getString("sahkoposti"),
                rs.getString("osoite"),
                rs.getString("nimi")
        );
    }
}
*/