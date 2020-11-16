package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pruebas {
    public static void main(String[]args){
        LocalDate t = LocalDate.now();
        System.out.println(t.plusDays(1));
    }
}
