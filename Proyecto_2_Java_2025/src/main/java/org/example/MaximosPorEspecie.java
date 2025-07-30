package org.example;

import java.util.HashMap;
import java.util.Map;

public class MaximosPorEspecie {
    public static final Map<ListadoAnimales, Integer> MAXIMOS = new HashMap<>();

    static {
        MAXIMOS.put(ListadoAnimales.Lobo, 30);
        MAXIMOS.put(ListadoAnimales.Boa, 30);
        MAXIMOS.put(ListadoAnimales.Zorro, 30);
        MAXIMOS.put(ListadoAnimales.Oso, 5);
        MAXIMOS.put(ListadoAnimales.Águila, 20);
        MAXIMOS.put(ListadoAnimales.Tigre, 20);
        MAXIMOS.put(ListadoAnimales.Jaguar, 20);
        MAXIMOS.put(ListadoAnimales.Komodo, 15);
        MAXIMOS.put(ListadoAnimales.Leon, 20);
        MAXIMOS.put(ListadoAnimales.Caballo, 20);
        MAXIMOS.put(ListadoAnimales.Ciervo, 20);
        MAXIMOS.put(ListadoAnimales.Conejo, 150);
        MAXIMOS.put(ListadoAnimales.Ratón, 500);
        MAXIMOS.put(ListadoAnimales.Cabra, 140);
        MAXIMOS.put(ListadoAnimales.Oveja, 140);
        MAXIMOS.put(ListadoAnimales.Jabalí, 50);
        MAXIMOS.put(ListadoAnimales.Búfalo, 10);
        MAXIMOS.put(ListadoAnimales.Pato, 200);
        MAXIMOS.put(ListadoAnimales.Oruga, 1000);
        MAXIMOS.put(ListadoAnimales.Elefante, 15);
        MAXIMOS.put(ListadoAnimales.Buey, 30);
        MAXIMOS.put(ListadoAnimales.Cerdo, 40);
        MAXIMOS.put(ListadoAnimales.Tejón, 30);
    }

    public static int getMaximo(ListadoAnimales especie) {
        return MAXIMOS.getOrDefault(especie, 0);
    }
}
