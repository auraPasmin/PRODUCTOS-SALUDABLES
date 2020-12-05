
package modelo;

import java.time.LocalDate;


public class RComision {
    private LocalDate fecha;
    private int vendedor;

    
    public RComision() {
        this.vendedor = 0;
    }
    public RComision(int vendedor) {
        this.vendedor = vendedor;
        this.fecha = LocalDate.now();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
   
    public int getVendedor() {
        return vendedor;
    }

    public void setVendedor(int vendedor) {
        this.vendedor = vendedor;
    }
    
}
