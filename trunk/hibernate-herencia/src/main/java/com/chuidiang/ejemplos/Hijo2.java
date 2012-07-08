package com.chuidiang.ejemplos;

public class Hijo2 extends Dato {

    boolean booleano;

    public boolean isBooleano() {
        return booleano;
    }

    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }

    @Override
    public String diQuienEres() {
        return "Soy Hijo2 y tengo un booleano=" + booleano;
    }

}
