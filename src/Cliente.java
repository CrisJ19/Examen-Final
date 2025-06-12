public class Cliente extends Persona {
    private String tipoCorte;

    // Constructor
    public Cliente(String nombre, String telefono, String tipoCorte) {
        super(nombre, telefono);
        this.tipoCorte = tipoCorte;
    }

    // Getters y Setters
    public String getTipoCorte() {
        return tipoCorte;
    }

    public void setTipoCorte(String tipoCorte) {
        this.tipoCorte = tipoCorte;
    }

    // Método sobrescrito
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + ", Tipo de corte: " + tipoCorte;
    }
}