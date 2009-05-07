package com.chuidiang.ejemplos.junit38;

/**
 * Ejemplo de como se arranca una suite de test. Desde linea de comando, con el
 * classpath configurado, se haria
 * 
 * set CLASSPATH=path\mi.jar;path\junit.jar
 * 
 * java junit.textui.TestRunner com.chuidiang.ejemplos.junit38.AllTests
 * 
 * o bien
 * 
 * java junit.swingui.TestRunner com.chuidiang.ejemplos.junit38.AllTests
 * 
 * @author Chuidiang
 */
public class Main {
    public static void main(String[] args) {
        // En modo texto
        // junit.textui.TestRunner
        // .main(new String[] { "com.chuidiang.ejemplos.junit38.AllTests" });

        // Con interface grafica de usuario
        junit.swingui.TestRunner
                .main(new String[] { "com.chuidiang.ejemplos.junit38.AllTests" });
    }
}
