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

    public void inicializarTerrenoAleatoriamente(int numAnimales, int numPlantas, int numRocas, int numCharcos) {
        List<int[]> posicionesDisponibles = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                posicionesDisponibles.add(new int[]{i, j});
            }
        }

        Collections.shuffle(posicionesDisponibles);

        int totalRequerido = numAnimales + numPlantas + numRocas + numCharcos;
        if (totalRequerido > posicionesDisponibles.size()) {
            System.out.println("Advertencia: No hay suficientes celdas para todas las entidades.");
            totalRequerido = posicionesDisponibles.size();
        }

        int index = 0;

        Map<ListadoAnimales, Integer> conteoActual = new HashMap<>();
        for (ListadoAnimales tipo : ListadoAnimales.values()) {
            conteoActual.put(tipo, 0);
        }

        int intentos = 0;
        int animalesGenerados = 0;
        while (animalesGenerados < numAnimales && index < posicionesDisponibles.size() && intentos < numAnimales * 10) {
            intentos++;
            ListadoAnimales tipo = FabricaAnimales.getTipoAleatorio();
            int maxPermitido = MaximosPorEspecie.getMaximo(tipo);
            int actuales = conteoActual.get(tipo);

            if (actuales >= maxPermitido) {
                continue;
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

    public void mostrarTerreno() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Entidad e = mapa[i][j];
                if (e != null) {
                    System.out.print(e.getSimbolo() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void simularTurno() {
        Entidad[][] nuevoMapa = new Entidad[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Entidad entidad = mapa[i][j];

                if (entidad instanceof Animal animal) {
                    if (!animal.estaVivo()) {
                        continue;
                    }

                    animal.aumentarHambre(mapa);
                    animal.aumentarSed(mapa);
                    animal.envejecer(mapa);
                    animal.comer(mapa);

                    if (!animal.estaVivo()) {
                        Carne carne;
                        if (animal.isVenenosoAlMorir()) {
                            carne = new Carne(true);
                        } else {
                            carne = new Carne(false);
                        }

                        carne.setPosicion(i, j);
                        nuevoMapa[i][j] = carne;

                    } else {
                        int[] nuevaPos = animal.moverse(mapa);

                        if (nuevaPos != null) {
                            int nuevaX = nuevaPos[0];
                            int nuevaY = nuevaPos[1];

                            if (nuevoMapa[nuevaX][nuevaY] == null) {
                                nuevoMapa[nuevaX][nuevaY] = animal;
                                animal.setPosicion(nuevaX, nuevaY);
                            } else {
                                nuevoMapa[i][j] = animal;
                            }

                            mapa[i][j] = null;

                        } else {
                            nuevoMapa[i][j] = animal;
                        }
                    }

                } else if (entidad != null) {
                    nuevoMapa[i][j] = entidad;
                }
            }
        }

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
