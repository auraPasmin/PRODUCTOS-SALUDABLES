
package hilos;

import java.time.LocalDate;
import javax.swing.SwingWorker;
import modelo.Materia_Prima;
import modelo.Materia_PrimaDAO;


public class AutomaticFillerMP extends Thread{

    String[] nombres = {"papa", "carne", "arroz","harina-trigo", "pollo","queso", "masa-hojaldre"};
    int[] cantidades = {250, 300, 400, 450, 500, 550, 600};
    int[] valores = {300, 400, 450, 600, 700, 800, 350, 650, 750};
    
    
    
    @Override
    public void run(){
        try {
            while(true){
                int indice = (int) (Math.random() * nombres.length) + 1;
                int indice2 = (int) (Math.random() * cantidades.length) + 1;
                int indice3 = (int) (Math.random() * valores.length) + 1;
        
                int nit =(int) (Math.random() * 50000) + 1;
        
                Materia_Prima mp = new Materia_Prima(nombres[indice],
                                            cantidades[indice2],
                                            LocalDate.now(),
                                            ""+nit,
                                            valores[indice3]);
                Materia_PrimaDAO MPDAO = new Materia_PrimaDAO();
                MPDAO.createMateria_Prima(mp);
                
                int tiempo =(int) (Math.random() * 10000) + 1;
            Thread.sleep(tiempo);
            }
        } catch (InterruptedException e) {
            System.out.println("paila " + e.getMessage());
        }
        
    }
    

}
