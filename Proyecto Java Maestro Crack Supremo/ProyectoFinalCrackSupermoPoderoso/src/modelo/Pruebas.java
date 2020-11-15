package modelo;

import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[]args){
        Recibo R = new Recibo();
        R.setC("Lalo's tienda");
        R.setCantidad(30);
        R.setP("Empanadas");
        R.setV(1006037732);
        R.setFecha(LocalDateTime.now());
        ReciboDAO r = new ReciboDAO();
        r.createRecibo(R);

    }
}
