package com.mk.editor.entities;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.util.Collection;

// Основной контейнер для любого объекта
public class Object3D extends Group {
  // последовательность поворотов
  public enum RotateOrder { XYZ, XZY, YXZ, YZX, ZXY, ZYX }
  // перемещение
  protected final Translate t = new Translate(0.0, 0.0, 0.0);
  // повороты
  protected final Rotate rx = new Rotate(0, Rotate.X_AXIS);
  protected final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
  protected final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
  // масштаб
  protected final Scale s = new Scale(1, 1, 1);

  /**
   * Конструктор 1
   */
  public Object3D() {
    this(RotateOrder.XZY);
  }
  /**
   * Конструктор 2
   * @param rotateOrder - порядок вращения
   */
  public Object3D(RotateOrder rotateOrder) {
    super();
    // выбор порядка вращения
    switch (rotateOrder) {
      case XYZ:
        this.getTransforms().addAll(t, rz, ry, rx, s);
        break;
      case XZY:
        this.getTransforms().addAll(t, ry, rz, rx, s);
        break;
      case YXZ:
        this.getTransforms().addAll(t, rz, rx, ry, s);
        break;
      case YZX:
        this.getTransforms().addAll(t, rx, rz, ry, s); // для камеры
        break;
      case ZXY:
        this.getTransforms().addAll(t, ry, rx, rz, s);
        break;
      case ZYX:
        this.getTransforms().addAll(t, rx, ry, rz, s);
        break;
    }
  }

  // Методы для получения наблюдаемых свойств
  /**
   * Возвращает свойство назначенное X
   * @return значение X
   */
  public DoubleProperty xProperty() {
    return this.t.xProperty();
  }
  /**
   * Возвращает свойство назначенное Y
   * @return значение Y
   */
  public DoubleProperty yProperty() {
    return this.t.yProperty();
  }
  /**
   * Возвращает свойство назначенное Z
   * @return значение Z
   */
  public DoubleProperty zProperty() {
    return this.t.zProperty();
  }
  /**
   * Возвращает свойство назначенное углу поворота вокруг оси X
   * @return значение угла поворота вокруг оси X
   */
  public DoubleProperty rxProperty() {
    return this.rx.angleProperty();
  }
  /**
   * Возвращает свойство назначенное углу поворота вокруг оси Y
   * @return значение угла поворота вокруг оси Y
   */
  public DoubleProperty ryProperty() {
    return this.ry.angleProperty();
  }
  /**
   * Возвращает свойство назначенное углу поворота вокруг оси Z
   * @return значение угла поворота вокруг оси Z
   */
  public DoubleProperty rzProperty() {
    return this.rz.angleProperty();
  }
  /**
   * Возвращает свойство назначенное масштабу по оси X
   * @return значение масштаба по оси X
   */
  public DoubleProperty sxProperty() {
    return this.s.xProperty();
  }
  /**
   * Возвращает свойство назначенное масштабу по оси Y
   * @return значение масштаба по оси Y
   */
  public DoubleProperty syProperty() {
    return this.s.yProperty();
  }
  /**
   * Возвращает свойство назначенное масштабу по оси Z
   * @return значение масштаба по оси Z
   */
  public DoubleProperty szProperty() {
    return this.s.zProperty();
  }

  // операции перемещения
  /**
   * Получение текущего X
   * @return - X координата
   */
  public double getTx() {
    return this.t.getX();
  }
  /**
   * Установка текущего X
   * @param x - новая X координата
   */
  public void setTx(double x) {
    this.t.setX(x);
  }
  /**
   * Изменение текущего X
   * @param dx - смещение для текущей X координаты
   */
  public void addTx(double dx) {
    this.setTx(this.getTx() + dx);
  }

  /**
   * Получение текущего Y
   * @return - Y координата
   */
  public double getTy() {
    return this.t.getY();
  }
  /**
   * Установка текущего Y
   * @param y - новая Y координата
   */
  public void setTy(double y) {
    this.t.setY(y);
  }
  /**
   * Изменение текущего Y
   * @param dy - смещение для текущей Y координаты
   */
  public void addTy(double dy) {
    this.setTy(this.getTy() + dy);
  }

  /**
   * Получение текущего Z
   * @return - Z координата
   */
  public double getTz() {
    return this.t.getZ();
  }
  /**
   * Установка текущего Z
   * @param z - новая Z координата
   */
  public void setTz(double z) {
    this.t.setZ(z);
  }
  /**
   * Изменение текущего Z
   * @param dz - смещение для текущей Z координаты
   */
  public void addTz(double dz) {
    this.setTz(this.getTz() + dz);
  }

  // операции вращения
  /**
   * Получение текущего угла во круг оси X
   * @return - угол в градусах
   */
  public double getRx() {
    return this.rx.getAngle();
  }
  /**
   * Установка текущего угла во круг оси X
   * @param x - угол в градусах
   */
  public void setRx(double x) {
    this.rx.setAngle(x % 360);
  }
  /**
   * Изменение текущего угла во круг оси X
   * @param dx - угол в градусах
   */
  public void addRx(double dx) {
    this.setRx(this.getRx() + dx);
  }

  /**
   * Получение текущего угла во круг оси Y
   * @return - угол в градусах
   */
  public double getRy() {
    return this.ry.getAngle();
  }
  /**
   * Установка текущего угла во круг оси Y
   * @param y - угол в градусах
   */
  public void setRy(double y) {
    this.ry.setAngle(y % 360);
  }
  /**
   * Изменение текущего угла во круг оси Y
   * @param dy - угол в градусах
   */
  public void addRy(double dy) {
    this.setRy(this.getRy() + dy);
  }

  /**
   * Получение текущего угла во круг оси Z
   * @return - угол в градусах
   */
  public double getRz() {
    return this.rz.getAngle();
  }
  /**
   * Установка текущего угла во круг оси Z
   * @param z - угол в градусах
   */
  public void setRz(double z) {
    this.rz.setAngle(z % 360);
  }
  /**
   * Изменение текущего угла во круг оси Z
   * @param dz - угол в градусах
   */
  public void addRz(double dz) {
    this.setRz(this.getRz() + dz);
  }

  // операции масштабирования
  /**
   * Установка масштаба по оси X
   * @param x - угол в градусах
   */
  public void setSx(double x) {
    this.s.setX(x);
  }
  /**
   * Установка масштаба по оси Y
   * @param y - угол в градусах
   */
  public void setSy(double y) {
    this.s.setY(y);
  }
  /**
   * Установка масштаба по оси Z
   * @param z - угол в градусах
   */
  public void setSz(double z) {
    this.s.setZ(z);
  }

  // управление дочерними узлами
  /**
   * Добавляет узел
   * @param node - узел
   */
  public void addChild(Node node) {
    this.getChildren().add(node);
  }
  /**
   * Удаляет узел
   * @param node - узел
   */
  public void removeChild(Node node) {
    this.getChildren().remove(node);
  }
  /**
   * Добавляет узлы
   * @param nodes - узлы
   */
  public void addChildren(Node... nodes) {
    this.getChildren().addAll(nodes);
  }
  /**
   * Добавляет узлы
   * @param nodes - коллекция узлов
   */
  public void addChildren(Collection nodes) {
    this.getChildren().addAll(nodes);
  }
}
