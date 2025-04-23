package mokki.mokki;

import java.io.*;
import java.sql.*;

/** Luokka luo tietokannan ja lisää testiaineiston mikäli tietokantaa ei ole vielä olemassa.
 * Mikäli tietokanta on jo luotu, luokka ei tee mitään.
 */
public class DatabaseInitializer {
    private static final String RAKENNE_SQL_FILE = "mokkikodit_database.sql";
    private static final String TESTIDATA_SQL_FILE = "testiaineisto.sql";

    public static void initializeDatabase() {
        try (Connection conn = DatabaseManager.getConnection()) {
            createTables(conn);
            loadTestDataIfNeeded(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodi luo taulukot mikäli niitä ei vielä ole
    private static void createTables(Connection conn) throws SQLException, IOException {
        if (!tablesExist(conn)) {
            System.out.println("Tauluja ei löytynyt – luodaan taulut tiedostosta: " + RAKENNE_SQL_FILE);
            executeSqlFile(conn, RAKENNE_SQL_FILE);
            System.out.println("Taulut luotu onnistuneesti.");
        } else {
            System.out.println("Taulut ovat jo olemassa – ei luoda uudelleen.");
        }
    }
    // tarkistaa onko taulukoita olemassa
    private static boolean tablesExist(Connection conn) throws SQLException {
        String query = "SHOW TABLES LIKE 'Asiakas'";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Palauttaa true jos taulu löytyy
        }
    }

    // lataa tarvittaessa testiaineiston
    private static void loadTestDataIfNeeded(Connection conn) throws SQLException, IOException {
        if (!dataExists(conn)) {
            System.out.println("Testidataa ei löytynyt – ladataan.");
            executeSqlFile(conn, TESTIDATA_SQL_FILE);
            System.out.println("Testidata ladattu onnistuneesti.");
        } else {
            System.out.println("Testidata on jo olemassa – ei ladata uudelleen.");
        }
    }

    // tarkistaa onko testiaineistoa olemassa
    private static boolean dataExists(Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM Asiakas";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // suorittaa sql-tiedostot
    private static void executeSqlFile(Connection conn, String filePath) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             Statement stmt = conn.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--")) continue;
                sql.append(line).append(" ");
                if (line.endsWith(";")) {
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }
        }
    }
}