package co.knry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import co.knry.entities.Administradora;

public class MyETL {

	private boolean valid;
	private int linea;
	private List<Error> errors=new ArrayList<Error>();
	private Logger logger;
	private Administradora administradora;
	
	
	public MyETL(String[] tokens, Logger logger, int linea) {
		this.logger=logger;
		this.valid=true;
		this.linea=linea;
		int validador=0;
		
		administradora=new Administradora();
		
		for (int i=0; i<tokens.length;i++){
			String s = tokens[i];
			//CODIGO
			if (i==0){
				if (testAlphaNumeric(s,i)){
					administradora.setCodigo(s);;
					validador++;
				}
			}
			//NOMBRE
			if (i==1){
				if(testAlphaNumeric(s,i)){
					administradora.setNombre(s);
					validador++;
				}
			}
			//COD ID
			if (i==2){
				if(testIdType(s,i)){
					administradora.setCodigo(s);
					validador++;
				}
			}
			//ID
			if (i==3){
				if(testNumeric(s,i)){
					administradora.setNroId(s);
					validador++;
				}
			}
			//NATURALEZA
			if (i==4){
				if(testNaturaleza(s,i)){
					administradora.setNaturaleza(s);
					validador++;
				}
			}
			//MULTIPLE ARP
			if (i==5){
				if(testARP(s,i)){
					administradora.setMultipleArp(1);
				}
				else{
					administradora.setMultipleArp(0);
				}
				validador++;
			}
			//FSP
			if (i==6){
				if(testFSP(s,i)){
					administradora.setFSP(1);
				}
				else{
					administradora.setFSP(0);
				}
				validador++;
			}
			
			//FUSIONADA
			if (i==7){
				if(testFusionada(s,i)){
					administradora.setFusionada(1);
				}
				else{
					administradora.setFusionada(0);
				}
				validador++;
			}
			
			//FECHA FUSION
			if (i==8){
				if(testFecha(s,i)){
					try{
					SimpleDateFormat format = new SimpleDateFormat("DDMMYYYY");
					java.util.Date parsed = format.parse(s);
					administradora.setFechaFusion(new java.sql.Date(parsed.getTime()));
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				
				validador++;
			}
			
		}
		
		this.valid=(validador==tokens.length)?true:false;
		
		
	}
	
	private boolean testFecha(String s, int i) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("DDMMYYYY");
	        java.util.Date parsed = format.parse(s);
			java.sql.Date date = new java.sql.Date(parsed.getTime());
			date.toString();
			return true;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean testFusionada(String s, int campo) {
		String[] matches = new String[] {"X","x"};
		for (String m : matches){
			if (s.contains(m)) return true;
		}
		return false;
	}
	
	private boolean testFSP(String s, int campo) {
		String[] matches = new String[] {"X","x"};
		for (String m : matches){
			if (s.contains(m)) return true;
		}
		return false;
	}
	
	private boolean testARP(String s, int campo) {
		String[] matches = new String[] {"X","x"};
		for (String m : matches){
			if (s.contains(m)) return true;
		}
		return false;
	}

	private boolean testNaturaleza(String s, int campo) {
		String[] matches = new String[] {"PR","PU","MI"};
		for (String m : matches){
			if (s.contains(m)) return true;
		}
		errMsj(linea,"No es una naturaleza v�lida",campo);
		return false;
	}

	private boolean testIdType(String s, int campo) {
		String[] matches = new String[] {"NI","CC","PA","RC"};
		for (String m : matches){
			if (s.contains(m)) return true;
		}
		errMsj(linea,"No es un tipo v�lido",campo);
		return false;
	}

	private boolean testNumeric(String s, int campo) {
		if (Pattern.matches("[0-9]+", s)){
			return true;
		}
		errMsj(linea,"Debe ser alfanum�rico",campo);
		return false;
	}
	
	private boolean testAlphaNumeric(String s, int campo) {
		if (Pattern.matches("[a-zA-Z0-9]{1,}", s)){
			return true;
		}
		errMsj(linea,"Debe ser alfanum�rico",campo);
		return false;
	}

	private boolean errMsj(int linea2, String string, int campo) {
		Error e = new Error(""+linea2,string,campo+"");
		errors.add(e);
		logger.log(e);
		return false;
	}

	public boolean isValid() {
		return valid;
	}
	
	public void setLinea(int linea) {
		this.linea = linea;
	}

	public List<Error> getErrors() {
		return errors;
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Administradora getAdministradora() {
		return administradora;
	}

	public void setAdministradora(Administradora administradora) {
		this.administradora = administradora;
	}
	
	
}

