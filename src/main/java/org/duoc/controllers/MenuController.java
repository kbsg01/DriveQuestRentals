package org.duoc.controllers;

import java.util.Scanner;

import org.duoc.models.Vehiculo;
import org.duoc.utils.MenuUtil;

/**
 * Controlador de menú principal de la aplicación.
 * Encapsula la lógica de interacción con el usuario y delega las operaciones
 * a los controladores y utilidades correspondientes.
 * Permite agregar, arrendar, listar vehículos, mostrar boletas y filtrar arriendos.
 */
public class MenuController {

    private final VehiculoController controller;
    private final MenuUtil menu;
    private final Scanner sc;

    /**
     * Constructor de MenuController.
     * @param controller Controlador de vehículos.
     * @param menu Utilidad para mostrar menús.
     * @param sc Scanner para entrada de datos.
     */
    public MenuController(VehiculoController controller, MenuUtil menu, Scanner sc) {
        this.controller = controller;
        this.menu = menu;
        this.sc = sc;
    }

    /**
     * Ejecuta la opción seleccionada del menú principal.
     * @param opcion Número de opción seleccionada por el usuario.
     */
    public void ejecutarMenu(int opcion) {
        switch (opcion) {
            case 1 -> agregarVehiculo();
            case 2 -> arriendoVehiculo();
            case 3 -> listarVehiculos();
            case 4 -> mostrarBoletas();
            case 5 -> mostrarArriendosFiltrados();
            default -> System.out.println("Opción no válida.");
        }
    }

    /**
     * Muestra el submenú para agregar vehículos y gestiona la creación según el tipo.
     */
    public void agregarVehiculo() {
        menu.addVehiculoMenu();
        System.out.print("Seleccione tipo: ");
        String tipo = sc.nextLine().trim();
        if (tipo.equals("1")) {
            boolean ok = controller.agregarVehiculoPasajeros(sc);
            System.out.println(ok ? "Vehículo de pasajeros agregado correctamente." : "No se pudo agregar el vehículo.");
        } else if (tipo.equals("2")) {
            boolean ok = controller.agregarVehiculoCarga(sc);
            System.out.println(ok ? "Vehículo de carga agregado correctamente." : "No se pudo agregar el vehículo.");
        } else {
            System.out.println("Opción de tipo de vehículo no válida.");
        }
    }

    /**
     * Muestra el submenú de arriendo y gestiona el proceso de arriendo por tipo de vehículo.
     */
    public void arriendoVehiculo() {
        menu.addArriendoMenu();
        System.out.print("Seleccione tipo: ");
        String tipoArr = sc.nextLine().trim();
        if (tipoArr.equals("1")) {
            System.out.println("Vehículos de pasajeros disponibles para arriendo:");
            controller.listarPasajeros().stream()
                    .filter(v -> v.getDiasArriendo() == 0)
                    .forEach(Vehiculo::mostrarDetalle);
            System.out.print("Ingrese la patente del vehículo a arrendar: ");
            String patente = sc.nextLine().trim();
            if (!controller.arrendarVehiculoPasajeros(patente, sc)) {
                System.out.println("No se pudo arrendar el vehículo. Valida la patente e intenta de nuevo.");
            }
        } else if (tipoArr.equals("2")) {
            System.out.println("Vehículos de carga disponibles para arriendo:");
            controller.listarCarga().stream()
                    .filter(v -> v.getDiasArriendo() == 0)
                    .forEach(Vehiculo::mostrarDetalle);
            System.out.print("Ingrese la patente del vehículo a arrendar: ");
            String patente = sc.nextLine().trim();
            if (!controller.arrendarVehiculoCarga(patente, sc)) {
                System.out.println("No se pudo arrendar el vehículo. Valida la patente e intenta de nuevo.");
            }
        } else if (tipoArr.equals("3")) {
            System.out.println("Volviendo al menú principal...");
        } else {
            System.out.println("Opción de arriendo no válida.");
        }
    }

    /**
     * Muestra el submenú de listado y gestiona la visualización de vehículos por tipo.
     */
    public void listarVehiculos() {
        menu.listVehiculoMenu();
        System.out.print("Seleccione tipo: ");
        String tipoList = sc.nextLine().trim();
        if (tipoList.equals("1")) {
            System.out.println("Listando vehículos de pasajeros:\n");
            System.out.printf("| %-10s | %-10s | %-10s | %-4s | %-6s | %-8s | %-7s |\n",
    "Patente", "Marca", "Modelo", "Año", "Puertas", "Capacidad", "Valor");
            controller.listarPasajeros().forEach(v -> v.filaDetalle(false));
        } else if (tipoList.equals("2")) {
            System.out.println("Listando vehículos de carga: \n");
            System.out.printf("| %-10s | %-10s | %-10s | %-4s | %-6s | %-8s | %-7s |\n",
    "Patente", "Marca", "Modelo", "Año", "Puertas", "Capacidad", "Valor");
            controller.listarCarga().forEach(v -> v.filaDetalle(false));
        } else if (tipoList.equals("3")) {
            System.out.println("Listando todos los vehículos:\n");
            System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-4s | %-6s | %-8s | %-7s |\n",
    "Tipo", "Patente", "Marca", "Modelo", "Año", "Puertas", "Capacidad", "Valor");
            controller.listarTodos().forEach(v -> v.filaDetalle(true));
        } else {
            System.out.println("Opción de listado no válida.");
        }
    }

    /**
     * Muestra las boletas de todos los vehículos registrados.
     */
    public void mostrarBoletas() {
        controller.mostrarBoletas();
    }

    /**
     * Muestra el submenú de arriendos filtrados y gestiona la visualización según el tipo de arriendo.
     */
    public void mostrarArriendosFiltrados() {
        menu.showArriendosMenu();
        System.out.print("Seleccione tipo: ");
        String tipoArrendado = sc.nextLine().trim();
        if (tipoArrendado.equals("1")) {
            controller.filtrarArriendosLargos().forEach(Vehiculo::mostrarDetalle);
        } else if (tipoArrendado.equals("2")) {
            controller.filtrarArriendosCortos().forEach(Vehiculo::mostrarDetalle);
        } else {
            System.out.println("Opción de arriendo no válida.");
        }
    }

}