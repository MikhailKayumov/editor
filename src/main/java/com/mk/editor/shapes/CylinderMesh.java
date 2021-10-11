package com.mk.editor.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Cylinder;

// Класс создание цилиндра
public class CylinderMesh extends BaseMesh {
  private static int count = 1; // порядковый номер (id)

  /**
   * Конструктор
   */
  public CylinderMesh() {
    super(
      "Cylinder",
      CylinderMesh.count++,
      new Cylinder(50, 100),
      new Cylinder(50, 100)
    );

    this.init();
  }

  /**
   * Возвращает свойство радиуса цилиндра
   * @return радиус
   */
  public DoubleProperty radiusProperty() {
    return ((Cylinder)this.shape).radiusProperty();
  }

  /**
   * Возвращает свойство высоты цилиндра
   * @return высота
   */
  public DoubleProperty heightProperty() {
    return ((Cylinder)this.shape).heightProperty();
  }

  /**
   * Связывает свойства высоты и радиуса цилиндра
   * с его объектом помощником
   */
  private void init() {
    ((Cylinder)this.shape).radiusProperty().addListener(event -> {
      double value = ((DoubleProperty)event).getValue();
      ((Cylinder)this.shapeOutline).setRadius(value);
    });
    ((Cylinder)this.shape).heightProperty().addListener(event -> {
      double value = ((DoubleProperty)event).getValue();
      ((Cylinder)this.shapeOutline).setHeight(value);
    });
  }
}
