package modelo;
import servicios.Fachada;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RecetaDAO {
    public RecetaDAO() {
        // Nothing here
    }

    /**
     * <strong>Crea</strong> una receta para el producto
     * @param receta, receta a registrar en la DB
     * @return 1 si se creo exitosamente, 0 si no se realizaron
     * cambios
     * @see Class RecetaDAO
     */
    public int createReceta(Receta receta) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int result = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO receta VALUES (?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1,receta.getP());
            instruccion.setString(2, receta.getM());
            instruccion.setInt(3, receta.getCantidad());

            result = instruccion.executeUpdate();
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
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }

    /**
     * <strong>Lee</strong> de la DB las recetas
     * @return <code>ArrayList<Receta></code> una lista
     * de las recetas.
     * @see Class recetaDAO
     */
    public ArrayList<Receta> readRecetas() {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ArrayList<Receta> listarReceta = null;
        ResultSet resultado = null;
        String sqlStatement;

        try {
            listarReceta = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM receta ORDER BY P";
            instruccion = conexion.prepareStatement(sqlStatement);
            resultado = instruccion.executeQuery();

            while(resultado.next()) {
                Receta receta = new Receta();

                receta.setP(resultado.getString(1));
                receta.setM(resultado.getString(2));
                receta.setCantidad(resultado.getInt(3));

                listarReceta.add(receta);
            }
        }
        catch(SQLException e) {
            // Do something
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null)
                    conexion.close();
            }
            catch(SQLException ex) {
                // Do something
            }
        }
        return listarReceta;
    }

    /**
     * <strong>Actualiza</strong> una receta de la DB
     * @param receta, una receta a ser cambiada
     * @return 1 si se realizo la actualizacion, 0 si
     * no se realizo ningun cambio
     * @see Class RecetaDAO
     */
    public int updateReceta(Receta receta) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE receta M = ?, Cantidad = ? WHERE P = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1,receta.getM());
            instruccion.setInt(2, receta.getCantidad());
            instruccion.setString(3, receta.getP());

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
     * <strong>Borra</strong> una receta de un producto
     * registrado en la DB
     * @param p, la receta del producto a ser eliminada
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class RecetaDAO
     */
    public int deleteReceta(String p) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;
        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM receta WHERE P = ?";
            instruccion = conexion.prepareStatement(sqlstatement);

            instruccion.setString(1, p);
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
    
    public String generarRecibo(int cedula, String NIT, LocalDate day){
        
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String data = "";
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement =  "SELECT R.P, R.Cantidad, P.Precio "
                + "FROM recibo R JOIN producto P ON R.P=P.Nombre "
                + "WHERE V= ? AND C= ? AND( Fecha BETWEEN ? AND ? )";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1,cedula);
            instruccion.setString(2, NIT);
            instruccion.setDate(3, Date.valueOf(day));
            instruccion.setDate(4, Date.valueOf(day.plusDays(1)));

            instruccion.executeQuery();
            
            while(resultado.next()) {
                data += resultado.getString(1) + ":" + resultado.getInt(2) + ":" + resultado.getInt(3) + "\n";
            }
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
        return data;

    }
}
