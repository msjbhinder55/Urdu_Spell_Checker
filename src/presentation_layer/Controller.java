package presentation_layer;

import presentation_layer.AppGui;

import java.sql.SQLException;

import business_layer.ReadXmlFiles;
import database_layer.DataBase;

public class Controller {

  private ReadXmlFiles model;
  private AppGui view;
  private DataBase db;

  public Controller() throws Exception {

    model = new ReadXmlFiles();
    db = new DataBase();

  }

  //path function
  public void path() {
    model.folderPath();
  }

  public void sendWords() {
    db.insertWords();
  }

  public void sendmanualWords(String string) {
    db.addManualWord(string);
  }

  public void showDataInTable() throws SQLException {
    db.addDataIntoJTable();
  }

  public void updateData(String id, String word, String new_Word) {
    db.updateTableData(id, word, new_Word);
  }

}
