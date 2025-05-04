package co.edu.uniquindio.uq.controller;

import co.edu.uniquindio.uq.model.EstadoSala;
import co.edu.uniquindio.uq.model.Sala;

public interface GestionSalas {

    void asignarSala(Sala sala);
    void liberarSala(Sala sala);
    void cambiarEstadoSala(Sala sala, EstadoSala nuevoEstado);
}
