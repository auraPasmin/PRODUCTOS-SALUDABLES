package modelo;

import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[]args){
        Receta receta = new Receta();
        receta.setP("Udon");
        receta.setM("fideos");
        receta.setCantidad(260);

        RecetaDAO rdao = new RecetaDAO();
        rdao.createReceta(receta);
    }
}
