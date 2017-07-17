package co.knry.utils;

public class LoggerFactory {

  public Logger getLogger() {
      return FileLogger.getFileLogger();
  }

}