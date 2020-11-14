package modelo;

public class Pruebas {
    public static void main(String[]args){
        Cliente c = new Cliente();
        c.setDireccion("La direccion de lalo");
        c.setNIT("12345");
        c.setNombre("Lalo's tienda");
        c.setX(1000);
        c.setY(1000);
        ClienteDAO cd = new ClienteDAO();
        cd.createCliente(c);
//        Recibo R = new Recibo();
//        R.setC("Lalo");
//        ReciboDAO r = new ReciboDAO();
    }
}
