package mokki.mokki;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane paneeli = new BorderPane();
        Scene kehys = new Scene(paneeli, 600, 300);
        primaryStage.setTitle("Mokki");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}