package com.hackathon.agenda;

import javax.swing.SwingUtilities;

import com.hackathon.agenda.vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}