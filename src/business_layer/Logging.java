package business_layer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {

  // Create a logger 
  static Logger logger = Logger.getLogger(Logging.class.getName());

  public static void main(String[] args) {

    // Set the Logging level to info 
    logger.setLevel(Level.INFO);

    // Log a message at the info level 
    logger.info("Logging an info message.");

    // Log a message at the warning level 
    logger.warning("Logging a warning message.");

    // Log a message at the severe level 
    logger.severe("Logging a severe message.");
  }
}