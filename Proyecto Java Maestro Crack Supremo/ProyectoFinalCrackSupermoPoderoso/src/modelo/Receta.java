package modelo;

public class Receta {
    private String p, m;
    private int cantidad;

    public Receta(String p, String m, int cantidad) {
        this.p = p;
        this.m = m;
        this.cantidad = cantidad;
    }

    public Receta() {
    }
    public String getP() {
        return p;
    }
    public String getM() {
        return m;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setP(String p) {
        this.p = p;
    }
    public void setM(String m) {
        this.m = m;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
