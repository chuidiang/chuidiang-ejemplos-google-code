package com.chuidiang.ejemplos.mazacote;

import java.util.Scanner;

/**
 * Ejemplo de clase mazacote, dificil de testear. Lo hace todo ella sola y las
 * entradas/salidas no son facilmente accesibles.
 * 
 * @author Chuidiang
 */
public class ClaseMazacote {

    /**
     * Se le pasa un texto a mostrar en pantalla y un scanner para leer la
     * entrada, devuelve un double recogido de dicha entrada. Desgraciadamente,
     * el metodo es private, por lo que no se puede testear.
     * 
     * @param scanner
     * @param textoPeticion
     * @return
     */
    private static double getSumando(Scanner scanner, String textoPeticion) {
        boolean correcto;
        correcto = false;
        double sumando2 = 0;
        while (!correcto) {
            try {
                System.out.println(textoPeticion);
                sumando2 = Double.parseDouble(scanner.nextLine());
                correcto = true;
            } catch (Exception e) {
                System.err.println("Eso no vale");
            }
        }
        return sumando2;
    }

    /**
     * Pide dos sumandos y muestra la suma
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sumando1 = getSumando(scanner, "Sumando 1:");
        double sumando2 = getSumando(scanner, "Sumando 2:");
        System.out.println("" + sumando1 + "+" + sumando2 + "="
                + (sumando1 + sumando2));
    }
}
