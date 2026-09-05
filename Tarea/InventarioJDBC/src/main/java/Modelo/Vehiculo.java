package Modelo;

public class Vehiculo {
    private int id;
    private String marca;
    private String modelo;
    private int anio;

    public Vehiculo(int id, String marca, String modelo, int anio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
}