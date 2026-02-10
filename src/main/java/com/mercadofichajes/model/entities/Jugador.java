package com.mercadofichajes.model.entities;

public class Jugador {

    // Atributos
    private int id;
    private String nombre;
    private int edad;
    private String posicion;
    private double valorMercado;
    private int equipoId;           // ID del equipo al que pertenece
    private String nombreEquipo;    // Para mostrar en la interfaz

    // Constructor vacío (necesario para JavaFX)
    public Jugador() {
    }

    // Constructor con todos los parámetros
    public Jugador(int id, String nombre, int edad, String posicion,
                   double valorMercado, int equipoId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.valorMercado = valorMercado;
        this.equipoId = equipoId;
    }

    // Getters y Setters

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nombre + " - " + posicion + " (" + valorMercado + "€)";
    }
}