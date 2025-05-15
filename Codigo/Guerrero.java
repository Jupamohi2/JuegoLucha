import java.util.Arrays;

public class Guerrero extends Personaje {
    public Guerrero(String nombre, Arma arma, Armadura armadura) {
        super(nombre, arma, armadura, Arrays.asList(
            new Ataque("Golpe Poderoso", 15, 0.20, 2.0),
            new Ataque("Estocada Rápida", 10, 0.40, 1.5),
            new Ataque("Hachazo Brutal", 20, 0.10, 3.0),
            new Ataque("Ataque Giratorio", 12, 0.30, 2.0)
        ));
    }

    @Override
    public void atacar(Personaje oponente) {
        // Este método será modificado para permitir elegir el ataque en el flujo principal
    }
}
