package org.duoc;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.duoc.controllers.MenuController;
import org.duoc.controllers.VehiculoController;
import org.duoc.utils.MenuUtil;

/**
 * Clase principal de la aplicación DriveQuest Rentals.
 * Gestiona el ciclo de vida del sistema, mostrando menús, procesando opciones
 * del usuario
 * y coordinando la interacción con el controlador de vehículos y la
 * persistencia de datos.
 * 
 * @author Karla Santibáñez
 * @version 1.0
 */
public class Main {
    /**
     * Método principal que inicia la aplicación.
     * Carga los vehículos desde archivo, muestra el menú principal y gestiona las
     * opciones seleccionadas por el usuario.
     * Al finalizar, guarda los vehículos en archivo y muestra el mensaje de salida.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        VehiculoController controller = new VehiculoController();
        MenuUtil menu = new MenuUtil();
        Scanner sc = new Scanner(System.in);
        MenuController menuController = new MenuController(controller, menu, sc);

        // Cargar vehículos desde archivo al iniciar
        controller.cargarVehiculos("vehiculos.csv");

        menu.welcomeMessage();
        boolean running = true;
        while (running) {
            try {
                menu.mainMenu();
                System.out.println("Seleccione una opción:");
                int option = sc.nextInt();
                sc.nextLine();
                if (option == 6) {
                    running = false;
                } else {
                    menuController.ejecutarMenu(option);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                sc.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico válido.");
            } catch (NullPointerException e) {
                System.out.println("Error: Se ha producido un error inesperado. Por favor, intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Guardar vehículos al finalizar
        controller.guardarVehiculos("vehiculos_out.csv");
        menu.exitMessage();
        sc.close();
    }
}