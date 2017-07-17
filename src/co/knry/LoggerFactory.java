package co.knry;

public class LoggerFactory {

  public Logger getLogger() {
      return FileLogger.getFileLogger();
  }

}