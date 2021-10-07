package com.mk.editor.entities;

import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class Scene3D extends SubScene {
  private World3D world;
  private Camera3D camera;

  double mousePosX, mousePosY, mouseOldX, mouseOldY;
  private static final double CONTROL_MULTIPLIER = 0.1;
  private static final double SHIFT_MULTIPLIER = 3.0;

  public Scene3D(Group root, World3D world, Camera3D camera) {
    super(root, 0, 0, true, SceneAntialiasing.BALANCED);

    this.world = world;
    this.camera = camera;
    this.world.addChild(camera);

    this.setDepthTest(DepthTest.ENABLE);
    this.setCamera(this.camera.getCamera());

    this.buildBodySystem();
    this.handleMouse();
  }

  public void render() {
    ((Group)this.getRoot()).getChildren().add(this.world);
  }

  private void buildBodySystem() {
    PhongMaterial whiteMaterial = new PhongMaterial(Color.WHITE);
    // whiteMaterial.setSpecularColor(Color.WHITE);

    Box box = new Box(54, 54, 54);
    box.setMaterial(whiteMaterial);
    Rotate rBox = new Rotate(0, 0, 0, 0, new Point3D(0, 0, 1));
    box.getTransforms().add(rBox);
    box.setTranslateZ(34);
    box.setTranslateX(40);
    box.setTranslateY(-60);
    rBox.setAngle(30);

    Box box2 = new Box(54, 54, 54);
    box2.setMaterial(whiteMaterial);
    Rotate rBox2 = new Rotate(0, 0, 0, 0, new Point3D(0, 0, 1));
    box2.getTransforms().add(rBox);
    box2.setTranslateZ(150);
    box2.setTranslateX(40);
    box2.setTranslateY(-60);
    rBox2.setAngle(30);

    this.world.addMesh(box, box2);
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
        if (res != null && res.getParent() == this.world.getMeshes()) {
          this.world.setPickedNode(res);
        } else {
          this.world.delPickedNode();
        }
      }
      // event.consume();
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
      }
    });
  }

  void setState(PickResult result) {
    if (result.getIntersectedNode() == null) {
      System.out.println("Scene");
      System.out.println(result.getIntersectedNode());
      System.out.println(result.getIntersectedPoint());
      System.out.println(String.format("%.1f", result.getIntersectedDistance()));
    } else {
      System.out.println(result.getIntersectedNode());
      System.out.println(result.getIntersectedPoint());
      System.out.println(String.format("%.1f", result.getIntersectedDistance()));
    }
    System.out.println("========================================");
  }
}
