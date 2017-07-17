package co.knry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import co.knry.service.AdministradorService;
import co.knry.utils.Error;
import co.knry.utils.Logger;
import co.knry.utils.LoggerFactory;
import co.knry.utils.Memento;
import co.knry.utils.MementoHandler;
import co.knry.utils.ObjConverter;

public class LucasianTest {
	private String filename = "/tmp/prueba.txt";
	private BufferedReader br;

	static LoggerFactory factory = new LoggerFactory();
	static Logger logger = factory.getLogger();

	List<Error> errors = Collections.synchronizedList(new ArrayList<Error>());
	List<ObjConverter> successes = Collections.synchronizedList(new ArrayList<ObjConverter>());

	private AdministradorService service = new AdministradorService();

	public static void main(String... args) {
		new LucasianTest().parse();
		
	}

	public void parse(){
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String linea;
			int numLinea = 1;
			List<Thread> hilos = new ArrayList<>();

			int [] exitosos=getExitosos();
			
			while ((linea = br.readLine()) != null) {
				if (!Arrays.asList(exitosos).contains(numLinea)){
					String[] tokens = linea.split(";");
					Inserter inserter = new Inserter(tokens, numLinea);
					Thread t2 = new Thread(inserter);
					hilos.add(t2);
					t2.start();
				}
				numLinea++;
			}

			for (Thread hilo : hilos) {
				try {
					hilo.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (errors.isEmpty()) {
				service.commit();
			} else {
				MementoHandler objMementoHandler = new MementoHandler();
				objMementoHandler.setMemento(new Memento(successes));
				service.rollback();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int[] getExitosos() {
		MementoHandler objMementoHandler = new MementoHandler();
		Memento m=objMementoHandler.getMemento();
		if (m!=null){
			List<ObjConverter> listObjConverter = m.getExitosos();
			int []exitosos=new int [listObjConverter.size()];
			for (int i=0;i<listObjConverter.size();i++){
				exitosos[i]=listObjConverter.get(i).getLinea();
			}
			return exitosos;
		}
		return new int[0];
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

	class Inserter implements Runnable {

		private String[] tokens;
		private int linea;

		public Inserter(String[] tokens, int linea) {
			this.tokens = tokens;
			this.linea = linea;
		}

		@Override
		public void run() {
			ObjConverter objConverter = new ObjConverter(tokens, logger, linea);
			
			if (objConverter.isValid()) {
				service.add(objConverter);
				synchronized (successes) {
					successes.add(objConverter);
				}
			} else {
				synchronized (errors) {
					errors.addAll(objConverter.getErrors());
				}
			}
		}
	}
}