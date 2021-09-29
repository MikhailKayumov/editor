package com.mk.editor.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

final public class Window {
  private final int MIN_WIDTH = 1240;
  private final int MIN_HEIGHT = 720;
  private final boolean MAXIMIZED = true;
  private final Stage stage;
  private String title = "Simple 3D Editor";
  private Scene scene;

  public Window(Stage primaryStage) {
    this.stage = primaryStage;
    this.init();
  }
  public Window setScene(Scene scene) {
    this.scene = scene;
    return this;
  }
  public Window show() {
    this.stage.setScene(this.scene);
    this.stage.show();
    return this;
  }

  private void init() {
    // this.stage.setMaximized(this.MAXIMIZED);

    this.stage.setMinWidth(this.MIN_WIDTH);
    this.stage.setMinHeight(this.MIN_HEIGHT);

    this.stage.setTitle(this.title);

    // Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    // System.out.println(primaryScreenBounds.toString());
    // set Stage boundaries to visible bounds of the main screen
    // stage.setX(primaryScreenBounds.getMinX());
    // stage.setY(primaryScreenBounds.getMinY());
    // stage.setWidth(primaryScreenBounds.getWidth());
    // stage.setHeight(primaryScreenBounds.getHeight());
  }
}
