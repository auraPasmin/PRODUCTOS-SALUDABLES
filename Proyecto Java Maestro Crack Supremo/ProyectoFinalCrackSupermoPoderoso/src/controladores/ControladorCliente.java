

package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import vistas.ChatCliente;
import vistas.VistaCliente;
import modelo.*;



public class ControladorCliente {
    VistaCliente VC = null;
    private ClienteDAO cdao = null;
    private Cliente cliente = null;
    
    public ControladorCliente(){
        VC = new VistaCliente();
        VC.btnChatActionPerformed(new ProgramaListener());
        VC.setVisible(true);             
    }
    
    
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("iniciar Chat")) {
                ChatCliente chat = new ChatCliente("Tsubaki", "127.0.0.1");
            }
            
            if(e.getActionCommand().equalsIgnoreCase("nuevo")){
                
            }else if(e.getActionCommand().equalsIgnoreCase("cancelar")){
                
            }else if(e.getActionCommand().equalsIgnoreCase("modificar")){
                
            }else if(e.getActionCommand().equalsIgnoreCase("salir")){
                
            }
        }

    }
}
