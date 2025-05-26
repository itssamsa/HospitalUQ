import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.uniquindio.uq.model.*;

public class SistemaHospitalarioTest {

    @Test
    void testCantidadMedicosRegistrados() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        sistema.getListaMedicos().clear();

        Medico m1 = new Medico("Médico 1", "101", "Dir1", "Tel1", "Especialidad1", "clave");
        Medico m2 = new Medico("Médico 2", "102", "Dir2", "Tel2", "Especialidad2", "clave");
        Medico m3 = new Medico("Médico 3", "103", "Dir3", "Tel3", "Especialidad3", "clave");
        Medico m4 = new Medico("Médico 4", "104", "Dir4", "Tel4", "Especialidad4", "clave");
        Medico m5 = new Medico("Médico 5", "105", "Dir5", "Tel5", "Especialidad5", "clave");
        Medico m6 = new Medico("Médico 6", "106", "Dir6", "Tel6", "Especialidad6", "clave");

        sistema.agregarMedico(m1);
        sistema.agregarMedico(m2);
        sistema.agregarMedico(m3);
        sistema.agregarMedico(m4);
        sistema.agregarMedico(m5);
        sistema.agregarMedico(m6);

        assertEquals(6, sistema.getListaMedicos().size(), "Debe haber 6 médicos registrados");
        }
    @Test
    void testBuscarMedicoPorCedula() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        sistema.getListaMedicos().clear();

        Medico medico = new Medico("Andrés Gómez", "201", "Calle A", "3123456789", "General", "clave123");
        sistema.agregarMedico(medico);

        Medico resultado = sistema.buscarMedicoPorCedula("201");
        assertNotNull(resultado);
        assertEquals("Andrés Gómez", resultado.getNombre());
    }
    @Test
    void testBuscarPacientePorCedula() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        Paciente lucia = new Paciente("Lucía Ramírez", "110", "Calle 123", "3001234567", "seguimiento por migrañas");
        sistema.agregarPaciente(lucia);

        Paciente paciente = sistema.buscarPaciente("110");
        assertNotNull(paciente);
        assertEquals("Lucía Ramírez", paciente.getNombre());
    }
    @Test
    void testEspecialidadDeMedico() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        sistema.getListaMedicos().clear();

        Medico medico = new Medico("María Fernanda", "206", "Av B", "3131234567", "Cardiologia", "clave456");
        sistema.agregarMedico(medico);

        Medico resultado = sistema.buscarMedicoPorCedula("206");
        assertNotNull(resultado);
        assertEquals("Cardiologia", resultado.getEspecialidad());
    }
    @Test
    void testMedicoNoExiste() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        sistema.getListaMedicos().clear();

        Medico medico = sistema.buscarMedicoPorCedula("999");
        assertNull(medico, "Debe retornar null si el médico no existe");
    }
    @Test
    void testAgregarNuevoMedico() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        sistema.getListaMedicos().clear();

        Medico nuevo = new Medico("Dr. Julio", "777", "Camelias", "12334567", "Neurología", "1234");
        sistema.agregarMedico(nuevo);

        Medico medico = sistema.buscarMedicoPorCedula("777");
        assertNotNull(medico);
        assertEquals("Dr. Julio", medico.getNombre());
    }
    @Test
    void testListaMedicosNoVacia() {
        SistemaHospitalario sistema = SistemaHospitalario.getInstance();
        sistema.getListaMedicos().clear();

        Medico m = new Medico("Médico X", "300", "Calle X", "3000000000", "Pediatría", "clave");
        sistema.agregarMedico(m);

        assertFalse(sistema.getListaMedicos().isEmpty(), "La lista de médicos no debe estar vacía");
    }
    
}