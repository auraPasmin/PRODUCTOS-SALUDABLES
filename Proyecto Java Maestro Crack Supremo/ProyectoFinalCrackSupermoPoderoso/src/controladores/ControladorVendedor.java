
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ProductoDAO;
import modelo.Vendedor;
import modelo.VendedorDAO;
import vistas.ChatCliente;
import vistas.VistaGVenta;
import vistas.VistaVendedor;


public class ControladorVendedor {
    protected VistaVendedor VV = null;
    protected VendedorDAO VDAO = null;
    protected ProductoDAO PDAO = null;
    Vendedor v;
    
    public ControladorVendedor(Vendedor v){
        this.v = v;
        this.VV = new VistaVendedor();
        this.VDAO = new VendedorDAO();
        this.PDAO = new ProductoDAO();
        this.VV.setVisible(true);
        
        this.VV.addListenerBtnGenearVenta(new ProgramaListener() );
        this.VV.addListenerBtnChat(new ProgramaListener());
        this.listarStock();
        this.listarRecibos();
    }
    public ControladorVendedor(VistaVendedor vistaV , VendedorDAO VDAO , Vendedor v){
        this.v = v;
        this.VV = vistaV;
        this.VDAO = VDAO;
        this.PDAO = new ProductoDAO();
        this.VV.setVisible(true);
        
        this.VV.addListenerBtnGenearVenta(new ProgramaListener() );
        this.VV.addListenerBtnChat(new ProgramaListener());
        this.listarStock();
        this.listarRecibos();
    }
    private void listarStock(){
        DefaultTableModel modelo =  (DefaultTableModel) this.VV.getJtStock().getModel();
        String[][] listado = PDAO.mostrarStock();
        
        for(int r=0; r < listado.length ;r++){
                modelo.addRow(listado[r]);
        }
    }
    private void listarRecibos(){
        DefaultTableModel modelo =  (DefaultTableModel) this.VV.getJtRecibos().getModel();
        String[][] listado = (String[][]) VDAO.generarRecibos(v);
            for(int i=0;i<listado.length-1;i++){
                modelo.addRow(listado[i]);
            }                 
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
            ControladorGVenta CGV = new ControladorGVenta(v);
        }
        
    }
}


/**
 generar vista --> genere una venta ( recibo ) dandode 
 
 */