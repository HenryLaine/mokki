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
        String sql = "UPDATE Mokki SET sijainti = ?, hinta = ?, huoneala = ?, henkilo_maara = ? WHERE mokkiID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mokki.getSijainti());
            stmt.setDouble(2, mokki.getHinta());
            stmt.setInt(3, mokki.getHuoneala());
            stmt.setInt(4, mokki.getHenkiloMaara());
            stmt.executeUpdate();
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