package co.knry.utils;

import java.util.List;

public class Memento implements java.io.Serializable {

	private static final long serialVersionUID = 5130846417489918716L;
	private List<ObjConverter> exitosos;
	
	public Memento(List<ObjConverter> exitosos) {
      this.exitosos = exitosos;
    }
    public List<ObjConverter> getExitosos() {
		return exitosos;
	}
  }