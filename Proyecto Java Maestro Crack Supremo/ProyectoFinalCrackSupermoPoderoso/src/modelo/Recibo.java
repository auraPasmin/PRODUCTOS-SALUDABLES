package modelo;

import java.time.LocalDateTime;

public class Recibo {
    private int v;
    private String c,p;
    private LocalDateTime fecha;
    private int cantidad;

    public Recibo() {
    }
    public Recibo(int v, String c, String p, LocalDateTime fecha, int cantidad) {
        this.v = v;
        this.c = c;
        this.p = p;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getV() {
        return v;
    }

    public String getC() {
        return c;
    }

    public String getP() {
        return p;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setP(String p) {
        this.p = p;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
