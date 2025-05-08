package mokki.mokki.dao;

import mokki.mokki.BackEnd.Mokki;
import mokki.mokki.gui.testiluokatTaulukonDatalle.KohteetWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Yritin muokata tätä AsiakasDAO:n kaltaiseksi ja käyttämällä KohteetWrapperin tietoja, en ole vielä kerennyt katsoa, miten hyvin toimii
// - Antti

public class MokkiDAO {
    private Connection conn;

    public MokkiDAO(Connection conn) {
        this.conn = conn;
    }

    public void lisaaMokki(KohteetWrapper k) throws SQLException {
        if (k.getTunnus() == null || k.getTunnus().trim().isEmpty())
        {
            throw  new IllegalArgumentException("Mökkitunnus ei saa olla tyhjä.");
        }

        String sql = "INSERT INTO Mokki (tunnus, sijainti, hinta, huoneala, henkilo_maara, huomioitavaa) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, k.getTunnus());
            stmt.setString(2, k.getSijainti());
            stmt.setDouble(3, k.getHinta());
            stmt.setDouble(4, k.getPintaAla());
            stmt.setInt(5, k.getHuoneita());
            stmt.setString(6, k.getHuomioitavaa());
            stmt.executeUpdate();
        }
    }

    public void poistaMokki(String tunnus) throws SQLException {
        String sql = "DELETE FROM Mokki WHERE tunnus = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tunnus);
            stmt.executeUpdate();
        }
    }

    public void muokkaaMokki(KohteetWrapper mokki) throws SQLException {
        KohteetWrapper vanhaMokki = haeMokki(mokki.getTunnus());
        if (vanhaMokki == null) {
            throw new SQLException("Mökkiä ei löydy ID:llä " + mokki.getTunnus());
        }

        String sql = "UPDATE Mokki SET sijainti = ?, hinta = ?, huoneala = ?, henkilo_maara = ?, huomioitavaa = ? WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mokki.getSijainti() != null && !mokki.getSijainti().isEmpty() ? mokki.getSijainti() : vanhaMokki.getSijainti());
            stmt.setDouble(2, mokki.getHinta() != 0.0 ? mokki.getHinta() : vanhaMokki.getHinta());
            stmt.setDouble(3, mokki.getPintaAla() != 0 ? mokki.getPintaAla() : vanhaMokki.getPintaAla());
            stmt.setInt(4, mokki.getHuoneita() != 0 ? mokki.getHuoneita() : vanhaMokki.getHuoneita());
            stmt.setString(5, mokki.getHuomioitavaa() != null ? mokki.getHuomioitavaa() : vanhaMokki.getHuomioitavaa());
            stmt.setString(6, mokki.getTunnus());
            stmt.executeUpdate();
        }
    }

    public KohteetWrapper haeMokki(String mokkiID) throws SQLException {
        String sql = "SELECT * FROM Mokki WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mokkiID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new KohteetWrapper(
                            rs.getString("mokkiID"),
                            rs.getString("sijainti"),
                            rs.getInt("hinta"),
                            rs.getInt("huoneala"),
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
                        rs.getString("mokkiID"),
                        rs.getString("sijainti"),
                        rs.getInt("hinta"),
                        rs.getInt("huoneala"),
                        rs.getInt("Huoneita"),
                        rs.getString("huomioitavaa")
                );
                mokit.add(mokki);
            }
        }

        return mokit;
    }
}
