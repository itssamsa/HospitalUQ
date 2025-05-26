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
}        