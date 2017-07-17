package co.knry;

public class Error {

	private String linea;
	private String descripcion;
	private String campo;
	
	public Error(String linea, String descripcion, String campo) {
		this.linea=linea;
		this.descripcion=descripcion;
		this.campo=campo;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	

}
