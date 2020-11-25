
package hilos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import modelo.Materia_Prima;
import modelo.Materia_PrimaDAO;
import modelo.NEDException;
import servicios.Fachada;


public class AutomaticFillerMP extends Thread{
        Connection conexion = null;
        PreparedStatement instruccion = null;
        ResultSet resultado = null;
        String sqlStatement;

    String[] nombres = {"papa", "carne", "arroz","harina-trigo", "pollo","queso", "masa-hojaldre"};
    int[] cantidades = {250, 300, 400, 450, 500, 550, 600};
    //int[] valores = {300, 400, 450, 600, 700, 800, 350, 650, 750};
    
    @Override
    public void run(){
        try {
            while(true){
                int indice = (int) ((Math.random() * nombres.length) + 1);
                int indice2 = (int) ((Math.random() * cantidades.length) + 1);
                //int indice3 = (int) (Math.random() * valores.length) + 1;
        
                Materia_PrimaDAO MPDAO = new Materia_PrimaDAO();
                Materia_Prima aux = MPDAO.cargarMateriaPrima(nombres[indice-1]);

                    Materia_Prima mp = new Materia_Prima(aux.getNombre(),
                                                    cantidades[indice2-1],
                                                    LocalDate.now(),
                                                    aux.getNit_proveedor(),
                                                    aux.getValor_unitario());
                    
                    MPDAO.updateMateriaPrima(mp);  
                
                int tiempo =(int) ((Math.random() * 1000) + 1);
                Thread.sleep(tiempo);
            }
        } catch (InterruptedException e) {
            System.out.println("paila " + e.getMessage());
        } catch (NEDException ex) {
                Logger.getLogger(AutomaticFillerMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    

}
