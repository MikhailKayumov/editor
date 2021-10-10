package com.mk.editor.entities;

import com.mk.editor.controllers.viewport.ViewportController;
import com.mk.editor.shapes.CubeMesh;
import com.mk.editor.shapes.BaseMesh;
import com.mk.editor.shapes.CylinderMesh;
import com.mk.editor.shapes.SphereMesh;

import javafx.scene.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public final class Scene3D extends SubScene {
  private Stage stage;
  private World3D world;
  private Camera3D camera;
  private ViewportController ctrl;

  double mousePosX, mousePosY, mouseOldX, mouseOldY;
  private static final double CONTROL_MULTIPLIER = 0.1;
  private static final double SHIFT_MULTIPLIER = 3.0;

  public Scene3D(Group root, World3D world, Camera3D camera, Stage stage) {
    super(root, 0, 0, true, SceneAntialiasing.BALANCED);

    this.stage = stage;
    this.world = world;
    this.camera = camera;
    this.ctrl = new ViewportController(this, this.world, this.camera, this.stage);

    this.world.setScene(this);
    this.world.addChild(camera);

    this.setDepthTest(DepthTest.ENABLE);
    this.setCamera(this.camera.getCamera());

    // this.buildBodySystem();
    this.handleMouse();
  }

  public void render() {
    ((Group)this.getRoot()).getChildren().add(this.world);
  }
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

  private void buildBodySystem() {
    CubeMesh cube = new CubeMesh();
    cube.setMaterial(Color.rgb(125, 50, 235));
    cube.addTz(30);
    cube.addTx(70);
    cube.setSize(50);

    SphereMesh sphere = new SphereMesh();
    sphere.setMaterial(Color.rgb(235, 50, 125));
    sphere.addTz(30);
    sphere.setRadius(25);

    CylinderMesh cylinder = new CylinderMesh();
    cylinder.setMaterial(Color.rgb(56, 75, 210));
    cylinder.addTz(30);
    cylinder.setRadius(25);
    cylinder.setHeight(50);
    cylinder.addTx(-70);

    this.world.addMesh(cube);
    this.world.addMesh(sphere);
    this.world.addMesh(cylinder);
  }

  private void handleMouse() {
    this.setOnScroll(event -> {
      double difference = 20.0 * (event.getDeltaY() > 0 ? 1 : -1); // MOUSE_SPEED
      if (event.isControlDown()) difference *= CONTROL_MULTIPLIER; // 0.1
      else if (event.isAltDown()) difference *= SHIFT_MULTIPLIER;// 10.0
      this.camera.zoom(difference);
    });

    this.setOnMousePressed(event -> {
      mousePosX = mouseOldX = event.getSceneX();
      mousePosY = mouseOldY = event.getSceneY();
      this.requestFocus();
    });
    this.setOnMouseDragged(event -> {
      if (!event.isMiddleButtonDown()) return;

      mouseOldX = mousePosX;
      mouseOldY = mousePosY;
      mousePosX = event.getSceneX();
      mousePosY = event.getSceneY();

      double speed = 0.2;
      if (event.isControlDown()) speed *= CONTROL_MULTIPLIER; // 0.1
      else if (event.isAltDown()) speed *= SHIFT_MULTIPLIER;// 3.0

      double posX = -(mousePosX - mouseOldX);
      double posY = mousePosY - mouseOldY;

      double azimuth = posX * speed;
      double altitude = posY * speed;

      if (event.isShiftDown()) {
        this.camera.translate(azimuth, altitude);
      } else {
        this.camera.rotate(azimuth, -altitude);
      }
    });
    this.setOnMouseClicked(event -> {
      if (event.getButton() == MouseButton.PRIMARY) {
        Node res = event.getPickResult().getIntersectedNode();
        if (res != null && res.getParent() instanceof BaseMesh) {
          this.world.setPickedNode((BaseMesh)res.getParent());
        } else {
          this.world.delPickedNode();
        }
      }
    });

    this.setOnKeyPressed(event -> {
      System.out.println("Focused is set on viewport");
      System.out.println("Event code: " + event.getCode());
      System.out.println("Event char: " + event.getCharacter());
      System.out.println("Event text: " + event.getText());

      if (event.getCode() == KeyCode.X) {
        if (event.isAltDown()) this.camera.resetZoom();
        else if (event.isControlDown()) this.camera.resetRotate();
        else if (event.isShiftDown()) this.camera.resetTranslate();
        else this.camera.reset();
      } else if (event.getCode() == KeyCode.DELETE) {
        this.world.deleteMesh();
      } else if (event.getCode() == KeyCode.PRINTSCREEN) {
        this.makeImg();
      }
    });
  }

}
