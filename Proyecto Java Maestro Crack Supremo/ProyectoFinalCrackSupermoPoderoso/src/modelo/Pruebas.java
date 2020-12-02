package modelo;


import controladores.ControladorAdmin;
import controladores.ControladorCCliente;
import controladores.ControladorCVendedor;
import controladores.ControladorCruds;
import controladores.ControladorGVenta;
import vistas.VistaAdmin;


public class Pruebas {
    public static void main(String[]args) throws NEDException{
        ControladorAdmin q = new ControladorAdmin(new VistaAdmin());
        
    }
}
