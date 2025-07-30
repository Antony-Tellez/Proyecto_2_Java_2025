package org.example;

public abstract class Animal implements Entidad {
    protected String nombre;
    protected double peso;
    protected double alimentoNecesario;
    protected Dieta dieta;
    protected int edad;
    protected int edadMaxima;
    protected int hambre; // 0 (saciado) a 100 (muere)
    protected int sed;    // 0 (hidratado) a 100 (muere)
    protected int velocidadMaxima;
    protected Genero genero;
    protected String simbolo; // Representación en la matriz

    protected boolean vivo = true;

    protected boolean murioPorVeneno = false;

    protected int x, y; // Posición en la matriz

    protected ListadoAnimales tipoAnimal;

    public ListadoAnimales getTipoAnimal() {
        return tipoAnimal;
    }

    public Animal() {}

    // Métodos comunes
    public void envejecer(Entidad[][] mapa) {
        edad++;
        if (edad >= edadMaxima) {
            this.morir("vejez", false, mapa);
        }
    }

    public void aumentarSed(Entidad[][] mapa) {
        sed++;
        if (sed >= 100) {
            morir("sed", false, mapa);
        }
    }

    public void aumentarHambre(Entidad[][] mapa) {
        hambre++;
        if (hambre >= 100) {
            morir("hambre", false, mapa);
        }
    }

    public void morir(String causa, boolean dejaCarne, Entidad[][] mapa) {
        vivo = false;

        murioPorVeneno = causa.toLowerCase().contains("envenenado");

        System.out.println(nombre + " murió por " + causa + " en (" + x + "," + y + ")");

        if (dejaCarne) {
            Carne carne = new Carne(murioPorVeneno);
            carne.setPosicion(x, y);
            mapa[x][y] = carne;
        } else {
            mapa[x][y] = null;
        }
    }

    public boolean isVenenosoAlMorir() {
        return murioPorVeneno;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public String getNombre() {
        return nombre;
    }

    protected double calcularProbabilidadComer() {
        return 0.3 + 0.006 * hambre;
    }

    public double getAlimentoNecesario() {
        return alimentoNecesario;
    }

    // Métodos abstractos que cada subclase concreta deberá definir
    public abstract int[] moverse(Entidad[][] mapa);
    public abstract void comer(Entidad[][] mapa);
}

