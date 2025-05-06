package mokki.mokki.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("database.properties"));
            url = props.getProperty("DB_URL") + props.getProperty("DB_DATABASE");
            user = props.getProperty("DB_USER");
            password = props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Tietokantakonfiguraation lataus epäonnistui.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}