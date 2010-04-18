package com.chuidiang.ejemplos.swing_worker;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Ejemplo de SwingWorker.<br>
 * El resultado de la tarea es de tipo Double. Usaremos Integer para actualizar
 * la barra de progreso.
 * 
 * @author Chuidiang
 * 
 */
public class Worker2 extends SwingWorker<Double, Integer> {
    // Etiqueta que pondra "Hecho" cuando termine el SwingWorker.
    private final JLabel etiqueta;
    // Barra de progreso que vamos a ir incrementando.
    private final JProgressBar progreso;

    /**
     * Le pasamos la etiqueta que tiene que poner "Hecho" cuando termine la
     * tarea y el barra de progreso que queremos que se vaya a actualizar.
     * 
     * @param unaEtiqueta
     * @param unaBarraProgreso
     */
    public Worker2(JLabel unaEtiqueta, JProgressBar unaBarraProgreso) {
        etiqueta = unaEtiqueta;
        progreso = unaBarraProgreso;
    }

    /**
     * Tarea que tarda mucho y se ejecutara en un hilo separado.
     */
    @Override
    protected Double doInBackground() throws Exception {
        System.out.println("doInBackground() esta en el hilo "
                + Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }

            // Se pasa valor para la barra de progreso. ESto llamara al metodo
            // process() en el hilo de despacho de eventos.
            publish(i + 1);
        }

        // Supuesto resultado de la tarea que tarda mucho.
        return 100.0;
    }

    /**
     * Tarea terminada, SwingWorker llama a este metodo en el hilo de despacho
     * de eventos. Aqui actualizamos la etiqueta para que ponga "Hecho".
     */
    @Override
    protected void done() {
        System.out.println("done() esta en el hilo "
                + Thread.currentThread().getName());
        etiqueta.setText("hecho");
    }

    /**
     * SwingWorker llama a este metodo en el hilo de despacho de eventos cuando
     * llamamos a publish() y le pasa los mismos parametros que nosotros
     * pongamos en publish().<br>
     * En este ejemplo, nosotros pasamos el valor de la barra de progreso.
     */
    @Override
    protected void process(List<Integer> chunks) {
        System.out.println("process() esta en el hilo "
                + Thread.currentThread().getName());
        progreso.setValue(chunks.get(0));
    }
}
