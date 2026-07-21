package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Agenda agenda = new Agenda();
            VentanaPrincipal ventana = new VentanaPrincipal();
            AgendaControlador controlador = new AgendaControlador(agenda, ventana);
            controlador.iniciar();
            ventana.setVisible(true);
        });
    }
}