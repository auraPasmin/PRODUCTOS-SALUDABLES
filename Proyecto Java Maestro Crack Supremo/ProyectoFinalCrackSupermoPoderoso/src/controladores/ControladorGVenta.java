package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

import modelo.NEDException;
import modelo.ProductoDAO;
import modelo.ReciboDAO;
import modelo.Vendedor;
import vistas.VistaGVenta;


public class ControladorGVenta {
    VistaGVenta vista;
    ReciboDAO r;
    ProductoDAO p;
    Vendedor v;
    public ControladorGVenta(Vendedor v){
        this.v = v;
        vista = new VistaGVenta();
        r = new ReciboDAO();
        p = new ProductoDAO();
        vista.btnGenerarAddListener(new ProgramaListener());
        vista.btnIngresarAddListener(new ProgramaListener());
        vista.setVisible(true);
        cargarProductos();
    }

    private void cargarProductos() {
        System.out.println(p.readProducto());
        vista.cargarProductos(p.readProducto());
    }
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if(e.getActionCommand().equalsIgnoreCase("generar")) {
                generarVenta();
            }else if(e.getActionCommand().equalsIgnoreCase("ingresar")){
                vista.addProducto(new Object[]{vista.getProducto(),vista.getCantidad()});
            }

        }
        private void generarVenta(){
            try {
                r.crearRecibo(v.getCedula(), vista.getNIT(), LocalDateTime.now(), vista.getProductos(), vista.getCantidadProd());
            } catch (NEDException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
        
    }
}
