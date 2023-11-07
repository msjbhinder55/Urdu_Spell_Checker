package facade_design_pattern;

import java.io.File;

import business_layer.ReadXmlFiles;
import database_layer.DataBase;

public class Facade implements FacadeInterface {

  private ReadXmlFiles read;
  private DataBase db;

  public Facade() throws Exception {

    read = new ReadXmlFiles();
    db = new DataBase();
  }
  /*
   * folderPath function to give the path of folder
   * */
  public void folderPath() {
    read.folderPath();

  }

  /*
   * readFile function read XML file and give file data
   * */
  public void readFile(String path) {
    read.readFile(path);
  }

  /*
   * insertData function to store file data into database
   * */
  public void insertData(File f) {
    db.insertData(f);

  }

  /*
   * insertWords function to store words into database
   * */
  public void insertWords() {
    db.insertWords();
  }

  /*
   * addManualWord function store the word into database given by user
   * */
  public void addManualWord(String string) {
    db.addManualWord(string);
  }

}