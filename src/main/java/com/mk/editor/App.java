package com.mk.editor;

import com.mk.editor.gui.Main;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      new Main(primaryStage).show();
    } catch (Exception e) {
      System.out.println("!!! ============= Error ============= !!!");
      System.out.println(e.getMessage());
      System.out.println(e.getClass());
      System.out.println(e.getSuppressed());
      System.out.println(e.getStackTrace());
    }
  }

  public static void run() {
    System.out.println("App is running.");
    launch();
  }
}