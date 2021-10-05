package com.mk.editor.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

final public class Window {
  private final int MIN_WIDTH = 1240;
  private final int MIN_HEIGHT = 720;

  private final Stage stage;

  public Window(Stage primaryStage) {
    this.stage = primaryStage;

    // this.stage.setMaximized(true);
    this.stage.setMinWidth(this.MIN_WIDTH);
    this.stage.setMinHeight(this.MIN_HEIGHT);

    this.stage.setTitle("Simple 3D Editor");
  }

  public void show(Scene scene) {
    this.stage.setScene(scene);
    this.stage.show();
  }
}
