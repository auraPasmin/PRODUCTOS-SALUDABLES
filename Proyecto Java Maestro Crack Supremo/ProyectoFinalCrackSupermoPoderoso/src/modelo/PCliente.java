/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Reynell
 */
public class PCliente{
    
    
    public static void main(String[]args){
        CCliente c = new CCliente("tsubaki","localhost");
        c.start();
        while(true){
            
            c.sendMessages(JOptionPane.showInputDialog("Mensajito jeje"));
        }
    }
    
}
