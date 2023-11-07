package main;

import presentation_layer.Controller;
import presentation_layer.AppGui;

public class Main {

  public static void main(String[] args) {

    try {
      new AppGui();
      new Controller();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
