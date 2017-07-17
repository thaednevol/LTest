package co.knry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.knry.service.AdministradorService;

public class LectorArchivo {
	private String filename="/tmp/prueba.txt";
	private BufferedReader br;
	
	static LoggerFactory factory = new LoggerFactory();
	static Logger logger = factory.getLogger();
	
	List<Error> errors = Collections.synchronizedList(new ArrayList<Error>());

	private AdministradorService service = new AdministradorService();
	
	public static void main(String ... args){		
//		if (args.length==0){
//			throw new IllegalArgumentException("Ingrese el nombre de un archivo");
//		}
		
		LectorArchivo la = new LectorArchivo();
		la.parse();
	}

	public void parse(){
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String linea;
			int numLinea=1;
			List<Thread> hilos = new ArrayList<>();
			
			while ((linea = br.readLine()) != null) {
				String[] tokens= linea.split(";");
				
				Inserter inserter = new Inserter(tokens,numLinea); 
				Thread t2 = new Thread(inserter);
				hilos.add(t2);
				t2.start();
				numLinea++;
			}
			
			for (Thread hilo : hilos) {
				  try {
					hilo.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (errors.isEmpty()){
					service.commit();
				}
				else {
					service.rollback();
				}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
	
	class Inserter implements Runnable{

		private String[] tokens;
		private int linea;
		
		public Inserter(String[] tokens, int linea) {
			this.tokens=tokens;
			this.linea=linea;
		}

		@Override
		public void run() {
			MyETL myETL = new MyETL(tokens,logger,linea);
				
			if (myETL.isValid()){
				service.add(myETL);
				
			}
			else {
				synchronized (errors) {
				    errors.addAll(myETL.getErrors());
				}
			}
		}
		
	}

	
	
	
}