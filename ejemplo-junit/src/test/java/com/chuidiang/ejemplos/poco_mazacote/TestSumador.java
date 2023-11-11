package com.chuidiang.ejemplos.poco_mazacote;

import com.chuidiang.ejemplos.semi_mazacote.Sumador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test de prueba de la clase Sumador
 * 
 * @author Chuidiang
 * 
 */
public class TestSumador {

    /** Clase a probar */
    private Sumador sumador;

    /** scanner específico para el test */
    private MockScanner scanner;

    /** MuestraResultados específico para el test */
    private MockMuestraResultados muestraResultados;

    /**
     * Crea el Sumador, los dos Mock e inicializa sumador.
     */
    @Before
    public void inicializacion() {
        sumador = new Sumador();
        scanner = new MockScanner();
        muestraResultados = new MockMuestraResultados();

        sumador.setIfzScanner(scanner);
        sumador.setIfzMuestraResultados(muestraResultados);

    }

    /**
     * Comprueba que Sumador suma bien.
     */
    @Test
    public final void testEmpiezaATrabajar() {
        sumador.empiezaATrabajar();

        // Se comprueba que se han leido las dos lineas del scanner
        assertEquals(2, scanner.getContador());

        // Se comprueba que han salido tres lineas por pantalla: para pedir los
        // dos sumadores y el resultado.
        assertEquals(3, muestraResultados.getContador());
    }
}
