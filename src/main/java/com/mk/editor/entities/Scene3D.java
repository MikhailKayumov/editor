package com.mk.editor.entities;

import com.mk.editor.controllers.viewport.ViewportController;

import javafx.scene.*;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public final class Scene3D extends SubScene {
  private final Stage stage; // состояние приложения
  private final World3D world; // мир сцены

  /**
   * Конструктор
   * @param root - корневой узел сцены
   * @param world - мир сцены
   * @param camera - камера сцены
   * @param stage - состояние приложения
   */
  public Scene3D(Group root, World3D world, Camera3D camera, Stage stage) {
    super(root, 0, 0, true, SceneAntialiasing.BALANCED);

    this.stage = stage;
    this.world = world;

    this.world.setScene(this);
    this.world.addChild(camera);

    this.setDepthTest(DepthTest.ENABLE);
    this.setCamera(camera.getCamera());

    new ViewportController(this, this.world, camera);
  }

  /**
   * Отрисовывает сцены добавляя мир в нее
   */
  public void render() {
    ((Group)this.getRoot()).getChildren().add(this.world);
  }

  /**
   * Создает изображение сцены
   */
  public void makeImg() {
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Save scene");
    chooser.setInitialFileName("scene.png");
    chooser.setInitialDirectory(new File(System.getProperty("user.home")));
    chooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg"),
      new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
      new FileChooser.ExtensionFilter("PNG Files", "*.png"),
      new FileChooser.ExtensionFilter("GIF Files", "*.gif")
    );

    File outFile = chooser.showSaveDialog(this.stage);
    if (outFile != null) {
      try {
        WritableImage image = this.snapshot(new SnapshotParameters(), null);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outFile);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
