package modelo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Pruebas {
    public static void main(String[]args) throws NEDException{
        LocalDate t = LocalDate.of(2020, 11, 01);
        t.plusMonths(1);
        System.out.println(t.plusMonths(1));
        RecetaDAO v = new RecetaDAO();
        ProductoDAO p = new ProductoDAO();
        
        
        Object[][]data = v.generarReceta(p.cargarProducto("paparellena"));
        for(Object[] d1 : data){
            System.out.println(Arrays.toString(d1));
        }
    }
}
