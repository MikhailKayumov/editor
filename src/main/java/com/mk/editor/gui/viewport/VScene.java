package com.mk.editor.gui.viewport;

import com.mk.editor.gui.utils.AppColor;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class VScene extends SubScene {
  private static double CAMERA_INITIAL_X_ANGLE = -10.0;
  private static double CAMERA_INITIAL_Y_ANGLE = 0.0;
  private static double AXIS_LENGTH = 250.0;
  private static double MOUSE_SPEED = 0.1;
  private static double ROTATION_SPEED = 2.0;

  final XformWorld world = new XformWorld();
  final XformCamera cameraXform = new XformCamera();
  private static final double CAMERA_INITIAL_DISTANCE = -10;
  private static final double CAMERA_NEAR_CLIP = 0.01;
  private static final double CAMERA_FAR_CLIP = 10000.0;

  double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;

  PerspectiveCamera camera = new PerspectiveCamera(true);

  private Group root;

  public VScene(Group root) {
    super(root, 0, 0, true, SceneAntialiasing.BALANCED);
    this.setFill(AppColor.BGScene);

    this.root = root;
    this.root.setDepthTest(DepthTest.ENABLE);
    this.root.getChildren().add(world);

    buildCamera();
    buildBodySystem();
    handleMouse();
    this.setCamera(camera);
  }

  private void buildCamera() {
    this.root.getChildren().add(cameraXform);
    cameraXform.getChildren().add(camera);
    camera.setNearClip(CAMERA_NEAR_CLIP);
    camera.setFarClip(CAMERA_FAR_CLIP);
    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
  }
  private void buildBodySystem() {
    PhongMaterial whiteMaterial = new PhongMaterial();
    whiteMaterial.setDiffuseColor(Color.WHITE);
    whiteMaterial.setSpecularColor(Color.LIGHTBLUE);

    Box box = new Box(5, 5, 3);
    box.setMaterial(whiteMaterial);

    world.getChildren().add(box);
  }
  private void handleMouse() {
    System.out.println("handleMouse");

    this.setOnScroll(event -> {
      int direction = event.getDeltaY() > 0 ? 1 : -1;
      camera.translateZProperty().set(camera.getTranslateZ() + 100 * direction);
    });
//    this.setOnMousePressed(ViewportHandlers::mousePressed);
    this.setOnMousePressed(event -> {
      mousePosX = event.getSceneX();
      mousePosY = event.getSceneY();
      mouseOldX = event.getSceneX();
      mouseOldY = event.getSceneY();
    });
    this.setOnMouseDragged((MouseEvent me) -> {
      mouseOldX = mousePosX;
      mouseOldY = mousePosY;
      mousePosX = me.getSceneX();
      mousePosY = me.getSceneY();
      mouseDeltaX = (mousePosX - mouseOldX);
      mouseDeltaY = (mousePosY - mouseOldY);
      if (me.isMiddleButtonDown()) {
        if (me.isShiftDown()) {

        } else {
          // this is done when the mouse is dragged and each rotation is
          // performed in coordinates, that are rotated with the camera.
          cameraXform.ry.setAngle(cameraXform.ry.getAngle() + mouseDeltaX * 0.2);
          cameraXform.rx.setAngle(cameraXform.rx.getAngle() - mouseDeltaY * 0.2);
        }
      }
    });
  }
}

class XformWorld extends Group {
  final Translate t = new Translate(0.0, 0.0, 0.0);
  final Rotate rx = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
  final Rotate ry = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
  final Rotate rz = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);

  public XformWorld() {
    super();
    this.getTransforms().addAll(t, rx, ry, rz);
  }
}
class XformCamera extends Group {
  final Translate t = new Translate(0.0, 0.0, 0.0);
  final Rotate rx = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
  final Rotate ry = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
  final Rotate rz = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);

  public XformCamera() {
    super();
    this.getTransforms().addAll(t, rx, ry, rz);
  }
}