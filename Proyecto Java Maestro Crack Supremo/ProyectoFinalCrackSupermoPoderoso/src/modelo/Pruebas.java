package modelo;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Pruebas {
    public static void main(String[]args){
        ReciboDAO r = new ReciboDAO();
        ArrayList<String> p = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();
        
        
        p.add("pastelDePollo");
        c.add(20);
        p.add("dedo");
        c.add(20);
        p.add("hojaldra");
        c.add(30);
        try {
            r.crearRecibo(456412, "456412", LocalDateTime.now(), p, c);
        } catch (NEDException ex) {
            System.out.println(ex.toString());
        }
        
    }
}
