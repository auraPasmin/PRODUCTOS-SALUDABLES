/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*import vistas.VistaCrud;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.*;

import javax.swing.JOptionPane;
import vistas.VistaCruds;

public class ControladorCruds {
    private VistaCruds crud = null;
    protected int ind;
    private Object[] CRUDS = {new ClienteDAO(), 
                                new Materia_PrimaDAO(), 
                                new ProductoDAO(), 
                                new ProveedorDAO(), 
                                new RecetaDAO(), 
                                new ReciboDAO(), 
                                new VendedorDAO()};
   
   String [] Cliente = {"NIT","nombre","direccion","X","Y"};
   String [] Materia_Prima = {"nombre","cantidad","fechaCaducidad","NIT_Proveedor","ValorUnitario"};
   String [] Producto = {"nombre","cantidad","precio","fechaCaducidad"};
   String [] Proveedor = {"NIT","nombre","ubicacion","telefono","email"};
   String [] Receta = {"Producto","MateriaPrima","cantidad"};
   String [] Recibo = {"Vendedor","Cliente","Producto","fecha","cantidad"};
   String [] Vendedor = {"cedula","nombre","cargo","comision","Telefono","correo","sexo"};
           
    public ControladorCruds(int ind) {
        this.crud = new VistaCruds();
        
        this.ind = ind;
        this.crud.addButtonEvent(new programListener());
        crud.addWindowsEvent(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                crud.dispose();
            }
        });
        this.crud.setVisible(true);
    }
    
    class programListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
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
            }else
            if(event.getActionCommand().equalsIgnoreCase("listar")) {
                try {
                    decide(ind,"listar");
                }
                catch(NEDException ex) {
                    Logger.getLogger(ControladorCruds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//////FIN CLASE PROGRAMA LISTENER
    
 ///////////////////////////////////////////////////////////////////////////////////////////////////
    //############################################################################################
                //System.out.println(((Materia_PrimaDAO) CRUDS[ind]).readMateriaPrima());
        protected void decide(int ind, String str) throws NEDException{
            
            if(CRUDS[ind] instanceof ClienteDAO){
                crud.setModeloTabla(Cliente,null);
                //crud.getLabelTitle().setText("Cliente");
                switch(str){
                    case "crear":
                        this.ingresarCliente();
                        break;
                    case "editar":
                        //this.editarCliente();
                    case "eliminar":
                        //this.eliminarCliente();
                    default:
                        visualizarClientes((ClienteDAO)CRUDS[ind]);
                }
 
            }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
                crud.setModeloTabla(Materia_Prima, null);
                //crud.getLabelTitle().setText("Materia Prima");
                 switch(str){
                    case "crear":
                        this.ingresarMateria();
                    case "editar":
                        //this.editarMateria();
                    case "eliminar":
                        //this.eliminaMateria();
                    default:
                        //System.out.println("hay un error xD");
                 }
            }else if(CRUDS[ind] instanceof ProductoDAO){
                crud.setModeloTabla(Producto, null);
                //crud.getLabelTitle().setText("Producto");
                 switch(str){
                    case "crear":
                        this.ingresarProducto();
                    case "editar":
                        //this.editarProducto();
                    case "eliminar":
                        //this.eliminarProducto();
                    default:
                        //System.out.println("hay un error xD");
                 }
            }else if(CRUDS[ind] instanceof ProveedorDAO){
                crud.setModeloTabla(Proveedor, null);
                //crud.getLabelTitle().setText("Proveedor");
                 switch(str){
                    case "crear":
                        this.ingresarProveedor();
                    case "editar":
                         //this.editarProveedor();
                    case "eliminar":
                        //this.eliminarProveedor
                    default:
                        //System.out.println("hay un error xD");
                 }
            }else if(CRUDS[ind] instanceof RecetaDAO){
                crud.setModeloTabla(Receta, null);
                //crud.getLabelTitle().setText("Receta");
                 switch(str){
                    case "crear":
                        this.ingresarReceta();
                    case "editar":
                        //this.editarCliente();
                    case "eliminar":
                        //this.eliminarReceta();
                    default:
                        //System.out.println("hay un error xD");
                 }
            }else if(CRUDS[ind] instanceof ReciboDAO){
                crud.setModeloTabla(Recibo, null);
                //crud.getLabelTitle().setText("Recibo");
                 switch(str){
                    case "crear":
                        this.ingresarRecibo();
                    case "editar":
                        //this.editarCliente();
                    case "eliminar":
                        //this.eliminarRecibo();
                    default:
                        //System.out.println("hay un error xD");
                 }
            }else if(CRUDS[ind] instanceof VendedorDAO){
                crud.setModeloTabla(Vendedor, null);
                //crud.getLabelTitle().setText("Vendedor");
                 switch(str){
                    case "crear":
                        this.ingresarVendedor();
                        System.out.println("Crear Cliente jeje");
                    case "editar":
                        //this.editarVendedor();
                        System.out.println("Editar Cliente jeje");
                    case "eliminar":
                        //this.eliminarVendedor()
                        System.out.println("Eliminar Cliente jeje");
                    default:
                        //System.out.println("hay un error xD");
                 }
            }     
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
        crud.setModeloTabla(Cliente,data);
        }else{
            try {
                c = C.cargarCliente(cad);
                Object[][]data = {{c.getNIT(),c.getNombre(),c.getDireccion(),c.getX(),c.getY()}};
                crud.setModeloTabla(Cliente,data);
            } catch (NEDException ex) {
                JOptionPane.showMessageDialog(null, "No existe ese cliente");
                crud.setModeloTabla(Cliente,new Object[][]{});
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
}*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import vistas.VistaCrud;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.*;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vistas.VistaCruds;

/**
 *
 * @author 
 */
public class ControladorCruds {
    
    //private int selectRow = -1;
    private VistaCruds crud = null;
    private int ind;
    private String[] head;
    private Object[] CRUDS = {new ClienteDAO(), new Materia_PrimaDAO(), new ProductoDAO(), new ProveedorDAO(), new RecetaDAO(), new ReciboDAO(),
    new VendedorDAO()};
    
    public ControladorCruds(int ind) {
        this.ind = ind;
        crud = new VistaCruds();
        this.crud.addButtonEvent(new programListener());
        //this.crud.selectionOfList(new TableRowSelection());
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
            protected void decide(int ind, String str) throws NEDException{
            
            if(CRUDS[ind] instanceof ClienteDAO){
                crud.setModeloTabla(head, null);
                crud.getLabelTitle().setText("Cliente");
                switch(str){
                    case "crear":
                        this.ingresarCliente();
                    case "editar":
                        //this.editarCliente();
                    case "eliminar":
                        this.delete("");
                    default:
                        visualizarClientes(((ClienteDAO)CRUDS[ind]));
                }
 
            }else if(CRUDS[ind] instanceof Materia_PrimaDAO){
                crud.setModeloTabla(head, null);
                crud.getLabelTitle().setText("Materia Prima");
                 switch(str){
                    case "crear":
                        this.ingresarMateria();
                        break;
                    case "editar":
                        //this.editarMateria();
                    case "eliminar":
                        /*if(crud.indexRow() >= 0) {
                            JOptionPane.showMessageDialog(null, selectRow);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Seleccione una fila");*/
                    default:
                        visualizarMateriaPrima(((Materia_PrimaDAO)CRUDS[ind]));
                 }
            }else if(CRUDS[ind] instanceof ProductoDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Producto");
                 switch(str){
                    case "crear":
                        this.ingresarProducto();
                    case "editar":
                        //this.editarProducto();
                    case "eliminar":
                        this.delete("");
                    default:
                        visualizarProducto(((ProductoDAO)CRUDS[ind]));
                 }
            }else if(CRUDS[ind] instanceof ProveedorDAO){
                crud.setModeloTabla(head, null);
                crud.getLabelTitle().setText("Proveedor");
                 switch(str){
                    case "crear":
                        this.ingresarProveedor();
                    case "editar":
                         //this.editarProveedor();
                    case "eliminar":
                        this.delete("");
                    default:
                        visualizarProveedor(((ProveedorDAO)CRUDS[ind]).readProveedor());
                 }
            }else if(CRUDS[ind] instanceof RecetaDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Receta");
                 switch(str){
                    case "crear":
                        this.ingresarReceta();
                    case "editar":
                        //this.editarCliente();
                    case "eliminar":
                        this.delete("");
                    default:
                        visualizarReceta(((RecetaDAO)CRUDS[ind]).readRecetas());
                 }
            }else if(CRUDS[ind] instanceof ReciboDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Recibo");
                 switch(str){
                    case "crear":
                        this.ingresarRecibo();
                    case "editar":
                        //this.editarCliente();
                    case "eliminar":
                        this.delete("");
                    default:
                        visualizarRecibo(((ReciboDAO)CRUDS[ind]).readRecibo());
                 }
            }else if(CRUDS[ind] instanceof VendedorDAO){
                crud.setModeloTabla(head,null);
                crud.getLabelTitle().setText("Vendedor");
                 switch(str){
                    case "crear":
                        this.ingresarVendedor();
                        System.out.println("Crear Cliente jeje");
                    case "editar":
                        //this.editarVendedor();
                        System.out.println("Editar Cliente jeje");
                    case "eliminar":
                        this.delete("");
                    default:
                        visualizarVendedor(((VendedorDAO)CRUDS[ind]).readVendedores());
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
    
    /*private void deleteRecibo(String ) {
        try {
            ((ReciboDAO) CRUDS[ind]).deleteRecibo(Integer.parseInt(n));
        }
        catch(Exception e) {
            e.getMessage();
        }
    }*/
    
    class TableRowSelection implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            //selectRow = crud.indexRow();
        }
    }
    
    class programListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
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
            }else if(event.getActionCommand().equalsIgnoreCase("listar")) {
                try {
                    decide(ind,"listar");
                } catch (NEDException ex) {
                    Logger.getLogger(ControladorCruds.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    
    }
}

