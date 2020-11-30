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
