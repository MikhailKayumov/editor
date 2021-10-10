package com.mk.editor.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

// Класс окна приложения
final public class Window {
  private final int MIN_WIDTH = 1240; // минимальная ширина
  private final int MIN_HEIGHT = 720; // минимальная высота

  private final Stage stage; // основное состояние

  /**
   * Конструктор
   * @param primaryStage - основное состояние
   */
  public Window(Stage primaryStage) {
    this.stage = primaryStage;

    // this.stage.setMaximized(true);
    this.stage.setMinWidth(this.MIN_WIDTH);
    this.stage.setMinHeight(this.MIN_HEIGHT);

    this.stage.setTitle("Simple 3D Editor");
  }

  /**
   * Включает отображение основных панелей
   * @param scene - сцена с основными панелями
   */
  public void show(Scene scene) {
    this.stage.setScene(scene);
    this.stage.show();
  }
}
