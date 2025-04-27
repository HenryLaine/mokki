package mokki.mokki.dao;
import mokki.mokki.BackEnd.Mokki;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Tämä luokka ottaa yhteyden tietokantaan ja muokkaa sekä hakee tietoa
 * Mökki-taulukosta.


public class MokkiDAO {
    private Connection conn;

    public MokkiDAO(Connection conn) {
        this.conn = conn;
    }

    // Mökin lisääminen tietokantaan. MokkiID on automaattisesti tuleva luku.


    public void lisaaMokki(Mokki mokki) throws SQLException {
        String sql = "INSERT INTO Mokki (sijainti, hinta, huoneala, henkilo_maara) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mokki.getSijainti());
            stmt.setDouble(2, mokki.getHinta());
            stmt.setInt(3, mokki.getHuoneala());
            stmt.setInt(4, mokki.getHenkiloMaara());
            stmt.executeUpdate();
        }
    }

    public void poistaMokki(int mokkiID) throws SQLException {
        String sql = "DELETE FROM Mokki WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mokkiID);
            stmt.executeUpdate();
        }
    }

    // Metodi muokkaa olemassa olevaa mökkiä

    public void muokkaaMokki(Mokki mokki) throws SQLException {
        Mokki vanhaMokki = haeMokki(mokki.getMokkiID());
        if (vanhaMokki == null) {
            throw new SQLException("Mökkiä ei löydy ID:llä " + mokki.getMokkiID());
        }

        String sql = "UPDATE Mokki SET sijainti = ?, hinta = ?, huoneala = ?, henkilo_maara = ? WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mokki.getSijainti() != null && !mokki.getSijainti().isEmpty() ? mokki.getSijainti() : vanhaMokki.getSijainti());
            stmt.setDouble(2, mokki.getHinta() != 0.0 ? mokki.getHinta() : vanhaMokki.getHinta());
            stmt.setInt(3, mokki.getHuoneala() != 0 ? mokki.getHuoneala() : vanhaMokki.getHuoneala());
            stmt.setInt(4, mokki.getHenkiloMaara() != 0 ? mokki.getHenkiloMaara() : vanhaMokki.getHenkiloMaara());
            stmt.setInt(5, mokki.getMokkiID());
            stmt.executeUpdate();
        }
    }
    // apumetodi hakee yhden mökin
    public Mokki haeMokki(int mokkiID) throws SQLException {
        String sql = "SELECT * FROM Mokki WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mokkiID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mokki(
                            rs.getInt("mokkiID"),
                            rs.getString("sijainti"),
                            rs.getDouble("hinta"),
                            rs.getInt("huoneala"),
                            rs.getInt("henkilo_maara")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    // Metodi hakee listauksen kaikista mökki-olioista.

    public List<Mokki> haeMokit() throws SQLException {
        List<Mokki> mokit = new ArrayList<>();
        String sql = "SELECT * FROM Mokki";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mokki mokki = new Mokki(
                        rs.getInt("mokkiID"),
                        rs.getString("sijainti"),
                        rs.getDouble("hinta"),
                        rs.getInt("huoneala"),
                        rs.getInt("henkilo_maara")
                );
                mokit.add(mokki);
            }
        }

        return mokit;
    }
}
 */