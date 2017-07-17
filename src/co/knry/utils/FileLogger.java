package co.knry.utils;

import java.io.Serializable;

public class FileLogger implements Logger, Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FileLogger logger;

	  //Prevent clients from using the constructor
	  private FileLogger() {
	  }

	  public static FileLogger getFileLogger() {
	    if (logger == null) {
	      logger = new FileLogger();
	      Error e = new Error("Linea","Descripcion","Campo");
	      logger.log(e);
	    }
	    return logger;
	  }

	  public synchronized void log(Error e) {
	    FileUtil futil = new FileUtil();
	    futil.writeToFile("log.txt",e.getLinea()+":"+e.getDescripcion()+":"+e.getCampo(), true, true);
	  }

	}