package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;

public class AgendaControlador {

    private Agenda agenda;

    public AgendaControlador(int capacidadMaxima) {
        agenda = new Agenda(capacidadMaxima);
    }

    public void anadirContacto(String nombre,
                               String apellido,
                               String telefono) {

        Contacto contacto = new Contacto(nombre, apellido, telefono);
        // agenda.anadirContacto(contacto);
    }

    public void listarContactos() {
        agenda.listarContactos();
    }

    public void buscarContacto(String nombre,
                               String apellido) {

        agenda.buscarContacto(nombre, apellido);
    }

    public void eliminarContacto(String nombre,
                                 String apellido) {

        Contacto contacto = new Contacto(nombre, apellido, "");
        agenda.eliminarContacto(contacto);
    }

    public void modificarTelefono(String nombre,
                                  String apellido,
                                  String nuevoTelefono) {

        agenda.modificarTelefono(
                nombre,
                apellido,
                nuevoTelefono
        );
    }

    public boolean agendaLlena() {
        return agenda.agendaLlena();
    }

    public void espaciosLibres() {
        agenda.espaciosLibres();
    }
}