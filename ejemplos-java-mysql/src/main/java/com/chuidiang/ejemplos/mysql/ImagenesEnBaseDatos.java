package com.chuidiang.ejemplos.mysql;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Ejemplo de inserción y extración de una imagen jpg en una base de datos
 * mysql, usando un campo Blob.
 * 
 * @author Chuidiang
 */
public class ImagenesEnBaseDatos {

    /**
     * Instancia esta clase.
     */
    public static void main(String[] args) {
        new ImagenesEnBaseDatos();
    }

    /** Conexion con la base de datos */
    private Connection conexion = null;

    public ImagenesEnBaseDatos() {
        estableceConexion();
        insertaImagen();
        leeImagen();
    }

    /**
     * Lectura de la imagen de la base de datos.<br>
     * Se lee y se guarda en un fichero ./paisaje2.jpg, que debería ser igual al
     * original ./paisaje.jpg.
     */
    private void leeImagen() {
        try {
            // Se prepara la sentencia sql de consulta
            PreparedStatement st = conexion
                    .prepareStatement("select * from fotos");

            // Se hace la consulta
            ResultSet rs = st.executeQuery();

            // Bucle para leer resultados. Se lee el nombre de la imagen y
            // se guarda en un fichero de nombre "copia_<nombre_imagen>".
            while (rs.next()) {
                // Lectura del nombre
                String nombre = rs.getString(2);

                // Se prepara un Blob para la lectura de los bytes. Blob no
                // carga en memoria todos los bytes, sino que los va trayendo
                // según se le pidan.
                Blob bytesImagen = rs.getBlob(3);
                InputStream is = bytesImagen.getBinaryStream();

                // Fichero de salida
                FileOutputStream fw = new FileOutputStream("copia_" + nombre);

                // Bucle de lectura del blob y escritura en el fichero, de 1024
                // en 1024 bytes.
                byte bytes[] = new byte[1024];
                int leidos = is.read(bytes);
                while (leidos > 0) {
                    fw.write(bytes);
                    leidos = is.read(bytes);
                }
                fw.close();
                is.close();
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee una imagen de un fichero ./paisaje.jpg que se supone existe y lo
     * inserta en la base de datos. Dicha base de datos debe tener una tabla de
     * nombre fotos con la siguiente estructura:<br>
     * mysql> describe fotos;<br>
     * | Field | Type | Null | Key | Default | Extra |<br>
     * | id | int(11) | NO | PRI | NULL | auto_increment |<br>
     * | nombre | text | YES | | NULL | |<br>
     * | imagen | blob | YES | | NULL | |<br>
     * El campo de tipo blob admite hasta 65535 bytes.
     */
    private void insertaImagen() {
        try {
            // Apertura del fichero
            FileInputStream is = new FileInputStream("./paisaje.jpg");

            // Preparando el insert
            PreparedStatement st = conexion
                    .prepareStatement("insert into fotos values(null,?,?)");

            // Se ponen los valores a insertar
            st.setString(1, "paisaje.jpg");
            // El InputStream de la imagen se pasa como blob
            st.setBlob(2, is);

            // Ejecucion y cierre
            st.execute();
            is.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece la conexion en la base de datos, suponiendo que es mysql, esta
     * en localhost, tiene una base de datos "hibernate", con usuario
     * "hibernate" y password "hibernate".<br>
     * Deja la conexión abierta en el atributo privado conexion.
     */
    private void estableceConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hibernate", "hibernate",
                    "hibernate");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
