public interface GestionSalas {

    void asignarSala(Sala sala);
    void liberarSala(Sala sala);
    void cambiarEstadoSala(Sala sala, EstadoSala nuevoEstado);
}
