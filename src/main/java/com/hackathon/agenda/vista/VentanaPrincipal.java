package com.hackathon.agenda.vista;

import com.hackathon.agenda.controlador.AgendaControlador;

import java.util.Scanner;

public class VentanaPrincipal {

    private final Scanner scanner;
    private AgendaControlador agendaControlador;

    public VentanaPrincipal() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {

        System.out.println("===== AGENDA DE CONTACTOS =====");

        int capacidad = solicitarCapacidad();

        agendaControlador = new AgendaControlador(capacidad);

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero();
            ejecutarOpcion(opcion);

        } while (opcion != 0);

        scanner.close();
    }

    private void mostrarMenu() {

        System.out.println();
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Listar contactos");
        System.out.println("3. Buscar contacto");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. Modificar teléfono");
        System.out.println("6. Verificar si la agenda está llena");
        System.out.println("7. Mostrar espacios libres");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void ejecutarOpcion(int opcion) {

        switch (opcion) {

            case 1:
                anadirContacto();
                break;

            case 2:
                agendaControlador.listarContactos();
                break;

            case 3:
                buscarContacto();
                break;

            case 4:
                eliminarContacto();
                break;

            case 5:
                modificarTelefono();
                break;

            case 6:
                if (agendaControlador.agendaLlena()) {
                    System.out.println("La agenda está llena.");
                } else {
                    System.out.println("La agenda tiene espacio disponible.");
                }
                break;

            case 7:
                agendaControlador.espaciosLibres();
                break;

            case 0:
                System.out.println("Programa finalizado.");
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    private void anadirContacto() {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        agendaControlador.anadirContacto(
                nombre,
                apellido,
                telefono
        );
    }

    private void buscarContacto() {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        agendaControlador.buscarContacto(
                nombre,
                apellido
        );
    }

    private void eliminarContacto() {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        agendaControlador.eliminarContacto(
                nombre,
                apellido
        );
    }

    private void modificarTelefono() {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo teléfono: ");
        String nuevoTelefono = scanner.nextLine();

        agendaControlador.modificarTelefono(
                nombre,
                apellido,
                nuevoTelefono
        );
    }

    private int solicitarCapacidad() {

        int capacidad;

        do {
            System.out.print(
                    "Ingrese la capacidad máxima de la agenda: "
            );

            capacidad = leerEntero();

            if (capacidad <= 0) {
                System.out.println(
                        "La capacidad debe ser mayor que cero."
                );
            }

        } while (capacidad <= 0);

        return capacidad;
    }

    private int leerEntero() {

        while (true) {

            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);

            } catch (NumberFormatException error) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
