package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.CVendedor;
import vistas.ChatServidor;

public class ControladorCVendedor {
        CVendedor chatVendedor;
    ChatServidor vista;

    public ControladorCVendedor(String cliente) {
        vista = new ChatServidor(cliente);
        chatVendedor = new CVendedor(cliente, vista.getText());
        chatVendedor.start();
        vista.addListenerbtn(new ChatController());
    }
    class ChatController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("enviar")){
                chatVendedor.sendMessages(vista.getMessage());
            }
            System.out.println(ae.getActionCommand());
        }
    
    }
}
