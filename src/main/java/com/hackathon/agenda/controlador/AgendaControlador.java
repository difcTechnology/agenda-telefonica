package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Contacto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AgendaControlador {

    private final List<Contacto> contactos;
    private final int capacidadMaxima;

    public AgendaControlador(int capacidadMaxima) {

        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero."
            );
        }

        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    public void anadirContacto(Contacto contacto) {

        if (contacto == null) {
            System.out.println("El contacto no es válido.");
            return;
        }

        if (contacto.getNombre().isBlank()
                || contacto.getApellido().isBlank()) {

            System.out.println(
                    "El nombre y el apellido no pueden estar vacíos."
            );

            return;
        }

        if (agendaLlena()) {
            System.out.println(
                    "La agenda está llena. No se pueden agregar más contactos."
            );

            return;
        }

        if (existeContacto(contacto)) {
            System.out.println(
                    "Ya existe un contacto con ese nombre y apellido."
            );

            return;
        }

        contactos.add(contacto);

        System.out.println("Contacto agregado correctamente.");
    }

    public boolean existeContacto(Contacto contacto) {

        if (contacto == null) {
            return false;
        }

        return contactos.contains(contacto);
    }

    public void listarContactos() {

        if (contactos.isEmpty()) {
            System.out.println("La agenda no tiene contactos.");
            return;
        }

        contactos.stream()
                .sorted(
                        Comparator.comparing(
                                        Contacto::getNombre,
                                        String.CASE_INSENSITIVE_ORDER
                                )
                                .thenComparing(
                                        Contacto::getApellido,
                                        String.CASE_INSENSITIVE_ORDER
                                )
                )
                .forEach(System.out::println);
    }

    public void buscarContacto(String nombre, String apellido) {

        Contacto contacto = encontrarContacto(nombre, apellido);

        if (contacto == null) {
            System.out.println("Contacto no encontrado.");
            return;
        }

        System.out.println(
                contacto.getNombre()
                        + " "
                        + contacto.getApellido()
                        + " - Teléfono: "
                        + contacto.getTelefono()
        );
    }

    public void eliminarContacto(Contacto contacto) {

        if (contacto == null) {
            System.out.println("El contacto no es válido.");
            return;
        }

        if (contactos.remove(contacto)) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println(
                    "No se pudo eliminar porque el contacto no existe."
            );
        }
    }

    public void modificarTelefono(
            String nombre,
            String apellido,
            String nuevoTelefono
    ) {

        Contacto contacto = encontrarContacto(nombre, apellido);

        if (contacto == null) {
            System.out.println("El contacto no existe.");
            return;
        }

        if (nuevoTelefono == null || nuevoTelefono.isBlank()) {
            System.out.println(
                    "El nuevo teléfono no puede estar vacío."
            );

            return;
        }

        contacto.setTelefono(nuevoTelefono);

        System.out.println("Teléfono modificado correctamente.");
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public void mostrarEstadoAgenda() {

        if (agendaLlena()) {
            System.out.println(
                    "La agenda está llena. No hay espacio disponible."
            );
        } else {
            System.out.println(
                    "La agenda todavía tiene espacio disponible."
            );
        }
    }

    public void espaciosLibres() {

        int espaciosDisponibles =
                capacidadMaxima - contactos.size();

        System.out.println(
                "Espacios disponibles: " + espaciosDisponibles
        );

        if (espaciosDisponibles == 0) {
            System.out.println(
                    "No hay espacio disponible para nuevos contactos."
            );
        }
    }

    private Contacto encontrarContacto(
            String nombre,
            String apellido
    ) {

        if (nombre == null || apellido == null) {
            return null;
        }

        String nombreBuscado = nombre.trim();
        String apellidoBuscado = apellido.trim();

        for (Contacto contacto : contactos) {

            boolean mismoNombre =
                    contacto.getNombre()
                            .equalsIgnoreCase(nombreBuscado);

            boolean mismoApellido =
                    contacto.getApellido()
                            .equalsIgnoreCase(apellidoBuscado);

            if (mismoNombre && mismoApellido) {
                return contacto;
            }
        }

        return null;
    }
}