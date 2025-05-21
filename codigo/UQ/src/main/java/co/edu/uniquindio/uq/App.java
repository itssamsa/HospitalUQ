package co.edu.uniquindio.uq;

import co.edu.uniquindio.uq.model.*;
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

    // CREACION PACIENTES
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
        p11.setHistorialMedico("Historial de Lucio Vazquez: seguimiento por fuerte dolor abdominal.");
        sistema.agregarPaciente(p11);

        // Creación de médicos (CUIDADO CON LAS TILDES POR QUE O SINO QUEDA MAL)
        Medico m1 = new Medico("Andrés López", "201", "Carrera 10", "3101112233", "Medicina General", "8:00 AM - 4:00 PM","1234");
        sistema.agregarMedico(m1);

        Medico m2 = new Medico("Carolina Rodríguez", "202", "Calle 22", "3102223344", "Medicina General", "9:00 AM - 5:00 PM","1234");
        sistema.agregarMedico(m2);

        Medico m3 = new Medico("Santiago Ríos", "203", "Avenida 15", "3103334455", "Pediatria", "10:00 AM - 6:00 PM","1234");
        sistema.agregarMedico(m3);

        Medico m4 = new Medico("Luisa Vélez", "204", "Calle 30", "3104445566", "Pediatria", "1:00 PM - 7:00 PM","1234");
        sistema.agregarMedico(m4);

        Medico m5 = new Medico("Daniel Gutiérrez", "205", "Carrera 5", "3105556677", "Cardiologia", "7:00 AM - 3:00 PM","1234");
        sistema.agregarMedico(m5);

        Medico m6 = new Medico("María Fernanda Ruiz", "206", "Avenida 7", "3106667788", "Cardiologia", "9:00 AM - 5:00 PM","1234");
        sistema.agregarMedico(m6);


        Administrador a1 = new Administrador("Felipe López", "501", "Carrera 10", "3101112233", "1234");
        sistema.agregarAdministrador(a1);

        Administrador a2 = new Administrador("Lina Rodríguez", "502", "Calle 22", "3102223344", "1234");
        sistema.agregarAdministrador(a2);

        Administrador a3 = new Administrador("Leonardo Ríos", "503333", "Avenida 15", "3103334455","1234");
        sistema.agregarAdministrador(a3);


        //Creacion Salas
        Sala s1 = new Sala("Sala de rayos X", "8:00 AM - 12:00 PM", EstadoSala.DISPONIBLE);
        sistema.agregarSala(s1);

        Sala s2 = new Sala("Sala de cirugía", "1:00 PM - 5:00 PM", EstadoSala.OCUPADA);
        sistema.agregarSala(s2);

        Sala s3 = new Sala("Sala de cuidados intensivos", "9:00 AM - 3:00 PM", EstadoSala.ENMANTENIMIENTO);
        sistema.agregarSala(s3);

        Sala s4 = new Sala("Sala de pediatría", "10:00 AM - 2:00 PM", EstadoSala.DISPONIBLE);
        sistema.agregarSala(s4);

        Sala s5 = new Sala("Sala de emergencias", "24 horas", EstadoSala.OCUPADA);
        sistema.agregarSala(s5);
    }
}








