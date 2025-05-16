import java.util.List;
import java.util.Scanner;

abstract class Personaje {
    protected String nombre;
    protected int puntosDeVida;
    protected final int MAX_DANO = 30;
    protected final int MIN_DANO = 10;
    protected Arma arma;
    protected Armadura armadura;
    protected List<Ataque> ataques;
    protected int vidaMaxima;

    // Constructor para inicializar los datos del personaje
    public Personaje(String nombre, Arma arma, Armadura armadura, List<Ataque> ataques) {
        this.nombre = nombre;
        this.arma = arma;
        this.armadura = armadura;
        this.ataques = ataques;
        this.vidaMaxima = 100 + (armadura != null ? armadura.getProteccion() : 0);
        this.puntosDeVida = vidaMaxima;
    }

    // Metodo abstracto para realizar un ataque a otro personaje
    public abstract void atacar(Personaje oponente, Scanner scanner);

    // Metodo para recibir daño
    public void recibirDaño(int daño) {
        int dañoReducido = daño - (armadura != null ? armadura.getProteccion() : 0);
        if (dañoReducido < 0)
            dañoReducido = 0;
        this.puntosDeVida -= dañoReducido;
        if (this.puntosDeVida < 0) {
            this.puntosDeVida = 0; // No se puede tener menos de 0 puntos de vida
        }
        System.out.println(this.nombre + " recibe " + dañoReducido + " puntos de daño tras protección de armadura.");
    }

    // Metodo para recibir daño (sin reducción por armadura)
    public void recibirDano(int dano) {
        this.puntosDeVida -= dano;
        if (this.puntosDeVida < 0) {
            this.puntosDeVida = 0; // No se puede tener menos de 0 puntos de vida
        }
        System.out.println(this.nombre + " recibe " + dano + " puntos de daño.");
    }

    // Verifica si el personaje sigue vivo
    public boolean estaVivo() {
        return this.puntosDeVida > 0;
    }

    // Devuelve el nombre del personaje
    public String getNombre() {
        return this.nombre;
    }

    // Devuelve los puntos de vida actuales
    public int getPuntosDeVida() {
        return this.puntosDeVida;
    }

    public Arma getArma() {
        return arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

}
