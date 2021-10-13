package com.mk.editor.entities;

import javafx.beans.property.*;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Translate;

// Класс камеры приложения
public final class Camera3D extends Object3D {
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
    this.camera.getTransforms().add(this.ct);

    // добавления камеры в объект поворота по оси X
    this.altitude.addChild(this.camera);
    // добавления объект поворота в главную обертку
    this.addChild(this.altitude);

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
   * Возвращает свойство назначенное угла азимута
   * @return значение угла азимута
   */
  public DoubleProperty azimuthProperty() {
    return this.rzProperty();
  }
  /**
   * Возвращает свойство назначенное угла высоты
   * @return значение угла высоты
   */
  public DoubleProperty altitudeProperty() {
    return this.altitude.rx.angleProperty();
  }
  /**
   * Возвращает свойство назначенное зума
   * @return значение зума
   */
  public DoubleProperty zoomProperty() {
    return this.ct.zProperty();
  }

  /**
   * Смещает камеру относительно мира
   * @param newX - смещение по X относительно мира
   * @param newY - смещение по Y относительно мира
   */
  public void translate(double newX, double newY) {
    // перевод углов в радианы
    double rad = Math.toRadians(this.getRz());

    double cos = Math.cos(rad); // косинус полярного угла
    double sin = Math.sin(rad); // cинус полярного угла

    // расчет смещения по координатам X и Y
    // относительно центра мира
    double dx = newX * cos + -newY * sin;
    double dy = newX * sin + newY * cos;

    // смещение
    this.addTy(dy);
    this.addTx(dx);

    // синус угла высоты
    double axRad = Math.sin(Math.toRadians(this.altitude.getRx()));
    // смещение по Z, если синус угла высоты больше 0.90
    // примерно 25 градусов от оси Z мира
    if (Math.abs(axRad) > 0.88) {
      this.addTz(-newY * axRad);
    }
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
    // this.zoomProperty.set(String.valueOf(-this.ct.getZ()));
  }
  /**
   * Сброс камеры в исходное положение
   */
  public void reset() {
    this.resetTranslate();
    this.resetRotate();
    this.resetZoom();
  }
  /**
   * Сброс смещения камеры в исходное положение
   */
  public void resetTranslate() {
    this.setTx(0);
    this.setTy(0);
    this.setTz(0);
  }
  /**
   * Сброс поворота камеры в исходное положение
   */
  public void resetRotate() {
    this.setRz(0);
    this.altitude.setRx(-110);
  }
  /**
   * Сброс зума камеры в исходное положение
   */
  public void resetZoom() {
    this.ct.setZ(-85);
  }

  /**
   * Инициализация
   */
  private void init() {
    this.rotate(-22, -128);
    this.zoom(-85);
  }
}
