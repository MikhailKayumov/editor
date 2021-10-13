package com.mk.editor.controllers.viewport;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.Scene3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.shapes.BaseMesh;

import javafx.scene.Node;
import javafx.scene.input.*;

public final class ViewportController {
  // скорость перемещения камеры
  private static final double MOUSE_TRANSLATE_SPEED = 0.035;
  // скорость вращения камеры
  private static final double MOUSE_ROTATE_SPEED = 0.18;
  // скорость зума
  private static final double MOUSE_ZOOM_SPEED = 3.87;
  // увеличение скорости при нажатом CTRL
  private static final double CONTROL_MULTIPLIER = 3.85;
  // уменьшение скорости при нажатом ALT
  private static final double ALT_MULTIPLIER = 0.35;

  private double mousePosX; // позиция мыши по X
  private double mousePosY; // позиция мыши по Y
  private double mouseOldX; // предыдущая позиция мыши по X
  private double mouseOldY; // предыдущая позиция мыши по Y

  private final World3D world; // мир
  private final Scene3D scene; // сцена
  private final Camera3D camera; // камера

  /**
   * Конструктор
   * @param scene - сцена
   * @param world - мир
   * @param camera - камера
   */
  public ViewportController(Scene3D scene, World3D world, Camera3D camera) {
    this.world = world;
    this.scene = scene;
    this.camera = camera;

    this.initMouse();
    this.initKeyboard();
  }

  /**
   * Инициализация мыши
   */
  private void initMouse() {
    this.scene.setOnScroll(this::onScroll);
    this.scene.setOnMousePressed(this::onMousePressed);
    this.scene.setOnMouseDragged(this::onMouseDragged);
    this.scene.setOnMouseClicked(this::onMouseClicked);
  }

  /**
   * Обработчик колеса мыши
   * @param event - события поворота колеса мыши
   */
  private void onScroll(ScrollEvent event) {
    double difference = MOUSE_ZOOM_SPEED * (event.getDeltaY() > 0 ? 1 : -1);

    if (event.isControlDown()) difference *= CONTROL_MULTIPLIER;
    else if (event.isAltDown()) difference *= ALT_MULTIPLIER;

    this.camera.zoom(difference);
  }
  /**
   * Обработчик нажатия кнопок мыши. Не завязан на определенную кнопку.
   * Срабатывает до отпускания кнопки
   * @param event - события нажатия кнопки мыши
   */
  private void onMousePressed(MouseEvent event) {
    mousePosX = mouseOldX = event.getSceneX();
    mousePosY = mouseOldY = event.getSceneY();
    this.scene.requestFocus();
  }
  /**
   * Обработчик движения мыши с нажатой кнопкой.
   * Завязан на среднюю кнопку (колесо).
   * @param event - события нажатия кнопки мыши
   */
  private void onMouseDragged(MouseEvent event) {
    if (!event.isMiddleButtonDown()) return;

    mouseOldX = mousePosX;
    mouseOldY = mousePosY;
    mousePosX = event.getSceneX();
    mousePosY = event.getSceneY();

    double speed = event.isShiftDown() ? MOUSE_TRANSLATE_SPEED : MOUSE_ROTATE_SPEED;
    if (event.isControlDown()) speed *= CONTROL_MULTIPLIER;
    else if (event.isAltDown()) speed *= ALT_MULTIPLIER;

    double posX = -(mousePosX - mouseOldX);
    double posY = mousePosY - mouseOldY;

    double azimuth = posX * speed;
    double altitude = posY * speed;

    if (event.isShiftDown()) {
      this.camera.translate(azimuth, altitude);
    } else {
      this.camera.rotate(azimuth, -altitude);
    }
  }
  /**
   * Обработчик нажатия кнопок мыши. Завязан на левую кнопку.
   * Срабатывает на после отпускания
   * @param event - события нажатия и отпускания кнопки мыши
   */
  private void onMouseClicked(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) {
      Node res = event.getPickResult().getIntersectedNode();
      if (res != null && res.getParent() instanceof BaseMesh) {
        this.world.setPickedNode((BaseMesh)res.getParent());
      } else {
        this.world.delPickedNode();
      }
    }
  }

  /**
   * Инициализация клавиатуры
   */
  private void initKeyboard() {
    this.scene.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case DELETE:
          this.deleteMesh();
          break;
        case PRINTSCREEN:
          this.makeImage();
          break;
        case X:
          this.resetCamera(event);
          break;
        case F:
          this.focusCameraToMesh();
          break;
      }
    });
  }
  /**
   * Сбрасывает камеру в позицию по умолчанию
   * @param event - событие клавиатуры
   */
  private void resetCamera(KeyEvent event) {
    if (event.isAltDown()) this.camera.resetZoom();
    else if (event.isControlDown()) this.camera.resetRotate();
    else if (event.isShiftDown()) this.camera.resetTranslate();
    else this.camera.reset();
  }
  /**
   * Фокусирует камеру на выбранном объекте
   */
  private void focusCameraToMesh() {
    if (this.world.isSetPickedNode()) {
      BaseMesh mesh = this.world.getPickedMesh();
      this.camera.setTx(mesh.getTx());
      this.camera.setTy(mesh.getTy());
      this.camera.setTz(mesh.getTz());
    }
  }
  /**
   * Удаляет выбранные объект со сцены
   */
  private void deleteMesh() {
    this.world.deleteMesh();
  }
  /**
   * Создает изображение сцены
   */
  private void makeImage() {
    this.scene.makeImg();
  }
}
