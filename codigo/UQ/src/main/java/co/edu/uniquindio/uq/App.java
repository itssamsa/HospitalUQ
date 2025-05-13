package co.edu.uniquindio.uq;

import co.edu.uniquindio.uq.model.Paciente;
import co.edu.uniquindio.uq.model.SistemaHospitalario;
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

        // Inicializar pacientes predeterminados
        inicializarDatos();

        // Cambiar a la escena principal (login u otra)
        cambiarEscena("/co/edu/uniquindio/uq/Login.fxml", "Inicio de Sesión");
    }

    public static void cambiarEscena(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(titulo);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarDatos() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();

        Paciente p1 = new Paciente("Juan Pérez", "101", "Calle 123", "3001112233");
        p1.setHistorialMedico("Historial de Juan Pérez: sin antecedentes relevantes.");
        sistema.agregarPaciente(p1);

        Paciente p2 = new Paciente("María Gómez", "102", "Carrera 45", "3002223344");
        p2.setHistorialMedico("Historial de María Gómez: hipertensión controlada.");
        sistema.agregarPaciente(p2);

        Paciente p3 = new Paciente("Carlos Ruiz", "103", "Avenida 10", "3003334455");
        p3.setHistorialMedico("Historial de Carlos Ruiz: fractura de brazo en 2022.");
        sistema.agregarPaciente(p3);

        Paciente p4 = new Paciente("Ana Torres", "104", "Calle 50", "3004445566");
        p4.setHistorialMedico("Historial de Ana Torres: alergias alimentarias.");
        sistema.agregarPaciente(p4);

        Paciente p5 = new Paciente("Luis Martínez", "105", "Carrera 1", "3005556677");
        p5.setHistorialMedico("Historial de Luis Martínez: diabetes tipo 2.");
        sistema.agregarPaciente(p5);

        Paciente p6 = new Paciente("Laura Restrepo", "106", "Calle 89", "3006667788");
        p6.setHistorialMedico("Historial de Laura Restrepo: cirugía de rodilla en 2020.");
        sistema.agregarPaciente(p6);

        Paciente p7 = new Paciente("Miguel Santos", "107", "Avenida 30", "3007778899");
        p7.setHistorialMedico("Historial de Miguel Santos: asma leve.");
        sistema.agregarPaciente(p7);

        Paciente p8 = new Paciente("Sofía Herrera", "108", "Carrera 20", "3008889900");
        p8.setHistorialMedico("Historial de Sofía Herrera: sin antecedentes.");
        sistema.agregarPaciente(p8);

        Paciente p9 = new Paciente("Pedro Vargas", "109", "Calle 74", "3009990011");
        p9.setHistorialMedico("Historial de Pedro Vargas: intervención dental en 2023.");
        sistema.agregarPaciente(p9);

        Paciente p10 = new Paciente("Lucía Ramírez", "110", "Calle 38", "3010001122");
        p10.setHistorialMedico("Historial de Lucía Ramírez: seguimiento por migrañas.");
        sistema.agregarPaciente(p10);

        Paciente p11 = new Paciente("Lucio Vasquez", "111", "Calle 51", "3010012335");
        p11.setHistorialMedico("Historial de Lucía Ramírez: seguimiento por fuerte dolor abdominal.");
        sistema.agregarPaciente(p11);
    }

}
