package com.mk.editor.entities;

import com.mk.editor.gui.utils.AppColor;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Scene3D extends SubScene {
  private World3D world;
  private Camera3D camera;
  private Object3D axisGroup = new Object3D();

  double mousePosX, mousePosY, mouseOldX, mouseOldY;
  private static final double CONTROL_MULTIPLIER = 0.1;
  private static final double SHIFT_MULTIPLIER = 3.0;

  public Scene3D(Group root, World3D world, Camera3D camera) {
    super(root, 0, 0, true, SceneAntialiasing.BALANCED);

    this.world = world;
    this.camera = camera;
    this.world.addChild(camera);

    root.getChildren().add(world);
    root.setDepthTest(DepthTest.ENABLE);

    this.buildBodySystem();
    this.buildAxes();
    this.handleMouse();

    this.setCamera(this.camera.getCamera());
  }

  private void buildBodySystem() {
    PhongMaterial whiteMaterial = new PhongMaterial();
    whiteMaterial.setDiffuseColor(Color.WHITE);
    whiteMaterial.setSpecularColor(Color.WHITE);

    Box box = new Box(50, 50, 50);
    box.setMaterial(whiteMaterial);
    Rotate rBox = new Rotate(0, 0, 0, 0, new Point3D(0, 1, 0));
    box.getTransforms().add(rBox);
    rBox.setAngle(45);

    this.world.addChild(box);
  }
  private void buildAxes() {
    final PhongMaterial redMaterial = new PhongMaterial();
    redMaterial.setDiffuseColor(Color.RED);

    final PhongMaterial greenMaterial = new PhongMaterial();
    greenMaterial.setDiffuseColor(Color.GREEN);

    final PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setDiffuseColor(Color.BLUE);

    final Cylinder xAxis = new Cylinder(1, 100);
    xAxis.setMaterial(redMaterial);
    xAxis.setRotate(-90);
    xAxis.setTranslateX(50);

    final Cylinder yAxis = new Cylinder(1, 100);
    yAxis.setMaterial(greenMaterial);
    yAxis.setTranslateY(50);

    final Cylinder zAxis = new Cylinder(1, 100);
    zAxis.setMaterial(blueMaterial);
    zAxis.setTranslateZ(50);
    zAxis.setRotationAxis(new Point3D(1, 0, 0));
    zAxis.setRotate(-90);

    ArrayList<Line> grid = new ArrayList<>();
    for (int i = -1000; i <= 1000; i += 25) {
      Line l1 = new Line(i, -1000, i, 1000);
      l1.setSmooth(false);
      l1.setStroke(i == 0 ? Color.GREEN : AppColor.GridColor);
      l1.setStrokeWidth(0.5);

      Line l2 = new Line(-1000, i, 1000, i);
      l2.setSmooth(false);
      l2.setStroke(i == 0 ? Color.RED : AppColor.GridColor);
      l2.setStrokeWidth(0.5);

      grid.add(l1);
      grid.add(l2);
    }

    axisGroup.getChildren().addAll(grid);
    axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);

    world.getChildren().addAll(axisGroup);
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
    });
    this.setOnMouseDragged(event -> {
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

      if (event.isPrimaryButtonDown()) {
        this.camera.translate(azimuth, altitude);
      }
      else if (event.isMiddleButtonDown()) {
        this.camera.rotate(azimuth, -altitude);
      }
    });
  }
}
