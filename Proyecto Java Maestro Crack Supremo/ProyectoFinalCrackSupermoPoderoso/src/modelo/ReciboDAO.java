package modelo;
import java.sql.*;
import java.util.ArrayList;
import servicios.Fachada;

public class ReciboDAO {
    public ReciboDAO() {
        // nothing here
    }

    /**
     * <strong>Crea</strong> un recibo de una venta
     * @param recibo, un recibo a ser registrado en la DB.
     * @return 1 si se creo el registro exitosamente, 0
     * si no sucedio el registro a la DB
     * @see Class ReciboDAO
     */
    public int createRecibo(Recibo recibo) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO recibo VALUES (?, ?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1, recibo.getV());
            instruccion.setString(2, recibo.getC());
            instruccion.setString(3, recibo.getP());
            instruccion.setTimestamp(4, java.sql.Timestamp.valueOf(recibo.getFecha()));
            instruccion.setInt(5, recibo.getCantidad());

            resultado = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null)
                    conexion.close();
            }
            catch(SQLException ex) {
                System.out.println("jeje no cerro xd");
            }
        }
        return resultado;
    }

     /**
      * <strong>Lee</strong> los recibos de la DB
      * @return <code>ArrayList<Recibo></code> , una lista de los
      * recibos en la DB
      * @see Class ReciboDAO
      */
    public ArrayList<Recibo> readRecibo() {
        Connection conexion = null;
        PreparedStatement instruccion =null;
        ResultSet busqueda = null;
        ArrayList<Recibo> listarRecibos = null;
        String sqlStatement;

        try {
            listarRecibos = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM recibo ORDER BY V";
            instruccion = conexion.prepareStatement(sqlStatement);
            busqueda = instruccion.executeQuery();

            while(busqueda.next()) {
                Recibo recibo = new Recibo();
                recibo.setV(busqueda.getInt(1));
                recibo.setC(busqueda.getString(2));
                recibo.setP(busqueda.getString(3));
                recibo.setFecha(busqueda.getTimestamp(4).toLocalDateTime());
                recibo.setCantidad(busqueda.getInt(5));
                listarRecibos.add(recibo);
            }
        }
        catch (SQLException e) {
            // Do something
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null)
                    conexion.close();
            }
            catch (SQLException ex) {
                // Do something ...
            }
        }
        return listarRecibos;
    }

    /**
     * <strong>Actualiza</strong> la informacion de un recibo
     * en la DB
     * @param recibo, un recibo a ser actualizado tomando como
     * referencia de cambio el vendedor que lo efectuo
     * @return 1 si se realizaron los cambios con exito, 0 si
     * no se realizo una actualizacion
     * @see Class ReciboDAO
     */
    public int updateRecibo(Recibo recibo) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE recibo C = ?, P = ?, Fecha = ?, Cantidad = ?" +
                    "WHERE V = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1, recibo.getC());
            instruccion.setString(2, recibo.getP());
            instruccion.setTimestamp(3, java.sql.Timestamp.valueOf(recibo.getFecha()));
            instruccion.setInt(4, recibo.getCantidad());
            instruccion.setInt(5, recibo.getV());

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
            catch (SQLException ex) {
                // Do something ...
            }
        }
        return resultado;
    }

    /**
     * <strong>Borra</strong> un recibo de la DB
     * @param v, recibo a ser eliminado utilizando como referencia
     * el vendedor del recibo.
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class ReciboDAO
     */
    public int deleteRecibo(int v) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;

        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM recibo WHERE V = ?";
            instruccion = conexion.prepareStatement(sqlstatement);

            instruccion.setInt(1, v);
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
            catch (SQLException ex) {
                // Do something
            }
        }
        return resultado;
    }
}