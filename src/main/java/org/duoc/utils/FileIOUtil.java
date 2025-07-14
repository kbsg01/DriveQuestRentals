package org.duoc.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.duoc.models.*;

/**
 * Utilidad para la lectura y escritura de vehículos en archivos CSV.
 * Permite guardar y cargar la colección de vehículos, gestionando el formato y las excepciones.
 * 
 * @author Karla Santibáñez
 */
public class FileIOUtil {

    /**
     * Guarda la colección de vehículos en un archivo CSV.
     * @param vehiculos Colección de vehículos a guardar.
     * @param ruta Ruta del archivo CSV de destino.
     */
    public static void guardarVehiculosCSV(Collection<Vehiculo> vehiculos, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            // Escribe la cabecera
            bw.write("TIPO,PATENTE,MARCA,MODELO,DIAS_ARRIENDO,VALOR_DIARIO,PUERTAS,ANIO,CAPACIDAD");
            bw.newLine();
            for (Vehiculo v : vehiculos) {
                String tipo = (v instanceof VehiculoPasajeros) ? "PASAJEROS" : "CARGA";
                bw.write(tipo + "," + v.getPatente() + "," + v.getMarca() + "," + v.getModelo() + "," +
                        v.getDiasArriendo() + "," + v.getValorDiario() + "," + v.getPuertas() + "," + v.getAnio());
                if (v instanceof VehiculoPasajeros) {
                    bw.write("," + ((VehiculoPasajeros) v).getCapacidadPasajeros());
                } else if (v instanceof VehiculoCarga) {
                    bw.write("," + ((VehiculoCarga) v).getCapacidadCarga());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar vehículos: " + e.getMessage());
        }
    }

    /**
     * Carga vehículos desde un archivo CSV y los agrega al mapa proporcionado.
     * Ignora líneas vacías y la cabecera.
     * @param vehiculos Mapa de vehículos donde se agregarán los datos cargados.
     * @param ruta Ruta del archivo CSV de origen.
     */
    public static void cargarVehiculosCSV(Map<String, Vehiculo> vehiculos, String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue; // Ignora líneas vacías
                if (primeraLinea) { // Salta la cabecera
                    primeraLinea = false;
                    if (linea.toUpperCase().startsWith("TIPO")) continue;
                }
                String[] datos = linea.split(",");
                if (datos.length < 9) {
                    System.out.println("Línea inválida (faltan datos), se ignora: " + linea);
                    continue;
                }
                String tipo = datos[0].trim();
                String patente = datos[1].trim();
                String marca = datos[2].trim();
                String modelo = datos[3].trim();
                int diasArriendo = Integer.parseInt(datos[4].trim());
                int valorDiario = Integer.parseInt(datos[5].trim());
                int puertas = Integer.parseInt(datos[6].trim());
                int anio = Integer.parseInt(datos[7].trim());
                Vehiculo v = null;
                if ("PASAJEROS".equalsIgnoreCase(tipo)) {
                    int capacidadPasajeros = Integer.parseInt(datos[8].trim());
                    v = new VehiculoPasajeros(patente, marca, modelo, diasArriendo, valorDiario, puertas, anio, capacidadPasajeros);
                } else if ("CARGA".equalsIgnoreCase(tipo)) {
                    int capacidadCarga = Integer.parseInt(datos[8].trim());
                    v = new VehiculoCarga(patente, marca, modelo, diasArriendo, valorDiario, puertas, anio, capacidadCarga);
                } else {
                    System.out.println("Tipo de vehículo desconocido, se ignora: " + tipo);
                    continue;
                }
                if (v != null) {
                    vehiculos.put(patente, v); // Inserta en el Map, asegura unicidad
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar vehículos: " + e.getMessage());
        }
    }
}
