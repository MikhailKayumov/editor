package com.mk.editor;

import com.mk.editor.gui.Main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

// Класс загрузчик приложения
public final class App extends Application {
  /**
   * Начинает запуск приложения
   * @param primaryStage - основное состояние
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      new Main(primaryStage).render();
    } catch (Exception e) {
      System.out.println("!!! ============= Error ============= !!!");
      System.out.println(e.getMessage());
      System.out.println(e.getClass());
      System.out.println(Arrays.toString(e.getSuppressed()));
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }

  /**
   * Запускает приложение
   */
  public static void run() {
    System.out.println("App is running.");
    launch();
  }
}