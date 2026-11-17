public class MImage {
    public static void main(String args[]) throws Exception {

        System.out.println("          RED DE HOPFIELD - MARIO BROS (10x10)         \n");

        // Ruta de la carpeta donde se guardan los archivos .txt de las figuras
        String ruta = "datasets/";

        // 1. LEER LOS 5 PATRONES DESDE LOS ARCHIVOS TXT
        // 1. Mario
        int[] mario = new int[100];
        java.util.Scanner sc1 = new java.util.Scanner(new java.io.File(ruta + "mario.txt"));
        for (int i = 0; i < 100; i++) {
            mario[i] = sc1.nextInt();
        }
        sc1.close();

        // 2. Super Hongo
        int[] hongo = new int[100];
        java.util.Scanner sc2 = new java.util.Scanner(new java.io.File(ruta + "hongo.txt"));
        for (int i = 0; i < 100; i++) {
            hongo[i] = sc2.nextInt();
        }
        sc2.close();

        // 3. Super Estrella
        int[] estrella = new int[100];
        java.util.Scanner sc3 = new java.util.Scanner(new java.io.File(ruta + "estrella.txt"));
        for (int i = 0; i < 100; i++) {
            estrella[i] = sc3.nextInt();
        }
        sc3.close();

        // 4. Goomba
        int[] goomba = new int[100];
        java.util.Scanner sc4 = new java.util.Scanner(new java.io.File(ruta + "goomba.txt"));
        for (int i = 0; i < 100; i++) {
            goomba[i] = sc4.nextInt();
        }
        sc4.close();

        // 5. Flor de Fuego
        int[] flor = new int[100];
        java.util.Scanner sc5 = new java.util.Scanner(new java.io.File(ruta + "flor.txt"));
        for (int i = 0; i < 100; i++) {
            flor[i] = sc5.nextInt();
        }
        sc5.close();

        // 2. IMPRIMIR LOS PATRONES EN PANTALLA (CON CUADROS ██)
        System.out.println("Patrón 1: Mario");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                System.out.print(mario[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 2: Super Hongo");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                System.out.print(hongo[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 3: Super Estrella");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                System.out.print(estrella[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 4: Goomba");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                System.out.print(goomba[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 5: Flor de Fuego");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                System.out.print(flor[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        // 3. REEMPLAZAR CEROS POR -1 (REPRESENTACIÓN BIPOLAR)
        for (int k = 0; k < 100; k++) {
            if (mario[k] == 0)    mario[k] = -1;
            if (hongo[k] == 0)    hongo[k] = -1;
            if (estrella[k] == 0) estrella[k] = -1;
            if (goomba[k] == 0)   goomba[k] = -1;
            if (flor[k] == 0)     flor[k] = -1;
        }

        // 4. MATRICES DE CONEXIONES PARA CADA PATRÓN
        int N = 100;
        int[][] m1 = new int[N][N];
        int[][] m2 = new int[N][N];
        int[][] m3 = new int[N][N];
        int[][] m4 = new int[N][N];
        int[][] m5 = new int[N][N];

        for (int a = 0; a < N; a++) {
            for (int t = 0; t < N; t++) {
                m1[a][t] = mario[a] * mario[t];
                m2[a][t] = hongo[a] * hongo[t];
                m3[a][t] = estrella[a] * estrella[t];
                m4[a][t] = goomba[a] * goomba[t];
                m5[a][t] = flor[a] * flor[t];
            }
        }

        // 5. SUMAR TODAS LAS MATRICES EN LA MATRIZ TOTAL DE PESOS
        int[][] mTotal = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int a = 0; a < N; a++) {
                mTotal[i][a] = m1[i][a] + m2[i][a] + m3[i][a] + m4[i][a] + m5[i][a];
            }
        }

        // 6. PONER LA DIAGONAL PRINCIPAL EN CEROS
        for (int i = 0; i < N; i++) {
            mTotal[i][i] = 0;
        }

        // 7. LEER EL PATRÓN DE ENTRADA A RECONOCER
        int[] entrada = new int[100];
        java.util.Scanner sc6 = new java.util.Scanner(new java.io.File(ruta + "entrada.txt"));
        for (int i = 0; i < 100; i++) {
            entrada[i] = sc6.nextInt();
        }
        sc6.close();

        // Convertir ceros a -1 en la entrada
        for (int k = 0; k < 100; k++) {
            if (entrada[k] == 0) {
                entrada[k] = -1;
            }
        }

        System.out.println("--- PATRÓN DE ENTRADA (CON RUIDO A BUSCAR) ---");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                System.out.print(entrada[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        // 8. BÚSQUEDA Y RECUPERACIÓN (PROCESO ITERATIVO)
        int encontrado = 0;
        int iteracion = 1;

        while (encontrado == 0) {
            System.out.println("Iteración " + iteracion + ":");

            // Multiplicación: entrada * mTotal
            int[] producto = new int[N];
            for (int f = 0; f < N; f++) {
                for (int c = 0; c < N; c++) {
                    producto[f] = producto[f] + (entrada[c] * mTotal[f][c]);
                }
            }

            // Función de activación: si > 0 pone 1, si no pone -1
            for (int i = 0; i < N; i++) {
                if (producto[i] > 0) {
                    producto[i] = 1;
                } else {
                    producto[i] = -1;
                }
            }

            // Imprimir el estado actual en cuadros ██
            for (int f = 0; f < 10; f++) {
                for (int c = 0; c < 10; c++) {
                    System.out.print(producto[f * 10 + c] == 1 ? "██" : "  ");
                }
                System.out.println();
            }
            System.out.println();

            // Comprobar si entrada y producto son exactamente iguales
            encontrado = 1;
            for (int i = 0; i < N; i++) {
                if (entrada[i] != producto[i]) {
                    encontrado = 0;
                    break;
                }
            }

            if (encontrado == 1) {
                // Identificar a cuál de los 5 personajes corresponde el resultado
                int cMario = 0;
                int cHongo = 0;
                int cEstrella = 0;
                int cGoomba = 0;
                int cFlor = 0;

                for (int i = 0; i < N; i++) {
                    if (producto[i] == mario[i]) cMario++;
                    if (producto[i] == hongo[i]) cHongo++;
                    if (producto[i] == estrella[i]) cEstrella++;
                    if (producto[i] == goomba[i]) cGoomba++;
                    if (producto[i] == flor[i]) cFlor++;
                }

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

                System.out.println("==============================================");
                System.out.println("               ¡ENCONTRADO!                   ");
                System.out.println("  Personaje reconocido : " + personaje);
                System.out.println("  Coincidencia         : " + mayor + "%");
                System.out.println("==============================================\n");

                // Guardar el resultado final en resultado.txt (formato 0 y 1 para datasets)
                java.io.PrintWriter pw = new java.io.PrintWriter(ruta + "resultado.txt");
                for (int f = 0; f < 10; f++) {
                    for (int c = 0; c < 10; c++) {
                        pw.print((producto[f * 10 + c] > 0 ? 1 : 0) + " ");
                    }
                    pw.println();
                }
                pw.close();
                System.out.println("Guardado con éxito en: " + ruta + "resultado.txt\n");

            } else {
                for (int i = 0; i < N; i++) {
                    entrada[i] = producto[i];
                }
                iteracion++;
            }
        }
    }
}
