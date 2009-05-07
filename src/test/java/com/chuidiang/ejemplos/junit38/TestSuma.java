package com.chuidiang.ejemplos.junit38;

import com.chuidiang.ejemplos.Suma;

import junit.framework.TestCase;

public class TestSuma extends TestCase {

    private Suma suma;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        suma = new Suma();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // Fin de test. Aqui liberar recursos o borrar rastros del test
    }

    public void testGetSuma() {
        assertEquals("Test suma", 2.0, suma.getSuma(1.0, 1.0), 1e-6);
    }

    public void testIncrementa() {
        assertEquals("Test incrementa", 2.0, suma.incrementa(1.0), 1e-6);
    }

}
