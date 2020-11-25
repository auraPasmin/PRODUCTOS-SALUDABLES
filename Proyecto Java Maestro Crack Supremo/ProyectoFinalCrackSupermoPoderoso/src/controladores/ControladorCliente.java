

package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.VistaCliente;


public class ControladorCliente {
    VistaCliente VC = null;
    
    public ControladorCliente(VistaCliente vistaC){
        this.VC = vistaC;
        this.VC.setVisible(true);       
        
        
    }
    
    
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("nuevo")){
                
            }else if(e.getActionCommand().equalsIgnoreCase("cancelar")){
                
            }else if(e.getActionCommand().equalsIgnoreCase("modificar")){
                
            }else if(e.getActionCommand().equalsIgnoreCase("salir")){
                
            }
        }

    }
}
