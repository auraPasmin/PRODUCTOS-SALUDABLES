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
        LocalDate t = LocalDate.of(2020, 11, 01);
        t.p
        ReciboDAO v = new ReciboDAO();
        System.out.println(v.generarRecibo(456412, "125489", t));
    }
}
