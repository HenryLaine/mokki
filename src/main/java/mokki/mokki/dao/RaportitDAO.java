package mokki.mokki.dao;

import mokki.mokki.gui.testiluokatTaulukonDatalle.RaportitWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RaportitDAO {

    private Connection conn;

    public RaportitDAO(Connection conn) {
        this.conn = conn;
    }

    // Hakee raportin tietyn aikavälin perusteella
    public List<RaportitWrapper> haeRaportti(LocalDate alku, LocalDate loppu) {
        List<RaportitWrapper> raportti = new ArrayList<>();

        // SQL-kysely raportin hakemiseen
        String sql = """
       
                SELECT\s
                  m.mokkiID AS kohde,
                  COUNT(v.varaustunnus) AS varausten_maara,
                  SUM(CASE\s
                          WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?\s
                          THEN DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1\s
                          ELSE 0\s
                      END) AS varatut_paivat,
                  DATEDIFF(?, ?) + 1 AS ajanjakso_pituus,
                  ROUND(
                      100 * SUM(CASE\s
                          WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?\s
                          THEN DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1\s
                          ELSE 0\s
                      END) / (DATEDIFF(?, ?) + 1),\s
                  0) AS kayttoaste_prosentti,
                  m.hinta,
                  ROUND(m.hinta, 2) AS paivatulot,
                  ROUND(m.hinta * 7, 2) AS viikkotulot,
                  ROUND(m.hinta * 30, 2) AS kuukausitulot,
                  ROUND(SUM(CASE\s
                      WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?\s
                      THEN (DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1) * m.hinta\s
                      ELSE 0\s
                  END), 2) AS kokonaistulot
              FROM Mokki m
              LEFT JOIN Varaus v ON m.mokkiID = v.mokkiID
              GROUP BY m.mokkiID, m.hinta
              ORDER BY m.mokkiID
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            java.sql.Date alkupvm = java.sql.Date.valueOf(alku);
            java.sql.Date loppupvm = java.sql.Date.valueOf(loppu);

            for (int i = 1; i <= 16; i += 2) {
                stmt.setDate(i, loppupvm);
                stmt.setDate(i + 1, alkupvm);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    raportti.add(new RaportitWrapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raportti;
    }

    // Hakee varausten määrän tietylle mökille ja aikavälille
    public int haeVaraustenMaara(int mokkiID, LocalDate alku, LocalDate loppu) {
        int varaustenMaara = 0;

        String sql = """
            SELECT COUNT(v.varaustunnus) AS varausten_maara
            FROM Varaus v
            WHERE v.mokkiID = ? 
            AND v.aloitus_pvm <= ? 
            AND v.paattymis_pvm >= ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mokkiID); // Mökin ID
            stmt.setDate(2, java.sql.Date.valueOf(loppu)); // Loppupäivämäärä
            stmt.setDate(3, java.sql.Date.valueOf(alku)); // Alkupäivämäärä

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    varaustenMaara = rs.getInt("varausten_maara");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return varaustenMaara;
    }

    // Hakee käyttöasteen tietylle mökille ja aikavälille
    public double haeKayttoaste(int mokkiID, LocalDate alku, LocalDate loppu) {
        double kayttoaste = 0;

        String sql = """
            SELECT 
                SUM(DATEDIFF(LEAST(v.paattymis_pvm, ?) , GREATEST(v.aloitus_pvm, ?)) + 1) AS varatut_paivat,
                DATEDIFF(?, ?) + 1 AS ajanjakso_pituus
            FROM Varaus v
            WHERE v.mokkiID = ? 
            AND v.aloitus_pvm <= ? 
            AND v.paattymis_pvm >= ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(loppu)); // Loppupäivämäärä
            stmt.setDate(2, java.sql.Date.valueOf(alku)); // Alkupäivämäärä
            stmt.setDate(3, java.sql.Date.valueOf(loppu)); // Loppupäivämäärä
            stmt.setDate(4, java.sql.Date.valueOf(alku)); // Alkupäivämäärä
            stmt.setInt(5, mokkiID); // Mökin ID
            stmt.setDate(6, java.sql.Date.valueOf(loppu)); // Loppupäivämäärä
            stmt.setDate(7, java.sql.Date.valueOf(alku)); // Alkupäivämäärä

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int varatutPaivat = rs.getInt("varatut_paivat");
                    int ajanjaksoPituus = rs.getInt("ajanjakso_pituus");

                    if (ajanjaksoPituus > 0) {
                        kayttoaste = 100 * (double) varatutPaivat / ajanjaksoPituus;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kayttoaste;
    }

    // Hakee kokonaistulot tietylle mökille ja aikavälille
    public double haeTuotto(int mokkiID, LocalDate alku, LocalDate loppu) {
        double kokonaistulot = 0;

        String sql = """
            SELECT 
                SUM((DATEDIFF(LEAST(v.paattymis_pvm, ?) , GREATEST(v.aloitus_pvm, ?)) + 1) * m.hinta) AS kokonaistulot
            FROM Varaus v
            LEFT JOIN Mokki m ON v.mokkiID = m.mokkiID
            WHERE v.mokkiID = ?
            AND v.aloitus_pvm <= ?
            AND v.paattymis_pvm >= ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(loppu)); // Loppupäivämäärä
            stmt.setDate(2, java.sql.Date.valueOf(alku)); // Alkupäivämäärä
            stmt.setInt(3, mokkiID); // Mökin ID
            stmt.setDate(4, java.sql.Date.valueOf(loppu)); // Loppupäivämäärä
            stmt.setDate(5, java.sql.Date.valueOf(alku)); // Alkupäivämäärä

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    kokonaistulot = rs.getDouble("kokonaistulot");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kokonaistulot;
    }
}