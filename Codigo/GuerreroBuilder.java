public class GuerreroBuilder {
    private String nombre;
    private Arma arma;
    private Armadura armadura;

    public GuerreroBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public GuerreroBuilder conArma(Arma arma) {
        this.arma = arma;
        return this;
    }

    public GuerreroBuilder conArmadura(Armadura armadura) {
        this.armadura = armadura;
        return this;
    }

    public Guerrero build() {
        return new Guerrero(nombre, arma, armadura);
    }
}
