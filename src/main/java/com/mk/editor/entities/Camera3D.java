package com.mk.editor.entities;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Translate;

// Класс камеры приложения
public class Camera3D extends Object3D {
  private final Camera camera = new PerspectiveCamera(true); // камера
  private final Object3D altitude = new Object3D(); // объект поворота по оси X
  private final Translate ct = new Translate(0, 0, 0); // для зума

  /**
   * Конструктор
   */
  public Camera3D() {
    super(Object3D.RotateOrder.YZX); // конструктор родителя

    this.camera.setNearClip(0.01); // ближняя область отсечения
    this.camera.setFarClip(10000.0); // дальняя область отсечения
    this.camera.getTransforms().add(ct);

    // добавления камеры в объект поворота по оси X
    this.altitude.getChildren().add(this.camera);
    // добавления объект поворота в главную обертку
    this.getChildren().add(this.altitude);

    // инициализация (опционально)
    this.init();
  }
  /**
   * Возвращает объект камеры
   * @return camera - объект камеры
   */
  public Camera getCamera() {
    return camera;
  }
  /**
   * Смещает камеру относительно мира
   * @param newX - смещение по X относительно мира
   * @param newY - смещение по Y относительно мира
   */
  public void translate(double newX, double newY) {
    double cos = Math.cos(this.getRz() * Math.PI / 180);
    double sin = Math.sin(this.getRz() * Math.PI / 180);

    double dx = newX * cos + -newY * sin;
    double dy = newX * sin + newY * cos;

    this.addTx(dx);
    this.addTy(dy);
  }
  /**
   * Устанавливает поворот камеры относительно мира и смещения
   * @param azimuth - поворот по Z (азимут)
   * @param altitude - поворот по X (высота)
   */
  public void rotate(double azimuth, double altitude) {
    this.addRz(azimuth);
    this.altitude.addRx(altitude);
  }
  /**
   * Эффект зума
   * @param z - дистанция от центра altitude
   */
  public void zoom(double z) {
    this.ct.setZ(this.ct.getZ() + z);
  }

  /**
   * Инициализация
   */
  private void init() {
    this.rotate(-19, -138);
    this.zoom(-500);
  }
}
