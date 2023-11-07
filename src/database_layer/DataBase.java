package database_layer;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import facade_design_pattern.FacadeInterface;

public class DataBase implements FacadeInterface {
	
	// Create a logger 
    static Logger logger = Logger.getLogger(DataBase.class.getName());
  Connection con = null;
  Statement stmt = null;
  //private Model model;
  ResultSet rs = null;
  String content;
  private String title;
  private String author;
  private String paragraph;
  Map < String, Integer > mp;

  public DataBase() throws Exception {

    //model = new Model();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");
  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make insertData function to connect and insert data into Datbase
   * */
  public void insertData(File f) {

    DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();

    try {

      DocumentBuilder build = fact.newDocumentBuilder();
      Document document = build.parse(f);
      document.getDocumentElement().normalize();

      title = document.getElementsByTagName("title").item(0).getTextContent();
      author = document.getElementsByTagName("name").item(0).getTextContent();
      paragraph = document.getElementsByTagName("section").item(0).getTextContent();

      title = title.replaceAll("(?U)[\\W_]+", " ");
      title = title.replaceAll("[a-zA-Z]", " ");
      title = title.replaceAll("[0-9]", " ");
      title = title.trim();
      author = author.replaceAll("(?U)[\\W_]+", " ");
      author = author.replaceAll("[a-zA-Z]", " ");
      author = author.replaceAll("[0-9]", " ");
      author = author.trim();
      paragraph = paragraph.replaceAll("(?U)[\\W_]+", " ");
      paragraph = paragraph.replaceAll("[a-zA-Z]", " ");
      paragraph = paragraph.replaceAll("[0-9]", " ");

      paragraph = paragraph.trim();

      stmt = con.createStatement();

      String insert = "INSERT INTO `content`(`title`, `author`, `paragraph`) VALUES ('" + title + "' ,'" + author + "','" + paragraph + "')";
      stmt.executeUpdate(insert);

      System.out.println("Inserted");
      System.out.println("Title:" + title);
      System.out.println("Author" + author);
      System.out.println("Content" + paragraph);

    } catch (Exception e) {

      System.out.println(e.getMessage());
   // Log a message at the warning level 
      logger.warning("Logging a warning message."); 
    }

  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make insertWords function to connect and insert data
   * */
  public void insertWords() {

    String str = fetchData();
    Map < String, Integer > mp = new HashMap < > ();

    // Splitting to find the word
    String arr[] = str.split(" ");

    // Loop to iterate over the words
    for (int i = 0; i < arr.length; i++) {
      // Condition to check if the
      // array element is present
      // the hash-map
      if (mp.containsKey(arr[i])) {
        mp.put(arr[i], mp.get(arr[i]) + 1);
      } else {
        mp.put(arr[i], 1);
      }
    }

    // Loop to iterate over the
    // elements of the map
    for (Entry < String, Integer > entry: mp.entrySet()) {
      //System.out.println(entry.getKey() + " = " + entry.getValue());

      try {

        System.out.println("Connected");

        stmt = con.createStatement();

        String insert = "INSERT INTO `words`(`word`, `frequency`) VALUES ('" + entry.getKey() + "' ,'" + entry.getValue() + "')";
        stmt.executeUpdate(insert);

        System.out.println("Inserted");

      } catch (Exception e) {

        System.out.println(e.getMessage());
      }
    }

  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make fetchData function to get data from database
   * */

  public String fetchData() {

    try {

      System.out.println("Connected");

      stmt = con.createStatement();

      String fetch = "SELECT `paragraph` FROM `content`";
      rs = stmt.executeQuery(fetch);

      while (rs.next()) {

        content = rs.getString("paragraph");
        //System.out.println(content);
      }

      rs.close();

      System.out.println("Fetched");

    } catch (Exception e) {

      System.out.println(e.getMessage());
    }
    return content;

  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make addManualWord function to send user added words to database
   * */

  public void addManualWord(String string) {

    try {

      System.out.println("Connected");

      stmt = con.createStatement();

      String fetch = "SELECT `word` FROM `words`";
      rs = stmt.executeQuery(fetch);

      String word = null;
      while (rs.next()) {

        word = rs.getString("word");
        System.out.println(word);
      }

      rs.close();

      System.out.println("Fetched");

      System.out.println("Connected");

      stmt = con.createStatement();

      if (string.equals(word)) {

        String update = "UPDATE `words` SET `frequency`=  + (frequency  + 1) WHERE 'wid' = + 'wid' + ';' ";
        stmt.executeUpdate(update);

        System.out.println("updated");
      } else {

        String insert = "INSERT INTO `words`(`word`, `frequency`) VALUES ('" + string + "' ,'" + 1 + "')";

        stmt.executeUpdate(insert);

        System.out.println("Inserted");

      }

    } catch (Exception e) {

      System.out.println(e.getMessage());
    }

  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make addDataIntoJTable function to display data in the JTable
   * */

  public void addDataIntoJTable() throws SQLException {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");

    try {

      System.out.println("Connected");

      stmt = con.createStatement();

      String fetch = "SELECT * FROM `words`";
      rs = stmt.executeQuery(fetch);
      rsmd = rs.getMetaData();
      JTable jt1 = new JTable();
      DefaultTableModel model = (DefaultTableModel) jt1.getModel();

      int col = rsmd.getColumnCount();
      String colName[] = new String[col];
      for (int i = 0; i < col; i++) {
        colName[i] = rsmd.getColumnName(i + 1);
        model.setColumnIdentifiers(colName);
        String wid, word, frequency;
        while (rs.next()) {

          wid = rs.getString(1);
          word = rs.getString(2);
          frequency = rs.getString(3);

          String[] row = {
            wid,
            word,
            frequency
          };

          model.addRow(row);

        }
      }

      rs.close();

      System.out.println("Fetched");

    } catch (Exception e) {

      System.out.println(e.getMessage());
    }

  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make updateTableData function to update the JTable Data and DataBase Data
   * */

  public void updateTableData(String id, String word, String new_Word) {
    try {

      System.out.println("Connected");

      stmt = con.createStatement();

      String update = "UPDATE words SET word=? WHERE wid=?";

      PreparedStatement pstmt = con.prepareStatement(update);

      String wid = id;
      String newWrd = new_Word;

      pstmt.setString(1, newWrd);
      pstmt.setString(2, wid);

      pstmt.executeUpdate();
      System.out.println("updated");

    } catch (Exception e) {

      System.out.println(e.getMessage());
   // Log a message at the warning level 
      logger.warning("Logging a warning message."); 
    }

  }

  @Override
  public void folderPath() {
    // TODO Auto-generated method stub
    System.out.println("DataBase::folderPath()");
  }

  @Override
  public void readFile(String path) {
    // TODO Auto-generated method stub
    System.out.println("DataBase::readFile()");
  }

}
