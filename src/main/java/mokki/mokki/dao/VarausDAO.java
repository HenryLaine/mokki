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
            stmt.setDate(1,java.sql.Date.valueOf(w.getAlkaa()));
            stmt.setDate(2,java.sql.Date.valueOf(w.getPaattyy()));
            stmt.setInt(3,1);
            stmt.setString(4,w.getAsiakkaanSahkoposti());
            stmt.setInt(5,Integer.parseInt(w.getKohteenTunnus()));
            stmt.executeUpdate();
        }
    }


    // luo uusi varaus
    /*
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
    }*/


    public List<VarauksetWrapper> rajaaVaraukset(String hakusana) throws SQLException {
        List<VarauksetWrapper> rajatutVaraukset = new ArrayList<>();

        String sql = "SELECT * FROM Varaus WHERE " +
                "varaustunnus LIKE ? OR " +
                "aloitus_pvm LIKE ? OR " +
                "paattymis_pvm LIKE ? OR " +
                "henkilo_maara LIKE ?";
                //"sahkoposti LIKE ? OR" +
                //"mokkiID LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String likeHakusana = "%" + hakusana + "%";
            stmt.setString(1, likeHakusana);
            stmt.setString(2, likeHakusana);
            stmt.setString(3, likeHakusana);
            stmt.setString(4, likeHakusana);

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
            stmt.setDate(1,java.sql.Date.valueOf(wrapper.getAlkaa()));
            stmt.setDate(2,java.sql.Date.valueOf(wrapper.getPaattyy()));
            stmt.setString(3,wrapper.getAsiakkaanSahkoposti());
            stmt.setInt(4,Integer.parseInt(wrapper.getKohteenTunnus()));
            stmt.setInt(5,Integer.parseInt(wrapper.getTunnus()));
            stmt.executeUpdate();

        }

        }





    // varauksen muokkaaminen
    /*
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
     */

    // apumetodi hakee yhden varauksen

    public VarauksetWrapper haeVaraus(int varausID) throws SQLException {
        String sql = "SELECT * FROM Varaus WHERE varausID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, varausID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VarauksetWrapper(
                            rs.getString("tunnus"),
                            rs.getString("kohteentunnus"),
                            rs.getString("asiakas"),
                            rs.getString("Asiakas"),
                            rs.getDate("alkaa").toLocalDate(),
                            rs.getDate("paattyy").toLocalDate(),
                            rs.getString("tila"),
                            rs.getString("huomioitavaa")
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

    // kaikkien varausten hakeminen käyttäen varauksetwrapper

    public List<VarauksetWrapper> haeKaikkiWrapperVaraukset()throws SQLException {
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






    // kaikkien varausten hakeminen
    /**
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
     */

    // varausten hakeminen aikavälillä

    /**
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
     */


    // apuluokka: resultSet -> Varaus
    /**
    private VarauksetWrapper ResultSetToVaraus(ResultSet rs) throws SQLException {
        int id = rs.getInt("varaustunnus");
        Date alku = rs.getDate("aloitus_pvm");
        Date loppu = rs.getDate("paattymis_pvm");
        int maara = rs.getInt("henkilo_maara");
        String sahkoposti = rs.getString("sahkoposti");
        int mokkiID = rs.getInt("mokkiID");

        return new VarauksetWrapper(id, alku, loppu, maara, sahkoposti, mokkiID);
    }
     */
}

