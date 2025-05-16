import java.util.Arrays;
import java.util.Scanner;

public class Berserker extends Personaje {
    public Berserker(String nombre, Arma arma, Armadura armadura) {
        super(nombre, arma, armadura, Arrays.asList(
            new Ataque("Golpe Poderoso", 15, 0.20, 2.0),
            new Ataque("Estocada Rápida", 10, 0.40, 1.5),
            new Ataque("Hachazo Brutal", 20, 0.10, 3.0),
            new Ataque("Ataque Giratorio", 12, 0.30, 2.0)
        ));
    }

    @Override
    public void atacar(Personaje oponente, Scanner scanner) {
        System.out.println("Elige el ataque para " + this.getNombre() + ":");
        for (int i = 0; i < this.getAtaques().size(); i++) {
            Ataque atk = this.getAtaques().get(i);
            System.out.println((i + 1) + ". " + atk.getNombre() + " | Daño base: " + atk.getDanoBase() + " + Daño arma: " + this.getArma().getDano()
                    + " | Prob. crítico: " + (int) (atk.getProbCritico() * 100) + "% | Multiplicador crítico: x" + atk.getMultiplicadorCritico());
        }
        int idx = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print("Selecciona el número de ataque: ");
            idx = scanner.nextInt() - 1;
            scanner.nextLine();
            if (idx >= 0 && idx < this.getAtaques().size()) {
                valido = true;
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        Ataque ataque = this.getAtaques().get(idx);
        int danoTotal = ataque.getDanoBase() + this.getArma().getDano();
        boolean critico = Math.random() < ataque.getProbCritico();
        if (critico) {
            danoTotal = (int) (danoTotal * ataque.getMultiplicadorCritico());
            System.out.println("¡CRÍTICO! El ataque inflige daño multiplicado.");
        }
        oponente.recibirDano(danoTotal);
        System.out.println(this.getNombre() + " usa " + ataque.getNombre() + " y causa " + danoTotal + " puntos de daño (arma: " + this.getArma().getNombre() + ").");
        System.out.println("\u001B[31m" + oponente.getNombre() + " ahora tiene " + oponente.getPuntosDeVida() + "/" + oponente.getVidaMaxima() + " puntos de vida.\u001B[0m");
    }

    public static class BerserkerBuilder {
        private String nombre;
        private Arma arma;
        private Armadura armadura;

        public BerserkerBuilder conNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public BerserkerBuilder conArma(Arma arma) {
            this.arma = arma;
            return this;
        }

        public BerserkerBuilder conArmadura(Armadura armadura) {
            this.armadura = armadura;
            return this;
        }

        public Berserker build() {
            return new Berserker(nombre, arma, armadura);
        }
    }
}
