package org.example;

import java.util.List;
import java.util.Random;

public class AnimalEspecífico extends Animal{
    public AnimalEspecífico (String nombre, double peso, double alimentoNecesario, Dieta dieta, int velocidadMaxima, int edadMaxima, String simbolo, Genero genero) {
        this.nombre = nombre;
        this.peso = peso;
        this.alimentoNecesario = alimentoNecesario;
        this.dieta = dieta;
        this.velocidadMaxima = velocidadMaxima;
        this.edadMaxima = edadMaxima;
        this.simbolo = simbolo;
        this.genero = genero;
        this.tipoAnimal = ListadoAnimales.valueOf(nombre);

        this.hambre = 50 + new Random().nextInt(30);
        this.sed = new Random().nextInt(20);
    }

    @Override
    public int[] moverse(Entidad[][] mapa) {
        if (!vivo) return null;

        List<int[]> direcciones = new java.util.ArrayList<>();
        direcciones.add(new int[]{-1, 0});  // arriba
        direcciones.add(new int[]{1, 0});   // abajo
        direcciones.add(new int[]{0, -1});  // izquierda
        direcciones.add(new int[]{0, 1});   // derecha
        direcciones.add(new int[]{-1, -1}); // arriba izquierda
        direcciones.add(new int[]{-1, 1});  // arriba derecha
        direcciones.add(new int[]{1, -1});  // abajo izquierda
        direcciones.add(new int[]{1, 1});   // abajo derecha

        java.util.Collections.shuffle(direcciones);

        for (int[] dir : direcciones) {
            int nuevaX = x + dir[0];
            int nuevaY = y + dir[1];

            if (nuevaX >= 0 && nuevaY >= 0 && nuevaX < mapa.length && nuevaY < mapa[0].length) {
                if (mapa[nuevaX][nuevaY] == null) {
                    return new int[]{nuevaX, nuevaY};
                }
            }
        }

        return null; // No pudo moverse
    }

    @Override
    public void comer(Entidad[][] mapa) {
        if (!vivo) return;

        int filas = mapa.length;
        int columnas = mapa[0].length;

        boolean alimentoEncontrado = false;

        // Buscar en el vecindario inmediato (8 direcciones)
        for (int dx = -1; dx <= 1 && !alimentoEncontrado; dx++) {
            for (int dy = -1; dy <= 1 && !alimentoEncontrado; dy++) {
                int nx = x + dx;
                int ny = y + dy;

                if (dx == 0 && dy == 0) continue;
                if (nx < 0 || ny < 0 || nx >= filas || ny >= columnas) continue;

                Entidad objetivo = mapa[nx][ny];

                // CASO PLANTAS (Herbívoros y Omnívoros pueden comerlas)
                if (objetivo instanceof Planta planta && planta.estaVivo()) {
                    if (dieta == Dieta.Herbívoro || dieta == Dieta.Omnívoro) {
                        double probabilidad = calcularProbabilidadComer();
                        if (Math.random() < probabilidad) {
                            if (planta.esVenenosa()) {
                                this.morir("envenenado por planta", true, mapa);
                                planta.consumir();
                                mapa[nx][ny] = null;
                                alimentoEncontrado = true;
                            } else {
                                this.hambre -= alimentoNecesario;
                                if (hambre < 0) hambre = 0;
                                planta.consumir();
                                mapa[nx][ny] = null;
                                alimentoEncontrado = true;
                                System.out.println(nombre + " comió planta en (" + nx + "," + ny + ")");
                            }
                        }
                    }
                }

                // CASO ANIMALES
                else if (objetivo instanceof Animal animalObjetivo && animalObjetivo.estaVivo()) {
                    switch (dieta) {
                        case Carnívoro -> {
                            if (animalObjetivo.getDieta() == Dieta.Herbívoro) {
                                ListadoAnimales depredador = this.getTipoAnimal();
                                ListadoAnimales presa = animalObjetivo.getTipoAnimal();

                                int probabilidad = TablaProbabilidades.getProbabilidad(depredador, presa, this.hambre);
                                if (Math.random() * 100 < probabilidad) {
                                    this.hambre -= animalObjetivo.getAlimentoNecesario();
                                    if (hambre < 0) hambre = 0;
                                    animalObjetivo.morir("depredado",false, mapa);
                                    alimentoEncontrado = true;
                                    System.out.println(nombre + " cazó a " + animalObjetivo.getNombre() + " en (" + nx + "," + ny + ")");
                                }
                            }
                        }

                        case Omnívoro -> {
                            if (animalObjetivo.getDieta() == Dieta.Herbívoro) {
                                ListadoAnimales depredador = this.getTipoAnimal();
                                ListadoAnimales presa = animalObjetivo.getTipoAnimal();

                                int probabilidad = TablaProbabilidades.getProbabilidad(depredador, presa, this.hambre);
                                if (Math.random() * 100 < probabilidad) {
                                    this.hambre -= animalObjetivo.getAlimentoNecesario();
                                    if (hambre < 0) hambre = 0;
                                    animalObjetivo.morir("depredado", false, mapa);
                                    alimentoEncontrado = true;
                                    System.out.println(nombre + " cazó a " + animalObjetivo.getNombre() + " en (" + nx + "," + ny + ")");
                                }
                            }
                        }
                    }
                }

                else if (objetivo instanceof Carne carne) {
                    if (dieta == Dieta.Carnívoro || dieta == Dieta.Omnívoro) {
                        double probabilidad = calcularProbabilidadComer();
                        if (Math.random() < probabilidad) {
                            if (carne.esVenenosa()) {
                                this.morir("envenenado por carne", true, mapa);
                                mapa[nx][ny] = null;
                                alimentoEncontrado = true;
                            } else {
                                this.hambre -= alimentoNecesario;
                                if (hambre < 0) hambre = 0;
                                mapa[nx][ny] = null;
                                alimentoEncontrado = true;
                                System.out.println(nombre + " comió carne en (" + nx + "," + ny + ")");
                            }
                        }
                    }
                }
            }
        }
    }
}
