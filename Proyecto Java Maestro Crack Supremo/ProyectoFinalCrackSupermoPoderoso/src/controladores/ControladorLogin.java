package controladores;

import controladores.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Cliente C= null;
        Vendedor V = null;
        if(cad.equals("admin123")){
            VistaAdmin v = new VistaAdmin();
            v.setVisible(true);
            vista.dispose();
        }else{
            try {
                V = v.cargarVendedor(Integer.parseInt(cad));
                VistaVendedor n = new VistaVendedor();
                n.setVisible(true);
                vista.dispose();
            } catch (NEDException | NumberFormatException ex) {
                V = null;
            }
            try {
                C = c.cargarCliente(cad);
                ControladorCliente controlador = new ControladorCliente();
                vista.dispose();
            } catch (NEDException ex) {
                C = null;
            }
        }
        if(V== null && C == null){
            JOptionPane.showMessageDialog(vista, "No existe ese usuario en el sistema");
        }
    }
}
