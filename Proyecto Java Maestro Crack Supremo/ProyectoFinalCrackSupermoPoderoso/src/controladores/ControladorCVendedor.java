package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.CVendedor;
import vistas.VistaChat;

public class ControladorCVendedor {
    CVendedor chatVendedor;
    //ChatServidor vista;
    VistaChat vista;
    public ControladorCVendedor(String vendedor) {
        vista = new VistaChat();
        chatVendedor = new CVendedor(vendedor, vista.getChat());
        vista.addListenerjBotonEnviar(new ChatController());
        vista.addWindowClose(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String messageSave = chatVendedor.saveChat();
                chatVendedor.interruptServer();
                
                if(!messageSave.equals(""))
                    vista.guardarChat(messageSave);
            }
        });
        chatVendedor.start();
        vista.setUsuario("Vendedor " + vendedor);
        vista.setVisible(true);
    }
    
    /*public void starchat() {
        chatVendedor.start();
    }*/
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
