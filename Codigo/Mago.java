import java.util.Arrays;

public class Mago extends Personaje {
    public Mago(String nombre, Arma arma, Armadura armadura) {
        super(nombre, arma, armadura, Arrays.asList(
            new Ataque("Bola de Fuego", 19, 0.25, 2.0),
            new Ataque("Rayo Helado", 16, 0.35, 1.7),
            new Ataque("Explosión Arcana", 23, 0.15, 2.5),
            new Ataque("Descarga Eléctrica", 18, 0.30, 2.0)
        ));
    }

    @Override
    public void atacar(Personaje oponente) {
        // Este método será modificado para permitir elegir el ataque en el flujo principal
        int dañoBase = 10; // Suponiendo un daño base
        int dañoTotal = dañoBase + (arma != null ? arma.getDaño() : 0);
        // El mago tiene 20% de probabilidad de hacer un ataque critico (doble daño)
        if (Math.random() < 0.2) {
            dañoTotal *= 2;
            System.out.println(this.nombre + " (Mago) lanza un ataque critico!");
        }
        oponente.recibirDaño(dañoTotal);
        System.out.println(this.nombre + " (Mago) ataca a " + oponente.getNombre() + " causando " + dañoTotal
                + " puntos de daño (arma: " + arma.getNombre() + ").");
    }
}

/* hola 