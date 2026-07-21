package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                Agenda agenda = new Agenda();
                VentanaPrincipal ventana = new VentanaPrincipal();
                AgendaControlador controlador = new AgendaControlador(agenda, ventana);
                controlador.iniciar();
                ventana.setVisible(true);
            }catch (Exception e){
                System.out.println("Se presento un error :"+e.getMessage());
            }
        });
    }
}