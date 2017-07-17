package co.knry.entities;

import java.sql.Date;


public class Administradora {
	
	private String codigo;
	private String nombre;
	private String codTpId;
	private String nroId;
	private String naturaleza;
	private int multipleArp;
	private int FSP;
	private int fusionada;
	private Date fechaFusion;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodTpId() {
		return codTpId;
	}
	public void setCodTpId(String codTpId) {
		this.codTpId = codTpId;
	}
	public String getNroId() {
		return nroId;
	}
	public void setNroId(String nroId) {
		this.nroId = nroId;
	}
	public String getNaturaleza() {
		return naturaleza;
	}
	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}
	public int getMultipleArp() {
		return multipleArp;
	}
	public void setMultipleArp(int multipleArp) {
		this.multipleArp = multipleArp;
	}
	public int getFSP() {
		return FSP;
	}
	public void setFSP(int fSP) {
		FSP = fSP;
	}
	public int getFusionada() {
		return fusionada;
	}
	public void setFusionada(int fusionada) {
		this.fusionada = fusionada;
	}
	public Date getFechaFusion() {
		return fechaFusion;
	}
	public void setFechaFusion(Date fechaFusion) {
		this.fechaFusion = fechaFusion;
	}
	
	

}
