

package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
  
    public ControladorCliente(Cliente c){
        this.cliente = c;
        this.cdao = new ClienteDAO();
        VC = new VistaCliente();
        VC.btnChatActionPerformed(new ProgramaListener());
        VC.btnMostrarActionPerformed(new ProgramaListener());
        VC.setVisible(true);
        VC.setjtaDatospersonales("Datos personales: \n" + cliente.getNIT() + "\n" + cliente.getNombre() + "\n" + cliente.getDireccion());
        this.mostrarTabla();
    }
    private void mostrarTabla(){
            this.limpiarListadoTabla();
            Object[][]data = cdao.generarRecibos(cliente);
            System.out.println(Arrays.toString(data));
            DefaultTableModel modelo = (DefaultTableModel) VC.getJtFacturas().getModel();
            for(int i= 0; i < data.length; i++){
                modelo.addRow(data[i]);
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
                System.out.println("");
                mostrarRecibo();
            }

        }
        private void mostrarRecibo(){
            JTable tabla = VC.getJtFacturas();
            int indice = tabla.getSelectedRow();
            if(indice!=-1){
                ReciboDAO r = new ReciboDAO();
                //r.generarRecibo(indice, NIT, LocalDate.MAX)
                JOptionPane.showMessageDialog(VC, "Vendedor: "+ tabla.getModel().getValueAt(indice, 0)
                                                + "\nFecha: "+ tabla.getModel().getValueAt(indice, 1)
                                                + "\nDatos recibo: " + Arrays.deepToString(r.generarRecibo( 
                                                        Integer.parseInt((String) tabla.getModel().getValueAt(indice, 0)),
                                                        cliente.getNIT(),
                                                        LocalDate.parse((String) tabla.getModel().getValueAt(indice, 1))))
                                            );
            }
        }
    }

}
