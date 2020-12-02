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
 * @author つばき
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
            System.out.println("true");
        }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
            System.out.println(((Materia_PrimaDAO) CRUDS[ind]).readMateriaPrima());
        }else if(CRUDS[ind] instanceof ProductoDAO){
            System.out.println("materia prima");
        }else if(CRUDS[ind] instanceof ProveedorDAO){
            System.out.println("materia prima");
        }else if(CRUDS[ind] instanceof RecetaDAO){
            System.out.println("materia prima");
        }else if(CRUDS[ind] instanceof ReciboDAO){
            System.out.println("materia prima");
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
}
