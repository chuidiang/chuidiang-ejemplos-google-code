package com.chuidiang.ejemplos.databasemetadata_mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chuidiang.ejemplos.mysql.Conexion;

/**
 * Conexion a una base de datos y uso de DataBaseMetaData para ver que tablas
 * tiene esa base de datos y de que tipos son sus columnas.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploDataBaseMetaData {

    /**
     * Ejemplo de DataBaseMetaDAta
     * 
     * @param args
     */
    public static void main(String[] args) {
        new EjemploDataBaseMetaData();
    }

    /** Conexion con la base de datos */
    private Connection conexion = null;

    /**
     * Establece la conexion y muestra las tablas y columnas de cada tabla
     */
    public EjemploDataBaseMetaData() {
        estableConexion();
        muestraTablas();
    }

    /** Estable la conexion con la base de datos */
    private void estableConexion() {
        conexion = Conexion.getConection();
    }

    /** Muetras las columnas de unta tabla de un catalogo dado */
    private void muestraColumnas(String catalogo, String tabla) {
        DatabaseMetaData metaDatos;
        try {
            metaDatos = conexion.getMetaData();
            ResultSet rs = metaDatos.getColumns(catalogo, null, tabla, null);
            while (rs.next()) {
                // El contenido de cada columna del ResultSet se puede ver en
                // la API de java, en el metodo getColumns() de DataBaseMetaData
                // La 4 corresponde al TABLE_NAME
                // y la 6 al TYPE_NAME
                String nombreColumna = rs.getString(4);
                String tipoColumna = rs.getString(6);
                System.out.println("   COLUMNA, nombre=" + nombreColumna
                        + " tipo = " + tipoColumna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra las tablas de base de datos de la conexion actual y las columnas
     * de cada una de las tablas.
     */
    private void muestraTablas() {
        if (null == conexion) {
            return;
        }

        try {
            DatabaseMetaData metaDatos = conexion.getMetaData();
            ResultSet rs = metaDatos.getTables(null, null, "%", null);
            while (rs.next()) {
                // El contenido de cada columna del ResultSet se puede ver
                // en la API, en el metodo getTables() de DataBaseMetaData.
                // La columna 1 es TABLE_CAT
                // y la 3 es TABLE_NAME
                String catalogo = rs.getString(1);
                String tabla = rs.getString(3);
                System.out.println("TABLA=" + catalogo + "." + tabla);

                muestraColumnas(catalogo, tabla);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
