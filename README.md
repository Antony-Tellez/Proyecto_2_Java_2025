# Proyecto_2_Java_2025
Segundo Proyecto de Java 2025 (CodeGym)

Este proyecto simula un ecosistema en el que diferentes especies de animales interactúan con su entorno, el cual incluye plantas, agua, obstáculos y otros animales a través de comportamientos como el movimiento, la alimentación y la muerte. La arquitectura está organizada con un enfoque orientado a objetos, que permite una clara separación de responsabilidades y una alta extensibilidad.

Clase Animal y sus Subclases
La clase Animal es abstracta y define el comportamiento común a todas las especies animales: moverse, comer, envejecer, aumentar hambre y sed, y eventualmente morir. Las subclases, como AnimalEspecífico, implementan los métodos abstractos adaptándolos a cada tipo particular de animal. Esta abstracción permite que la lógica general de simulación sea independiente de las especies concretas, facilitando así la reutilización y extensión del código.

Interfaz Entidad y Otras Interfaces Funcionales
La interfaz Entidad representa cualquier objeto que pueda ocupar una celda del terreno: Plantas, Animales, Carne, Obstáculos, etc. Esta estrategia promueve un diseño basado en capacidades, lo que permite que diferentes clases implementen solo las funcionalidades que les corresponden sin cargar con lógica innecesaria.

Uso de los Enum ListadoAnimales, Dieta y Genero
El uso de enum en este proyecto es fundamental para mantener la consistencia, claridad y robustez en la representación de características esenciales de los animales. Cada uno de los enums —ListadoAnimales, Dieta y Genero— cumple un propósito específico dentro de la simulación y mejora la estructura del código al reducir el riesgo de errores y facilitar futuras extensiones.

El enum ListadoAnimales actúa como una enumeración centralizada de todas las especies del sistema, agrupando tanto animales carnívoros, herbívoros como omnívoros. Gracias a este enfoque, se pueden referenciar las especies de manera segura en diferentes partes del programa (Como la fábrica de animales, la tabla de probabilidades de caza o el control de cantidades máximas por especie), evitando errores por nombres mal escritos o cadenas mal formateadas. Además, facilita el uso de estructuras como Map o matrices asociadas a especies, ya que se cuenta con un conjunto finito y controlado de valores posibles.
Por su parte, el enum Dieta clasifica a los animales según su régimen alimenticio: Carnívoro, Herbívoro u Omnívoro. Esta clasificación es clave en la lógica de alimentación, ya que permite determinar qué tipo de entidades (plantas, otros animales o carne en el suelo) pueden ser consumidas por un animal. Además, se utiliza para condicionar comportamientos específicos dentro del método comer() y en la lógica de caza, así como para definir restricciones alimenticias implícitas en el sistema.
Finalmente, el enum Genero (con valores típicos como Macho y Hembra) permite representar el sexo biológico de los animales. Aunque en la versión actual aún no se ha desarrollado una lógica basada en el género, su inclusión desde el inicio del diseño representa una decisión proactiva y extensible que facilita la evolución del simulador hacia comportamientos más complejos y realistas.
En conjunto, el uso de estos tres enums proporciona una forma estructurada de representar aspectos esenciales de los animales en la simulación. Esto no solo mejora la legibilidad del código, sino que también permite aprovechar las características del lenguaje Java, como el control de tipo en tiempo de compilación y el uso eficiente en estructuras de control, colecciones y lógica condicional.

Clase Terreno
La Clase Terreno representa el mapa o entorno donde ocurre la simulación. Contiene una matriz de Entidad y métodos para inicializarla aleatoriamente, simular turnos y mostrar su estado. Cada turno representa una iteración del tiempo en la simulación, y es responsable de aplicar todas las reglas internas: Envejecimiento, movimiento, alimentación y muerte. Además, implementa una lógica para detener la simulación si todos los animales han muerto, haciendo el sistema más realista y eficiente.

Clase FabricaAnimales
La FabricaAnimales es responsable de crear instancias de animales con características específicas por especie. Internamente usa un mapa (Map) que asocia cada ListadoAnimales con un conjunto de datos (DatosAnimal) que define su dieta, peso, necesidades alimenticias, símbolo gráfico, etc. Esta decisión encapsula la lógica de construcción, facilita cambios futuros y permite integrar límites de cantidad por especie en la generación aleatoria.

Clase TablaProbabilidades y Lógica de Alimentación
La clase TablaProbabilidades gestiona las probabilidades de que un depredador se alimente de una presa, ajustadas según su nivel de hambre. Esto aporta una capa de realismo al comportamiento de caza y permite diferenciar la efectividad de caza según especie y contexto. Estas probabilidades permiten una simulación más controlada y coherente.

Gestión de Muerte por Veneno
El proyecto incluye un sistema para identificar si un animal ha muerto por ingerir carne o plantas venenosas. Si eso ocurre, se deja carne envenenada, y esta a su vez puede envenenar a otros carnívoros. Esta mecánica genera un efecto de “cadena alimenticia tóxica”, agregando profundidad y consecuencias a las decisiones de alimentación dentro del ecosistema.

Simulación y Entrada del Usuario (Main)
La clase Main actúa como punto de entrada del sistema e interactúa con el usuario por consola. Permite personalizar el nombre y el número de turnos por iteración. También valida entradas incorrectas y permite repetir la simulación por bloques. Su diseño busca ser amigable e intuitivo para el usuario, fomentando la exploración del sistema de manera controlada.

