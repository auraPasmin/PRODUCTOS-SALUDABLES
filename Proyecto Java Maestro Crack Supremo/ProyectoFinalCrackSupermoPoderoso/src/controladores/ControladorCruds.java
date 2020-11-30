/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.VistaCrud;

/**
 *
 * @author つばき
 */
public class ControladorCruds {
    private VistaCrud crud = null;
    
    public controlador() {
        crud = new VistaCrud();
        
        // Metodos escucha de crear
        crud.addActionBtnCrear(new buttonEvent);
        crud.addActionBtnCrear1(new buttonEvent);
        crud.addActionBtnCrear2(new buttonEvent);
        crud.addActionBtnCrear3(new buttonEvent);
        crud.addActionBtnCrear4(new buttonEvent);
        crud.addActionBtnCrear5(new buttonEvent);
        crud.addActionBtnCrear6(new buttonEvent);
        
        // Metodos escucha de Eliminar
        crud.addActionBtnEliminar(new buttonEvent);
        crud.addActionBtnEliminar1(new buttonEvent);
        crud.addActionBtnEliminar2(new buttonEvent);
        crud.addActionBtnEliminar3(new buttonEvent);
        crud.addActionBtnEliminar4(new buttonEvent);
        crud.addActionBtnEliminar5(new buttonEvent);
        crud.addActionBtnEliminar6(new buttonEvent);
    }
}
