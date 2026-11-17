public class MImage {
    public static void main(String args[]) throws Exception {

        System.out.println("          RED DE HOPFIELD - MARIO BROS (10x10)         \n");

        // Ruta de la carpeta donde se guardan los archivos .txt de las figuras
        String ruta = "datasets/";

        // 1. LEER LOS 5 PATRONES (figuras de 10x10 = 100 pixeles)
        // 1. Mario
        int[] mario = new int[100];
        java.util.Scanner sc1 = new java.util.Scanner(new java.io.File(ruta + "mario.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros del txt uno por uno
            mario[i] = sc1.nextInt();
        }
        sc1.close();

        // 2. Super Hongo
        int[] hongo = new int[100];
        java.util.Scanner sc2 = new java.util.Scanner(new java.io.File(ruta + "hongo.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros del hongo
            hongo[i] = sc2.nextInt();
        }
        sc2.close();

        // 3. Super Estrella
        int[] estrella = new int[100];
        java.util.Scanner sc3 = new java.util.Scanner(new java.io.File(ruta + "estrella.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de la estrella
            estrella[i] = sc3.nextInt();
        }
        sc3.close();

        // 4. Goomba
        int[] goomba = new int[100];
        java.util.Scanner sc4 = new java.util.Scanner(new java.io.File(ruta + "goomba.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de goomba
            goomba[i] = sc4.nextInt();
        }
        sc4.close();

        // 5. Flor de Fuego
        int[] flor = new int[100];
        java.util.Scanner sc5 = new java.util.Scanner(new java.io.File(ruta + "flor.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de la flor
            flor[i] = sc5.nextInt();
        }
        sc5.close();

        // 2. IMPRIMIR LOS PATRONES EN PANTALLA (CON CUADROS)
        // f = fila (0 a 9), c = columna (0 a 9), f*10+c accede al arreglo plano de 100
        System.out.println("Patrón 1: Mario");
        for (int f = 0; f < 10; f++) { // f = recorre las 10 filas
            for (int c = 0; c < 10; c++) { // c = recorre las 10 columnas
                System.out.print(mario[f * 10 + c] == 1 ? "██" : "  "); // pinta cuadro si es 1 o espacio si es 0
            }
            System.out.println(); // salto de linea al terminar la fila
        }
        System.out.println();

        System.out.println("Patrón 2: Super Hongo");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(hongo[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 3: Super Estrella");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(estrella[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 4: Goomba");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(goomba[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 5: Flor de Fuego");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(flor[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        // 3. REEMPLAZAR CEROS POR -1 (REPRESENTACIÓN BIPOLAR)
        for (int k = 0; k < 100; k++) { // k = posicion del pixel (0 a 99)
            if (mario[k] == 0)    mario[k] = -1;
            if (hongo[k] == 0)    hongo[k] = -1;
            if (estrella[k] == 0) estrella[k] = -1;
            if (goomba[k] == 0)   goomba[k] = -1;
            if (flor[k] == 0)     flor[k] = -1;
        }

        // 4. Matrices de memoria N*N (figura 10x10 = 100 pixeles, matriz de 100x100)
        int N = 100;
        int[][] m1 = new int[N][N];
        int[][] m2 = new int[N][N];
        int[][] m3 = new int[N][N];
        int[][] m4 = new int[N][N];
        int[][] m5 = new int[N][N];

        // producto cruzado pixel por pixel (X * X transpuesta)
        for (int a = 0; a < N; a++) { // a = pixel en la fila
            for (int t = 0; t < N; t++) { // t = pixel en la columna
                m1[a][t] = mario[a] * mario[t];
                m2[a][t] = hongo[a] * hongo[t];
                m3[a][t] = estrella[a] * estrella[t];
                m4[a][t] = goomba[a] * goomba[t];
                m5[a][t] = flor[a] * flor[t];
            }
        }

        // suma de memorias de las 5 figuras
        int[][] mTotal = new int[N][N];
        for (int i = 0; i < N; i++) { // i = fila de la matriz
            for (int a = 0; a < N; a++) { // a = columna de la matriz
                mTotal[i][a] = m1[i][a] + m2[i][a] + m3[i][a] + m4[i][a] + m5[i][a];
            }
        }

        // diagonal en ceros (i = misma fila y columna [i][i], no autoconexion)
        for (int i = 0; i < N; i++) { // i = recorre la diagonal de 0 a 99
            mTotal[i][i] = 0;
        }

        // 7. LEER EL PATRÓN DE ENTRADA A RECONOCER
        int[] entrada = new int[100];
        java.util.Scanner sc6 = new java.util.Scanner(new java.io.File(ruta + "entrada.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de la entrada
            entrada[i] = sc6.nextInt();
        }
        sc6.close();

        // Convertir ceros a -1 en la entrada
        for (int k = 0; k < 100; k++) { // k = pixel de la entrada
            if (entrada[k] == 0) {
                entrada[k] = -1;
            }
        }

        System.out.println("--- PATRÓN DE ENTRADA (CON RUIDO A BUSCAR) ---");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(entrada[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        // 8. BÚSQUEDA Y RECUPERACIÓN (PROCESO ITERATIVO)
        // La red busca corrigiendo pixeles dañados hasta estabilizarse
        int encontrado = 0;
        int iteracion = 1;

        while (encontrado == 0) {
            System.out.println("Iteración " + iteracion + ":");

            // PASO 1 (BUSCAR): Multiplica el patron por la matriz de memoria (suma de pesos)
            int[] producto = new int[N];
            for (int f = 0; f < N; f++) { // f = neurona de salida (fila)
                for (int c = 0; c < N; c++) { // c = neurona de entrada (columna)
                    producto[f] = producto[f] + (entrada[c] * mTotal[f][c]);
                }
            }

            // PASO 2 (CORREGIR PIXELES): Funcion de activacion bipolar (si > 0 es 1, si no es -1)
            for (int i = 0; i < N; i++) { // i = revisa el signo de cada pixel
                if (producto[i] > 0) {
                    producto[i] = 1;
                } else {
                    producto[i] = -1;
                }
            }

            // PASO 3: Imprimir como va quedando la figura en esta iteracion
            for (int f = 0; f < 10; f++) { // f = fila (0 a 9)
                for (int c = 0; c < 10; c++) { // c = columna (0 a 9)
                    System.out.print(producto[f * 10 + c] == 1 ? "██" : "  ");
                }
                System.out.println();
            }
            System.out.println();

            // PASO 4 (COMO SABE SI LO ENCONTRO): Compara entrada con producto.
            // Si son exactamente iguales en todos los pixeles, la red ya es estable (convergio)
            encontrado = 1;
            for (int i = 0; i < N; i++) { // i = compara pixel actual con el anterior
                if (entrada[i] != producto[i]) {
                    encontrado = 0; // si un solo pixel es diferente, todavia no termina
                    break;
                }
            }

            if (encontrado == 1) {
                // PASO 5 (IDENTIFICAR CUAL PERSONAJE ES):
                // Cuenta coincidencias de pixeles del resultado final con cada una de las 5 figuras originales
                int cMario = 0;
                int cHongo = 0;
                int cEstrella = 0;
                int cGoomba = 0;
                int cFlor = 0;

                for (int i = 0; i < N; i++) { // i = compara pixel por pixel con cada personaje original
                    if (producto[i] == mario[i]) cMario++; // si coincide con el pixel i de mario, suma 1
                    if (producto[i] == hongo[i]) cHongo++; // si coincide con el pixel i del hongo, suma 1
                    if (producto[i] == estrella[i]) cEstrella++; // si coincide con el pixel i de la estrella, suma 1
                    if (producto[i] == goomba[i]) cGoomba++; // si coincide con el pixel i de goomba, suma 1
                    if (producto[i] == flor[i]) cFlor++; // si coincide con el pixel i de flor, suma 1
                }

                // El que tenga mayor coincidencia es el personaje que la red reconocio
                int mayor = cMario;
                String personaje = "Mario (Patrón 1)";
                if (cHongo > mayor) {
                    mayor = cHongo;
                    personaje = "Super Hongo (Patrón 2)";
                }
                if (cEstrella > mayor) {
                    mayor = cEstrella;
                    personaje = "Super Estrella (Patrón 3)";
                }
                if (cGoomba > mayor) {
                    mayor = cGoomba;
                    personaje = "Goomba (Patrón 4)";
                }
                if (cFlor > mayor) {
                    mayor = cFlor;
                    personaje = "Flor de Fuego (Patrón 5)";
                }

                System.out.println("¡ENCONTRADO!");
                System.out.println("Personaje reconocido: " + personaje);
                System.out.println("Coincidencia: " + mayor + "%\n");

                // PASO 6 (GUARDAR): Guarda la figura recuperada limpia en resultado.txt
                java.io.PrintWriter pw = new java.io.PrintWriter(ruta + "resultado.txt");
                for (int f = 0; f < 10; f++) { // f = fila del resultado
                    for (int c = 0; c < 10; c++) { // c = columna del resultado
                        pw.print((producto[f * 10 + c] > 0 ? 1 : 0) + " ");
                    }
                    pw.println();
                }
                pw.close();
                System.out.println("Guardado con éxito en: " + ruta + "resultado.txt\n");

            } else {
                // SI AUN NO LO ENCUENTRA: El resultado se vuelve la nueva entrada y repite el ciclo
                for (int i = 0; i < N; i++) { // i = copia el producto para la siguiente iteracion
                    entrada[i] = producto[i];
                }
                iteracion++;
            }
        }
    }
}
