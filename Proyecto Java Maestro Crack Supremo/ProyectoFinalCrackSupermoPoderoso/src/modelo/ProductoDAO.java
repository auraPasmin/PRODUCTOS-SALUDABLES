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


public class ProductoDAO {
    
    public ProductoDAO(){
    }
    
    /**
     * <strong>Crea</strong> un producto
     * @param producto, producto a registrar en la DB
     * @return 1 si se creo exitosamente, 0 si no se registro
     * cambiado
     * @see Class ProductoDAO
     */
    public int createProducto(Producto producto) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int result = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO producto VALUES (?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1, producto.getNombre());
            instruccion.setInt(2, producto.getCantidad());
            instruccion.setDouble(3, producto.getPrecio());
            instruccion.setDate(4 , java.sql.Date.valueOf( producto.getFechaCaducidad()));

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
     * <strong>Lee</strong> de la DB los productos
     * @return <code>ArrayList<Producto></code> una lista
     * de las productos.
     * @see Class productoDAO
     */
    public ArrayList<Producto> readProducto() {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ArrayList<Producto> listarProducto = null;
        ResultSet resultado = null;
        String sqlStatement;

        try {
            listarProducto = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM producto";
            instruccion = conexion.prepareStatement(sqlStatement);
            resultado = instruccion.executeQuery();

            while(resultado.next()) {
                Producto prod = new Producto();

                prod.setNombre(resultado.getString(1));
                prod.setCantidad(resultado.getInt(2));
                prod.setPrecio(resultado.getDouble(3));
                prod.setFechaCaducidad(resultado.getDate(4).toLocalDate());

                listarProducto.add(prod);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
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
        return listarProducto;
    }
    
    public Producto cargarProducto(String nombre) throws NEDException {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String sqlStatement;
        Producto prod = null;
        try {
            conexion = Fachada.startConnection();
            System.out.println(conexion.isClosed());
            
            sqlStatement = "SELECT * FROM producto WHERE nombre = ?";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setString(1, nombre);
            resultado = instruccion.executeQuery();
            
            if(resultado.next()) {
                prod = new Producto();

                prod.setNombre(resultado.getString(1));
                prod.setCantidad(resultado.getInt(2));
                prod.setPrecio(resultado.getDouble(3));
                prod.setFechaCaducidad(resultado.getDate(4).toLocalDate());
                
            }else{
                throw new NEDException(300,nombre);
                
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
            try {
                if(instruccion != null)
                    instruccion.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }          
            }
            catch(SQLException ex) {
            }
        
        return  prod;
    }

    /**
     * <strong>Actualiza</strong> un producto de la DB
     * @param producto, un producto a ser cambiado
     * @return 1 si se recibo la actualizacion, 0 si
     * no se realizo ningun cambio
     * @see Class ProductoDAO
     */
    public int updateProducto(Producto producto) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE producto SET Cantidad = ?, precio = ?, FechaCaducidad = ? WHERE nombre = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1, producto.getCantidad());
            instruccion.setDouble(2, producto.getPrecio());
            instruccion.setDate(3 , java.sql.Date.valueOf( producto.getFechaCaducidad()));
            instruccion.setString(4, producto.getNombre());

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
    }

    /**
     * <strong>Borra</strong> un producto
     * registrado en la DB
     * @param p, el producto a ser eliminado
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class ProductoDAO
     */
    public int deleteProducto(String p) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;
        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM producto WHERE nombre = ?";
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
    public int reabastecerProducto(String nombreP, int cant) throws NEDException{
        ArrayList<Materia_Prima> mps = new ArrayList<>();
        Materia_Prima m;
        Producto pi = cargarProducto(nombreP);
        RecetaDAO r = new RecetaDAO();
        Object[][]recetas = r.generarReceta(pi);
        for(int i = 0 ; i < recetas.length ; ++i){
            Materia_PrimaDAO mp= new Materia_PrimaDAO();
            m = mp.cargarMateriaPrima((String) recetas[i][0]);
            int necesario = (int)recetas[i][1];
            if(m.getCantidad() < cant*necesario)
                throw new NEDException(201, m.getNombre());
            else{
                m.setCantidad(m.getCantidad() - (cant*necesario));
                mps.add(m);
            }
        }
        pi.setCantidad(pi.getCantidad() + cant);
        updateProducto(pi);
        System.out.println("salimos de verificar la materia prima xd");
        for(int i = 0 ; i < recetas.length ; ++i){
            Materia_PrimaDAO mp= new Materia_PrimaDAO();
            System.out.println(mp.updateMateriaPrima(mps.get(i)));
        }
        return 1;
    }
    
    public String[][] mostrarStock(){
        String[][]stock = null;
        ArrayList<Producto> productos = this.readProducto();
        
        for(int i=0; i<productos.size();i++){
            stock[i][0] = productos.get(i).getNombre();
            stock[i][1] = ""+productos.get(i).getCantidad() ;
        }
        
        return stock;
    }
}
