package modelo;

import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[]args){
        /*Recibo r = new Recibo();

        r.setV(100607577);
        r.setC("12a");
        r.setCantidad(50);
        r.setFecha(LocalDateTime.now());
        r.setP("Udon");
        ReciboDAO re = new ReciboDAO();
        re.createRecibo(r);*/

        Vendedor vendedor = new Vendedor();
        vendedor.setCedula(2543211);
        vendedor.setNombre("Rem");
        vendedor.setCargo("Proveedor");
        vendedor.setComision(2300000);
        vendedor.setTelefono(300840704);
        vendedor.setEmail("rem@gmail.es");
        vendedor.setSexo("mujer");

        VendedorDAO vdao = new VendedorDAO();
        vdao.createVendedor(vendedor);
    }
}
