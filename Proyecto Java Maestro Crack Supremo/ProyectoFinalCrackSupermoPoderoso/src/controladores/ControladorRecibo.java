package controladores;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.NEDException;
import modelo.ReciboDAO;
import modelo.Vendedor;
import modelo.VendedorDAO;
import vistas.VistaRecibo;

public class ControladorRecibo {
    private VendedorDAO v;
    private ReciboDAO r;
    private ClienteDAO c;
    private VistaRecibo vista;
    private String vendedor, cliente;
    private LocalDate fecha;
    public ControladorRecibo(String vendedor, String cliente, LocalDate fecha){
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.fecha = fecha;
        v = new VendedorDAO();
        r = new ReciboDAO();
        c = new ClienteDAO();
        vista = new VistaRecibo();
        
        vista.setVisible(true);
    }
    public void insertarDatos(){
        try {
            Vendedor V = v.cargarVendedor(Integer.parseInt(vendedor));
            Cliente C = c.cargarCliente(cliente);
            vista.setVendedor(V.getNombre());
            vista.setCliente(C.getNombre());
            vista.setFecha(fecha.toString());
            vista.setVentas(r.generarRecibo(Integer.parseInt(vendedor), cliente, fecha));
        } catch (NEDException ex) {
            Logger.getLogger(ControladorRecibo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
