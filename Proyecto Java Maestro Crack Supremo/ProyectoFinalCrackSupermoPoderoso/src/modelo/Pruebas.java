package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[]args){
        ProductoDAO r = new ProductoDAO();
        System.out.println(r.cargarProducto("Empanada"));
        
        
    }
}
