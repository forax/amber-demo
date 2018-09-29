package fr.umlv.concisemethod;

public interface Logger {
  public enum Level {
    ERROR, WARNING
  }
  
  void log(Level level, String message);
  
  default void error(String message) -> log(Level.ERROR, message);
  
  default void warning(String message) -> log(Level.WARNING, message);
  
  public static void main(String[] args) -> ((Runnable)() -> {
      Logger logger = (level, message) -> System.err.println("[" + level + "] " + message);
      logger.warning("warning !");
      logger.error("error !");
    }).run();
}
