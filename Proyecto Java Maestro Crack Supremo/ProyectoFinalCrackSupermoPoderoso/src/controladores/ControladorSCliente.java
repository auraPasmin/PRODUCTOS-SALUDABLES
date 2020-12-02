/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public ControladorSCliente(int ven){
        V = new VendedorDAO();
        try {
            double[][] ub = V.encontrarUbicacion(ven, LocalDate.parse("2020-11-20"));
            
            vista = new VistaSVendedor(ub[0],ub[1]);
            vista.setVisible(true);
        } catch (NEDException ex) {
            Logger.getLogger(ControladorSCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
