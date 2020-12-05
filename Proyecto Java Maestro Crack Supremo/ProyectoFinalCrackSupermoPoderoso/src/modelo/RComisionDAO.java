

package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import servicios.Fachada;


public class RComisionDAO {

    public RComisionDAO() {
    }
    /**
     * <strong>Crea</strong> una comision para un vendedor
     * @param comision, una comision a ser registrada en la DB.
     * @return 1 si se creo el registro exitosamente, 0
     * si no sucedio el registro a la DB
     * @throws modelo.NEDException
     * @see Class RComision
     */
    public int createComision(RComision comision) throws NEDException {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO comision VALUES (?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setInt(1, comision.getVendedor());
            instruccion.setDate(2 , Date.valueOf(comision.getFecha()));

            resultado = instruccion.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("aca me petie!");
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
                System.out.println("jeje no cerro! xd");
            }
        }
        return resultado;
    }

     /**
      * <strong>Lee</strong> los comisiones de la DB
      * @return <code>ArrayList<RComision></code> , una lista de las
      * comsiones en la DB
      * @see Class RComisionDAO
      */
    public ArrayList<RComision> readComision() {
        Connection conexion = null;
        PreparedStatement instruccion =null;
        ResultSet busqueda = null;
        ArrayList<RComision> listarComisiones = null;
        String sqlStatement;

        try {
            listarComisiones = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM comision ORDER BY fecha";
            instruccion = conexion.prepareStatement(sqlStatement);
            busqueda = instruccion.executeQuery();

            while(busqueda.next()) {
                RComision comision = new RComision();
                comision.setVendedor(busqueda.getInt(1));
                comision.setFecha(busqueda.getDate(2).toLocalDate() );
                listarComisiones.add(comision);
            }
        }
        catch (SQLException e) {
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
            catch (SQLException ex) {
                // Do something ...
            }
        }
        return listarComisiones;
    }

    /**
     * <strong>Actualiza</strong> la informacion de una comision
     * en la DB
     * @param comsion, una comsion a ser actualizada tomando como
     * referencia el vendedor que lo efectuo
     * @return 1 si se realizaron los cambios con exito, 0 si
     * no se realizo una actualizacion
     * @see Class RComisionDAO
     *//*
    public int updateRecibo(RComision comision) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE comision SET = ? " +
                    "WHERE V = ? AND C = ? AND P = ? AND Fecha = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(3, recibo.getC());
            instruccion.setString(4, recibo.getP());
            instruccion.setTimestamp(5, java.sql.Timestamp.valueOf(recibo.getFecha()));
            instruccion.setInt(1, recibo.getCantidad());
            instruccion.setInt(2, recibo.getV());
            System.out.println(instruccion);
            resultado = instruccion.executeUpdate();
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
        return resultado;
    }*/

    /**
     * <strong>Borra</strong> una comision de la DB
     * @param vendedor, comision a ser eliminada utilizando como referencia
     * el vendedor del recibo.
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class RComisionDAO
     */
    public int deleteComision(int vendedor) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;

        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM comision WHERE vendedor = ?";
            instruccion = conexion.prepareStatement(sqlstatement);

            instruccion.setInt(1, vendedor);
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
    
    public Object[][] generarRecibo(int vendedor){    
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String data = "";
        String sqlStatement;
        Object[][]d = null;
        try {
            conexion = Fachada.startConnection();
            sqlStatement =  "SELECT vendedor, fecha "
                + "FROM comision "
                + "WHERE vendedor = ? " ;
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setInt(1,vendedor);
            System.out.println(instruccion);
            resultado = instruccion.executeQuery();
            if(resultado.next()){
                resultado.last();
                int r = resultado.getRow();
                d = new Object[r][2];
                int i = 0;
                resultado.beforeFirst();
                while(resultado.next()) {
                    d[i][0] = resultado.getInt(1);
                    d[i][1] = resultado.getDate(2);
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
    /*
    public int crearRecibo(int cedula, String NIT, LocalDateTime fecha, ArrayList<String> prod, ArrayList<Integer> cant) throws NEDException{
        ArrayList<Producto> venta = new ArrayList<>();
        Producto pi;
        for(int i = 0 ; i < prod.size() ; ++i){
            ProductoDAO p= new ProductoDAO();
            pi = p.cargarProducto(prod.get(i));
            System.out.println(pi.toString());
            if(pi.getCantidad() < cant.get(i))
                throw new NEDException(301, prod.get(i));
            else{
                pi.setCantidad(pi.getCantidad() - cant.get(i));
                venta.add(pi);
            }
        }
        System.out.println("salimos de verificar los productos xd");
        for(int i = 0 ; i < prod.size() ; ++i){
            ProductoDAO p= new ProductoDAO();
            Recibo r = new Recibo(cedula, NIT, prod.get(i), fecha, cant.get(i));
            //createRecibo(r);
            System.out.println(p.updateProducto(venta.get(i)));
        }
        return 1;
    }*/
}
