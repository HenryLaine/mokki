package mokki.mokki.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseCreator {

    private static String serverUrl;
    private static String databaseName;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("database.properties"));
            serverUrl = props.getProperty("DB_URL"); // Huom! Tarvitsee uuden avaimen!
            databaseName = props.getProperty("DB_DATABASE");
            user = props.getProperty("DB_USER");
            password = props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Tietokantakonfiguraation lataus epäonnistui.");
        }
    }

    public static void ensureDatabaseExists() {
        try (Connection conn = DriverManager.getConnection(serverUrl, user, password);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            stmt.executeUpdate(sql);
            System.out.println("Tietokanta tarkistettu/luotu onnistuneesti.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Tietokannan luonti epäonnistui.");
        }
    }
}