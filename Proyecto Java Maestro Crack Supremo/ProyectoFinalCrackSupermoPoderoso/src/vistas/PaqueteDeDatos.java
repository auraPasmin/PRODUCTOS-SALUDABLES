import java.io.Serializable;

class PaqueteDeDatos implements Serializable{
	private String nameCliente;
	private String mensaje;
	
	public PaqueteDeDatos() {
		nameCliente = "";
		mensaje = "";
	}

	public String getNameCliente() {
		return nameCliente;
	}

	public void setNameCliente(String nameCliente) {
		this.nameCliente = nameCliente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
