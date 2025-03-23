package org.example.entities;

import java.util.Date;

public class Tarea {
    private static int contador = 1;
    private int id;
    private String titulo;
    private String descripcion;
    private Date fechaVencimiento;
    private String prioridad;
    private boolean completado;

    // tarea siempre se inicia como pendiente (completado=false)
    public Tarea(String titulo, String descripcion, Date fechaVencimiento, String prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.prioridad = prioridad;
        this.completado = false;
        this.id = contador;
        contador++;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "-----------------------------------------------------------------" + "\n" +
                "ID: " + id + "\n" +
                "Título: " + titulo + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Fecha de Vencimiento: " + fechaVencimiento + "\n" +
                "Prioridad: " + prioridad + "\n" +
                "Completado: " + (completado ? "Sí" : "No") + "\n" +
                "-----------------------------------------------------------------";
    }
}
