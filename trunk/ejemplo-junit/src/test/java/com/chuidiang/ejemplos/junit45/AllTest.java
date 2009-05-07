package com.chuidiang.ejemplos.junit45;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Ejemplo de TestSuite en junit 4.5
 * 
 * Basta hacer una clase cualquiera, aunque no tenga codigo, como esta, con dos
 * anotcaciones.
 * 
 * @RunWith(Suite.class) para indicar que esta clase debe ejecutarse como una
 *                       Suite de test de Junit
 * 
 * @SuiteClasses( { TestResta.class, TestSuma.class }) con todas las clases de
 *                test que queremos que se ejecuten.
 * 
 * @author Chuidiang
 * 
 */
@RunWith(Suite.class)
@SuiteClasses( { TestResta.class, TestSuma.class })
public class AllTest {

}
