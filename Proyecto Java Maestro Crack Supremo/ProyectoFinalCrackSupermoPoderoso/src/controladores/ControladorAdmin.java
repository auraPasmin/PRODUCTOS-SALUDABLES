
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.JOptionPane;
import modelo.VendedorDAO;
import vistas.VistaAdmin;


public class ControladorAdmin {
    VistaAdmin VA = null;
    ControladorCruds CCRUDS;
    VendedorDAO v = new VendedorDAO();
    public ControladorAdmin(){
        this.VA = new VistaAdmin();
        this.VA.addListenerBtnClientes(new buttonEvent());
        this.VA.addListenerBtnProducto(new buttonEvent());
        this.VA.addListenerBtnVendedor(new buttonEvent());
        this.VA.addListenerBtnRecibo(new buttonEvent());
        this.VA.addListenerBtnMP(new buttonEvent());
        this.VA.addListenerBtnProveedor(new buttonEvent());
        this.VA.addListenerBtnReceta(new buttonEvent());
        this.VA.addListenerjBtnGeolocalizar(new buttonEvent());
        this.VA.setVisible(true);
        VA.setFieldsjClocalizar(v.readVendedores());
    }
    class buttonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getActionCommand().equalsIgnoreCase("Clientes")) {
                CCRUDS = new ControladorCruds(0);
            }else if(event.getActionCommand().equalsIgnoreCase("Materia Prima")) {
                CCRUDS = new ControladorCruds(1);
            }else if(event.getActionCommand().equalsIgnoreCase("producto")) {
                CCRUDS = new ControladorCruds(2);
            }else if(event.getActionCommand().equalsIgnoreCase("proveedor")) {
                CCRUDS = new ControladorCruds(3);
            }else if(event.getActionCommand().equalsIgnoreCase("Receta")) {
                CCRUDS = new ControladorCruds(4);
            }else if(event.getActionCommand().equalsIgnoreCase("recibo")) {
                CCRUDS = new ControladorCruds(5);
            }else if(event.getActionCommand().equalsIgnoreCase("Vendedor")) {
                CCRUDS = new ControladorCruds(6);
            }else if(event.getActionCommand().equalsIgnoreCase("Geolocalizar")){
                LocalDate dateToGeo = dateGeo();
                if(dateToGeo != null) {
                    ControladorSCliente cs = new ControladorSCliente(Integer.parseInt(VA.getFieldsjClocalizar()), dateToGeo);
                }
            }
        }
    }
    
    public LocalDate dateGeo() {
        int day = VA.getDay();
        LocalDate date = null;
        
        try {
            date = LocalDate.of(2020, Month.of(VA.getMes()), day);
        }
        catch(DateTimeException e) {
            JOptionPane.showMessageDialog(null, "Esa fecha no existe");
        }
        return date;
    }

}
