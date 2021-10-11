package com.mk.editor.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Box;

// Класс создание куба
public class CubeMesh extends BaseMesh {
  private static int count = 1; // порядковый номер (id)

  /**
   * Конструктор
   */
  public CubeMesh() {
    super("Cube", CubeMesh.count++, new Box(100, 100, 100), new Box(100, 100, 100));
    this.init();
  }

  /**
   * Возвращает свойство ширины куба
   * @return ширина
   */
  public DoubleProperty widthProperty() {
    return ((Box)this.shape).widthProperty();
  }

  /**
   * Возвращает свойство высоты куба
   * @return высота
   */
  public DoubleProperty heightProperty() {
    return ((Box)this.shape).heightProperty();
  }

  /**
   * Возвращает свойство толщины куба
   * @return толщина
   */
  public DoubleProperty depthProperty() {
    return ((Box)this.shape).depthProperty();
  }

  /**
   * Связывает свойства ширины, высоты и толщины куба
   * с его объектом помощником
   */
  private void init() {
    ((Box)this.shape).widthProperty().addListener(event -> {
      double value = ((DoubleProperty)event).getValue();
      ((Box)this.shapeOutline).setWidth(value);
    });
    ((Box)this.shape).heightProperty().addListener(event -> {
      double value = ((DoubleProperty)event).getValue();
      ((Box)this.shapeOutline).setHeight(value);
    });
    ((Box)this.shape).depthProperty().addListener(event -> {
      double value = ((DoubleProperty)event).getValue();
      ((Box)this.shapeOutline).setDepth(value);
    });
  }
}
