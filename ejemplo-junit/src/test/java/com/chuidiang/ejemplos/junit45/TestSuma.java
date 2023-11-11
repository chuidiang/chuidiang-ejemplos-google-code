package com.chuidiang.ejemplos.junit45;

import com.chuidiang.ejemplos.Suma;
import org.junit.*;

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
public class TestSuma {

    private Suma suma;

    @Test
    public void aVerSiIncrementaBien() {
        Assert.assertEquals("Test incrementa", 2.0, suma.incrementa(1.0), 1e-6);
    }

    @Test
    @Ignore // este test falla a posta
    public void aVerSiSumaBien() {
        Assert.assertEquals("Test suma", 2.0, suma.getSuma(1.0, 1.0), 1e-6);
    }

    @Before
    public void paraEjecutarAntes() throws Exception {
        suma = new Suma();
    }

    @After
    public void paraEjecutarDespues() throws Exception {
        // Fin de test. Aqui liberar recursos o borrar rastros del test
    }

}
