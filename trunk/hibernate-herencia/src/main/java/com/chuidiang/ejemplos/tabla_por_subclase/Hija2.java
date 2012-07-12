package com.chuidiang.ejemplos.tabla_por_subclase;

public class Hija2 extends Padre {

    boolean atributoHija2;


    public boolean isAtributoHija2() {
        return atributoHija2;
    }


    public void setAtributoHija2(boolean atributoHija2) {
        this.atributoHija2 = atributoHija2;
    }


    @Override
    public String diQuienEres() {
        return "Soy Hijo2 y tengo un atributoHija2=" + atributoHija2;
    }

}
