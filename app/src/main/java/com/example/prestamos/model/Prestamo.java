package com.example.prestamos.model;

import java.io.Serializable;

public class Prestamo implements Serializable {
    private String nombre_estudiante;
    private String telefono_estudiante;
    private int dispositivoId;
    private String fecha_prestamo;
    private String fecha_devolucion;

    // Constructor vacío para Retrofit
    public Prestamo() {
    }

    // Constructor que acepta nombre y teléfono
    public Prestamo(String nombre, String telefono, int dispositivoId, String fecha_prestamo, String fecha_devolucion) {
        this.nombre_estudiante = nombre;
        this.telefono_estudiante = telefono;
        this.dispositivoId = dispositivoId;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
    }

    // Getters y Setters

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }

    public void setNombre_estudiante(String nombre_estudiante) {
        this.nombre_estudiante = nombre_estudiante;
    }

    public String getTelefono_estudiante() {
        return telefono_estudiante;
    }

    public void setTelefono_estudiante(String telefono_estudiante) {
        this.telefono_estudiante = telefono_estudiante;
    }

    public int getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(int dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                ", nombre_estudiante='" + nombre_estudiante + '\'' +
                ", telefono_estudiante='" + telefono_estudiante + '\'' +
                ", dispositivoId=" + dispositivoId +
                ", fecha_prestamo='" + fecha_prestamo + '\'' +
                ", fecha_devolucion='" + fecha_devolucion + '\'' +
                '}';
    }
}
