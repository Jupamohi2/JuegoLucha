public class Mochila extends PersonajeDecorator {
    private boolean pocionDisponible = true;

    public Mochila(Personaje personaje) {
        super(personaje);
    }

    public boolean usarPocion() {
        if (pocionDisponible) {
            int vidaAntes = personaje.getPuntosDeVida();
            int vidaMax = personaje.getVidaMaxima();
            int nuevaVida = Math.min(vidaAntes + 30, vidaMax);
            // Reflejar la curación en el personaje real
            try {
                java.lang.reflect.Field field = personaje.getClass().getSuperclass().getDeclaredField("puntosDeVida");
                field.setAccessible(true);
                field.setInt(personaje, nuevaVida);
            } catch (Exception e) {
                System.out.println("Error al curar: " + e.getMessage());
                return false;
            }
            pocionDisponible = false;
            System.out.println(personaje.getNombre() + " usó una poción y recuperó 30 puntos de vida.");
            return true;
        } else {
            System.out.println("¡La poción ya fue usada!");
            return false;
        }
    }

    public boolean tienePocion() {
        return pocionDisponible;
    }
}
