package org.example;

import java.util.HashMap;
import java.util.Map;

public class TablaProbabilidades {

    private static final Map<ListadoAnimales, Map<ListadoAnimales, Integer>> tabla = new HashMap<>();

    static {
        Map<ListadoAnimales, Integer> Lobo = new HashMap<>();
        tabla.put(ListadoAnimales.Lobo, Lobo);
        Lobo.put(ListadoAnimales.Caballo, 10);
        Lobo.put(ListadoAnimales.Ciervo, 15);
        Lobo.put(ListadoAnimales.Conejo, 60);
        Lobo.put(ListadoAnimales.Ratón, 80);
        Lobo.put(ListadoAnimales.Cabra, 60);
        Lobo.put(ListadoAnimales.Oveja, 70);
        Lobo.put(ListadoAnimales.Jabalí, 15);
        Lobo.put(ListadoAnimales.Búfalo, 10);
        Lobo.put(ListadoAnimales.Pato, 40);
        Lobo.put(ListadoAnimales.Cerdo, 30);
        Lobo.put(ListadoAnimales.Tejón, 20);

        Map<ListadoAnimales, Integer> Boa = new HashMap<>();
        tabla.put(ListadoAnimales.Boa, Boa);
        Boa.put(ListadoAnimales.Zorro, 15);
        Boa.put(ListadoAnimales.Conejo, 20);
        Boa.put(ListadoAnimales.Ratón, 40);
        Boa.put(ListadoAnimales.Pato, 10);
        Boa.put(ListadoAnimales.Tejón, 5);

        Map<ListadoAnimales, Integer> Zorro = new HashMap<>();
        tabla.put(ListadoAnimales.Zorro, Zorro);
        Zorro.put(ListadoAnimales.Conejo, 70);
        Zorro.put(ListadoAnimales.Ratón, 90);
        Zorro.put(ListadoAnimales.Pato, 60);
        Zorro.put(ListadoAnimales.Oruga, 40);
        Zorro.put(ListadoAnimales.Cerdo, 50);
        Zorro.put(ListadoAnimales.Tejón, 30);

        Map<ListadoAnimales, Integer> Oso = new HashMap<>();
        tabla.put(ListadoAnimales.Oso, Oso);
        Oso.put(ListadoAnimales.Boa, 80);
        Oso.put(ListadoAnimales.Caballo, 40);
        Oso.put(ListadoAnimales.Ciervo, 80);
        Oso.put(ListadoAnimales.Conejo, 80);
        Oso.put(ListadoAnimales.Ratón, 90);
        Oso.put(ListadoAnimales.Cabra, 70);
        Oso.put(ListadoAnimales.Oveja, 70);
        Oso.put(ListadoAnimales.Jabalí, 50);
        Oso.put(ListadoAnimales.Búfalo, 20);
        Oso.put(ListadoAnimales.Pato, 10);
        Oso.put(ListadoAnimales.Buey, 20);
        Oso.put(ListadoAnimales.Cerdo, 70);
        Oso.put(ListadoAnimales.Tejón, 60);

        Map<ListadoAnimales, Integer> Águila = new HashMap<>();
        tabla.put(ListadoAnimales.Águila, Águila);
        Águila.put(ListadoAnimales.Zorro, 10);
        Águila.put(ListadoAnimales.Conejo, 90);
        Águila.put(ListadoAnimales.Ratón, 90);
        Águila.put(ListadoAnimales.Pato, 80);
        Águila.put(ListadoAnimales.Tejón, 20);

        Map<ListadoAnimales, Integer> Tigre = new HashMap<>();
        tabla.put(ListadoAnimales.Tigre, Tigre);
        Tigre.put(ListadoAnimales.Lobo, 40);
        Tigre.put(ListadoAnimales.Boa, 80);
        Tigre.put(ListadoAnimales.Zorro, 10);
        Tigre.put(ListadoAnimales.Caballo, 50);
        Tigre.put(ListadoAnimales.Ciervo, 90);
        Tigre.put(ListadoAnimales.Conejo, 90);
        Tigre.put(ListadoAnimales.Ratón, 90);
        Tigre.put(ListadoAnimales.Cabra, 80);
        Tigre.put(ListadoAnimales.Oveja, 90);
        Tigre.put(ListadoAnimales.Jabalí, 70);
        Tigre.put(ListadoAnimales.Búfalo, 15);
        Tigre.put(ListadoAnimales.Pato, 90);
        Tigre.put(ListadoAnimales.Buey, 15);
        Tigre.put(ListadoAnimales.Cerdo, 70);
        Tigre.put(ListadoAnimales.Tejón, 5);

        Map<ListadoAnimales, Integer> Jaguar = new HashMap<>();
        tabla.put(ListadoAnimales.Jaguar, Jaguar);
        Jaguar.put(ListadoAnimales.Lobo, 30);
        Jaguar.put(ListadoAnimales.Boa, 70);
        Jaguar.put(ListadoAnimales.Zorro, 10);
        Jaguar.put(ListadoAnimales.Caballo, 50);
        Jaguar.put(ListadoAnimales.Ciervo, 90);
        Jaguar.put(ListadoAnimales.Conejo, 90);
        Jaguar.put(ListadoAnimales.Ratón, 90);
        Jaguar.put(ListadoAnimales.Cabra, 90);
        Jaguar.put(ListadoAnimales.Oveja, 90);
        Jaguar.put(ListadoAnimales.Jabalí, 50);
        Jaguar.put(ListadoAnimales.Búfalo, 10);
        Jaguar.put(ListadoAnimales.Pato, 90);
        Jaguar.put(ListadoAnimales.Buey, 15);
        Jaguar.put(ListadoAnimales.Cerdo, 60);
        Jaguar.put(ListadoAnimales.Tejón, 20);

        Map<ListadoAnimales, Integer> Komodo = new HashMap<>();
        tabla.put(ListadoAnimales.Komodo, Komodo);
        Komodo.put(ListadoAnimales.Lobo, 30);
        Komodo.put(ListadoAnimales.Boa, 80);
        Komodo.put(ListadoAnimales.Zorro, 60);
        Komodo.put(ListadoAnimales.Caballo, 60);
        Komodo.put(ListadoAnimales.Ciervo, 90);
        Komodo.put(ListadoAnimales.Conejo, 90);
        Komodo.put(ListadoAnimales.Ratón, 90);
        Komodo.put(ListadoAnimales.Cabra, 90);
        Komodo.put(ListadoAnimales.Oveja, 90);
        Komodo.put(ListadoAnimales.Jabalí, 90);
        Komodo.put(ListadoAnimales.Búfalo, 10);
        Komodo.put(ListadoAnimales.Pato, 90);
        Komodo.put(ListadoAnimales.Buey, 10);
        Komodo.put(ListadoAnimales.Cerdo, 90);
        Komodo.put(ListadoAnimales.Tejón, 20);

        Map<ListadoAnimales, Integer> Leon = new HashMap<>();
        tabla.put(ListadoAnimales.Leon, Leon);
        Leon.put(ListadoAnimales.Lobo, 40);
        Leon.put(ListadoAnimales.Boa, 50);
        Leon.put(ListadoAnimales.Zorro, 20);
        Leon.put(ListadoAnimales.Caballo, 70);
        Leon.put(ListadoAnimales.Ciervo, 90);
        Leon.put(ListadoAnimales.Conejo, 90);
        Leon.put(ListadoAnimales.Ratón, 90);
        Leon.put(ListadoAnimales.Cabra, 90);
        Leon.put(ListadoAnimales.Oveja, 90);
        Leon.put(ListadoAnimales.Jabalí, 90);
        Leon.put(ListadoAnimales.Búfalo, 5);
        Leon.put(ListadoAnimales.Pato, 90);
        Leon.put(ListadoAnimales.Buey, 15);
        Leon.put(ListadoAnimales.Cerdo, 70);
        Leon.put(ListadoAnimales.Tejón, 20);

        Map<ListadoAnimales, Integer> Jabalí = new HashMap<>();
        tabla.put(ListadoAnimales.Jabalí, Jabalí);
        Jabalí.put(ListadoAnimales.Ratón, 50);
        Jabalí.put(ListadoAnimales.Oruga, 90);

        Map<ListadoAnimales, Integer> Pato = new HashMap<>();
        tabla.put(ListadoAnimales.Pato, Pato);
        Pato.put(ListadoAnimales.Oruga, 90);

        Map<ListadoAnimales, Integer> Cerdo = new HashMap<>();
        tabla.put(ListadoAnimales.Cerdo, Cerdo);
        Cerdo.put(ListadoAnimales.Oruga, 90);

        Map<ListadoAnimales, Integer> Tejón = new HashMap<>();
        tabla.put(ListadoAnimales.Tejón, Tejón);
        Tejón.put(ListadoAnimales.Boa, 70);
        Tejón.put(ListadoAnimales.Conejo, 70);
        Tejón.put(ListadoAnimales.Ratón, 70);

    }

    public static int getProbabilidad(ListadoAnimales depredador, ListadoAnimales presa, int nivelHambre) {
        int base = tabla.getOrDefault(depredador, new HashMap<>()).getOrDefault(presa, 0);

        // Ajuste según hambre (0 a 100)
        // Puedes usar una fórmula más sofisticada si lo deseas
        double factorHambre = 1.0 + (nivelHambre / 100.0); // hambre 0 => x1.0, hambre 100 => x2.0
        int ajustada = (int) Math.round(base * factorHambre);

        return Math.min(100, ajustada); // Nunca pasar de 100%
    }
}
