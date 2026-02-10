package com.mercadoficajes.model.entities;

import java.time.LocalDate;

/**
 * Clase Jugador - Representa un jugador de fútbol
 *
 * REQUISITOS DEL EJERCICIO:
 * - Nombre del jugador
 * - Edad
 * - Posición en el campo
 * - Valor de mercado
 * - Equipo al que pertenece
 *
 * @author Grupo 2 - Fran y Alejandro
 */
public class Jugador {
    // Atributos principales
    private int id;                  // Identificador único
    private String nombre;           // Nombre del jugador
    private int edad;                // Edad del jugador
    private String posicion;         // Posición (Portero, Defensa, Centrocampista, Delantero)
    private double valorMercado;     // Valor de mercado en euros
    public Jugador(int id, String nombre, int edad, String posicion,
                   double valorMercado, int equipoId, String nombreEquipo) {
    private LocalDate fechaRegistro; // Fecha de registro en el sistema

    public Jugador() {
        this.posicion = posicion;
        this.valorMercado = valorMercado;
    // Constructor completo
    public Jugador(int id, String nombre, String posicion, int edad, String nacionalidad,
                   double precio, int equipoId, String nombreEquipo) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.precio = precio;
        this.equipoId = equipoId;
        this.nombreEquipo = nombreEquipo;
        this.fechaRegistro = LocalDate.now();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    // Método adicional para compatibilidad con TableView
    public String getNacionalidad() {
        return valorMercado;
    }

    public void setNacionalidad(String nacionalidad) {
        this.valorMercado = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return nombre + " - " + posicion + " (" + nombreEquipo + ")";
    }
}

