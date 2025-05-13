package mokki.mokki.dao;
import mokki.mokki.gui.testiluokatTaulukonDatalle.VarauksetWrapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Luokka ottaa yhteyden tietokantaan ja muokkaa, lisää, poistaa ja listaa varauksia.
*/

public class VarausDAO {
    private Connection conn;

    public VarausDAO(Connection conn) {
        this.conn = conn;
    }

    // tämä blokki tässä käyttää varauksetwrapper luokkaa ja tämä siis lisää varauksen
    public void lisaaVaraus(VarauksetWrapper w) throws SQLException {
        String sql = "INSERT INTO Varaus (aloitus_pvm, paattymis_pvm, henkilo_maara, sahkoposti,mokkiID) VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(w.getAlkaa()));
            stmt.setDate(2, java.sql.Date.valueOf(w.getPaattyy()));
            stmt.setInt(3, 1);
            stmt.setString(4, w.getAsiakkaanSahkoposti());
            stmt.setInt(5, Integer.parseInt(w.getKohteenTunnus()));
            stmt.executeUpdate();
        }
    }


    public List<VarauksetWrapper> rajaaVaraukset(String hakusana) throws SQLException {
        List<VarauksetWrapper> rajatutVaraukset = new ArrayList<>();

        String sql = "SELECT v.*, a.nimi, a.sahkoposti FROM Varaus v " +
                "JOIN AsiakasTiedotView a ON v.sahkoposti = a.sahkoposti " +
                "WHERE CAST(v.varaustunnus AS CHAR) LIKE ? OR " +
                "CAST(v.mokkiID AS CHAR) LIKE ? OR " +
                "CAST(v.henkilo_maara AS CHAR) LIKE ? OR " +
                "v.aloitus_pvm LIKE ? OR " +
                "v.paattymis_pvm LIKE ? OR " +
                "a.sahkoposti LIKE ? OR " +
                "a.nimi LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String likeHakusana = "%" + hakusana + "%";
            for (int i = 1; i <= 7; i++) {
                stmt.setString(i, likeHakusana);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VarauksetWrapper varaus = new VarauksetWrapper(
                            String.valueOf(rs.getInt("varaustunnus")),
                            String.valueOf(rs.getInt("mokkiID")),
                            rs.getString("nimi"),
                            rs.getString("sahkoposti"),
                            rs.getDate("aloitus_pvm").toLocalDate(),
                            rs.getDate("paattymis_pvm").toLocalDate(),
                            "Aktiivinen",
                            ""
                    );
                    rajatutVaraukset.add(varaus);
                }
            }

        }
        return rajatutVaraukset;
    }

    // varauksen muokkaaminen joka käyttää varauksetwrapper luokkaa
    public void muokkaaVarausta(VarauksetWrapper wrapper) throws SQLException {
        String sql = "UPDATE Varaus SET aloitus_pvm = ?, paattymis_pvm = ?, sahkoposti = ?, mokkiID = ? WHERE varaustunnus = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(wrapper.getAlkaa()));
            stmt.setDate(2, java.sql.Date.valueOf(wrapper.getPaattyy()));
            stmt.setString(3, wrapper.getAsiakkaanSahkoposti());
            stmt.setInt(4, Integer.parseInt(wrapper.getKohteenTunnus()));
            stmt.setInt(5, Integer.parseInt(wrapper.getTunnus()));
            stmt.executeUpdate();

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

    // kaikkien varausten hakeminen käyttäen varauksetwrapper

    public List<VarauksetWrapper> haeKaikkiWrapperVaraukset() throws SQLException {
        List<VarauksetWrapper> lista = new ArrayList<>();
        String sql = "SELECT v.*, a.nimi, a.sahkoposti FROM Varaus v JOIN AsiakasTiedotView a ON v.sahkoposti = a.sahkoposti";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                VarauksetWrapper varaus = new VarauksetWrapper(
                        String.valueOf(rs.getInt("varaustunnus")),
                        String.valueOf(rs.getInt("mokkiID")),
                        rs.getString("nimi"),
                        rs.getString("sahkoposti"),
                        rs.getDate("aloitus_pvm").toLocalDate(),
                        rs.getDate("paattymis_pvm").toLocalDate(),
                        "Aktiivinen",
                        ""
                );
                lista.add(varaus);
            }
        }
        return lista;
    }
}