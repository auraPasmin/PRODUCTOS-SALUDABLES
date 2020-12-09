

package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
        VC.setjtaDatospersonales("Datos personales: \nNIT: " + cliente.getNIT() + "\nNombre:" + cliente.getNombre() + "\nDireccion: " + cliente.getDireccion());
        this.mostrarTabla();
    }
    private void mostrarTabla(){
        try {
            this.limpiarListadoTabla();
            Object[][]data = cdao.generarRecibos(cliente);
            System.out.println(Arrays.toString(data));
            DefaultTableModel modelo = (DefaultTableModel) VC.getJtFacturas().getModel();
            for(int i= 0; i < data.length; i++){
                modelo.addRow(data[i]);
            }
        }
        catch(NullPointerException e) {
            System.out.println("error de la tabla");
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
                ControladorCCliente c = new ControladorCCliente(cliente.getNombre(), "localhost");
            }else if(e.getActionCommand().equalsIgnoreCase("mostrar")){
                System.out.println("");
                mostrarRecibo();
            }

        }
        private void mostrarRecibo(){
            JTable tabla = VC.getJtFacturas();
            int indice = tabla.getSelectedRow();
            if(indice!=-1){
                ControladorRecibo c = new ControladorRecibo(tabla.getModel().getValueAt(indice, 0)+"",
                        cliente.getNIT(), 
                        ((LocalDateTime) tabla.getModel().getValueAt(indice, 1)).toLocalDate());
                c.insertarDatos();
            }else{
                JOptionPane.showMessageDialog(null,"Seleccione un recibo");
            }
        }
    }

}
