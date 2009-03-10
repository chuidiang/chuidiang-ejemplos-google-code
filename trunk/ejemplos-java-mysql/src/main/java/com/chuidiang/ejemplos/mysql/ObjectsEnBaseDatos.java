package com.chuidiang.ejemplos.mysql;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JButton;

/**
 * Guarda un objeto DatoGrande en base de datos y luego lo lee.
 * 
 * @author Chuidiang
 */
public class ObjectsEnBaseDatos {

    /**
     * Instancia esta clase.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new ObjectsEnBaseDatos();
    }

    /** Conexion con la base de datos */
    private Connection conexion;

    /** Un dato serializable */
    private DatoGrande datoGrande = null;

    /** Establece conexion, guarda un DatoGrande en base de datos y luego lo lee */
    public ObjectsEnBaseDatos() {
        conexion = Conexion.getConection();
        // Crea y rellena datos en un DatoGrande, que usara el metodo de
        // insert.
        rellenaDatoGrande();
        insertaDato();
        leeDato();
        Conexion.cierraConexion(conexion);
    }

    /**
     * Lee de base de datos un DatoGrande y saca su contenido por pantalla.
     */
    private void leeDato() {
        try {
            // La consulta y bucle para recorrer resultados
            PreparedStatement ps = conexion
                    .prepareStatement("select * from objetos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                // Se obtiene el campo blob
                Blob blob = rs.getBlob("objeto");

                // Se reconstruye el objeto con un ObjectInputStream
                ObjectInputStream ois = new ObjectInputStream(blob
                        .getBinaryStream());
                DatoGrande dato = (DatoGrande) ois.readObject();

                // Se saca por pantalla
                System.out.println(dato.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta un DatoGrande en la base de datos.
     */
    private void insertaDato() {
        try {
            // El insert
            PreparedStatement ps = conexion
                    .prepareStatement("insert into objetos values (null, ?)");

            // Se obtiene el array de bytes de la clase DatoGrande
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArray);
            oos.writeObject(datoGrande);

            // Se inserta en bd
            ps.setBytes(1, byteArray.toByteArray());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea y rellena un DatoGrande.
     */
    private void rellenaDatoGrande() {
        datoGrande = new DatoGrande();
        Dato dato = new Dato();
        dato.setCadena("la cadena");
        dato.setFecha(new Date());
        dato.setValor(33);
        datoGrande.setDato(dato);
        datoGrande.setUnBoton(new JButton("El botón"));
        datoGrande.setValor(11.22);
    }
}
