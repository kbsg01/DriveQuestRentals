package org.duoc.utils;

/**
 * Utilidad para mostrar los menús y mensajes de la aplicación DriveQuest Rentals.
 * Proporciona métodos para mostrar el menú principal, menús de opciones y mensajes de bienvenida y salida.
 * 
 * @author Karla Santibáñez
 */
public class MenuUtil {
    /**
     * Muestra el mensaje de bienvenida al usuario.
     */
    public void welcomeMessage() {
        System.out.println("===== Bienvenido a DriveQuest Rentals =====");
        System.out.println("===========================================");
    }

    /**
     * Muestra el mensaje de salida al usuario.
     */
    public void exitMessage() {
        System.out.println("Gracias por usar DriveQuest Rentals. ¡Hasta pronto!");
    }
    
    /**
     * Muestra el menú principal de la aplicación.
     */
    public void mainMenu() {
        System.out.println("============= Menú principal ==============");
        System.out.println("1. Agregar Vehículo");
        System.out.println("2. Arrendar Vehículo");
        System.out.println("3. Listar Vehículos");
        System.out.println("4. Mostrar boletas emitidas");
        System.out.println("5. Mostrar arriendos");
        System.out.println("6. Salir del sistema");
        System.out.println("===========================================");
    }

    /**
     * Muestra el menú para agregar vehículos.
     */
    public void addVehiculoMenu() {
        System.out.println("============= Menú - Agregar vehículo ==============");
        System.out.println("Seleccione el tipo de vehículo a agregar:");
        System.out.println("1. Vehículo de pasajeros");
        System.out.println("2. Vehículo de carga");
        System.out.println("3. Volver al menú principal");
        System.out.println("=====================================================");
    }

    /**
     * Muestra el menú para agregar arriendos.
     */
    public void addArriendoMenu(){
        System.out.println("============= Menú - Agregar arriendo ==============");
        System.out.println("Seleccione el tipo de vehiculo a arrendar:");
        System.out.println("1. Vehículo de pasajeros");
        System.out.println("2. Vehículo de carga");
        System.out.println("3. Volver al menú principal");
        System.out.println("=====================================================");
    }

    /**
     * Muestra el menú para listar vehículos.
     */
    public void listVehiculoMenu() {
        System.out.println("============= Menú - Listar vehículos ==============");
        System.out.println("Seleccione el tipo de vehículo a listar:");
        System.out.println("1. Vehículo de pasajeros");
        System.out.println("2. Vehículo de carga");
        System.out.println("3. Listar todos los vehículos");
        System.out.println("4. Volver al menú principal");
        System.out.println("=====================================================");
    }

    /**
     * Muestra el menú para mostrar arriendos.
     */
    public void showArriendosMenu() {
        System.out.println("============= Menú - Mostrar arriendos ==============");
        System.out.println("Seleccione el tipo de arriendo a mostrar:");
        System.out.println("1. Mostrar todos los arriendos (>= 7 días de arriendo)");
        System.out.println("2. Mostrar todos los arriendos (<= 6 días de arriendo)");
        System.out.println("3. Volver al menú principal");
        System.out.println("=====================================================");
    }

}
