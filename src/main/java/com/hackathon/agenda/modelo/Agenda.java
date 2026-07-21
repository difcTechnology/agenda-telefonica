package com.hackathon.agenda.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Agenda {
    private final List<Contacto> contactos;
    private final int capacidadMaxima;

    // Regex para validar teléfonos: acepta de 7 a 15 dígitos, opcionalmente con '+' al inicio, espacios o guiones.
    private static final String REGEX_TELEFONO = "^\\+?[0-9\\s\\-]{7,15}$";

    public Agenda() {
        this(10); // Capacidad por defecto de 10 contactos
    }

    public Agenda(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Valida la estructura y formato de un número telefónico.
     */
    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(REGEX_TELEFONO, telefono.trim());
    }

    /**
     * Valida espacio disponible, duplicados y campos obligatorios antes de insertar.
     * Retorna un mensaje descriptivo si falla o null si la adición fue exitosa.
     */
    public String añadirContacto(Contacto c) {
        if (c == null) {
            return "El contacto no puede ser nulo.";
        }
        if (c.getNombre().isEmpty()) {
            return "El nombre del contacto es obligatorio.";
        }
        if (c.getApellido().isEmpty()) {
            return "El apellido del contacto es obligatorio.";
        }
        if (c.getTelefono().isEmpty()) {
            return "El teléfono del contacto es obligatorio.";
        }
        if (!esTelefonoValido(c.getTelefono())) {
            return "Formato de teléfono inválido (debe contener entre 7 y 15 dígitos).";
        }
        if (agendaLlena()) {
            return "La agenda está llena. No se pueden agregar más contactos (Capacidad: " + capacidadMaxima + ").";
        }
        if (existeContacto(c)) {
            return "El contacto " + c.getNombre() + " " + c.getApellido() + " ya existe en la agenda.";
        }

        contactos.add(c);
        return null; // Operación exitosa
    }

    /**
     * Verifica si el contacto ya se encuentra registrado por nombre y apellido.
     */
    public boolean existeContacto(Contacto c) {
        if (c == null) return false;
        return contactos.contains(c);
    }

    /**
     * Retorna la lista completa de contactos ordenada alfabéticamente por nombre y apellido.
     */
    public List<Contacto> listarContactos() {
        List<Contacto> listaOrdenada = new ArrayList<>(contactos);
        listaOrdenada.sort(Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER));
        return listaOrdenada;
    }

    /**
     * Devuelve el teléfono del contacto localizado o null si no existe.
     */
    public Contacto buscarContacto(String nombre, String apellido) {
        if (nombre == null || apellido == null) return null;
        Contacto temp = new Contacto(nombre, apellido, "");
        for (Contacto c : contactos) {
            if (c.equals(temp)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Remueve el registro del almacenamiento si existe.
     */
    public boolean eliminarContacto(Contacto c) {
        if (c == null) return false;
        return contactos.remove(c);
    }

    /**
     * Actualiza el teléfono de un contacto existente previo paso por la validación Regex.
     */
    public String modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        if (nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty()) {
            return "Debe especificar el nombre y apellido del contacto a modificar.";
        }
        if (!esTelefonoValido(nuevoTelefono)) {
            return "Formato de teléfono inválido (debe contener entre 7 y 15 dígitos).";
        }

        Contacto temp = new Contacto(nombre, apellido, "");
        int index = contactos.indexOf(temp);
        if (index == -1) {
            return "El contacto " + nombre + " " + apellido + " no existe en la agenda.";
        }

        contactos.get(index).setTelefono(nuevoTelefono);
        return null; // Operación exitosa
    }

    /**
     * Retorna si la agenda alcanzó su capacidad máxima.
     */
    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    /**
     * Retorna el número de espacios libres disponibles.
     */
    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }
}
