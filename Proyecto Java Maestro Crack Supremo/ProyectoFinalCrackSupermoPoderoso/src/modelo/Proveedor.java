package modelo;

public class Proveedor {
    private String nit,nombre,ubicacion;
    private int telefono;
    private String email;

    public Proveedor() {
    }

    public Proveedor(String nit, String nombre, String ubicacion, int telefono, String email) {
        this.nit = nit;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
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

    @Override
    public String toString() {
        return "Proveedor{" + "nit=" + nit + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono + ", email=" + email + '}';
    }

    
}
