package com.mercadoficajes.model.entities;

/**
 * Clase Equipo - Representa un equipo de fútbol
 *
 * REQUISITOS DEL EJERCICIO:
 * - Nombre del equipo
 * - Ciudad donde está ubicado
 * - Presupuesto disponible
 *
 * @author Grupo 2 - Fran y Alejandro
 */
public class Equipo {
    // Atributos principales
    private int id;                // Identificador único
    private String nombre;         // Nombre del equipo
    private String ciudad;         // Ciudad del equipo
    public Equipo(int id, String nombre, String ciudad, double presupuesto) {

    // Constructor vacío
        this.ciudad = ciudad;

    // Constructor completo
    public Equipo(int id, String nombre, String pais, String liga, double presupuesto) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.liga = liga;
        this.presupuesto = presupuesto;
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
    public String getCiudad() {
        return ciudad;
    public void setPais(String pais) {
        this.pais = pais;
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return nombre + " (" + liga + ")";
    }
        return nombre + " (" + ciudad + ")";

