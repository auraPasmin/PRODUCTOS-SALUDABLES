package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorGVenta {
    
    
    public ControladorGVenta(){
        
        
    }
    class ProgramaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("Generar venta")) {
                
            }else if(e.getActionCommand().equalsIgnoreCase("Chat")){
                
            }  

        }
        private void generarVenta(){
            //llama otra vista que recibira todos los datos y creacion de Cliente y Recibo 
            
        }
        
    }
}
