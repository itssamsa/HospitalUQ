package co.edu.uniquindio.uq;

import co.edu.uniquindio.uq.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

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

        Paciente p1 = new Paciente("Juan Pérez", "101", "Calle 123", "3001112233","1234");
        p1.setHistorialMedico("Historial de Juan Pérez: sin antecedentes relevantes.");
        sistema.agregarPaciente(p1);

        Paciente p2 = new Paciente("María Gómez", "102", "Carrera 45", "3002223344","1234");
        p2.setHistorialMedico("Historial de María Gómez: hipertensión controlada.");
        sistema.agregarPaciente(p2);

        Paciente p3 = new Paciente("Carlos Ruiz", "103", "Avenida 10", "3003334455","1234");
        p3.setHistorialMedico("Historial de Carlos Ruiz: fractura de brazo en 2022.");
        sistema.agregarPaciente(p3);

        Paciente p4 = new Paciente("Ana Torres", "104", "Calle 50", "3004445566","1234");
        p4.setHistorialMedico("Historial de Ana Torres: alergias alimentarias.");
        sistema.agregarPaciente(p4);

        Paciente p5 = new Paciente("Luis Martínez", "105", "Carrera 1", "3005556677","1234");
        p5.setHistorialMedico("Historial de Luis Martínez: diabetes tipo 2.");
        sistema.agregarPaciente(p5);

        Paciente p6 = new Paciente("Laura Restrepo", "106", "Calle 89", "3006667788","1234");
        p6.setHistorialMedico("Historial de Laura Restrepo: cirugía de rodilla en 2020.");
        sistema.agregarPaciente(p6);

        Paciente p7 = new Paciente("Miguel Santos", "107", "Avenida 30", "3007778899","1234");
        p7.setHistorialMedico("Historial de Miguel Santos: asma leve.");
        sistema.agregarPaciente(p7);

        Paciente p8 = new Paciente("Sofía Herrera", "108", "Carrera 20", "3008889900","1234");
        p8.setHistorialMedico("Historial de Sofía Herrera: sin antecedentes.");
        sistema.agregarPaciente(p8);

        Paciente p9 = new Paciente("Pedro Vargas", "109", "Calle 74", "3009990011","1234");
        p9.setHistorialMedico("Historial de Pedro Vargas: intervención dental en 2023.");
        sistema.agregarPaciente(p9);

        Paciente p10 = new Paciente("Lucía Ramírez", "110", "Calle 38", "3010001122","1234");
        p10.setHistorialMedico("Historial de Lucía Ramírez: seguimiento por migrañas.");
        sistema.agregarPaciente(p10);

        Paciente p11 = new Paciente("Lucio Vasquez", "111", "Calle 51", "3010012335","1234");
        p11.setHistorialMedico("Historial de Lucio Vazquez: seguimiento por fuerte dolor abdominal.");
        sistema.agregarPaciente(p11);

        // Creación de médicos (CUIDADO CON LAS TILDES POR QUE O SINO QUEDA MAL)
        // Médico 1 - Andrés López (Lunes y Martes)


        // Médico 1
        Medico m1 = new Medico("Andrés López", "201", "Carrera 10", "3101112233", "Medicina General", "1234");
        Set<String> dias1 = new HashSet<>();
        dias1.add("Lunes");
        dias1.add("Martes");
        dias1.add("Viernes");
        Map<String, String> turnos1 = new HashMap<>();
        turnos1.put("Lunes", "06:00-14:00");
        turnos1.put("Martes", "14:00-22:00");
        turnos1.put("Viernes", "22:00-06:00");
        Agenda agenda1 = new Agenda(dias1, turnos1);
        m1.setAgenda(agenda1);
        sistema.agregarMedico(m1);

// Médico 2
        Medico m2 = new Medico("Carolina Rodríguez", "202", "Calle 22", "3102223344", "Medicina General", "1234");
        Set<String> dias2 = new HashSet<>();
        dias2.add("Lunes");
        dias2.add("Martes");
        dias2.add("Viernes");
        Map<String, String> turnos2 = new HashMap<>();
        turnos2.put("Lunes", "14:00-22:00");
        turnos2.put("Martes", "06:00-14:00");
        turnos2.put("Viernes", "22:00-06:00");
        Agenda agenda2 = new Agenda(dias2, turnos2);
        m2.setAgenda(agenda2);
        sistema.agregarMedico(m2);

// Médico 3
        Medico m3 = new Medico("Santiago Ríos", "203", "Avenida 15", "3103334455", "Pediatria", "1234");
        Set<String> dias3 = new HashSet<>();
        dias3.add("Lunes");
        dias3.add("Martes");
        dias3.add("Viernes");
        Map<String, String> turnos3 = new HashMap<>();
        turnos3.put("Lunes", "06:00-14:00");
        turnos3.put("Martes", "14:00-22:00");
        turnos3.put("Viernes", "22:00-06:00");
        Agenda agenda3 = new Agenda(dias3, turnos3);
        m3.setAgenda(agenda3);
        sistema.agregarMedico(m3);

// Médico 4
        Medico m4 = new Medico("Luisa Vélez", "204", "Calle 30", "3104445566", "Pediatria", "1234");
        Set<String> dias4 = new HashSet<>();
        dias4.add("Lunes");
        dias4.add("Martes");
        dias4.add("Viernes");
        Map<String, String> turnos4 = new HashMap<>();
        turnos4.put("Lunes", "06:00-14:00");
        turnos4.put("Martes", "14:00-22:00");
        turnos4.put("Viernes", "22:00-06:00");
        Agenda agenda4 = new Agenda(dias4, turnos4);
        m4.setAgenda(agenda4);
        sistema.agregarMedico(m4);

// Médico 5
        Medico m5 = new Medico("Daniel Gutiérrez", "205", "Carrera 5", "3105556677", "Cardiologia", "1234");
        Set<String> dias5 = new HashSet<>();
        dias5.add("Lunes");
        dias5.add("Martes");
        dias5.add("Viernes");
        Map<String, String> turnos5 = new HashMap<>();
        turnos5.put("Lunes", "06:00-14:00");
        turnos5.put("Martes", "14:00-22:00");
        turnos5.put("Viernes", "22:00-06:00");
        Agenda agenda5 = new Agenda(dias5, turnos5);
        m5.setAgenda(agenda5);
        sistema.agregarMedico(m5);

// Médico 6
        Medico m6 = new Medico("María Fernanda Ruiz", "206", "Avenida 7", "3106667788", "Cardiologia", "1234");
        Set<String> dias6 = new HashSet<>();
        dias6.add("Lunes");
        dias6.add("Martes");
        dias6.add("Viernes");
        Map<String, String> turnos6 = new HashMap<>();
        turnos6.put("Lunes", "06:00-14:00");
        turnos6.put("Martes", "14:00-22:00");
        turnos6.put("Viernes", "22:00-06:00");
        Agenda agenda6 = new Agenda(dias6, turnos6);
        m6.setAgenda(agenda6);
        sistema.agregarMedico(m6);



        // ADMINISTRADORES


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

        Sala s5 = new Sala("Sala de emergencias", "24 horas", EstadoSala.DISPONIBLE);
        sistema.agregarSala(s5);
    }
}








