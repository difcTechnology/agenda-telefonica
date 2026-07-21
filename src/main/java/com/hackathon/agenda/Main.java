package com.hackathon.agenda;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtEdad;
    private JButton btnAgregar, btnModificar, btnEliminar;

    public Main() {
        // Configuración básica de la ventana
        setTitle("Gestión de Registros");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Configurar el DefaultTableModel
        String[] columnas = {"Nombre", "Edad"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que editen la tabla haciendo doble clic
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // 2. Evento de selección de registro
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtiene los datos de la fila seleccionada
                    String nombre = modelo.getValueAt(filaSeleccionada, 0).toString();
                    String edad = modelo.getValueAt(filaSeleccionada, 1).toString();

                    // Pone los datos en los campos de texto
                    txtNombre.setText(nombre);
                    txtEdad.setText(edad);
                }
            }
        });

        // Panel inferior con controles
        JPanel panelControles = new JPanel(new GridLayout(3, 2, 5, 5));
        txtNombre = new JTextField();
        txtEdad = new JTextField();
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        panelControles.add(new JLabel("Nombre:"));
        panelControles.add(txtNombre);
        panelControles.add(new JLabel("Edad:"));
        panelControles.add(txtEdad);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        panelControles.add(panelBotones);

        add(scrollPane, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);

        // 3. Acción para AGREGAR
        btnAgregar.addActionListener(e -> {
            String[] nuevoRegistro = {txtNombre.getText(), txtEdad.getText()};
            modelo.addRow(nuevoRegistro);
            limpiarCampos();
        });

        // 4. Acción para MODIFICAR
        btnModificar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Modifica el modelo en la fila y columna indicada
                modelo.setValueAt(txtNombre.getText(), filaSeleccionada, 0);
                modelo.setValueAt(txtEdad.getText(), filaSeleccionada, 1);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila para modificar.");
            }
        });

        // 5. Acción para ELIMINAR
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                modelo.removeRow(filaSeleccionada);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.");
            }
        });
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}