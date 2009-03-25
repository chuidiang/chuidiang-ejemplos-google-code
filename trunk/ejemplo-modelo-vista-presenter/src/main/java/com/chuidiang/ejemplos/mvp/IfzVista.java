package com.chuidiang.ejemplos.mvp;

/**
 * Interface que implementa la vista.<br>
 * Aquí deben estar todos los métodos que necesite llamar el Presenter.
 * 
 * @author Chuidiang
 * 
 */
public interface IfzVista {

    /** Devuelve el tipo seleccionado en el primer combo box */
    Object getTipo();

    /** Devuelve el valor seleccionado en el segundo combo box */
    Object getValor();

    /** Pasa los posibles valores al segundo combo box */
    void setPosiblesValores(int[] valores);

    /** Pasa los posibles tipos al primer combo box */
    void setPosiblesTipos(String[] tipos);

}
