package org.example;

public class ObstaculoTerreno implements Entidad{
    private int x;
    private int y;
    private final String tipo; // "Roca" o "Agua"
    private final String simbolo;

    public ObstaculoTerreno(String tipo) {
        this.tipo = tipo;
        this.simbolo = tipo.equals("Roca") ? "⛰\uFE0F" : "\uD83D\uDCA7";
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
        return true; // o false si deseas excluirlos de lógica de vida/muerte
    }

    public String getTipo() {
        return tipo;
    }
}
