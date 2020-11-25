package modelo;
import servicios.Fachada;

import java.sql.*;
import java.time.LocalDate;
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
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch(SQLException ex) {
                // Do something
            }
        }
        return listarVendedor;
    }
    
    public Vendedor cargarVendedor(int cedula) throws NEDException {
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
            }else{
                throw new NEDException(700,cedula+"");
            }
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
    
    public String encontrarUbicacion(int cedula, LocalDate day) throws NEDException{
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String sqlStatement;
        Vendedor v = null;
        String res = "";
        try {
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT DISTINCT c.x, C.y, R.Fecha "
                    + "FROM recibo R JOIN Cliente C ON R.C=C.NIT "
                    + "WHERE V= ? AND (Fecha BETWEEN ? AND ?)ORDER BY R.Fecha";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setInt(1,cedula);
            instruccion.setDate(2, Date.valueOf(day));
            instruccion.setDate(3, Date.valueOf(day.plusDays(1)));
            resultado = instruccion.executeQuery();
            while(resultado.next()) {
                res+= resultado.getDouble(1) + ":" + resultado.getDouble(2) +"\n";
            }
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
            catch(SQLException ex) {
                // Do something
            }
        }
        return res.trim();
    }
    
    public Object[][] generarRecibos(Vendedor v){    
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String sqlStatement;
        Object[][]d = null;
        try {
            conexion = Fachada.startConnection();
            sqlStatement =  "SELECT DISTINCT C, Fecha FROM recibo WHERE V = ?";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setInt(1, v.getCedula());
            System.out.println(instruccion);
            resultado = instruccion.executeQuery();
            if(resultado.next()){
                resultado.last();
                int r = resultado.getRow();
                d = new Object[r][2];
                int i = 0;
                resultado.beforeFirst();
                while(resultado.next()) {
                    d[i][0] = resultado.getString(1);
                    d[i][1] = resultado.getString(2);
                    ++i;
                }
            }
            
        }
        catch(SQLException e) {
            System.out.println(e.toString());
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
        return d;

    }
    
    public double evaluarComision(Vendedor v, LocalDate t){
        
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        double comision = 0;
        String sqlStatement; 
        try {
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT COUNT(DISTINCT V,C) FROM `recibo` WHERE Fecha BETWEEN ? AND ? and V = ?";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setDate(1, Date.valueOf(t));
            instruccion.setDate(2, Date.valueOf(t.plusMonths(1)));
            instruccion.setInt(3, v.getCedula());
            System.out.println(instruccion);
            resultado = instruccion.executeQuery();
            if(resultado.next()) {
                comision = v.getComision()*resultado.getInt(1);
            }
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
            catch(SQLException ex) {
                // Do something
            }
        }
        return comision;
    }
}
