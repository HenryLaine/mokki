package mokki.mokki.dao;

import mokki.mokki.gui.testiluokatTaulukonDatalle.KohteetWrapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Luokka luo toimii data access objectina Mokki-taulukkoon tietokannassa.
 * Luokkaa käytetään hakemaan tietoja ja kirjoittamaan tietoja tietokannasta ja tietokantaan.
 * Luokka ottaa parametrina KohteetWrapper olioita ja palauttaa tiedot KohteetWrapper oliona.
 */

public class MokkiDAO {
    private Connection conn;

    public MokkiDAO(Connection conn) {
        this.conn = conn;
    }

    public void lisaaMokki(KohteetWrapper kohteetWrapper) throws SQLException {
        String sql = "INSERT INTO Mokki (sijainti, hinta, huoneita, huoneala, henkilo_maara, huomioitavaa) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, kohteetWrapper.getSijainti());
            stmt.setDouble(2, kohteetWrapper.getHinta());
            stmt.setInt(3, kohteetWrapper.getHuoneita());
            stmt.setDouble(4, kohteetWrapper.getPintaAla());
            stmt.setInt(5, kohteetWrapper.getHenkilomaara());
            stmt.setString(6, kohteetWrapper.getHuomioitavaa());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    kohteetWrapper.setTunnus(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void poistaMokki(int mokkiID) throws SQLException {
        String sql = "DELETE FROM Mokki WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mokkiID);
            stmt.executeUpdate();
        }
    }

    public void muokkaaMokki(KohteetWrapper mokki) throws SQLException {
        KohteetWrapper vanhaMokki = haeMokki(Integer.valueOf(mokki.getTunnus()));
        if (vanhaMokki == null) {
            throw new SQLException("Mökkiä ei löydy ID:llä " + mokki.getTunnus());
        }

        String sql = "UPDATE Mokki SET sijainti = ?, hinta = ?, huoneita=?, huoneala = ?, henkilo_maara = ?, huomioitavaa = ? WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mokki.getSijainti() != null && !mokki.getSijainti().isEmpty() ? mokki.getSijainti() : vanhaMokki.getSijainti());
            stmt.setDouble(2, mokki.getHinta() != 0.0 ? mokki.getHinta() : vanhaMokki.getHinta());
            stmt.setInt(3, mokki.getHuoneita() != 0 ? mokki.getHuoneita() : vanhaMokki.getHuoneita());
            stmt.setDouble(4, mokki.getPintaAla() != 0 ? mokki.getPintaAla() : vanhaMokki.getPintaAla());
            stmt.setInt(5, mokki.getHenkilomaara() != 0 ? mokki.getHenkilomaara() : vanhaMokki.getHenkilomaara());
            stmt.setString(6, mokki.getHuomioitavaa() != null ? mokki.getHuomioitavaa() : vanhaMokki.getHuomioitavaa());
            stmt.setString(7, mokki.getTunnus());
            stmt.executeUpdate();
        }
    }

    public KohteetWrapper haeMokki(Integer mokkiID) throws SQLException {
        String sql = "SELECT * FROM Mokki WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mokkiID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new KohteetWrapper(
                            rs.getInt("mokkiID"),
                            rs.getString("sijainti"),
                            rs.getInt("huoneita"),
                            rs.getDouble("huoneala"),
                            rs.getDouble("hinta"),
                            rs.getInt("henkilo_maara"),
                            rs.getString("huomioitavaa")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public List<KohteetWrapper> haeMokit() throws SQLException {
        List<KohteetWrapper> mokit = new ArrayList<>();
        String sql = "SELECT * FROM Mokki";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                KohteetWrapper mokki = new KohteetWrapper(
                        rs.getInt("mokkiID"),
                        rs.getString("sijainti"),
                        rs.getInt("huoneita"),
                        rs.getDouble("hinta"),
                        rs.getDouble("huoneala"),
                        rs.getInt("henkilo_maara"),
                        rs.getString("huomioitavaa")
                );
                mokit.add(mokki);
            }
        }

        return mokit;
    }
    public List<KohteetWrapper> rajaaMokit(String hakusana) throws SQLException {
        List<KohteetWrapper> rajatutMokit = new ArrayList<>();

        String sql = "SELECT * FROM Mokki WHERE " +
                "MokkiID LIKE ? OR " +
                "henkilo_maara LIKE ? OR " +
                "sijainti LIKE ? OR " +
                "huomioitavaa LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String likeHakusana = "%" + hakusana + "%";
            stmt.setString(1, likeHakusana);
            stmt.setString(2, likeHakusana);
            stmt.setString(3, likeHakusana);
            stmt.setString(4, likeHakusana);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    KohteetWrapper mokki = new KohteetWrapper(
                            rs.getInt("mokkiID"),
                            rs.getString("sijainti"),
                            rs.getInt("Huoneita"),
                            rs.getDouble("hinta"),
                            rs.getDouble("huoneala"),
                            rs.getInt("henkilo_maara"),
                            rs.getString("huomioitavaa")
                    );
                    rajatutMokit.add(mokki);
                }
            }
        }

        return rajatutMokit;
    }
}