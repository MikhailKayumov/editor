package com.mk.editor.gui;

import com.mk.editor.gui.sidebar.Sidebar;
import com.mk.editor.gui.statusbar.Statusbar;
import com.mk.editor.gui.toolbar.Toolbar;
import com.mk.editor.gui.viewport.Viewport;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main {

  private final Window window;
  private final Scene scene;
  private final GridPane root;

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
      1240,
      720,
      true,
      SceneAntialiasing.BALANCED
    );
  }
  public void show() {
    this.fillRoot();
    this.window.setScene(this.scene).show();
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
    RowConstraints row3 = new RowConstraints(20);
    row3.setValignment(VPos.TOP);
    pane.getRowConstraints().add(row3);

    return pane;
  }
  private void fillRoot() {
    MainRegion[] nodes = {
      new Toolbar(),
      new Sidebar(),
      new Statusbar(),
      new Viewport()
    };

    for (MainRegion node: nodes) {
      this.root.add(
        node.getContent(),
        node.getColumnId(),
        node.getRowId(),
        node.getColspan(),
        node.getRowspan()
      );
    }

  }
}
