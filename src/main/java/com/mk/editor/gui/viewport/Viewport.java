package com.mk.editor.gui.viewport;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.Scene3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.gui.MainRegion;
import com.mk.editor.utils.AppColor;

import javafx.scene.Group;
import javafx.stage.Stage;

// Класс создания 3D сцены
public final class Viewport extends MainRegion {
  private final Scene3D scene; // сцена

  /**
   * Конструктор
   * @param world - мир
   * @param camera - камера
   * @param stage - состояние приложения
   */
  public Viewport(World3D world, Camera3D camera, Stage stage) {
    super(AppColor.BGTransparent, 0, 1);

    this.scene = new Scene3D(new Group(), world, camera, stage);
    this.scene.widthProperty().bind(this.root.widthProperty());
    this.scene.heightProperty().bind(this.root.heightProperty());
    this.scene.setFill(AppColor.BGScene);
  }

  /**
   * Отрисовывает сцену
   */
  public void render() {
    this.root.getChildren().add(this.scene);
    this.scene.render();
  }
}
