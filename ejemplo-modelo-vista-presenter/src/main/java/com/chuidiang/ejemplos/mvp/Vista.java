package com.chuidiang.ejemplos.mvp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Clase Vista del ejemplo Modelo-Vista-Presenter.<br>
 * 
 * Esta vista tiene dos JComboBox. El primero permite elegir las opciones
 * "pares" o "impares". Al seleccionar una de ellas, el segundo combo cambiara
 * sus items a valores 2,4,6 o a valores 1,3,5
 * 
 * Sin embargo, siguiendo el patron Modelo-Vista-Presenter, esta Vista no sabe
 * nada de nada, así que no pone ella los items en los combobox ni interactua
 * directamente con el modelo. Unicamente, cuando se selecciona un item en uno
 * de los combos, llama a un metodo del Presenter.
 * 
 * La vista instancia al Presenter y en las llamdas que hace a metodos del
 * Presenter es importante que no pase parametros. A cambio, debe tener metodos
 * get que permitan recoger informacion de la vista, es decir, metodos get que
 * permita saber que hay seleccionado en cada combo.
 * 
 * @author Chuidiang
 * 
 */
public class Vista extends JPanel implements IfzVista {
    /** Combo de tipo "pares" o "impares" */
    JComboBox comboTipo;

    /** Combo de valores 2,4,6 o bien 1,3,5 */
    JComboBox comboValores;

    /** La clase Presenter, que tiene la logica de todo esto */
    private Presenter presenter;

    /**
     * Pone los combo en el panel e instancia el Presenter
     * 
     * @param modelo
     *            Modelo que se pasara al Presenter al instanciarlo
     */
    public Vista(Modelo modelo) {
        setLayout(new FlowLayout());
        comboTipo = new JComboBox();
        add(comboTipo);
        comboValores = new JComboBox();
        add(comboValores);
        presenter = new Presenter(modelo, this);
        addListeners();
    }

    /**
     * Anade los listeners al combo, de forma que cuando el usuario seleccione
     * algo en uno de ellos, simplemente se llama a un metodo del Presenter, sin
     * pasarle nada como parametro.
     */
    private void addListeners() {
        comboTipo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.cambiadoTipo();
            }
        });

        comboValores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.cambiadoValor();
            }
        });
    }

    /**
     * Devuelve el tipo seleccionado en el primer combo
     */
    @Override
    public Object getTipo() {
        return comboTipo.getSelectedItem();
    }

    /**
     * Devuelve el valor seleccionado en el segundo combo
     */
    @Override
    public Object getValor() {
        return comboValores.getSelectedItem();
    }

    /**
     * Pone los valores que se le pasan como posibles items del segundo combo,
     * borrando los que hubiera previamente.
     */
    @Override
    public void setPosiblesValores(int[] valores) {
        comboValores.removeAllItems();
        for (int valor : valores)
            comboValores.addItem(valor);
    }

    /**
     * Pone los valores que se le pasan como posibles items del primer combo,
     * borrando los que hubiera previamente.
     */
    @Override
    public void setPosiblesTipos(String[] tipos) {
        comboTipo.removeAllItems();
        for (String tipo : tipos) {
            comboTipo.addItem(tipo);
        }

    }

}
