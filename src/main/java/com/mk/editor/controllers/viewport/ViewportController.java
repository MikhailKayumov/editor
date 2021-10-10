package com.mk.editor.controllers.viewport;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.Scene3D;
import com.mk.editor.entities.World3D;

import javafx.stage.Stage;

public final class ViewportController {
  private final Stage stage;
  private final World3D world;
  private final Scene3D scene;
  private final Camera3D camera;

  public ViewportController(Scene3D scene, World3D world, Camera3D camera, Stage stage) {
    this.stage = stage;
    this.world = world;
    this.scene = scene;
    this.camera = camera;
  }
}
