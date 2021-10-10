package com.mk.editor.gui.sidebar;

import com.mk.editor.controllers.sidebar.SidebarController;
import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.gui.MainRegion;
import com.mk.editor.utils.AppColor;
import com.mk.editor.utils.BorderPosition;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public final class Sidebar extends MainRegion {
  private final World3D world;
  private final Camera3D camera;
  private final VBox container;
  private final VBox properties;
  private final Outliner outliner;
  private final FXMLLoader cameraPropsLoader;
  private final FXMLLoader objectPropsLoader;
  private final SidebarController ctrl;

  public Sidebar(World3D world, Camera3D camera) {
    super(
      BorderPosition.LEFT,
      AppColor.BGPrimary,
      1,
      0,
      1,
      2
    );
    this.root.getChildren().add(this.content);
    this.world = world;
    this.camera = camera;
    this.outliner = new Outliner(world);
    this.properties = new VBox();
    this.container = new VBox();
    this.container.getChildren().addAll(this.outliner.getRoot(), this.properties);
    this.addContent(this.container);

    this.ctrl = new SidebarController(this.world, this.camera);

    this.cameraPropsLoader = new FXMLLoader(getClass().getResource("camera_properties.fxml"));
    this.cameraPropsLoader.setRoot(this.properties);
    this.cameraPropsLoader.setController(this.ctrl);

    this.objectPropsLoader = new FXMLLoader(getClass().getResource("object_properties.fxml"));
    this.objectPropsLoader.setRoot(this.properties);
    this.objectPropsLoader.setController(this.ctrl);
  }

  public void render() throws IOException {
    this.outliner.build();

    this.properties.setFillWidth(true);
    this.properties.setMaxWidth(this.root.getWidth());

    this.cameraPropsLoader.load();

    this.world.getPickedNode().addListener(event -> {
      this.properties.getChildren().clear();
      try {
        if (this.world.isSetPickedNode()) {
          this.ctrl.setState(SidebarController.State.Object);
          this.objectPropsLoader.load();
        } else {
          this.ctrl.setState(SidebarController.State.CameraWorld);
          this.cameraPropsLoader.load();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
