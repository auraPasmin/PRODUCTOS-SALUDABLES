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
     * create
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
     * read
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
                v.setTelefono(resultado.getInt(4));
                v.setEmail(resultado.getString(5));
                v.setComision(resultado.getDouble(6));
                v.setSexo(resultado.getString(7));

                listarVendedor.add(v);
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
        return listarVendedor;
    }

    /**
     * updat
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
