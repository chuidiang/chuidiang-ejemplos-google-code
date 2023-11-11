package com.chuidiang.ejemplos.semi_mazacote;

import java.util.Scanner;

/**
 * Main de ejemplo de una clase menos mazacote, m�s f�cil de testear. En vez de
 * leer directamnte de teclado y escribir de pantalla, lee de una interface y
 * escribe en una interface
 * 
 * @author Chuidiang
 */

public class ClaseSemiMazacote {

    /**
     * Instancia la clase Sumador, le facilita implementaciones de las
     * interfaces, y la pone a trabajar.
     * 
     * @param args
     */
    public static void main(String[] args) {

        // Implementacion de IfzScanner, que lee de teclado.
        IfzScanner unScanner = new IfzScanner() {
            private final Scanner scanner = new Scanner(System.in);

            public String nextLine() {
                return scanner.nextLine();
            }

        };

        // Implementacion de IfzMuestraResultados, que escribe en pantalla
        IfzMuestraResultados muestraResultados = new IfzMuestraResultados() {
            public void println(String textoPeticion) {
                System.out.println(textoPeticion);
            }
        };

        // Se crea Sumador y se inicializa.
        Sumador sumador = new Sumador();
        sumador.setIfzScanner(unScanner);
        sumador.setIfzMuestraResultados(muestraResultados);

        // Se le pone a trabajar.
        sumador.empiezaATrabajar();
    }
}
