package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import vistas.ChatCliente;

public class ControladorCCliente {
    CCliente chatCliente;
    ChatCliente vista;

    public ControladorCCliente(String cliente, String direccion) {
        vista = new ChatCliente(cliente, direccion);
        chatCliente = new CCliente(cliente, direccion, vista.getText());
        chatCliente.start();
    }
    class ChatController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            System.out.println(ae.getActionCommand());
        }
    
    }
    
}
