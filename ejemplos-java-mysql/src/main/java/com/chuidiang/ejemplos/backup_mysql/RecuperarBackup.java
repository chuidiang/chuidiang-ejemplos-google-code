package com.chuidiang.ejemplos.backup_mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Ejemplo de como recuperar un backup de mysql desde java.
 * 
 * @author Chuidiang
 */
public class RecuperarBackup {
    /**
     * Instancia esta clase.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new RecuperarBackup();
    }

    /**
     * Llama a mysql y le redirige a su entrada el fichero de backup obtenido
     * con mysqldump.
     */
    public RecuperarBackup() {
        Process p;
        try {
            // Ejecucion del cliente mysql
            p = Runtime
                    .getRuntime()
                    .exec(
                            "c:/aplicaciones/wamp/mysql/bin/mysql -u hibernate -phibernate hibernate");

            // Lectura de la salida de error y se muestra por pantalla.
            InputStream es = p.getErrorStream();
            muestraSalidaDeError(es);

            // Lectura del fichero de backup y redireccion a la entrada estandar
            // de mysql.
            OutputStream os = p.getOutputStream();
            FileInputStream fis = new FileInputStream("e:/backup.sql");

            byte buffer[] = new byte[1024];
            int leido = fis.read(buffer);
            while (leido > 0) {
                System.out.println(leido);
                os.write(buffer, 0, leido);
                leido = fis.read(buffer);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Se saca por pantalla la salida de errores del comando, por si acaso.
     * 
     * @param es
     *            El InputStream de donde leer los errores del comando mysql.
     */
    private void muestraSalidaDeError(final InputStream es) {
        Thread hiloError = new Thread() {
            public void run() {
                try {
                    byte[] buffer = new byte[1024];
                    int leido = es.read(buffer);
                    while (leido > 0) {
                        System.out.println(new String(buffer, 0, leido));
                        leido = es.read(buffer);
                    }
                    es.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        hiloError.start();
    }
}
