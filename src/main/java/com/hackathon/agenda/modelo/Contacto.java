package com.hackathon.agenda.modelo;

import java.util.Objects;

public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre == null ? "" : nombre.trim();
        this.apellido = apellido == null ? "" : apellido.trim();
        this.telefono = telefono == null ? "" : telefono.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre == null ? "" : nombre.trim();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido == null ? "" : apellido.trim();
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono == null ? "" : telefono.trim();
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

        Contacto otroContacto = (Contacto) objeto;

        return nombre.equalsIgnoreCase(otroContacto.nombre)
                && apellido.equalsIgnoreCase(otroContacto.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nombre.toLowerCase(),
                apellido.toLowerCase()
        );
    }
}