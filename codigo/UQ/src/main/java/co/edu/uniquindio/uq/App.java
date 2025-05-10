package co.edu.uniquindio.uq;

import co.edu.uniquindio.uq.model.Paciente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;
        cambiarEscena("/co/edu/uniquindio/uq/Login.fxml", "Inicio de Sesión");
    }

    public static void cambiarEscena(String fxml, String titulo) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = loader.load();

            // Crear la escena y mostrarla
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(titulo);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
