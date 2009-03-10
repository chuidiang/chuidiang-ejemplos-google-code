package com.chuidiang.ejemplos.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para obtener la conexión a la base de datos.
 * 
 * @author Chuidiang
 */
public class Conexion {

    /**
     * Establece y devuelve la conexión con la base de datos.
     * 
     * @return null si hay problemas.
     */
    public static Connection getConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/hibernate", "hibernate",
                    "hibernate");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cierra la conexión que se le pasa.
     * 
     * @param conexion
     */
    public static void cierraConexion(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
