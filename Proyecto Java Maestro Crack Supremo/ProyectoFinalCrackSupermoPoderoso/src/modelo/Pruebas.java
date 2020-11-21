package modelo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Pruebas {
    public static void main(String[]args){
        LocalDate t = LocalDate.of(2020, 11, 20);
        VendedorDAO v = new VendedorDAO();
        try {
            System.out.println(v.encontrarUbicacion(456412, t).trim());
            
        } catch (NEDException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
