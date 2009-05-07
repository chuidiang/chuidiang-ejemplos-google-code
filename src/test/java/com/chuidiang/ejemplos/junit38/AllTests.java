package com.chuidiang.ejemplos.junit38;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Un TestSuite, que contiene las clases de test de TestSuma y TestResta.
 * Ejecutando con Junit una clase con método suite(), se ejecutarán todos los
 * test que contenga dicha clase.
 * 
 * @author Chuidiang
 * 
 */
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.chuidiang.ejemplos");
        suite.addTestSuite(TestResta.class);
        suite.addTestSuite(TestSuma.class);
        return suite;
    }

}
