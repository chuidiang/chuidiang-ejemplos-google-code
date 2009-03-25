package com.chuidiang.ejemplos.mvp;

import junit.framework.TestCase;

/**
 * Ejemplo de test de junit del Presenter en el ejemplo Modelo-Vista-Presenter.
 * 
 * No es importante el test en si mismo, ya que es bastante tonto e inutil. Lo
 * importante es fijarse en que:<br>
 * - La vista se ha cambiado por un MockVista, por lo que no tenemos que hacer
 * el test con una ventana Swing. Podemos hacer el MockVista a nuestro gusto.<br>
 * - No hay que hacer nada con el raton ni pulsar botones en la vista. Basta con
 * llamar a los metodos del Presenter que tratan esos eventos
 * 
 * @author Chuidiang
 * 
 */
public class TestPresenter extends TestCase {
    /** Un test tonto */
    public void testPares() {
        // Se instancian las clases y mocks objects.
        Modelo modelo = new Modelo();
        MockVista mockVista = new MockVista();

        // Se instancia la clase bajo test.
        Presenter presenter = new Presenter(modelo, mockVista);

        // Se simula una pulsacion de raton sore el primer combo, indicando al
        // mockvista que debe devolver y llamando al metodo del Presenter que
        // trataria dicha pulsacion
        mockVista.setTipoADevolver(Modelo.IMPARES);
        presenter.cambiadoTipo();

        // Se simula la seleccion de item en el segundo combo
        presenter.cambiadoValor();
    }

    /** Un mock del objeto vista, hecho a medida para nuestro test */
    class MockVista implements IfzVista {
        /** Que tipo queremos que este seleccionado en el primer combo */
        private String tipoADevolver = Modelo.PARES;

        /**
         * Para decirle que tipo queremos que este seleccionado en el primer
         * combo para el test
         * 
         * @param nuevoTipo
         *            Modelo.PARES o Modelo.IMPARES
         */
        public void setTipoADevolver(String nuevoTipo) {
            this.tipoADevolver = nuevoTipo;
        }

        /**
         * Devuelve el tipo que estaria seleccionado en el primer combo
         */
        @Override
        public Object getTipo() {
            return tipoADevolver;
        }

        /**
         * Devuelve el valor que estaria seleccionado en el segundo combo
         */
        @Override
        public Object getValor() {
            return 2;
        }

        /**
         * En este metodo aprovechamos para hacer los assert, para ver si el
         * Presenter nos llama con los valores esperados
         */
        @Override
        public void setPosiblesTipos(String[] tipos) {
            assertEquals(2, tipos.length);
            assertEquals(Modelo.IMPARES, tipos[0]);
            assertEquals(Modelo.PARES, tipos[1]);
        }

        /**
         * En este metodo aprovechamos para hacer los assert, para ver si el
         * Presenter nos llama con los valores esperados
         */
        @Override
        public void setPosiblesValores(int[] valores) {
            assertEquals(3, valores.length);
            if (Modelo.PARES.equals(tipoADevolver)) {
                assertEquals(2, valores[0]);
                assertEquals(4, valores[1]);
                assertEquals(6, valores[2]);
            } else {
                assertEquals(1, valores[0]);
                assertEquals(3, valores[1]);
                assertEquals(5, valores[2]);
            }
        }
    }
}
