package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import modelo.*;
import vistas.*;

public class ControladorCCliente {
    CCliente chatCliente;
    ChatCliente vista;

    public ControladorCCliente(String cliente, String direccion) {
        vista = new ChatCliente(cliente, direccion);
        vista.setVisible(true);
        //vista.addActionBtnSend(new EventChat());
        //vista.addActionTextMessaje(new EventChat());
        
        //chatCliente = new CCliente(cliente, direccion, vista.getText());
        //chatCliente.start();
        
    }
    
    class EventChat implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("enviar")){
                //chatCliente.sendMessages(vista.getMessage());
                System.out.println("fofof");
            }
            System.out.println(ae.getActionCommand());
        }
    
    }
    
}
