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

        String sql = """
            SELECT
              m.mokkiID AS kohde,
              COUNT(v.varaustunnus) AS varausten_maara,
              SUM(CASE
                      WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?
                      THEN DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1
                      ELSE 0
                  END) AS varatut_paivat,
              DATEDIFF(?, ?) + 1 AS ajanjakso_pituus,
              ROUND(
                  100 * SUM(CASE
                      WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?
                      THEN DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1
                      ELSE 0
                  END) / (DATEDIFF(?, ?) + 1),
              0) AS kayttoaste_prosentti,
              m.hinta,

              -- Keskimääräinen päivätulo ajanjaksolla
              ROUND(SUM(CASE
                  WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?
                  THEN (DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1) * m.hinta
                  ELSE 0
              END) / (DATEDIFF(?, ?) + 1), 2) AS paivatulot,

              -- Keskimääräinen viikkotulo
              ROUND(SUM(CASE
                  WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?
                  THEN (DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1) * m.hinta
                  ELSE 0
              END) / (DATEDIFF(?, ?) + 1) * 7, 2) AS viikkotulot,

              -- Keskimääräinen kuukausitulo
              ROUND(SUM(CASE
                  WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?
                  THEN (DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1) * m.hinta
                  ELSE 0
              END) / (DATEDIFF(?, ?) + 1) * 30, 2) AS kuukausitulot,

              -- Kokonaistulot
              ROUND(SUM(CASE
                  WHEN v.aloitus_pvm <= ? AND v.paattymis_pvm >= ?
                  THEN (DATEDIFF(LEAST(v.paattymis_pvm, ?), GREATEST(v.aloitus_pvm, ?)) + 1) * m.hinta
                  ELSE 0
              END), 2) AS kokonaistulot

            FROM Mokki m
            LEFT JOIN Varaus v ON m.mokkiID = v.mokkiID
            GROUP BY m.mokkiID, m.hinta
            ORDER BY m.mokkiID
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            java.sql.Date alkupvm = java.sql.Date.valueOf(alku);
            java.sql.Date loppupvm = java.sql.Date.valueOf(loppu);

            int i = 1;
            for (int j = 0; j < 17; j++) { // 9 kertaa 4 päivämäärää (<=, >=, LEAST, GREATEST)
                stmt.setDate(i++, loppupvm);
                stmt.setDate(i++, alkupvm);
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
}
