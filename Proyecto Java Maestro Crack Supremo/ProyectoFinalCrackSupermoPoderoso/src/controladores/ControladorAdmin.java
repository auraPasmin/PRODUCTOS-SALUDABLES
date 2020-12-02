
package controladores;

import javax.swing.JOptionPane;
import java.awt.event.*;
import vistas.VistaAdmin;


public class ControladorAdmin {
    VistaAdmin VA = null;
    ControladorCruds CR = null;
    
    public ControladorAdmin(VistaAdmin vistaA){
        this.VA = vistaA;
        VA.addListenerBotones(new ButtonEvent());
        this.VA.setVisible(true);
        
    }
    
    class ButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getActionCommand().equalsIgnoreCase("Clientes")) {
                CR = new ControladorCruds(0);
            }
            if(event.getActionCommand().equalsIgnoreCase("Materia Prima")) {
                CR = new ControladorCruds(1);
            }
            if(event.getActionCommand().equalsIgnoreCase("Producto")) {
                CR = new ControladorCruds(2);
            }
            if(event.getActionCommand().equalsIgnoreCase("Proveedor")) {
                CR = new ControladorCruds(3);
            }
            if(event.getActionCommand().equalsIgnoreCase("Receta")) {
                CR = new ControladorCruds(4);
            }
            if(event.getActionCommand().equalsIgnoreCase("Recibo")) {
                CR = new ControladorCruds(5);
            }
            if(event.getActionCommand().equalsIgnoreCase("Vendedor")) {
                CR = new ControladorCruds(6);
            }
        }
    }
}
