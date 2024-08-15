package com.example.prestamos.model;

import java.io.Serializable;

public class Dispositivo implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private boolean disponible;

    // Constructor vacío para Retrofit
    public Dispositivo() {}

    // Getters y setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion + " - " + (disponible ? "Disponible" : "No Disponible");
    }
}
