package com.chuidiang.ejemplos.swing_worker;

import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Ejemplo de SwingWorker con una barra de progreso.<br>
 * El SwingWorker contará hasta 10 con esperas de un segundo en cada iteración y
 * actualizará una barra de progreso.
 * 
 * @author Chuidiang
 * 
 */
public class Principal2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Principal2();
    }

    /**
     * Muestra una ventana con una etiqueta, un dialogo con un progress bar y
     * lanza un SwingWorker. Cierra la ventana de progreso cuando termina el
     * worker.
     */
    public Principal2() {
        // La ventana principal, con una etiqueta que pondrá "Hecho" cuando
        // termine el SwingWorker.
        JFrame ventanaPrincipal = new JFrame();
        JLabel etiqueta = new JLabel("Sin hacer");
        ventanaPrincipal.getContentPane().add(etiqueta);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.pack();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        // Una ventana con la barra de progreso
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        JDialog dialogoProgreso = new JDialog(ventanaPrincipal, "progreso");
        dialogoProgreso.getContentPane().add(barraProgreso);
        dialogoProgreso.pack();
        dialogoProgreso.setLocationRelativeTo(null);

        // Hacemos visible la barra de progreso y lanzamos
        // el SwingWorker.
        dialogoProgreso.setVisible(true);
        Worker2 worker = new Worker2(etiqueta, barraProgreso);
        worker.execute();
        // w.execute() devuelve el control inmediatamente, por lo que
        // no debemos ocultar la barra de progreso aqui

        // Mostramos el resultado y ocultamos la barra de progreso.
        try {
            // Esta llamada se queda bloqueada hasta que termine
            // el SwingWorker. Debemos ocultar la barra de progreso
            // inmediatamente después.
            System.out.println("El resultado es " + worker.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        dialogoProgreso.setVisible(false);
    }
}
