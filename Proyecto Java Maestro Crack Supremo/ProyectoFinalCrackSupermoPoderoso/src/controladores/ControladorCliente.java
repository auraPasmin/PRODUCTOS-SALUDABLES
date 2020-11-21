

package controladores;

import javax.swing.JOptionPane;
import vistas.VistaCliente;


public class ControladorCliente {
    VistaCliente VC = null;
    
    public ControladorCliente(VistaCliente vistaC){
        this.VC = vistaC;
        this.VC.setVisible(true);        
    }
    public void funcion(){
        JOptionPane.showMessageDialog(null, "funcion controlador de vendedor");
    }
}
