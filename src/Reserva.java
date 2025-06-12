public class Reserva {
    private Cliente cliente;
    private String fecha;
    private String hora;

    // Constructor
    public Reserva(Cliente cliente, String fecha, String hora) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    // Método para mostrar información de la reserva
    public String mostrarReserva() {
        return "Reserva: Cliente [" + cliente.mostrarInformacion() + "], Fecha: " + fecha + ", Hora: " + hora;
    }
}