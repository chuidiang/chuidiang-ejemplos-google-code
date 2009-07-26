package com.chuidiang.ejemplos.preparedstatement_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ejemplo para comparar la eficiencia de Statement con PreparedStatement
 * 
 * @author Chuidiang
 * 
 */
public class RendimientoPreparedStatement {
    private static final int NUMERO_REGISTROS = 10000;

    public static void main(String[] args) {
        new RendimientoPreparedStatement();
    }

    /** Campos del registro */
    int edad;
    String nombre;
    String apellido;

    /** Conexion con la bd */
    private Connection conexion = null;
    private PreparedStatement ps;
    private Statement s;

    /**
     * borra toda la tabla person e inserta mil registros con statement y con
     * PreparedStatement, para comparar los tiempos.
     */
    public RendimientoPreparedStatement() {
        estableceConexion();
        // selectConStatement();
        // selectConPreparedStatement();
        borraTodo();
        insertaConStatement();
        borraTodo();
        insertaConPreparedStatement();
    }

    /**
     * Borra todos los registros de la tabla person
     */
    private void borraTodo() {
        Statement st;
        try {
            st = conexion.createStatement();
            st.executeUpdate("delete from person");
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Establece la conexión con la bd.
     */
    private void estableceConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hibernate?useServerPrepStmts=true",
                    "hibernate", "hibernate");
            conexion.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Inserta mil registros en la tabla person con PreparedStatement
     */
    private void insertaConPreparedStatement() {
        long tiempoInicial = System.currentTimeMillis();
        try {
            ps = conexion
                    .prepareStatement("insert into person values (null,?,?,?)");
            for (int i = 0; i < NUMERO_REGISTROS; i++) {
                ps.setInt(1, edad);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.execute();
            }
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("prepared statement "
                + (tiempoFinal - tiempoInicial));
    }

    /**
     * Inserta mil registros en la tabla person usando statement
     */
    private void insertaConStatement() {
        long tiempoInicial = System.currentTimeMillis();
        try {
            s = conexion.createStatement();
            for (int i = 0; i < NUMERO_REGISTROS; i++) {
                rellena(i);
                s.execute("insert into person values (null," + edad + ",'"
                        + nombre + "','" + apellido + "')");
            }
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("statement " + (tiempoFinal - tiempoInicial));

    }

    /** Rellena los campos */
    private void rellena(int i) {
        nombre = "nombre" + i;
        apellido = "apellido" + i;
        edad = i;
    }

    /**
     * Hace un select con un where por clave con un PreparedStatement
     */
    private void selectConPreparedStatement() {
        long tiempoInicial = System.currentTimeMillis();
        try {
            ps = conexion
                    .prepareStatement("select * from person where person_id=?");
            for (int i = 0; i < NUMERO_REGISTROS; i++) {
                ps.setInt(1, i);
                ps.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("prepared statement "
                + (tiempoFinal - tiempoInicial));
    }

    /** Hace un select con un where por clave con un Statement normal */
    private void selectConStatement() {
        long tiempoInicial = System.currentTimeMillis();
        try {
            s = conexion.createStatement();
            for (int i = 0; i < NUMERO_REGISTROS; i++) {
                s.execute("select * from person where person_id=" + i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("statement " + (tiempoFinal - tiempoInicial));

    }
}
