public class Ataque {
    private final String nombre;
    private final int danoBase;
    private final double probCritico; // Ejemplo: 0.2 para 20%
    private final double multiplicadorCritico; // Ejemplo: 2.0 para x2

    public Ataque(String nombre, int danoBase, double probCritico, double multiplicadorCritico) {
        this.nombre = nombre;
        this.danoBase = danoBase;
        this.probCritico = probCritico;
        this.multiplicadorCritico = multiplicadorCritico;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public double getProbCritico() {
        return probCritico;
    }

    public double getMultiplicadorCritico() {
        return multiplicadorCritico;
    }

    @Override
    public String toString() {
        return nombre + " (Daño base: " + danoBase + ", Prob. crítico: " + (int)(probCritico*100) + "% x" + multiplicadorCritico + ")";
    }
}
