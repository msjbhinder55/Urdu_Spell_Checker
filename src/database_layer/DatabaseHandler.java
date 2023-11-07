package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

  public List < String[] > getData() {

    // Create a list to store the results
    List < String[] > results = new ArrayList < > ();

    try {
      // Connect to the database using JDBC
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");

      // Execute the SQL query to retrieve wid and word from the table
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT wid, word FROM words");

      // Iterate over the rows of the result set and add each wid and word to the list
      while (rs.next()) {
        int wid = rs.getInt("wid");
        String word = rs.getString("word");
        results.add(new String[] {
          String.valueOf(wid), word
        });
      }

      // Close the connection and statement
      stmt.close();
      conn.close();

    } catch (SQLException e) {
      // Catch any SQL exceptions and print the stack trace
      e.printStackTrace();
      // Log a message at the warning level 
      //logger.warning("Logging a warning message.");
    }
    return results;
  }

  public List < String[] > generateMutants() {
    // Connect to the database using JDBC

    // Create a list to store the results
    List < String[] > mutants = new ArrayList < > ();

    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");

      // Execute the SQL query to generate mutants and store them in the mutant table
      Statement stmt = conn.createStatement();
      stmt.executeUpdate("INSERT INTO mutant (mutant) SELECT concat(word, '_mutant') FROM word");

      // Retrieve the mutants from the mutant table
      ResultSet rs = stmt.executeQuery("SELECT id, word FROM spellchecker");

      // Iterate over the rows of the result set and add each mutant_id and mutant to the list
      while (rs.next()) {
        int mutantId = rs.getInt("id");
        String mutant = rs.getString("word");
        mutants.add(new String[] {
          String.valueOf(mutantId), mutant
        });
      }

      // Close the connection and statement
      stmt.close();
      conn.close();

    } catch (SQLException e) {
      // Catch any SQL exceptions and print the stack trace
      e.printStackTrace();
      // Log a message at the warning level 
      //logger.warning("Logging a warning message.");
    }

    return mutants;
  }
}