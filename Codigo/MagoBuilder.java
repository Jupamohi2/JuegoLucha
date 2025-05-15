public class MagoBuilder {
    private String nombre;
    private Arma arma;
    private Armadura armadura;

    public MagoBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MagoBuilder conArma(Arma arma) {
        this.arma = arma;
        return this;
    }

    public MagoBuilder conArmadura(Armadura armadura) {
        this.armadura = armadura;
        return this;
    }

    public Mago build() {
        return new Mago(nombre, arma, armadura);
    }
}
