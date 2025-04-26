package mokki.mokki.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {

    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/"; // ilman tietokantaa
    private static final String DATABASE_NAME = "mokkikodit";
    private static final String USER = "devuser";
    private static final String PASSWORD = "Mokki666!";

    public static void ensureDatabaseExists() {
        try (Connection conn = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            stmt.executeUpdate(sql);
            System.out.println("Tietokanta tarkistettu/luotu onnistuneesti.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Tietokannan luonti epäonnistui.");
        }
    }
}