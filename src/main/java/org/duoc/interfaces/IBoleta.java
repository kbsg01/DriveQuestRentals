package org.duoc.interfaces;

/**
 * Interfaz IBoleta que define los métodos para mostrar boletas de vehículos.
 * Incluye constantes para IVA y descuentos específicos para vehículos de carga y pasajeros.
 */
public interface IBoleta {
    double iva = 0.19;
    double dctoCarga = 0.07;
    double dctoPasajeros = 0.12;

    void mostrarBoleta();
    void imprimirBoleta(int arriendo, int subtotal, int descuento, int imp, int total);
}
