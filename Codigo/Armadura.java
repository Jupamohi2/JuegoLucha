public class Armadura {
    private final String nombre;
    private final int proteccion;

    public Armadura(String nombre, int proteccion) {
        this.nombre = nombre;
        this.proteccion = proteccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProteccion() {
        return proteccion;
    }

    @Override
    public String toString() {
        return nombre + " (Protección: " + proteccion + ")";
    }
}
