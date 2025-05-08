package mokki.mokki.dao;
import mokki.mokki.BackEnd.Lasku;
import mokki.mokki.BackEnd.Varaus;
import mokki.mokki.gui.testiluokatTaulukonDatalle.LaskutWrapper;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Tietokantayhteyden ottaminen ja laskuihin liittyvien sql kyselyiden tekeminen.
 * Luokka lisää laskuja, muokkaa laskuja, poistaa laskuja sekä raportoi laskuja.
*/


/* Lisäilin metodeja jotka sopivat laskutwrapperin kanssa, koska vanhat näyttää siltä, että
ne käyttävät lasku luokka
*/

public class LaskutDAO {
    private Connection conn;

    public LaskutDAO(Connection conn) {
        this.conn = conn;
    }


    // tämä blokki tässä käyttää laskutwrapper luokkaa ja tämä siis lisää laskun
    public void lisaaLasku(LaskutWrapper lasku)throws SQLException{
        String sql = "INSERT INTO Laskut(laskuID, tuote, asiakas, viitenumero, maksettava,status) VALUES(?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,lasku.getLaskunumero());
            stmt.setString(2,lasku.getTuote());
            stmt.setString(3,lasku.getAsiakas());
            stmt.setInt(4,lasku.getViitenumero());
            stmt.setDouble(5,lasku.getMaksettava());
            stmt.setString(6,lasku.getTila());
            stmt.executeUpdate();
        }
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

    // sopii wrapperluokan kanssa ja muokkaa laskua
    public void muokkaaLaskua(LaskutWrapper lasku) throws SQLException {
        String sql = "UPDATE Laskut SET tuote = ?, asiakas = ?, viitenumero = ?, maksettava = ?, status = ? WHERE laskuID = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lasku.getTuote());
            stmt.setString(2,lasku.getAsiakas());
            stmt.setInt(3,lasku.getViitenumero());
            stmt.setDouble(4,lasku.getMaksettava());
            stmt.setString(5, lasku.getTila());
            stmt.setInt(6,lasku.getLaskunumero());
            stmt.executeUpdate();

        }
    }




    // Koko laskun muokkaaminen
    public void muokkaaLaskua(Lasku lasku) throws SQLException {
        Lasku vanhaLasku = haeLasku(lasku.getLaskunumero());
        if (vanhaLasku == null) {
            throw new SQLException("Laskua ei löydy laskuID:llä " + lasku.getLaskunumero());
        }

        String sql = "UPDATE Laskut SET veroton_hinta = ?, alv = ?, paivamaara = ?, erapaiva = ?, status = ?, " +
                "sahkoposti = ?, osoite = ?, nimi = ? WHERE laskuID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, lasku.getVerotonHinta() != 0.0 ? lasku.getVerotonHinta() : vanhaLasku.getVerotonHinta());
            stmt.setDouble(2, lasku.getAlv() != 0.0 ? lasku.getAlv() : vanhaLasku.getAlv());
            stmt.setDate(3, lasku.getPaivamaara() != null ? lasku.getPaivamaara() : vanhaLasku.getPaivamaara());
            stmt.setDate(4, lasku.getErapaiva() != null ? lasku.getErapaiva() : vanhaLasku.getErapaiva());
            stmt.setString(5, lasku.getStatus() != null ? lasku.getStatus() : vanhaLasku.getStatus());
            stmt.setString(6, lasku.getSahkoposti() != null ? lasku.getSahkoposti() : vanhaLasku.getSahkoposti());
            stmt.setString(7, lasku.getOsoite() != null ? lasku.getOsoite() : vanhaLasku.getOsoite());
            stmt.setString(8, lasku.getNimi() != null ? lasku.getNimi() : vanhaLasku.getNimi());
            stmt.setInt(9, lasku.getLaskuID());
            stmt.executeUpdate();
        }
    }
    //hae vain yksi lasku
    public Lasku haeLasku(int laskuID) throws SQLException {
        String sql = "SELECT * FROM Laskut WHERE laskuID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, laskuID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Lasku lasku = new Lasku(
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
                    return lasku;
                } else {
                    return null;
                }
            }
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




    // hakee kaikki laskut ja sopii laskutwrapper kanssa
    public List<LaskutWrapper> haeKaikkiLaskut()throws SQLException{
        List<LaskutWrapper> lista = new ArrayList<>();
        String sql = "SELECT * FROM Laskut";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LaskutWrapper lasku = new LaskutWrapper(
                        rs.getInt("laskuID"),
                        rs.getString("tuote"),
                        rs.getString("asiakas"),
                        rs.getInt("viitenumero"),
                        rs.getDouble("maksettava"),
                        rs.getString("status"));
                lista.add(lasku);
            }

        }
        return lista;
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
