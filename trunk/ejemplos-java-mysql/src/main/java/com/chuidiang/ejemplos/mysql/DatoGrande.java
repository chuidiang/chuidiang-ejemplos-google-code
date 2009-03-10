package com.chuidiang.ejemplos.mysql;

import java.io.Serializable;

import javax.swing.JButton;

/**
 * Un dato serializable que tiene a su vez dentro otra clase serializable.
 * 
 * @author Chuidiang
 */
public class DatoGrande implements Serializable {
    /**
     * serial uid
     */
    private static final long serialVersionUID = -3145396459136305416L;

    /** Otra clase serializable */
    private Dato dato;

    /** Un valor double */
    private double valor;

    /** Una clase también Serializable */
    private JButton unBoton;

    public Dato getDato() {
        return dato;
    }

    public void setDato(Dato dato) {
        this.dato = dato;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public JButton getUnBoton() {
        return unBoton;
    }

    public void setUnBoton(JButton unBoton) {
        this.unBoton = unBoton;
    }

    /**
     * Para poder sacar por pantalla rapidamente el contenido de la clase.
     */
    public String toString() {
        return dato.toString() + " " + valor + " " + unBoton.getText();
    }
}
