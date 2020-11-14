
package modelo;


public class Cliente {
    private String NIT, nombre, direccion;
    private double X,Y;

    public Cliente() {
    }

    public Cliente(String NIT, String nombre, String direccion, double X, double Y) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.direccion = direccion;
        this.X = X;
        this.Y = Y;
    }

    public String getNIT() {
        return NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setX(double X) {
        this.X = X;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    @Override
    public String toString() {
        return "Cliente{" + "NIT=" + NIT + ", nombre=" + nombre + ", direccion=" + direccion + ", X=" + X + ", Y=" + Y + '}';
    }
    
    
}
