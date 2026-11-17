# Red Neuronal de Hopfield - Temática Mario Bros (10x10)

Implementación en Java de una Red Neuronal de Hopfield para memoria asociativa y recuperación de patrones en una matriz de 10x10 ($N = 100$ neuronas), desarrollada sin librerías externas ni módulos adicionales (únicamente arreglos y ciclos nativos).

## Estructura del Proyecto
* `MImage.java`: Código fuente principal de la red de Hopfield.
* `datasets/`: Carpeta con los archivos de texto que contienen las matrices de 10x10 en ceros y unos:
  * `mario.txt` (Patrón 1: Mario)
  * `hongo.txt` (Patrón 2: Super Hongo)
  * `estrella.txt` (Patrón 3: Super Estrella)
  * `goomba.txt` (Patrón 4: Goomba)
  * `flor.txt` (Patrón 5: Flor de Fuego)
  * `entrada.txt` (Patrón de prueba / entrada con ruido a recuperar)
  * `resultado.txt` (Patrón resultante recuperado generado por la red)

## Compilación y Ejecución
```bash
javac MImage.java
java MImage
```
