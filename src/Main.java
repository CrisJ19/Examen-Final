import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reserva[] reservas = new Reserva[5]; // Arreglo para almacenar hasta 5 reservas
        int contadorReservas = 0;

        while (true) {
            System.out.println("\nSistema de Gestión de Reservas - Barbería");
            System.out.println("1. Registrar nueva reserva");
            System.out.println("2. Mostrar todas las reservas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    if (contadorReservas < 5) {
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese el teléfono del cliente: ");
                        String telefono = scanner.nextLine();

                        System.out.print("Ingrese el tipo de corte (Corto, Largo, Diseño): ");
                        String tipoCorte = scanner.nextLine();

                        System.out.print("Ingrese la fecha de la reserva (ej. 2025-07-06): ");
                        String fecha = scanner.nextLine();

                        System.out.print("Ingrese la hora de la reserva (ej. 14:00): ");
                        String hora = scanner.nextLine();

                        try {
                            // Validar campos vacíos
                            if (nombre.isEmpty() || telefono.isEmpty() || tipoCorte.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                                throw new IllegalArgumentException("Todos los campos son obligatorios.");
                            }

                            // Validar formato de fecha
                            if (!validarFecha(fecha)) {
                                throw new IllegalArgumentException("Formato de fecha inválido. Use AAAA-MM-DD.");
                            }

                            // Validar formato y rango de hora
                            if (!validarHora(hora)) {
                                throw new IllegalArgumentException("Formato de hora inválido. Use HH:MM.");
                            }
                            if (!esHoraPermitida(hora)) {
                                throw new IllegalArgumentException("Las reservas solo se permiten de 9:00 a 12:00 y de 14:00 a 19:00.");
                            }

                            // Verificar si ya existe una reserva en la misma fecha y hora
                            if (existeReserva(reservas, contadorReservas, fecha, hora)) {
                                throw new IllegalArgumentException("Ya existe una reserva para la fecha " + fecha + " a las " + hora + ".");
                            }

                            // Verificar si el cliente ya tiene una reserva (por nombre o teléfono)
                            if (existeCliente(reservas, contadorReservas, nombre, telefono)) {
                                throw new IllegalArgumentException("El cliente con nombre " + nombre + " o teléfono " + telefono + " ya tiene una reserva.");
                            }

                            Cliente cliente = new Cliente(nombre, telefono, tipoCorte);
                            Reserva reserva = new Reserva(cliente, fecha, hora);
                            reservas[contadorReservas] = reserva;
                            contadorReservas++;
                            System.out.println("Reserva registrada con éxito.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Error: No hay más espacio para reservas.");
                    }
                    break;

                case 2:
                    System.out.println("\nLista de Reservas:");
                    for (int i = 0; i < contadorReservas; i++) {
                        System.out.println(reservas[i].mostrarReserva());
                    }
                    if (contadorReservas == 0) {
                        System.out.println("No hay reservas registradas.");
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    // Método reutilizable para validar formato de fecha (simple)
    public static boolean validarFecha(String fecha) {
        return fecha.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Método reutilizable para validar formato de hora (simple)
    public static boolean validarHora(String hora) {
        return hora.matches("\\d{2}:\\d{2}");
    }

    // Método reutilizable para verificar si la hora está en el rango permitido
    public static boolean esHoraPermitida(String hora) {
        try {
            String[] partes = hora.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);

            // Horario permitido: 9:00-12:00 y 14:00-19:00
            if (horas < 9 || horas > 19) {
                return false;
            }
            if (horas >= 12 && horas < 14) {
                return false;
            }
            if (horas == 19 && minutos > 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    // Método reutilizable para verificar si ya existe una reserva en la misma fecha y hora
    public static boolean existeReserva(Reserva[] reservas, int contador, String fecha, String hora) {
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getFecha().equals(fecha) && reservas[i].getHora().equals(hora)) {
                return true;
            }
        }
        return false;
    }

    // Método reutilizable para verificar si el cliente ya tiene una reserva (por nombre o teléfono)
    public static boolean existeCliente(Reserva[] reservas, int contador, String nombre, String telefono) {
        for (int i = 0; i < contador; i++) {
            Cliente cliente = reservas[i].getCliente();
            if (cliente.getNombre().equalsIgnoreCase(nombre) || cliente.getTelefono().equals(telefono)) {
                return true;
            }
        }
        return false;
    }
}