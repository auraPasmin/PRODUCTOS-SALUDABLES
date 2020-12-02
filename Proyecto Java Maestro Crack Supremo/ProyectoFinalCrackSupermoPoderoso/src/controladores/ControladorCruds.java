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
import vistas.VistaCruds;

/**
 *
 * @author 
 */
public class ControladorCruds {
    private VistaCruds crud = null;
    private int ind;
    private String[] head;
    private Object[] CRUDS = {new ClienteDAO(), new Materia_PrimaDAO(), new ProductoDAO(), new ProveedorDAO(), new RecetaDAO(), new ReciboDAO(),
    new VendedorDAO()};
    public ControladorCruds(int ind) {
        this.ind = ind;
        crud = new VistaCruds();
        if(CRUDS[ind] instanceof ClienteDAO){
            head = new String[]{"NIT","nombre","direccion","X","Y"};
        }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
            head = new String[]{"nombre","cantidad","fechaCaducidad","NIT_Proveedor","ValorUnitario"};
        }else if(CRUDS[ind] instanceof ProductoDAO){
            head = new String[]{"nombre","cantidad","precio","fechaCaducidad"};
        }else if(CRUDS[ind] instanceof ProveedorDAO){
        
            head = new String[]{"NIT","nombre","ubicacion","telefono","email"};
        }else if(CRUDS[ind] instanceof RecetaDAO){
            head = new String[]{"Producto","MateriaPrima","cantidad"};
        }else if(CRUDS[ind] instanceof ReciboDAO){

            head = new String[]{"Vendedor","Cliente","Producto","fecha","cantidad"};
        }else{
            head = new String[]{"cedula","nombre","cargo","comision","Telefono","correo","sexo"};
        }
        crud.setModeloTabla(head,null);
        cargarDatos();
        crud.setVisible(true);
    }
    public void cargarDatos(){
        if(CRUDS[ind] instanceof ClienteDAO){
            visualizarClientes(((ClienteDAO)CRUDS[ind]));
        }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
            visualizarMateriaPrima(((Materia_PrimaDAO)CRUDS[ind]));
        }else if(CRUDS[ind] instanceof ProductoDAO){
            visualizarProducto(((ProductoDAO)CRUDS[ind]));
        }else if(CRUDS[ind] instanceof ProveedorDAO){
            visualizarProveedor(((ProveedorDAO)CRUDS[ind]).readProveedor());
        }else if(CRUDS[ind] instanceof RecetaDAO){
            visualizarReceta(((RecetaDAO)CRUDS[ind]).readRecetas());
        }else if(CRUDS[ind] instanceof ReciboDAO){
            visualizarRecibo(((ReciboDAO)CRUDS[ind]).readRecibo());
        }else{
            visualizarVendedor(((VendedorDAO)CRUDS[ind]).readVendedores());
        }
    }

    private void visualizarClientes(ClienteDAO C) {
        ArrayList<Cliente> listarClientes = C.listarClientes();
        System.out.println(listarClientes);
        String cad = "".trim();
        Cliente c;
        if(cad.equals("")){
            Object[][]data = new Object[listarClientes.size()][5];
            for(int i = 0 ; i < listarClientes.size() ; ++i){
                c = listarClientes.get(i);
                data[i][0] = c.getNIT();
                data[i][1] = c.getNombre();
                data[i][2] = c.getDireccion();
                data[i][3] = c.getX();
                data[i][4] = c.getY();
            }
        crud.setModeloTabla(head,data);
        }else{
            try {
                c = C.cargarCliente(cad);
                Object[][]data = {{c.getNIT(),c.getNombre(),c.getDireccion(),c.getX(),c.getY()}};
                crud.setModeloTabla(head,data);
            } catch (NEDException ex) {
                JOptionPane.showMessageDialog(null, "No existe ese cliente");
                crud.setModeloTabla(head,new Object[][]{});
            }
        }
        
    }

    private void visualizarMateriaPrima(Materia_PrimaDAO MP) {
        ArrayList<Materia_Prima> mat = MP.readMateriaPrima();
        String cad = "".trim();
        Materia_Prima c;
        if(cad.equals("")){
            Object[][]data = new Object[mat.size()][5];
            for(int i = 0 ; i < mat.size() ; ++i){
                 c = mat.get(i);
                data[i][0] = c.getNombre();
                data[i][1] = c.getCantidad();
                data[i][2] = c.getFechaCaducidad();
                data[i][3] = c.getNit_proveedor();
                data[i][4] = c.getValor_unitario();
            }
            crud.setModeloTabla(head,data);
            crud.setModeloTabla(head,data);
        }else{
            try {
                c = MP.cargarMateriaPrima(cad);
                Object[][]data = {{c.getNombre(),c.getCantidad(),c.getFechaCaducidad(),c.getNit_proveedor(),c.getValor_unitario()}};
                crud.setModeloTabla(head,data);
            } catch (NEDException ex) {
                JOptionPane.showMessageDialog(null, "No existe esa materia prima");
                crud.setModeloTabla(head,new Object[][]{});
            }
        }
        
    }

    private void visualizarProducto(ProductoDAO P) {
        ArrayList<Producto> prod = P.readProducto();
        String cad = "".trim();
        Producto c;
        Object[][]data = new Object[prod.size()][4];
        for(int i = 0 ; i < prod.size() ; ++i){
            c = prod.get(i);
            data[i][0] = c.getNombre();
            data[i][1] = c.getCantidad();
            data[i][2] = c.getCantidad();
            data[i][3] = c.getFechaCaducidad();  
        }
        crud.setModeloTabla(head,data);
    }

    private void visualizarProveedor(ArrayList<Proveedor> prov) {
        Object[][]data = new Object[prov.size()][5];
        for(int i = 0 ; i < prov.size() ; ++i){
            Proveedor c = prov.get(i);
            data[i][0] = c.getNit();
            data[i][1] = c.getNombre();
            data[i][2] = c.getUbicacion();
            data[i][3] = c.getTelefono();  
            data[i][4] = c.getEmail(); 
        }
        crud.setModeloTabla(head,data);
    }

    private void visualizarReceta(ArrayList<Receta> rect) {
        Object[][]data = new Object[rect.size()][3];
        for(int i = 0 ; i < rect.size() ; ++i){
            Receta c = rect.get(i);
            data[i][0] = c.getP();
            data[i][1] = c.getM();
            data[i][2] = c.getCantidad();
        }
        crud.setModeloTabla(head,data);
    }

    private void visualizarRecibo(ArrayList<Recibo> rec) {
        Object[][]data = new Object[rec.size()][5];
        for(int i = 0 ; i < rec.size() ; ++i){
            Recibo c = rec.get(i);
            data[i][0] = c.getV();
            data[i][1] = c.getC();
            data[i][2] = c.getP();
            data[i][3] = c.getFecha();
            data[i][4] = c.getCantidad();
        }
        crud.setModeloTabla(head,data);
    }

    private void visualizarVendedor(ArrayList<Vendedor> ven) {
        Object[][]data = new Object[ven.size()][7];
        for(int i = 0 ; i < ven.size() ; ++i){
            Vendedor c = ven.get(i);
            data[i][0] = c.getCedula();
            data[i][1] = c.getNombre();
            data[i][2] = c.getCargo();
            data[i][3] = c.getComision();
            data[i][4] = c.getTelefono();
            data[i][5] = c.getEmail();
            data[i][6] = c.getSexo();
        }
        crud.setModeloTabla(head,data);
    }

    class buttonEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}