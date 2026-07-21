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

        public void iniciar() {
        vista.getBtnAnadir().addActionListener(e -> accionAnadir());
        vista.getBtnModificar().addActionListener(e -> accionModificar());
        vista.getBtnEliminar().addActionListener(e -> accionEliminar());
        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarCampos());
    }


    private void accionAnadir() {
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();

        Contacto contacto = new Contacto(nombre, apellido, telefono);
        String error = agenda.añadirContacto(contacto);

        if (error == null) {
            String[] nuevoRegistro = new String[]{nombre, apellido, telefono};
            vista.getModeloTabla().addRow(nuevoRegistro);
            vista.mostrarMensaje("Contacto agregado: " + nombre + " " + apellido);
        } else {
            vista.mostrarMensaje(error);
        }
    }

    // Elimina el contacto seleccionado o ingresado
    private void accionEliminar() {
        // Obtener texto de la vista
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();

        // Validar campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarMensaje("Debe seleccionar un contacto o ingresar nombre y apellido para eliminar.");
            return;
        }

        // Eliminar en el modelo
        Contacto contactoAEliminar = new Contacto(nombre, apellido, "");
        boolean eliminado = agenda.eliminarContacto(contactoAEliminar);

        // Actualizar la interfaz
        if (eliminado) {
            vista.mostrarMensaje("Contacto " + nombre + " " + apellido + " eliminado correctamente.");
            vista.limpiarCampos();
            //vista.actualizarTabla(agenda.listarContactos());
        } else {
            vista.mostrarMensaje("El contacto no existe en la agenda.");
        }
    }

    private void accionModificar() {
    }

}
