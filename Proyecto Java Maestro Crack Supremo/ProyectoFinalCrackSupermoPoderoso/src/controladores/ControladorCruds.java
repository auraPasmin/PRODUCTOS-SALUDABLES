/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import java.util.logging.*;

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
        this.crud.addListenerBtnCrear(new programListener());
        this.crud.addListenerBtnEditar(new programListener());
        this.crud.addListenerBtnEliminar(new programListener());
        this.crud.addListenerbtnListar(new programListener());
        this.crud.addListenerbtnBuscar(new programListener());
        this.crud.setVisible(true);
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
    protected void decide(int ind, String str) throws NEDException{
            String selected = "";
            try{
                selected = crud.getData()[0] + "";
            }catch(ArrayIndexOutOfBoundsException AIOB){
                JOptionPane.showMessageDialog(null,"No hay nada seleccionado");
            }catch(NullPointerException e){
                System.out.println("nada seleccionado");
            }
            
            Object[]data = crud.getData();
            if(CRUDS[ind] instanceof ClienteDAO){
                //crud.setModeloTabla(head, null);
                crud.getLabelTitle().setText("Cliente");
                switch(str){
                    case "crear":
                        this.ingresarCliente();
                        break;
                    case "editar":
                        this.editarCliente(data);
                        visualizarClientes(((ClienteDAO)CRUDS[ind]));
                        break;
                    case "eliminar":
                        this.delete(selected);
                        break;
                    default:
                        System.out.println("jeje visualizar");
                        visualizarClientes(((ClienteDAO)CRUDS[ind]));
                        break;
                }
 
            }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
                crud.setModeloTabla(head, null);
                crud.getLabelTitle().setText("Materia Prima");
                 switch(str){
                    case "crear":
                        this.ingresarMateria();
                        visualizarMateriaPrima(((Materia_PrimaDAO)CRUDS[ind]));
                        break;
                    case "editar":
                        this.editarMateria_Prima(data);
                        visualizarMateriaPrima(((Materia_PrimaDAO)CRUDS[ind]));;
                        break;
                    case "eliminar":
                        this.delete(selected);
                        visualizarMateriaPrima(((Materia_PrimaDAO)CRUDS[ind]));
                        break;
                    default:
                        visualizarMateriaPrima(((Materia_PrimaDAO)CRUDS[ind]));
                        break;
                 }
            }else if(CRUDS[ind] instanceof ProductoDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Producto");
                 switch(str){
                    case "crear":
                        this.ingresarProducto();
                        visualizarProducto(((ProductoDAO)CRUDS[ind]));
                        break;
                    case "editar":
                        this.editarProducto(data);
                        visualizarProducto(((ProductoDAO)CRUDS[ind]));
                        break;
                    case "eliminar":
                        this.delete(selected);
                        visualizarProducto(((ProductoDAO)CRUDS[ind]));
                        break;
                    default:
                        visualizarProducto(((ProductoDAO)CRUDS[ind]));
                        break;
                 }
            }else if(CRUDS[ind] instanceof ProveedorDAO){
                crud.setModeloTabla(head, null);
                crud.getLabelTitle().setText("Proveedor");
                 switch(str){
                    case "crear":
                        this.ingresarProveedor();
                        visualizarProveedor(((ProveedorDAO)CRUDS[ind]).readProveedor());
                        break;
                    case "editar":
                         this.editarProveedor(data);
                         visualizarProveedor(((ProveedorDAO)CRUDS[ind]).readProveedor());
                         break;
                    case "eliminar":
                        this.delete(selected);
                        visualizarProveedor(((ProveedorDAO)CRUDS[ind]).readProveedor());
                        break;
                    default:
                        visualizarProveedor(((ProveedorDAO)CRUDS[ind]).readProveedor());
                        break;
                 }
            }else if(CRUDS[ind] instanceof RecetaDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Receta");
                 switch(str){
                    case "crear":
                        this.ingresarReceta();
                        visualizarReceta(((RecetaDAO)CRUDS[ind]).readRecetas());
                        break;
                    case "editar":
                        this.editarReceta(data);
                        visualizarReceta(((RecetaDAO)CRUDS[ind]).readRecetas());
                        break;
                    case "eliminar":
                        this.delete(selected);
                        visualizarReceta(((RecetaDAO)CRUDS[ind]).readRecetas());
                        break;
                    default:
                        visualizarReceta(((RecetaDAO)CRUDS[ind]).readRecetas());
                        break;
                 }
            }else if(CRUDS[ind] instanceof ReciboDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Recibo");
                 switch(str){
                    case "crear":
                        this.ingresarRecibo();
                        visualizarRecibo(((ReciboDAO)CRUDS[ind]).readRecibo());
                        break;
                    case "editar":
                        this.editarRecibo(data);
                        visualizarRecibo(((ReciboDAO)CRUDS[ind]).readRecibo());
                        break;
                    case "eliminar":
                        this.delete(selected);
                        visualizarRecibo(((ReciboDAO)CRUDS[ind]).readRecibo());
                        break;
                    default:
                        visualizarRecibo(((ReciboDAO)CRUDS[ind]).readRecibo());
                        break;
                 }
            }else if(CRUDS[ind] instanceof VendedorDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Vendedor");
                 switch(str){
                    case "crear":
                        this.ingresarVendedor();
                        break;
                    case "editar":
                        this.editarVendedor(data);
                        visualizarVendedor(((VendedorDAO)CRUDS[ind]).readVendedores());
                        break;
                    case "eliminar":
                        this.delete(selected);
                        visualizarVendedor(((VendedorDAO)CRUDS[ind]).readVendedores());
                        break;
                    default:
                        visualizarVendedor(((VendedorDAO)CRUDS[ind]).readVendedores());
                        break;
                 }
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
            }catch(NumberFormatException e) {
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

    private void ingresarCliente(){
            Cliente Cli = new Cliente();
            Cli.setNIT(JOptionPane.showInputDialog("ingrese NIT"));
            Cli.setNombre(JOptionPane.showInputDialog("ingrese nombre"));
            Cli.setDireccion(JOptionPane.showInputDialog("ingrese direccion"));
            Cli.setX( Double.parseDouble(JOptionPane.showInputDialog("ingrese Latitud")));
            Cli.setY(Double.parseDouble(JOptionPane.showInputDialog("ingrese Longitud")));
            ((ClienteDAO) CRUDS[ind]).createCliente(Cli); 
    }
    private void ingresarMateria(){
            Materia_Prima mat = new Materia_Prima();
            mat.setNombre(JOptionPane.showInputDialog("ingrese nombre"));
            mat.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("ingrese cantidad")));
            mat.setFechaCaducidad(LocalDate.now());
            mat.setNit_proveedor(JOptionPane.showInputDialog("ingrese NIT_Proveedor"));
            mat.setValor_unitario(Integer.parseInt(JOptionPane.showInputDialog("ingrese ValorUnitario")));
            ((Materia_PrimaDAO) CRUDS[ind]).createMateria_Prima(mat);   
    }
    private void ingresarProducto(){
            Producto prod = new Producto();
            prod.setNombre(JOptionPane.showInputDialog("ingrese nombre"));
            prod.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("ingrese cantidad")));
            prod.setFechaCaducidad(LocalDate.now());
            prod.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("ingrese precio")));
            ((ProductoDAO) CRUDS[ind]).createProducto(prod); 
    }
    private void ingresarProveedor(){
            Proveedor prov = new Proveedor();
            prov.setNit(JOptionPane.showInputDialog("ingrese NIT"));
            prov.setNombre(JOptionPane.showInputDialog("ingrese nombre"));
            prov.setUbicacion(JOptionPane.showInputDialog("ingrese ubicacion"));
            prov.setTelefono(Integer.parseInt(JOptionPane.showInputDialog("ingrese telefono")));
            prov.setEmail(JOptionPane.showInputDialog("ingrese email"));
            ((ProveedorDAO) CRUDS[ind]).createProveedor(prov); 
    }
    private void ingresarReceta(){
            Receta rec = new Receta();
            rec.setP(JOptionPane.showInputDialog("ingrese Producto"));
            rec.setM(JOptionPane.showInputDialog("ingrese Materia Prima"));
            rec.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("ingrese Cantidad")));
            ((RecetaDAO) CRUDS[ind]).createReceta(rec);          
    }
    private void ingresarRecibo() throws NEDException{
            Recibo rb = new Recibo();
            rb.setV(Integer.parseInt(JOptionPane.showInputDialog("ingrese cedula Vendedor")));
            rb.setC(JOptionPane.showInputDialog("ingrese cedula cliente"));
            rb.setP(JOptionPane.showInputDialog("ingrese Producto"));
            rb.setFecha(LocalDateTime.now());
            rb.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("ingrese Cantidad")));
            ((ReciboDAO) CRUDS[ind]).createRecibo(rb);     
    }
    private void ingresarVendedor(){
            Vendedor vend = new Vendedor();
            vend.setCedula(Integer.parseInt(JOptionPane.showInputDialog("ingrese cedula")));
            vend.setNombre(JOptionPane.showInputDialog("ingrese nombre"));
            vend.setCargo(JOptionPane.showInputDialog("ingrese cargo"));
            vend.setComision(Double.parseDouble(JOptionPane.showInputDialog("ingrese comision")));
            vend.setTelefono(Integer.parseInt(JOptionPane.showInputDialog("ingrese telefono")));
            vend.setEmail(JOptionPane.showInputDialog("ingrese email"));
            vend.setSexo(JOptionPane.showInputDialog("ingrese sexo (M o F)"));
            ((VendedorDAO) CRUDS[ind]).createVendedor(vend); 
    }



    private void editarCliente(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        Cliente c = new Cliente((String)data[0],(String)data[1],(String)data[2],(double)data[3],(double)data[4]);
        c.setNombre(JOptionPane.showInputDialog("Digite el nuevo nombre", data[1]));
        c.setDireccion(JOptionPane.showInputDialog("Digite la nueva direccion", data[2]));
        c.setX(Double.parseDouble(JOptionPane.showInputDialog("Digite la nueva posición x", data[3])));
        c.setY(Double.parseDouble(JOptionPane.showInputDialog("Digite la nueva posición x", data[4])));
        ((ClienteDAO) CRUDS[ind]).modificarCliente(c); 
        JOptionPane.showMessageDialog(crud,"Cliente modificado");
    }
    private void editarMateria_Prima(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        Materia_Prima m = new Materia_Prima((String) data[0],(int) data[1],(LocalDate) data[2],(String) data[3],(int) data[4]);
        m.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad",data[1])));
        m.setFechaCaducidad(LocalDate.parse(JOptionPane.showInputDialog("Ingrese la nueva fecha de caducidad",data[2])));
        m.setValor_unitario(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo valor unitario",data[4])));
        ((Materia_PrimaDAO) CRUDS[ind]).updateMateriaPrima(m);
        JOptionPane.showMessageDialog(crud,"Materia prima modificada");
    }
    private void editarProducto(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        Producto p = new Producto((String) data[0],(int) data[1],(double) data[2],(LocalDate) data[3]);
        p.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad",data[1])));
        p.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva cantidad",data[2])));
        p.setFechaCaducidad(LocalDate.parse(JOptionPane.showInputDialog("Ingrese la nueva fecha de caducidad",data[3]) ));
        
        ((ProductoDAO) CRUDS[ind]).updateProducto(p);
        JOptionPane.showMessageDialog(crud,"Producto modificado");
    }
    private void editarProveedor(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        Proveedor p = new Proveedor((String) data[0],(String) data[1],(String) data[2],(int) data[3], (String)data[4]);
        p.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre",data[1]));
        p.setUbicacion(JOptionPane.showInputDialog("Ingrese la nueva ubicacion",data[2]));
        p.setTelefono(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo telefono",data[3])));
        p.setEmail(JOptionPane.showInputDialog("Ingrese el nuevo email",data[4]));
        ((ProveedorDAO) CRUDS[ind]).updateProveedor(p);
        JOptionPane.showMessageDialog(crud,"Proveedor modificado");
    }
    private void editarReceta(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        Receta r = new Receta((String) data[0],(String) data[1],(int) data[2]);
        r.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad",data[2])));
        ((RecetaDAO) CRUDS[ind]).updateReceta(r);
        JOptionPane.showMessageDialog(crud,"Receta modificada");
    }
    private void editarRecibo(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        Recibo r = new Recibo((int) data[0],(String) data[1],(String) data[2],(LocalDateTime)data[3],(int)data[4]);
        r.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad",data[4])));
        ((ReciboDAO) CRUDS[ind]).updateRecibo(r);
        JOptionPane.showMessageDialog(crud,"Recibo modificado");
    }
    private void editarVendedor(Object[] data) {
        if(data== null){
            JOptionPane.showMessageDialog(crud,"Seleccione un registro");
            return;
        }
        //(int cedula, String nombre, String cargo, int telefono, String email, double comision, String sexo)
        Vendedor v = new Vendedor((int) data[0],(String) data[1],(String) data[2],(int)data[3],(String)data[4],(double)data[5],(String)data[6]);
        v.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre",data[1]));
        v.setCargo(JOptionPane.showInputDialog("Ingrese el nuevo cargo",data[2]));
        v.setTelefono(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo telefono",data[3])));
        v.setEmail(JOptionPane.showInputDialog("Ingrese el nuevo email",data[4]));
        v.setComision(Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva comision",data[5])));
        v.setSexo(JOptionPane.showInputDialog("Ingrese el nuevo sexo",data[6]));
        ((VendedorDAO) CRUDS[ind]).updateVendedor(v);
        JOptionPane.showMessageDialog(crud,"Vendedor modificado");
    }
    
    class programListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println(event.getActionCommand());
            if(event.getActionCommand().equalsIgnoreCase("crear")) {
                try {
                    decide(ind,"crear");//  <-----------------
                } catch (NEDException ex) {
                    Logger.getLogger(ControladorCruds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else
            if(event.getActionCommand().equalsIgnoreCase("editar")) {
                try {
                    decide(ind,"editar");//  <-----------------
                } catch (NEDException ex) {
                    Logger.getLogger(ControladorCruds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else
            if(event.getActionCommand().equalsIgnoreCase("eliminar")) {
                try {
                    decide(ind,"eliminar");//  <-----------------
                } catch (NEDException ex) {
                    Logger.getLogger(ControladorCruds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                try {
                    decide(ind,"listar");
                } catch (NEDException ex) {
                    Logger.getLogger(ControladorCruds.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    
    }
}