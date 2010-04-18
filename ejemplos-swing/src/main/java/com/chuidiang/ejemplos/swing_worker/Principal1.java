package com.chuidiang.ejemplos.swing_worker;

import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Ejemplo de SwingWorker. Una tarea que cuenta de 1 a 10 con esperas de un
 * segundo y al final marca en una etiqueta que esta hecho.<br>
 * Se trata de comprobar que la tarea se ejecuta en un hilo separado y la
 * actualizacion de la etiqueta (JLabel) en el hilo de despacho de eventos.
 * 
 * @author Chuidiang
 */
public class Principal1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Principal1();
    }

    /**
     * Abre una ventana con una etiqueta y lanza un SwingWorker.
     */
    public Principal1() {
        // La venana principal con una etiqueta que pondrá "hecho".
        JFrame ventanaPrincipal = new JFrame();
        JLabel etiqueta = new JLabel("sin hacer");
        ventanaPrincipal.getContentPane().add(etiqueta);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.pack();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        // Se lanza el SwingWorker
        Worker1 worker = new Worker1(etiqueta);
        worker.execute();

        try {
            // Se recoge el resultado del SwingWorker. Esta
            // llamada se queda bloqueada hasta que termine
            // de trabajar el SwingWorker.
            System.out.println("El resultado es " + worker.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
