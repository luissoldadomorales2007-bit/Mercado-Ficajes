package com.mercadoficajes.model.entities;

import java.time.LocalDate;

/**
 * Clase Traspaso - Representa un traspaso de jugador entre equipos
 *
 * REQUISITOS DEL EJERCICIO:
 * - Jugador involucrado
 * - Equipo de origen
 * - Equipo de destino
 * - Precio del traspaso
 *
 * @author Grupo 2 - Fran y Alejandro
 */
public class Transferencia {
    // Atributos principales
    private int id;                        // Identificador único del traspaso
    private int jugadorId;                 // ID del jugador traspasado
    private String nombreJugador;          // Nombre del jugador (para mostrar)
    private int equipoOrigenId;            // ID del equipo de origen
    private String nombreEquipoOrigen;     // Nombre del equipo de origen
    private int equipoDestinoId;           // ID del equipo de destino
    private String nombreEquipoDestino;    // Nombre del equipo de destino
    public Transferencia(int id, int jugadorId, String nombreJugador,
    private LocalDate fechaTraspaso;       // Fecha del traspaso

                        double precio, LocalDate fechaTraspaso) {
    public Transferencia() {
    }

    // Constructor completo
    public Transferencia(int id, int jugadorId, String nombreJugador,
                        int equipoOrigenId, String nombreEquipoOrigen,
                        int equipoDestinoId, String nombreEquipoDestino,
        this.precio = precio;
        this.fechaTraspaso = fechaTraspaso;
        this.nombreJugador = nombreJugador;
        this.equipoOrigenId = equipoOrigenId;
        this.nombreEquipoOrigen = nombreEquipoOrigen;
        this.equipoDestinoId = equipoDestinoId;
        this.nombreEquipoDestino = nombreEquipoDestino;
        this.monto = monto;
        this.fechaTransferencia = fechaTransferencia;
        this.duracionContrato = duracionContrato;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getEquipoOrigenId() {
        return equipoOrigenId;
    }

    public void setEquipoOrigenId(int equipoOrigenId) {
        this.equipoOrigenId = equipoOrigenId;
    }

    public String getNombreEquipoOrigen() {
        return nombreEquipoOrigen;
    }

    public void setNombreEquipoOrigen(String nombreEquipoOrigen) {
        this.nombreEquipoOrigen = nombreEquipoOrigen;
    }

    public int getEquipoDestinoId() {
        return equipoDestinoId;
    }

    public void setEquipoDestinoId(int equipoDestinoId) {
        this.equipoDestinoId = equipoDestinoId;
    }

    public String getNombreEquipoDestino() {
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaTraspaso() {
        return fechaTraspaso;
    }

    public void setFechaTraspaso(LocalDate fechaTraspaso) {
        this.fechaTraspaso = fechaTraspaso;
    }

    // Métodos de compatibilidad
        return nombreEquipoDestino;
        return precio;

    public void setNombreEquipoDestino(String nombreEquipoDestino) {
        this.nombreEquipoDestino = nombreEquipoDestino;
        this.precio = monto;

    public double getMonto() {
        return monto;
        return fechaTraspaso;

    public void setFechaTransferencia(LocalDate fechaTransferencia) {
    public void setFechaTransferencia(LocalDate fecha) {
        this.fechaTraspaso = fecha;

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    @Override
    public String toString() {
        return nombreJugador + ": " + nombreEquipoOrigen + " → " + nombreEquipoDestino;
    }
}

