package modelo;

import java.time.LocalDate;

public class Producto {
    private String nombre;
    private int cantidad;
    private double precio;
    private LocalDate fechaCaducidad;

    public Producto() {
    }

    public Producto(String nombre, int cantidad, double precio, LocalDate fechaCaducidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", fechaCaducidad=" + fechaCaducidad + '}';
    }
    
    
    
}
