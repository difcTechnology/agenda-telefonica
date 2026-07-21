package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

public class AgendaControlador {

    private final Agenda agenda;
    private final VentanaPrincipal vista;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;
    }

    // Registra los eventos de los botones
    public void iniciar() {
        vista.getBtnAnadir().addActionListener(e -> accionAnadir());
        vista.getBtnModificar().addActionListener(e -> accionModificar());
        vista.getBtnEliminar().addActionListener(e -> accionEliminar());
    }

    private void accionAnadir() {
        System.out.println("Se hizo clic en Añadir");
    }

    private void accionModificar() {
        System.out.println("Se hizo clic en Modificar");
    }

    // Elimina el contacto seleccionado o ingresado
    private void accionEliminar() {
        // Obtener texto de la vista
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();

        // Validar campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarMensajeError("Debe seleccionar un contacto o ingresar nombre y apellido para eliminar.");
            return;
        }

        // Eliminar en el modelo
        Contacto contactoAEliminar = new Contacto(nombre, apellido, "");
        boolean eliminado = agenda.eliminarContacto(contactoAEliminar);

        // Actualizar la interfaz
        if (eliminado) {
            vista.mostrarMensajeExito("Contacto " + nombre + " " + apellido + " eliminado correctamente.");
            vista.limpiarCampos();
            vista.actualizarTabla(agenda.listarContactos());
        } else {
            vista.mostrarMensajeError("El contacto no existe en la agenda.");
        }
    }
}