package org.duoc.models;

/**
 * Clase abstracta Vehiculo que define las propiedades y métodos comunes
 * para todos los vehículos.
 */
public abstract class Vehiculo {

    private String patente;
    private String marca;
    private String modelo;
    private int diasArriendo;
    private int valorDiario;
    private int puertas;
    private int anio;


    public Vehiculo(){}

    public Vehiculo(String patente, String marca, String modelo, int diasArriendo,int valorDiario, int puertas, int anio) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.diasArriendo = diasArriendo;
        this.valorDiario = valorDiario;
        this.puertas = puertas;
        this.anio = anio;
    }

    public abstract void mostrarDetalle();

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getDiasArriendo() { return diasArriendo; }
    public void setDiasArriendo(int diasArriendo) { this.diasArriendo = diasArriendo; }

    public int getValorDiario() { return valorDiario; }
    public void setValorDiario(int valorDiario) { this.valorDiario = valorDiario; }

    public int getPuertas() { return puertas; }
    public void setPuertas(int puertas) { this.puertas = puertas; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

}
