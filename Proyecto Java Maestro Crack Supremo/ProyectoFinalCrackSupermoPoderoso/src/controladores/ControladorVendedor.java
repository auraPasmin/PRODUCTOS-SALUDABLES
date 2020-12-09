
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ProductoDAO;
import modelo.Vendedor;
import modelo.VendedorDAO;
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
        this.VV.addListenerbtnActualizar(new ProgramaListener());
        this.VV.addListenerbtnVisualizar(new ProgramaListener());
        listarStock();
        listarVentas();
    }
    public ControladorVendedor(VistaVendedor vistaV , VendedorDAO VDAO, ProductoDAO PDAO , Vendedor v){
        this.v = v;
        this.VV = vistaV;
        this.VDAO = VDAO;
        this.PDAO = PDAO;
        this.VV.setVisible(true);
        
        this.VV.addListenerBtnGenearVenta(new ProgramaListener() );
        this.VV.addListenerBtnChat(new ProgramaListener());
        this.VV.addListenerbtnActualizar(new ProgramaListener());
        this.VV.addListenerbtnVisualizar(new ProgramaListener());
    }
    private void listarStock(){
        DefaultTableModel modelo =  new DefaultTableModel(new Object[][]{},new String[]{"Nombre","Cantidad","Precio","Fecha caducidad"});
        String[][] listado = PDAO.mostrarStock();
        while(modelo.getRowCount()!=0){
            modelo.removeRow(0);
        }
        for(int r=0; r < listado.length ;r++){
            if(!listado[r][1].trim().equals("0"))
                modelo.addRow(listado[r]);
        }
        VV.getJtStock().setModel(modelo);
        System.out.println("alo");
    }

    private void listarVentas() {
        DefaultTableModel modelo =  new DefaultTableModel(VDAO.generarRecibos(v),new String[]{"Cliente","Fecha"});
        VV.getJtRecibos().setModel(modelo);

    }
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if(e.getActionCommand().equalsIgnoreCase("Generar venta")) {
                this.generarVenta();
            }else if(e.getActionCommand().equalsIgnoreCase("Chat")){
                System.out.println(e.getActionCommand());
                ControladorCVendedor chat = new ControladorCVendedor(v.getNombre());
            }else if(e.getActionCommand().equalsIgnoreCase("Actualizar venta")){
                listarVentas();
            }else if(e.getActionCommand().equalsIgnoreCase("Visualizar venta")){
                mostrarRecibo();
            }   

        }
        private void generarVenta(){
            //llama otra vista que recibira todos los datos y creacion de Cliente y Recibo 
            //VistaGVenta VGV = new VistaGVenta();
            ControladorGVenta CGV = new ControladorGVenta(v);
        }
        private void mostrarRecibo(){
            JTable tabla = VV.getJtRecibos();
            int indice = tabla.getSelectedRow();
            if(indice!=-1){
                ControladorRecibo c = new ControladorRecibo(v.getCedula()+"",
                        tabla.getModel().getValueAt(indice, 0)+"",
                        ((LocalDateTime) tabla.getModel().getValueAt(indice, 1)).toLocalDate() );
                c.insertarDatos();
            }else{
                JOptionPane.showMessageDialog(null,"Seleccione un recibo");
            }
        }
        
    }
}


/**
 generar vista --> genere una venta ( recibo ) dandode 
 
 */