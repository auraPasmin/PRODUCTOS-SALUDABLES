package modelo;
import java.sql.*;
import java.util.ArrayList;
import servicios.Fachada;


public class ClienteDAO {
    String url, name, pass;
    public ClienteDAO() {
        // Nothing here
    }

    /**
     * Guarda un cliente
     * @param cliente, un objeto de tipo Cliente
     * @return numero de cambios realizados
     * @see Class ClienteDAO
     */
    public int createCliente(Cliente cliente) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;
        
        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setString(1, cliente.getNIT());
            instruccion.setString(2, cliente.getNombre());     
            instruccion.setString(3, cliente.getDireccion());
            instruccion.setDouble(4, cliente.getX());
            instruccion.setDouble(5, cliente.getY());

            resultado = instruccion.executeUpdate();
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
            catch (SQLException ex) {
                System.out.println("xdddddddd");
            }
        }
        return resultado;
    }

    /**
     * Lista todos los clientes
     * @return ArrayList, una lista de clientes
     * @see Class clienteDAO
     */
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultConsulta = null;
        String statementSql;

        try {
            conexion = Fachada.startConnection();
            statementSql = "SELECT * FROM cliente ORDER BY NIT";
            instruccion = conexion.prepareStatement(statementSql);
            resultConsulta = instruccion.executeQuery();

            Cliente cliente = new Cliente();
            while(resultConsulta.next()) {
                cliente.setNIT(resultConsulta.getString(1));
                cliente.setNombre(resultConsulta.getString(2));
                cliente.setDireccion(resultConsulta.getString(3));
                cliente.setX(resultConsulta.getDouble(4));
                cliente.setY(resultConsulta.getDouble(5));

                listaCliente.add(cliente);
            }
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
            catch(SQLException ex) {
                // Do something ...
            }
        }
        return listaCliente;
    }

    // Metodos Opcionales
    // -------------------------------------------------------------
    /**
     * Realiza la busqueda de un cliente por su <strong>NIT</strong>
     * @param nit un identificador del cliente
     * @return Un objeto <code>Cliente</code> de la busqueda
     * @throws NEDException
     * @see Class ClienteDAO
     */
    public Cliente buscarNIT(String nit) throws NEDException {
        Cliente cliente = null;
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet busqueda = null;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM cliente WHERE NIT = ?";
            instruccion = conexion.prepareStatement(sqlStatement);
            instruccion.setString(1, nit);
            busqueda = instruccion.executeQuery();

            if(busqueda.next()) {
                cliente = new Cliente();
                cliente.setNIT(busqueda.getString(1));
                cliente.setNombre(busqueda.getString(2));
                cliente.setDireccion(busqueda.getString(3));
                cliente.setX(busqueda.getDouble(4));
                cliente.setY(busqueda.getDouble(5));
            }else{
                throw new NEDException(100, nit);
            }
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
            catch(SQLException ex) {
                // Do something ...
            }
        }
        return cliente;
    } 
    // ---------------------------------------------------------
    // End of metodos opcionales

    /**
     * Modifica un cliente
     * @param cliente
     * @return numero de cambios realizados
     * @see Class ClienteDAO
     */
    public int modificarCliente(Cliente cliente) {
        Connection conexion = null;
        PreparedStatement instrucciones = null;
        int resultado = 0;
        String sqlStatement;
        
        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE cliente SET nombre = ?, direccion = ?" +
                              ", X = ?, Y = ? WHERE NIT = ?";
            instrucciones = conexion.prepareStatement(sqlStatement);

            instrucciones.setString(1, cliente.getNombre());
            instrucciones.setString(2, cliente.getDireccion());
            instrucciones.setDouble(3, cliente.getX());
            instrucciones.setDouble(4, cliente.getY());
            instrucciones.setString(5, cliente.getNIT());

            resultado = instrucciones.executeUpdate();
        }
        catch(SQLException e) {
            // Do something ...
        }
        finally {
            try {
                if(instrucciones != null)
                    instrucciones.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch(SQLException ex) {
                // Do something ...
            }
        }
        return resultado;
    }

    /**
     * Borra un cliente
     * @param nit, numero del identidad del cliente
     * @return numero de cambio realizado, 1 cambio realizado,
     * 0 ningun cambio realizado, -1 error al realizar la operacion
     * @see Class ClienteDAO
     */
    public int borrarCliente(String nit) {
        Connection conexion = null;
        PreparedStatement instrucciones = null;
        int resultado = 0;
        String sqlStatement;

        try{
            conexion = Fachada.startConnection();
            sqlStatement = "DELETE FROM cliente WHERE NIT = ?";
            instrucciones = conexion.prepareStatement(sqlStatement);

            instrucciones.setString(1, nit);
            resultado = instrucciones.executeUpdate();
        }
        catch(SQLException e) {
            // Do something ...
        }
        finally {
            try {
                if(instrucciones != null)
                    instrucciones.close();
                if(conexion != null){
                    conexion.close();
                    Fachada.closeConnection();
                }
            }
            catch(SQLException ex) {
                // Do something ...
            }
        }
        return resultado;
    }

}
