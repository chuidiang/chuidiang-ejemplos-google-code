package com.chuidiang.ejemplos.mvp;

/**
 * Clase de Modelo para el ejemplo Modelo-Vista-Presenter.<br>
 * Es una clase sencilla para el ejemplo y sabe que numeros son pares o impares.
 * 
 * @author Chuidiang
 */
public class Modelo {
    /** Tipo pares */
    public static final String PARES = "pares";

    /** Tipo impares */
    public static final String IMPARES = "impares";

    /** Posibles valores pares */
    private static final int[] pares = { 2, 4, 6 };

    /** Posibles valores impares */
    private static final int[] impares = { 1, 3, 5 };

    /**
     * Devuelve los posibles valores segun el tipo que se le pase.
     * 
     * @param tipo
     *            PARES o IMPARES
     * @return array de valores pares o impares. null si no el tipo no es PARES
     *         o IMPARES
     */
    public int[] getPosiblesValores(String tipo) {
        if (PARES.equals(tipo))
            return pares;
        if (IMPARES.equals(tipo))
            return impares;
        return null;
    }
}
