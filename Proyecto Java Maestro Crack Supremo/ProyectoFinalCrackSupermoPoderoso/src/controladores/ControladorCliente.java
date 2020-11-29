

package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vistas.ChatCliente;
import vistas.VistaCliente;
import modelo.*;



public class ControladorCliente {
    protected VistaCliente VC = null;
    private ClienteDAO cdao = null;
    private Cliente cliente = null;
    private ReciboDAO RDAO = new ReciboDAO();
  
    public ControladorCliente(VistaCliente VC, ClienteDAO cdao){
        this.VC = VC; 
        this.cdao = cdao;
        //VC = new VistaCliente();
        VC.btnChatActionPerformed(new ProgramaListener());
        VC.setVisible(true);    
        this.mostrarTabla();
    }
    private void mostrarTabla(){
            this.limpiarListadoTabla();
            ArrayList<Recibo> rec = RDAO.readRecibo();
            DefaultTableModel modelo = (DefaultTableModel) VC.getJtFacturas().getModel();
            for(int i= 0; i < rec.size(); i++){
                modelo.addRow(new Object[]{
                    rec.get(i).getP(),
                    rec.get(i).getV(),
                    rec.get(i).getFecha(),
                    rec.get(i).getCantidad()});
            }
        }
    private void limpiarListadoTabla(){
            DefaultTableModel modelo;
            modelo = (DefaultTableModel)  VC.getJtFacturas().getModel();
            for(int i=modelo.getRowCount()-1; i>=0 ; i--){
                modelo.removeRow(i);
            }
        } 
    
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("iniciar Chat")) {
                ChatCliente chat = new ChatCliente("Tsubaki", "127.0.0.1");
            }else if(e.getActionCommand().equalsIgnoreCase("mostrar")){
                this.mostrarRecibo();
            }

        }
        private void mostrarRecibo(){
            JTable tabla = VC.getJtFacturas();
            int indice = tabla.getSelectedRow();
            if(indice!=-1){
                JOptionPane.showMessageDialog(VC, "Producto: "+ tabla.getColumn(0).toString()
                                                + "\nVendedor: "+ tabla.getColumn(1).toString()
                                                + "\nFecha: "+ tabla.getColumn(2).toString()
                                                + "\nCantidad"+ tabla.getColumn(3).toString()
                                            );
            }
        }
    }

}
