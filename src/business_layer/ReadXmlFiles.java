package business_layer;

import database_layer.DataBase;
import facade_design_pattern.FacadeInterface;

import java.io.File;

public class ReadXmlFiles implements FacadeInterface {

  //DataBase
  private DataBase db;

  public ReadXmlFiles() throws Exception {

    db = new DataBase();
  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make folderPath function to get folder path
   * */
  public void folderPath() {

    File file = new File("D:\\FAST NUCES\\5th Semester\\Software Construction and Develpoment\\Spell Checker\\makhzan-master\\makhzan-master\\text");
    String path = file.toString();
    try {
      readFile(path);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /*
   * author: Fahad-Bin-Imran 
   * (20F-0194)
   * 
   * Make readFile function to  read file data
   * */
  public void readFile(String path) {
    File file = new File(path);
    File[] fileArray = file.listFiles();
    for (File f: fileArray) {
      System.out.println(f.toString());
      db.insertData(f);

    }
  }

  @Override
  public void insertData(File f) {
    // TODO Auto-generated method stub
    System.out.println("ReadXmlFiles::insertData()");
  }

  @Override
  public void insertWords() {
    // TODO Auto-generated method stub
    System.out.println("ReadXmlFiles::insertWords()");
  }

  @Override
  public void addManualWord(String string) {
    // TODO Auto-generated method stub
    System.out.println("ReadXmlFiles::addManualWord()");
  }
}