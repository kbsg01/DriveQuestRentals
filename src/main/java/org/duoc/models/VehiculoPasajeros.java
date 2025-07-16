package org.duoc.models;

import org.duoc.interfaces.IBoleta;

public class VehiculoPasajeros extends Vehiculo implements IBoleta {

    private int capacidadPasajeros;

    /**
     * Constructor por defecto de VehiculoPasajeros.
     **/
    public VehiculoPasajeros(){
        super();
    }

    /**
     * Constructor de VehiculoPasajeros con parámetros.
     * Este constructor inicializa un objeto VehiculoPasajeros con los valores proporcionados.
     * @param patente Patente del vehículo.
     * @param marca Marca del vehículo.
     * @param modelo Modelo del vehículo.
     * @param diasArriendo Días de arriendo del vehículo.
     * @param valorDiario Valor diario de arriendo del vehículo.
     * @param puertas Número de puertas del vehículo.
     * @param anio Año del vehículo.
     * @param capacidadPasajeros Capacidad de pasajeros del vehículo.
     * **/
    public VehiculoPasajeros(String patente, String marca, String modelo, int diasArriendo, int valorDiario, int puertas, int anio, int capacidadPasajeros){
        super(patente, marca, modelo, diasArriendo, valorDiario, puertas, anio);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }
    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    /**
     * Muestra los detalles del vehículo de pasajeros.
     * Este método imprime en consola la información del vehículo, incluyendo:
     * Patente, Marca, Modelo, Año, Número de puertas, Capacidad de pasajeros, Días de arriendo, Valor diario de arriendo.
     * **/
    @Override
    public void mostrarDetalle() {
        System.out.println("====== Detalles del Vehículo ======");
        System.out.println("Tipo: Vehículo de Pasajeros");
        System.out.println("===================================");
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Año: " + getAnio());
        System.out.println("Puertas: " + getPuertas());
        System.out.println("Capacidad de pasajeros: " + capacidadPasajeros);
        System.out.println("Días de Arriendo: " + getDiasArriendo());
        System.out.println("Valor Diario: $" + getValorDiario());
        System.out.println("===================================");
    }

    @Override
    public void filaDetalle(boolean mostrar) {
        if(mostrar){
            System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-4d | %-7d | %-9d | %-7s |\n", 
                "Pasajeros", getPatente(), getMarca(), getModelo(), getAnio(), getPuertas(), getCapacidadPasajeros(), "$"+getValorDiario());
        } else {
            System.out.printf("| %-10s | %-10s | %-10s | %-4s | %-7s | %-9s | %-7s |\n", 
            getPatente(), getMarca(), getModelo(), getAnio(), getPuertas(), getCapacidadPasajeros(), "$"+getValorDiario());
        }
        
    }

    /**
     * Muestra la boleta de arriendo del vehículo de pasajeros.
     * Este método calcula el subtotal, descuento, impuesto y total a pagar,
     * y luego imprime la boleta con los detalles del arriendo.
    */
    @Override
    public void mostrarBoleta() {
        int arriendo = getDiasArriendo();
        // Calcular subtotal, descuento, impuesto y total
        int subtotal = getDiasArriendo() * getValorDiario();
        int descuento = (int) (subtotal * dctoPasajeros);
        int impuesto = (int ) ((subtotal - descuento) * iva);
        int total = subtotal - descuento + impuesto;

        imprimirBoleta(arriendo, subtotal, descuento, impuesto, total);
    }

    /**
     * Imprime la boleta del arriendo del vehículo de pasajeros.
     * Este método recibe los valores calculados de subtotal, descuento, impuesto y total, 
     * además de los días de arriendo y los imprime en un formato legible.
     * @param arriendo Días de arriendo del vehículo.
     * @param subtotal El subtotal del arriendo.
     * @param descuento El descuento aplicado al subtotal.
     * @param imp El impuesto calculado sobre el subtotal menos el descuento por el IVA.
     * @param total El total a pagar después de aplicar el descuento y el impuesto.
     * 
    */
    @Override
    public void imprimirBoleta(int arriendo, int subtotal, int descuento, int impuesto, int total){
        System.out.println("===== Boleta Electrónica - DriveQuest Rentals =====");
        System.out.println("Tipo de Vehículo: Vehículo de pasajeros");
        System.out.println("Patente: " + getPatente());
        System.out.println("Días de Arriendo: " + arriendo);
        System.out.println("===================================================");
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Descuento: -$"+ descuento);
        System.out.println("IVA (19%): $"+ impuesto);
        System.out.println("Total a Pagar: $" + total);
        System.out.println("\n===================================================\n");
    }
}
