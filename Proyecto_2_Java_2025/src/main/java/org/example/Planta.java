package org.example;

public class Planta implements Entidad {
    private int x, y;
    private String simbolo; // O usa otro símbolo
    private boolean viva = true;
    private boolean venenosa;

    public Planta(boolean venenosa) {
        this.venenosa = venenosa;
        this.simbolo = venenosa ? "\uD83C\uDF3A" : "\uD83C\uDF3F"; // 🌺 si venenosa, 🌿 si no
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getSimbolo() {
        return simbolo;
    }

    @Override
    public boolean estaVivo() {
        return viva;
    }

    public boolean esVenenosa() {
        return venenosa;
    }

    public void consumir() {
        viva = false;
    }
}
