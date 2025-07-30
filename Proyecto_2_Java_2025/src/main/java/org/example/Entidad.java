package org.example;

public interface Entidad {
    int getX();
    int getY();
    void setPosicion(int x, int y);
    String getSimbolo(); // Para mostrar en consola
    boolean estaVivo(); // Puede ser true por defecto en plantas
}
