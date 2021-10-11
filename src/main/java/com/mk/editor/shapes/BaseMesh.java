package com.mk.editor.shapes;

import com.mk.editor.entities.Object3D;
import com.mk.editor.utils.AppColor;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;

// Базовый класс для всех 3D объектов
public abstract class BaseMesh extends Object3D {
  protected int number; // порядковый номер (id)
  protected String name; // имя объекта
  protected Shape3D shape; // форма объекта
  protected Shape3D shapeOutline; // форма обводки объекта
  protected PhongMaterial material = new PhongMaterial(); // материал объекта

  /**
   * Базовый конструктор
   * @param name - имя объекта
   * @param number - порядковый номер
   * @param shape - форма объекта
   * @param shapeOutline - форма обводки объекта
   */
  protected BaseMesh(String name, int number, Shape3D shape, Shape3D shapeOutline) {
    this.name = name;
    this.number = number;

    this.shape = shape;
    this.shapeOutline = shapeOutline;

    this.initShape();
  }

  /**
   * Устанавливает цвет материала
   * @param color - цвет
   */
  public void setMaterial(Color color) {
    this.material.setDiffuseColor(color);
  }

  /**
   * Возвращает цвет материала
   * @return цвет
   */
  public Color getMaterial() {
    return this.material.getDiffuseColor();
  }

  /**
   * Делает видимым обводку объекта при выборе
   */
  public void pick() {
    this.addChild(this.shapeOutline);
  }

  /**
   * Делает невидимым обводку объекта при потере фокуса
   */
  public void unpick(){
    this.removeChild(this.shapeOutline);
  }

  /**
   * Возвращает уникально имя объекта
   * @return имя
   */
  public String getName() {
    return this.name + "_" + this.number;
  }

  /**
   * Инициализирует объект при создании
   */
  protected void initShape() {
    this.shape.setMaterial(this.material);
    this.shape.setRotationAxis(Rotate.X_AXIS);
    this.shape.setRotate(90);
    this.shape.setCullFace(CullFace.NONE);

    this.shapeOutline.setDrawMode(DrawMode.LINE);
    this.shapeOutline.setMaterial(new PhongMaterial(AppColor.PickedColor));
    this.shapeOutline.setRotationAxis(Rotate.X_AXIS);
    this.shapeOutline.setRotate(90);
    this.shapeOutline.setCullFace(CullFace.NONE);

    this.addChild(this.shape);
  }
}
