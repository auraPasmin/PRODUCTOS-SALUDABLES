package modelo;

public class Proveedor {
    private String nit,nombre,ubicacion;
    private int telefono;
    private String email,nit_proveedor;

    public Proveedor() {
    }

    public Proveedor(String nit, String nombre, String ubicacion, int telefono, String email, String nit_proveedor) {
        this.nit = nit;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
        this.nit_proveedor = nit_proveedor;
    }

    public String getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }
    
}
