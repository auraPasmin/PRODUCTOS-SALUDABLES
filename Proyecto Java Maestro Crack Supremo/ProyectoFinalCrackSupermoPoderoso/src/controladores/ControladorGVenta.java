package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import modelo.NEDException;
import modelo.ReciboDAO;
import modelo.Vendedor;
import vistas.VistaGVenta;


public class ControladorGVenta {
    VistaGVenta vista;
    ReciboDAO r;
    Vendedor v;
    public ControladorGVenta(Vendedor v){
        this.v = v;
        vista = new VistaGVenta();
        r = new ReciboDAO();
        vista.setVisible(true);
    }
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("Generar")) {
                generarVenta();
            }

        }
        private void generarVenta(){
            try {
                r.crearRecibo(v.getCedula(), vista.getNIT(), LocalDateTime.now(), vista.getProductos(), vista.getCantidad());
            } catch (NEDException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
}
