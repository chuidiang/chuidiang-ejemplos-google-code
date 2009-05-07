package com.chuidiang.ejemplos.junit38;

import com.chuidiang.ejemplos.Resta;

import junit.framework.TestCase;

public class TestResta extends TestCase {
    private Resta resta;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        resta = new Resta();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDecrementa() {
        assertEquals("Test decrementa", 1.0, resta.decrementa(2.0), 1e-6);
    }

    public void testGetDiferencia() {
        assertEquals("Test resta", 1.0, resta.getDiferencia(2.0, 1.0), 1e-6);
    }

}
