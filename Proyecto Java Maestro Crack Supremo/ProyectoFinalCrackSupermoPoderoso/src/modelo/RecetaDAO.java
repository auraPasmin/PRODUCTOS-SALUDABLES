package modelo;
import servicios.Fachada;

import java.sql.*;
import java.util.ArrayList;
import modelo.Receta;

public class RecetaDAO {
    public RecetaDAO() {
        // Nothing here
    }

    /**
     * create
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
     * read
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
     * updat
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
     * delete
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
}
