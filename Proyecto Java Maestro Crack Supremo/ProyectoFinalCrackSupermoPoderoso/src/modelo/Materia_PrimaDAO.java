package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import servicios.Fachada;

public class Materia_PrimaDAO {
        public Materia_PrimaDAO() {
            // Nothing here
        }

    /**
     * <strong>Crea</strong> y registra una materia prima la DB
     * @param materia_prima, materia prima a registrar en la DB
     * @return 1 si se creo exitosamente, 0 si no se realizaron
     * cambios
     * @see Class Materia_PrimaDAO
     */
    public int createMateria_Prima(Materia_Prima materia_prima) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int result = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "INSERT INTO materiaprima VALUES (?, ?, ?, ?, ?)";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setString(1,materia_prima.getNombre());
            instruccion.setInt(2, materia_prima.getCantidad());
            instruccion.setDate(3, Date.valueOf(materia_prima.getFechaCaducidad()));
            instruccion.setString(4, materia_prima.getNit_proveedor());
            instruccion.setInt(5, materia_prima.getValor_unitario());
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
     * <strong>Lee</strong> de la DB las recetas
     * @return <code>ArrayList<Materia_Prima></code> una lista
     * de las recetas.
     * @see Class Materia_PrimaDAO
     */
    public ArrayList<Materia_Prima> readMateriaPrima() {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ArrayList<Materia_Prima> listarMateria = null;
        ResultSet resultado = null;
        String sqlStatement;

        try {
            listarMateria = new ArrayList<>();
            conexion = Fachada.startConnection();
            sqlStatement = "SELECT * FROM materiaprima ORDER BY nombre";
            instruccion = conexion.prepareStatement(sqlStatement);
            resultado = instruccion.executeQuery();

            while(resultado.next()) {
                Materia_Prima mp = new Materia_Prima();

                mp.setNombre(resultado.getString(1));
                mp.setCantidad(resultado.getInt(2));
                mp.setFechaCaducidad(resultado.getDate(3).toLocalDate());
                mp.setNit_proveedor(resultado.getString(4));
                mp.setValor_unitario(resultado.getInt(5));
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
        public Materia_Prima cargarMateriaPrima(String nombre) throws NEDException {
            Connection conexion = null;
            PreparedStatement instruccion = null;
            ResultSet resultado = null;
            String sqlStatement;
            Materia_Prima mt = null;
            try {
                conexion = Fachada.startConnection();
                System.out.println(conexion.isClosed());

                sqlStatement = "SELECT * FROM materiaprima WHERE nombre = ?";
                instruccion = conexion.prepareStatement(sqlStatement);
                instruccion.setString(1, nombre);
                resultado = instruccion.executeQuery();

                if(resultado.next()) {
                    mt = new Materia_Prima();
                    mt.setNombre(resultado.getString(1));
                    mt.setCantidad(resultado.getInt(2));
                    mt.setFechaCaducidad(resultado.getDate(3).toLocalDate());
                    mt.setNit_proveedor(resultado.getString(4));
                    mt.setValor_unitario(resultado.getInt(5));
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
        
        return mt;
    }

    /**
     * <strong>Actualiza</strong> una materia prima de la DB
     * @param mt, una materia prima a ser cambiada
     * @return 1 si se realizo la actualizacion, 0 si
     * no se realizo ningun cambio
     * @see Class Materia_PrimaDAO
     */
    public int updateMateriaPrima(Materia_Prima mt) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE materiaprima SET cantidad = ?, fechaCaducidad = ?, NIT_proveedor = ?, valorUnitario = ? WHERE nombre = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1,mt.getCantidad());
            instruccion.setDate(2, Date.valueOf(mt.getFechaCaducidad()));
            instruccion.setString(3, mt.getNit_proveedor());
            instruccion.setInt(4, mt.getValor_unitario());
            instruccion.setString(5, mt.getNombre());

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
                // Do something ...
            }
        }
        return resultado;
    }

    /**
     * <strong>Borra</strong> una materia prima
     * registrado en la DB
     * @param nombre, el nombre de la materia prima a borrar
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class Materia_PrimaDAO
     */
    public int deleteMateriaPrima(String nombre) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlstatement;
        try {
            conexion = Fachada.startConnection();
            sqlstatement = "DELETE FROM materiaprima WHERE nombre = ?";
            instruccion = conexion.prepareStatement(sqlstatement);

            instruccion.setString(1, nombre);
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
    
    public void crearMP(Materia_Prima MP){
        ArrayList<Materia_Prima> mp = null;
        Connection conexion = null;
        PreparedStatement listar = null;
        ResultSet resultadoListar = null;
        String sqlStatement2;

            mp =  readMateriaPrima();
            System.out.println(mp);
            int size = mp.size();
            
            for(int i=0; i<size; i++){
                if(mp.get(i).getNombre().equals(MP.getNombre())){
                    MP.setCantidad(mp.get(i).getCantidad() + MP.getCantidad());
                    this.updateMateriaPrima(MP);
                }
            }
        //return mp;
    }
    
}
