package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

import java.awt.*;
import java.util.List;

public class AgendaControlador {

    private final Agenda agenda;
    private final VentanaPrincipal  vista;
    private Contacto contactoSeleccionado;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;
    }

        public void iniciar() {
        vista.getBtnAnadir().addActionListener(e -> accionAnadir());
        vista.getBtnModificar().addActionListener(e -> accionModificar());
        vista.getBtnEliminar().addActionListener(e -> accionEliminar());
        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarCampos());
        vista.getBtnListar().addActionListener(e -> listarContactos());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getTablaContactos().getSelectionModel().addListSelectionListener(e->{
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = vista.getTablaContactos().getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtiene los datos de la fila seleccionada
                    contactoSeleccionado = new Contacto();
                    contactoSeleccionado.setNombre(vista.getTablaContactos().getValueAt(filaSeleccionada, 0).toString());
                    contactoSeleccionado.setApellido(vista.getTablaContactos().getValueAt(filaSeleccionada, 1).toString());
                    contactoSeleccionado.setTelefono(vista.getTablaContactos().getValueAt(filaSeleccionada, 2).toString());
                }


            }
        });
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
            vista.mostrarMensaje("Contacto agregado: " + nombre + " " + apellido, Color.GREEN);
        } else {
            vista.mostrarMensaje(error,Color.RED);
        }
    }

    // Elimina el contacto seleccionado o ingresado
    private void accionEliminar() {
        if(contactoSeleccionado != null) {
            boolean eliminado = agenda.eliminarContacto(contactoSeleccionado);
            //Eliminarlo de la vista
            int filaSeleccionada = vista.getTablaContactos().getSelectedRow();
            if (filaSeleccionada != -1) {
                vista.getModeloTabla().removeRow(filaSeleccionada);
            }
            // Actualizar la interfaz
            if (eliminado) {
                vista.mostrarMensaje("Contacto " + contactoSeleccionado.getNombre() + " " + contactoSeleccionado.getApellido() + " eliminado correctamente.", Color.GREEN);
                vista.limpiarCampos();
                //vista.actualizarTabla(agenda.listarContactos());
            } else {
                vista.mostrarMensaje("El contacto no existe en la agenda.", Color.RED);
            }
        }else{
            vista.mostrarMensaje("Debe seleccionar un registro", Color.RED);
        }
    }

    private void accionModificar() {
        String nombre = contactoSeleccionado.getNombre();
        String apellido = contactoSeleccionado.getApellido();
        String nuevoTelefono = vista.getTxtTelefono().getText().trim();

        String error = agenda.modificarTelefono(nombre, apellido, nuevoTelefono);

        if (error == null) {
            listarContactos();
            vista.limpiarCampos();
            vista.mostrarMensaje("Teléfono actualizado: " + nombre + " " + apellido, Color.GREEN);
        } else {
            vista.mostrarMensaje(error, Color.RED);
        }
    }

    public void listarContactos(){
        vista.getModeloTabla().setRowCount(0);
        List<Contacto> contactos = agenda.listarContactos();

        for(Contacto contacto : contactos){
            String[] nuevoRegistro = new String[]{contacto.getNombre(), contacto.getApellido(), contacto.getTelefono()};
            vista.getModeloTabla().addRow(nuevoRegistro);
        }
    }

    public void buscar(){
        vista.getModeloTabla().setRowCount(0);
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();

        Contacto contacto = agenda.buscarContacto(nombre,apellido);

        if(contacto != null) {
            String[] nuevoRegistro = new String[]{contacto.getNombre(), contacto.getApellido(), contacto.getTelefono()};
            vista.getModeloTabla().addRow(nuevoRegistro);
        }

    }

}
