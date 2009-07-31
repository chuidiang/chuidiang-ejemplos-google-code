package com.chuidiang.ejemplos.databasemetadata_mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.chuidiang.ejemplos.mysql.Conexion;

/**
 * Se hace una consulta a base de datos y se usa ResultSetMetaData para saber el
 * nombre y tipo de cada una de las columnas del ResultSet obtenido en la
 * consulta.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploResultSetMetaData {

    /**
     * Ejemplo de ResultSetMetaData
     * 
     * @param args
     */
    public static void main(String[] args) {
        new EjemploResultSetMetaData();
    }

    /** Conexion con la base de datos */
    private Connection conexion = null;

    /**
     * Establece la conexion, realiza una consulta y muestra el nombre y tipo de
     * cada una de las columnas del ResultSet obtenido en la consulta.
     */
    public EjemploResultSetMetaData() {
        estableConexion();
        muestraMetaDatosConsulta();
    }

    /** Estable la conexion con la base de datos */
    private void estableConexion() {
        conexion = Conexion.getConection();
    }

    /**
     * realiza una consulta y muestra el nombre y tipo de cada una de las
     * columnas del ResultSet obtenido en la consulta.
     */
    private void muestraMetaDatosConsulta() {
        if (null == conexion) {
            return;
        }

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select * from person");
            ResultSetMetaData rsmd = rs.getMetaData();

            int numeroColumnas = rsmd.getColumnCount();

            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.println("catalogo=" + rsmd.getCatalogName(i) + "."
                        + rsmd.getColumnName(i) + " --> "
                        + rsmd.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
