import java.util.Scanner;

class JuegoLucha {
    private Personaje jugador1;
    private Personaje jugador2;

    // Constructor para inicializar los personajes
    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    // Modificado: turno permite usar poción como acción
    private void turno(Personaje atacante, Personaje defensor, Scanner scanner) {
        System.out.println("\u001B[32mTurno de " + atacante.getNombre() + ". Puntos de vida de " + defensor.getNombre() + ": "
        + defensor.getPuntosDeVida() + "/" + defensor.getVidaMaxima() + "\u001B[0m");
        if (atacante instanceof Mochila) {
            Mochila m = (Mochila) atacante;
            boolean puedeCurar = m.tienePocion();
            System.out.println("Elige la acción para " + atacante.getNombre() + ":");
            System.out.println("1. Atacar");
            if (puedeCurar) {
                System.out.println("2. Usar poción de curación (+30 vida, 1 solo uso)");
            }
            int idx = -1;
            boolean valido = false;
            while (!valido) {
                System.out.print("Selecciona el número de acción: ");
                idx = scanner.nextInt();
                scanner.nextLine();
                if (idx == 1) {
                    valido = true;
                } else if (puedeCurar && idx == 2) {
                    valido = true;
                } else {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                }
            }
            if (puedeCurar && idx == 2) {
                boolean usada = m.usarPocion();
                if (usada) {
                    System.out.println("¡Has usado tu turno para curarte!");
                } else {
                    System.out.println("No se pudo usar la poción. Pierdes el turno.");
                }
                return;
            } else {
                atacante.atacar(defensor, scanner);
            }
        } else {
            atacante.atacar(defensor, scanner);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Opciones predeterminadas para Guerrero
        Arma[] armasGuerrero = {
                new Arma("Espada", 8),
                new Arma("Hacha", 10),
                new Arma("Lanza", 7)
        };
        Armadura[] armadurasGuerrero = {
                new Armadura("Pesada", 12),
                new Armadura("Ligera", 8),
                new Armadura("Cota de malla", 10),
                new Armadura("De metal", 11)
        };
        // Opciones predeterminadas para Mago
        Arma[] armasMago = {
                new Arma("Bastón mágico", 11),
                new Arma("Varita", 9),
                new Arma("Libro de hechizos", 10)
        };
        Armadura[] armadurasMago = {
                new Armadura("Túnica", 3),
                new Armadura("Capa mágica", 5),
                new Armadura("Escudo arcano", 7)
        };

        // --- JUGADOR 1 ---
        System.out.print("Introduce el nombre del jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Elige el tipo de personaje para jugador 1 (1=Guerrero, 2=Mago): ");
        int tipo1 = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        Personaje jugador1;
        if (tipo1 == 2) {
            // Mostrar armas mago
            System.out.println("Opciones de arma para Mago:");
            for (int i = 0; i < armasMago.length; i++) {
                System.out.println((i + 1) + ". " + armasMago[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras mago
            System.out.println("Opciones de armadura para Mago:");
            for (int i = 0; i < armadurasMago.length; i++) {
                System.out.println((i + 1) + ". " + armadurasMago[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador1 = new Mago.MagoBuilder()
                    .conNombre(nombre1)
                    .conArma(armasMago[armaIdx])
                    .conArmadura(armadurasMago[armaduraIdx])
                    .build();
        } else {
            // Mostrar armas guerrero
            System.out.println("Opciones de arma para Guerrero:");
            for (int i = 0; i < armasGuerrero.length; i++) {
                System.out.println((i + 1) + ". " + armasGuerrero[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras guerrero
            System.out.println("Opciones de armadura para Guerrero:");
            for (int i = 0; i < armadurasGuerrero.length; i++) {
                System.out.println((i + 1) + ". " + armadurasGuerrero[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador1 = new Guerrero.GuerreroBuilder()
                    .conNombre(nombre1)
                    .conArma(armasGuerrero[armaIdx])
                    .conArmadura(armadurasGuerrero[armaduraIdx])
                    .build();
        }
        // Decorar con mochila
        jugador1 = new Mochila(jugador1);

        // --- JUGADOR 2 ---
        System.out.print("Introduce el nombre del jugador 2: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Elige el tipo de personaje para jugador 2 (1=Guerrero, 2=Mago): ");
        int tipo2 = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        Personaje jugador2;
        if (tipo2 == 2) {
            // Mostrar armas mago
            System.out.println("Opciones de arma para Mago:");
            for (int i = 0; i < armasMago.length; i++) {
                System.out.println((i + 1) + ". " + armasMago[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras mago
            System.out.println("Opciones de armadura para Mago:");
            for (int i = 0; i < armadurasMago.length; i++) {
                System.out.println((i + 1) + ". " + armadurasMago[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador2 = new Mago.MagoBuilder()
                    .conNombre(nombre2)
                    .conArma(armasMago[armaIdx])
                    .conArmadura(armadurasMago[armaduraIdx])
                    .build();
        } else {
            // Mostrar armas guerrero
            System.out.println("Opciones de arma para Guerrero:");
            for (int i = 0; i < armasGuerrero.length; i++) {
                System.out.println((i + 1) + ". " + armasGuerrero[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras guerrero
            System.out.println("Opciones de armadura para Guerrero:");
            for (int i = 0; i < armadurasGuerrero.length; i++) {
                System.out.println((i + 1) + ". " + armadurasGuerrero[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador2 = new Guerrero.GuerreroBuilder()
                    .conNombre(nombre2)
                    .conArma(armasGuerrero[armaIdx])
                    .conArmadura(armadurasGuerrero[armaduraIdx])
                    .build();
        }
        // Decorar con mochila
        jugador2 = new Mochila(jugador2);

        // Crea el juego con los personajes seleccionados y comienza la pelea
        JuegoLucha juego = new JuegoLucha(jugador1, jugador2);
        juego.iniciarPelea();
    }

    // Metodo para iniciar la pelea
    public void iniciarPelea() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("La pelea comienza entre " + jugador1.getNombre() + " y " + jugador2.getNombre() + "...");
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            turno(jugador1, jugador2, scanner);
            if (jugador2.estaVivo()) {
                turno(jugador2, jugador1, scanner);
            }
        }
        // Mostrar el resultado de la pelea
        if (jugador1.estaVivo()) {
            System.out.println(jugador1.getNombre() + " ha ganado la pelea.");
        } else {
            System.out.println(jugador2.getNombre() + " ha ganado la pelea.");
        }
    }
}
