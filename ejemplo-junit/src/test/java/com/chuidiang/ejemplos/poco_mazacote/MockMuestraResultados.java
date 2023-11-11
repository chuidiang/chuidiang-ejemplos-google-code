package com.chuidiang.ejemplos.poco_mazacote;

import com.chuidiang.ejemplos.semi_mazacote.IfzMuestraResultados;

import static org.junit.Assert.assertEquals;

/**
 * Una IfzMuestraResultados específica para test. En este test específico sabe
 * qué debe mostrar la clase Sumador, así que simplemente va comprobando que lo
 * que muestra la clase Sumador es lo esperado.
 * 
 * @author Chuidiang
 * 
 */
public class MockMuestraResultados implements IfzMuestraResultados {

    /** Para saber por qué texto del array vamos */
    private int contador = 0;

    /** Textos que esperamos recibir */
    private final String[] textosEsperados = { "Sumando 1:", "Sumando 2:",
            "1.0+2.0=3.0" };

    /** Devuelve el número de textos recibidos */
    public int getContador() {
        return contador;
    }

    /** Comprueba que el texto recibido coincide con el esperado */
    public void println(String textoRecibido) {
        assertEquals(textosEsperados[contador++], textoRecibido);
    }

}
