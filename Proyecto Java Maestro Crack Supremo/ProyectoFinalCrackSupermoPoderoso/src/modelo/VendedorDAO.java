package modelo;
import servicios.Fachada;

import java.sql.*;
import java.util.ArrayList;
import modelo.Vendedor;

public class VendedorDAO {
    public VendedorDAO() {
        // Nothing here
    }

    /**
     * Crea una <strong>vendedor</strong> para la distribucion
     * de los productos.
     * @param vendedor, el vendedor a agregar a la DB
     * @return Si se creo exitosamente devuelve 1, 0 si no realizo cambios.
     * @see Class VendedorDAO
     */
    public int createVendedor(Vendedor vendedor) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int result = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO vendedores VALUES (?, ?, ? ,?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1,vendedor.getCedula());
            instruccion.setString(2, vendedor.getNombre());
            instruccion.setString(3, vendedor.getCargo());
            instruccion.setDouble(4, vendedor.getComision());
            instruccion.setInt(5, vendedor.getTelefono());
            instruccion.setString(6, vendedor.getEmail());
            instruccion.setString(7, vendedor.getSexo());

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
     * <strong>Lee</strong> de la DB los vendedores
     * @return <code>ArrayList<Vendedor></code> , una
     * lista de los vendedores
     * @see Class VendedoresDAO
     */
    public ArrayList<Vendedor> readVendedores() {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ArrayList<Vendedor> listarVendedor = null;
        ResultSet resultado = null;
        String sqlStatement;

        try {
            listarVendedor = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM vendedores ORDER BY cedula";
            instruccion = conexion.prepareStatement(sqlStatement);
            resultado = instruccion.executeQuery();

            while(resultado.next()) {
                Vendedor v = new Vendedor();
                v.setCedula(resultado.getInt(1));
                v.setNombre(resultado.getString(2));
                v.setCargo(resultado.getString(3));
                v.setComision(resultado.getDouble(4));
                v.setTelefono(resultado.getInt(5));
                v.setEmail(resultado.getString(6));
                
                v.setSexo(resultado.getString(7));

                listarVendedor.add(v);
            }
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
                // Do something
            }
        }
        return listarVendedor;
    }
    
    public Vendedor cargarVendedor(int cedula) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String sqlStatement;
        Vendedor v = null;
        try {
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM vendedores WHERE cedula = ?";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setInt(1,cedula);
            resultado = instruccion.executeQuery();

            if(resultado.next()) {
                v = new Vendedor();
                v.setCedula(resultado.getInt(1));
                v.setNombre(resultado.getString(2));
                v.setCargo(resultado.getString(3));
                v.setComision(resultado.getDouble(4));
                v.setTelefono(resultado.getInt(5));
                v.setEmail(resultado.getString(6));

            }
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
                // Do something
            }
        }
        return v;
    }

    /**
     * <strong>Actualiza</strong> los valores de un registro del
     * vendedor en la DB.
     * @param vendedor, valores nuevos del vendedor a actualizar
     * @return Si se realizo el cambio con exito devuelve 1, 0 si
     * no se realizo nada.
     * @see Class VendedorDAO
     */
    public int updateVendedor(Vendedor vendedor) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE vendedores SET nombre = ?, cargo = ?, comision = ?" +
                    ", telefono = ?, correo = ?, sexo = ? WHERE cedula = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1,vendedor.getNombre());
            instruccion.setString(2,vendedor.getCargo());
            instruccion.setDouble(3, vendedor.getComision());
            instruccion.setInt(4, vendedor.getTelefono());
            instruccion.setString(5, vendedor.getEmail());
            instruccion.setString(6, vendedor.getSexo());
            instruccion.setInt(7, vendedor.getCedula());

            resultado = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getErrorCode());
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
     * <strong>Elimina</strong> un vendedor de la DB.
     * @param cedula, identificacion del vendedor a eliminar
     * @return Si se elimino con exito devuelve 1, 0 en caso
     * contrario.
     * @see Class VendedorDAO
     */
    public int deleteVendedor(int cedula) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;
        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM vendedores WHERE cedula = ?";
            instruccion = conexion.prepareStatement(sqlstatement);

            instruccion.setInt(1, cedula);
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