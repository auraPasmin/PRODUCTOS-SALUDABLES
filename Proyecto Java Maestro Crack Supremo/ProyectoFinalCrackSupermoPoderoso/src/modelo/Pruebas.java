package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pruebas {
    public static void main(String[]args){
        ProductoDAO r = new ProductoDAO();
        try {
            System.out.println(r.cargarProducto("Coca cola"));
        } catch (NEDException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
