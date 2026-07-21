package com.hackathon.agenda.modelo;

import java.util.Objects;

public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono.trim();
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }

    @Override
    public boolean equals(Object objeto) {

        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Contacto)) {
            return false;
        }

        Contacto otro = (Contacto) objeto;

        return nombre.equalsIgnoreCase(otro.nombre)
                && apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nombre.toLowerCase(),
                apellido.toLowerCase()
        );
    }
}