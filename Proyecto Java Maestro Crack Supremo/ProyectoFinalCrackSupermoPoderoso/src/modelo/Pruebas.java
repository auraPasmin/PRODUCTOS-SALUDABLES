package modelo;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Pruebas {
    public static void main(String[]args){
        ProductoDAO r = new ProductoDAO();
        try {
            throw new NEDException(701,"lala");
        } catch (NEDException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
}
