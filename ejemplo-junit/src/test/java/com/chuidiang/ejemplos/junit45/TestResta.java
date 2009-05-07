package com.chuidiang.ejemplos.junit45;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chuidiang.ejemplos.Resta;

/**
 * Una clase de tests con junit 4.5 El metodo que queramos que se ejecute antes
 * de hacer el test, debemos anotarlo con @Before. El que queramos que se
 * ejecute despues de los test debemos anotarlo con @After. Los metodos de test
 * con @Test. Para poder usar los metodos assert, debemos hacer un
 * 
 * import static org.junit.Assert.*;
 * 
 * @author Chuidiang
 */
public class TestResta {
    private Resta resta;

    @Test
    public void aVerSiDecrementaBien() {
        assertEquals("Test decrementa", 1.0, resta.decrementa(2.0), 1e-6);
    }

    @Test
    public void aVerSiRestaBien() {
        assertEquals("Test resta", 1.0, resta.getDiferencia(2.0, 1.0), 1e-6);
    }

    @Before
    public void paraEjecutarAntes() throws Exception {
        resta = new Resta();
    }

    @After
    public void paraEjecutarDespues() throws Exception {
        // Liberar recursos
    }

}
