package com.chuidiang.ejemplos;

/**
 * Clase tonta para poder hacer test de junit con ella
 * 
 * @author Chuidiang
 */
public class Suma {
    public double getSuma(double a, double b) {
        // Se multiplica en vez de sumar a posta, para que los test fallen
        return a * b;
    }

    public double incrementa(double a) {
        return a + 1;
    }
}
