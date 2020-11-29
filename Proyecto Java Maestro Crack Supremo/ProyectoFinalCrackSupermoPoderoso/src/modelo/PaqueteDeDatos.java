package modelo;
import java.io.Serializable;

public class PaqueteDeDatos implements Serializable{
	private String nameUser;
	private String mensaje;
	
	public PaqueteDeDatos() {
		nameUser = "";
		mensaje = "";
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
