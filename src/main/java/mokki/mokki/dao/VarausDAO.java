package mokki.mokki.dao;
import mokki.mokki.BackEnd.Varaus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Luokka ottaa yhteyden tietokantaan ja muokkaa, lisää, poistaa ja listaa varauksia.

public class VarausDAO {
    private Connection conn;

    public VarausDAO(Connection conn) {
        this.conn = conn;
    }
    // luo uusi varaus
    public void luoVaraus(Varaus varaus) throws SQLException {
        String sql = "INSERT INTO Varaus (aloitus_pvm, paattymis_pvm, henkilo_maara, sahkoposti, mokkiID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, varaus.getVarausAlkuPvm());
            stmt.setDate(2, varaus.getVarausLoppuPvm());
            stmt.setInt(3, varaus.getVaraajienMaara());
            stmt.setString(4, varaus.getSahkoposti());
            stmt.setInt(5, varaus.getMokkiID());
            stmt.executeUpdate();
        }
    }
    // varauksen muokkaaminen
    public void muokkaaVarausta(Varaus varaus) throws SQLException {
        Varaus vanhaVaraus = haeVaraus(varaus.getVarausTunnus());
        if (vanhaVaraus == null) {
            throw new SQLException("Varausta ei löydy ID:llä " + varaus.getVarausTunnus());
        }

        String sql = "UPDATE Varaus SET aloitus_pvm = ?, paattymis_pvm = ?, henkilo_maara = ?, sahkoposti = ?, mokkiID = ? WHERE varausID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, varaus.getVarausAlkuPvm() != null ? varaus.getVarausAlkuPvm() : vanhaVaraus.getVarausAlkuPvm());
            stmt.setDate(2, varaus.getVarausLoppuPvm() != null ? varaus.getVarausLoppuPvm() : vanhaVaraus.getVarausLoppuPvm());
            stmt.setInt(3, varaus.getVaraajienMaara() != 0 ? varaus.getVaraajienMaara() : vanhaVaraus.getVaraajienMaara());
            stmt.setString(4, varaus.getSahkoposti() != null && !varaus.getSahkoposti().isEmpty() ? varaus.getSahkoposti() : vanhaVaraus.getSahkoposti());
            stmt.setInt(5, varaus.getMokkiID() != 0 ? varaus.getMokkiID() : vanhaVaraus.getMokkiID());
            stmt.setInt(6, varaus.getVarausTunnus());
            stmt.executeUpdate();
        }
    }
    // apumetodi hakee yhden varauksen
    public Varaus haeVaraus(int varausID) throws SQLException {
        String sql = "SELECT * FROM Varaus WHERE varausID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, varausID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Varaus(
                            rs.getInt("varausTunnus"),
                            rs.getDate("varausAlkuPvm"),
                            rs.getDate("varausLoppuPvm"),
                            rs.getInt("varaajienMaara"),
                            rs.getString("sahkoposti"),
                            rs.getInt("mokkiID")
                    );
                } else {
                    return null;
                }
            }
        }
    }
    // varauksen poistaminen
    public void poistaVaraus(int varaustunnus) throws SQLException {
        String sql = "DELETE FROM Varaus WHERE varaustunnus = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, varaustunnus);
            stmt.executeUpdate();
        }
    }

    // kaikkien varausten hakeminen
    public List<Varaus> haeKaikkiVaraukset() throws SQLException {
        List<Varaus> varaukset = new ArrayList<>();
        String sql = "SELECT * FROM Varaus";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                varaukset.add(ResultSetToVaraus(rs));
            }
        }
        return varaukset;
    }
    // varausten hakeminen aikavälillä
    public List<Varaus> haeVarauksetPaivamaaranPerusteella(Date alku, Date loppu) throws SQLException {
        List<Varaus> varaukset = new ArrayList<>();
        String sql = "SELECT * FROM Varaus WHERE aloitus_pvm >= ? AND paattymis_pvm <= ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, alku);
            stmt.setDate(2, loppu);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    varaukset.add(ResultSetToVaraus(rs));
                }
            }
        }
        return varaukset;
    }

    // apuluokka: resultSet -> Varaus
    private Varaus ResultSetToVaraus(ResultSet rs) throws SQLException {
        int id = rs.getInt("varaustunnus");
        Date alku = rs.getDate("aloitus_pvm");
        Date loppu = rs.getDate("paattymis_pvm");
        int maara = rs.getInt("henkilo_maara");
        String sahkoposti = rs.getString("sahkoposti");
        int mokkiID = rs.getInt("mokkiID");

        return new Varaus(id, alku, loppu, maara, sahkoposti, mokkiID);
    }
}*/
