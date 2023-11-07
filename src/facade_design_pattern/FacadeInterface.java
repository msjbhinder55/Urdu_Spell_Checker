package facade_design_pattern;

import java.io.File;

public interface FacadeInterface {

  /*
   * folderPath function to give the path of folder
   * */
  public void folderPath();

  /*
   * readFile function read XML file and give file data
   * */
  public void readFile(String path);

  /*
   * insertData function to store file data into database
   * */
  public void insertData(File f);

  /*
   * insertWords function to store words into database
   * */
  public void insertWords();

  /*
   * addManualWord function store the word into database given by user
   * */
  public void addManualWord(String string);

}
