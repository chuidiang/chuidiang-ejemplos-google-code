package com.chuidiang.ejemplos.mvp;

import javax.swing.JFrame;

/**
 * Clase principal del ejemplo de modelo-vista-presenter.<br>
 * Instancia la vista y el modelo, además de meter la vista en un JFrame y
 * mostrarlo en pantalla.
 * 
 * La vista serán dos JComboBox. El primero permite elegir valores pares o
 * impares. El segundo cambiará al seleccionar pares o impares en el primero. Si
 * se seleccionan pares, el segundo combo box mostrará 2,4,6. Si se seleccionan
 * impares, mostrará 1,3,5. La Vista sera "tonta", es decir, no tendra ningun
 * tipo de inteligencia ni sera la encargada de cambiar los items del segundo
 * combo box ni hara nada cuando se seleccione un valor en ninguno de ellos,
 * salvo llamar a un metodo del Presenter.
 * 
 * El Modelo simplemente sabra que valores deben poder elegirse segun se elijan
 * pares o impares.
 * 
 * El Presenter es la clase encargada de relaccionar la Vista y el Modelo.
 * Tambien es la encargada de recoger/meter datos en el modelo y de decir a la
 * vista que debe mostrar.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploModeloVistaPresenter {

    /**
     * Crea modelo, vista y visualiza una ventana principal con todo.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Instanciacion de modelo y vista.
        Modelo modelo = new Modelo();
        Vista vista = new Vista(modelo);

        // Ventana principal y mostrarlo todo
        JFrame v = new JFrame("Ejemplo Modelo-Vista-Presenter");
        v.getContentPane().add(vista);
        v.pack();
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }

}
