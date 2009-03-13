package com.chuidiang.ejemplos.option_pane_dialog_modal;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 * Dialogo modal que sirve de ventana de captura de datos.<br>
 * Contiene un JTextField en el que escribimos un texto y pulsando enter después
 * de escribir en el, la ventana se cierra.
 * 
 * @author Chuidiang
 * 
 */
public class DialogoModal extends JDialog {
    private JTextField textField;

    /**
     * Constructor que pone titulo al dialogo, construye la ventana y la hace
     * modal.
     * 
     * @param padre
     *            Frame que hace de padre de esta dialogo.
     */
    public DialogoModal(Frame padre) {

        // padre y modal
        super(padre, true);
        setTitle("Mete un dato");
        textField = new JTextField(20);
        getContentPane().add(textField);

        // Se oculta la ventana al pulsar <enter> sobre el textfield
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
    }

    /**
     * Deveulve el texto en el jtextfield
     * 
     * @return el texto
     */
    public String getText() {
        return textField.getText();
    }
}
