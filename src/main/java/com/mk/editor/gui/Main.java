package com.mk.editor.gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main {
  private final int WIDTH = 1240;
  private final int HEIGHT = 720;

  private Window window;
  private Scene scene;
  private GridPane root;
  private Region toolbar;
  private Region viewport;
  private Region sidebar;
  private Region statusbar;

  public Main(Stage stage) {
    this.window = new Window(stage);
    this.root = this.initRoot();

    // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));

    // Scene scene = new Scene(fxmlLoader.load(), 800, 600);

    // stage.setTitle("Hello!");
    // stage.setScene(scene);
    // stage.show();
    // Camera camera = new PerspectiveCamera();
    // scene.setCamera(camera);
    // scene.setOnScroll(event -> {
    // int direction = event.getDeltaY() > 0 ? 1 : -1;
    // camera.translateZProperty().set(camera.getTranslateZ() + 100 * direction);
    // });
    this.scene = new Scene(
        this.root,
        this.WIDTH,
        this.HEIGHT,
        true,
        SceneAntialiasing.BALANCED
    );
  }
  public Main show() {
    this.window.setScene(this.scene).show();
    return this;
  }

  private GridPane initRoot() {
    GridPane pane = new GridPane();

    // columns
    ColumnConstraints column1 = new ColumnConstraints(400, 300, Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true);
    pane.getColumnConstraints().add(column1);
    ColumnConstraints column2 = new ColumnConstraints(100, 200, 200);
    column2.setHalignment(HPos.RIGHT);
    pane.getColumnConstraints().add(column2);

    // rows
    RowConstraints row1 = new RowConstraints(80);
    row1.setValignment(VPos.TOP);
    pane.getRowConstraints().add(row1);
    RowConstraints row2 = new RowConstraints(400, 400, Double.MAX_VALUE, Priority.ALWAYS, VPos.TOP, true);
    pane.getRowConstraints().add(row2);
    RowConstraints row3 = new RowConstraints(40);
    row3.setValignment(VPos.TOP);
    pane.getRowConstraints().add(row3);

//    Label first = new Label("Toolbar");
//    Label second = new Label("3D viewport");
//    Label third = new Label("Sidebar");
//    Label forth = new Label("Statusbar");
//
//    pane.add(first, 0, 0);
//    pane.add(second, 0, 1);
//    pane.add(third, 1, 0, 1, 2);
//    pane.add(forth, 0, 2, 2, 1);

    return pane;
  }
}
