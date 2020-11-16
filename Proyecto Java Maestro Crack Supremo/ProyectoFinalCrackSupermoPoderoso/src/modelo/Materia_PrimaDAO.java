package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * @see Class RecetaDAO
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
                if(conexion != null)
                    conexion.close();
            }
            catch(SQLException ex) {
                // Do something
            }
        }
        return listarMateria;
    }

    /**
     * <strong>Actualiza</strong> una materia prima de la DB
     * @param mt, una receta a ser cambiada
     * @return 1 si se realizo la actualizacion, 0 si
     * no se realizo ningun cambio
     * @see Class RecetaDAO
     */
    public int updateMateriaPrima(Materia_Prima mt) {
        Connection conexion = null;
        PreparedStatement instruccion = null;
        int resultado = 0;
        String sqlStatement;

        try {
            conexion = Fachada.startConnection();
            sqlStatement = "UPDATE materiaprima cantidad = ?, fechaCaducidad = ?, NIT_proveedor = ?, valorUnitario = ? WHERE nombre = ?";
            instruccion = conexion.prepareStatement(sqlStatement);

            instruccion.setInt(1,mt.getCantidad());
            instruccion.setDate(2, Date.valueOf(mt.getFechaCaducidad()));
            instruccion.setString(3, mt.getNit_proveedor());
            instruccion.setInt(4, mt.getValor_unitario());
            instruccion.setString(5, mt.getNombre());

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
     * <strong>Borra</strong> una materia prima
     * registrado en la DB
     * @param nombre, el nombre de la materia prima a borrar
     * @return 1 si se realizo la eliminacion correctamente,
     * 0 si no se realizo nada
     * @see Class RecetaDAO
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
