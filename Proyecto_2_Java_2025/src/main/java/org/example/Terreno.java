package org.example;

import java.util.*;

public class Terreno {
    private Entidad[][] mapa;
    private int filas;
    private int columnas;
    private Random random = new Random();

    public Terreno(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new Entidad[filas][columnas];
    }

    public Entidad[][] getMapa() {
        return mapa;
    }

    public void colocarEntidad(Entidad entidad, int x, int y) {
        if (x >= 0 && y >= 0 && x < filas && y < columnas && mapa[x][y] == null) {
            mapa[x][y] = entidad;
            entidad.setPosicion(x, y);
        }
    }

    // Inicializa el terreno con entidades aleatorias
    public void inicializarTerrenoAleatoriamente(int numAnimales, int numPlantas, int numRocas, int numCharcos) {
        List<int[]> posicionesDisponibles = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                posicionesDisponibles.add(new int[]{i, j});
            }
        }

        // Mezclar posiciones aleatoriamente
        Collections.shuffle(posicionesDisponibles);

        int totalRequerido = numAnimales + numPlantas + numRocas + numCharcos;
        if (totalRequerido > posicionesDisponibles.size()) {
            System.out.println("Advertencia: No hay suficientes celdas para todas las entidades.");
            totalRequerido = posicionesDisponibles.size(); // evitar error
        }

        int index = 0;

        // 👉 Contador por especie
        Map<ListadoAnimales, Integer> conteoActual = new HashMap<>();
        for (ListadoAnimales tipo : ListadoAnimales.values()) {
            conteoActual.put(tipo, 0);
        }

        int intentos = 0;
        int animalesGenerados = 0;
        while (animalesGenerados < numAnimales && index < posicionesDisponibles.size() && intentos < numAnimales * 10) {
            intentos++;
            ListadoAnimales tipo = FabricaAnimales.getTipoAleatorio(); // Necesitas este método en la fábrica
            int maxPermitido = MaximosPorEspecie.getMaximo(tipo);
            int actuales = conteoActual.get(tipo);

            if (actuales >= maxPermitido) {
                continue; // Ya se alcanzó el máximo para esta especie
            }

            Animal animal = FabricaAnimales.crearAnimal(tipo);

            int[] pos = posicionesDisponibles.get(index++);
            colocarEntidad(animal, pos[0], pos[1]);
            conteoActual.put(tipo, actuales + 1);
            animalesGenerados++;
        }

        // Insertar plantas
        for (int i = 0; i < numPlantas && index < posicionesDisponibles.size(); i++, index++) {
            int[] pos = posicionesDisponibles.get(index);
            boolean esVenenosa = Math.random() < 0.3;
            Planta planta = new Planta(esVenenosa);
            colocarEntidad(planta, pos[0], pos[1]);
        }

        // Insertar rocas
        for (int i = 0; i < numRocas && index < posicionesDisponibles.size(); i++, index++) {
            int[] pos = posicionesDisponibles.get(index);
            ObstaculoTerreno roca = new ObstaculoTerreno("Roca");
            colocarEntidad(roca, pos[0], pos[1]);
        }

        // Insertar charcos
        for (int i = 0; i < numCharcos && index < posicionesDisponibles.size(); i++, index++) {
            int[] pos = posicionesDisponibles.get(index);
            ObstaculoTerreno charco = new ObstaculoTerreno("Agua");
            colocarEntidad(charco, pos[0], pos[1]);
        }
    }

    // Método de utilidad para crear un animal aleatorio
    private Animal crearAnimalAleatorio() {
        return FabricaAnimales.crearAnimalAleatorio();
    }

    // Muestra el mapa en la consola
    public void mostrarTerreno() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Entidad e = mapa[i][j];
                if (e != null) {
                    System.out.print(e.getSimbolo() + " ");
                } else {
                    System.out.print(". ");  // Espacio vacío
                }
            }
            System.out.println();
        }
    }

    // Simula un turno completo
    public void simularTurno() {
        Entidad[][] nuevoMapa = new Entidad[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Entidad entidad = mapa[i][j];

                if (entidad instanceof Animal animal) {
                    if (!animal.estaVivo()) {
                        continue; // ya está muerto, no se procesa
                    }

                    animal.aumentarHambre(mapa);
                    animal.aumentarSed(mapa);
                    animal.envejecer(mapa);
                    animal.comer(mapa); // Este método puede matar al animal por veneno

                    if (!animal.estaVivo()) {
                        // El animal murió tras comer o por condiciones internas

                        Carne carne;
                        if (animal.isVenenosoAlMorir()) {
                            carne = new Carne(true); // carne venenosa
                        } else {
                            carne = new Carne(false); // carne normal
                        }

                        carne.setPosicion(i, j);
                        nuevoMapa[i][j] = carne;

                    } else {
                        // El animal está vivo, puede moverse
                        int[] nuevaPos = animal.moverse(mapa);

                        if (nuevaPos != null) {
                            int nuevaX = nuevaPos[0];
                            int nuevaY = nuevaPos[1];

                            if (nuevoMapa[nuevaX][nuevaY] == null) {
                                nuevoMapa[nuevaX][nuevaY] = animal;
                                animal.setPosicion(nuevaX, nuevaY);
                            } else {
                                // Colisión, se queda donde estaba
                                nuevoMapa[i][j] = animal;
                            }

                            mapa[i][j] = null; // limpiar la posición anterior

                        } else {
                            nuevoMapa[i][j] = animal; // no se movió
                        }
                    }

                } else if (entidad != null) {
                    // Plantas, obstáculos, carne, etc., simplemente se copian
                    nuevoMapa[i][j] = entidad;
                }
            }
        }

        // Reemplazamos el mapa original por el nuevo
        mapa = nuevoMapa;
    }

    public boolean quedanAnimalesVivos() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                Entidad entidad = mapa[i][j];
                if (entidad instanceof Animal animal && animal.estaVivo()) {
                    return true;
                }
            }
        }
        return false;
    }
}
