package modelo;


import controladores.ControladorAdmin;
import controladores.ControladorCCliente;
import controladores.ControladorCVendedor;
import controladores.ControladorCruds;
import controladores.ControladorGVenta;


public class Pruebas {
    public static void main(String[]args) throws NEDException{
        //ControladorAdmin c = new ControladorAdmin();
        RComision RC = new RComision(478541);
        RComisionDAO RDAO = new RComisionDAO();
        RDAO.createComision(RC);
        //RDAO.deleteComision(456412);
        
    }
}
