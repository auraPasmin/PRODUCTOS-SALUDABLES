
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ProductoDAO;
import modelo.VendedorDAO;
import vistas.ChatCliente;
import vistas.VistaGVenta;
import vistas.VistaVendedor;


public class ControladorVendedor {
    protected VistaVendedor VV = null;
    protected VendedorDAO VDAO = null;
    protected ProductoDAO PDAO = null;
    
    public ControladorVendedor(VistaVendedor vistaV , VendedorDAO VDAO, ProductoDAO PDAO ){
        this.VV = vistaV;
        this.VDAO = VDAO;
        this.PDAO = PDAO;
        this.VV.setVisible(true);
        
        this.VV.addListenerBtnGenearVenta(new ProgramaListener() );
        this.VV.addListenerBtnChat(new ProgramaListener());
    }
    
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("Generar venta")) {
                this.generarVenta();
            }else if(e.getActionCommand().equalsIgnoreCase("Chat")){
                
            }  

        }
        private void generarVenta(){
            //llama otra vista que recibira todos los datos y creacion de Cliente y Recibo 
            //VistaGVenta VGV = new VistaGVenta();
            ControladorGVenta CGV = new ControladorGVenta();
        }
        
    }
}


/**
 generar vista --> genere una venta ( recibo ) dandode 
 
 */