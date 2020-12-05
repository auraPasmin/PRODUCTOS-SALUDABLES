package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.CVendedor;
import vistas.VistaChat;

public class ControladorCVendedor {
    CVendedor chatVendedor;
    //ChatServidor vista;
    VistaChat vista;
    public ControladorCVendedor(String vendedor) {
        vista = new VistaChat();
        chatVendedor = new CVendedor(vendedor, vista.getChat());
        chatVendedor.start();
        vista.addListenerjBotonEnviar(new ChatController());
        vista.setUsuario("Vendedor " + vendedor);
        vista.setVisible(true);
    }
    class ChatController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("Enviar")){
                chatVendedor.sendMessages(vista.getMessage());
            }
            System.out.println(ae.getActionCommand());
        }
    
    }
}
