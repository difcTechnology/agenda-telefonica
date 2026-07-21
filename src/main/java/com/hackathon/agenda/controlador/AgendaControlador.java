package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

public class AgendaControlador {

    private final Agenda agenda;
    private final VentanaPrincipal vista;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;
    }

    public void iniciar() {
        vista.getBtnAnadir().addActionListener(e -> accionAnadir());
        vista.getBtnModificar().addActionListener(e -> accionModificar());
    }

    private void accionAnadir() {
        System.out.println("Se hizo clic en Añadir");
    }

    private void accionModificar() {
        System.out.println("Se hizo clic en Modificar");
    }
}
