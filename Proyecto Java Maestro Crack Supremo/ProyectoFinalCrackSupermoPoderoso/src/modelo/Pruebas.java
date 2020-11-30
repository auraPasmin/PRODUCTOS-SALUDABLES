package modelo;


import controladores.ControladorCCliente;
import controladores.ControladorCVendedor;
import controladores.ControladorGVenta;


public class Pruebas {
    public static void main(String[]args) throws NEDException{
/*
        Materia_PrimaDAO m = new Materia_PrimaDAO();
        m.crearMP(new Materia_Prima());
*/      

        /**
         * Prueba reynell
         *
        AutomaticFillerMP ATMP = new AutomaticFillerMP();
        Thread hiloATMP = new Thread(ATMP);
        hiloATMP.setDaemon(true);
        ATMP.start();
        * 
        * /*/
        
        // Prueba Santiago
        //ControladorCVendedor cv = new ControladorCVendedor("master");
        VendedorDAO v = new VendedorDAO();
        ControladorGVenta cc = new ControladorGVenta(v.cargarVendedor(456412));        
        
    }
}
