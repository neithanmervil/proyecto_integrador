package com.example.prestamos.model;

import java.io.Serializable;

public class Dispositivo implements Serializable {
    private int id;
    private String nombre_dispositivo;
    private String descripcion;
    private boolean esta_disponible;

    // Constructor vacío para Retrofit
    public Dispositivo() {}

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_dispositivo() {
        return nombre_dispositivo;
    }

    public void setNombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsta_disponible() {
        return esta_disponible;
    }

    public void setEsta_disponible(boolean esta_disponible) {
        this.esta_disponible = esta_disponible;
    }

    @Override
    public String toString() {
        return id + " - " + nombre_dispositivo + " - " + descripcion + " - " + esta_disponible;
    }
}
