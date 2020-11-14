package modelo;
import java.sql.*;
import java.util.ArrayList;
import servicios.Fachada;

public class ReciboDAO {
    public ReciboDAO() {
        // nothing here
    }

    /**
     * create
     */
    public int createRecibo(Recibo recibo) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConection();
            sqlStatement = "INSERT INTO recibo VALUES (?, ?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1, recibo.getV());
            instruccion.setString(2, recibo.getC());
            instruccion.setString(3, recibo.getP());
            // Forma 1 para la fecha del recibo
            // instruccion.setObject(4, recibo.getFecha());
            // Forma 2:
            // instruccion.setDate(4, java.sql.Date.valueOf(recibo.getFecha().toString()));
            instruccion.setTimestamp(4, java.sql.Timestamp.valueOf(recibo.getFecha()));
            instruccion.setInt(5, recibo.getCantidad());

            resultado = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            // Do something ...
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null)
                    conexion.close();
            }
            catch(SQLException ex) {
                // Do something ...
            }
        }
        return resultado;
    }

     /**
      * read
      */
    public ArrayList<Recibo> readRecibo() {
        Connection conexion = null;
        PreparedStatement instruccion =null;
        ResultSet busqueda = null;
        ArrayList<Recibo> listarRecibos = null;

//        try {
//
//        }
        return null;
    }

    /**
     * update
     */

    /**
     * delete
     */
}