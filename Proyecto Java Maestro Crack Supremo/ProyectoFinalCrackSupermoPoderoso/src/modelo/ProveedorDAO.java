package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import servicios.Fachada;

public class ProveedorDAO {
            public ProveedorDAO() {
            // Nothing here
        }

    /**
     * <strong>Crea</strong> y registra un proveedor en la DB
     * @param p, proveedor a registrar en la DB
     * @return 1 si se creo exitosamente, 0 si no se realizaron
     * cambios
     * @see Class ProveedorDAO
     */
    public int createProveedor(Proveedor p) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int result = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO proveedor VALUES (?, ?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1,p.getNit());
            instruccion.setString(2, p.getNombre());
            instruccion.setString(3, p.getUbicacion());
            instruccion.setInt(4, p.getTelefono());
            instruccion.setString(5, p.getEmail());
            result = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }

    /**
     * <strong>Lee</strong> de la DB los proveedores
     * @return <code>ArrayList<Receta></code> una lista
     * de las recetas.
     * @see Class ProveedorDAO
     */
    public ArrayList<Proveedor> readProveedor() {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ArrayList<Proveedor> listarMateria = null;
        ResultSet resultado = null;
        String sqlStatement;

        try {
            listarMateria = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM proveedor ORDER BY nit";
            instruccion = conexion.prepareStatement(sqlStatement);
            resultado = instruccion.executeQuery();

            while(resultado.next()) {
                Proveedor mp = new Proveedor();

                mp.setNit(resultado.getString(1));
                mp.setNombre(resultado.getString(2));
                mp.setUbicacion(resultado.getString(3));
                mp.setTelefono(resultado.getInt(4));
                mp.setEmail(resultado.getString(5));
                listarMateria.add(mp);
            }
        }
        catch(SQLException e) {
            // Do something
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch(SQLException ex) {
                // Do something
            }
        }
        return listarMateria;
    }

    /**
     * <strong>Actualiza</strong> un proveedor de la DB
     * @param p, un proveedor a ser cambiado
     * @return 1 si se realizo la actualizacion, 0 si
     * no se realizo ningun cambio
     * @see Class ProveedorDAO
     */
    public int updateProveedor(Proveedor p) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE proveedor SET nombre = ?, ubicacion = ?, telefono = ?, email = ? WHERE nit = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(5,p.getNit());
            instruccion.setString(1, p.getNombre());
            instruccion.setString(2, p.getUbicacion());
            instruccion.setInt(3, p.getTelefono());
            instruccion.setString(4, p.getEmail());

            resultado = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            // Do something ...
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch (SQLException ex) {
                // Do something ...
            }
        }
        return resultado;
    }

    /**
     * <strong>Borra</strong> una materia prima
     * registrado en la DB
     * @param nit, el nit del proveedor a borrar
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class RecetaDAO
     */
    public int deleteMateriaPrima(String nit) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;
        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM proveedor WHERE nit = ?";
            instruccion = conexion.prepareStatement(sqlstatement);

            instruccion.setString(1, nit);
            resultado = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            // Do something ...
        }
        finally {
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch (SQLException ex) {
                // Do something
            }
        }
        return resultado;
    }
}
