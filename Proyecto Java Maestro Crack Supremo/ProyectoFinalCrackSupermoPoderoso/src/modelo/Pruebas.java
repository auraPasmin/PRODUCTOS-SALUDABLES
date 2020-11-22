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

        ProductoDAO p = new ProductoDAO();
        System.out.println(p.reabastecerProducto("paparellena", 3));
    }
}
