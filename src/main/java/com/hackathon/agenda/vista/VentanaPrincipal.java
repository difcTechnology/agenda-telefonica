package com.hackathon.agenda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    // Campos
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    // Botones
    private JButton btnAnadir;
    private JButton btnModificar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    // Tabla
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    // Estado
    private JLabel lblEstado;

    public VentanaPrincipal() {
        inicializar();
    }

    private void inicializar() {

        setTitle("Agenda Telefónica");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        //formulario

        JPanel panelFormulario = new JPanel(new GridLayout(4,2,5,5));

        panelFormulario.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido"));
        txtApellido = new JTextField();
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Teléfono"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnAnadir = new JButton("Añadir");
        btnModificar = new JButton("Modificar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAnadir);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout());

        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        //tabla

        modeloTabla = new DefaultTableModel(
                new Object[]{"Nombre","Apellido","Teléfono"},0);

        tablaContactos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaContactos);

        add(scroll, BorderLayout.CENTER);

        //estado

        lblEstado = new JLabel("Listo");
        lblEstado.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(lblEstado, BorderLayout.SOUTH);
    }

    //getter

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTablaContactos() {
        return tablaContactos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void mostrarMensaje(String mensaje) {
        lblEstado.setText(mensaje);
    }


    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }
}
