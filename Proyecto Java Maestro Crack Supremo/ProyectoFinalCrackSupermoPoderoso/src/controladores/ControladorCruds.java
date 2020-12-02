/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.VistaCrud;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import modelo.*;

import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class ControladorCruds {
    private VistaCrud crud = null;
    private int ind;
    private Object[] CRUDS = {new ClienteDAO(), new Materia_PrimaDAO(), new ProductoDAO(), new ProveedorDAO(), new RecetaDAO(), new ReciboDAO(),
    new VendedorDAO()};
    public ControladorCruds(int ind) {
        this.ind = ind;
        
        crud = new VistaCrud();
        if(CRUDS[ind] instanceof ClienteDAO){
            crud.setModeloTabla(new String[] {"NIT", "Nombre", "Direcion", "Longitud", "Latitud"});
        }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
            crud.setModeloTabla(new String[] {"Nombre", "Cantidad", "Fecha de Vencimiento", "NIT", "Valor"});
        }else if(CRUDS[ind] instanceof ProductoDAO){
            crud.setModeloTabla(new String[] {"Nombre", "Cantidad", "Precio", "Fecha de vencimiento"});
        }else if(CRUDS[ind] instanceof ProveedorDAO){
            crud.setModeloTabla(new String[] {"NIT", "Nombre", "Ubicacion", "Telefono", "Email"});
        }else if(CRUDS[ind] instanceof RecetaDAO){
            crud.setModeloTabla(new String[] {"Producto", "Materia", "Cantidad"});
        }else if(CRUDS[ind] instanceof ReciboDAO){
            crud.setModeloTabla(new String[] {"Vendedor", "Cliente", "Producto", "Fecha", "Cantidad"});
        }else{
            crud.setModeloTabla(new String[]{"cedula","nombre","cargo","comision","Telefono","correo","sexo"});
        }
        crud.setVisible(true);
    }

    class buttonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        }
    }
    
    public void delete(String n) {
        if(CRUDS[ind] instanceof ClienteDAO){
            ((ClienteDAO) CRUDS[ind]).borrarCliente(n);
        }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
            ((Materia_PrimaDAO) CRUDS[ind]).deleteMateriaPrima(n);
        }else if(CRUDS[ind] instanceof ProductoDAO){
            ((ProductoDAO) CRUDS[ind]).deleteProducto(n);
        }else if(CRUDS[ind] instanceof ProveedorDAO){
            ((ProveedorDAO) CRUDS[ind]).deleteMateriaPrima(n);
        }else if(CRUDS[ind] instanceof RecetaDAO){
            ((RecetaDAO) CRUDS[ind]).deleteReceta(n);
        }else if(CRUDS[ind] instanceof ReciboDAO){
            try {
                ((ReciboDAO) CRUDS[ind]).deleteRecibo(Integer.parseInt(n));
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor numerico", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            try {
                ((VendedorDAO)CRUDS[ind]).deleteVendedor(Integer.parseInt(n));
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor numerico", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}