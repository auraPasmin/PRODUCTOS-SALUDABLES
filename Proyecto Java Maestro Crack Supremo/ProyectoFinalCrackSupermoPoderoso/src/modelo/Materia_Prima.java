package modelo;

import java.time.LocalDate;

public class Materia_Prima {
    private String nombre;
    private int cantidad;
    private LocalDate fechaCaducidad;
    private String nit_proveedor;
    private int valor_unitario;
    public Materia_Prima() {
    }

    public Materia_Prima(String nombre, int cantidad, LocalDate fechaCaducidad, String nit_proveedor, int valor_unitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.nit_proveedor = nit_proveedor;
        this.valor_unitario = valor_unitario;
    }

    public String getNombre() {
        return nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    public String getNit_proveedor() {
        return nit_proveedor;
    }
    public int getValor_unitario() {
        return valor_unitario;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }
    public void setValor_unitario(int valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
    
    @Override
    public String toString() {
        return "Materia_Prima{" + "nombre=" + nombre + ", cantidad=" + cantidad + ", fechaCaducidad=" + fechaCaducidad + ", nit_proveedor=" + nit_proveedor + ", valor_unitario=" + valor_unitario + '}';
    }
    

    
}
