import co.edu.uniquindio.uq.controller.SistemaHospitalario;
import co.edu.uniquindio.uq.model.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaHospitalarioTest {

    private SistemaHospitalario sistema;

    @BeforeEach
    public void setUp() {
        sistema = SistemaHospitalario.getInstance();
    }

    @Test
    public void testAgregarPaciente() {
        Paciente paciente = new Paciente("123", "Juan Perez", "01/01/1990", "Masculino", "1234");
        sistema.agregarPaciente(paciente);
        assertTrue(sistema.getListaPacientes().contains(paciente));
    }

    @Test
    public void testAgregarMedico() {
        Medico medico = new Medico("456", "Dra. Ana", "Cardiologia", "3126985615", "Pediatra", "1234");
        sistema.agregarMedico(medico);
        assertTrue(sistema.getListaMedicos().contains(medico));
    }

    @Test
    public void testAgregarAdministrador() {
        Administrador admin = new Administrador("Lina Rodríguez", "502", "Calle 22", "3102223344", "1234");
        sistema.agregarAdministrador(admin);
        assertTrue(sistema.getListaAdministradores().contains(admin));
    }

    @Test
    public void testAgregarSala() {
        Sala sala = new Sala("Sala A", "08:00 - 10:00", EstadoSala.DISPONIBLE);
        sistema.agregarSala(sala);
        assertTrue(sistema.obtenerSalas().contains(sala));
    }

    @Test
    public void testReservarSalaDisponible() {
        Sala sala = new Sala("Sala B", "10:00 - 12:00", EstadoSala.DISPONIBLE);
        sistema.agregarSala(sala);
        assertTrue(sistema.reservarSala("Sala B", "10:00 - 12:00"));
    }

    @Test
    public void testReservarSalaNoDisponible() {
        Sala sala = new Sala("Sala C", "14:00 - 16:00", EstadoSala.OCUPADA);
        sistema.agregarSala(sala);
        assertFalse(sistema.reservarSala("Sala C", "14:00 - 16:00"));
    }

    @Test
    public void testGenerarReporte() {
        ObservableList<String> reporte = sistema.generarReporteGeneral();
        assertTrue(reporte.isEmpty());
    }

    @Test
    public void testBuscarPacientePorCedula() {
        Paciente paciente = new Paciente("999", "Laura", "02/02/1995", "Femenino", "1234");
        sistema.agregarPaciente(paciente);
        assertEquals(paciente, sistema.buscarPaciente("999"));
    }

    @Test
    public void testBuscarMedicoPorCedula() {
        Medico medico = new Medico("María Fernanda Ruiz", "206", "Avenida 7", "3106667788", "Cardiologia", "1234");
        sistema.agregarMedico(medico);
        assertEquals(medico, sistema.buscarMedicoPorCedula("206"));
    }


    @Test
    public void testHistorialMedicoPaciente() {
        Paciente paciente = new Paciente("Pedro Vargas", "109", "Calle 74", "3009990011","1234");
        paciente.setHistorialMedico("Cita: 12/12/2025 - Revisión");
        sistema.agregarPaciente(paciente);

        ObservableList<String> reporte = sistema.generarReporteGeneral();
        assertFalse(reporte.isEmpty());
    }

    @Test
    public void testGenerarNotificacionesPorMedico() {
        Medico medico = new Medico("Daniel Gutiérrez", "205", "Carrera 5", "3105556677", "Cardiologia", "1234");
        Paciente paciente = new Paciente("Lucio Vasquez", "111", "Calle 51", "3010012335","1234");
        paciente.setHistorialMedico("Cita: 05/06/2024 - Revisión con médico 001");

        sistema.agregarMedico(medico);
        sistema.agregarPaciente(paciente);

        ObservableList<String> notificaciones = sistema.generarNotificacionesPorMedico("001");
        assertFalse(notificaciones.isEmpty());
    }

    @Test
    public void testSalaDisponibleTrue() {
        Sala sala = new Sala("Sala D", "16:00 - 18:00", EstadoSala.DISPONIBLE);
        sistema.agregarSala(sala);
        assertTrue(sistema.estaSalaDisponible("Sala D", "16:00 - 18:00"));
    }

    @Test
    public void testSalaDisponibleFalse() {
        Sala sala = new Sala("Sala E", "18:00 - 20:00", EstadoSala.OCUPADA);
        sistema.agregarSala(sala);
        assertFalse(sistema.estaSalaDisponible("Sala E", "18:00 - 20:00"));
    }


    @Test
    public void testAgregarSalaDuplicada() {
        Sala sala = new Sala("Sala X", "08:00 - 10:00", EstadoSala.DISPONIBLE);
        sistema.agregarSala(sala);
        sistema.agregarSala(sala);
        assertEquals(2, sistema.obtenerSalas().filtered(s -> s.getNombreSala().equals("Sala X")).size());
    }

    @Test
    public void testGestorSalas() {
        sistema.gestionarSalas("Sala Z", "09:00 - 11:00", EstadoSala.DISPONIBLE);
        assertTrue(sistema.obtenerSalas().stream().anyMatch(s -> s.getNombreSala().equals("Sala Z")));
    }

    @Test
    public void testNoEncontrarPaciente() {
        assertNull(sistema.buscarPaciente("000"));
    }

    @Test
    public void testNoEncontrarMedico() {
        assertNull(sistema.buscarMedicoPorCedula("000"));
    }
}
