package org.example.services;

import org.example.entities.Tarea;

import java.util.*;

public class TareaService implements ITareaService {
    private List<Tarea> tareas;
    private Map<String, Integer> prioridades = Map.of(
            "ALTA", 1,
            "MEDIA", 2,
            "BAJA", 3
    );

    public TareaService() {
        this.tareas = new ArrayList<>();
    }

    @Override
    public List<Tarea> obtenerTodas() {
        return this.tareas;
    }

    @Override
    public List<Tarea> obtenerOdenPrioridad() {
        return this.tareas.stream()
                .sorted(Comparator.comparingInt(tarea -> prioridades.get(tarea.getPrioridad())))
                .toList();
    }

    @Override
    public List<Tarea> obtenerOrdenFechaVencimiento() {
        return this.tareas.stream()
                .sorted(Comparator.comparing(Tarea::getFechaVencimiento))
                .toList();
    }

    @Override
    public Optional<Tarea> obtenerPorId(int id) {
        return this.tareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public void guardar(Tarea tarea) {
        this.tareas.add(tarea);
    }

    @Override
    public boolean modificar(int id, Tarea tarea) {
        Optional<Tarea> t = this.obtenerPorId(id);
        if (t.isPresent()) {
            Tarea tareaExistente = t.get();
            if (!tarea.getTitulo().trim().equals("")) {
                tareaExistente.setTitulo(tarea.getTitulo().trim());
            }
            if (!tarea.getDescripcion().trim().equals("")) {
                tareaExistente.setDescripcion(tarea.getDescripcion().trim());
            }
            if (tarea.getFechaVencimiento() != null) {
                tareaExistente.setFechaVencimiento(tarea.getFechaVencimiento());
            }
            if (!tarea.getPrioridad().trim().equals("")) {
                tareaExistente.setPrioridad(tarea.getPrioridad().trim());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        Optional<Tarea> tarea = this.obtenerPorId(id);
        if (tarea.isPresent()) {
            this.tareas.remove(tarea.get());
            return true;
        }
        return false;
    }
}
