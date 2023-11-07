package facade_design_pattern;

import java.util.List;

import database_layer.DatabaseHandler;

public class DatabaseFacade {
  private DatabaseHandler databaseHandler;

  public DatabaseFacade() {
    this.databaseHandler = new DatabaseHandler();
  }

  public List < String[] > getData() {
    return databaseHandler.getData();
  }

  public List < String[] > generateMutants() {
    return databaseHandler.generateMutants();
  }
}

//DatabaseFacade facade = new DatabaseFacade();
//List<String[]> data = facade.getData();