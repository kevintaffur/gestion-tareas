package org.example.services;

import org.example.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface ITareaService {
    List<Tarea> obtenerTodas();
    List<Tarea> obtenerOdenPrioridad();
    List<Tarea> obtenerOrdenFechaVencimiento();
    List<Tarea> obtenerCompletadas();
    List<Tarea> obtenerPendientes();
    Optional<Tarea> obtenerPorId(int id);
    void guardar(Tarea tarea);
    boolean modificar(int id, Tarea tarea);
    boolean eliminar(int id);
}
