package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.NEDException;
import modelo.Vendedor;
import modelo.VendedorDAO;
import vistas.VistaAdmin;
import vistas.VistaCliente;
import vistas.VistaLogin;
import vistas.VistaVendedor;

public class ControladorLogin {
    private VistaLogin vista;
    private VendedorDAO v;
    private ClienteDAO c;
    public ControladorLogin(){
        vista = new VistaLogin();
        v = new VendedorDAO();
        c = new ClienteDAO();
        vista.setVisible(true);
        vista.addListenerBtID(new LoginListener());
        
    }

    public ControladorLogin(VistaLogin vista, VendedorDAO v, ClienteDAO c) {
        this.vista = vista;
        this.v = v;
        this.c = c;
        vista.setVisible(true);
        vista.addListenerBtID(new LoginListener());
    }
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("ENTRAR")){
                ingreso();
            }
        }

    }
    
    public void ingreso(){
        String cad = vista.getID();
        Cliente C = null;
        Vendedor V = null;
        if(cad.equals("admin123")){
            ControladorAdmin v = new ControladorAdmin();
            
            vista.dispose();
        }else{
            try {
                V = v.cargarVendedor(Integer.parseInt(cad));
                ControladorVendedor controlador = new ControladorVendedor(V);
                vista.dispose();
                return;
            } catch (NEDException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
            
            try {
                C = c.cargarCliente(cad);
                ControladorCliente controlador = new ControladorCliente(C);
                vista.dispose();
                return;
            } catch (NEDException ex) {
                System.out.println(ex.getMessage());
            }
            if(V== null && C == null){
                JOptionPane.showMessageDialog(vista, "No existe ese usuario en el sistema");
            }
        }
        
    }
    
    /*public void ingreso() {
        String cad = vista.getID();
        Cliente C = null;
        Vendedor V = null;
        
        if(cad.equals("admin123")) {
            ControladorAdmin controlAdmin = new ControladorAdmin();
            vista.dispose();
        }
        else {
            if(V == null) {
                try {
                    V = v.cargarVendedor(Integer.parseInt(cad));
                    ControladorVendedor controlV = new ControladorVendedor(V);
                    if(C != null) {
                        vista.dispose();
                        return;
                    }
                }
                catch(NEDException | NullPointerException e) {
                    System.out.println(e.getCause());
                }
            }
            
            if(C == null) {
                try {
                    C = c.cargarCliente(cad);
                    ControladorCliente controlC = new ControladorCliente(C);
                    if(V != null) {
                        vista.dispose();
                        return;
                    }
                }
                catch(NEDException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if((C == null) && (V == null)) {
            JOptionPane.showMessageDialog(null, "No existe ese usuario en el sistema");
        }
    }*/
}
