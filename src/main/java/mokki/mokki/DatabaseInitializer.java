package mokki.mokki;

import java.io.*;
import java.sql.*;

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

    private static void createTables(Connection conn) throws SQLException, IOException {
        System.out.println("Luodaan taulut tiedostosta: " + RAKENNE_SQL_FILE);
        executeSqlFile(conn, RAKENNE_SQL_FILE);
        System.out.println("Taulut luotu onnistuneesti.");
    }

    private static void loadTestDataIfNeeded(Connection conn) throws SQLException, IOException {
        if (!dataExists(conn)) {
            System.out.println("Testidataa ei löytynyt – ladataan.");
            executeSqlFile(conn, TESTIDATA_SQL_FILE);
            System.out.println("Testidata ladattu onnistuneesti.");
        } else {
            System.out.println("Testidata on jo olemassa – ei ladata uudelleen.");
        }
    }

    private static boolean dataExists(Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM Asiakas";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() && rs.getInt(1) > 0;
        }
    }

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