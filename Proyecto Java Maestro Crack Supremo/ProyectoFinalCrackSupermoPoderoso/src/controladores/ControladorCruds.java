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
    
    public ControladorCruds() {
        crud = new VistaCrud();
        crud.setVisible(true);
        
        // Metodos escucha de crear
        crud.addActionBtnCrear(new buttonEvent());
        // Metodo escucha de listar
        crud.addActionBtnListar(new buttonEvent());
    }

    class buttonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getActionCommand().equalsIgnoreCase("Listar Materia")) {
                crud.clearTable();
                ArrayList<Materia_Prima> l = null;
                Object[] o = null;
                try {
                    listarMateria thread = new listarMateria();
                    o = new Object[5];
                    thread.execute();
                    l = thread.get();
                    
                    for(int n = 0; n < l.size(); n++) {
                        o[0] = l.get(n).getNombre();
                        o[1] = l.get(n).getCantidad();
                        o[2] = l.get(n).getFechaCaducidad();
                        o[3] = l.get(n).getNit_proveedor();
                        o[4] = l.get(n).getValor_unitario();
                        
                        crud.tableMateria(o);
                    }
                }
                catch(InterruptedException | ExecutionException e) {
                    e.getMessage();
                }
                
            }
            
            if(event.getActionCommand().equalsIgnoreCase("Crear Materia")) {
            }
            
            if(event.getActionCommand().equalsIgnoreCase("Listar Receta")) {
                crud.clearTableReceta();
                ArrayList<Receta> r = null;
                Object[] objetoReceta = null;
                try {
                    ListarReceta recetas = new ListarReceta();
                    recetas.execute();
                    r = recetas.get();
                    objetoReceta = new Object[3];
                    
                    for(int n = 0; n < r.size(); n++) {
                        objetoReceta[0] = r.get(n).getP();
                        objetoReceta[1] = r.get(n).getM();
                        objetoReceta[2] = r.get(n).getCantidad();
                        
                        crud.tablaReceta(objetoReceta);
                    }
                }
                catch(ExecutionException | InterruptedException e) {
                    e.getMessage();
                }
            }
            
            if(event.getActionCommand().equalsIgnoreCase("Listar Producto")) {
                crud.clearTableProducto();
                ArrayList<Producto> p = null;
                Object[] objetoProducto = null;
                try {
                    ListarProducto productos = new ListarProducto();
                    productos.execute();
                    p = productos.get();
                    objetoProducto = new Object[4];
                    
                    for(int n = 0; n < p.size(); n++) {
                        objetoProducto[0] = p.get(n).getNombre();
                        objetoProducto[1] = p.get(n).getCantidad();
                        objetoProducto[2] = p.get(n).getFechaCaducidad();
                        objetoProducto[3] = p.get(n).getPrecio();
                        
                        crud.tablaProducto(objetoProducto);
                    }
                }
                catch(ExecutionException | InterruptedException e) {
                    e.getMessage();
                }
            }
        }
    }
}

class listarMateria extends SwingWorker<ArrayList<Materia_Prima>, Object> {
    
    private Materia_PrimaDAO materiaDao;
    
    public listarMateria() {
        materiaDao = new Materia_PrimaDAO();
    }
    
    @Override
    public ArrayList<Materia_Prima> doInBackground() {
        return materiaDao.readMateriaPrima();
    }
    
    @Override
    protected void done() {
        // ...
    }
}

class ListarReceta extends SwingWorker<ArrayList<Receta>, Object> {
    private RecetaDAO recetaDao;
    
    public ListarReceta() {
        recetaDao = new RecetaDAO();
    }
    
    @Override
    public ArrayList<Receta> doInBackground() {
        return recetaDao.readRecetas();
    }
    
    @Override
    public void done() {}
}

class ListarProducto extends SwingWorker<ArrayList<Producto>, Object> {
    private ProductoDAO productoDao;
    
    public ListarProducto() {
        productoDao = new ProductoDAO();
    }
    
    @Override
    public ArrayList<Producto> doInBackground() {
        return productoDao.readProducto();
    }
    
    @Override
    public void done() {}
}