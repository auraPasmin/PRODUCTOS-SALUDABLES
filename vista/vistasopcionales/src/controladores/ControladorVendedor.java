
package controladores;

import javax.swing.JOptionPane;
import vistas.VistaVendedor;


public class ControladorVendedor {
    VistaVendedor VV = null;
    
    public ControladorVendedor(VistaVendedor vistaV){
        this.VV = vistaV;
        this.VV.setVisible(true);
    }
    public void funcion(){
        JOptionPane.showMessageDialog(null, "funcion controlador de vendedor");
    }
}
