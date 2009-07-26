package com.chuidiang.ejemplos.preparedstatement_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejemplos con PreparedStatement
 * 
 * @author Chuidiang
 * 
 */
public class EjemploPreparedStatement {

    public static void main(String[] args) {
        new EjemploPreparedStatement();
    }

    /** Conexion con la bd */
    private Connection conexion = null;

    /**
     * PreparedStatement con el select por id, que se va a usar varias veces y
     * por eso lo ponemos como atributo, para no crearlo localmente en el método
     * cada vez que se llama al mismo
     */
    private PreparedStatement psSelectConClave = null;

    /**
     * borra toda la tabla person e inserta mil registros con statement y con
     * PreparedStatement, para comparar los tiempos.
     */
    public EjemploPreparedStatement() {
        estableceConexion();
        insertaPreparedStatement();
        int clave = insertaPreparedStatementGetClave();
        selectPreparedStatement(clave);
        updatePreparedStatement(clave);
    }

    /**
     * Establece la conexión con la bd.
     */
    private void estableceConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Es necesario useServerPrepStmts=true para que los
            // PreparedStatement
            // se hagan en el servidor de bd. Si no lo ponemos, funciona todo
            // igual, pero los PreparedStatement se convertirán internamente a
            // Statements.
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hibernate?useServerPrepStmts=true",
                    "hibernate", "hibernate");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Inserta un registro
     */
    private void insertaPreparedStatement() {
        try {
            PreparedStatement ps = conexion
                    .prepareStatement("insert into person values (null,?,?,?)");
            ps.setInt(1, 22);
            ps.setString(2, "Juan");
            ps.setString(3, "Perez");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta un registro y devuelve la clave que se genera para dicho
     * registro.
     * 
     * @return La clave generada
     */
    private int insertaPreparedStatementGetClave() {
        int claveGenerada = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "insert into person values (null,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 22);
            ps.setString(2, "Juan");
            ps.setString(3, "Perez");
            ps.executeUpdate();

            // Se obtiene la clave generada
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                claveGenerada = rs.getInt(1);
                System.out.println("Clave generada = " + claveGenerada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claveGenerada;
    }

    /**
     * Crea si no lo esta ya un PreparedStatement con un select por clave y lo
     * ejecuta.
     * 
     * @param clave
     *            La clave del registro que se quiere consultar.
     */
    private void selectPreparedStatement(int clave) {
        try {
            if (null == psSelectConClave) {
                psSelectConClave = conexion
                        .prepareStatement("select * from person where person_id=?");
            }
            psSelectConClave.setInt(1, clave);
            ResultSet rs = psSelectConClave.executeQuery();
            while (rs.next()) {
                System.out.println("Clave=" + rs.getInt(1) + " Edad="
                        + rs.getInt(2) + " Nombre=" + rs.getString(3)
                        + " Apellido=" + rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifica el registro cuya clave se le pasa usando un PreparedStatement.
     * 
     * @param clave
     *            Clave del registro que se quiere modificar
     */
    private void updatePreparedStatement(int clave) {
        try {
            PreparedStatement ps = conexion
                    .prepareStatement("update person set firstname=? where person_id=?");
            ps.setString(1, "nuevo nombre");
            ps.setInt(2, clave);
            ps.executeUpdate();
            System.out.println("modificado");
            selectPreparedStatement(clave);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
