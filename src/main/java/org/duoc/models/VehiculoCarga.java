package org.duoc.models;

import org.duoc.interfaces.IBoleta;

public class VehiculoCarga extends Vehiculo implements IBoleta {
    private int capacidadCarga;
    /**
     * Constructor por defecto de VehiculoCarga.
     **/
    public VehiculoCarga() {
        super();
    }

    public VehiculoCarga(String patente, String marca, String modelo, int diasArriendo, int valorDiario, int puertas, int anio, int capacidadCarga) {
        super(patente, marca, modelo, diasArriendo, valorDiario, puertas, anio);
        this.capacidadCarga = capacidadCarga;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }
    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    /**
     * Muestra los detalles del vehículo de carga.
     * Este método imprime en consola la información del vehículo, incluyendo:
     * Patente, Marca, Modelo, Año, Número de puertas, 
     * Capacidad de carga, Días de arriendo, Valor diario de arriendo.
    */
    @Override
    public void mostrarDetalle() {
        System.out.println("====== Detalles del Vehículo ======");
        System.out.println("Tipo: Vehículo de carga");
        System.out.println("===================================");
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Año: " + getAnio());
        System.out.println("Puertas: " + getPuertas());
        System.out.println("Capacidad de carga: " + capacidadCarga);
        System.out.println("Días de Arriendo: " + getDiasArriendo());
        System.out.println("Valor Diario: $" + getValorDiario());
        System.out.println("===================================");
    }


    /*
     * Muestra la boleta de arriendo del vehículo de carga.
     * Este método calcula el subtotal, descuento, impuesto y total a pagar,
     * y luego imprime la boleta con los detalles del arriendo.
    */
    @Override
    public void mostrarBoleta() {
        int arriendo = getDiasArriendo();
        // Calcular subtotal, descuento, impuesto y total
        int subtotal = getDiasArriendo() * getValorDiario();
        int descuento = (int) (subtotal*dctoCarga);
        int impuesto = (int) ((subtotal - descuento) * iva);
        int total = subtotal - descuento + impuesto;

        imprimirBoleta(arriendo, subtotal, descuento, impuesto, total);    
    }

    /**
     * Imprime la boleta del arriendo del vehículo de carga.
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
    public void imprimirBoleta(int arriendo, int subtotal, int descuento, int impuesto, int total) {
        System.out.println("===== Boleta Electrónica - DriveQuest Rentals =====");
        System.out.println("Tipo de Vehículo: Vehículo de carga");
        System.out.println("Días de Arriendo: " + arriendo);
        System.out.println("===================================================");
        System.out.println("Subtotal     :  $"+ subtotal);
        System.out.println("Descuento    : -$"+ descuento);
        System.out.println("IVA (19%)    :  $"+ impuesto);
        System.out.println("Total a Pagar:  $"+ total);
        System.out.println("===================================================");
    }
}
