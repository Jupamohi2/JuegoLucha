public class Arma {
    private final String nombre;
    private final int daño;

    public Arma(String nombre, int daño) {
        this.nombre = nombre;
        this.daño = daño;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
    }

    public int getDano() {
        return getDaño();
    }

    @Override
    public String toString() {
        return nombre + " (Daño: " + daño + ")";
    }
}
