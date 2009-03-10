package com.chuidiang.ejemplos.mysql;

import java.io.Serializable;
import java.util.Date;

/**
 * Un dato serializable, para poder insertarlo en base de datos.
 * 
 * @author Chuidiang
 */
public class Dato implements Serializable {
    /**
     * serial uid
     */
    private static final long serialVersionUID = -48390394042382173L;

    /** Un campo fecha */
    private Date fecha;

    /** Un campo int */
    private int valor;

    /** Un campo String */
    private String cadena;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     * Para poder sacar por pantalla rapidamente el contenido de la clase
     */
    public String toString() {
        return fecha + " " + valor + " " + cadena;
    }
}
