package com.hackathon.agenda;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class a {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Ejemplo DefaultTableModel");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 200);

        // 1. Crear las cabeceras de la tabla
        String[] columnas = {"Nombre", "Edad", "Ciudad"};

        // 2. Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // 3. Agregar filas al modelo
        Object[] fila1 = {"Ana", 25, "Bogotá"};
        Object[] fila2 = {"Juan", 30, "Medellín"};
        Object[] fila3 = {"Pedro", 22, "Cali"};

        modelo.addRow(fila1);
        modelo.addRow(fila2);
        modelo.addRow(fila3);

        // 4. Crear la tabla usando el modelo
        JTable tabla = new JTable(modelo);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            // Evitar que el evento se dispare dos veces al hacer clic
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tabla.getSelectedRow();

                // Verificar si hay una fila válida seleccionada
                if (filaSeleccionada != -1) {
                    // Obtener los datos usando el modelo (para evitar errores al ordenar columnas)
                    String id = modelo.getValueAt(filaSeleccionada, 0).toString();
                    String nombre = modelo.getValueAt(filaSeleccionada, 1).toString();
                    String rol = modelo.getValueAt(filaSeleccionada, 2).toString();

                    System.out.println("Seleccionaste la fila " + filaSeleccionada);
                    System.out.println("ID: " + id + " | Nombre: " + nombre + " | Rol: " + rol);
                }
            }
        });

        // Agregar la tabla a un panel con desplazamiento (scroll)
        JScrollPane panel = new JScrollPane(tabla);
        ventana.add(panel);

        ventana.setVisible(true);
    }
}