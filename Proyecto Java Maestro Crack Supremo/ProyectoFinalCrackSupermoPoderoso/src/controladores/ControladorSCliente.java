/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.NEDException;
import modelo.VendedorDAO;
import vistas.VistaSVendedor;

/**
 *
 * @author Reynell
 */
public class ControladorSCliente {
    private VistaSVendedor vista;
    private VendedorDAO V;
    public ControladorSCliente(int ven, LocalDate date){
        V = new VendedorDAO();
        try {
            double[][] ub = V.encontrarUbicacion(ven, date);
            
            vista = new VistaSVendedor(ub);
            vista.setVisible(true);
        } catch (NEDException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "El vendedor identificado con " + ven + "No tiene ventas en esa fecha");
        }
        
    }
}
