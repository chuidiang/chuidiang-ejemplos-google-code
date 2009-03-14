package com.chuidiang.ejemplos.option_pane_dialog_modal;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Ventana con botones para mostrar todos los posibles tipos de JOptionPane mas
 * un JDialog modal y recoger datos de ellos, que se mostraran en un JTextField.
 * 
 * @author Chuidiang
 * 
 */
public class EjemplosJOptionPane {

    /** El JTextField donde se mostraran los datos */
    private JTextField textField;

    /** Un dialogo modal para pedir datos */
    private DialogoModal dialogoModal;

    /**
     * Instancia esta clase.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new EjemplosJOptionPane();
    }

    /**
     * Construye y visualiza la ventana.
     */
    public EjemplosJOptionPane() {
        // Ventana principal.
        JFrame v = new JFrame();

        // botones para las posibles ventanas de entrada de datos.
        JButton botonMessageDialog = new JButton("Message dialog");
        JButton botonConfirmDialog = new JButton("Confirm dialog");
        JButton botonOptionDialog = new JButton("Option dialog");
        JButton botonInputDialog = new JButton("Input dialog");
        JButton botonDialogoModal = new JButton("Dialogo modal");

        // Se contruye la ventana principal.
        v.setLayout(new FlowLayout());
        v.getContentPane().add(botonMessageDialog);
        v.getContentPane().add(botonConfirmDialog);
        v.getContentPane().add(botonOptionDialog);
        v.getContentPane().add(botonInputDialog);
        v.getContentPane().add(botonDialogoModal);
        textField = new JTextField(25);
        v.getContentPane().add(textField);

        // Se visualiza la ventana.
        v.pack();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addListenersALosBotones(botonMessageDialog, botonConfirmDialog,
                botonOptionDialog, botonInputDialog, botonDialogoModal);
    }

    /**
     * Anade a cada boton un listener que muestra la ventana correspondiente
     * para pedir los datos.
     * 
     * @param botonMessageDialog
     * @param botonConfirmDialog
     * @param botonOptionDialog
     * @param botonInputDialog
     * @param botonDialogoModal
     */
    private void addListenersALosBotones(JButton botonMessageDialog,
            JButton botonConfirmDialog, JButton botonOptionDialog,
            JButton botonInputDialog, JButton botonDialogoModal) {
        botonMessageDialog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                muestraMessageDialog(evento);
            }
        });
        botonConfirmDialog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                muestraConfirmDialog(evento);

            }
        });
        botonOptionDialog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                muestraOptionDialog(evento);

            }
        });
        botonInputDialog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                muestraInputDialog(evento);
            }
        });

        botonDialogoModal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                muestraDialogoModal(evento);
            }
        });
    }

    /**
     * Muestra el dialogo modal cuando se pulsa el boton del dialogo modal.
     * 
     * @param evento
     */
    protected void muestraDialogoModal(ActionEvent evento) {

        // El padre del JDialog modal debe ser el JFrame principal de la
        // aplicacion,
        // pero hacemos aquí este código un poco más general, para ver cómo se
        // puede obtener la ventana padre a partir de un ActionEvent.
        // Se deberia contemplar tambien la posiblidad de que el Window obtenido
        // sea un JDialgo.
        Component componente = (Component) evento.getSource();
        if (null == dialogoModal) {
            Window ventanaPadre;
            if (componente instanceof Window)
                ventanaPadre = (Window) componente;
            else
                ventanaPadre = SwingUtilities.getWindowAncestor(componente);

            // No se deberia hacer el cast directamente a Frame. Deberia
            // comprobarse
            // antes con instanceof si ventanaPadre el Frame o Dialog, o incluso
            // null
            dialogoModal = new DialogoModal((Frame) ventanaPadre);
        }

        // Se muestra el dialogo.
        dialogoModal.pack();
        dialogoModal.setLocationRelativeTo(componente);
        dialogoModal.setVisible(true);
        // Al ser modal, la llamada a setVisible(true) se queda bloqueada hasta
        // que el dialogo modal se oculte. Por ello, a continuación tenemos
        // la seguridad de que el texto ya esta disponible.
        textField.setText(dialogoModal.getText());
    }

    /**
     * Muestra un input dialog y muestra el texto recogido de el en el
     * jtextfield
     * 
     * @param evento
     */
    protected void muestraInputDialog(ActionEvent evento) {
        String seleccion = JOptionPane.showInputDialog((Component) evento
                .getSource(), "Input dialog", JOptionPane.QUESTION_MESSAGE);
        textField.setText(seleccion);

    }

    /**
     * Muestra un option dialog con varios botones de opciones. Muestra en el
     * jtextfield la opcion elegida.
     * 
     * @param evento
     */
    protected void muestraOptionDialog(ActionEvent evento) {
        Object seleccion = JOptionPane.showInputDialog((Component) evento
                .getSource(), "Seleccione opcion", "Selector de opciones",
                JOptionPane.QUESTION_MESSAGE, null, new Object[] { "opcion 1",
                        "opcion 2", "opcion 3" }, "opcion 1");
        textField.setText("seleccionada opcion " + seleccion);
    }

    /**
     * Muestra un dialog de confirmacion, mostrando en el jtextfield si se ha
     * confirmado o no.
     * 
     * @param evento
     */
    protected void muestraConfirmDialog(ActionEvent evento) {
        int confirmado = JOptionPane.showConfirmDialog((Component) evento
                .getSource(), "¿Lo confirmas?");
        if (JOptionPane.OK_OPTION == confirmado)
            textField.setText("confirmado");
        else
            textField.setText("NO");
    }

    /**
     * Muestra un mensaje, avisando cuando el usuario cierra la ventana de
     * mensaje
     * 
     * @param evento
     */
    protected void muestraMessageDialog(ActionEvent evento) {
        JOptionPane.showMessageDialog((Component) evento.getSource(),
                "Un aviso puñetero");
        textField.setText("ya estas avisado");
    }
}
