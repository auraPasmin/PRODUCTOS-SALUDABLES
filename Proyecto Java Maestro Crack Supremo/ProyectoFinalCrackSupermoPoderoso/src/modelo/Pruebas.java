package modelo;


import hilos.AutomaticFillerMP;
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
/*
        Materia_PrimaDAO m = new Materia_PrimaDAO();
        m.crearMP(new Materia_Prima());
*/        
        AutomaticFillerMP ATMP = new AutomaticFillerMP();
        Thread hiloATMP = new Thread(ATMP);
        hiloATMP.setDaemon(true);
        ATMP.start();
    }
}
