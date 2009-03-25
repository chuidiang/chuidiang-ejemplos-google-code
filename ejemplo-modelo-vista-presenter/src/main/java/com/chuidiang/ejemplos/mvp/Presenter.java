package com.chuidiang.ejemplos.mvp;

/**
 * Clase Presenter del ejemplo Modelo-Vista-Presenter.<br>
 * Esta clase recibe la vista y el modelo. Es la encargada de relacionarlas,
 * actuar sobre el modelo, pedirle datos, decirle a la Vista que debe mostrar y
 * recoger los eventos producidos sobre la vista.
 * 
 * @author Chuidiang
 * 
 */
public class Presenter {
    /** El modelo */
    private Modelo modelo;

    /** La vista */
    private IfzVista vista;

    /**
     * Variable temporal que indica si en la vista se estan cambiando los items
     * en los combo box, para ignorar los eventos que se produzcan.
     */
    private boolean cambiandoTipo = false;

    /**
     * Guarda modelo y vista.<br>
     * Inicializa la vista pasandole los items que debe mostrar en cada combo
     * 
     * @param modelo
     *            El modelo
     * @param vista
     *            La vista
     */
    public Presenter(Modelo modelo, IfzVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Pasa a la vista los items del primer combo.
        vista.setPosiblesTipos(new String[] { Modelo.IMPARES, Modelo.PARES });

        // Se llama al metodo de cambiadoTipo() para poner los items del segundo
        // combo en funcion de que haya quedado seleccionado en el primer combo.
        cambiadoTipo();
    }

    /**
     * Cambia los items del segundo combo, segun el tipo que se haya
     * seleccionado en el primer combo.<br>
     * La vista llamara a este metodo cuando se cambie la seleccion del primer
     * combo
     */
    public void cambiadoTipo() {
        // Se marca que se estan haciendo cambios, para ignorar los eventos
        // que saltaran al ir haciendolos.
        cambiandoTipo = true;

        // Se recoge el tipo seleccionado en la vista
        String tipo = (String) vista.getTipo();

        // Se obtienen los posibles valores para ese tipo
        int[] valores = modelo.getPosiblesValores(tipo);

        // Se pasan los posibles valores a la vista.
        if (null != valores)
            vista.setPosiblesValores(valores);

        // Fin de los cambios.
        cambiandoTipo = false;
    }

    /**
     * Muestra en pantalla el valor elegido en el segundo combo.<br>
     * La vista llamara a este metodo cuando se seleccione algo en el segundo
     * combo.
     */
    public void cambiadoValor() {
        // No hacer nada si el evento se ha producido por estar cambiando los
        // items de valores por haber cambiado los tipos.
        if (cambiandoTipo)
            return;
        System.out.println("Se ha elegido valor " + vista.getValor());
    }
}
