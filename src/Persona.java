public class Persona {
    private String nombre;
    private String telefono;

    // Constructor
    public Persona(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método para mostrar información
    public String mostrarInformacion() {
        return "Nombre: " + nombre + ", Teléfono: " + telefono;
    }
}