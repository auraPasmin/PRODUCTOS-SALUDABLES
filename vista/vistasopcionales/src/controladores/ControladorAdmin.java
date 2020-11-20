
package controladores;

import javax.swing.JOptionPane;
import vistas.VistaAdmin;


public class ControladorAdmin {
    VistaAdmin VA = null;
    
    public ControladorAdmin(VistaAdmin vistaA){
        this.VA = vistaA;
        this.VA.setVisible(true);
    }
    public void funcion(){
        JOptionPane.showMessageDialog(null, "funcion controlador de vendedor");
    }
}
