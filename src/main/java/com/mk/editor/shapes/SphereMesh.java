package com.mk.editor.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Sphere;

// Класс создания сферы
public class SphereMesh extends BaseMesh {
  private static int count = 1; // порядковый номер (id)

  /**
   * Конструктор
   */
  public SphereMesh() {
    super("Sphere", SphereMesh.count++, new Sphere(50), new Sphere(50));
    this.init();
  }

  /**
   * Возвращает свойство радиуса сферы
   * @return радиус
   */
  public DoubleProperty sphereRadius() {
    return ((Sphere)this.shape).radiusProperty();
  }

  /**
   * Связывает свойство радиуса сферы
   * с его объектом помощником
   */
  private void init() {
    ((Sphere)this.shape).radiusProperty().addListener(event -> {
      double value = ((DoubleProperty)event).getValue();
      ((Sphere)this.shapeOutline).setRadius(value);
    });
  }
}
