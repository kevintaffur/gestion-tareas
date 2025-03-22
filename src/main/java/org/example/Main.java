package org.example;

import org.example.entities.Tarea;
import org.example.services.ITareaService;
import org.example.services.TareaService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ITareaService tareaService = new TareaService();

    public static void main(String[] args) {
        System.out.println("Gestión de Tareas");
        while (true) {
            System.out.println("1. Agregar tarea:");
            System.out.println("2. Modificar tarea:");
            System.out.println("3. Eliminar tarea:");
            System.out.println("4. Mostrar tareas:");
            System.out.println("5. Ordenar tareas por prioridad:");
            System.out.println("6. Ordenar tareas por fecha de vencimiento:");
            System.out.println("7. Salir:");

            System.out.print("Ingrese una opción: ");
            int opcion = 0;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                System.out.println("Ingrese una opción válida.");
            }

            if (opcion == 7) {
                break;
            }

            switch (opcion){
                case 1:
                    AgregarTarea();
                    break;
                case 2:
                    ModificarTarea();
                    break;
                case 3:
                    EliminarTarea();
                    break;
                case 4:
                    MostrarTareas();
                    break;
                case 5:
                    OrdenarTareasPorPrioridad();
                    break;
                case 6:
                    OrdenarTareasPorFechaVencimiento();
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    public static void AgregarTarea() {
        System.out.println("Agregar tarea");
        String titulo = "";
        do {
            System.out.print("Ingrese un titulo: ");
            titulo = sc.nextLine();
        } while (titulo.trim().equals(""));
        String descripcion = "";
        do {
            System.out.print("Ingrese una descripcion: ");
            descripcion = sc.nextLine();
        } while (descripcion.trim().equals(""));
        Date fecha = null;
        do {
            try {
                System.out.print("Ingrese una fecha de vencimiento (DD/MM/YYYY): ");
                String fechaString = sc.nextLine();
                String[] fechaArr = fechaString.split("/");
                fecha = new Date(Integer.parseInt(fechaArr[2]), Integer.parseInt(fechaArr[1]), Integer.parseInt(fechaArr[0]));
            } catch (Exception ex) {
                System.out.println("Ingrese una fecha válida.");
            }
        } while (fecha == null);
        String prioridad = "";
        do {
            System.out.print("Ingrese una prioridad (ALTA, MEDIA, BAJA): ");
            prioridad = sc.nextLine();
            if (prioridad.trim().toUpperCase().equals("ALTA") ||
                prioridad.trim().toUpperCase().equals("MEDIA") ||
                prioridad.trim().toUpperCase().equals("BAJA")) {
                break;
            }
            prioridad = "";
        } while (prioridad.trim().equals(""));
        tareaService.guardar(new Tarea(titulo, descripcion, fecha, prioridad.toUpperCase()));
        System.out.println("Tarea agregada satisfactoriamente.");
    }

    public static void ModificarTarea() {}

    public static void EliminarTarea() {
        System.out.println("Eliminar tarea");
        if (!MostrarTareas()){
            return;
        }
        int id = 0;
        do {
            try {
                System.out.print("Ingrese el id de la tarea a eliminar: ");
                id = Integer.parseInt(sc.nextLine());

                if (!tareaService.eliminar(id)) {
                    System.out.println("Tarea no existente.");
                    id = 0;
                } else {
                    System.out.println("Tarea eliminada satisfactoriamente.");
                }
            } catch (Exception ex) {
                System.out.println("Ingrese un id de tarea válido.");
            }
        } while (id == 0);
    }

    public static boolean MostrarTareas() {
        List<Tarea> tareas = tareaService.obtenerTodas();
        if (tareas.isEmpty()) {
            System.out.println("No tiene tareas guardadas.");
            return false;
        }
        System.out.println("Estas son las tareas guardadas:");
        for (Tarea tarea : tareas) {
            System.out.println(tarea.toString());
        }
        return true;
    }

    public static void OrdenarTareasPorPrioridad() {
        List<Tarea> tareas = tareaService.obtenerOdenPrioridad();
        if (tareas.isEmpty()) {
            System.out.println("No tiene tareas guardadas.");
            return;
        }
        System.out.println("Estas son las tareas por orden de prioridad:");
        for (Tarea tarea : tareas) {
            System.out.println(tarea.toString());
        }
    }

    public static void OrdenarTareasPorFechaVencimiento() {
        List<Tarea> tareas = tareaService.obtenerOrdenFechaVencimiento();
        if (tareas.isEmpty()) {
            System.out.println("No tiene tareas guardadas.");
            return;
        }
        System.out.println("Estas son las tareas por orden de fecha de vencimiento:");
        for (Tarea tarea : tareas) {
            System.out.println(tarea.toString());
        }
    }
}