package com.example.prestamos.model;

import java.io.Serializable;

public class Dispositivo implements Serializable {
    private int id_dispositivo;  // Agregamos el campo id_dispositivo
    private String nombre_dispositivo;
    private String descripcion;
    private boolean esta_disponible;

    // Constructor vacío para Retrofit
    public Dispositivo() {}

    // Constructor que acepta todos los campos
    public Dispositivo(int id_dispositivo, String nombre_dispositivo, String descripcion, boolean esta_disponible) {
        this.id_dispositivo = id_dispositivo;
        this.nombre_dispositivo = nombre_dispositivo;
        this.descripcion = descripcion;
        this.esta_disponible = esta_disponible;
    }

    // Getters y Setters para todos los campos
    public int getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(int id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
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
        return "Dispositivo{" +
                "id_dispositivo=" + id_dispositivo +
                ", nombre_dispositivo='" + nombre_dispositivo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", esta_disponible=" + esta_disponible +
                '}';
    }
}