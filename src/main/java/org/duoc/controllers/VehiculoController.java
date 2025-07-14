package org.duoc.controllers;

import java.util.List;
import java.util.Scanner;

import org.duoc.models.Vehiculo;
import org.duoc.models.VehiculoCarga;
import org.duoc.models.VehiculoPasajeros;
import org.duoc.services.VehiculoService;
import org.duoc.utils.FileIOUtil;

/**
 * Controlador para la gestión de vehículos en DriveQuest Rentals.
 * Centraliza la lógica de interacción entre la interfaz de usuario y el servicio de vehículos,
 * permitiendo agregar, arrendar, listar, filtrar y persistir vehículos de pasajeros y carga.
 */
public class VehiculoController {
    private final VehiculoService service;

    // Constructor que inicializa el servicio de vehículos
    public VehiculoController() {
        this.service = new VehiculoService();
    }

    // Clase interna para manejar los datos comunes de un vehículo
    public static class DatosVehiculo {
        public String patente;
        public String marca;
        public String modelo;
        public int diasArriendo;
        public int valorDiario;
        public int puertas;
        public int anio;
    }

    
    /**
     * Agrega un vehículo de pasajeros consultando los datos al usuario.
     * Valida los datos ingresados y delega la creación al servicio.
     * @param scanner Scanner para la entrada de datos.
     * @return true si el vehículo fue agregado correctamente, false en caso contrario.
     */
    public boolean agregarVehiculoPasajeros(Scanner scanner) {
        DatosVehiculo datos = consultarDatosComunes(scanner);
        if (datos == null)
            return false;
        try {
            System.out.print("Capacidad pasajeros: ");
            int capacidad = Integer.parseInt(scanner.nextLine());
            if (capacidad <= 0) {
                System.out.println("La capacidad de pasajeros debe ser mayor a cero.");
                return false;
            }
            VehiculoPasajeros v = new VehiculoPasajeros(
                    datos.patente, datos.marca, datos.modelo, datos.diasArriendo,
                    datos.valorDiario, datos.puertas, datos.anio, capacidad);
            boolean agregado = service.addVehiculo(v);
            if (!agregado) {
                System.out.println("No se pudo agregar el vehículo. ¿La patente ya existe?");
            }
            return agregado;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido para la capacidad.");
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar vehículo de pasajeros: " + e.getMessage());
            return false;
        }
    }

    /**
     * Agrega un vehículo de carga consultando los datos al usuario.
     * Valida los datos ingresados y delega la creación al servicio.
     * @param scanner Scanner para la entrada de datos.
     * @return true si el vehículo fue agregado correctamente, false en caso contrario.
     */
    public boolean agregarVehiculoCarga(Scanner scanner) {
        DatosVehiculo datos = consultarDatosComunes(scanner);
        if (datos == null)
            return false;
        try {
            System.out.print("Capacidad carga: ");
            int capacidad = Integer.parseInt(scanner.nextLine());
            if (capacidad <= 0) {
                System.out.println("La capacidad de carga debe ser mayor a cero.");
                return false;
            }
            VehiculoCarga v = new VehiculoCarga(
                    datos.patente, datos.marca, datos.modelo, datos.diasArriendo,
                    datos.valorDiario, datos.puertas, datos.anio, capacidad);
            boolean agregado = service.addVehiculo(v);
            if (!agregado) {
                System.out.println("No se pudo agregar el vehículo. ¿La patente ya existe?");
            }
            return agregado;
        }catch(NumberFormatException e){
            System.out.println("Error: Debe ingresar un valor numérico válido para la capacidad.");
            return false;
        } catch (Exception e) {
            System.out.println("Error al ingresar datos específicos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carga los vehículos desde un archivo CSV en un hilo sincronizado.
     * @param ruta Ruta del archivo CSV de origen.
     */
    public void cargarVehiculos(String ruta) {
        Thread hilo = new Thread(() -> {
            synchronized (service.getVehiculosMap()) {
                try {
                    System.out.println("Cargando vehículos...");
                    FileIOUtil.cargarVehiculosCSV(service.getVehiculosMap(), ruta);
                    System.out.println(service.getVehiculosMap().size()+" vehículos cargados correctamente.");
                } catch (Exception e) {
                    System.out.println("Error al cargar vehículos: " + e.getMessage());
                }
            }
        });
        hilo.setName("CargaVehiculosThread");
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("La carga fue interrumpida.");
        }
    }

    /**
     * Guarda los vehículos en un archivo CSV de forma sincronizada.
     * @param ruta Ruta del archivo CSV de destino.
     */
    public void guardarVehiculos(String ruta) {
        synchronized (service.getVehiculosMap()) {
            try {
                FileIOUtil.guardarVehiculosCSV(service.getVehiculosMap().values(), ruta);
                System.out.println("Vehículos guardados correctamente.");
            } catch (Exception e) {
                System.out.println("Error al guardar vehículos: " + e.getMessage());
            }
        }
    }

    /**
     * Lista todos los vehículos registrados.
     * @return Lista de vehículos.
     */
    public List<Vehiculo> listarTodos() {
        return service.listVehiculos();
    }

    /**
     * Lista todos los vehículos de pasajeros registrados.
     * @return Lista de VehiculoPasajeros.
     */
    public List<VehiculoPasajeros> listarPasajeros() {
        return service.listarVehiculosPasajeros();
    }

    /**
     * Lista todos los vehículos de carga registrados.
     * @return Lista de VehiculoCarga.
     */
    public List<VehiculoCarga> listarCarga() {
        return service.listarVehiculosCarga();
    }

    /**
     * Filtra vehículos con arriendo largo (>= 7 días).
     * @return Lista de vehículos con arriendo largo.
     */
    public List<Vehiculo> filtrarArriendosLargos() {
        return service.filtrarArriendosLargos();
    }

    /**
     * Filtra vehículos con arriendo corto (<= 6 días).
     * @return Lista de vehículos con arriendo corto.
     */
    public List<Vehiculo> filtrarArriendosCortos() {
        return service.filtrarArriendosCortos();
    }

    /**
     * Muestra las boletas de todos los vehículos registrados.
     */
    public void mostrarBoletas() {
        service.mostrarBoletasEmitidas();
    }

    /**
     * Consulta los datos comunes de un vehículo solicitando la entrada al usuario.
     * Valida los datos ingresados y retorna un objeto auxiliar con los datos.
     * @param scanner Scanner para la entrada de datos.
     * @return Objeto DatosVehiculo con los datos ingresados, o null si hay error.
     */
    public DatosVehiculo consultarDatosComunes(Scanner scanner) {
        DatosVehiculo datos = new DatosVehiculo();
        try {
            System.out.print("Patente: ");
            datos.patente = scanner.nextLine().trim();
            if (datos.patente.isEmpty()) {
                System.out.println("La patente no puede estar vacía.");
                return null;
            }
            System.out.print("Marca: ");
            datos.marca = scanner.nextLine().trim();
            if (datos.marca.isEmpty()) {
                System.out.println("La marca no puede estar vacía.");
                return null;
            }
            System.out.print("Modelo: ");
            datos.modelo = scanner.nextLine().trim();
            if (datos.modelo.isEmpty()) {
                System.out.println("El modelo no puede estar vacío.");
                return null;
            }
            datos.diasArriendo = 0;
            System.out.print("Valor diario: ");
            datos.valorDiario = Integer.parseInt(scanner.nextLine());
            if (datos.valorDiario <= 0) {
                System.out.println("El valor diario debe ser mayor a cero.");
                return null;
            }
            System.out.print("Puertas: ");
            datos.puertas = Integer.parseInt(scanner.nextLine());
            if (datos.puertas <= 0) {
                System.out.println("El número de puertas debe ser mayor a cero.");
                return null;
            }
            System.out.print("Año: ");
            datos.anio = Integer.parseInt(scanner.nextLine());
            if (datos.anio < 1900 || datos.anio > java.time.Year.now().getValue()) {
                System.out.println("El año debe estar entre 1900 y el actual.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada no válida. Asegúrese de ingresar números donde se requiera.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al ingresar datos comunes: " + e.getMessage());
            return null;

        }
        return datos;
    }

    /**
     * Realiza el proceso de arriendo de un vehículo de pasajeros por patente.
     * Solicita los días de arriendo y muestra la boleta si el arriendo es exitoso.
     * @param patente Patente del vehículo a arrendar.
     * @param sc Scanner para la entrada de datos.
     * @return true si el arriendo fue exitoso, false en caso contrario.
     */
    public boolean arrendarVehiculoPasajeros(String patente, Scanner sc) {
        VehiculoPasajeros v = null;
        // Buscar el vehículo por patente
        for (VehiculoPasajeros vp : listarPasajeros()) {
            if (vp.getPatente().equalsIgnoreCase(patente)) {
                v = vp;
                break;
            }
        }
        if (v == null) {
            System.out.println("No se encontró un vehículo de pasajeros con esa patente.");
            return false;
        }
        if (v.getDiasArriendo() > 0) {
            System.out.println("El vehículo ya está arrendado.");
            return false;
        }
        try {
            System.out.print("Ingrese días de arriendo: ");
            int dias = Integer.parseInt(sc.nextLine());
            if (dias <= 0) {
                System.out.println("Los días deben ser mayores a cero.");
                return false;
            }
            v.setDiasArriendo(dias);
            v.mostrarBoleta();   // Muestra la boleta
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido de días.");
            return false;
        }
    }

    /**
     * Realiza el proceso de arriendo de un vehículo de carga por patente.
     * Solicita los días de arriendo y muestra la boleta si el arriendo es exitoso.
     * @param patente Patente del vehículo a arrendar.
     * @param sc Scanner para la entrada de datos.
     * @return true si el arriendo fue exitoso, false en caso contrario.
     */
    public boolean arrendarVehiculoCarga(String patente, Scanner sc) {
        VehiculoCarga v = null;
        // Buscar el vehículo por patente
        for (VehiculoCarga vc : listarCarga()) {
            if (vc.getPatente().equalsIgnoreCase(patente)) {
                v = vc;
                break;
            }
        }
        if (v == null) {
            System.out.println("No se encontró un vehículo de carga con esa patente.");
            return false;
        }
        if (v.getDiasArriendo() > 0) {
            System.out.println("El vehículo ya está arrendado.");
            return false;
        }
        try {
            System.out.print("Ingrese días de arriendo: ");
            int dias = Integer.parseInt(sc.nextLine());
            if (dias <= 0) {
                System.out.println("Los días deben ser mayores a cero.");
                return false;
            }
            v.setDiasArriendo(dias);
            v.mostrarBoleta();
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido de días.");
            return false;
        }
    }
}
