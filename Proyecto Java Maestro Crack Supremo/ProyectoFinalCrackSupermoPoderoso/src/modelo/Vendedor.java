
package modelo;

public class Vendedor {
    private int cedula;
    private String nombre, cargo;
    private int telefono;
    private String email;
    private double comision;
    private String sexo;

    public Vendedor() {
    }

    public Vendedor(int cedula, String nombre, String cargo, int telefono, String email, double comision, String sexo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.cargo = cargo;
        this.telefono = telefono;
        this.email = email;
        this.comision = comision;
        this.sexo = sexo;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public double getComision() {
        return comision;
    }

    public String getSexo() {
        return sexo;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "cedula=" + cedula + ", nombre=" + nombre + ", cargo=" + cargo + ", telefono=" + telefono + ", email=" + email + ", comision=" + comision + ", sexo=" + sexo + '}';
    }
    
    
    
}
