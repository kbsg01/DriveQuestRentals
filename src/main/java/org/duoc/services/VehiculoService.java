package org.duoc.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.duoc.interfaces.IBoleta;
import org.duoc.models.*;

/**
 * Servicio para la gestión de vehículos en DriveQuest Rentals.
 * Proporciona métodos para agregar, buscar, listar, filtrar y mostrar boletas de vehículos,
 * asegurando la unicidad de patentes y la concurrencia segura mediante sincronización.
 * 
 * @author Karla Santibáñez
 */
public class VehiculoService {
    private final Map<String, Vehiculo> vehiculos = Collections.synchronizedMap(new HashMap<>());

    /**
     * Devuelve el mapa sincronizado de vehículos registrados.
     * @return Map con la patente como clave y el vehículo como valor.
     */
    public Map<String, Vehiculo> getVehiculosMap() {
        return vehiculos;
    }

    /**
     * Agrega un vehículo al sistema, validando unicidad de patente y datos.
     * @param vehiculo Vehículo a agregar.
     * @return true si se agregó correctamente, false si la patente existe o los datos son inválidos.
     */
    public boolean addVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            System.out.println("Error: El vehículo no puede ser nulo");
            return false;
        }
        if (vehiculo.getPatente().isEmpty()) {
            System.out.println("Error: La patente no puede estar vacía");
            return false;
        }
        synchronized (vehiculos) {
            if (vehiculos.containsKey(vehiculo.getPatente())) {
                System.out.println("Error: Ya existe un vehículo con la patente " + vehiculo.getPatente());
                return false;
            }
            vehiculos.put(vehiculo.getPatente(), vehiculo);
            return true;
        }
    }

    /**
     * Lista todos los vehículos registrados.
     * @return Lista de vehículos.
     */
    public List<Vehiculo> listVehiculos() {
        synchronized (vehiculos) {
            return new ArrayList<>(vehiculos.values());
        }
    }

    /**
     * Lista todos los vehículos de pasajeros registrados.
     * @return Lista de VehiculoPasajeros.
     */
    public List<VehiculoPasajeros> listarVehiculosPasajeros() {
        List<VehiculoPasajeros> lista = new ArrayList<>();
        synchronized(vehiculos){
            for (Vehiculo v : vehiculos.values()) {
                if (v instanceof VehiculoPasajeros) {
                    lista.add((VehiculoPasajeros) v);
                }
            }
        }
        return lista;
    }

    /**
     * Lista todos los vehículos de carga registrados.
     * @return Lista de VehiculoCarga.
     */
    public List<VehiculoCarga> listarVehiculosCarga() {
        List<VehiculoCarga> lista = new ArrayList<>();
        synchronized(vehiculos){
            for (Vehiculo v : vehiculos.values()) {
                if (v instanceof VehiculoCarga) {
                    lista.add((VehiculoCarga) v);
                }
            }
        }
        return lista;
    }

    /**
     * Filtra vehículos con arriendo largo (>= 7 días).
     * @return Lista de vehículos con arriendo largo.
     */
    public List<Vehiculo> filtrarArriendosLargos() {
        List<Vehiculo> lista = new ArrayList<>();
        synchronized (vehiculos) {
            for (Vehiculo v : vehiculos.values()) {
                if (v.getDiasArriendo() >= 7) {
                    lista.add(v);
                }
            }
        }
        return lista;
    }

    /**
     * Cuenta la cantidad de vehículos con arriendo largo.
     * @return Número de vehículos con arriendo largo.
     */
    public int contarArriendosLargos() {
        return filtrarArriendosLargos().size();
    }

    /**
     * Filtra vehículos con arriendo corto (<= 6 días).
     * @return Lista de vehículos con arriendo corto.
     */
    public List<Vehiculo> filtrarArriendosCortos() {
        List<Vehiculo> lista = new ArrayList<>();
        synchronized (vehiculos) {
            for (Vehiculo v : vehiculos.values()) {
                if (v.getDiasArriendo() <= 6) {
                    lista.add(v);
                }
            }
        }
        return lista;
    }

    /**
     * Cuenta la cantidad de vehículos con arriendo corto.
     * @return Número de vehículos con arriendo corto.
     */
    public int contarArriendosCortos() {
        return filtrarArriendosCortos().size();
    }

    /**
     * Muestra todas las boletas emitidas por los vehículos registrados.
     */
    public void mostrarBoletasEmitidas() {
        synchronized( vehiculos){
            for (Vehiculo v : vehiculos.values()) {
                if (v instanceof IBoleta && v.getDiasArriendo() > 0) {
                    ((IBoleta) v).mostrarBoleta();
                }
            }
        }
    }

    /**
     * Busca y retorna un vehículo por su patente.
     * @param patente Patente del vehículo a buscar.
     * @return Vehículo correspondiente o null si no existe.
     */
    public Vehiculo getVehiculoByPatente(String patente) {
        if (patente == null || patente.isEmpty()) {
            System.out.println("Error: La patente no puede ser nula o vacía");
            return null;
        }
        synchronized(vehiculos){
            Vehiculo vehiculo = vehiculos.get(patente);
            if (vehiculo == null) {
                System.out.println("Error: No se encontró un vehículo con la patente " + patente);
            }
            return vehiculo;
        }
    }

}
