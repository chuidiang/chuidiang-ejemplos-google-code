package com.chuidiang.ejemplos.junit45;

import org.junit.runner.JUnitCore;

/**
 * Ejemplo de como se arranca una suite de test. Desde linea de comando, con el
 * classpath configurado, se haria
 * 
 * Con el classpath adecuado
 * 
 * set CLASSPATH=path/junit.jar;path/mi.jar
 * 
 * se ejecuta con
 * 
 * java org.junit.runner.JUnitCore com.chuidiang.ejemplos.junit45.AllTest
 * 
 * @author Chuidiang
 */
public class Main {
    public static void main(String[] args) {
        JUnitCore.main("com.chuidiang.ejemplos.junit45.AllTest");
    }
}
