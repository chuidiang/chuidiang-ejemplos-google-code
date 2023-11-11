package com.chuidiang.ejemplos.poco_mazacote;

import com.chuidiang.ejemplos.semi_mazacote.IfzScanner;

/**
 * Implmentacion de IfzScanner específica para este test. Las lineas devueltas
 * son "1" y "2".
 * 
 * @author Chuidiang
 * 
 */
public class MockScanner implements IfzScanner {

    /** Lineas que debe ir devolviendo */
    private final String[] lineas = { "1", "2" };

    /** Numero de lineas devueltas */
    private int contador = 0;

    /** Devuelve el número de líneas devueltas */
    public int getContador() {
        return contador;
    }

    /** Devuelve la siguiente linea que toca */
    public String nextLine() {
        return lineas[contador++];
    }

}
