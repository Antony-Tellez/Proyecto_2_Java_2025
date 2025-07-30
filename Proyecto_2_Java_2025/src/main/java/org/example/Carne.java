package org.example;

public class Carne implements Entidad{
    private int x, y;
    private String simbolo;
    private boolean viva = true; // Por si quieres permitir que se "descomponga"
    private boolean venenosa;

    public Carne(boolean venenosa) {
        this.venenosa = venenosa;
        this.simbolo = venenosa ? "☠\uFE0F" : "\uD83C\uDF56"; // 💀 si venenosa, 🥩 si no
        this.viva = true;
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
