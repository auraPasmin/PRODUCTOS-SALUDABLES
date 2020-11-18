package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[]args){
        VendedorDAO r = new VendedorDAO();
        System.out.println(r.cargarVendedor(1006037732));
        
        
    }
}
