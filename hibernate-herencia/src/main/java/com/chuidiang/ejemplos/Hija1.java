package com.chuidiang.ejemplos;

public class Hija1 extends Padre {
    int atributoHija1;


    public int getAtributoHija1() {
        return atributoHija1;
    }


    public void setAtributoHija1(int atributoHija1) {
        this.atributoHija1 = atributoHija1;
    }


    @Override
    public String diQuienEres() {
        return "Soy Hijo1 y tengo un atributoHija1="+atributoHija1;
    }
}
