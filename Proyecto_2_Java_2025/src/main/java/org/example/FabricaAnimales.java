package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

    public class FabricaAnimales {
        private static final Random random = new Random();

        private static final Map<ListadoAnimales, DatosAnimal> datosAnimales = new HashMap<>();

        static {
            datosAnimales.put(ListadoAnimales.Lobo, new DatosAnimal(Dieta.Carnívoro, "\uD83D\uDC3A", 50, 8, 15, 3));
            datosAnimales.put(ListadoAnimales.Boa, new DatosAnimal(Dieta.Carnívoro,"\uD83D\uDC0D",15,3,15,1));
            datosAnimales.put(ListadoAnimales.Zorro, new DatosAnimal(Dieta.Carnívoro,"\uD83E\uDD8A",8,2,12,2));
            datosAnimales.put(ListadoAnimales.Oso, new DatosAnimal(Dieta.Carnívoro,"\uD83D\uDC3B",500,80,17,2));
            datosAnimales.put(ListadoAnimales.Águila, new DatosAnimal(Dieta.Carnívoro,"\uD83E\uDD85",6,1,12,3));
            datosAnimales.put(ListadoAnimales.Tigre, new DatosAnimal(Dieta.Carnívoro,"\uD83D\uDC2F",170,45,16,2));
            datosAnimales.put(ListadoAnimales.Jaguar,new DatosAnimal(Dieta.Carnívoro,"\uD83D\uDC06",96,40,10,9));
            datosAnimales.put(ListadoAnimales.Komodo, new DatosAnimal(Dieta.Carnívoro,"\uD83D\uDC09",91,80,30,7));
            datosAnimales.put(ListadoAnimales.Leon, new DatosAnimal(Dieta.Carnívoro, "\uD83E\uDD81",190, 12, 15, 5));

            datosAnimales.put(ListadoAnimales.Caballo, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC34",400,60,15,4));
            datosAnimales.put(ListadoAnimales.Ciervo, new DatosAnimal(Dieta.Herbívoro,"\uD83E\uDD8C",300,50,9,4));
            datosAnimales.put(ListadoAnimales.Conejo, new DatosAnimal(Dieta.Herbívoro, "\uD83D\uDC30",2, 0.45, 9, 2));
            datosAnimales.put(ListadoAnimales.Ratón, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC2D",0.05,0.01,7,1));
            datosAnimales.put(ListadoAnimales.Cabra, new DatosAnimal(Dieta.Herbívoro, "\uD83D\uDC10", 60, 10, 8, 3));
            datosAnimales.put(ListadoAnimales.Oveja, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC11",70,15,17,3));
            datosAnimales.put(ListadoAnimales.Búfalo, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC03",700,100,18,3));
            datosAnimales.put(ListadoAnimales.Oruga, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC1B",0.01,0,1,0));
            datosAnimales.put(ListadoAnimales.Elefante, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC18",3000,200,50,3));
            datosAnimales.put(ListadoAnimales.Buey, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC02",500,90,18,3));

            datosAnimales.put(ListadoAnimales.Cerdo, new DatosAnimal(Dieta.Omnívoro, "\uD83D\uDC37", 80, 7, 10, 4));
            datosAnimales.put(ListadoAnimales.Jabalí, new DatosAnimal(Dieta.Herbívoro,"\uD83D\uDC17",400,50,9,2));
            datosAnimales.put(ListadoAnimales.Tejón, new DatosAnimal(Dieta.Omnívoro, "\uD83E\uDDA1", 10, 4, 12, 2));
            datosAnimales.put(ListadoAnimales.Pato, new DatosAnimal(Dieta.Omnívoro, "\uD83E\uDD86", 1, 0.15, 10, 4));

            // Agrega más animales según necesites
        }

        public static Animal crearAnimalAleatorio() {
            ListadoAnimales[] valores = ListadoAnimales.values();
            ListadoAnimales seleccionado = valores[random.nextInt(valores.length)];
            return crearAnimal(seleccionado);
        }

        public static ListadoAnimales getTipoAleatorio() {
            ListadoAnimales[] valores = ListadoAnimales.values();
            return valores[random.nextInt(valores.length)];
        }

        public static Animal crearAnimal(ListadoAnimales tipo) {
            DatosAnimal datos = datosAnimales.get(tipo);
            if (datos == null) throw new IllegalArgumentException("Datos no definidos para: " + tipo);
            Genero generoAleatorio = Math.random() < 0.5 ? Genero.Macho : Genero.Hembra;

            return new AnimalEspecífico(
                    tipo.name(),                    // String nombre
                    datos.peso,                    // double peso
                    datos.alimentoNecesario,       // double alimentoNecesario
                    datos.dieta,                   // Dieta dieta
                    datos.velocidadMaxima,         // int velocidadMaxima
                    datos.edadMaxima,              // int edadMaxima
                    datos.simbolo,                 // String simbolo
                    generoAleatorio                // Genero genero
            );
        }

        private record DatosAnimal(Dieta dieta, String simbolo, double peso, double alimentoNecesario, int edadMaxima, int velocidadMaxima) {}
    }
