package com.chuidiang.ejemplos.swing_worker;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * Ejemplo de SwingWoker.<br>
 * El tipo de resultado a devolver es un Double. No hay barra de progreso.
 * 
 * @author Chuidiang
 */
public class Worker1 extends SwingWorker<Double, Void> {
    /** JLabel en el que se marcara que ha terminado el trabajo */
    private final JLabel etiqueta;

    /**
     * Se le pasa el JLabel en el que se quiere que se ponga la palabra "Hecho"
     * cuando el SwingWoker haya terminado.
     * 
     * @param unaEtiqueta
     */
    public Worker1(JLabel unaEtiqueta) {
        etiqueta = unaEtiqueta;
    }

    /**
     * Tarea que tarda mucho y que queremos que se ejecute en un hilo separado.
     */
    @Override
    protected Double doInBackground() throws Exception {
        System.out.println("doInBackground() esta en el hilo "
                + Thread.currentThread().getName());

        // Un simbre bucle hasta 10, con esperas de un segundo entre medias.
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }
        }
        // El supuesto resultado de la operación.
        return 100.0;
    }

    /**
     * Cuando termine la tarea, SwingWorker llamará a este metodo en el hilo de
     * despacho de eventos. Aqui es donde debemos actualizar el JLabel.
     */
    @Override
    protected void done() {
        System.out.println("done() esta en el hilo "
                + Thread.currentThread().getName());
        etiqueta.setText("hecho");
    }
}
