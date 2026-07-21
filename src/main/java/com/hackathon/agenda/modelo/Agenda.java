package com.hackathon.agenda.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agenda {

    private List<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda(int capacidadMaxima) {

        this.capacidadMaxima = capacidadMaxima;
        contactos = new ArrayList<>();
    }

    public void anadirContacto(Contacto contacto) {

        if (contacto.getNombre().isBlank()
                || contacto.getApellido().isBlank()) {

            System.out.println("El nombre y el apellido no pueden estar vacíos.");
            return;
        }

        if (agendaLlena()) {

            System.out.println("La agenda está llena.");
            return;
        }

        if (existeContacto(contacto)) {

            System.out.println("El contacto ya existe.");
            return;
        }

        contactos.add(contacto);

        System.out.println("Contacto agregado correctamente.");
    }

    public boolean existeContacto(Contacto contacto) {

        return contactos.contains(contacto);
    }

    public void listarContactos() {

        if (contactos.isEmpty()) {

            System.out.println("La agenda está vacía.");
            return;
        }

        contactos.sort(
                Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
        );

        for (Contacto contacto : contactos) {

            System.out.println(contacto);
        }
    }

    public void buscarContacto(String nombre, String apellido) {

        Contacto contacto = encontrarContacto(nombre, apellido);

        if (contacto == null) {

            System.out.println("Contacto no encontrado.");
            return;
        }

        System.out.println("Teléfono: " + contacto.getTelefono());
    }

    public void eliminarContacto(Contacto contacto) {

        if (contactos.remove(contacto)) {

            System.out.println("Contacto eliminado correctamente.");
        } else {

            System.out.println("El contacto no existe.");
        }
    }

    public void modificarTelefono(String nombre,
                                  String apellido,
                                  String nuevoTelefono) {

        Contacto contacto = encontrarContacto(nombre, apellido);

        if (contacto == null) {

            System.out.println("El contacto no existe.");
            return;
        }

        contacto.setTelefono(nuevoTelefono);

        System.out.println("Teléfono actualizado correctamente.");
    }

    public boolean agendaLlena() {

        return contactos.size() >= capacidadMaxima;
    }

    public void espaciosLibres() {

        int espacios = capacidadMaxima - contactos.size();

        System.out.println("Espacios disponibles: " + espacios);

        if (espacios == 0) {

            System.out.println("No hay espacio para nuevos contactos.");
        }
    }

    private Contacto encontrarContacto(String nombre,
                                       String apellido) {

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(nombre.trim())
                    && contacto.getApellido().equalsIgnoreCase(apellido.trim())) {

                return contacto;
            }
        }

        return null;
    }
}