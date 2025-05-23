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

        // Opciones predeterminadas para Berserker
        Arma[] armasBerserker = {
                new Arma("Espada", 8),
                new Arma("Hacha", 10),
                new Arma("Lanza", 7)
        };
        Armadura[] armadurasBerserker = {
                new Armadura("Pesada", 12),
                new Armadura("Ligera", 8),
                new Armadura("Cota de malla", 10),
                new Armadura("De metal", 11)
        };
        // Opciones predeterminadas para Druida
        Arma[] armasDruida = {
                new Arma("Bastón mágico", 11),
                new Arma("Varita", 9),
                new Arma("Báculo de roble", 10)
        };
        Armadura[] armadurasDruida = {
                new Armadura("Armadura de hojas", 3),
                new Armadura("Túnica de piel", 5),
                new Armadura("Escudo arcano", 7)
        };

        // --- JUGADOR 1 ---
        System.out.print("Introduce el nombre del jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Elige el tipo de personaje para jugador 1 (1=Berserker, 2=Druida): ");
        int tipo1 = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        Personaje jugador1;
        if (tipo1 == 2) {
            // Mostrar armas druida
            System.out.println("Opciones de arma para Druida:");
            for (int i = 0; i < armasDruida.length; i++) {
                System.out.println((i + 1) + ". " + armasDruida[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras druida
            System.out.println("Opciones de armadura para Druida:");
            for (int i = 0; i < armadurasDruida.length; i++) {
                System.out.println((i + 1) + ". " + armadurasDruida[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador1 = new Druida.DruidaBuilder()
                    .conNombre(nombre1)
                    .conArma(armasDruida[armaIdx])
                    .conArmadura(armadurasDruida[armaduraIdx])
                    .build();
        } else {
            // Mostrar armas berserker
            System.out.println("Opciones de arma para Berserker:");
            for (int i = 0; i < armasBerserker.length; i++) {
                System.out.println((i + 1) + ". " + armasBerserker[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras berserker
            System.out.println("Opciones de armadura para Berserker:");
            for (int i = 0; i < armadurasBerserker.length; i++) {
                System.out.println((i + 1) + ". " + armadurasBerserker[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador1 = new Berserker.BerserkerBuilder()
                    .conNombre(nombre1)
                    .conArma(armasBerserker[armaIdx])
                    .conArmadura(armadurasBerserker[armaduraIdx])
                    .build();
        }
        // Decorar con mochila
        jugador1 = new Mochila(jugador1);

        // --- JUGADOR 2 ---
        System.out.print("Introduce el nombre del jugador 2: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Elige el tipo de personaje para jugador 2 (1=Berserker, 2=Druida): ");
        int tipo2 = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        Personaje jugador2;
        if (tipo2 == 2) {
            // Mostrar armas druida
            System.out.println("Opciones de arma para Druida:");
            for (int i = 0; i < armasDruida.length; i++) {
                System.out.println((i + 1) + ". " + armasDruida[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras druida
            System.out.println("Opciones de armadura para Druida:");
            for (int i = 0; i < armadurasDruida.length; i++) {
                System.out.println((i + 1) + ". " + armadurasDruida[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador2 = new Druida.DruidaBuilder()
                    .conNombre(nombre2)
                    .conArma(armasDruida[armaIdx])
                    .conArmadura(armadurasDruida[armaduraIdx])
                    .build();
        } else {
            // Mostrar armas berserker
            System.out.println("Opciones de arma para Berserker:");
            for (int i = 0; i < armasBerserker.length; i++) {
                System.out.println((i + 1) + ". " + armasBerserker[i]);
            }
            System.out.print("Elige el arma: ");
            int armaIdx = scanner.nextInt() - 1;
            // Mostrar armaduras berserker
            System.out.println("Opciones de armadura para Berserker:");
            for (int i = 0; i < armadurasBerserker.length; i++) {
                System.out.println((i + 1) + ". " + armadurasBerserker[i]);
            }
            System.out.print("Elige la armadura: ");
            int armaduraIdx = scanner.nextInt() - 1;
            scanner.nextLine();
            jugador2 = new Berserker.BerserkerBuilder()
                    .conNombre(nombre2)
                    .conArma(armasBerserker[armaIdx])
                    .conArmadura(armadurasBerserker[armaduraIdx])
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
