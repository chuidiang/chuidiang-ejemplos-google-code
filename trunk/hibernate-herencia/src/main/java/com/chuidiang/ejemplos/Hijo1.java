package com.chuidiang.ejemplos;

public class Hijo1 extends Dato {
    int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String diQuienEres() {
        return "Soy Hijo1 y tengo un numero="+numero;
    }
}
