package com.mk.editor.utils;

import com.mk.editor.entities.Object3D;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

// Объект создает узел содержащий оси координат, различных цветов
public final class Axes extends Object3D {
  /**
   * Конструктор
   * @param axesRadius - толщина оси
   * @param axesLength - длина оси
   */
  public Axes(double axesRadius, double axesLength) {
    super();

    Cylinder xAxis = this.buildXAxis(axesRadius, axesLength); // ось X
    Cylinder yAxis = this.buildYAxis(axesRadius, axesLength); // ось Y
    Cylinder zAxis = this.buildZAxis(axesRadius, axesLength); // ось Z

    this.getChildren().addAll(xAxis, yAxis, zAxis);
  }

  /**
   * Создает ось X
   * @param radius - толщина оси
   * @param len - длина оси
   * @return ось красного оттенка
   */
  private Cylinder buildXAxis(double radius, double len) {
    Cylinder axis = this.buildAxis(radius, len, AppColor.XAxisColor);
    axis.setTranslateX(len / 2);
    axis.setRotate(-90);
    return axis;
  }
  /**
   * Создает ось Y
   * @param radius - толщина оси
   * @param len - длина оси
   * @return ось зеленого оттенка
   */
  private Cylinder buildYAxis(double radius, double len) {
    Cylinder axis = this.buildAxis(radius, len, AppColor.YAxisColor);
    axis.setTranslateY(len / 2);
    return axis;
  }
  /**
   * Создает ось Z
   * @param radius - толщина оси
   * @param len - длина оси
   * @return ось синего оттенка
   */
  private Cylinder buildZAxis(double radius, double len) {
    Cylinder axis = this.buildAxis(radius, len, AppColor.ZAxisColor);
    axis.setTranslateZ(len / 2);
    axis.setRotationAxis(Rotate.X_AXIS);
    axis.setRotate(-90);
    return axis;
  }

  /**
   * Создает ось
   * @param r - толщина оси
   * @param l - длина оси
   * @param c - цвет оси
   * @return ось указанного цвета
   */
  private Cylinder buildAxis(double r, double l, Color c) {
    Cylinder axis = new Cylinder(r, l);
    axis.setMaterial(this.buildMaterial(c));
    return axis;
  }
  /**
   * Создает материал оси
   * @param color - цвет оси
   * @return материал
   */
  private PhongMaterial buildMaterial(Color color) {
    PhongMaterial mat = new PhongMaterial(color);
    return mat;
  }
}
