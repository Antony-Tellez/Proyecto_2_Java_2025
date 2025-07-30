package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hola, por favor escriba el nombre con el que se quiera identificar aquí abajo en la terminal:");
        String name = scanner.nextLine();

        System.out.println("");
        System.out.println("Bienvenido " + name + ". \n" +
                "En esta aplicación podrás simular el comportamiento de diversas especies con su entorno. \n" +
                "¡Genial! ¿Verdad?");

        System.out.println("");
        System.out.println("Pero antes de iniciar: ¿Deseas conocer que animales puedes encontrar en esta simulación? \n" +
                "(Escriba “Si” en la terminal si lo desea o “No” si lo que busca es iniciar directamente el programa).");

        String answer1 = scanner.nextLine();

        while (true) {
            if (answer1.equalsIgnoreCase("Si")||answer1.equalsIgnoreCase("Sí")) {
                System.out.println("");
                System.out.println("Entre los animales puedes encontrar:\n" +
                        "Animales Carnívoros:\n" +
                        "Lobos, Boas, Zorros, Osos, Águilas, Tigres, Jaguares, Dragones de Komodo y Leones\n" +
                        "\n" +
                        "Animales Herbívoros:\n" +
                        "Caballos, Ciervos, Conejos, Ratones, Cabras, Ovejas, Búfalos, Orugas, Elefantes y Bueyes\n" +
                        "\n" +
                        "Y Animales Omnívoros:\n" +
                        "Cerdos, Tejones, Patos, Jabalies");
                System.out.println("");
                break;
            } else if (answer1.equalsIgnoreCase("No")) {
                System.out.println("");
                System.out.println("Ok, entonces sin más dilación comencemos!.");
                System.out.println("");
                break;
            } else {
                System.out.println("Respuesta no válida, por favor intentalo de nuevo");
                answer1 = scanner.nextLine();
            }
        }

        System.out.println("Recuerda que el tamaño del terreno en esta simulación, junto con la cantidad de animales generados es totalmente aleatoria.\n" +
                "Claro está, tu puedes seleccionar cuantos turnos se pueden simular en cada iteración.\n" +
                "\n" +
                "Además, debes tener en cuenta que: \n" +
                "En la primera inicialización de la simulación, el primer turno mostrado será el estado actual del terreno.\n" +
                "La simulación finalizará una vez mueran todos los animales.\n" +
                "\n" +
                "¿Cuántos turnos deseas simular a continuación?");

        int turnos = 0;

        while (true) {

            if (scanner.hasNextInt()) {
                turnos = scanner.nextInt();
                scanner.nextLine();

                if (turnos > 0) {
                    break;
                } else {
                    System.out.println("❌ El número debe ser mayor que cero. Inténtalo de nuevo.\n");
                }
            } else {
                System.out.println("❌ Entrada no válida. Debes ingresar un número entero positivo. Inténtalo de nuevo.\n");
                scanner.nextLine();
            }
        }

        System.out.println("Muy bien, ¡Comencemos!");

        Random random = new Random();

        // Terreno aleatorio entre 10x10 y 100x100
        int filas = 10 + random.nextInt(61);
        int columnas = 10 + random.nextInt(61);

        System.out.println("Tamaño del terreno generado: " + filas + " x " + columnas);

        Terreno terreno = new Terreno(filas, columnas);

        int totalCasillas = filas * columnas;
        int numAnimales = totalCasillas / 10; // 10% animales
        int numPlantas = totalCasillas / 5;   // 20% plantas
        int numRocas = totalCasillas / 20;
        int numCharcos = totalCasillas / 30;

        terreno.inicializarTerrenoAleatoriamente(numAnimales, numPlantas, numRocas, numCharcos);

        System.out.println("\nEstado inicial del terreno:");
        terreno.mostrarTerreno();

        terreno.simularTurno();

        // Ejecuta ciclos
        for (int i = 0; i < turnos; i++) {
            if (!terreno.quedanAnimalesVivos()) {
                System.out.println("\n❗ Todos los animales han muerto. La simulación ha terminado.");
                return;
            }

            System.out.println("\nDespués de " + (i+1) + " turno/s:");
            terreno.mostrarTerreno();
            terreno.simularTurno();
        }

        while (true) {
            System.out.println("\n¿Deseas simular más turnos? (Sí/No)");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("Sí") || respuesta.equalsIgnoreCase("Si")) {

                int nuevosTurnos = 0;
                while (true) {
                    System.out.println("¿Cuántos turnos adicionales deseas simular?");
                    if (scanner.hasNextInt()) {
                        nuevosTurnos = scanner.nextInt();
                        scanner.nextLine();
                        if (nuevosTurnos > 0) {
                            break;
                        } else {
                            System.out.println("❌ El número debe ser mayor que cero. Intenta nuevamente.");
                        }
                    } else {
                        System.out.println("❌ Entrada no válida. Ingresa un número entero positivo.");
                        scanner.nextLine();
                    }
                }

                for (int i = 0; i < nuevosTurnos; i++) {
                    if (!terreno.quedanAnimalesVivos()) {
                        System.out.println("\n❗ Todos los animales han muerto. La simulación ha terminado.");
                        return;
                    }

                    System.out.println("\nDespués de turno adicional " + (i + 1) + ":");
                    terreno.mostrarTerreno();
                    terreno.simularTurno();
                }

            } else if (respuesta.equalsIgnoreCase("No")) {
                System.out.println("Gracias por usar la simulación. ¡Hasta la próxima!");
                break;
            } else {
                System.out.println("❌ Respuesta no válida. Por favor, escribe 'Sí' o 'No'.");
            }
        }
    }
 }
