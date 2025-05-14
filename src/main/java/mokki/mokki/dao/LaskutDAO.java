package mokki.mokki.dao;


import mokki.mokki.gui.TaulukkoWrapper.LaskutWrapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Tietokantayhteyden ottaminen ja laskuihin liittyvien sql kyselyiden tekeminen.
 * Luokka lisää laskuja, muokkaa laskuja, poistaa laskuja.
*/

public class LaskutDAO {
    private Connection conn;

    public LaskutDAO(Connection conn) {
        this.conn = conn;
    }


    // tämä blokki tässä käyttää laskutwrapper luokkaa ja tämä siis lisää laskun
    public void lisaaLasku(LaskutWrapper lasku)throws SQLException{
        String sql = "INSERT INTO Laskut (laskuID, varaustunnus, asiakas, viitenumero, maksettava, status, veroton_hinta, " +
                "alv, paivamaara, erapaiva, osoite, nimi, sahkoposti) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1,lasku.getLaskunumero());
            stmt.setInt(2,lasku.getVaraus());
            stmt.setString(3,lasku.getAsiakas());
            stmt.setInt(4,lasku.getViitenumero());
            stmt.setDouble(5,lasku.getMaksettava());
            stmt.setString(6,lasku.getTila());
            stmt.setDouble(7, lasku.getVerotonHinta());
            stmt.setDouble(8, lasku.getAlv());
            stmt.setDate(9, java.sql.Date.valueOf(lasku.getPaivamaara()));
            stmt.setDate(10, java.sql.Date.valueOf(lasku.getEraPaiva()));
            stmt.setString(11, lasku.getOsoite());
            stmt.setString(12, lasku.getNimi());
            stmt.setString(13, lasku.getSahkposti());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lasku.setLaskunumero(generatedKeys.getInt(1)); // Päivitä olioon ID
                }
            }

        }
    }

    // Koko laskun muokkaaminen
    public void muokkaaLaskua(LaskutWrapper lasku) throws SQLException {
        LaskutWrapper vanhaLasku = haeLasku(lasku.getLaskunumero());

        Date laskunPaivamaara = Date.valueOf(lasku.getPaivamaara());
        if (vanhaLasku == null) {
            throw new SQLException("Laskua ei löydy laskuID:llä " + lasku.getLaskunumero());
        }

        String sql = "UPDATE Laskut SET veroton_hinta = ?, alv = ?, paivamaara = ?, erapaiva = ?, status = ?, " +
                "sahkoposti = ?, osoite = ?, nimi = ?, varaustunnus = ? WHERE laskuID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, lasku.getVerotonHinta() != 0.0 ? lasku.getVerotonHinta() : vanhaLasku.getVerotonHinta());
            stmt.setDouble(2, lasku.getAlv() != 0.0 ? lasku.getAlv() : vanhaLasku.getAlv());
            stmt.setDate(3, laskunPaivamaara != null ? laskunPaivamaara : laskunPaivamaara);
            stmt.setDate(4, laskunPaivamaara != null ? laskunPaivamaara : laskunPaivamaara);
            stmt.setString(5, lasku.getTila() != null ? lasku.getTila() : vanhaLasku.getTila());
            stmt.setString(6, lasku.getSahkposti() != null ? lasku.getSahkposti() : vanhaLasku.getSahkposti());
            stmt.setString(7, lasku.getOsoite() != null ? lasku.getOsoite() : vanhaLasku.getOsoite());
            stmt.setString(8, lasku.getNimi() != null ? lasku.getNimi() : vanhaLasku.getNimi());
            stmt.setInt(9, lasku.getVaraus() != 0 ? lasku.getVaraus() : vanhaLasku.getVaraus());
            stmt.setInt(10, lasku.getLaskunumero());
            stmt.executeUpdate();
        }
    }
    //hae vain yksi lasku
    public LaskutWrapper haeLasku(int laskutunnus) throws SQLException {
        String sql = "SELECT * FROM Laskut WHERE laskuID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, laskutunnus);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LaskutWrapper lasku = new LaskutWrapper(
                            rs.getInt("laskuID"),
                            rs.getInt("varaustunnus"),
                            rs.getString("asiakas"),
                            rs.getInt("viitenumero"),
                            rs.getDouble("maksettava"),
                            rs.getDouble("veroton_hinta"),
                            rs.getDouble("alv"),
                            rs.getDate("paivamaara").toLocalDate(),
                            rs.getDate("erapaiva").toLocalDate(),
                            rs.getString("sahkoposti"),
                            rs.getString("osoite"),
                            rs.getString("nimi"),
                            rs.getString("status")
                    );
                    return lasku;
                } else {
                    return null;
                }
            }
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

    // hakee kaikki laskut ja sopii laskutwrapper kanssa
    public List<LaskutWrapper> haeKaikkiLaskut()throws SQLException{
        List<LaskutWrapper> lista = new ArrayList<>();
        String sql = "SELECT * FROM Laskut";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LaskutWrapper lasku = new LaskutWrapper(
                        rs.getInt("laskuID"), // ok
                        rs.getInt("varaustunnus"), // Löytyy varaustunnuksena, pitäisi olla int
                        rs.getString("asiakas"), // ei ole tietokannassa,
                        rs.getInt("viitenumero"), // ok
                        rs.getDouble("maksettava"), // ei löydy tietokannassa
                        rs.getDouble("veroton_hinta"), // ok
                        rs.getDouble("alv"), // ok
                        rs.getDate("paivamaara").toLocalDate(), // ok
                        rs.getDate("erapaiva").toLocalDate(), // ok
                        rs.getString("sahkoposti"), // ok
                        rs.getString("osoite"), // ok
                        rs.getString("nimi"), //ok
                        rs.getString("status") //ok
                );
                        lista.add(lasku);
            }

        }
        return lista;
    }
    public List<LaskutWrapper> rajaaLaskut(String hakusana) throws SQLException {
        List<LaskutWrapper> rajatutLaskut = new ArrayList<>();

        String sql = "SELECT * FROM Laskut WHERE " +
                "CAST(laskuID AS CHAR) LIKE ? OR " +
                "CAST(varaustunnus AS CHAR) LIKE ? OR " +
                "nimi LIKE ? OR " +
                "sahkoposti LIKE ? OR " +
                "osoite LIKE ? OR " +
                "status LIKE ? OR " +
                "CAST(veroton_hinta AS CHAR) LIKE ? OR " +
                "CAST(alv AS CHAR) LIKE ? OR " +
                "CAST(paivamaara AS CHAR) LIKE ? OR " +
                "CAST(erapaiva AS CHAR) LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String likeHakusana = "%" + hakusana + "%";
            for (int i = 1; i <= 10; i++) {
                stmt.setString(i, likeHakusana);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int laskuID = rs.getInt("laskuID");
                    int varaustunnus = rs.getInt("varaustunnus");
                    String nimi = rs.getString("nimi");
                    String sahkoposti = rs.getString("sahkoposti");
                    String osoite = rs.getString("osoite");
                    String tila = rs.getString("status");
                    double verotonHinta = rs.getDouble("veroton_hinta");
                    double alv = rs.getDouble("alv");
                    LocalDate paivamaara = rs.getDate("paivamaara").toLocalDate();
                    LocalDate erapaiva = rs.getDate("erapaiva").toLocalDate();
                    int viitenumero = laskuID; // Oletetaan että laskuID toimii viitenumerona tässä
                    double maksettava = verotonHinta + verotonHinta * 0.1; // ALV lasketaan wrapperissa uudelleen

                    LaskutWrapper wrapper = new LaskutWrapper(
                            laskuID,
                            varaustunnus,
                            nimi,
                            viitenumero,
                            maksettava,
                            verotonHinta,
                            alv,
                            paivamaara,
                            erapaiva,
                            sahkoposti,
                            osoite,
                            nimi,
                            tila
                    );

                    rajatutLaskut.add(wrapper);
                }
            }
        }

        return rajatutLaskut;
    }

    public void paivitaLaskunStatus(int laskuID, String uusiStatus) throws SQLException {
        String sql = "UPDATE Laskut SET status = ? WHERE laskuID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, uusiStatus);
            stmt.setInt(2, laskuID);
            stmt.executeUpdate();
        }
    }
}
