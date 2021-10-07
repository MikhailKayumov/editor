package com.mk.editor.gui.viewport;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.Scene3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.gui.MainRegion;
import com.mk.editor.utils.AppColor;
import javafx.scene.Group;

public class Viewport extends MainRegion {
  private final Scene3D scene;

  public Viewport(World3D world, Camera3D camera) {
    super(AppColor.BGTransparent, 0, 1);

    this.scene = new Scene3D(new Group(), world, camera);
    this.scene.widthProperty().bind(this.root.widthProperty());
    this.scene.heightProperty().bind(this.root.heightProperty());
    this.scene.setFill(AppColor.BGScene);
  }

  public void render() {
    this.root.getChildren().add(this.scene);
    this.scene.render();
  }
}
